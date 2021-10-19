package br.com.analisetransacao.gateway;

import br.com.analisetransacao.domain.Transacao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransacaoGateway {

    List<Transacao> consultarTransacoes();

    @Transactional
    Transacao cadastrarTransacao(Transacao transacao);

    @Transactional
    Transacao aprovarTransacao(Integer idTransacao, String statusTransacao);

}
