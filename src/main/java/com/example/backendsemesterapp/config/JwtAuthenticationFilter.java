package com.example.backendsemesterapp.config;



import com.example.backendsemesterapp.jwtUtil.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// JwtAuthenticationFilter activates when making Http request

// Component tells Springboot that class is a managed bean
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization"); // Adds "Authorization" header where JWT token will be provided
        final String jwt;

        // userEmail represent Username
        // Using email to get userDetails from the database
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) { // Check if request includes authHeader and contains "Bearer "
            filterChain.doFilter(request, response);

            return;  // JWT token is invalid
        }


        jwt = authHeader.substring(7);  // Extract JWT token
        userEmail = jwtService.extractUsername(jwt);  // Extract JWT token

        // Check that userEmail exists
        // Looks if user already authenticated through SecurityContextHolder
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            // Get userDetails from database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); // Getting userEmail from User
            if (jwtService.isTokenValid(jwt, userDetails)) {  // Check if JWT token with entered userEmail is valid
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)  // Updating SecurityContextHolder out of request
                );
                SecurityContextHolder.getContext().setAuthentication(authToken); // Adding User to database and setting User as authenticated
            }
        }
        filterChain.doFilter(request, response); // Re-run filter for future log in attempts
    }
}
