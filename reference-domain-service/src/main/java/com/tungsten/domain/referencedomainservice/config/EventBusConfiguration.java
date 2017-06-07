package com.tungsten.domain.referencedomainservice.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusConfiguration {

  private final AmqpAdmin admin;

  public EventBusConfiguration(AmqpAdmin admin) {
    this.admin = admin;
  }

  @Bean
  public Exchange exchange() {
    Exchange exchange = ExchangeBuilder.fanoutExchange("tungsten.event.bus").durable().build();
    admin.declareExchange(exchange);
    return exchange;
  }

  @Bean
  public Queue queue() {
    Queue queue = QueueBuilder.durable("reference.domain.queue").build();
    admin.declareQueue(queue);
    return queue;
  }

  @Bean
  public Binding binding() {
    Binding binding = BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    admin.declareBinding(binding);
    return binding;
  }
}
