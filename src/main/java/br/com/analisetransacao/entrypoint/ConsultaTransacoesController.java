package br.com.analisetransacao.entrypoint;

import br.com.analisetransacao.entrypoint.json.response.DataResponse;
import br.com.analisetransacao.entrypoint.json.response.TransacaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ConsultaTransacoesController {

    @GetMapping("/v1/transacoes")
    ResponseEntity<DataResponse<List<TransacaoResponse>>> consultarTransacoes();

}
