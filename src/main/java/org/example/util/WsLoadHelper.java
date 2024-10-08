package org.example.util;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.regex;
import static io.gatling.javaapi.http.HttpDsl.ws;

import io.gatling.javaapi.core.ChainBuilder;

public class WsLoadHelper {

  public static ChainBuilder connectWebSocket() {
    return exec(
        ws("Connect to WebSocket")
            .connect("wss://game.releasethekraken.io/backend/ws?jwt=#{jwtToken}")
            .onConnected(exec(
                session -> session.set("connected", true)
            ))
    );
  }

  public static ChainBuilder closeWebSocket() {
    return exec(ws("Close WS").close());
  }

  public static ChainBuilder sendGetUserRequest() {
    return exec(
        ws("Send GetUser Request")
            .sendText("{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getUser\"}")
            .await(30).on(ws.checkTextMessage("check getUser").check(regex(".*")))
    );
  }


  // Методы отправки текста через WebSocket с различными вариантами
  public static ChainBuilder sendSendTapsRequest(int x, int y) {
    return exec(
        ws("Send SendTaps Request")
            .sendText(
                "{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"sendTaps\", \"params\": [{\"x\": "
                    + x + ", \"y\": " + y + "}]}")
            .await(60).on(ws.checkTextMessage("check SendTaps").check(regex(".*")))
    );
  }

  public static ChainBuilder sendUpdateProfileRequest(String nickname, String wallet) {
    return exec(
        ws("Send UpdateProfile Request")
            .sendText(
                "{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"updateProfile\", \"params\": {\"nickName\": \""
                    + nickname + "\", \"wallet\": \"" + wallet + "\"}}")
            .await(30).on(ws.checkTextMessage("check SendTaps").check(regex(".*")))
    );
  }

  public static ChainBuilder sendGetTopUsersRequest(int limit) {
    return exec(
        ws("Send GetTopUsers Request")
            .sendText(
                "{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getTopUsers\", \"params\": {\"limit\": "
                    + limit + "}}")
            .await(30).on(ws.checkTextMessage("check SendTaps").check(regex(".*")))
    );
  }

  public static ChainBuilder sendGetUsersAroundRequest(int limit) {
    return exec(
        ws("Send GetUsersAround Request")
            .sendText(
                "{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getUsersAround\", \"params\": {\"limit\": "
                    + limit + "}}")
            .await(30).on(ws.checkTextMessage("check SendTaps").check(regex(".*")))
    );
  }

  public static ChainBuilder sendGetTopReferralsRequest(int limit) {
    return exec(
        ws("Send GetTopReferrals Request")
            .sendText(
                "{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getTopReferrals\", \"params\": {\"limit\": "
                    + limit + "}}")
            .await(30).on(ws.checkTextMessage("check SendTaps").check(regex(".*")))
    );
  }

  public static ChainBuilder sendSubscribeRequest() {
    return exec(
        ws("Send Subscribe Request")
            .sendText("{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"subscribe\"}")
            .await(30).on(ws.checkTextMessage("check SendTaps").check(regex(".*")))
    );
  }

  public static ChainBuilder sendUnsubscribeRequest() {
    return exec(
        ws("Send Unsubscribe Request")
            .sendText("{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"unsubscribe\"}")
            .await(30).on(ws.checkTextMessage("check SendTaps").check(regex(".*")))
    );
  }

//  // Примеры отправки текста с различными стратегиями
//  public static ChainBuilder sendTextWithELString() {
//    return exec(ws("Message")
//        .sendText("{\"text\": \"Hello, I'm #{id} and this is message #{i}!\"}"));
//  }
//
//  public static ChainBuilder sendTextWithFunction() {
//    return exec(ws("Message")
//        .sendText(session -> "{\"text\": \"Hello, I'm " + session.getString("id")
//            + " and this is message " + session.getString("i") + "!\"}"));
//  }
//
//  public static ChainBuilder sendTextWithElFileBody() {
//    return exec(ws("Message")
//        .sendText(ElFileBody("filePath")));
//  }
//
//  public static ChainBuilder sendTextWithPebbleStringBody() {
//    return exec(ws("Message")
//        .sendText(PebbleStringBody("somePebbleTemplate")));
//  }
//
//  public static ChainBuilder sendTextWithPebbleFileBody() {
//    return exec(ws("Message")
//        .sendText(PebbleFileBody("filePath")));
//  }

}