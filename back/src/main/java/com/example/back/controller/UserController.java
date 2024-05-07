package com.example.back.controller;


import com.example.back.entity.User;
import com.example.back.mapper.UserMapper;
import com.example.back.service.RoleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RoleService roleService;

    @GetMapping("/user")
    public List query(){
        List<User> list = userMapper.find();
        System.out.println(list);
        return list;
    }



    @PostMapping("/user")
    public String add(@RequestBody User user){
        int i  = userMapper.add(user);
        if(i>0){
            return "成功";
        }else{
            return "失敗";
        }
    }

    @DeleteMapping  ("/user")
    public String delete(User user){
        int i  = userMapper.deleteById(user);
        if(i>0){
            return "成功";
        }else{
            return "失敗";
        }
    }

    @PostMapping("/user/update")
    public String update(@RequestBody User user){
        int i = userMapper.updateById(user);
            if(i>0){
                return "成功";
            }else{
                return "失敗";
            }

    }

    @GetMapping("/user/update/{id}")
    public User queryId(@PathVariable int id){
        User list =  userMapper.selectById(id);
        System.out.println(list);
        return list;
    }
}
