package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.nothingFor;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;
import static org.example.util.WsLoadHelper.closeWebSocket;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetUserRequest;
import static org.example.util.WsLoadHelper.sendSendTapsRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

public class NewUserTap extends Simulation {

  Integer user = 3000;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full registration and game scenario")
      .pause(3)
      .exec(getAnonymousSessionToken())
      .pause(1)
      .exec(connectWebSocket())
      .pause(3)
      .exec(sendGetUserRequest())
      .repeat(2000).on( // Выполняем 2000 повторений
          exec(sendSendTapsRequest())
              .pause(Duration.ofMillis(200)) // Задержка между повторениями 200 мсек
      )
      .pause(3)
      .exec(sendGetUserRequest())
      .pause(2)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectOpen(
            nothingFor(Duration.ofSeconds(2)),
            rampUsers(user).during(200),
            constantUsersPerSec(1).during(200),
            rampUsers(0).during(150)
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(15));
  }

}