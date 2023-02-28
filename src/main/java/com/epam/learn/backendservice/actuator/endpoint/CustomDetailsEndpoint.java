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
    return "custom-details-end-point";
  }
}
