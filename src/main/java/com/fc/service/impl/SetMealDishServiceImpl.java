package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fc.dao.SetMealDishDao;
import com.fc.entity.SetMealDish;
import com.fc.service.SetMealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SetMealDishServiceImpl implements SetMealDishService {
    @Autowired
    private SetMealDishDao setMealDishDao;

    @Override
    public boolean batchInsert(List<SetMealDish> setMealDishes) {
        boolean flag = true;
        for (SetMealDish setMealDish : setMealDishes) {
            if (setMealDishDao.insert(setMealDish) == 0) {
                flag = false;
            }
        }

        return flag;
    }

    @Override
    public boolean removeBySetMealIds(List<Long> ids) {
        //delete from set_meal_dish where set_meal_id in (1,2,3)
        LambdaQueryWrapper<SetMealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetMealDish::getSetmealId, ids);
        //删除关系表中的数据----set_meal_dish
        return setMealDishDao.delete(lambdaQueryWrapper) > 0;
    }

    @Override
    public List<SetMealDish> findBySetMealId(Long setMealId) {
        LambdaQueryWrapper<SetMealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetMealDish::getSetmealId, setMealId);

        return setMealDishDao.selectList(queryWrapper);
    }

    @Override
    public boolean removeBatchByIds(List<SetMealDish> oldSetMealDishes) {
        return setMealDishDao.deleteBatchIds(oldSetMealDishes) > 0;
    }

    @Override
    public boolean saveBatch(List<SetMealDish> setMealDishes) {
        for (SetMealDish setMealDish : setMealDishes) {
            setMealDishDao.insert(setMealDish);
        }
        return true;
    }

    @Override
    public boolean changeCopiesBySetMealId(Long id, Integer copies) {
        LambdaUpdateWrapper<SetMealDish> updateWrapper = new LambdaUpdateWrapper<>();

        updateWrapper.eq(SetMealDish::getSetmealId, id)
                .set(SetMealDish::getCopies, copies);
        return setMealDishDao.update(null, updateWrapper) > 0;
    }
}
