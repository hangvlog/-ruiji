package com.example.ruiji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ruiji.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Enzo Cotter on 2023/3/13.
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
