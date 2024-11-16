package org.example.simulation.stress;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.core.CoreDsl.stressPeakUsers;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
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


public class StressUserCloseTap extends Simulation {

  Integer maxUsers = 1000;

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
        int numberOfTaps = ThreadLocalRandom.current().nextInt(100, 200);
        return session.set("numberOfTaps", numberOfTaps);
      })
      .repeat("#{numberOfTaps}").on(
          exec(sendTapsRequest())
              // Случайная пауза между 100мс и 500мс
              .pause(Duration.ofMillis(ThreadLocalRandom.current().nextInt(100, 501)))
      )
      // Случайная пауза от 2 до 5 секунд
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(2, 6)))
      // Отправка запроса getUser
      .exec(sendGetUserRequest()
      );

  {
    setUp(
        scn.injectOpen(
            // Резкое увеличение пользователей до максимального числа за 1 минуту
            rampUsers(maxUsers).during(Duration.ofMinutes(1)),

            // Резкий всплеск нагрузки: сразу 50% от maxUsers
            atOnceUsers(maxUsers / 2),

            // Постоянная высокая нагрузка в течение 3 минут
            constantUsersPerSec(maxUsers / 3).during(Duration.ofMinutes(3)),

            // Постепенное увеличение нагрузки от 50% до 100% за 2 минуты
            rampUsersPerSec(maxUsers / 2).to(maxUsers).during(Duration.ofMinutes(2)),

            // Пиковая нагрузка: 150% от maxUsers в течение 1 минуты
            stressPeakUsers(maxUsers + (maxUsers / 2)).during(Duration.ofMinutes(1)),

            // Постепенное снижение нагрузки от 100% до 0% за 1 минуту
            rampUsers(0).during(Duration.ofMinutes(1))
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(10));
  }

}
