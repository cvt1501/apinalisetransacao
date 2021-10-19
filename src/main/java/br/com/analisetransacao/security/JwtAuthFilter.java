package br.com.analisetransacao.security;

import br.com.analisetransacao.usecase.AutenticacaoUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService service;

    private final AutenticacaoUsecase usecase;

    public JwtAuthFilter(JwtService jwtService, AutenticacaoUsecase autenticacaoUsecase) {
        this.service = jwtService;
        this.usecase = autenticacaoUsecase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException, ServletException {

        log.info("Iniciando o filtro de autenticação jwt");

        String authorization = httpServletRequest.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer")) {
            final String token = authorization.split(" ")[1];

            if(Boolean.TRUE.equals(service.validarToken(token))) {
                final String emailUsuario = service.obterEmailUsuario(token);
                final UserDetails usuario = usecase.loadUserByUsername(emailUsuario);
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
