package com.example.guest.exception;

import brave.Tracer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class GuessServiceConverter {
  private ObjectMapper mapper = new ObjectMapper();
  private static final String ERROR_CODE = "errorCode";
  private static final String ERROR_MESSAGE = "errorMessage";
  private static final String TRACE_ID = "traceId";

  public JsonNode toJsonNode(GuestServiceErrorResponse response, String extraError, Tracer tracer) {
    var objectNode = mapper.createObjectNode();
    objectNode.put(ERROR_CODE, response.getErrorCode());
    objectNode.put(ERROR_MESSAGE, response.getErrorMessage() + extraError);
    objectNode.put(TRACE_ID, tracer.currentSpan().context().traceIdString());
    return objectNode;
  }

}
