package com.apptware.interview.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This test class has a validation for {@link com.apptware.interview.serialization.Adult}. The
 * first test tests the validation using a constructor by creating an illegal entity and expecting
 * an error message for the same. The second test has a same purpose using serialization.
 *
 * <p>The candidate is expected to modify the test case and the corresponding class for which the
 * test case is written so that the appropriate exception is thrown with appropriate messages.
 */
class AdultTest {

  @Test
  void testConstructorValidation() {
    Assertions.assertThatThrownBy(() -> new Adult("", "", 18))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Firstname or Lastname CANNOT be blank.");
    Assertions.assertThatThrownBy(() -> new Adult("Firstname", "Lastname", 17))
        .isInstanceOf(IllegalArgumentException.class)
    // Changes expected ----->
        .hasMessage("Inappropriate Age for an Adult.");
    // <----- Changes expected

    String json1 =
        """
            {
              "firstName": "",
              "lastName": "",
              "age": 18
            }
            """;

    String json2 =
        """
            {
              "firstName": "Firstname",
              "lastName": "Lastname",
              "age": 17
            }
            """;

    ObjectMapper objectMapper = new ObjectMapper();
    Assertions.assertThatThrownBy(
            () -> {
              Adult adult = objectMapper.readValue(json1, Adult.class);
              System.out.println(adult);
            })
    // Changes expected ----->
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Firstname or Lastname CANNOT be blank.");
    // <----- Changes expected
    Assertions.assertThatThrownBy(
            () -> {
              Adult adult = objectMapper.readValue(json2, Adult.class);
              System.out.println(adult);
            })
    // Changes expected ----->
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Inappropriate Age for an Adult.");
    // <----- Changes expected
  }
}
