package com.example.ruiji.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ruiji.common.CustumException;
import com.example.ruiji.entity.Category;
import com.example.ruiji.entity.Dish;
import com.example.ruiji.entity.Setmeal;
import com.example.ruiji.mapper.CategoryMapper;
import com.example.ruiji.service.CategoryService;
import com.example.ruiji.service.DishService;
import com.example.ruiji.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Enzo Cotter on 2023/4/18.
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id删除分类，删除前判断关联否
     * @param id
     */
    @Override
    public void remove(Long id) {
//        id关联了菜品
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        if (count1>0){
            throw new CustumException("当前分类下关联了菜品，无法删除");
        }
//        id关联了套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = dishService.count(dishLambdaQueryWrapper);
        if (count2>0){
            throw new CustumException("当前分类下关联了套餐，无法删除");
        }
        super.removeById(id);
    }
}
