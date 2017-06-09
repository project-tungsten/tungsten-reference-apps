package com.tungsten.reference.command.domain;

import com.tungsten.reference.command.command.CreateSampleAggregateCommand;
import com.tungsten.reference.command.event.SampleAggregateCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class SampleAggregate {

  @AggregateIdentifier
  private String id;

  @SuppressWarnings("unused")
  private SampleAggregate() {
  }

  @CommandHandler
  public SampleAggregate(CreateSampleAggregateCommand command) {
    apply(new SampleAggregateCreatedEvent(command.getId(), command.getFirstName(), command.getLastName()));
  }

  @EventSourcingHandler
  public void on(SampleAggregateCreatedEvent event) {
    this.id = event.getSampleAggregateId();
  }
}
