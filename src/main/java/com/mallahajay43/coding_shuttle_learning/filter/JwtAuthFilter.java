package com.mallahajay43.coding_shuttle_learning.filter;

import com.mallahajay43.coding_shuttle_learning.entities.User;
import com.mallahajay43.coding_shuttle_learning.service.JwtService;
import com.mallahajay43.coding_shuttle_learning.service.SessionService;
import com.mallahajay43.coding_shuttle_learning.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final SessionService sessionService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Checking for authorization header.
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request,response);
                return;
            }

            String token = authHeader.replace("Bearer ", "");
            UUID userId = jwtService.getUserId(token);

            if (userId !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Fetching user details.
                User user = userDetailsService.findUserById(userId).orElseThrow(
                        () -> new AuthenticationCredentialsNotFoundException("User does not exist")
                );

                // Checks token against sessionEntity table.
                if (!sessionService.isValidToken(token, user)) {
                    throw new AuthenticationException("User session revoked");
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);

                // Sets additional details like request ip etc.
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                System.out.println("Validated");
            }
            filterChain.doFilter(request,response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}
