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
public class LivroDto implements Serializable {

    private static final long serialVersionUID = 5389865584691696651L;

    private Integer idLivro;

    private String nome;

    private String autor;

    private String editora;

}
