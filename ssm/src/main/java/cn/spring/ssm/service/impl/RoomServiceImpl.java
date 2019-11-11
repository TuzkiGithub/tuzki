package cn.spring.ssm.service.impl;

import cn.spring.ssm.dao.common.RoomMapper;
import cn.spring.ssm.model.Room;
import cn.spring.ssm.model.User;
import cn.spring.ssm.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.service
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
