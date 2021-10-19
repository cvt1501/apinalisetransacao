package br.com.analisetransacao.usecase;

import br.com.analisetransacao.domain.Transacao;
import br.com.analisetransacao.domain.enums.StatusTransacaoEnum;
import br.com.analisetransacao.service.message.MensagemTransacaoAprovada;
import br.com.analisetransacao.gateway.TransacaoGateway;
import br.com.analisetransacao.service.TransacaoProducerService;
import br.com.analisetransacao.usecase.exception.ErroDeAtualizacaoUsecaseException;
import br.com.analisetransacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AprovarTransacaoUsecase {

    @Autowired
    private TransacaoGateway gateway;

    @Autowired
    private TransacaoProducerService service;

    public Transacao aprovarTransacao(Integer idTransacao) {

        log.info("Iniciando a aprovação da transacao de id {}", idTransacao);

        try {
            final Transacao transacao = gateway.aprovarTransacao(idTransacao, StatusTransacaoEnum.CONCLUIDA.toString());

            if(transacao == null) throw new ErroDeAtualizacaoUsecaseException(ConstanteUtils.ERRO_ATUALIZAR_TRANSACAO);

            service.send(
                    MensagemTransacaoAprovada
                            .builder()
                            .idTransacao(transacao.getIdTransacao())
                            .statusTransacao(transacao.getStatusTransacao())
                            .build());

            return transacao;
        } catch (ErroDeAtualizacaoUsecaseException ex) {
            log.error("Erro de atualização de transacao de id {}, causa {}", idTransacao, ex.getMessage());

            throw ex;
        }
    }

}
