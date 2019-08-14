package com.team7.uranus.config;

import com.team7.uranus.Exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        // Get authorization from Http request
        final String token = request.getHeader("token");

        // Check the authorization, check if the token is started by "Bearer "
        if (token == null) {
            throw new MyException(400 ,"缺少token");
        }
        Integer roles;
        try {
            final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
            roles =(Integer)claims.get("roles");
            // Add the claim to request header
            logger.info(claims);
            request.setAttribute("user", claims);
            request.setAttribute("userId",claims.get("sub"));
            request.setAttribute("roles",claims.get("roles"));
            logger.info(claims);
        } catch (final SignatureException e) {
            throw new MyException(300,"Invalid token");
        }
        if(roles==0&&request.getRequestURI().contains("/api/admin/")){
            throw new MyException(681,"权限不足");
        }

        chain.doFilter(req, res);

    }
}
