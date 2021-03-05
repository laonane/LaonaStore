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

/**
 * @description: 广告分类服务实现类
 * @author: laona
 * @create: 2021-03-05 12:46
 **/
@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryDao categoryDao;

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
        Page<ContentCategory> categoryList = (Page<ContentCategory>) categoryDao.selectByExample(query);
        return new PageResult<>(categoryList.getTotal(), categoryList.getResult());
    }

}
