package org.example.simulation.load;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;


public class GetTokenLoadOpen extends Simulation {

  Integer maxUsers = 3000;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Полный сценарий регистрации и игры")
      // Случайная начальная пауза от 1 до 3 секунд
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(1, 3)))
      // Получение анонимного токена сессии
      .exec(getAnonymousSessionToken()
      );

  {
    setUp(
        scn.injectOpen(
            rampUsers(maxUsers).during(Duration.ofMinutes(30))
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(30));

  }
}
