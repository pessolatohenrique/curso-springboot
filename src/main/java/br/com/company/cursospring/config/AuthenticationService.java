package br.com.company.cursospring.config;

import br.com.company.cursospring.models.Usuario;
import br.com.company.cursospring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if (usuario.isPresent()) {
            System.out.println(usuario.get());
            return usuario.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
