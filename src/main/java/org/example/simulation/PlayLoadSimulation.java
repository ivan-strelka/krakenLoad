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

  Integer user = 20;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full game scenario")
      .exec(getAnonymousSessionToken())
      .pause(10)
      .exec(connectWebSocket())
      .pause(10)
      .exec(sendSendTapsRequest(5, 10))
      .pause(10)
      .exec(sendGetTopReferralsRequest(10))
      .pause(10)
      .exec(sendSubscribeRequest())
      .pause(10)
      .exec(sendGetTopUsersRequest(10))
      .pause(10)
      .exec(sendGetUserRequest())
      .pause(10)
      .exec(sendUpdateProfileRequest("Test", "1123"))
      .pause(10)
      .exec(sendGetUsersAroundRequest(10))
      .pause(10)
      .exec(closeWebSocket()
      );

  {
    setUp(
        scn.injectOpen(
            rampUsers(user).during(20),
            atOnceUsers(user),
            constantUsersPerSec(user).during(150),
            rampUsers(0).during(20)
        )
    ).protocols(httpProtocol);
  }
}