package br.com.analisetransacao.usecase;

import br.com.analisetransacao.domain.Transacao;
import br.com.analisetransacao.gateway.TransacaoGateway;
import br.com.analisetransacao.usecase.exception.DadosNaoEncontradoUsecaseException;
import br.com.analisetransacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConsultaTransacoesUsecase {

    @Autowired
    public TransacaoGateway gateway;

    public List<Transacao> consultarTransacoes() {

        log.info("Iniciando a consulta de transacoes");

        try {
            final List<Transacao> transacoes = gateway.consultarTransacoes();

            if(transacoes.isEmpty()) throw new DadosNaoEncontradoUsecaseException(ConstanteUtils.ERRO_TRANSACAO_NAO_ENCONTRADA);

            return transacoes;
        } catch (DadosNaoEncontradoUsecaseException ex) {
            log.error("Erro ao realizar consulta de transacoes, causa {}", ex.getMessage());

            throw ex;
        }
    }

}
