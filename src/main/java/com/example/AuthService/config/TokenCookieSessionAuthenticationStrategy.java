//package com.example.AuthService.config;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//
//import org.springframework.security.core.token.Token;
//import org.springframework.security.web.authentication.session.SessionAuthenticationException;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Objects;
//import java.util.function.Function;
//
//public class TokenCookieSessionAuthenticationStrategy implements SessionAuthenticationStrategy {
//    private Function<Authentication, Token> tokenCookieFactory;
//
//    private Function<Token, String> tokenStringSerializable = Objects::toString;
//
//    @Override
//    public void onAuthentication(Authentication authentication, HttpServletRequest request,
//                                 HttpServletResponse response) throws SessionAuthenticationException {
//        if (authentication instanceof UsernamePasswordAuthenticationToken){
//            var token = this.tokenCookieFactory.apply(authentication);
//            var tokenString = this.tokenStringSerializable.apply(token);
//
//            var cookie = new Cookie("__Host-auth-token", tokenString);
//            cookie.setPath("/");
//            cookie.setDomain(null);
//            cookie.setSecure(true);
//            cookie.setHttpOnly(true);
//            cookie.setMaxAge((int) ChronoUnit.SECONDS.between(Instant.now(), token.expiresAt()));
//        }
//    }
//}
