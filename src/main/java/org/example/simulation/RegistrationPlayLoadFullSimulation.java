package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.constants.Constants.USERNAME;
import static org.example.constants.Constants.WALLET;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;
import static org.example.util.WsLoadHelper.closeWebSocket;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetTopReferralsRequest;
import static org.example.util.WsLoadHelper.sendGetTopUsersRequest;
import static org.example.util.WsLoadHelper.sendGetUserRequest;
import static org.example.util.WsLoadHelper.sendGetUsersAroundRequest;
import static org.example.util.WsLoadHelper.sendSendTapsRequest;
import static org.example.util.WsLoadHelper.sendSubscribeRequest;
import static org.example.util.WsLoadHelper.sendUnsubscribeRequest;
import static org.example.util.WsLoadHelper.sendUpdateProfileRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class RegistrationPlayLoadFullSimulation extends Simulation {

  Integer user = 10;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full registration and game scenario")
      .pause(2)
      .exec(getAnonymousSessionToken())
      .pause(5)
      .exec(connectWebSocket())
      .pause(5)
      .exec(sendGetTopReferralsRequest())
      .pause(5)
      .exec(sendSubscribeRequest())
      .pause(5)
      .exec(sendGetTopUsersRequest())
      .pause(5)
      .exec(sendGetUserRequest())
      .pause(5)
      .exec(sendUpdateProfileRequest(USERNAME, WALLET))
      .pause(5)
      .exec(sendGetUsersAroundRequest())
      .pause(5)
      .exec(sendSendTapsRequest())
      .pause(5)
      .exec(sendUnsubscribeRequest())
      .pause(5)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectOpen(
            rampUsers(user).during(10), // Плавное увеличение до заданного количества пользователей
            constantUsersPerSec(user).during(2), // Держим нагрузку 2.5 минуты
            rampUsers(0).during(10)              // Плавное снижение нагрузки
        )
    ).protocols(httpProtocol);
  }

}