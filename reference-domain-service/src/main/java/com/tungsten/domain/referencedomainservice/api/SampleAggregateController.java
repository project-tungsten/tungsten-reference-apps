package com.tungsten.domain.referencedomainservice.api;

import com.tungsten.domain.referencedomainservice.command.CreateSampleAggregateCommand;
import com.tungsten.domain.referencedomainservice.util.IdGenerator;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
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
  public void create(@RequestBody CreateSampleAggregateRequest request) {
    CreateSampleAggregateCommand command = new CreateSampleAggregateCommand(idGenerator.generate(), request.getFirstName(), request.getLastName());
    gateway.send(command);
  }

}
