package com.example.shop.Jwt;

import com.example.shop.DTO.Security.PersonDetails;
import com.example.shop.DTO.Security.UserPrincipalData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final UserPrincipalData userPrincipalData;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(UserPrincipalData userPrincipalData, JwtTokenProvider jwtTokenProvider) {
        this.userPrincipalData = userPrincipalData;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        if(token != null && jwtTokenProvider.validateToken(token)){
            Authentication authentication = jwtTokenProvider.authentication(token);
            if(authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
                userPrincipalData.setId(personDetails.getId());
                userPrincipalData.setUserName(personDetails.getUsername());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
