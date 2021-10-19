package br.com.analisetransacao.usecase;

import br.com.analisetransacao.domain.Transacao;
import br.com.analisetransacao.gateway.TransacaoGateway;
import br.com.analisetransacao.usecase.exception.ErroDeCadastroUsecaseException;
import br.com.analisetransacao.utils.ConstanteUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CadastroTransacaoUsecase {

    @Autowired
    public TransacaoGateway gateway;

    public Transacao cadastrarTransacao(Transacao transacao) {

        log.info("Iniciando o cadastro de transacao de id {}", transacao.getIdTransacao());

        try {
            final Transacao transacaoSalva = gateway.cadastrarTransacao(transacao);

            if(transacao == null) throw new ErroDeCadastroUsecaseException(ConstanteUtils.ERRO_CADASTRAR_TRANSACAO);

            return transacaoSalva;
        } catch (ErroDeCadastroUsecaseException ex) {
            log.error("Erro ao realizar cadastro de transacao, causa {}", ex.getMessage());

            throw ex;
        }
    }

}
