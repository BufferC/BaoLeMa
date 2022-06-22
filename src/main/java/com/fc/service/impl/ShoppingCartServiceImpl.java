package com.fc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.common.BaseContext;
import com.fc.dao.ShoppingCartDao;
import com.fc.entity.ShoppingCart;
import com.fc.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Override
    public List<ShoppingCart> findByUserId(Long userId) {
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, userId);
        return shoppingCartDao.selectList(wrapper);
    }

    @Override
    public boolean removeByUserId(Long userId) {
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, userId);
        return shoppingCartDao.delete(wrapper) > 0;
    }

    @Override
    public boolean save(ShoppingCart shoppingCart) {
        return shoppingCartDao.insert(shoppingCart) > 0;
    }

    // 查询当前菜品或者套餐是否在购物车中
    @Override
    public ShoppingCart getByUserId(ShoppingCart shoppingCart) {
        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId = BaseContext.getCurrentId();

        shoppingCart.setUserId(currentId);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);

        if (dishId != null) {
            //添加到购物车的是菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            //添加到购物车的是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }
        return shoppingCartDao.selectOne(queryWrapper);
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        ShoppingCart cartServiceOne = getByUserId(shoppingCart);

        if (cartServiceOne != null) {
            //如果已经存在，就在原来数量基础上加一
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartDao.updateById(cartServiceOne);
        } else {
            //如果不存在，则添加到购物车，数量默认就是一
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartDao.insert(shoppingCart);
            cartServiceOne = shoppingCart;
        }
        return cartServiceOne;
    }

    @Override
    public ShoppingCart sub(ShoppingCart shoppingCart) {
        ShoppingCart cartServiceOne = getByUserId(shoppingCart);
        Integer number = cartServiceOne.getNumber();
        cartServiceOne.setNumber(number - 1);

        if (cartServiceOne.getNumber() > 0) {
            // 购物车还有剩余，直接在原来数量基础上减一
            shoppingCartDao.updateById(cartServiceOne);
            return cartServiceOne;
        } else {
            LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
            // 购物车中没有了，直接删除
            queryWrapper.eq(ShoppingCart::getDishId, shoppingCart.getDishId());
            queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());

            shoppingCartDao.delete(queryWrapper);
            return new ShoppingCart();
        }
    }

    @Override
    public List<ShoppingCart> findByUserIdOnDesc(Long userId) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        return shoppingCartDao.selectList(queryWrapper);
    }
}
