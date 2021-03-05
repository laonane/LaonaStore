package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.curator.shaded.com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.ad.ContentCategoryDao;
import wiki.laona.core.pojo.ad.ContentCategory;
import wiki.laona.core.pojo.ad.ContentCategoryQuery;
import wiki.laona.core.pojo.entity.PageResult;

import java.util.List;

/**
 * @description: 广告分类服务实现类
 * @author: laona
 * @create: 2021-03-05 12:46
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryDao contentCategoryDao;

    @Override
    public PageResult<?> findPage(ContentCategory category, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        ContentCategoryQuery query = new ContentCategoryQuery();
        ContentCategoryQuery.Criteria criteria = query.createCriteria();
        if (category != null) {
            if (!Strings.isNullOrEmpty(category.getName())) {
                criteria.andNameLike("%" + category.getName() + "%");
            }
        }
        Page<ContentCategory> categoryList = (Page<ContentCategory>) contentCategoryDao.selectByExample(query);
        return new PageResult<>(categoryList.getTotal(), categoryList.getResult());
    }

    @Override
    public void add(ContentCategory category) {
        contentCategoryDao.insertSelective(category);
    }

    @Override
    public ContentCategory findOne(Long id) {
        return contentCategoryDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ContentCategory category) {
        contentCategoryDao.updateByPrimaryKeySelective(category);
    }

    @Override
    public void delete(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                contentCategoryDao.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public List<ContentCategory> findAll() {
        return contentCategoryDao.selectByExample(null);
    }

}
