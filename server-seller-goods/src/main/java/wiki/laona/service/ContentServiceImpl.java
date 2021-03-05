package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.ad.ContentDao;
import wiki.laona.core.pojo.ad.Content;
import wiki.laona.core.pojo.ad.ContentQuery;
import wiki.laona.core.pojo.entity.PageResult;

import java.util.List;
import java.util.Objects;

/**
 * @description: 广告分类服务实现类
 * @author: laona
 * @create: 2021-03-05 14:31
 **/
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Override
    public PageResult<Content> findPage(Content content, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        ContentQuery query = new ContentQuery();
        ContentQuery.Criteria criteria = query.createCriteria();
        if (content != null) {
            if (content.getTitle() != null && !"".equals(content.getTitle())) {
                criteria.andTitleLike("%" + content.getTitle() + "%");
            }
        }
        Page<Content> contentList = (Page<Content>) contentDao.selectByExample(query);
        return new PageResult<>(contentList.getTotal(), contentList.getResult());
    }

    @Override
    public void add(Content content) {
        // 1. 将新广告添加到数据库中
        contentDao.insertSelective(content);
    }

    @Override
    public Content findOne(Long id) {
        return contentDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Content content) {
        //将新的广告对象更新到数据库中
        contentDao.updateByPrimaryKeySelective(content);
    }

    @Override
    public void delete(Long[] ids) {
        if (Objects.nonNull(ids)) {
            for (Long id : ids) {
                //3. 根据广告id删除数据库中的广告数据
                contentDao.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public List<Content> findByCategoryId(Long categoryId) {
        ContentQuery contentQuery = new ContentQuery();
        ContentQuery.Criteria criteria = contentQuery.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        return contentDao.selectByExample(contentQuery);
    }
}
