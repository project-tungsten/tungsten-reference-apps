package com.tungsten.domain.referencedomainservice.listener;

import com.tungsten.domain.referencedomainservice.event.SampleAggregateCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SampleAggregateEventsListener {

  private final Logger logger;

  public SampleAggregateEventsListener(Logger logger) {
    this.logger = logger;
  }

  @EventHandler
  public void on(SampleAggregateCreatedEvent event) {
    logger.info("Received event: {}", event);
  }
}
