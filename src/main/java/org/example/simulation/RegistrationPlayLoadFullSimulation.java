package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;
import static org.example.util.WsLoadHelper.closeWebSocket;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetTopReferralsRequest;
import static org.example.util.WsLoadHelper.sendGetTopUsersRequest;
import static org.example.util.WsLoadHelper.sendTapsRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class RegistrationPlayLoadFullSimulation extends Simulation {

  Integer user = 10;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

//  ScenarioBuilder scn = scenario("Full registration and game scenario")
//      .pause(2)
//      .exec(getAnonymousSessionToken())
//      .pause(2)
//      .exec(connectWebSocket())
//      .pause(2)
//      .exec(sendTapsRequest())
//      .pause(2)
//      .exec(closeWebSocket()
//      );

  ScenarioBuilder scn = scenario("Full registration and game scenario")
      .pause(2)
      .exec(getAnonymousSessionToken())
      .pause(2)
      .exec(connectWebSocket())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendGetTopReferralsRequest())
      .pause(2)
      .exec(sendGetTopUsersRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(sendTapsRequest())
      .pause(2)
      .exec(closeWebSocket()
      );


  {
    setUp(
        scn.injectOpen(
            rampUsers(user).during(20), // Плавное увеличение до заданного количества пользователей
//            constantUsersPerSec(user).during(2), // Держим нагрузку 2.5 минуты
            rampUsers(0).during(25)              // Плавное снижение нагрузки
        )
    ).protocols(httpProtocol);
  }

}

//ScenarioBuilder scn = scenario("Full registration and game scenario")
//    .pause(2)
//    .exec(getAnonymousSessionToken())
//    .pause(2)
//    .exec(connectWebSocket())
//    .pause(2)
//    .exec(sendGetTopReferralsRequest())
//    .pause(2)
//    .exec(sendSubscribeRequest())
//    .pause(2)
//    .exec(sendGetTopUsersRequest())
//    .pause(2)
//    .exec(sendGetUserRequest())
//    .pause(2)
//    .exec(sendUpdateProfileRequest(USERNAME, WALLET))
//    .pause(2)
//    .exec(sendGetUsersAroundRequest())
//    .pause(2)
//    .exec(sendTapsRequest())
//    .pause(2)
//    .exec(sendUnsubscribeRequest())
//    .pause(2)
//    .exec(closeWebSocket()
//    );

