package br.com.analisetransacao.entrypoint.impl;

import br.com.analisetransacao.entrypoint.ConsultaTransacoesController;
import br.com.analisetransacao.entrypoint.json.response.DataResponse;
import br.com.analisetransacao.entrypoint.json.response.TransacaoResponse;
import br.com.analisetransacao.entrypoint.mapper.TransacaoEntrypointMapper;
import br.com.analisetransacao.usecase.ConsultaTransacoesUsecase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ConsultaTransacoesControllerImpl implements ConsultaTransacoesController {

    @Autowired
    private ConsultaTransacoesUsecase usecase;

    @Autowired
    private TransacaoEntrypointMapper mapper;

    @Override
    public ResponseEntity<DataResponse<List<TransacaoResponse>>> consultarTransacoes() {

        log.info("Iniciando a consulta de transacoes");

        return ResponseEntity.ok(DataResponse
                .<List<TransacaoResponse>>builder()
                .data(usecase.consultarTransacoes()
                        .stream()
                        .map(mapper::domainToJsonResponse)
                        .collect(Collectors.toList()))
                .build());
    }

}
