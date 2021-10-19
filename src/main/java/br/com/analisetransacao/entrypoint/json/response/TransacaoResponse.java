package br.com.analisetransacao.entrypoint.json.response;

import br.com.analisetransacao.domain.Doador;
import br.com.analisetransacao.domain.Livro;
import br.com.analisetransacao.domain.Polo;
import br.com.analisetransacao.domain.enums.StatusTransacaoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoResponse {

    private Integer idTransacao;

    private Long pontos;

    private StatusTransacaoEnum statusTransacao;

    private Polo polo;

    private Doador doador;

    private List<Livro> livros;

}
