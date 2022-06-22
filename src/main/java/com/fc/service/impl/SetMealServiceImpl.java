package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dao.SetMealDao;
import com.fc.dto.SetMealDto;
import com.fc.entity.Category;
import com.fc.entity.SetMeal;
import com.fc.entity.SetMealDish;
import com.fc.exception.CustomException;
import com.fc.service.CategoryService;
import com.fc.service.SetMealDishService;
import com.fc.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetMealDishService setMealDishService;

    @Override
    public Long getCountByCategoryId(Long categoryId) {
        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<SetMeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        setMealLambdaQueryWrapper.eq(SetMeal::getCategoryId, categoryId);
        return setMealDao.selectCount(setMealLambdaQueryWrapper);
    }

    @Override
    public void saveWithDish(SetMealDto setMealDto) {
        //保存套餐的基本信息，操作setMeal，执行insert操作
        setMealDao.insert(setMealDto);

        List<SetMealDish> setMealDishes = setMealDto.getSetmealDishes();

        System.out.println(setMealDishes);

        Stream<SetMealDish> stream = setMealDishes.stream().peek((item) -> item.setSetmealId(setMealDto.getId()));

        setMealDishes = stream.collect(Collectors.toList());

        // 保存套餐和菜品的关联信息，操作set_meal_dish,执行insert操作
        setMealDishService.batchInsert(setMealDishes);
    }

    @Override
    public void removeWithDish(List<Long> ids) {
        // select count(*) from setmeal where id in (1,2,3) and status = 1
        // 查询套餐状态，确定是否可用删除
        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SetMeal::getId, ids);
        queryWrapper.eq(SetMeal::getStatus, 1);

        Long count = setMealDao.selectCount(queryWrapper);
        if (count > 0) {
            //如果不能删除，抛出一个业务异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        //如果可以删除，先删除套餐表中的数据---set_meal
        setMealDao.deleteBatchIds(ids);

        setMealDishService.removeBySetMealIds(ids);
    }

    @Override
    public Page<SetMealDto> findSetMealList(int page, int pageSize, String name) {
        //分页构造器对象
        Page<SetMeal> pageInfo = new Page<>(page, pageSize);
        Page<SetMealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null, SetMeal::getName, name);
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(SetMeal::getUpdateTime);

        setMealDao.selectPage(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<SetMeal> records = pageInfo.getRecords();

        List<SetMealDto> list = records.stream().map((item) -> {
            SetMealDto setmealDto = new SetMealDto();
            //对象拷贝
            BeanUtils.copyProperties(item, setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryService.findById(categoryId);
            if (category != null) {
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        return dtoPage.setRecords(list);
    }

    @Override
    public List<SetMeal> findByCondition(SetMeal setMeal) {
        //条件构造器
        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(setMeal.getName()), SetMeal::getName, setMeal.getName());
        queryWrapper.eq(null != setMeal.getCategoryId(), SetMeal::getCategoryId, setMeal.getCategoryId());
        queryWrapper.eq(null != setMeal.getStatus(), SetMeal::getStatus, setMeal.getStatus());
        queryWrapper.orderByDesc(SetMeal::getUpdateTime);
        return setMealDao.selectList(queryWrapper);
    }

    @Override
    public boolean changeStatus(Integer status, Long[] ids) {
        boolean flag = true;
        SetMeal setMeal = new SetMeal();
        for (Long id : ids) {
            setMeal.setId(id);
            setMeal.setStatus(status);

            if (setMealDao.updateById(setMeal) == 0) {
                flag = false;
            }
        }

        return flag;
    }

    @Override
    public SetMealDto getByIdWithSetMealDish(Long setMealId) {
        // 查询基本信息
        SetMeal setMeal = setMealDao.selectById(setMealId);

        SetMealDto setMealDto = new SetMealDto();
        BeanUtils.copyProperties(setMeal, setMealDto);

        List<SetMealDish> setMealDishes = setMealDishService.findBySetMealId(setMealId);
        setMealDto.setSetmealDishes(setMealDishes);

        Category category = categoryService.findById(setMeal.getCategoryId());
        setMealDto.setCategoryName(category.getName());

        return setMealDto;
    }

    @Override
    public boolean updateWithSetMealDish(SetMealDto setMealDto) {
        System.out.println(setMealDto);

        // 更新set_meal
        setMealDao.updateById(setMealDto);

        List<SetMealDish> setMealDishes = setMealDto.getSetmealDishes();

        List<SetMealDish> oldSetMealDishes = setMealDishService.findBySetMealId(setMealDto.getId());

        // 如果有菜品少了
        if (setMealDishes.size() < oldSetMealDishes.size()) {
            // 获取取消的菜品
            oldSetMealDishes.removeAll(setMealDishes);

            // 删除取消的菜品
            setMealDishService.removeBatchByIds(oldSetMealDishes);
        } else if (setMealDishes.size() > oldSetMealDishes.size()) {
            setMealDishes.removeAll(oldSetMealDishes);

            for (SetMealDish setMealDish : setMealDishes) {
                BeanUtils.copyProperties(oldSetMealDishes.get(0), setMealDish, "id", "dishId", "name", "price", "copies");
            }

            setMealDishService.saveBatch(setMealDishes);
        }

        for (SetMealDish setMealDish : setMealDishes) {
            setMealDishService.changeCopiesBySetMealId(setMealDto.getId(), setMealDish.getCopies());
        }

        return false;
    }

    @Override
    public List<SetMealDish> getAllDish(Long setMealId) {
        return setMealDishService.findBySetMealId(setMealId);
    }
}
