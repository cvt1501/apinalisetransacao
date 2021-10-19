package br.com.analisetransacao.gateway.impl;

import br.com.analisetransacao.domain.Transacao;
import br.com.analisetransacao.gateway.TransacaoGateway;
import br.com.analisetransacao.gateway.dto.TransacaoDto;
import br.com.analisetransacao.gateway.exception.GatewayException;
import br.com.analisetransacao.gateway.mapper.TransacaoMapper;
import br.com.analisetransacao.gateway.repository.TransacaoRepository;
import br.com.analisetransacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TransacaoGatewayImpl implements TransacaoGateway {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private TransacaoMapper mapper;

    @Override
    public List<Transacao> consultarTransacoes() {

        log.info("Iniciando a consulta de transacoes");

        try {
            final List<TransacaoDto> transacoes = repository.findAll();

            return transacoes
                    .stream()
                    .map(mapper::toDomain)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Erro ao realizar cadastro de transacao, causa {}", ex.getMessage());

            throw new GatewayException(ConstanteUtils.ERRO_CONSULTAR_TRANSACAO);
        }
    }

    @Override
    public Transacao cadastrarTransacao(Transacao transacao) {

        log.info("Iniciando o cadastro da transacao de id {}", transacao.getIdTransacao());

        try {
            repository.save(mapper.toJsonDto(transacao));

            return transacao;
        } catch (Exception ex) {
            log.error("Erro ao realizar cadastro de transacao, causa {}", ex.getMessage());

            throw new GatewayException(ConstanteUtils.ERRO_CADASTRAR_TRANSACAO);
        }
    }

    @Override
    public Transacao aprovarTransacao(Integer idTransacao, String statusTransacao) {

        log.info("Iniciando a atualização da transacao de id {}", idTransacao);

        try {
            return mapper.toDomain(repository.update(idTransacao, statusTransacao));
        } catch (Exception ex) {
            log.error("Erro ao realizar cadastro de transacao, causa {}", ex.getMessage());

            throw new GatewayException(ConstanteUtils.ERRO_ATUALIZAR_TRANSACAO);
        }
    }

}
