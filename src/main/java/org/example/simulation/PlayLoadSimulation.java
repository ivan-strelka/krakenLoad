package org.example.simulation;


import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.nothingFor;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
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

public class PlayLoadSimulation extends Simulation {

  private final static Integer user = 5000;
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
      .repeat(500).on( // Выполняем 2000 повторений
          exec(sendSendTapsRequest())
              .pause(Duration.ofMillis(200)) // Задержка между повторениями 200 мсек
      )
      .pause(5)
      .exec(closeWebSocket());


  {
    setUp(
        scn.injectOpen(
            nothingFor(Duration.ofSeconds(2)),
            rampUsers(user).during(150),
            rampUsers(0).during(150)
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(15));
  }

//  {
//    setUp(
//        scn.injectClosed(
//            rampConcurrentUsers(1).to(user).during(50),
//            constantConcurrentUsers(user).during(150)
//        )
//    );
//
//  }

//  ScenarioBuilder scn = scenario("Full only game scenario")
//      .exec(session -> {
//        String token = getRandomToken();
//        return session.set("token", token);
//      })
//      .pause(5)
//      .exec(connectWebSocket(jwtToken))
//      .pause(5)
//      .exec(sendGetTopReferralsRequest(10))
//      .pause(5)
//      .exec(sendSubscribeRequest())
//      .pause(5)
//      .exec(sendGetTopUsersRequest(10))
//      .pause(5)
//      .exec(sendGetUserRequest())
//      .pause(5)
//      .exec(sendUpdateProfileRequest("unknow kraken", wallet))
//      .pause(5)
//      .exec(sendGetUsersAroundRequest(10))
//      .pause(5)
//      .repeat(400).on( // Выполняем 400 повторений
//          exec(sendSendTapsRequest(5, 10))
//              .pause(Duration.ofMillis(200)) // Задержка между повторениями 200 мсек
//      )
//      .pause(5)
//      .exec(closeWebSocket());

//  {
//    setUp(
//        scn.injectOpen(
//            nothingFor(Duration.ofSeconds(5)),
//            // Плавный рост пользователей до 100 за 2 минуты (120 секунд)
//            rampUsers(user).during(100),
//            constantUsersPerSec(1).during(300),
//            // Плавное снижение числа по
//            // льзователей с 100 до 0 за 2 минуты (120 секунд)
//            rampUsers(0).during(100)
//        )
//    ).protocols(httpProtocol).maxDuration(600);
//  }
}
