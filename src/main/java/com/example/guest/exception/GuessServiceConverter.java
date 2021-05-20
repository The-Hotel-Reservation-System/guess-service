package com.example.guest.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class GuessServiceConverter {
  private final String ERROR_CODE = "errorCode";
  private final String ERROR_MESSAGE = "errorMessage";
  private ObjectMapper mapper = new ObjectMapper();

  public JsonNode toJsonNode(GuestServiceErrorResponse response, String extraError) {
    ObjectNode objectNode = mapper.createObjectNode();
    objectNode.put(ERROR_CODE, response.getErrorCode());
    objectNode.put(ERROR_MESSAGE, response.getErrorMessage() + extraError);
    return objectNode;
  }

}
