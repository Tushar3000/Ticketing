package com.tushar.ticket.filters;

import java.io.IOException;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tushar.ticket.domain.entities.User;
import com.tushar.ticket.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserProvisiongFilter extends OncePerRequestFilter{
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication !=null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt jwt)
        {
            try{
            UUID keycloakId=UUID.fromString(jwt.getSubject());

            if(!userRepository.existsById(keycloakId))
            {
                User user=new User();
                user.setId(keycloakId);
                user.setName(jwt.getClaimAsString("preferred_username"));
                user.setEmail(jwt.getClaimAsString("email"));

                userRepository.save(user);
            }
        }
            catch (IllegalArgumentException e) {
                // Log invalid UUID format
                System.err.println("Invalid UUID in JWT subject: " + jwt.getSubject());
            } catch (Exception e) {
                // Log but don't block the request
                System.err.println("Error provisioning user: " + e.getMessage());
            }
        
            filterChain.doFilter(request, response);
        }
        //throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }
    
}
