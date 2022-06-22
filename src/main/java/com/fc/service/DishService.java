package com.fc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dto.DishDto;
import com.fc.entity.Dish;

import java.util.List;

public interface DishService {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
    void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新对应的口味信息
    void updateWithFlavor(DishDto dishDto);

    Long getCountByCategoryId(Long id);

    Page<DishDto> getDishDtoPage(int page, int pageSize, String name);

    boolean changeStatus(Integer status, Long[] ids);

    boolean deleteByIds(Long[] ids);

    List<DishDto> getDisDtoLists(Dish dish);
}
