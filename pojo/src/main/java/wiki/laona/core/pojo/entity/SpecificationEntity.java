package wiki.laona.core.pojo.entity;

import lombok.Data;
import wiki.laona.core.pojo.specification.Specification;
import wiki.laona.core.pojo.specification.SpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 规格实体
 * @author: laona
 * @create: 2021-02-06 18:45
 **/
@Data
public class SpecificationEntity implements Serializable {
    /**
     * 规格
     */
    private Specification spec;
    /**
     * 规格选项集合
     */
    private List<SpecificationOption> specOption;
}
