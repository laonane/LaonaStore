package wiki.laona.core.dao.specification;

import org.apache.ibatis.annotations.Param;
import wiki.laona.core.pojo.specification.SpecificationOption;
import wiki.laona.core.pojo.specification.SpecificationOptionQuery;

import java.util.List;

public interface SpecificationOptionDao {
    int countByExample(SpecificationOptionQuery example);

    int deleteByExample(SpecificationOptionQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(SpecificationOption record);

    int insertSelective(SpecificationOption record);

    List<SpecificationOption> selectByExample(SpecificationOptionQuery example);

    SpecificationOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpecificationOption record,
                                 @Param("example") SpecificationOptionQuery example);

    int updateByExample(@Param("record") SpecificationOption record,
                        @Param("example") SpecificationOptionQuery example);

    int updateByPrimaryKeySelective(SpecificationOption record);

    int updateByPrimaryKey(SpecificationOption record);
}