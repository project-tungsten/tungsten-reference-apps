package com.tungsten.reference.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSampleAggregateCommand {
  @NotNull
  @TargetAggregateIdentifier
  private String id;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
}
