package com.bitcs.service;

import com.bitcs.dao.UserDao;
import com.bitcs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GeChen
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }


    public boolean tx() {
        userDao.insert(new User(2, "222"));
        userDao.insert(new User(1, "111"));
        return true;
    }
}
