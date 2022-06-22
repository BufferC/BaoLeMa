package com.fc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.dto.SetMealDto;
import com.fc.entity.SetMeal;
import com.fc.entity.SetMealDish;
import com.fc.service.SetMealService;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理
 */

@RestController
@RequestMapping("setmeal")
@Slf4j
public class SetMealController {
    @Autowired
    private SetMealService setMealService;

    /**
     * 新增套餐
     */
    @PostMapping
    public ResultVO<String> save(@RequestBody SetMealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);

        setMealService.saveWithDish(setmealDto);

        return ResultVO.success("新增套餐成功");
    }

    /**
     * 套餐分页查询
     */
    @GetMapping("page")
    public ResultVO<Page<SetMealDto>> page(int page,int pageSize,String name){
        Page<SetMealDto> dtoPage = setMealService.findSetMealList(page, pageSize, name);
        return ResultVO.success(dtoPage);
    }

    /**
     * 删除套餐
     */
    @DeleteMapping
    public ResultVO<String> delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);

        setMealService.removeWithDish(ids);

        return ResultVO.success("套餐数据删除成功");
    }

    @GetMapping("list")
    public ResultVO<List<SetMeal>> list(SetMeal setMeal) {
        log.info("setMeal:{}", setMeal);

        return ResultVO.success(setMealService.findByCondition(setMeal));
    }

    /**
     * 批量启停售
     * @param status 0：停售，1：起售
     */
    @PostMapping("status/{status}")
    public ResultVO<String> changeStatus(@PathVariable Integer status, Long[] ids) {
        if (setMealService.changeStatus(status, ids)) {
            if (ids.length > 1) {
                return ResultVO.success("批量删除成功");
            } else {
                return ResultVO.success("删除成功");
            }
        }

        return ResultVO.error("删除失败");
    }

    /**
     * 获取套餐以及包含的菜品
     */
    @GetMapping("/{id}")
    public ResultVO<SetMealDto> get(@PathVariable Long id) {
        SetMealDto setMealDto = setMealService.getByIdWithSetMealDish(id);

        return ResultVO.success(setMealDto);
    }

    /**
     * 修改套餐
     */
    @PutMapping
    public ResultVO<String> update(@RequestBody SetMealDto setMealDto){
        log.info(setMealDto.toString());

        setMealService.updateWithSetMealDish(setMealDto);

        return ResultVO.success("修改菜品成功");
    }

    /**
     * 获取套餐中的菜品
     */
    @GetMapping("dish/{id}")
    public ResultVO<List<SetMealDish>> getDishes(@PathVariable Long id) {

        List<SetMealDish> dishes = setMealService.getAllDish(id);

        return ResultVO.success(dishes);
    }
}
