package br.com.analisetransacao.service.message;

import br.com.analisetransacao.domain.enums.StatusTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MensagemTransacaoAprovada {

    private Integer idTransacao;

    private StatusTransacaoEnum statusTransacao;

}
