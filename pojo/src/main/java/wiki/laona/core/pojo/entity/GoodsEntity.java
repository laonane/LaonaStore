package wiki.laona.core.pojo.entity;

import lombok.Data;
import wiki.laona.core.pojo.good.Goods;
import wiki.laona.core.pojo.good.GoodsDesc;
import wiki.laona.core.pojo.item.Item;

import java.io.Serializable;
import java.util.List;

/**
 * @description: goods 实体
 * @author: laona
 * @create: 2021-03-03 15:21
 **/
@Data
public class GoodsEntity  implements Serializable {
    private Goods goods;
    private GoodsDesc goodsDesc;
    private List<Item> itemList;
}
