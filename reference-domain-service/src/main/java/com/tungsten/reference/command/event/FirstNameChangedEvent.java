package com.tungsten.reference.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstNameChangedEvent {
  private String aggregateId;
  private String firstName;
}
