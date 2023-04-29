package com.example.ruiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ruiji.entity.User;
import com.example.ruiji.mapper.UserMapper;
import com.example.ruiji.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
