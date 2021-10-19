package br.com.analisetransacao.entrypoint.json.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutenticarUsuarioRequest {

    private String email;

    private String senha;

}
