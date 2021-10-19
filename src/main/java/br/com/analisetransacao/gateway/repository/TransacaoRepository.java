package br.com.analisetransacao.gateway.repository;

import br.com.analisetransacao.domain.enums.StatusTransacaoEnum;
import br.com.analisetransacao.gateway.dto.TransacaoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TransacaoRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisKeyValueTemplate redisKeyValueTemplate;

    private static final String KEY = "TRANSACAO";

    public List<TransacaoDto> findAll() {

        log.info("Iniciando a consulta de transacao");

        try {
            final List<TransacaoDto> transacoes = redisTemplate.opsForHash().values(KEY);

            return transacoes;
        } catch (Exception ex) {
            log.error("Erro ao consultar transacoes, causa {}", ex.getMessage());

            throw ex;
        }
    }

    public TransacaoDto save(final TransacaoDto transacaoDto) {

        log.info("Iniciando o cadastro da transacao");

        try {
            redisTemplate.opsForHash().put(KEY, transacaoDto.getIdTransacao(), transacaoDto);

            return transacaoDto;
        } catch (Exception ex) {
            log.error("Erro ao cadastrar transacao de id {}, causa {}", transacaoDto.getIdTransacao(), ex.getMessage());

            throw ex;
        }
    }

    public TransacaoDto update(final Integer idTransacao, final String statusTransacao) {

        log.info("Iniciando a atualização da transacao de id {}", idTransacao);

        try {
            TransacaoDto transacaoDto = (TransacaoDto) redisTemplate.opsForHash().get(KEY, idTransacao);

            transacaoDto.setStatusTransacao(statusTransacao);

            redisTemplate.opsForHash().put(KEY, transacaoDto.getIdTransacao(), transacaoDto);

            return transacaoDto;
        } catch (Exception ex) {
            log.error("Erro ao atualizar transacao de id {}, causa {}", idTransacao, ex.getMessage());

            throw ex;
        }
    }

}
