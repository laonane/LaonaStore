package wiki.laona.core.pojo.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页结果
 * @author: laona
 * @create: 2021-02-04 01:43
 **/
@Data
public class PageResult<E> implements Serializable {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 当前页的数据
     */
    private List<E> rows;

    public PageResult(Long total, List<E> rows) {
        this.total = total;
        this.rows = rows;
    }
}
