package wiki.laona.core.dao.good;

import org.apache.ibatis.annotations.Param;
import wiki.laona.core.pojo.good.Brand;
import wiki.laona.core.pojo.good.BrandQuery;

import java.util.List;
import java.util.Map;

public interface BrandDao {
    int countByExample(BrandQuery example);

    int deleteByExample(BrandQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandQuery example);

    Brand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandQuery example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandQuery example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    /**
     * 获取品牌下拉列表的值
     * @return
     */
    List<Map> selectBrandOptionMap();
}