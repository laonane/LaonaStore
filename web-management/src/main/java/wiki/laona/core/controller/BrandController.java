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
        return brandService.findPageBrands(page, pageSize);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Brand brand) {
        System.out.println("brand = " + brand);
        try {
            brandService.addBrand(brand);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.SAVE_FAILED);
        }
    }
}
