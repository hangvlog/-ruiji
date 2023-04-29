package com.example.ruiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ruiji.entity.Orders;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);
}
