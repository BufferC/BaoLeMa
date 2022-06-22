package com.fc.controller;

import com.fc.common.BaseContext;
import com.fc.entity.ShoppingCart;
import com.fc.service.ShoppingCartService;
import com.fc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车
 */
@Slf4j
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     */
    @PostMapping("add")
    public ResultVO<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart = shoppingCartService.add(shoppingCart);

        if (shoppingCart != null) {
            return ResultVO.success(shoppingCart);
        }

        return ResultVO.error("添加失败");
    }

    /**
     * 减少购物车菜品
     */
    @PostMapping("sub")
    public ResultVO<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart = shoppingCartService.sub(shoppingCart);

        return ResultVO.success(shoppingCart);
    }

    /**
     * 查看购物车
     */
    @GetMapping("list")
    public ResultVO<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCartService.findByUserIdOnDesc(BaseContext.getCurrentId());

        return ResultVO.success(list);
    }

    /**
     * 清空购物车
     */
    @DeleteMapping("clean")
    public ResultVO<String> clean() {
        shoppingCartService.removeByUserId(BaseContext.getCurrentId());

        return ResultVO.success("清空购物车成功");
    }
}