package com.jsrdxzw.mallshopbe.core.configuration;

import com.jsrdxzw.mallshopbe.bo.OrderListenerBo;
import com.jsrdxzw.mallshopbe.core.listener.OrderListener;
import lombok.Setter;
import org.apache.pulsar.client.api.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuzhiwei
 * @date 2021-02-07
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "pulsar")
public class PulsarConfiguration {

    private String serviceUrl;
    private String mallOrderTopic;
    private Integer listenerThreadNum;
    private String mallOrderSubscriptionName;
    private String mallOrderConsumerName;
    private String mallOrderProducerName;

    @Bean
    public PulsarClient pulsarClient() throws PulsarClientException {
        return PulsarClient.builder().serviceUrl(serviceUrl).listenerThreads(listenerThreadNum).build();
    }

    @Bean
    public Producer<OrderListenerBo> mallOrderProducer(PulsarClient pulsarClient) throws PulsarClientException {
        return pulsarClient.newProducer(Schema.JSON(OrderListenerBo.class))
                .topic(mallOrderTopic)
                .compressionType(CompressionType.ZLIB)
                .blockIfQueueFull(true)
                .producerName(mallOrderProducerName)
                .create();
    }

    @Bean
    public Consumer<OrderListenerBo> mallOrderConsumer(PulsarClient pulsarClient, OrderListener orderListener)
            throws PulsarClientException {
        return pulsarClient.newConsumer(Schema.JSON(OrderListenerBo.class))
                .topic(mallOrderTopic)
                .subscriptionType(SubscriptionType.Shared)
                .subscriptionName(mallOrderSubscriptionName)
                .consumerName(mallOrderConsumerName)
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .deadLetterPolicy(DeadLetterPolicy.builder().maxRedeliverCount(5).build())
                .messageListener(orderListener)
                .subscribe();
    }
}
