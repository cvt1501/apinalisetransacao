package br.com.analisetransacao.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@RedisHash("TRANSACAO")
public class TransacaoDto implements Serializable {

    private static final long serialVersionUID = 6087043424064545449L;

    @Id
    private Integer idTransacao;

    private Long pontos;

    private String statusTransacao;

    private LocalDateTime dataTransacao;

    private PoloDto polo;

    private DoadorDto doador;

    private List<LivroDto> livros;

}
