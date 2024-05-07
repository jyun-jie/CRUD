package com.example.back.LoginHandler;


import com.example.back.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
/*
    登入成功處理器
* */
public class LoginSucessHandler implements AuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

//        for(int i =0 ; i<authentication.getAuthorities().size();i++){
//            String str = authentication.getAuthorities().toString();
//            String words = str.substring(1, str.length() - 1);
//            System.out.println(words);
//        }
        //new ObjectMapper().writeValueAsString(authentication.getAuthorities());
        String str = authentication.getAuthorities().toString();
        String words = str.substring(1, str.length() - 1);
        out.write(words);
        out.flush();
        out.close();

    }

}
