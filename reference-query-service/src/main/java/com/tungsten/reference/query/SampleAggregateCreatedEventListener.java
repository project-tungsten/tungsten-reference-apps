package com.tungsten.reference.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SampleAggregateCreatedEventListener {

  private final Logger logger;

  public SampleAggregateCreatedEventListener(Logger logger) {
    this.logger = logger;
  }

  @StreamListener(Sink.INPUT)
  public void process1(Map<String, String> event) {
    logger.info("Received map: {}", event);
  }

  @StreamListener(Sink.INPUT)
  public void process2(AggregateCreatedEvent event) {
    logger.info("Received event: {}", event);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class AggregateCreatedEvent {
    private String sampleAggregateId;
    private String firstName;
    private String lastName;
  }
}
