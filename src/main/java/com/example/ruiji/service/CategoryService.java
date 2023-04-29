package com.example.ruiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ruiji.entity.Category;

/**
 * Created by Enzo Cotter on 2023/4/18.
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
