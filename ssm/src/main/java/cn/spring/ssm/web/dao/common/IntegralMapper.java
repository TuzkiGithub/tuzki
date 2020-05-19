package cn.spring.ssm.web.dao.common;

import cn.spring.ssm.web.model.Integral;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.mq.dao
 * User: 25414
 * Date: 2019/10/9
 * Time: 18:11
 * Description:
 */
@Mapper
public interface IntegralMapper {

    @Insert("insert into integral(integral,user_id) values(#{integral},#{user_id})")
    Boolean insert(Integral integral);
}
