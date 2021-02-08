package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.template.TypeTemplateDao;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.template.TypeTemplate;
import wiki.laona.core.pojo.template.TypeTemplateQuery;

import java.util.Arrays;
import java.util.List;

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
        // typeTemplateDao.selectByPrimaryKey(id);
        return typeTemplateDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateTypeTemplate(TypeTemplate typeTemplate) {
        typeTemplateDao.updateByPrimaryKeySelective(typeTemplate);
    }
}
