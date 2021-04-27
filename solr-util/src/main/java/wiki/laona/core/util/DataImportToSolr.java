package wiki.laona.core.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;
import wiki.laona.core.dao.item.ItemDao;
import wiki.laona.core.pojo.item.Item;
import wiki.laona.core.pojo.item.ItemQuery;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author laona
 * @description 数据导入到solr
 * @create 2021-04-24 15:58
 **/
@Component
public class DataImportToSolr {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private SolrTemplate solrTemplate;

    public void importItemToSolr() {
        // 1. 查询所有库存数据（通过审核的库存）
        ItemQuery itemQuery = new ItemQuery();
        ItemQuery.Criteria criteria = itemQuery.createCriteria();
        criteria.andStatusEqualTo("2");

        List<Item> items = itemDao.selectByExample(itemQuery);
        if (Objects.nonNull(items)) {
            // 处理规格数据 str -> map
            for (Item item : items) {
                String spec = item.getSpec();
                // 装成 json 对此昂
                Map<String, String> map = JSON.parseObject(spec, Map.class);
                item.setSpecMap(map);
            }
        }
        // 2. 把查询出的数据保存到索引库中
        solrTemplate.saveBeans(items);
        solrTemplate.commit();
    }


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
        // DataImportToSolr dataImportToSolr = new DataImportToSolr();
        // dataImportToSolr.importItemToSolr();
        DataImportToSolr dataImportToSolr = (DataImportToSolr) context.getBean("dataImportToSolr");
        dataImportToSolr.importItemToSolr();
    }
}
