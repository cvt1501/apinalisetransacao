package br.com.analisetransacao.service;

import br.com.analisetransacao.service.message.MensagemTransacaoAprovada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransacaoProducerService {

    @Value("${spring.kafka.topics.topic-aprovacao-transacao}")
    private String topico;

    @Autowired
    private KafkaTemplate<String, MensagemTransacaoAprovada> kafkaTemplate;

    public void send(MensagemTransacaoAprovada mensagemTransacaoAprovada) {
        kafkaTemplate.send(topico, mensagemTransacaoAprovada).addCallback(
                sucess -> log.info("Mensagem com transacao enviada com sucesso {}", sucess.getProducerRecord().value()),
                failure -> log.error("Erro ao enviar mensagem, causa {}", failure.getMessage())
        );
    }

}
