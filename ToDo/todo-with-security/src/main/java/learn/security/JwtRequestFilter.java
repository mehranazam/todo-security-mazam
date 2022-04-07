package learn.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends BasicAuthenticationFilter {

    JwtConverter converter;

    public JwtRequestFilter(AuthenticationManager authenticationManager, JwtConverter converter) {
        super(authenticationManager);
        this.converter = converter;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization ");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            User converted = converter.getUserFromToken(authHeader);
            if (converted != null) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(converted.getUsername(), null, converted.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
            } else {
                response.setStatus(403);
            }

        }
        chain.doFilter(request, response);
    }
}

