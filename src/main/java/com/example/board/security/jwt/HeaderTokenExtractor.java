//package com.example.board.security.jwt;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.NoSuchElementException;
//
//@Component
//public class HeaderTokenExtractor {
//
//
//    public final String HEADER_PREFIX = "Bearer ";
//
//    public String extract(String header, HttpServletRequest request) {
//
//        if (header == null || header.equals("") || header.length() < HEADER_PREFIX.length()) {
//            System.out.println("error request : " + request.getRequestURI());
//            throw new NoSuchElementException("잘못된 JWT 정보");
//        }
//
//        return header.substring(
//                HEADER_PREFIX.length(),
//                header.length()
//        );
//
//        //header에 담긴 정보가 null이거나 공란일 경우 error 띄워줘서 한번 거르고 값이 있을 경우 그 값을 extract로 만들어둠...?
//    }
//}