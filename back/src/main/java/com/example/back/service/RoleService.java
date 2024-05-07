package com.example.back.service;

import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.example.back.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleService implements UserDetailsService{

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Role role1 = roleMapper.findUserByUsername(username);
        if (role1 == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new org.springframework.security.core.userdetails.User(
                role1.getUsername(),
                role1.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(role1.getRole()))
        );
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }



    public void addUser(List<User> user) {
        HashOperations<String,Integer,Object> hashOperations = redisTemplate.opsForHash();
        for(int i =0 ; i<user.size();i++){
            hashOperations.put("User",i,user.get(i));
            System.out.println(hashOperations.get("User",i));
        }
    }

    public List getUser() {
        HashOperations<String,Integer,Object> hashOperations = redisTemplate.opsForHash();
        List<Object> list = new ArrayList<>();
        for(int i =0 ; i<hashOperations.size("User");i++){
            list.add(hashOperations.get("User",i));
            System.out.println(list);
        }
        return list;
    }





}
