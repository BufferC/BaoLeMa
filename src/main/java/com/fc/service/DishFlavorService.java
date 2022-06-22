package com.fc.service;

import com.fc.entity.DishFlavor;

import java.util.Collection;
import java.util.List;

public interface DishFlavorService {
    boolean updateByDishFlavorIds(Collection<DishFlavor> flavors);

    List<DishFlavor> findByDishId(Long dishId);

    boolean removeByDishId(Long dishId);

    boolean insertByDishFlavorIds(List<DishFlavor> flavors);
}
