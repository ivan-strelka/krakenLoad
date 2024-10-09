package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;
import static org.example.util.WsLoadHelper.closeWebSocket;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetUserRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

public class RegistrationCloseSimulation extends Simulation {

  Integer user = 4000;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full registration close scenario")
      .pause(1)
      .exec(getAnonymousSessionToken())
      .pause(1)
      .exec(connectWebSocket())
      .pause(3)
      .exec(sendGetUserRequest())
      .pause(3)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectClosed(
            rampConcurrentUsers(1).to(user).during(100),
            constantConcurrentUsers(user).during(200),
            rampConcurrentUsers(user).to(0).during(100)
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(7));

  }

}