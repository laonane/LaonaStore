package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.seller.SellerDao;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.seller.Seller;
import wiki.laona.core.pojo.seller.SellerQuery;

import java.util.Date;
import java.util.List;

/**
 * @description: seller service 实现类
 * @author: laona
 * @create: 2021-02-08 18:55
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerDao sellerDao;

    @Override
    public void register(Seller seller) {
        seller.setCreateTime(new Date());
        sellerDao.insertSelective(seller);
    }

    @Override
    public PageResult<Seller> getSellerAuditingList(Integer page, Integer pageSize, Seller seller) {
        SellerQuery sellerQuery = new SellerQuery();
        Page startPage = PageHelper.startPage(page, pageSize);
        if (seller != null) {
            SellerQuery.Criteria criteria = sellerQuery.createCriteria();

            String status = seller.getStatus();
            String name = seller.getName();
            String nickName = seller.getNickName();
            // 按类型查询 0待审核，1通过审核，2审核未通过，3该商户已注销
            if (!Strings.isNullOrEmpty(status)) {
                criteria.andStatusEqualTo(status);
            }
            // 模糊查询
            if (!Strings.isNullOrEmpty(name)) {
                criteria.andNameLike("%" + name + "%");
            }
            if (!Strings.isNullOrEmpty(nickName)) {
                criteria.andNickNameLike("%" + nickName + "%");
            }
        }
        List<Seller> sellers = sellerDao.selectByExample(sellerQuery);
        return new PageResult<>(startPage.getTotal(), sellers);
    }

    @Override
    public Seller getSellerAuditingBySellerId(String sid) {
        return sellerDao.selectByPrimaryKey(sid);
    }

    @Override
    public void updateSellerAuditingBySId(Seller seller) {
        sellerDao.updateByPrimaryKeySelective(seller);
    }

    @Override
    public Seller findUser(String username) {
        return sellerDao.selectByPrimaryKey(username);
    }
}
