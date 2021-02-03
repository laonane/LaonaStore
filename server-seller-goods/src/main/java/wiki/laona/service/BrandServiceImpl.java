package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import wiki.laona.core.dao.good.BrandDao;
import wiki.laona.core.pojo.good.Brand;

import java.util.List;

/**
 * @description: 品牌信息服务实现类
 * @author: laona
 * @create: 2021-02-03 16:37
 **/
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandDao brandDao;

    /**
     * 获取所有品牌
     *
     * @return 品牌信息列表
     */
    @Override
    public List<Brand> findAllBrands() {
        return brandDao.selectByExample(null);
    }
}
