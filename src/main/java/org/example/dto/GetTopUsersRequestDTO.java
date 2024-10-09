package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTopUsersRequestDTO {

  @JsonProperty("jsonrpc")
  private String jsonrpc;

  @JsonProperty("id")
  private int id;

  @JsonProperty("method")
  private String method;

  @JsonProperty("params")
  private ParamsGetTop params;

  public static void main(String[] args) {
    // Пример использования DTO
    GetTopUsersRequestDTO request = new GetTopUsersRequestDTO("2.0", 1, "getTopUsers",
        new ParamsGetTop(10));
    System.out.println(request);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ParamsGetTop {

    @JsonProperty("limit")
    private int limit;
  }
}