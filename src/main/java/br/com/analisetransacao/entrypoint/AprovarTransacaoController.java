package br.com.analisetransacao.entrypoint;

import br.com.analisetransacao.entrypoint.json.response.DataResponse;
import br.com.analisetransacao.entrypoint.json.response.TransacaoAprovadaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface AprovarTransacaoController {

    @PutMapping("/v1/transacao/aprovar/{idTransacao}")
    ResponseEntity<DataResponse<TransacaoAprovadaResponse>> aprovarTransacao(@PathVariable Integer idTransacao);

}
