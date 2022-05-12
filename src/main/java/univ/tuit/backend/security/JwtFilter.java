package univ.tuit.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import univ.tuit.backend.config.exception.klass.InvalidParameterException;
import univ.tuit.backend.store.jpo.UserJpo;
import univ.tuit.backend.store.repo.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager manager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserJpo userJpo = getUserFromRequest(request);
        try {
            if (userJpo != null
                    && userJpo.isAccountNonExpired()
                    && userJpo.isAccountNonLocked()
                    && userJpo.isCredentialsNonExpired()
                    && userJpo.isEnabled()) {
                UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(userJpo, null, userJpo.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
        } catch (Exception e) {
            response.sendError(e.hashCode(), e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    public UserJpo getUserFromRequest(HttpServletRequest request) throws InvalidParameterException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.length() != 6) {
            String token = authorization.substring(7);
            boolean validateJwtToken = jwtProvider.validateJwtToken(token);
            if (validateJwtToken) {
                Integer userIdFromToken = jwtProvider.getUserIdFromToken(token);
                return userRepository.getById(userIdFromToken);
            }
        }
        return null;
//        throw new InvalidParameterException("User data error");
    }
}
