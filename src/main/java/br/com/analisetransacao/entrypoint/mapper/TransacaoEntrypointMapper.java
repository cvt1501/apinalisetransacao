package br.com.analisetransacao.entrypoint.mapper;

import br.com.analisetransacao.domain.Transacao;
import br.com.analisetransacao.entrypoint.json.response.TransacaoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoEntrypointMapper {

    TransacaoResponse domainToJsonResponse(Transacao transacao);

}
