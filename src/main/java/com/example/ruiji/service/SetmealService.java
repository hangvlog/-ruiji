package com.example.ruiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ruiji.dto.SetmealDto;
import com.example.ruiji.entity.Setmeal;

import java.util.List;

/**
 * Created by Enzo Cotter on 2023/4/18.
 */
public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> ids);
}
