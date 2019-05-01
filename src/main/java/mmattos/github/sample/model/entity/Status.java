package mmattos.github.sample.model.entity;

import static org.apache.commons.lang3.StringUtils.upperCase;

import com.fasterxml.jackson.annotation.JsonValue;
import org.modelmapper.AbstractConverter;

public enum Status {
  ENABLED("Enabled"),
  DISABLED("Disabled");

  private String value;

  public static final AbstractConverter ABSTRACT_CONVERTER = new AbstractConverter<String, Status>() {
    @Override
    protected Status convert(String status) {
      return Status.valueOf(upperCase(status));
    }
  };

  Status(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }
}

