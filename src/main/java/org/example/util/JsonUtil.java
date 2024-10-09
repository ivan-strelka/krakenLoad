package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

  // Создаем единственный экземпляр ObjectMapper для повторного использования
  private static final ObjectMapper objectMapper = new ObjectMapper();

  // Метод для преобразования объекта в JSON строку
  public static String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null; // Можно заменить на выбрасывание RuntimeException, если требуется обработка ошибки
    }
  }

  // Метод для преобразования JSON строки в объект указанного типа
  public static <T> T fromJson(String jsonString, Class<T> clazz) {
    try {
      return objectMapper.readValue(jsonString, clazz);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null; // Можно заменить на выбрасывание RuntimeException, если требуется обработка ошибки
    }
  }
}