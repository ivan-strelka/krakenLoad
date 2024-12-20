package org.example.util;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.regex;
import static io.gatling.javaapi.http.HttpDsl.ws;
import static org.example.constants.Constants.BASE_URL_WS;
import static org.example.util.Utils.getRandomNumber;

import io.gatling.javaapi.core.ChainBuilder;
import java.util.List;
import org.example.dto.GetTopUsersRequestDTO;
import org.example.dto.GetTopUsersRequestDTO.ParamsGetTop;
import org.example.dto.GetUserRequestDTO;
import org.example.dto.GetUsersAroundRequest;
import org.example.dto.GetUsersAroundRequest.ParamsGetUsers;
import org.example.dto.SendTapsRequest;
import org.example.dto.SubscribeRequest;
import org.example.dto.UpdateProfileRequestDTO;
import org.example.dto.UpdateProfileRequestDTO.Params;

public class WsLoadHelper {

  private final static String url = BASE_URL_WS + "/backend/ws?jwt=#{jwtToken}";

  public static ChainBuilder connectWebSocket() {
    return exec(
        ws("Connect to WebSocket")
            .connect(url)
            .onConnected(exec(
                session -> session.set("connected", true)
            ))
    );
  }

  public static ChainBuilder connectWebSocket(String jwtToken) {
    return exec(
        ws("Connect to WebSocket")
            .connect(url)
            .onConnected(exec(
                session -> session.set("connected", true)
            ))
    );
  }

  public static ChainBuilder closeWebSocket() {
    return exec(ws("Close WS").close());
  }

  public static ChainBuilder sendGetUserRequest() {

    GetUserRequestDTO request = new GetUserRequestDTO("2.0", 1, "getUser");
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

  public static ChainBuilder sendTapsRequest() {
    int x = getRandomNumber(1, 1000);
    int y = getRandomNumber(1, 1000);
    SendTapsRequest sendTapsRequest = new SendTapsRequest(
        "2.0", 1, "sendTaps", List.of(new SendTapsRequest.SendTapsParams(x, y))
    );

    String jsonString = JsonUtil.toJson(sendTapsRequest);

    return exec(
        ws("Send SendTaps Request")
            .sendText(jsonString)
            .await(20).on(ws.checkTextMessage("Check SendTaps")
                .check(jsonPath("$.id").exists())
                .check(jsonPath("$.jsonrpc").exists())
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
                .check(jsonPath("$.jsonrpc").exists())
                .check(jsonPath("$.error").exists())
            )
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

  public static ChainBuilder sendGetUsersAroundRequest() {
    int limit = getRandomNumber(1, 20);
    GetUsersAroundRequest request = new GetUsersAroundRequest("2.0", 1, "getUsersAround",
        new ParamsGetUsers(limit));
    String json = JsonUtil.toJson(request);
    return exec(
        ws("Send getUsersAround Request")
            .sendText(json)
            .await(20).on(ws.checkTextMessage("check sendGetUsersAroundRequest")
                .check(jsonPath("$.id").exists())
                .check(jsonPath("$.jsonrpc").exists())
                .check(jsonPath("$.result").exists())
            )
    );
  }

  public static ChainBuilder sendGetTopReferralsRequest() {
    int limit = getRandomNumber(1, 20);
    GetUsersAroundRequest request = new GetUsersAroundRequest("2.0", 1, "getTopReferrals",
        new ParamsGetUsers(limit));

    String json = JsonUtil.toJson(request);
    return exec(
        ws("Send getTopReferrals Request")
            .sendText(json)
            .await(20)
            .on(ws.checkTextMessage("check sendGetTopReferralsRequest")
                .check(regex(".*")).check(jsonPath("$.id").exists())
                .check(jsonPath("$.jsonrpc").exists())
                .check(jsonPath("$.result").exists())
            )
    );
  }

  public static ChainBuilder sendSubscribeRequest() {
    SubscribeRequest subscribeRequest = new SubscribeRequest("2.0", 1, "subscribe");
    String json = JsonUtil.toJson(subscribeRequest);
    return exec(
        ws("Send Subscribe Request")
            .sendText(json)
            .await(20).on(ws.checkTextMessage("check sendSubscribeRequest")
                .check(jsonPath("$.jsonrpc").exists())
            )
    );
  }

  public static ChainBuilder sendUnsubscribeRequest() {
    SubscribeRequest subscribeRequest = new SubscribeRequest("2.0", 1, "unsubscribe");
    String json = JsonUtil.toJson(subscribeRequest);

    return exec(
        ws("Send unsubscribe Request")
            .sendText(json)
            .await(20).on(ws.checkTextMessage("check sendUnsubscribeRequest")
                .check(jsonPath("$.id").exists())
                .check(jsonPath("$.jsonrpc").exists())
                .check(jsonPath("$.result").exists())
            )
    );
  }
}