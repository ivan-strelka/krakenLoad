package org.example.simulation;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static org.example.util.HttpLoadHelper.*;

public class GetSessionTokenSimulation extends Simulation {

  HttpProtocolBuilder httpProtocol = http
      .baseUrl("https://game.releasethekraken.io")
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Get Anonymous Session Token Simulation")
      .exec(getAnonymousSessionToken());

  {
//    setUp(scn.injectOpen(rampUsers(500).during(20),atOnceUsers(500), constantUsersPerSec(500).during(300))).protocols(httpProtocol);
    setUp(scn.injectOpen(
        rampUsers(500).during(20),
        constantUsersPerSec(500).during(200),
        rampUsers(0).during(30))
    ).protocols(httpProtocol);
  }

}
