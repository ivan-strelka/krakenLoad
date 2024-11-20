package org.example.util;

import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateTokensRestAssured {

  private static final String TOKEN_URL = "https://185.131.54.233/backend/api/anonymous_session";
  private static final String OUTPUT_CSV = "src/main/resources/data.csv"; //
  private static final int TOTAL_REQUESTS = 30000; // Количество запросов

  public static void main(String[] args) {
    useRelaxedHTTPSValidation();
    RestAssured.baseURI = TOKEN_URL;

    try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(OUTPUT_CSV))
        .withQuoteChar(ICSVWriter.NO_QUOTE_CHARACTER) // Убираем кавычки
        .build()) {

      // Добавляем заголовок в CSV
      writer.writeNext(new String[]{"token"});

      for (int i = 1; i <= TOTAL_REQUESTS; i++) {
        try {
          // Выполняем POST-запрос с RestAssured
          Response response = RestAssured
              .given()
              .contentType("application/json")
              .body("{}") // Пустое тело
              .post();

          // Проверяем, что запрос успешен
          if (response.statusCode() == 200) {
            // Извлекаем токен из JSON-ответа
            String token = response.jsonPath().getString("jwt");

            // Записываем токен в CSV
            writer.writeNext(new String[]{token});

            // Логируем успешный запрос
            System.out.println("Запрос " + i + " выполнен. Токен: " + token);
          } else {
            // Логируем ошибки HTTP
            System.err.println("Ошибка HTTP: " + response.statusCode() + " для запроса " + i);
          }
        } catch (Exception e) {
          // Логируем исключения
          System.err.println("Ошибка при запросе " + i + ": " + e.getMessage());
        }
      }

      System.out.println("Генерация токенов завершена. Результат сохранен в " + OUTPUT_CSV);

    } catch (IOException e) {
      System.err.println("Ошибка при записи файла: " + e.getMessage());
    }
  }
}