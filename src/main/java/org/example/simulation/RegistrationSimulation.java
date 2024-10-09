package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.nothingFor;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
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

public class RegistrationSimulation extends Simulation {

  Integer user = 5000;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full registration open scenario")
      .pause(1)
      .exec(getAnonymousSessionToken())
      .pause(1)
      .exec(connectWebSocket())
      .pause(5)
      .exec(sendGetUserRequest())
      .pause(3)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectOpen(
            nothingFor(Duration.ofSeconds(2)),
            rampUsers(user).during(150),
//            constantUsersPerSec(1).during(200),
            rampUsers(0).during(150)
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(5));
  }

}