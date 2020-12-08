package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> finaAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand finaById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer add(Brand brand) {

        return brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findList(Brand brand) {
        Example example=createExample(brand);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(brandMapper.selectAll());
    }

    @Override
    public PageInfo<Brand> findPage(Brand brand, int page, int size) {
        Example example=createExample(brand);
        PageHelper.startPage(page,size);
        return new PageInfo<>(brandMapper.selectByExample(example));
    }

    public Example createExample(Brand brand){
        Example example=new Example(Brand.class);
        Example.Criteria criteria=example.createCriteria();
        if (brand!=null){
            if (StringUtils.isNotEmpty(brand.getName())){
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            if (StringUtils.isNotEmpty(brand.getImage())){
                criteria.andLike("image","%"+brand.getImage()+"%");
            }
            if (StringUtils.isNotEmpty(brand.getLetter())){
                criteria.andLike("letter","%"+brand.getLetter()+"%");
            }
            if (brand.getSeq()!=null){
                criteria.andEqualTo("seq",brand.getSeq());
            }
            if (brand.getId()!=null){
                criteria.andEqualTo("id",brand.getId());
            }
        }

        return example;
    }

}
