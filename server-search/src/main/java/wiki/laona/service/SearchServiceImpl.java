package wiki.laona.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.core.pojo.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * @author laona
 * @description 搜索服务实现类
 * @create 2021-04-24 17:56
 **/
@Slf4j
@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Map<String, Object> search(Map paramMap) {
        // 处理搜索业务
        // 获取当前查询关键字
        String keywords = String.valueOf(paramMap.get("keywords"));
        int pageNo = Integer.parseInt(String.valueOf(paramMap.get("pageNo")));
        int pageSize = Integer.parseInt(String.valueOf(paramMap.get("pageSize")));

        // 查询条件
        SimpleQuery query = new SimpleQuery();
        Criteria criteria = new Criteria("item_keywords").is(keywords);
        query.addCriteria(criteria);

        // 分页查询
        // 从第几条数据开始， 每次查询多少条数据
        if (pageNo <= 0) {
            pageNo = 1;
        }
        // 计算当前查询数据的位置
        Integer start = (pageNo - 1) * pageSize;
        // 设置从第几条数据开始查询
        query.setOffset(start);
        // 每页多少条数据
        query.setRows(pageSize);

        // 查询返回数据
        ScoredPage<Item> items = solrTemplate.queryForPage(query, Item.class);

        // 封装结果集
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", items.getContent());
        resultMap.put("total", items.getTotalElements());
        resultMap.put("totalPages", items.getTotalPages());
        log.info("搜索结果集{}", resultMap);
        return resultMap;
    }
}
