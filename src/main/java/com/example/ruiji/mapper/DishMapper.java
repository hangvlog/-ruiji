package com.example.ruiji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ruiji.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Enzo Cotter on 2023/4/18.
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
