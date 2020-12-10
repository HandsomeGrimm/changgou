package com.changgou.goods.controller;

import com.changgou.goods.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
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

    /**
    *   @author: zhangy
    *   @Date: 2020/12/8 11:13
    *   @description: 添加品牌
    */
    @PostMapping("/add")
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"品牌插入成功！");
    }

    /**
     *   @author: zhangy
     *   @Date: 2020/12/8 11:13
     *   @description: 品牌列表
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brands=brandService.finaAll();

        return new Result<>(true, StatusCode.OK,"查询品牌集合成功！",brands);
    }

    /**
     *   @author: zhangy
     *   @Date: 2020/12/8 11:13
     *   @description: 根据id查询品牌
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(value = "id")Integer id){
        Brand brand=brandService.finaById(id);
        return new Result<>(true,StatusCode.OK,"根据id查询品牌成功！",brand);
    }

    /**
     *   @author: zhangy
     *   @Date: 2020/12/8 11:13
     *   @description: 修改品牌
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Brand brand,@PathVariable("id")Integer id){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     *   @author: zhangy
     *   @Date: 2020/12/8 11:13
     *   @description: 删除品牌
     */
    @DeleteMapping(value = "{id}")
    public Result delete(@PathVariable Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
    *   @author: zhangy
    *   @Date: 2020/12/8 11:28
    *   @description: 条件查询
    */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand){
        List<Brand> brandList=brandService.findList(brand);
        return new Result<List<Brand>>(true,StatusCode.OK,"查询成功",brandList);
    }

    /**
     * 分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //分页查询
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 分页搜索实现
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) Brand brand, @PathVariable  int page, @PathVariable  int size){
        //执行搜索
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 根据分类实现品牌列表查询
     * /brand/category/{id}  分类ID
     */
    @GetMapping(value = "/category/{id}")
    public Result<List<Brand>> findBrandByCategory(@PathVariable(value = "id")Integer categoryId){
        //调用Service查询品牌数据
        List<Brand> categoryList = brandService.findByCategory(categoryId);
        return new Result<List<Brand>>(true,StatusCode.OK,"查询成功！",categoryList);
    }
}
