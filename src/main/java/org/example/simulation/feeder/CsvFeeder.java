package org.example.simulation.feeder;


import static io.gatling.javaapi.core.CoreDsl.csv;

import io.gatling.javaapi.core.FeederBuilder.Batchable;

public class CsvFeeder {

  // Метод для получения CSV-фидера
  public static Batchable<String> getCsvFeeder(String fileName) {
    return csv(fileName).circular(); // Circular для повторного использования данных
  }
}
