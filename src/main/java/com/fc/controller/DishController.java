package com.fc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dto.DishDto;
import com.fc.entity.Dish;
import com.fc.service.DishService;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     */
    @PostMapping
    public ResultVO<String> save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());

        dishService.saveWithFlavor(dishDto);

        return ResultVO.success("新增菜品成功");
    }

    /**
     * 菜品信息分页查询
     */
    @GetMapping("page")
    public ResultVO<Page<DishDto>> page(int page, int pageSize, String name) {
        Page<DishDto> dishDtoPage = dishService.getDishDtoPage(page, pageSize, name);

        return ResultVO.success(dishDtoPage);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     */
    @GetMapping("/{id}")
    public ResultVO<DishDto> get(@PathVariable Long id) {

        DishDto dishDto = dishService.getByIdWithFlavor(id);

        return ResultVO.success(dishDto);
    }

    /**
     * 修改菜品
     */
    @PutMapping
    public ResultVO<String> update(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());

        dishService.updateWithFlavor(dishDto);

        return ResultVO.success("新增菜品成功");
    }

    /**
     * 批量删除
     */
    @DeleteMapping
    public ResultVO<String> delete(Long[] ids) {

        if (dishService.deleteByIds(ids)) {
            return ResultVO.success("删除菜品成功");
        }

        return ResultVO.success("删除菜品失败");
    }

    /**
     * 批量启停售
     * @param status 0：停售，1：起售
     */
    @PostMapping("status/{status}")
    public ResultVO<String> changeStatus(@PathVariable Integer status, Long[] ids) {
        if (dishService.changeStatus(status, ids)) {
            if (ids.length > 1) {
                return ResultVO.success("批量启停售成功");
            } else {
                return ResultVO.success("启停售成功");
            }
        }

        return ResultVO.error("删除失败");
    }

    /**
     * 获取菜品的口味以及所在的分类
     */
    @GetMapping("list")
    public ResultVO<List<DishDto>> list(Dish dish) {
        log.info("dish:{}", dish);

        List<DishDto> dishDtoLists = dishService.getDisDtoLists(dish);

        return ResultVO.success(dishDtoLists);
    }
}
