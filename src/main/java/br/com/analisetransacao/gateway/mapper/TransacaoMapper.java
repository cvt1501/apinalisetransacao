package br.com.analisetransacao.gateway.mapper;

import br.com.analisetransacao.domain.Transacao;
import br.com.analisetransacao.gateway.dto.TransacaoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    TransacaoDto toJsonDto(Transacao transacao);

    Transacao toDomain(TransacaoDto dto);

}
