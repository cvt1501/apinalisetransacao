package br.com.analisetransacao.entrypoint.impl;

import br.com.analisetransacao.domain.Transacao;
import br.com.analisetransacao.entrypoint.AprovarTransacaoController;
import br.com.analisetransacao.entrypoint.json.response.DataResponse;
import br.com.analisetransacao.entrypoint.json.response.TransacaoAprovadaResponse;
import br.com.analisetransacao.usecase.AprovarTransacaoUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AprovarTransacaoControllerImpl implements AprovarTransacaoController {

    @Autowired
    private AprovarTransacaoUsecase usecase;

    @Override
    public ResponseEntity<DataResponse<TransacaoAprovadaResponse>> aprovarTransacao(Integer idTransacao) {

        log.info("Iniciando a aprovação da transacao de id {}", idTransacao);

        final Transacao transacao = usecase.aprovarTransacao(idTransacao);

        return ResponseEntity.ok(DataResponse
                .<TransacaoAprovadaResponse>builder()
                .data(TransacaoAprovadaResponse
                        .builder()
                        .mensagem(String.format("Transação de id %s aprovada com sucesso", transacao.getIdTransacao()))
                        .build())
                .build());
    }

}
