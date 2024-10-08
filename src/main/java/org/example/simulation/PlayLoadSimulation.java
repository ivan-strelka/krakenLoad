package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
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

public class PlayLoadSimulation extends Simulation {

  Integer user = 100;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");


  ScenarioBuilder scn = scenario("WebSocket")
      .exec(getAnonymousSessionToken())
       .pause(2)
      .exec(connectWebSocket())
       .pause(2)
      .exec(sendSendTapsRequest(5, 10))
       .pause(2)
      .exec(sendGetTopReferralsRequest(10))
       .pause(2)
      .exec(sendSubscribeRequest())
       .pause(2)
      .exec(sendGetTopUsersRequest(10))
       .pause(2)
      .exec(sendGetUserRequest())
       .pause(2)
      .exec(sendUpdateProfileRequest("sdsf", "1123"))
       .pause(2)
      .exec(sendGetUsersAroundRequest(10))
       .pause(2)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectOpen(
            rampUsers(user).during(10),
            atOnceUsers(user),
            constantUsersPerSec(user).during(60),
            rampUsers(0).during(10)
        )
    ).protocols(httpProtocol);
  }
}