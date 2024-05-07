package com.example.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

//    @Select("SELECT * FROM t_role WHERE username = #{username} AND password = #{password}")
//    List<Role> findUserByUsername(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM t_role WHERE username = #{username}")
    Role findUserByUsername(@Param("username") String username);

}
