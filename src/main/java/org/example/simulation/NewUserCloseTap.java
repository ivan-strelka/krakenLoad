package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;
import static org.example.util.WsLoadHelper.closeWebSocket;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetUserRequest;
import static org.example.util.WsLoadHelper.sendTapsRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

public class NewUserCloseTap extends Simulation {

  Integer user = 3000;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full registration and game scenario")
      .pause(3)
      .exec(getAnonymousSessionToken())
      .pause(1)
      .exec(connectWebSocket())
      .pause(3)
      .exec(sendGetUserRequest())
      .repeat(2000).on( // Выполняем 2000 повторений
          exec(sendTapsRequest())
              .pause(Duration.ofMillis(200)) // Задержка между повторениями 200 мсек
      )
      .pause(3)
      .exec(sendGetUserRequest())
      .pause(2)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectClosed(
            rampConcurrentUsers(1).to(user).during(500),
            constantConcurrentUsers(user).during(300),
            rampConcurrentUsers(user).to(0).during(500)
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(18));

  }

}
