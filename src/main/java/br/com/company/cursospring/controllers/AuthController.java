package br.com.company.cursospring.controllers;

import br.com.company.cursospring.config.TokenService;
import br.com.company.cursospring.models.dto.TokenDto;
import br.com.company.cursospring.models.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
            UsernamePasswordAuthenticationToken authData = new UsernamePasswordAuthenticationToken(form.getUsuario(), form.getSenha());
            Authentication authentication = authenticationManager.authenticate(authData);
            String token = tokenService.generate(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
    }
}
