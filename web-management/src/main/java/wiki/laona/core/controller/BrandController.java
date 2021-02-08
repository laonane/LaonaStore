package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.good.Brand;
import wiki.laona.service.BrandService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description: 品牌控制器
 * @author: laona
 * @create: 2021-02-03 16:40
 **/
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/getBrandList")
    @ResponseBody
    public List<Brand> getBrandList() {
        return brandService.findAllBrands();
    }

    @RequestMapping("/getBrandMap")
    @ResponseBody
    public List<Map> getBrandMap() {
        return brandService.findAllBrandsMap();
    }

    @RequestMapping("/getBrandPageList")
    @ResponseBody
    public PageResult<Brand> getBrandPageList(Integer page, Integer pageSize, @RequestBody Brand brand) {
        return brandService.findPageBrands(page, pageSize, brand);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Brand brand) {
        try {
            brandService.addBrand(brand);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.SAVE_FAILED);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Brand brand) {
        try {
            brandService.updateBrand(brand);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.SAVE_FAILED);
        }
    }

    @RequestMapping("/getBrandById")
    @ResponseBody
    public Result getBrandById(Long id) {
        try {
            Brand brand = brandService.getBrandById(id);
            return new Result(ResultCode.SUCCESS, brand);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED);
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBrandByIds(Long[] ids) {
        try {
            brandService.deleteBrandByIds(ids);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED);
        }
    }
}
