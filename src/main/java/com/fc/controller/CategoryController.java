package com.fc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.entity.Category;
import com.fc.service.CategoryService;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     */
    @PostMapping
    public ResultVO<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        if (categoryService.insert(category)) {
            return ResultVO.success("新增分类成功");
        }

        return ResultVO.error("新增分类失败");
    }

    /**
     * 分页查询
     */
    @GetMapping("page")
    public ResultVO<Page<Category>> page(int page,int pageSize){
        //分页查询
        Page<Category> pageInfo = categoryService.findByPage(page, pageSize);
        return ResultVO.success(pageInfo);
    }

    /**
     * 根据id删除分类
     */
    @DeleteMapping
    public ResultVO<String> delete(Long id){
        log.info("删除分类，id为：{}",id);

        if (categoryService.remove(id)) {
            return ResultVO.success("分类信息删除成功");
        }

        return ResultVO.error("分类信息删除失败");
    }

    /**
     * 根据id修改分类信息
     */
    @PutMapping
    public ResultVO<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);

        if (categoryService.updateById(category)) {
            return ResultVO.success("修改分类信息成功");
        }

        return ResultVO.error("修改分类信息失败");
    }

    /**
     * 根据条件查询分类数据
     */
    @GetMapping("list")
    public ResultVO<List<Category>> list(Category category){

        List<Category> list = categoryService.findByType(category.getType());
        return ResultVO.success(list);
    }
}
