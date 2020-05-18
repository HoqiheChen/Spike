package com.bitcs.service;

import com.bitcs.VO.LoginVO;
import com.bitcs.dao.UserDao;
import com.bitcs.domain.User;
import com.bitcs.exception.GlobalException;
import com.bitcs.util.CodeMsg;
import com.bitcs.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GeChen
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(long id) {
        return userDao.getById(id);
    }

    /**
     * 用户登录
     *
     * @param loginVO
     * @return
     */
    public boolean login(LoginVO loginVO) {
        if (loginVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVO.getMobile();
        String formPassword = loginVO.getPassword();
        //判断手机号是否存在
        User user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPassword, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return true;
    }
}
