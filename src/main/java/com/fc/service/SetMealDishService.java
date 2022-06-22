package com.fc.service;

import com.fc.entity.SetMealDish;

import java.util.List;

public interface SetMealDishService {
    boolean batchInsert(List<SetMealDish> setMealDishes);

    boolean removeBySetMealIds(List<Long> ids);

    List<SetMealDish> findBySetMealId(Long setMealId);

    boolean removeBatchByIds(List<SetMealDish> oldSetMealDishes);

    boolean saveBatch(List<SetMealDish> setMealDishes);

    boolean changeCopiesBySetMealId(Long id, Integer copies);
}
