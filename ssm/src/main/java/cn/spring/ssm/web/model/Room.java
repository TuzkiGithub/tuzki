package cn.spring.ssm.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Package: cn.zz.cms_manage.entity
 * Admin: zhangzhe
 * Date: 2019/3/31
 * Time: 13:39
 * Description:
 */
@Data
@Accessors(chain = true)
public class Room {
    @Id
    private Long room_id;
    private Integer room_num;
    private String campus;
    private String build;
    @Column(name = "room_status")
    private Short room_status;
    private Integer room_size;
}
