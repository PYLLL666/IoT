package com.llp.dao;

import com.llp.pojo.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * description:数据持久层
 * create by Administrator
 * create on 2021/06/10/11:31
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where username=#{username} and password=#{password}")
    User1 findUser(@Param("username") String username, @Param("password") String password);
}
