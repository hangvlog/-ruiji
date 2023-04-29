package com.example.ruiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ruiji.entity.DishFlavor;
import com.example.ruiji.mapper.DishFlavorMapper;
import com.example.ruiji.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
