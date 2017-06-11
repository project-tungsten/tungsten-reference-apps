package com.tungsten.reference.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAggregateCommand {
  @NotNull
  @TargetAggregateIdentifier
  private String id;
  @NotBlank
  @Length(min = 3)
  private String firstName;
  @NotBlank
  @Length(min = 3)
  private String lastName;
}
