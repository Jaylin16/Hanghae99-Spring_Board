//package com.example.board.security.filter;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class FormLoginFilter extends UsernamePasswordAuthenticationFilter {
//    //username과 password로 검증하는 필터 상속...
//
//    final private ObjectMapper objectMapper;
//
//    public FormLoginFilter(final AuthenticationManager authenticationManager) {
//        super.setAuthenticationManager(authenticationManager);
//        //authenticationManager(대략.. 인증 검사하는 매니저?)에 있는 값 가지고 와서
//
//        objectMapper = new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        //구성이 null이 아니면 기본값으로 objectMapper 생성...?
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        UsernamePasswordAuthenticationToken authRequest;
//
//
//        try {
//            JsonNode requestBody = objectMapper.readTree(request.getInputStream());
//            String username = requestBody.get("username").asText();
//            String password = requestBody.get("password").asText();
//            authRequest = new UsernamePasswordAuthenticationToken(username, password);
//        } catch (Exception e) {
//            throw new RuntimeException("username, password 입력이 필요합니다. (JSON)");
//        }
//        //body에 입력된 username과 password 받아서 token으로 만들어준거를 authRequest라는걸로 반환. 만약에 body에 입력값 없으면 실행오류로 입력하라고 에러 메세지 띄움.
//
//
//        setDetails(request, authRequest);
//        //클라이언트한테 받은 회원정보 전체랑 만들어진 토큰은 details로 set.
//
//        return this.getAuthenticationManager().authenticate(authRequest);
//        //여기서 인증 검사하는 매니저한테 요청받은 인증요청에 토큰 정보 담아서 리턴.
//
//    }
//
//    //결국 이곳은 로그인한 회원한테 토큰 있는지 없는지 확인해서 신규 토큰 만들어주는 곳...?
//}