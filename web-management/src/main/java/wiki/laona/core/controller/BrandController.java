package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
}
