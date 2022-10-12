//package com.example.board.security.config;
//
//import com.example.board.security.FilterSkipMatcher;
//import com.example.board.security.FormLoginSuccessHandler;
//import com.example.board.security.filter.FormLoginFilter;
//import com.example.board.security.filter.JwtAuthFilter;
//import com.example.board.security.jwt.HeaderTokenExtractor;
//import com.example.board.security.provider.FormLoginAuthProvider;
//import com.example.board.security.provider.JwtAuthProvider;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JwtAuthProvider jwtAuthProvider;
//    private final HeaderTokenExtractor headerTokenExtractor;
//
//    public SecurityConfig(
//            JwtAuthProvider jwtAuthProvider,
//            HeaderTokenExtractor headerTokenExtractor
//    ) {
//        this.jwtAuthProvider = jwtAuthProvider;
//        this.headerTokenExtractor = headerTokenExtractor;
//    }
//
//    @Bean
//    //비밀번호 암호화 알고리즘을 Bean으로 등록
//    public BCryptPasswordEncoder encodePassword() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) {
//        auth
//                .authenticationProvider(formLoginAuthProvider())
//                .authenticationProvider(jwtAuthProvider);
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
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
//        http.csrf()
//                .ignoringAntMatchers("/user/**");
//
//
//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//
//        http.authorizeRequests()
//                //image 폴더를 login 없이 허용
//                .antMatchers("/images/**").permitAll()
//                //css 폴더를 login 없이 허용
//                .antMatchers("/css/**").permitAll()
//                // 회원 관리 처리 API 전부를 login 없이 허용
//                .antMatchers("/user/**").permitAll()
//
//                .anyRequest().permitAll()
//                .and()
//
//                .logout()
//                .logoutUrl("/user/logout").permitAll()
//                .and()
//
//                .exceptionHandling()
//                .accessDeniedPage("/forbidden.html");
//
//        http
//                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    public FormLoginFilter formLoginFilter() throws Exception {
//        FormLoginFilter formLoginFilter = new FormLoginFilter(authenticationManager());
//        formLoginFilter.setFilterProcessesUrl("/user/login");
//        formLoginFilter.setAuthenticationSuccessHandler(formLoginSuccessHandler());
//        formLoginFilter.afterPropertiesSet();
//        return formLoginFilter;
//    }
//
//    @Bean
//    public FormLoginSuccessHandler formLoginSuccessHandler() {
//        return new FormLoginSuccessHandler();
//    }
//
//    @Bean
//    public FormLoginAuthProvider formLoginAuthProvider() {
//        return new FormLoginAuthProvider(encodePassword());
//    }
//
//    private JwtAuthFilter jwtFilter() throws Exception {
//        List<String> skipPathList = new ArrayList<>();
//
//        skipPathList.add("GET,/images/**");
//        skipPathList.add("GET,/css/**");
//
//        skipPathList.add("GET,/h2-console/**");
//        skipPathList.add("POST,/h2-console/**");
//        skipPathList.add("GET,/user/**");
//        skipPathList.add("POST,/user/signup");
//
//        skipPathList.add("GET,/");
//        skipPathList.add("GET,/basic.js");
//
//        skipPathList.add("GET,/favicon.ico");
//
//        FilterSkipMatcher matcher = new FilterSkipMatcher(
//                skipPathList,
//                "/**"
//        );
//
//        JwtAuthFilter filter = new JwtAuthFilter(
//                matcher,
//                headerTokenExtractor
//        );
//        filter.setAuthenticationManager(super.authenticationManagerBean());
//
//        return filter;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
