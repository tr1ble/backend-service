package com.epam.learn.backendservice.actuator.endpoint;

import com.epam.learn.backendservice.actuator.model.CustomDetails;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom-details")
public class CustomDetailsEndpoint {

  private static final String CUSTOM_DETAILS_END_POINT = "custom-details-end-point";

  @ReadOperation
  public CustomDetails details() {
    Map<String, Object> details = new LinkedHashMap<>();
    details.put("CustomDetails", "Custom details");
    return CustomDetails.builder()
        .details(details)
        .build();
  }

  @ReadOperation
  public String customDetailsEndpointByName(@Selector String name) {
    return CUSTOM_DETAILS_END_POINT;
  }
}
