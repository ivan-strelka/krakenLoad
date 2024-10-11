package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeRequest {

  @JsonProperty("jsonrpc")
  private String jsonrpc;

  @JsonProperty("id")
  private int id;

  @JsonProperty("method")
  private String method;
}
