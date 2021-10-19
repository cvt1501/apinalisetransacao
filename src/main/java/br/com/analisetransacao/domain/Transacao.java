package br.com.analisetransacao.domain;

import br.com.analisetransacao.domain.enums.StatusTransacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transacao {

    private Integer idTransacao;

    private Long pontos;

    private StatusTransacaoEnum statusTransacao;

    private LocalDateTime dataTransacao;

    private Polo polo;

    private Doador doador;

    private List<Livro> livros;

}
