package com.tungsten.reference.command.domain;

import com.tungsten.reference.command.command.ChangeFirstNameCommand;
import com.tungsten.reference.command.command.CreateAggregateCommand;
import com.tungsten.reference.command.event.FirstNameChangedEvent;
import com.tungsten.reference.command.event.AggregateCreatedEvent;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class SampleAggregateTest {

  private static final String ID = "test-id";
  private static final String JOHN = "John";
  private static final String MIKE = "Mike";
  private static final String SMITH = "Smith";
  private FixtureConfiguration<SampleAggregate> fixture;

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture<>(SampleAggregate.class);
    fixture.registerCommandHandlerInterceptor(new BeanValidationInterceptor<>());
  }

  @Test
  public void shouldCreateAggregate() throws Exception {
    fixture.given()
        .when(new CreateAggregateCommand(ID, JOHN, SMITH))
        .expectSuccessfulHandlerExecution()
        .expectEvents(new AggregateCreatedEvent(ID, JOHN, SMITH));
  }

  @Test
  public void shouldNotCreateAggregateWithInvalidFirstName() throws Exception {
    fixture.given()
        .when(new CreateAggregateCommand(ID, "Jo", SMITH))
        .expectNoEvents()
        .expectException(JSR303ViolationException.class);
  }

  @Test
  public void shouldChangeFirstNameIfDifferentFromOriginal() throws Exception {
    fixture.given(new AggregateCreatedEvent(ID, JOHN, SMITH))
        .when(new ChangeFirstNameCommand(ID, MIKE))
        .expectSuccessfulHandlerExecution()
        .expectEvents(new FirstNameChangedEvent(ID, MIKE));
  }

  @Test
  public void shouldChangeFirstNameMoreThanOnce() throws Exception {
    fixture.given(new AggregateCreatedEvent(ID, JOHN, SMITH),
        new FirstNameChangedEvent(ID, MIKE))
        .when(new ChangeFirstNameCommand(ID, JOHN))
        .expectSuccessfulHandlerExecution()
        .expectEvents(new FirstNameChangedEvent(ID, JOHN));
  }

  @Test
  public void shouldNotEmitEventIfFirstNameDoesNotChange() throws Exception {
    fixture.given(new AggregateCreatedEvent(ID, JOHN, SMITH))
        .when(new ChangeFirstNameCommand(ID, JOHN))
        .expectSuccessfulHandlerExecution()
        .expectNoEvents();
  }
}