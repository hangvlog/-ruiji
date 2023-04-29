package com.example.ruiji.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ruiji.common.CustumException;
import com.example.ruiji.dto.SetmealDto;
import com.example.ruiji.entity.Setmeal;
import com.example.ruiji.entity.SetmealDish;
import com.example.ruiji.mapper.SetmealMapper;
import com.example.ruiji.service.SetmealDishService;
import com.example.ruiji.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Enzo Cotter on 2023/4/18.
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 添加套餐 setmeal/setmeal_dish
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        //setmeal
        this.save(setmealDto);
        //setmeal_dish
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐及对应菜品链接 setmeal/setmeal_dish
     * @param ids
     */
    @Transactional
    public void removeWithDish(List<Long> ids){
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId,ids);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus,1);

        int count = this.count(setmealLambdaQueryWrapper);
        if (count>0){
            throw new CustumException("套餐正在售卖中，无法删除");
        }

        //setmeal
        this.removeByIds(ids);
        //setmeal_dish
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(setmealDishLambdaQueryWrapper);
    }

}
