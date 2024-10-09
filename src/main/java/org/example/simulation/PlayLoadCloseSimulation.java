package org.example.simulation;


import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.util.TokenManager.getRandomToken;
import static org.example.util.WsLoadHelper.closeWebSocket;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetUserRequest;
import static org.example.util.WsLoadHelper.sendSendTapsRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

public class PlayLoadCloseSimulation extends Simulation {

  private final static Integer user = 3000;
  private final static String wallet = "0x9bd8b7b527ca4e6738cbdabdf51c22466756073d";

  private final static String jwtToken = "${token}";

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full only game scenario")
      .exec(session -> {
        String token = getRandomToken();
        return session.set("token", token);
      })
      .exec(connectWebSocket(jwtToken))
      .pause(1)
      .exec(sendGetUserRequest())
      .pause(3)
      .repeat(2000).on( // Выполняем 2000 повторений
          exec(sendSendTapsRequest())
              .pause(Duration.ofMillis(200)) // Задержка между повторениями 200 мсек
      )
      .pause(5)
      .exec(closeWebSocket());

  {
    setUp(
        scn.injectClosed(
            rampConcurrentUsers(1).to(user).during(250),
            constantConcurrentUsers(user).during(250)
        )
    );

  }

}
