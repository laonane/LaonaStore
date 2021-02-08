package wiki.laona.core.dao.specification;

import org.apache.ibatis.annotations.Param;
import wiki.laona.core.pojo.specification.Specification;
import wiki.laona.core.pojo.specification.SpecificationQuery;

import java.util.List;
import java.util.Map;

public interface SpecificationDao {
    int countByExample(SpecificationQuery example);

    int deleteByExample(SpecificationQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Specification record);

    int insertSelective(Specification record);

    List<Specification> selectByExample(SpecificationQuery example);

    Specification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Specification record, @Param("example") SpecificationQuery example);

    int updateByExample(@Param("record") Specification record, @Param("example") SpecificationQuery example);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);

    /**
     * 获取规格下拉数据
     * @return
     */
    List<Map> findAllSpecificationsMap();
}