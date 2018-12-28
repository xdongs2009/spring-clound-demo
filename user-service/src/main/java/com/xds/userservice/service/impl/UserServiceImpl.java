package com.xds.userservice.service.impl;


import com.xds.userservice.entity.User;
import com.xds.userservice.entity.UserSaveEvent;
import com.xds.userservice.repository.UserRepository;
import com.xds.userservice.repository.UserSaveEventRepository;
import com.xds.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSaveEventRepository userSaveEventRepository;


    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long saveEvent(Long orderId) {
        UserSaveEvent event = new UserSaveEvent();
        event.setOrderId(orderId);
        event.setCreateTime(new Timestamp(System.currentTimeMillis()));
        UserSaveEvent result = userSaveEventRepository.save(event);
//        int i = 1/0;
        return result.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void newUserSaveEventHandle() {
        List<UserSaveEvent> eventList = userSaveEventRepository.findByStatus(0);
        if (eventList == null || eventList.size() == 0) {
            LOGGER.info("no new events");
            return;
        }
        for (UserSaveEvent event : eventList) {
            User user = new User();
            user.setOrderId(event.getOrderId());
            user.setName("abc");
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            event.setStatus(1);
            event.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            userSaveEventRepository.save(event);
            LOGGER.info("save user success, orderId = " + event.getOrderId());
            //int i = 1/0;
        }
    }
}
