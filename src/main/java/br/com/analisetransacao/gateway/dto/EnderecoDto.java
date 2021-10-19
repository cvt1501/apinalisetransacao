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
public class EnderecoDto implements Serializable {

    private static final long serialVersionUID = -4454557023358034680L;

    private Integer idEndereco;

    private String rua;

    private String bairro;

    private String cep;

    private Integer numero;

    private String cidade;

    private String estado;

}
