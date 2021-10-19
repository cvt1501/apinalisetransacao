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
public class DoadorDto implements Serializable {

    private static final long serialVersionUID = 6499910773613322395L;

    private Integer idDoador;

    private String nome;

    private String cpf;

    private Long pontos;

    private EnderecoDto endereco;

}
