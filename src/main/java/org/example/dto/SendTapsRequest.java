package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendTapsRequest {

  @JsonProperty("jsonrpc")
  private String jsonrpc;

  @JsonProperty("id")
  private int id;

  @JsonProperty("method")
  private String method;

  @JsonProperty("params")
  private List<SendTapsParams> params;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SendTapsParams {

    @JsonProperty("x")
    private int x;

    @JsonProperty("y")
    private int y;
  }

}
