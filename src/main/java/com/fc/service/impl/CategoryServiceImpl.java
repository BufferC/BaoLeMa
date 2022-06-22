package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dao.CategoryDao;
import com.fc.entity.Category;
import com.fc.exception.CustomException;
import com.fc.service.CategoryService;
import com.fc.service.DishService;
import com.fc.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetMealService setMealService;
    @Autowired
    private CategoryDao categoryDao;

    /**
     * 根据id删除分类，删除之前需要进行判断
     */
    @Override
    public boolean remove(Long id) {
        // 查询当前分类是否关联了菜品
        Long dishCount = dishService.getCountByCategoryId(id);

        // 如果已经关联，抛出一个业务异常
        if (dishCount > 0) {
            //已经关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        // 查询当前分类是否关联了套餐
        Long setMealCount = setMealService.getCountByCategoryId(id);

        if (setMealCount > 0) {
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除分类
        return categoryDao.deleteById(id) > 0;
    }

    @Override
    public boolean updateById(Category category) {
        return categoryDao.updateById(category) > 0;
    }

    @Override
    public Page<Category> findByPage(int page, int pageSize) {
        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);

        return categoryDao.selectPage(pageInfo, queryWrapper);
    }

    @Override
    public boolean insert(Category category) {
        return categoryDao.insert(category) > 0;
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryDao.selectById(categoryId);
    }

    @Override
    public List<Category> findByType(Integer type) {
        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(type != null, Category::getType, type);
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        return categoryDao.selectList(queryWrapper);
    }
}
