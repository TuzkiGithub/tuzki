package cn.spring.ssm.dao.common;

import cn.spring.ssm.model.LocalMsgVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select * from local_message")
    List<LocalMsgVO> selectAll();
}
