package wiki.laona.core.pojo.entity;

import lombok.Data;
import wiki.laona.core.pojo.good.Brand;
import wiki.laona.core.pojo.specification.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 模板实体
 * @author: laona
 * @create: 2021-02-06 22:00
 **/
@Data
public class TemplateEntity implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 规格列表
     */
    private List<Specification> specIds;
    /**
     * 品牌列表
     */
    private List<Brand> brandIds;
    /**
     * 自定义属性
     */
    private String customAttributeItems;
}
