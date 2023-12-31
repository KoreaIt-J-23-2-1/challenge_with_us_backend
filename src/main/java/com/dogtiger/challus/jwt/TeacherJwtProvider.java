package com.dogtiger.challus.jwt;

import com.dogtiger.challus.entity.User;
import com.dogtiger.challus.repository.UserMapper;
import com.dogtiger.challus.security.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
public class TeacherJwtProvider {
    private final Key key;
    private final UserMapper userMapper;

    public TeacherJwtProvider(@Value("${jwt.secret}") String secret, UserMapper userMapper) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.userMapper = userMapper;
    }

    public String generateToken(User user) {
        Date date = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));

        return Jwts.builder()
                .setSubject("AccessToken")
                .setExpiration(date)
                .claim("email", user.getEmail())
                .claim("oauth2Id", user.getOauth2Id())
                .claim("userId", user.getUserId())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String removeBearer(String bearerToken) {
        if(!StringUtils.hasText(bearerToken)) {
            return null;
        }
        return bearerToken.substring("Bearer".length());
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        if(claims == null) {
            return null;
        }

        String email = claims.get("email").toString();
        String oauth2Id = claims.get("oauth2Id") != null ? claims.get("oauth2Id").toString() : null;

        User user;
        if(oauth2Id != null) {
            user = userMapper.findUserByOauth2Id(oauth2Id);
        }else {
            user = userMapper.findUserByEmail(claims.get("email").toString());
        }

        if(user == null) {
            return null;
        }

        PrincipalUser principalUser = new PrincipalUser(user);
        return new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
    }

    private Claims getClaims(String token) {
        Claims claims = null;
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
        return claims;
    }
}