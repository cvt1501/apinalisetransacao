package br.com.analisetransacao.entrypoint.mapper;

import br.com.analisetransacao.domain.Usuario;
import br.com.analisetransacao.entrypoint.json.request.AutenticarUsuarioRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntrypointMapper {

    Usuario autenticarUsuarioRequestToDomain(AutenticarUsuarioRequest request);

}
