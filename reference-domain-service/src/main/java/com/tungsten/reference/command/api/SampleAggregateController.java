package com.tungsten.reference.command.api;

import com.tungsten.reference.command.command.ChangeFirstNameCommand;
import com.tungsten.reference.command.command.CreateAggregateCommand;
import com.tungsten.reference.command.util.IdGenerator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/aggregates")
public class SampleAggregateController {

  private final IdGenerator idGenerator;
  private final CommandGateway gateway;

  public SampleAggregateController(IdGenerator idGenerator, CommandGateway gateway) {
    this.idGenerator = idGenerator;
    this.gateway = gateway;
  }

  @ResponseStatus(ACCEPTED)
  @PostMapping
  public String create(@RequestBody CreateAggregateRequest request) {
    String id = idGenerator.generate();
    CreateAggregateCommand command = new CreateAggregateCommand(id, request.getFirstName(), request.getLastName());
    gateway.send(command);
    return id;
  }

  @ResponseStatus(ACCEPTED)
  @PutMapping("/{aggregateId}/change-first-name")
  public void update(@NotBlank @PathVariable String aggregateId,
                     @RequestBody ChangeFirstNameRequest request) {
    ChangeFirstNameCommand command = new ChangeFirstNameCommand(aggregateId, request.getFirstName());
    gateway.send(command);
  }
}
