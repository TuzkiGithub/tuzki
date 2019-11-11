package cn.spring.ssm.dao.slave;

import cn.spring.ssm.model.Student;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.dao
 * User: 25414
 * Date: 2019/9/11
 * Time: 17:49
 * Description:
 */
@org.apache.ibatis.annotations.Mapper
public interface StudentMapper_slave extends Mapper<Student> {
}
