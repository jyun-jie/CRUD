package com.example.back.config;


import com.example.back.LoginHandler.ExceptionHandler;
import com.example.back.LoginHandler.LoginFailureHandler;
import com.example.back.LoginHandler.LoginSucessHandler;
import com.example.back.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;
import java.io.PrintWriter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LoginSucessHandler loginSucessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private ExceptionHandler exceptionHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private RoleService roleService;


    private static final String URL_WHITELIST[] = {
            "/login"
    };

    //密碼編碼(必備)
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }



    //從資料庫中驗證
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(roleService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    //註冊 比properties 優先
//    @Bean
//    UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
//        users.createUser(User.withUsername("javaboy").password("123").roles("admin").build());
//        users.createUser(User.withUsername("NO").password("123").roles("user").build());
//
//        return users;
//    }



    //可以不用身分驗證
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web.ignoring().antMatchers();
            }
        };
    }
//    public SecurityFilterChain filterChain(HttpSecurity http)throws  Exception{
//        //開啟跨域 csrf攻擊關閉
//        http.cors().and().csrf().disable()
//        //登入登出
//                .formLogin()
////                .successHandler()
////                .failureHandler()
////                .and()
////                .logout()
////                .logoutSuccessHandler()
//
//        //session禁用
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//        //攔截規則
//                .and().authorizeRequests().antMatchers(URL_WHITELIST).permitAll()
//                .anyRequest().authenticated();
//        //自訂易過濾
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws  Exception{
        http.cors().and().csrf().disable()
                        .authorizeHttpRequests()
            .antMatchers("/private","/").hasAnyRole("user","admin") //必須有user
            .antMatchers("/login.html").permitAll() //都可以
                .and()
            .formLogin()
//                //登入畫面導向login.html
//                .loginPage("http://localhost:8080/")
//                  //  doLogin處理請求
//                .loginProcessingUrl("/dologin")
//                .usernameParameter("username") //指定登入表單的參數名
//                .passwordParameter("password")
                //登入成功
                .successHandler(loginSucessHandler)
                .failureHandler(loginFailureHandler)
                //.successForwardUrl("/private") //登入完自動導向網址(未成功)
//                .defaultSuccessUrl("/private",true) // 強制到指定位址
//                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                //.addFilterAfter(new MyCustomFilter(), LogoutFilter.class)
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(exceptionHandler);


        return http.build();
    }






}