package com.bitcs.dao;

import com.bitcs.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author GeChen
 */
@Mapper
public interface UserDao {
    /**
     * 根据用户id返回User实体
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getById(@Param("id") long id);

    /**
     * 根据用户信息插入到数据库
     *
     * @param user
     * @return
     */
    @Insert("insert into user(id,name) values(#{id},#{name})")
    int insert(User user);
}
