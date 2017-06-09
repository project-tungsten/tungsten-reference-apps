package com.tungsten.reference.command.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.boot.AMQPProperties;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AMQPProperties.class)
public class EventBusConfiguration {

  private final AmqpAdmin admin;
  private final AMQPProperties properties;

  public EventBusConfiguration(AmqpAdmin admin, AMQPProperties properties) {
    this.admin = admin;
    this.properties = properties;
  }

  @Bean
  public Exchange exchange() {
    Exchange exchange = ExchangeBuilder
        .topicExchange(properties.getExchange())
        .durable()
        .build();
    admin.declareExchange(exchange);
    return exchange;
  }

  @Bean
  public Serializer serializer(ObjectMapper mapper) {
    return new JacksonSerializer(mapper);
  }

}
