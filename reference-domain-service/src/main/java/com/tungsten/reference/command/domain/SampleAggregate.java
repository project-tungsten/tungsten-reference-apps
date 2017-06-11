package com.tungsten.reference.command.domain;

import com.tungsten.reference.command.command.ChangeFirstNameCommand;
import com.tungsten.reference.command.command.CreateAggregateCommand;
import com.tungsten.reference.command.event.FirstNameChangedEvent;
import com.tungsten.reference.command.event.AggregateCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class SampleAggregate {

  @AggregateIdentifier
  private String id;
  private String firstName;

  @SuppressWarnings("unused")
  private SampleAggregate() {
  }

  @CommandHandler
  public SampleAggregate(CreateAggregateCommand command) {
    apply(new AggregateCreatedEvent(command.getId(), command.getFirstName(), command.getLastName()));
  }

  @EventSourcingHandler
  public void on(AggregateCreatedEvent event) {
    this.id = event.getAggregateId();
    this.firstName = event.getFirstName();
  }

  @CommandHandler
  public void changeFirstName(ChangeFirstNameCommand command) {
    if (!firstName.equals(command.getFirstName())) {
      apply(new FirstNameChangedEvent(id, command.getFirstName()));
    }
  }

  @EventSourcingHandler
  public void on(FirstNameChangedEvent event) {
    this.firstName = event.getFirstName();
  }
}
