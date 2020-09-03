package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin //跨域
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/add")
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"品牌插入成功！");
    }

    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brands=brandService.finaAll();

        return new Result<>(true, StatusCode.OK,"查询品牌集合成功！",brands);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(value = "id")Integer id){
        Brand brand=brandService.finaById(id);
        return new Result<>(true,StatusCode.OK,"根据id查询品牌成功！",brand);
    }
}
