package org.example.util;

import static org.example.util.Utils.getRandomNumber;

import java.util.List;
import org.example.dto.SendTapsRequest;

public class Main {

  public static void main(String[] args) {
    int randomNumber = getRandomNumber(1, 100000);
    int x = getRandomNumber(1, 1000);
    int y = getRandomNumber(1, 1000);
    SendTapsRequest sendTapsRequest = new SendTapsRequest(
        "2.0", randomNumber, "sendTaps", List.of(new SendTapsRequest.SendTapsParams(x, y))
    );

    String jsonString = JsonUtil.toJson(sendTapsRequest);
    System.out.println(jsonString);
  }
}
