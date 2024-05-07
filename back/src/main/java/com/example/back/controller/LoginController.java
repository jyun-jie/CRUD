package com.example.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class LoginController {



    @GetMapping("/")
    public String home() {
        return "home"; // 返回主页视图的名称
    }

    @RequestMapping("/login")
    public Boolean login(){
        return true;
    }


    @GetMapping("/private")
    public String privateo(){ return "log inggg";}


    @GetMapping ("/logout")
    public String logout(){
        System.out.println("log outtt");
        try {
            Thread.sleep(3000); // 等待3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "log outtt";
    }

}
