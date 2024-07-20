package com.apptware.interview.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@JsonDeserialize(using = AdultDeserializer.class)
public class Adult {

  private String firstName;
  private String lastName;
  private Integer age;

  @JsonCreator
  public Adult(String firstName, String lastName, Integer age) {
    if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
      throw new IllegalArgumentException("Firstname or Lastname CANNOT be blank.");
    }

    if (age == null || age < 18) {
      throw new IllegalArgumentException("Inappropriate Age for an Adult.");
    }

    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }
}
