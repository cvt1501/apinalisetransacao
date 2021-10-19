package br.com.analisetransacao.entrypoint.json.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoadorResponse {

    @JsonProperty("nome_doador")
    private String nome;

    private String cpf;

    @JsonProperty("endereco_doador")
    private EnderecoResponse endereco;

    @JsonProperty("pontos_doador")
    private Long pontos;

}
