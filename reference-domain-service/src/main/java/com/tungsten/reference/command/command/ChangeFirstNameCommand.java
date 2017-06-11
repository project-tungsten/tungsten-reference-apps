package com.tungsten.reference.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeFirstNameCommand {

  @NotBlank
  @TargetAggregateIdentifier
  private String customerId;

  @NotBlank
  @Length(min = 3)
  private String firstName;

}
