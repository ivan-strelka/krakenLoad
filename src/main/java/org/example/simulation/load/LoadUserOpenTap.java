package org.example.simulation.load;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.util.HttpLoadHelper.getAnonymousSessionToken;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetUserRequest;
import static org.example.util.WsLoadHelper.sendTapsRequest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;


public class LoadUserOpenTap extends Simulation {

  Integer maxUsers = 4000;

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Полный сценарий регистрации и игры")
      // Случайная начальная пауза от 1 до 5 секунд
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(1, 6)))
      // Получение анонимного токена сессии
      .exec(getAnonymousSessionToken())
      // Случайная пауза от 1 до 3 секунд
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(1, 4)))
      // Подключение к WebSocket
      .exec(connectWebSocket())
      // Случайная пауза от 2 до 5 секунд
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(2, 6)))
      // Отправка запроса getUser
      .exec(sendGetUserRequest())
      // Установка цикла с случайным количеством повторений
      .exec(session -> {
        int numberOfTaps = ThreadLocalRandom.current().nextInt(100, 5000);
        return session.set("numberOfTaps", numberOfTaps);
      })
      .repeat("#{numberOfTaps}").on(
          exec(sendTapsRequest())
              // Случайная пауза между 100мс и 500мс
              .pause(Duration.ofMillis(ThreadLocalRandom.current().nextInt(100, 500)))
      )
      // Случайная пауза от 2 до 5 секунд
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(2, 6)))
      // Отправка запроса getUser
      .exec(sendGetUserRequest()
      );

  {
    setUp(
        scn.injectOpen(
            rampUsers(maxUsers).during(Duration.ofMinutes(2)),
            constantUsersPerSec(2).during(Duration.ofMinutes(3))   // Стабильная нагрузка
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(5));
  }

//  {
//    setUp(
//        scn.injectOpen(
//            nothingFor(Duration.ofSeconds(2)),
//            rampUsers(maxUsers).during(Duration.ofMinutes(2)),
//            constantUsersPerSec(5).during(Duration.ofMinutes(3)),
//            rampUsers(0).during(Duration.ofMinutes(2))
//        )
//    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(7));
//  }
//


}
