package com.example.Estate_Twin.auth.jwt;

import com.example.Estate_Twin.auth.service.CustomUserDetailService;
import com.example.Estate_Twin.user.domain.entity.User;
import com.nimbusds.jwt.JWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import sun.net.www.protocol.http.AuthenticationHeader;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Log4j2
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application-oauth.properties")
public class JwtTokenProvider {
    @Value("${app.auth.token.secret-key}")
    private String SECRET_KEY;
    private Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L*60*60;
    private Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L*60*60*24*7;
    private final CustomUserDetailService userDetailsService;
    @PostConstruct
    protected void init() {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createAccessToken(User user) { // paylod = user.getId()
        return createToken(user, ACCESS_TOKEN_EXPIRE_LENGTH);
    }

    public String createRefreshToken(User user) {
        return createToken(user, REFRESH_TOKEN_EXPIRE_LENGTH);
    }

    public String createToken(User user, long expireLength) {
        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        claims.put("username", user.getEmail());
        Date now = new Date();
        Date validity = new Date(now.getTime() + expireLength);
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();

    }

    public String getPayload(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        } catch (JwtException e){
            throw new RuntimeException("유효하지 않은 토큰 입니다");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserIdentifier(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    public String getUserIdentifier(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
}
