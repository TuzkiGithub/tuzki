package cn.spring.ssm.dao.common;

import cn.spring.ssm.model.Org;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.dao.common
 * User: 25414
 * Date: 2019/12/16
 * Time: 18:35
 * Description:
 */
@Mapper
public interface OrgMapper {

    @Select("select * from vum_org where pid = #{pid}")
    List<Org> getOrgByPid(String pid);

    @Select("select * from vum_org where id = #{id}")
    Org getOrgById(String id);

    @Select("select * from vum_org")
    List<Org> selectAll();

    @Update("update vum_org set type = #{type} where id = #{id}")
    void updateTypeById(@Param("id") String id, @Param("type") Integer type);
}
