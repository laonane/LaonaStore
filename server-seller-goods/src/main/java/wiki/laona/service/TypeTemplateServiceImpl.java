package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.specification.SpecificationOptionDao;
import wiki.laona.core.dao.template.TypeTemplateDao;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.specification.SpecificationOption;
import wiki.laona.core.pojo.specification.SpecificationOptionQuery;
import wiki.laona.core.pojo.template.TypeTemplate;
import wiki.laona.core.pojo.template.TypeTemplateQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 类型模板 service实现类
 * @author: laona
 * @create: 2021-02-06 22:18
 **/
@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateDao typeTemplateDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;

    @Override
    public PageResult<TypeTemplate> findTemplatePageList(Integer page, Integer pageSize, TypeTemplate template) {
        Page startPage = PageHelper.startPage(page, pageSize);
        TypeTemplateQuery typeTemplateQuery = new TypeTemplateQuery();
        // 降序显示
        typeTemplateQuery.setOrderByClause("id desc");
        if (template != null) {
            TypeTemplateQuery.Criteria criteria = typeTemplateQuery.createCriteria();
            if (!Strings.isNullOrEmpty(template.getName())) {
                criteria.andNameLike("%" + template.getName() + "%");
            }
        }
        List<TypeTemplate> typeTemplateList = typeTemplateDao.selectByExample(typeTemplateQuery);
        return new PageResult<>(startPage.getTotal(), typeTemplateList);
    }

    @Override
    public void deleteTempByIds(Long[] ids) {
        TypeTemplateQuery typeTemplateQuery = new TypeTemplateQuery();
        TypeTemplateQuery.Criteria criteria = typeTemplateQuery.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        typeTemplateDao.deleteByExample(typeTemplateQuery);
    }

    @Override
    public void addTypeTemplate(TypeTemplate typeTemplate) {
        typeTemplateDao.insertSelective(typeTemplate);
    }

    @Override
    public TypeTemplate getTemplateById(Long id) {
        return typeTemplateDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateTypeTemplate(TypeTemplate typeTemplate) {
        typeTemplateDao.updateByPrimaryKeySelective(typeTemplate);
    }

    @Override
    public List<Map> findBySpecWithId(Long id) {
        // 根据id 查询模板
        TypeTemplate template = typeTemplateDao.selectByPrimaryKey(id);
        // 可能没有对应的 模板
        if (Objects.nonNull(template)) {
            // 取出规格集合
            String specIds = template.getSpecIds();
            // 把String转成集合
            List<Map> maps = JSON.parseArray(specIds, Map.class);
            // 遍历每个规格，找出规格选项    {"id":43,"spec_name":"选择版本"}
            maps.forEach(specMap -> {
                // 取出id
                Long specId = Long.parseLong(String.valueOf(specMap.get("id")));
                // 设置查询结果为 specId = id
                SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
                SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
                criteria.andSpecIdEqualTo(specId);
                // 获取结果集
                List<SpecificationOption> specificationOptions =
                        specificationOptionDao.selectByExample(specificationOptionQuery);
                specMap.put("specificationOptions", specificationOptions);
            });
            return maps;
        }
        return null;
    }
}
