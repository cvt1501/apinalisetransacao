package br.com.analisetransacao.usecase;

import br.com.analisetransacao.domain.Usuario;
import br.com.analisetransacao.usecase.exception.UsuarioNaoEncontradoUsecaseException;
import br.com.analisetransacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AutenticacaoUsecase implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails autenticar(Usuario usuario) {

        log.info("Iniciando a autenticação do usuario de email {}", usuario.getEmail());

        try {
            final UserDetails userDetails = loadUserByUsername(usuario.getEmail());
            final Boolean senhaValidada = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());

            if(!senhaValidada) throw new UsuarioNaoEncontradoUsecaseException(ConstanteUtils.ERRO_LOGIN);

            return userDetails;
        } catch (UsuarioNaoEncontradoUsecaseException ex) {
            log.error("Erro na autenticação do usuario, causa {}", ex.getMessage());

            throw ex;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("Carregando usuario de email {} para autenticação", email);

        if(!"kevin_cavenatti@hotmail.com".equals(email)) throw new UsuarioNaoEncontradoUsecaseException(ConstanteUtils.ERRO_LOGIN);

        String[] roles = new String[]{"ADMIN"};

        return User
                .builder()
                .username("kevin_cavenatti@hotmail.com")
                .password(passwordEncoder.encode("aff123"))
                .roles(roles)
                .build();
    }

}
