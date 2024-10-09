package org.example.util;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.regex;
import static io.gatling.javaapi.http.HttpDsl.ws;
import static org.example.util.Utils.getRandomNumber;

import io.gatling.javaapi.core.ChainBuilder;
import java.util.Collections;
import org.example.dto.GetTopUsersRequestDTO;
import org.example.dto.GetTopUsersRequestDTO.ParamsGetTop;
import org.example.dto.GetUserRequestDTO;
import org.example.dto.SendTapsParams;
import org.example.dto.SendTapsRequest;
import org.example.dto.UpdateProfileRequestDTO;
import org.example.dto.UpdateProfileRequestDTO.Params;

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

  public static ChainBuilder connectWebSocket(String jwtToken) {
    return exec(
        ws("Connect to WebSocket")
            .connect("wss://game.releasethekraken.io/backend/ws?jwt=" + jwtToken)
            .onConnected(exec(
                session -> session.set("connected", true)
            ))
    );
  }

  public static ChainBuilder closeWebSocket() {
    return exec(ws("Close WS").close());
  }

  public static ChainBuilder sendGetUserRequest() {
    int randomNumber = getRandomNumber(1, 1000000);
    GetUserRequestDTO request = new GetUserRequestDTO("2.0", randomNumber, "getUser");
    String jsonString = JsonUtil.toJson(request);

    return exec(
        ws("Send GetUser Request")
            .sendText(jsonString)
            .await(20).on(ws.checkTextMessage("check getUser")
                .check(jsonPath("$.id").exists())
                .check(jsonPath("$.result.points").exists())
                .check(jsonPath("$.result.userId").exists())
                .check(jsonPath("$.result.level").exists())
                .check(jsonPath("$.result.nickname").exists())

            )
    );
  }


  public static ChainBuilder sendSendTapsRequest() {
    int randomNumber = getRandomNumber(1, 100000);
    int x = getRandomNumber(1, 1000);
    int y = getRandomNumber(1, 1000);
    SendTapsParams params = new SendTapsParams(x, y);
    SendTapsRequest request = new SendTapsRequest("2.0", randomNumber, "sendTaps",
        Collections.singletonList(params));

    String jsonString = JsonUtil.toJson(request);

    return exec(
        ws("Send SendTaps Request")
            .sendText(jsonString)
            .await(20).on(ws.checkTextMessage("check id and userInfo SendTaps")
                .check(jsonPath("$.id").exists())
                .check(jsonPath("$.result.userInfo").exists())
            )

    );
  }

  public static ChainBuilder sendUpdateProfileRequest(String nickname, String wallet) {
    int id = getRandomNumber(1, 1000000);
    UpdateProfileRequestDTO request = new UpdateProfileRequestDTO("2.0", id, "updateProfile",
        new Params(nickname, wallet));

    var jsonString = JsonUtil.toJson(request);
    return exec(
        ws("Send UpdateProfile Request")
            .sendText(jsonString)
            .await(20).on(ws.checkTextMessage("check UpdateProfile")
                .check(jsonPath("$.id").exists())
                .check(jsonPath("$.result").exists())
                .check(jsonPath("$.result.points").exists())
                .check(jsonPath("$.result.id").exists()))
    );
  }

  public static ChainBuilder sendGetTopUsersRequest() {
    int id = getRandomNumber(1, 1000000);
    GetTopUsersRequestDTO request = new GetTopUsersRequestDTO("2.0", id, "getTopUsers",
        new ParamsGetTop(30));
    var jsonString = JsonUtil.toJson(request);
    return exec(
        ws("Send getTopUsers Request")
            .sendText(jsonString)
            .await(20).on(ws.checkTextMessage("check sendGetTopUsersRequest")
                .check(jsonPath("$.id").exists())
                .check(jsonPath("$.jsonrpc").exists())
                .check(jsonPath("$.result").exists())

            )
    );
  }

  public static ChainBuilder sendGetUsersAroundRequest(int limit) {
    int randomNumber = getRandomNumber(1, 1000000);

    return exec(
        ws("Send getUsersAround Request")
            .sendText(
                "{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getUsersAround\", \"params\": {\"limit\": "
                    + limit + "}}")
            .await(20).on(ws.checkTextMessage("check sendGetUsersAroundRequest").check(regex(".*")))
    );
  }

  public static ChainBuilder sendGetTopReferralsRequest(int limit) {
    int randomNumber = getRandomNumber(1, 1000000);

    return exec(
        ws("Send getTopReferrals Request")
            .sendText(
                "{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getTopReferrals\", \"params\": {\"limit\": "
                    + limit + "}}")
            .await(20)
            .on(ws.checkTextMessage("check sendGetTopReferralsRequest").check(regex(".*")))
    );
  }

  public static ChainBuilder sendSubscribeRequest() {
    int randomNumber = getRandomNumber(1, 1000000);

    return exec(
        ws("Send Subscribe Request")
            .sendText("{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"subscribe\"}")
            .await(20).on(ws.checkTextMessage("check sendSubscribeRequest").check(regex(".*")))
    );
  }

  public static ChainBuilder sendUnsubscribeRequest() {
    int randomNumber = getRandomNumber(1, 1000000);

    return exec(
        ws("Send unsubscribe Request")
            .sendText("{\"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"unsubscribe\"}")
            .await(20).on(ws.checkTextMessage("check sendUnsubscribeRequest").check(regex(".*")))
    );
  }
}