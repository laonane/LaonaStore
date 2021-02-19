package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.dao.item.ItemCatDao;
import wiki.laona.core.dao.item.ItemDao;
import wiki.laona.core.pojo.entity.PageResult;
import wiki.laona.core.pojo.entity.Result;
import wiki.laona.core.pojo.item.Item;
import wiki.laona.core.pojo.item.ItemCat;
import wiki.laona.core.pojo.item.ItemCatQuery;

import java.util.List;

/**
 * @description: 条目服务实现类
 * @author: laona
 * @create: 2021-02-12 15:07
 **/
@Service
@Transactional
public class ItemCategoryCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCatDao itemCatDao;

    @Override
    public List<ItemCat> findItemPageList(Long parentId) {
        ItemCatQuery itemCatQuery = new ItemCatQuery();
        if (parentId != null) {
            ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();
            criteria.andParentIdEqualTo(parentId);
        }
        return itemCatDao.selectByExample(itemCatQuery);
    }

    @Override
    public ItemCat findItemCategoryById(Long id) {
        return itemCatDao.selectByPrimaryKey(id);
    }
}
