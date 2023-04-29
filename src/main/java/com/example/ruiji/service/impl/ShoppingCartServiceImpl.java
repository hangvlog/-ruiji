package com.example.ruiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ruiji.entity.ShoppingCart;
import com.example.ruiji.mapper.ShoppingCartMapper;
import com.example.ruiji.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
