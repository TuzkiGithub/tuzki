package cn.spring.app1.mapper;

import cn.spring.app1.model.LocalMsgVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.dao.common
 * User: 25414
 * Date: 2020/1/10
 * Time: 9:20
 * Description:
 */
@Mapper
public interface LocalMsgMapper {

    @Insert("insert into local_message values(#{id},#{msg},#{status},#{create_date})")
    void save(LocalMsgVO localMsgVO);

    @Update("update local_message set status = #{status} where msg = #{msg}")
    void update(@Param("msg") String msg, @Param("status") String status);
}
