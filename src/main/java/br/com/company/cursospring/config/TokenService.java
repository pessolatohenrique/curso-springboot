package br.com.company.cursospring.config;

import br.com.company.cursospring.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generate(Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date dataAtual = new Date();
        Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("API Fórum")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(dataAtual)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
                ;
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return Long.parseLong(claimsJws.getBody().getSubject());
    }
}
