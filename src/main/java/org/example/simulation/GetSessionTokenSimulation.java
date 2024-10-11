package org.example.simulation;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class GetSessionTokenSimulation extends Simulation {

  Integer user = 300;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Get Anonymous Session Token Simulation")
      .exec(getAnonymousSessionToken());

  {
    setUp(scn.injectOpen(
        incrementUsersPerSec(50)
            .times(3)  // Каждые 2-3 минуты добавляем 50 пользователей, 3 раза
            .eachLevelLasting(120),  // Каждое добавление длится 2 минуты
        constantUsersPerSec(user).during(120), // Держим нагрузку 2 минуты
        rampUsers(user).during(60), // Плавное увеличение нагрузки до максимума
        rampUsers(0).during(60)  // Плавное снижение нагрузки до нуля
    )).protocols(httpProtocol).maxDuration(600); // Общая максимальная длительность теста - 10 минут
  }

}
