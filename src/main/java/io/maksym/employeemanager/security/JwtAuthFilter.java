package io.maksym.employeemanager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Log the presence of the Authorization header
        if (header == null) {
            logger.warn("Authorization header is missing.");
        } else {
            String[] authElements = header.split(" ");

            // Check if the header is correctly formatted with Bearer and the token
            if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                String token = authElements[1];
                logger.info("JWT Token received: {}", token);

                try {
                    // Validate token and set authentication in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateToken(token));
                } catch (RuntimeException e) {
                    logger.error("Failed to validate token: {}", e.getMessage());
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Set unauthorized response
                    return; // Stop further processing of the request
                }
            } else {
                logger.warn("Invalid Authorization header format.");
            }
        }

        filterChain.doFilter(request, response); // Continue the filter chain
    }
}
