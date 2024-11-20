package org.example.simulation.load;


import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static org.example.constants.Constants.BASE_URL;
import static org.example.util.WsLoadHelper.connectWebSocket;
import static org.example.util.WsLoadHelper.sendGetUserRequest;
import static org.example.util.WsLoadHelper.sendTapsRequest;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import org.example.simulation.feeder.CsvFeeder;

public class PlayLoadCloseFeed extends Simulation {

  private final static Integer user = 5000;
  // Получаем фидер из провайдера
  FeederBuilder.FileBased<String> csvFeeder = CsvFeeder.getCsvFeeder("data.csv");

  HttpProtocolBuilder httpProtocol = http
      .baseUrl(BASE_URL)
      .acceptHeader("application/json");

  ScenarioBuilder scn = scenario("Full only game scenario")
      .feed(csvFeeder)
      .exec(session -> {
        String token = session.getString("token");
        return session.set("jwtToken", token);
      })
      .exec(connectWebSocket("#{jwtToken}"))
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(1, 2)))
      .exec(sendGetUserRequest())
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(1, 2)))
      .repeat(2000).on(
          exec(sendTapsRequest())
              .pause(Duration.ofMillis(1))
      )
      .pause(Duration.ofSeconds(ThreadLocalRandom.current().nextInt(1, 2)));

  {
    setUp(
        scn.injectClosed(
            rampConcurrentUsers(1).to(user).during(Duration.ofMinutes(2)),
            constantConcurrentUsers(user).during(Duration.ofMinutes(3))
        )
    ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(5));
  }
}
