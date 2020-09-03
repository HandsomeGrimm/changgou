package com.changgou.service;

import com.changgou.goods.pojo.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> finaAll();

    Brand finaById(Integer id);

    Integer add(Brand brand);
}
