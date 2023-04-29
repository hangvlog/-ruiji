package com.example.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.ruiji.common.BaseContext;
import com.example.ruiji.common.R;
import com.example.ruiji.entity.ShoppingCart;
import com.example.ruiji.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@RestController
@Slf4j
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     *
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        //设置用户id
        Long userid = BaseContext.getCurrentId();
        shoppingCart.setUserId(userid);

        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //判断当前添加是否购物车已经有
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userid);

        if (dishId != null) {
            //dish
            shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            //setmeal
            shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

//        进行查询
        ShoppingCart one = shoppingCartService.getOne(shoppingCartLambdaQueryWrapper);
        //
        if (one != null){
//            shoppingCartService.save(shoppingCart);
            one.setNumber(one.getNumber()+1);
            shoppingCartService.updateById(one);
        }else{
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            one = shoppingCart;
        }
        return R.success(one);
    }

    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        //获取用户id
        Long userid = BaseContext.getCurrentId();
        //查询
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId,userid);

        List<ShoppingCart> list = shoppingCartService.list(shoppingCartLambdaQueryWrapper);

        return R.success(list);
    }

}
