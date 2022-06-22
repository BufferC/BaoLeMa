package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dao.DishDao;
import com.fc.dto.DishDto;
import com.fc.entity.Category;
import com.fc.entity.Dish;
import com.fc.entity.DishFlavor;
import com.fc.service.CategoryService;
import com.fc.service.DishFlavorService;
import com.fc.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishDao dishDao;

    /**
     * 新增菜品，同时保存对应的口味数据
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        // 保存菜品的基本信息到菜品表dish
        dishDao.insert(dishDto);

        Long dishId = dishDto.getId();//菜品id

        // 菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().peek((item) -> item.setDishId(dishId)).collect(Collectors.toList());

        // 保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.insertByDishFlavorIds(flavors);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     */
    public DishDto getByIdWithFlavor(Long id) {
        // 查询菜品基本信息，从dish表查询
        Dish dish = dishDao.selectById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        // 查询当前菜品对应的口味信息，从dish_flavor表查询
        List<DishFlavor> flavors = dishFlavorService.findByDishId(dish.getId());
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        // 更新dish表基本信息
        dishDao.updateById(dishDto);

        // 清理当前菜品对应口味数据---dish_flavor表的delete操作
        dishFlavorService.removeByDishId(dishDto.getId());

        // 添加当前提交过来的口味数据---dish_flavor表的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().peek((item) -> item.setDishId(dishDto.getId())).collect(Collectors.toList());

        dishFlavorService.updateByDishFlavorIds(flavors);
    }

    @Override
    public Long getCountByCategoryId(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        return dishDao.selectCount(dishLambdaQueryWrapper);
    }

    @Override
    public Page<DishDto> getDishDtoPage(int page, int pageSize, String name) {
        //构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null, Dish::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行分页查询
        dishDao.selectPage(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

        List<Dish> records = pageInfo.getRecords();

        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);

            Long categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryService.findById(categoryId);

            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return dishDtoPage;
    }

    @Override
    public boolean changeStatus(Integer status, Long[] ids) {
        boolean flag = true;
        Dish dish = new Dish();
        for (Long id : ids) {
            dish.setId(id);
            dish.setStatus(status);

            if (dishDao.updateById(dish) == 0) {
                flag = false;
            }
        }

        return flag;
    }

    @Override
    public boolean deleteByIds(Long[] ids) {
        return dishDao.deleteBatchIds(Arrays.asList(ids)) > 0;
    }

    @Override
    public List<DishDto> getDisDtoLists(Dish dish) {
        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(dish.getName()), Dish::getName, dish.getName());
        queryWrapper.eq(null != dish.getCategoryId(), Dish::getCategoryId, dish.getCategoryId());
        //添加条件，查询状态为1（起售状态）的菜品
        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        List<Dish> dishes = dishDao.selectList(queryWrapper);

        Stream<DishDto> dishDtoStream = dishes.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Category category = categoryService.findById(item.getCategoryId());
            if (category != null) {
                dishDto.setCategoryName(category.getName());
            }
            dishDto.setFlavors(dishFlavorService.findByDishId(item.getId()));
            return dishDto;
        });

        return dishDtoStream.collect(Collectors.toList());
    }
}
