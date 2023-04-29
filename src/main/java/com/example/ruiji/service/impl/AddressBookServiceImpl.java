package com.example.ruiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ruiji.entity.AddressBook;
import com.example.ruiji.mapper.AddressBookMapper;
import com.example.ruiji.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
