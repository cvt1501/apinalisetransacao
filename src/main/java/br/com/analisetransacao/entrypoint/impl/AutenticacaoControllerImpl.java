package br.com.analisetransacao.entrypoint.impl;

import br.com.analisetransacao.domain.Usuario;
import br.com.analisetransacao.entrypoint.AutenticacaoController;
import br.com.analisetransacao.entrypoint.exception.ValidacaoControllerException;
import br.com.analisetransacao.entrypoint.json.request.AutenticarUsuarioRequest;
import br.com.analisetransacao.entrypoint.json.response.AutenticarUsuarioResponse;
import br.com.analisetransacao.entrypoint.json.response.DataResponse;
import br.com.analisetransacao.entrypoint.mapper.UsuarioEntrypointMapper;
import br.com.analisetransacao.security.JwtService;
import br.com.analisetransacao.usecase.AutenticacaoUsecase;
import br.com.analisetransacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AutenticacaoControllerImpl implements AutenticacaoController {

    @Autowired
    private AutenticacaoUsecase usecase;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioEntrypointMapper mapper;

    @Override
    public ResponseEntity<DataResponse<AutenticarUsuarioResponse>> autenticarUsuario(AutenticarUsuarioRequest request) {

        log.info("Iniciando a autenticação do usuario de email {}", request.getEmail());

        validarRequest(request);

        final UserDetails userDetails = usecase.autenticar(mapper.autenticarUsuarioRequestToDomain(request));
        final String token = jwtService.gerarToken(Usuario
                .builder()
                .email(userDetails.getUsername())
                .senha(userDetails.getPassword())
                .build());

        return ResponseEntity.ok(DataResponse
                .<AutenticarUsuarioResponse>builder()
                .data(AutenticarUsuarioResponse
                        .builder()
                        .email(userDetails.getUsername())
                        .token(token)
                        .build())
                .build()
        );
    }

    private void validarRequest(AutenticarUsuarioRequest request) {

        log.info("Validando as propiedades do request");

        try {
            if(request.getEmail().isBlank() || request.getSenha().isBlank()) {
                throw new ValidacaoControllerException(ConstanteUtils.ERRO_CAMPO_INVALIDO);
            }
        } catch (ValidacaoControllerException ex) {
            log.error("Erro ao validar propiedades do request, causa {}", ex.getMessage());

            throw ex;
        }
    }

}
