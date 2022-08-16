package com.example.shop.Jwt;


import com.example.shop.Entity.Role;
import com.example.shop.Service.Imp.UserServiceImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final UserServiceImp userServiceImp;

    @Autowired
    public JwtTokenProvider(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Value("${jwt.token.security}")
    private String secret;

    @Value("${jwt.token.validate}")
    private Long validateInMillisecond;

    @PostConstruct
    public void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String email, Role role){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", getPermission(role));

        Date issue = new Date();
        Date ex = new Date(validateInMillisecond + issue.getTime());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issue)
                .setExpiration(ex)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public List<String> getPermission(Role role){
        List<String> strings = new ArrayList<>();
        role.simpleGrantedAuthorities().forEach(n -> strings.add(n.getAuthority()));
        return strings;
    }

    public String getEmail(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication authentication(String token){
        UserDetails userDetails = userServiceImp.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if(bearer != null && bearer.startsWith("Bearer ")){
            return bearer.substring(7, bearer.length());
        }
        return null;
    }

    public boolean validateToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (AuthenticationException e){
            throw new RuntimeException(e);
        }
    }
}
