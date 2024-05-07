package com.example.back.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.back.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from t_user")
    public List<User> find();


    @Insert("insert into t_user values (#{id},#{username},#{password},#{birthday})")
    public int add(User user);


    public int deleteById(User user);




}
