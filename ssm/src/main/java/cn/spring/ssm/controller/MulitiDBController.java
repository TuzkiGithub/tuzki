package cn.spring.ssm.controller;

import cn.spring.ssm.dao.master.StudentMapper_master;
import cn.spring.ssm.dao.slave.StudentMapper_slave;
import cn.spring.ssm.enum_.ResultEnum;
import cn.spring.ssm.model.Student;
import cn.spring.ssm.util.JSONResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.controller
 * User: 25414
 * Date: 2019/9/11
 * Time: 17:51
 * Description:测试数据库主从，需要开启虚拟机
 */
@RestController
public class MulitiDBController {
    @Autowired
    private StudentMapper_slave studentMapper_slave_slave;

    @Autowired
    private StudentMapper_master studentMapper_master_master;


    /**
     * 主库写
     *
     * @return
     */
    @RequestMapping(value = "master_insert/{id}")
    @Transactional
    public String masterInsert(@PathVariable("id") Integer id) throws JsonProcessingException {

        Student student = new Student();
        student.setId(id)
                .setAge(22)
                .setName("haha");
        studentMapper_master_master.insert(student);
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }


    /**
     * 测试分布式事务（不使用事务）
     *
     * @return
     */
    @RequestMapping(value = "transaction_before/{id1}/{id2}")
    public String automikosTransactionBefore(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2) throws JsonProcessingException {
        Student student1 = new Student();
        student1.setId(id1)
                .setAge(22)
                .setName("hello");

        Student student2 = new Student();
        student2.setId(id2)
                .setAge(22)
                .setName("hello");
        studentMapper_master_master.insert(student1);
        studentMapper_slave_slave.insert(student2);
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }


    /**
     * 测试分布式事务（使用事务）
     *
     * @return
     */
    @RequestMapping(value = "transaction_after/{id1}/{id2}")
    @Transactional
    public String automikosTransactionAfter(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2) throws JsonProcessingException {
        Student student1 = new Student();
        student1.setId(id1).setName("hello");

        Student student2 = new Student();
        student2.setId(id2).setName("hello");

        studentMapper_master_master.insert(student1);
        studentMapper_slave_slave.insert(student2);
        return JSONResult.returnJSONString(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }


}
