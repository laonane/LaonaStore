package wiki.laona.service;

import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.seller.Seller;

/**
 * @description: seller service 接口
 * @author: laona
 * @create: 2021-02-08 18:53
 **/
public interface SellerService {

    /**
     * 商户入驻
     *
     * @param seller 商户
     */
    void register(Seller seller);

    /**
     * 分页查询所有待审核商家信息
     * @param page 当前页码
     * @param pageSize 一页有多少条数据
     * @param seller 商家信息列表
     */
    PageResult<Seller> getSellerAuditingList(Integer page, Integer pageSize, Seller seller);

    /**
     * 通过 sellerId  获取商家信息
     * @param sid 商家id
     * @return 商家信息
     */
    Seller getSellerAuditingBySellerId(String sid);

    /**
     * 更新商家审核状态
     * @param seller 商家信息
     */
    void updateSellerAuditingBySId(Seller seller);

    /**
     * 通过商家名查询商家
     * @param username 商家名
     * @return {@linkplain Seller} 商家
     */
    Seller findUser(String username);

}
