package com.fc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dto.SetMealDto;
import com.fc.entity.SetMeal;
import com.fc.entity.SetMealDish;

import java.util.List;

public interface SetMealService {
    Long getCountByCategoryId(Long categoryId);

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     */
    void saveWithDish(SetMealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     */
    void removeWithDish(List<Long> ids);

    Page<SetMealDto> findSetMealList(int page, int pageSize, String name);

    List<SetMeal> findByCondition(SetMeal setMeal);

    boolean changeStatus(Integer status, Long[] ids);

    SetMealDto getByIdWithSetMealDish(Long id);

    boolean updateWithSetMealDish(SetMealDto setMealDto);

    List<SetMealDish> getAllDish(Long id);
}
