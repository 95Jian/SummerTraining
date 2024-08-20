package com.example.demo.mapper;

import com.example.demo.entity.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RolePermissionMapper {

    @Select("SELECT permissionId FROM role_permission WHERE roleId = #{roleId}")
    List<Integer> findPermissionsByRoleId(int roleId);

    @Insert("INSERT INTO role_permission(roleId, permissionId, createTime) " +
            "VALUES(#{roleId}, #{permissionId}, #{createTime})")
    void insertRolePermission(RolePermission rolePermission);

    @Delete("DELETE FROM role_permission WHERE roleId = #{roleId} AND permissionId = #{permissionId}")
    void deleteRolePermission(@Param("roleId") int roleId, @Param("permissionId") int permissionId);


    @Delete("DELETE FROM role_permission WHERE roleId = #{roleId}")
    void deleteRoleAllPermission(@Param("roleId") int roleId);

    @Select("SELECT COUNT(*) FROM Role_permission WHERE roleId = #{roleId} AND permissionId = #{permissionId}")
    int countPermission(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    // 获取某个角色拥有的所有权限名称
    @Select("SELECT p.name " +
            "FROM Role_permission rp " +
            "JOIN Permission p ON rp.permissionId = p.id " +
            "WHERE rp.roleId = #{roleId}")
    List<String> getPermissionNameByRoleId(int roleId);



    @Select("SELECT id FROM permission WHERE name = #{name}")
    Integer findIdByName(String name);

}
