package com.bitcs.service;

import com.bitcs.VO.LoginVO;
import com.bitcs.dao.UserDao;
import com.bitcs.domain.User;
import com.bitcs.exception.GlobalException;
import com.bitcs.redis.RedisService;
import com.bitcs.redis.UserKey;
import com.bitcs.util.CodeMsg;
import com.bitcs.util.MD5Util;
import com.bitcs.util.UUIDUtil;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GeChen
 */
@Service
public class UserService {

    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public User getById(long id) {
        return userDao.getById(id);
    }

    /**
     * 用户登录
     *
     * @param loginVO
     * @return
     */
    public boolean login(HttpServletResponse httpServletResponse, LoginVO loginVO) {
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
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(httpServletResponse, token, user);
        return true;
    }

    /**
     * 通过token获取用户信息
     *
     * @param token
     * @return
     */
    public User getByToken(HttpServletResponse httpServletResponse, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        //延长有效期
        User user = redisService.get(UserKey.token, token, User.class);
        if (user != null) {
            addCookie(httpServletResponse, token, user);
        }
        return user;
    }


    private void addCookie(HttpServletResponse httpServletResponse, String token, User user) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        /**
         *项目根目录
         */
        config.setProjectPath("D:\\project\\Spike\\spike_service");
        /**
         * 项目名称
         */
        config.setProjectName("ProjectName");
        /**
         * 声明该API的版本
         */
        config.setApiVersion("V1.0");
        /**
         * 生成API 文档所在目录
         */
        config.setDocsPath("your api docs path");
        /**
         * 配置自动生成
         */
        config.setAutoGenerate(Boolean.TRUE);
        /**
         * 执行生成文档
         */
        Docs.buildHtmlDocs(config);
    }
}
