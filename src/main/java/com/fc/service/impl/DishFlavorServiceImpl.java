package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.dao.DishFlavorDao;
import com.fc.entity.DishFlavor;
import com.fc.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DishFlavorServiceImpl implements DishFlavorService {
    @Autowired
    private DishFlavorDao dishFlavorDao;

    @Override
    public boolean updateByDishFlavorIds(Collection<DishFlavor> flavors) {
        boolean flag = true;
        for (DishFlavor dishFlavor : flavors) {
            if (dishFlavorDao.updateById(dishFlavor) == 0) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public List<DishFlavor> findByDishId(Long dishId) {
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishId);
        return dishFlavorDao.selectList(queryWrapper);
    }

    @Override
    public boolean removeByDishId(Long dishId) {
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishId);
        return dishFlavorDao.delete(queryWrapper) > 0;
    }

    @Override
    public boolean insertByDishFlavorIds(List<DishFlavor> flavors) {
        boolean flag = true;
        for (DishFlavor dishFlavor : flavors) {
            if (dishFlavorDao.insert(dishFlavor) == 0) {
                flag = false;
            }
        }
        return flag;
    }
}
