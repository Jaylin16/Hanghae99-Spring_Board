//package com.example.board.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    //비밀번호 암호화 알고리즘을 Bean으로 등록
//    public BCryptPasswordEncoder encodePassword() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**");
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
//        http.csrf()
//                .ignoringAntMatchers("/user/**");
//
//        http.authorizeRequests()
//                //image 폴더를 login 없이 허용
//                .antMatchers("/images/**").permitAll()
//                //css 폴더를 login 없이 허용
//                .antMatchers("/css/**").permitAll()
//                // 회원 관리 처리 API 전부를 login 없이 허용
//                .antMatchers("/user/**").permitAll()
//
//                // 어떤 요청이든 '인증'
//                .anyRequest().authenticated()
//                .and()
//
//                // 로그인 기능 허용
//                .formLogin()// 로그인 기능작동
//                //사용자 정의 페이지 경로, 로그인 View 제공 (GET /user/login)
//                .loginPage("/user/login")
//                // 로그인 처리 (POST /user/login)
//                .loginProcessingUrl("/user/login")
//                // 로그인 성공 시 이동 페이지 경로
//                .defaultSuccessUrl("/")
//                //로그인 실패시 이동페이지 경로
//                .failureUrl("/user/login?error")
//                .permitAll()
//                .and()
//
//                // 로그아웃 기능 허용
//                .logout()
//                // 로그아웃 처리 URL
//                .logoutUrl("/user/logout")
//                .permitAll();
//    }
//}
