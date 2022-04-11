package br.com.company.cursospring.config;

import br.com.company.cursospring.models.Usuario;
import br.com.company.cursospring.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private UsuarioRepository repository;

    public AuthTokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);
        boolean validToken = tokenService.isValidToken(token);
        
        if (validToken) {
            this.authenticateUser(token);
        }
        
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        Long userId = tokenService.getIdUser(token);
        Usuario user = repository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer")) {
            return null;
        }

        return token.replace("Bearer".trim(), "").trim();
    }
}
