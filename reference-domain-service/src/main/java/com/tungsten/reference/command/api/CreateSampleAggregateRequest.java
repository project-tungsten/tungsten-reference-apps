package com.tungsten.reference.command.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class CreateSampleAggregateRequest {

  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
}