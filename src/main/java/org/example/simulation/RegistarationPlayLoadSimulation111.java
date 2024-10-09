package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;
import static org.example.util.WsLoadHelper.closeWebSocket;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetTopReferralsRequest;
import static org.example.util.WsLoadHelper.sendGetTopUsersRequest;
import static org.example.util.WsLoadHelper.sendGetUserRequest;
import static org.example.util.WsLoadHelper.sendGetUsersAroundRequest;
import static org.example.util.WsLoadHelper.sendSendTapsRequest;
import static org.example.util.WsLoadHelper.sendSubscribeRequest;
import static org.example.util.WsLoadHelper.sendUpdateProfileRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class RegistarationPlayLoadSimulation111 extends Simulation {

  Integer user = 150;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full registration and game scenario")
      .pause(2)
      .exec(getAnonymousSessionToken())
      .pause(5)
      .exec(connectWebSocket())
      .pause(5)
      .exec(sendGetTopReferralsRequest(10))
      .pause(5)
      .exec(sendSubscribeRequest())
      .pause(5)
      .exec(sendGetTopUsersRequest())
      .pause(5)
      .exec(sendGetUserRequest())
      .pause(5)
      .exec(
          sendUpdateProfileRequest("unknown kraken", "0x9bd8b7b527ca4e6738cbdabdf51c22466756073d"))
      .pause(5)
      .exec(sendGetUsersAroundRequest(10))
      .pause(5)
      .exec(sendSendTapsRequest())
      .pause(5)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectOpen(
            rampUsers(user).during(20), // Плавное увеличение до заданного количества пользователей
            constantUsersPerSec(user).during(200), // Держим нагрузку 2.5 минуты
            rampUsers(0).during(20)              // Плавное снижение нагрузки
        )
    ).protocols(httpProtocol);
  }

}