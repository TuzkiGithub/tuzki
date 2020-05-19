package cn.spring.ssm.web.dao.common;

import cn.spring.ssm.web.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.dao.common
 * User: 25414
 * Date: 2019/10/18
 * Time: 16:14
 * Description:
 */
@Mapper
public interface UserMapper {
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "userId", before = false, resultType = Long.class)
    @Insert("insert into user(username,password) values(#{username},#{password})")
    void insert(User user);

    @Select("select * from user")
    List<User> selectAll();

    List<User> selectLimit();
}
