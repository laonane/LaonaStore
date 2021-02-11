package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.seller.Seller;
import wiki.laona.service.SellerService;

/**
 * @description: 商家控制器
 * @author: laona
 * @create: 2021-02-09 13:59
 **/
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @RequestMapping("/getSellerAuditingList")
    public Result getSellerAuditingList(Integer page, Integer pageSize, @RequestBody Seller seller) {
        try {
            PageResult<Seller> sellers = sellerService.getSellerAuditingList(page, pageSize, seller);
            return new Result(ResultCode.SUCCESS, sellers);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "获取失败");
        }
    }

    @RequestMapping("/getSellerAuditing")
    public Result getSellerAuditing(String sid) {
        try {
            Seller seller = sellerService.getSellerAuditingBySellerId(sid);
            return new Result(ResultCode.SUCCESS, seller);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.QUERY_FAILED);
        }
    }

    @RequestMapping("/updateSellerAuditing")
    public Result updateSellerAuditing(@RequestBody Seller seller) {
        try {
            sellerService.updateSellerAuditingBySId(seller);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.QUERY_FAILED, "审核未通过");
        }
    }
}
