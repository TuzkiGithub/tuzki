package cn.spring.ssm.dao.common;

import cn.spring.ssm.model.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.dao.common
 * User: 25414
 * Date: 2019/12/27
 * Time: 8:50
 * Description:
 */

@Mapper
public interface ItemMapper {

    @Update("update tb_item set id = #{id}, title = #{title},sell_point = #{sell_point}, price = #{price}, num = #{num}, barcode = #{barcode},image = #{image},cid = #{cid}, status = #{status}")
    int updateItemByRecord(Item item);

    @Update("update tb_item set num = num - 1 where id = #{id}")
    int updateItemNumById(String id);
}
