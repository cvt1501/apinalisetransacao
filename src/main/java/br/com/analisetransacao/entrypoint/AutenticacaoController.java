package br.com.analisetransacao.entrypoint;

import br.com.analisetransacao.entrypoint.json.request.AutenticarUsuarioRequest;
import br.com.analisetransacao.entrypoint.json.response.AutenticarUsuarioResponse;
import br.com.analisetransacao.entrypoint.json.response.DataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AutenticacaoController {

    @PostMapping("/signin")
    ResponseEntity<DataResponse<AutenticarUsuarioResponse>> autenticarUsuario(@RequestBody AutenticarUsuarioRequest request);

}
