package com.bitcs.dao;

import com.bitcs.domain.User;
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
    User getById(@Param("id") int id);
}
