package com.tungsten.reference.query.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleAggregateView {

  @Id
  private String id;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
}
