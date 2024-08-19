package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    @Select("SELECT * FROM role WHERE name = #{name}")
    Role findByRolename(String name);

    @Select("SELECT * FROM Role")
    List<Role> findAllRoles();

    @Select("SELECT * FROM Role WHERE id = #{id}")
    Role findRoleById(int id);


    @Insert("INSERT INTO Role(name, description, createTime) " +
            "VALUES(#{name}, #{description}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertRole(Role role);

    @Update("UPDATE Role SET name = #{name}, description = #{description} WHERE id = #{id}")
    void updateRole(Role role);

    @Delete("DELETE FROM Role WHERE id = #{id}")
    void deleteRole(int id);
}
