package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import wiki.laona.core.dao.good.BrandDao;
import wiki.laona.core.pojo.good.Brand;

import java.util.List;

/**
 * @program: LaonaStore
 * @description: core包 TestCore 实现类
 * @author: HuaiAnGG
 * @create: 2021-01-31 01:14
 **/
@Service
public class TestCoreImpl implements TestCore {

    @Autowired
    private BrandDao brandDao;

    @Override
    public String getName() {
        // 从数据库取数据
        List<Brand> brands = brandDao.selectByExample(null);
        System.out.println("brands = " + brands);
        return "This is server-seller-goods service, Dubbo ~!<br>laonane";
    }
}
