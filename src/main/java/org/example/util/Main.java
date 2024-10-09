package org.example.util;

import java.util.Collections;
import org.example.dto.GetUserRequestDTO;

public class Main {

  public static void main(String[] args) {

    GetUserRequestDTO request = new GetUserRequestDTO("2.0", 1, "getUser");
    String test = JsonUtil.toJson(Collections.singletonList(request));

    System.out.println(test);
  }
}
