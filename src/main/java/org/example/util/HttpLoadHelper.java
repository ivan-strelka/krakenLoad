package org.example.util;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ChainBuilder;

public class HttpLoadHelper {

  public static ChainBuilder getAnonymousSessionToken() {
    return exec(
        http("Get Anonymous Session Token")
            .post("/backend/api/anonymous_session")
            .header("Content-Type", "application/json")
            .body(StringBody("{}"))
            .check(status().is(200))
            .check(jsonPath("$.jwt").saveAs("jwtToken"))

    );
  }

  public static ChainBuilder getTelegramSessionToken(String initData) {
    return exec(
        http("Get Telegram Session Token")
            .post("/backend/api/telegram_session")
            .header("Content-Type", "application/json")
            .body(StringBody("{}"))
            .check(status().is(200))
            .check(jsonPath("$.jwt").saveAs("jwtToken"))
    );
  }


}