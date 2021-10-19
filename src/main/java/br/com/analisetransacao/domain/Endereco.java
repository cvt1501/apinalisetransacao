package br.com.analisetransacao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Endereco {

    private Integer idEndereco;

    private String rua;

    private String bairro;

    private String cep;

    private Integer numero;

    private String cidade;

    private String estado;

}
