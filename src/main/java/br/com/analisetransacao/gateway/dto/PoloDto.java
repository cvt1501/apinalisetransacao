package br.com.analisetransacao.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PoloDto implements Serializable {

    private static final long serialVersionUID = -5025504505544163965L;

    private Integer idPolo;

    private String nome;

    private Long codigoPolo;

    private EnderecoDto endereco;

}
