package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserRole;
import com.example.demo.entity.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {



    @Select("SELECT roleId FROM user_role WHERE userId = #{userId}")
    List<Integer> findRoldIdByUserId(int userId);

    @Insert("INSERT INTO user_role(userId, roleId, createTime) " +
            "VALUES(#{userId}, #{roleId}, #{createTime})")
    void insertUserRole(UserRole userRole);

    @Delete("DELETE FROM user_role WHERE userId = #{userId} AND roleId = #{roleId}")
    void deleteUserRole(@Param("User") int userId, @Param("roldId") int roldId);

    @Delete("DELETE FROM user_role WHERE userId = #{userId}")
    void deleteUserAllRole(@Param("userId") int userId);

    @Select("SELECT COUNT(*) FROM Role_permission WHERE roleId = #{roleId} AND permissionId = #{permissionId}")
    int countRole(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    // 获取某个角色拥有的所有权限名称
    @Select("SELECT r.name " +
            "FROM user_role ur " +
            "JOIN role r ON ur.roleId = r.id " +
            "WHERE ur.userId = #{userId}")
    List<String> getRoleNameByUserId(int userId);



//    @Select("SELECT id FROM permission WHERE name = #{name}")
//    Integer findIdByName(String name);

}
