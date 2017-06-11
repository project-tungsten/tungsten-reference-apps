package com.tungsten.reference.query.listener;

import com.tungsten.reference.query.repository.SampleAggregateViewRepository;
import com.tungsten.reference.query.view.SampleAggregateView;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SampleAggregateCreatedEventListener {

  private final Logger logger;
  private final SampleAggregateViewRepository repository;

  public SampleAggregateCreatedEventListener(Logger logger, SampleAggregateViewRepository repository) {
    this.logger = logger;
    this.repository = repository;
  }

  @EventHandler(condition = "headers['axon-message-type']=='com.tungsten.reference.command.event.AggregateCreatedEvent'")
  public void onCreate(Map<String, String> event) {
    logger.info("Received create event: {}", event);
    SampleAggregateView view = new SampleAggregateView(event.get("aggregateId"),
        event.get("firstName"),
        event.get("lastName"));
    repository.save(view);
  }

  @EventHandler(condition = "headers['axon-message-type']=='com.tungsten.reference.command.event.FirstNameChangedEvent'")
  public void onUpdate(Map<String, String> event) {
    logger.info("Received update event: {}", event);
    SampleAggregateView view = repository.findOne(event.get("aggregateId"));
    view.setFirstName(event.get("firstName"));
    repository.save(view);
  }
}
