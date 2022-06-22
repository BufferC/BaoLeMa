package com.fc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.entity.Category;

import java.util.List;

public interface CategoryService {
    boolean remove(Long id);

    boolean updateById(Category category);

    Page<Category> findByPage(int page, int pageSize);

    boolean insert(Category category);

    Category findById(Long categoryId);

    List<Category> findByType(Integer type);
}
