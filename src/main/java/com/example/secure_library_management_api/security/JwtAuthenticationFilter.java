package com.example.secure_library_management_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserService customUserService;



    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws IOException, ServletException
    {
        String authHeader=request.getHeader("Authorization");
        log.info(authHeader);

                if(authHeader==null || !authHeader.startsWith("Bearer "))
                {
                    filterChain.doFilter(request,response);
                    return;
                }


                String token=authHeader.substring(7);
                log.info(token);
                String username=jwtService.extractUsername(token);
                log.info(username);

                if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
                {
                    UserDetails userDetails=customUserService.loadUserByUsername(username);
                    System.out.println("Debug Username: " + userDetails.getUsername());
                    System.out.println("Debug Password: " + userDetails.getPassword());
                    System.out.println("Debug Authorities: " + userDetails.getAuthorities());
                    if(jwtService.isTokenValid(token,userDetails))
                    {
                        UsernamePasswordAuthenticationToken authToken
                                =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authToken);


                    }
                }
                filterChain.doFilter(request,response);

    }





}
