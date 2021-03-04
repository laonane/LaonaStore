package wiki.laona.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.laona.core.pojo.entity.GoodsEntity;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.entity.ResultCode;
import wiki.laona.core.pojo.good.Goods;
import wiki.laona.service.GoodsService;

/**
 * @description: 商品控制器
 * @author: laona
 * @create: 2021-03-03 15:15
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @RequestMapping("/add")
    public Result addGoods(@RequestBody GoodsEntity goodsEntity) {
        System.out.println("goodsEntity = " + goodsEntity);
        try {
            // 获取当前登录用户 id
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("name = " + name);
            // 设置 seller_id (等于用户id)
            goodsEntity.getGoods().setSellerId(name);
            goodsService.add(goodsEntity);
            return new Result(ResultCode.SUCCESS, "商品添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.FAILED, "添加失败");
        }
    }

    @RequestMapping("/search")
    public PageResult<Goods> search(Integer page, Integer pageSize, @RequestBody Goods goods) {
        //获取当前登录用户的用户名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.setSellerId(userName);
        return goodsService.findPage(goods, page, pageSize);
    }

    @RequestMapping("/findGoodsEntityById")
    public GoodsEntity findGoodsEntityById(Long id) {
        return goodsService.findGoodsEntityById(id);
    }

}
