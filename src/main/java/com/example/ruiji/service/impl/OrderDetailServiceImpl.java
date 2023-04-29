package com.example.ruiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ruiji.entity.OrderDetail;
import com.example.ruiji.mapper.OrderDetailMapper;
import com.example.ruiji.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
