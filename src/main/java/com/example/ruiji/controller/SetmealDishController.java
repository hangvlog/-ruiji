package com.example.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ruiji.common.R;
import com.example.ruiji.dto.SetmealDto;
import com.example.ruiji.entity.Category;
import com.example.ruiji.entity.Setmeal;
import com.example.ruiji.entity.SetmealDish;
import com.example.ruiji.service.CategoryService;
import com.example.ruiji.service.SetmealDishService;
import com.example.ruiji.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealDishController {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增套餐
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
//        log.info(setmealDto.toString());
        setmealService.saveWithDish(setmealDto);
        return R.success("ok");

    }

    /**
     * 套餐分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> getPage(int page, int pageSize, String name) {
        Page<Setmeal> setmealPage = new Page<>();
        Page<SetmealDto> setmealDtoPage = new Page<>();


        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.like(name != null, Setmeal::getName, name);
        setmealLambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);

        Page<Setmeal> page1 = setmealService.page(setmealPage, setmealLambdaQueryWrapper);

        BeanUtils.copyProperties(setmealPage, setmealDtoPage, "records");
        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                BeanUtils.copyProperties(item, setmealDto);
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(list);
//        List<SetmealDto> records = null;
        return R.success(setmealDtoPage);
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        setmealService.removeWithDish(ids);
        return R.success("删除套餐成功");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> getList(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(setmeal.getCategoryId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
        setmealDishLambdaQueryWrapper.eq(setmeal.getStatus()!=null,Setmeal::getStatus,setmeal.getStatus());
        List<Setmeal> list = setmealService.list(setmealDishLambdaQueryWrapper);
        return R.success(list);

    }



}
