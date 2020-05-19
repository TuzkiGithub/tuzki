package cn.spring.ssm.web.dao.common;


import cn.spring.ssm.web.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.dao.master
 * User: 25414
 * Date: 2019/9/12
 * Time: 14:07
 * Description:
 */
@Mapper
public interface RoomMapper {

    @Select("select * from room")
    List<Room> selectAll();
}
