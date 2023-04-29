package com.example.ruiji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ruiji.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Enzo Cotter on 2023/4/19.
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
