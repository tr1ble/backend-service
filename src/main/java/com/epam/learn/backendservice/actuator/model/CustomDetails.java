package com.epam.learn.backendservice.actuator.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomDetails {

  private Map<String, Object> details;

  @JsonAnyGetter
  public Map<String, Object> getDetails() {
    return this.details;
  }
}
