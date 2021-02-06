package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.specification.SpecificationDao;
import wiki.laona.core.dao.specification.SpecificationOptionDao;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.SpecificationEntity;
import wiki.laona.core.pojo.specification.Specification;
import wiki.laona.core.pojo.specification.SpecificationOption;
import wiki.laona.core.pojo.specification.SpecificationOptionQuery;
import wiki.laona.core.pojo.specification.SpecificationQuery;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 规格服务实现类
 * @author: laona
 * @create: 2021-02-06 15:37
 **/
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;

    @Override
    public PageResult<Specification> findSpecPageList(Integer page, Integer pageSize, Specification spec) {
        SpecificationQuery specificationQuery = new SpecificationQuery();
        Page startPage = PageHelper.startPage(page, pageSize);
        // 结果按照 id 降序排序（最后添加在最前面）
        specificationQuery.setOrderByClause("id desc");
        if (spec != null) {
            SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
            if (!Strings.isNullOrEmpty(spec.getSpecName())) {
                criteria.andSpecNameLike("%" + spec.getSpecName() + "%");
            }
        }
        List<Specification> specifications = specificationDao.selectByExample(specificationQuery);
        return new PageResult<>(startPage.getTotal(), specifications);
    }

    @Override
    public void saveSpec(SpecificationEntity entity) {
        // 保存 specification 对象
        specificationDao.insertSelective(entity.getSpec());
        entity.getSpecOption().forEach(option -> {
            // 拿到 spec的id，保存到SpecOpt中
            option.setSpecId(entity.getSpec().getId());
            // 保存到数据库
            specificationOptionDao.insertSelective(option);
        });
    }

    @Override
    public void deleteSpecByIds(Long[] ids) {
        SpecificationQuery specificationQuery = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        specificationDao.deleteByExample(specificationQuery);
    }

    @Override
    public SpecificationEntity findSpecById(Long id) {
        SpecificationEntity entity = new SpecificationEntity();
        // 查询规格
        Specification specification = specificationDao.selectByPrimaryKey(id);
        entity.setSpec(specification);
        // 查询规格编号等于规格id 的规格option
        SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
        criteria.andSpecIdEqualTo(id);
        specificationOptionQuery.setOrderByClause("orders asc");
        // 查询所有符合条件的option
        List<SpecificationOption> options =
                specificationOptionDao.selectByExample(specificationOptionQuery);
        entity.setSpecOption(options);
        return entity;
    }

    @Override
    public void updateSpecEntity(SpecificationEntity entity) {
        Specification spec = entity.getSpec();
        // 设置 spec_id
        SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
        criteria.andSpecIdEqualTo(spec.getId());
        // 删除所有规格 option 信息
        specificationOptionDao.deleteByExample(specificationOptionQuery);
        // 重新插入规格 option 信息
        entity.getSpecOption().forEach(option -> {
            option.setSpecId(spec.getId());
            specificationOptionDao.insertSelective(option);
        });
        // 更新规格信息
        specificationDao.updateByPrimaryKeySelective(spec);
    }
}
