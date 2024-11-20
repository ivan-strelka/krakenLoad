package org.example.simulation.load;

import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;


public class GetTokenLoad extends Simulation {

  Integer maxUsers = 1500;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Полный сценарий регистрации и игры")
      // Случайная начальная пауза от 1 до 3 секунд
//      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(2, 3)))
      // Получение анонимного токена сессии
      .exec(getAnonymousSessionToken()
      );

  {
    setUp(
        scn.injectClosed(
            rampConcurrentUsers(1).to(maxUsers).during(Duration.ofMinutes(2)),
            constantConcurrentUsers(maxUsers).during(Duration.ofMinutes(3))
//            rampConcurrentUsers(maxUsers).to(0).during(Duration.ofMinutes(10))
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(5));

  }
}
