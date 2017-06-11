package com.tungsten.reference.command.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeFirstNameRequest {
  @NotBlank
  private String firstName;
}
