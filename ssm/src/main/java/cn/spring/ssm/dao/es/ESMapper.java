package cn.spring.ssm.dao.es;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.dao.es
 * User: 25414
 * Date: 2020/3/13
 * Time: 9:25
 * Description:
 */

@Mapper
public interface ESMapper {

    /**
     * 查询指定表全部数据
     *
     * @param table
     * @return
     */
    List<Map<Object, Object>> getEntryList(@Param("table") String table);
}
