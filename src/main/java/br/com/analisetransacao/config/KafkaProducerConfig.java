package br.com.analisetransacao.config;

import br.com.analisetransacao.service.message.MensagemTransacaoAprovada;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String enderecoKafka;

    @Value("${spring.kafka.topics.topic-aprovacao-transacao}")
    private String topico;

    @Bean
    public NewTopic criarTopico() {
        return new NewTopic(topico, 3, (short) 1);
    }

    @Bean
    public ProducerFactory<String, MensagemTransacaoAprovada> transacaoProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, enderecoKafka);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, MensagemTransacaoAprovada> transacaoKafkaTemplate() {
        return new KafkaTemplate<>(transacaoProducerFactory());
    }

}
