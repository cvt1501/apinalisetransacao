package br.com.analisetransacao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Livro {

    private Integer idLivro;

    private String nome;

    private String autor;

    private String editora;

}
