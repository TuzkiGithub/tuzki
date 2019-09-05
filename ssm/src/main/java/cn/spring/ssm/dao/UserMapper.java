package cn.spring.ssm.dao;

import cn.spring.ssm.model.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.dao
 * User: 25414
 * Date: 2019/9/5
 * Time: 16:41
 * Description:
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
}
