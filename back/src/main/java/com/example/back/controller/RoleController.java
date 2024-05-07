package com.example.back.controller;

import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.example.back.mapper.RoleMapper;
import com.example.back.mapper.UserMapper;
import com.example.back.service.RoleService;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@CrossOrigin
public class RoleController {



    @Autowired
    RoleMapper roleMapper;

//    @Autowired
//    RoleService roleService;

//    @PostMapping("/role")
//    public String loadUser(@RequestParam String username, @RequestParam String password){
//        List<Role> role1 = roleMapper.findUserByUsername(username, password);
//        if(role1 == null){
//            System.out.println("錯誤");
//            return null;
//        }else{
//            System.out.println(role1.get(0).getRole());
//            return role1.get(0).getRole();
//        }
//    }


}
