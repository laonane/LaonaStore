package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.good.BrandDao;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.good.Brand;
import wiki.laona.core.pojo.good.BrandQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description: 品牌信息服务实现类
 * @author: laona
 * @create: 2021-02-03 16:37
 **/
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> findAllBrands() {
        return brandDao.selectByExample(null);
    }

    @Override
    public PageResult<Brand> findPageBrands(Integer page, Integer pageSize, Brand brand) {
        PageHelper.startPage(page, pageSize);
        BrandQuery brandQuery = new BrandQuery();
        // 模糊查询
        if (brand != null) {
            BrandQuery.Criteria criteria = brandQuery.createCriteria();
            if (!Strings.isNullOrEmpty(brand.getFirstChar())) {
                // 需要加上 %%
                criteria.andFirstCharLike("%" + brand.getFirstChar() + "%");
            }
            if (!Strings.isNullOrEmpty(brand.getName())) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
        }
        // 结果按照 id 降序排序（最后添加在最前面）
        brandQuery.setOrderByClause("id desc");
        Page<Brand> pageInfo = (Page<Brand>) brandDao.selectByExample(brandQuery);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getResult());
    }

    @Override
    public void addBrand(Brand brand) {
        brandDao.insertSelective(brand);
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void deleteBrandByIds(Long[] ids) {
        BrandQuery brandQuery = new BrandQuery();
        BrandQuery.Criteria criteria = brandQuery.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        brandDao.deleteByExample(brandQuery);
    }

    @Override
    public List<Map> findAllBrandsMap() {
        return brandDao.selectBrandOptionMap();
    }

}
