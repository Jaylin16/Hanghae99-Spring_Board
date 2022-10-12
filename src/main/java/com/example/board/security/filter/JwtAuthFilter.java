//package com.example.board.security.filter;
//
//import com.example.board.security.jwt.HeaderTokenExtractor;
//import com.example.board.security.jwt.JwtPreProcessingToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {
//    //spring security 내장되어있는 인증처리 필터를 상속. filter자체가 이미 추상 인터페이스를 상속하고 있으므로 다중 상속이라 extends...??
//
//    private final HeaderTokenExtractor extractor;
//    //헤더로 받은 토큰 추출한 값 참고할꺼라고 붙여줌.
//
//    public JwtAuthFilter(
//            RequestMatcher requiresAuthenticationRequestMatcher,
//            HeaderTokenExtractor extractor
//            //헤더의 토큰값이 맞는지 매칭해보고, 맞으면 true, 아니면 false 반환.
//    ) {
//        super(requiresAuthenticationRequestMatcher);
//
//        this.extractor = extractor;
//        //맞으면 여기서 토큰 = 헤더에서 받아온 토큰값.
//    }
//
//    @Override
//    public Authentication attemptAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws AuthenticationException, IOException {
//
//        String tokenPayload = request.getHeader("Authorization");
//        if (tokenPayload == null) {
//            response.sendRedirect("/user/loginView");
//            return null;
//        }
//        //헤더에서 받아온 토큰의 payload 자리 값(두번째 자리)이 null이면 login 페이지로 redirect.
//
//        JwtPreProcessingToken jwtToken = new JwtPreProcessingToken(
//                extractor.extract(tokenPayload, request));
//
//        //토큰의 payload 자리 값이 null이 아니면 헤더로부터 받았던 토큰에서 토큰payload값과 함께 들어온 요청을 jwtToken에 넣음.
//
//        return super
//                .getAuthenticationManager()
//                .authenticate(jwtToken);
//        //인증 관리자가 요청한 인증 요청과정에 인증된 jwt토큰 반환.
//    }
//
//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult
//    ) throws IOException, ServletException {
//
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//
//        context.setAuthentication(authResult);
//        SecurityContextHolder.setContext(context);
//
//        chain.doFilter(
//                request,
//                response
//        );
//    }
//    //토큰 검증 결과 진짜가 맞을 경우 클라이언트한테 받아온 request랑 response 해줘야되는거 담아서 통과~ 다음 단계로 가지고 가세요??
//
//    @Override
//    protected void unsuccessfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException failed
//    ) throws IOException, ServletException {
//
//        SecurityContextHolder.clearContext();
//
//        super.unsuccessfulAuthentication(
//                request,
//                response,
//                failed
//        );
//    }
//    //토큰 검증 결과 진짜 아닐 경우 클라이언트한테 받아온 request랑 response 해줘야되는거 죄다 담아서 failed로 퉤퉤 돌아가.
//
//
//    //결국 이곳은 토큰을 가지고 접속한 사용자가 가지고 있는 토큰이 실제 서버와 일치하는 토큰인지 검사해서 인증 매니저한테 얘꺼 진짜 맞아요 하고 검사해서 반환해주는 곳...?
//
//}
