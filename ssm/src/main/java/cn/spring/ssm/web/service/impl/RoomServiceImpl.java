package cn.spring.ssm.web.service.impl;

import cn.spring.ssm.web.dao.common.RoomMapper;
import cn.spring.ssm.web.model.Room;
import cn.spring.ssm.web.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.web.service
 * User: 25414
 * Date: 2019/9/5
 * Time: 16:46
 * Description:
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> getRoomAll() {
        return roomMapper.selectAll();
    }
}
