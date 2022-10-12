//package com.example.board.security.jwt;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import com.auth0.jwt.interfaces.JWTVerifier;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Date;
//import java.util.Optional;
//
//import static com.example.board.security.jwt.JwtTokenUtils.*;
//
//@Component
//@Slf4j
//public class JwtDecoder {
//
//
//    public String decodeUsername(String token) {
//        DecodedJWT decodedJWT = isValidToken(token)
//                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰"));
//
//        Date expiredDate = decodedJWT
//                .getClaim(CLAIM_EXPIRED_DATE)
//                .asDate();
//
//        Date now = new Date();
//        if (expiredDate.before(now)) {
//            throw new IllegalArgumentException("유효하지 않은 토큰");
//        }
//
//        String username = decodedJWT
//                .getClaim(CLAIM_USER_NAME)
//                .asString();
//
//        return username;
//    }
//
//    private Optional<DecodedJWT> isValidToken(String token) {
//        DecodedJWT jwt = null;
//
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
//            JWTVerifier verifier = JWT
//                    .require(algorithm)
//                    .build();
//
//            jwt = verifier.verify(token);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//
//        return Optional.ofNullable(jwt);
//    }
//}
