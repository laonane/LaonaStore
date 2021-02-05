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

import java.util.List;

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

    @RequestMapping("/getBrandPageList")
    @ResponseBody
    public PageResult<Brand> getBrandPageList(Integer page, Integer pageSize) {
        // 在这调用 service
        PageResult<Brand> pageBrands = brandService.findPageBrands(page, pageSize);
        System.out.println("pageBrands = " + pageBrands);
        return pageBrands;
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
}
