package com.fc.service;

import com.fc.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> findByUserId(Long userId);

    boolean removeByUserId(Long userId);

    boolean save(ShoppingCart shoppingCart);

    ShoppingCart getByUserId(ShoppingCart shoppingCart);

    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart sub(ShoppingCart shoppingCart);

    List<ShoppingCart> findByUserIdOnDesc(Long userId);
}
