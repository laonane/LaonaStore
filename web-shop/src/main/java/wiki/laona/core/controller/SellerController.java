package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.seller.Seller;
import wiki.laona.service.SellerService;

/**
 * @description: seller控制器
 * @author: laona
 * @create: 2021-02-08 17:23
 **/
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Seller seller) {
        try {
            String password = seller.getPassword();
            String encodePassword = passwordEncoder.encode(password);
            seller.setPassword(encodePassword);
            System.out.println("encodePassword = " + encodePassword);
            sellerService.register(seller);
            return new Result(ResultCode.SUCCESS, "注册成功，等待运营商审核~！");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.SAVE_FAILED, "注册失败，该用户名已被占用。");
        }
    }
}
