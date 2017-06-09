package com.tungsten.reference.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleAggregateCreatedEvent {
  private String sampleAggregateId;
  private String firstName;
  private String lastName;
}
