package br.com.analisetransacao.entrypoint.json.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoResponse {

    private String rua;

    private String bairro;

    private Integer numero;

    private String cidade;

    private String estado;

    private String cep;

}
