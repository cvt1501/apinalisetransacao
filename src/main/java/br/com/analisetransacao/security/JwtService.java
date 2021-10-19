package br.com.analisetransacao.security;

import br.com.analisetransacao.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {

        log.info("Gerando o token do usuario de email {}", usuario.getEmail());

        long expString = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(usuario.getEmail())
                .setExpiration(data)
                .signWith( SignatureAlgorithm.HS512, chaveAssinatura )
                .compact();
    }

    public boolean validarToken(String token){

        log.info("Iniciando a validação do token {}", token);

        try {
            final String tokenFormatado = removerBearer(token);
            final Claims claims = obterClaims(tokenFormatado);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =
                    dataExpiracao.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (Exception e){
            return false;
        }
    }

    public String obterEmailUsuario(String token) throws ExpiredJwtException {

        final String tokenFormatado = removerBearer(token);

        log.info("Obtendo login do token {}", tokenFormatado);

        return obterClaims(tokenFormatado).getSubject();
    }

    private String removerBearer(String token) {
        log.info("Removendo bearer do token");

        if(!token.contains("Bearer")) return token;

        final String tokenFormatado = token.replace("Bearer ", "");

        return tokenFormatado;
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {

        log.info("Obtendo claims do token {}", token);

        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

}
