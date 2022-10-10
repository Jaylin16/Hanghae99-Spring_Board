package com.example.board.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/images/**").permitAll() //image 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll() //css 폴더를 login 없이 허용

                // 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()

                // 로그인 기능 허용
                .formLogin()// 로그인 기능작동
                .loginPage("/user/login") //사용자 정의 페이지 경로
                .defaultSuccessUrl("/")// 로그인 성공 시 이동 페이지 경로
                .failureUrl("/user/login?error")//로그인 실패시 이동페이지 경로
                .permitAll()
                .and()

                // 로그아웃 기능 허용
                .logout()
                .permitAll();
    }
}
