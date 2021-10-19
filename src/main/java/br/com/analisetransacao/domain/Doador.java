package br.com.analisetransacao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Doador {

    private Integer idDoador;

    private String nome;

    private String cpf;

    private Long pontos;

    private Endereco endereco;

}
