package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {



    @Select("SELECT * FROM user WHERE username = #{userName}")
    User findByUsername(String userName);


    @Update("UPDATE user SET username = #{username}, password = #{password}, phone = #{phone}, email = #{email}, " +
            "gender = #{gender}, address = #{address}" +
            "WHERE id = #{id}")
    void updateUser(User user);

    @Select("SELECT password FROM user WHERE id = #{id}")
    String getPasswordById(@Param("id") int id);

}
