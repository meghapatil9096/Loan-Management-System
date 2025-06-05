package com.neosoft.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

//    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            try{
                email= jwtTokenProvider.getEmailFromToken(token);   //use email instead of username
            }catch (Exception e){
                logger.error("Error extracting username from token:"+e.getMessage());
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication()==null){
//            DB call
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//            Token Validation
            if (jwtTokenProvider.validateToken(token,email)){
                var authorities = jwtTokenProvider.getAuthoritiesFromToken(token);  //get roles

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null,authorities);
//
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                Long userId = jwtTokenProvider.getUserIdToken(token);
                request.setAttribute("userId",userId);

//                filter Authentication setup
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
