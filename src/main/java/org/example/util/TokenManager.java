package org.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TokenManager {

  // Список токенов
  private static final List<String> tokens = new ArrayList<>();

  static {
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjMwMDcyMmU4LThkOTItNGFmMC1iZWI3LTRhNzM2OGIyYjU3ZiJ9.e4owC-Ji7My3zsQ04lm9hkoP7nZk6rIe_ivo3MsYDm8");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImI5YzA2ZmEzLWI5MGItNDVkYS1hZDFhLTMyZGMyMWNmY2Y2YyJ9.pHRBNUxT9VuNHtnKsE9wXh8HENlQM1aRfWHd_s5zfy0");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlhZjBjMzUzLTBjNDEtNDU2Yy1iY2U5LTk3MGRjZDA4ZTAxYSJ9.G03QTxwaZ5tvQ193ma44Azqt2oTiVYjHWKlfEBinnPU");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImI3M2U2YjNjLTg1NzAtNDI3MS1iNzRiLTIwMmQ5MGZlOWE2MyJ9.GEi8ylDRnlYDWRpREOmz_jPMgYviRxWDCg1b90t3Adc");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjgzNGY2Yzk1LTQ0MDItNDBmZC05NWFkLTgyOTY0ZTkzOTNhNyJ9.9f8FaNR5Un_On-gPivi2rutNl251t5Gvu6_zzVnsEqg");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjQyMDY3MzM1LTk1NzUtNDUwNC1iZTc4LTdkYzVkMjc4MDcxMyJ9.XDfn2i-b43BrntnbWRbYjAiIM9F9iTofhQKEiZurBHc");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjRjYTg3NDgxLWUzOWEtNGY5Zi04ODI1LWIwYTAzMGRhN2YyYyJ9.8tdK7a-zZSG-Xt6R3mpjwj5bWSvnZcDNyJuwDL0f4qE");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjQ1OTNkY2UyLWQ4YTYtNDkwNy05ZWM3LTNmYjhiMWY5MTFhNSJ9.0zGt2S_6XOmFMe8T1HSa8cU0R9-SLHsTDUpGgw1a0-Y");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjA4ZTQ2NDgxLTE2NTUtNDBmMC05NTkwLTIwNmM3YjcwZmQyYyJ9.eTtDPKgl6RRlbe8P7Wm1yvw9gSZThdcb64jwIwnugjk");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjliOWQ1MzM0LTNiNDktNDYxOS1hYTJkLTJiNDYxNjgyOGNlMiJ9.nHdYhsd5CT_FHK8mjeJpens7gltKQZ3KioO_chCuiNw");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjY0OGFjNGMwLTM0NmEtNDdkNy04NWUzLWNkYmY4MzgzMzMyYyJ9.rHnyVo5M67GzLmdn03lkrtfS9j_NC3AZS_Se9OQpzzM");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjRmNWVmOTkwLWNmNzctNGM4OC04MmJiLWI2YWVlYWY5NmI0MiJ9.gl0lbElGCsrS5glYHhyVCAU4Axh1i0lGOFkS6756iTs");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjYzNTAxMDFlLThiYTYtNGRiNS04ZDA1LTY5Njk5MzI2ZjFmNiJ9.HmoleCqUQ1XxYHrPWFS6s-A5zg2NoHrf7Uk05Bzhw4s");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjBhZWFlY2NhLTVjNjctNGQ4Mi04ZTEyLWYwMzhmYzBlYjRmNyJ9.1tGqPRfzRWw5v4UHiB-CNNlcUKeR2EX38pwhh-sfzkU");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImZjNzBiOWU1LTI2MTMtNDM4ZS04MWNiLTc1ZWYxYjI3OGVlMyJ9.cWF2uSppJeSRlHSf5pQNRDjcDvhNh6ejVxlZkq4sZiY");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImM3ODFiODUyLWVmZjItNDVjZi04ODk0LTM4Y2NhNTM4NDU1NyJ9.XqJXSj55ygT5pTWarQFvQMcIUshZSJAGt1d5A88cRJE");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjIzN2RkMTMxLTVmMjMtNGZjNS05OWEwLTczZDMzN2Y1NDZkYyJ9.oCJ9QRvsumm9wnEB-5fCra48OApiLvMuEC1uvuh0vMo");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjdmMjc0YjQ0LWQ0ZjMtNGQ0NC05YWY4LWVmYzk3MDY4NmEzYyJ9.sYZlS_FLHpApe7g0dB9EFku1CrMHtwaaQ9gYYfgw0u4");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6Ijc0YjcwYzAwLWIzYTQtNGQ1Zi05NWRkLWY3NTkyNWQxYjVhOSJ9.LkMoUEYocL7hCTXst02aatZiXFSJUHXClowDzfar54g");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImFmYTdkNDZlLWU5NDUtNGViMC05ODI2LWY2NmE5OGY0OTllOSJ9.UdmYXpmGg8SZ344_rWr7us_mHJRpNvdQiQSjIbjrVZY");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjU2ZGYzMmQyLTU4MDctNDFlMC1hMmZhLTBkNTgzNjJmNGJiYSJ9.4Dnm6ll3zeYy01DrVZMX5lbgjM6QXa3ZkcGZDZ_tnyM");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjA0MDUxNGJjLWViMmItNDk5Yy04MmRjLTY1ZDIxODhhYzk2NyJ9.OoZsRwaNSdl4RNt8CN9-u9kG1pQJKc80wyYNC1lVAfI");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImU2ZWVhZmZlLTMxYmItNDE1Zi1iMDlmLWVhZTdhMTNmZTJhNiJ9.KTxCNp4YvkEOS8gSCLSDv2EoGb8czeCD05e8pxp4ea4");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjRjOGE0NmZmLTRhMzQtNGQxMC1iMGJhLTFjNTY4YzViYTJjZiJ9.Gwb1zdUlPjoyU4Wg8uTMhEtxINBA--4D3f3LAFduMew");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImJkZDJmMjBjLTQ5NzEtNDY0OS1iZTFmLTIyOTZhNDcxMDMyYiJ9.yC7xXZa3CnNoF7t8gV_w9rrgK8_OqtlxBQ-IknCdCno");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjdkNTU2M2ZiLTc3MjItNDc2MS1iYWIzLTk1OGEyZTFkMGNhZSJ9.dEKQ_EMBq6p3q2OqSEC_VRwz8jNmzSokTnoU1g-cvNY");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImExODZkNzFhLTU3NzItNDViMC1hNGI3LTNiOGU1NTU5NzE1ZSJ9.9A9Eb4BBAfqGQH0LuADHKWVB6ugVrtpBb8zPlohVVwo");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjZmNjY4MjljLTczYWItNDJiNS04MTJhLTUxNzdkM2I0YzMxMCJ9.7DOVQ2XOsQZ90tEfbJEItWUKp5nBTDwrpLrFj5Dy1uE");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImM4YWMzZjk1LTA1ZWYtNDViYS05ODgyLTU2MjRjNzkzZmNkNyJ9.JLuR2xJDOKFfCQbctjK6o2gvEIqF2MWpnTm4XYSI_g8");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjZiODgzYjBlLTgxNjktNGYwNy05MjIyLTI3ODcyMGFkYzZjMiJ9.mPDh_Xo_C7JtLZDFdQgv5M6vDaz3qDqRGCy0U24yGCc");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImE0OWY5ZjBkLTk2NjMtNGQzNy1iNTkyLTYyNDU4MDAxYjUzZCJ9.AyV5Kv0c2k8lUPySRBEDpQrns2RwnNP0c_Y0Lyi-ZrU");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjU5NzM2NDkzLTdhMWUtNGQ0Yi05NjRmLTUxOTBmNzAwZDA5MSJ9.shGmEE7TbkPTzpJt2bm7nIhJsQj9QqJKpqZKhYLOrM4");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjE1ZGI4MjA1LWJkZDItNGYxNS05NzQ4LTBkMDg0MWQ5ZWE2ZSJ9.vXmMwbjEOG_6a_7XLTBQ-RJvF-73jYpae_s-Z30vRzI");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6Ijk2Yjc0NGIwLTFjOWUtNDRkOS05Njk3LTc4OThjN2UxMDkwMCJ9.faKy2GgfRyE-esmdyHMlPgX4aR1Eu8v2wKndtqZvErE");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjI1YTk5MDlkLWM2MTctNGU2Zi1hN2NkLTcyMWM2MjVlNTgwMyJ9.Vy6gGBXgKMQiGTLSJo4jpGxW4FP-Kd-8K0BTyZFe_No");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjA1YWZmMjhjLWM0OWUtNDg1MS05Nzk1LWExYTNiZDM4OGNjYyJ9.Gjtc9II8tDtCDpJ2Rc9RpWRGRi7VNiMJRCrMRH-PSic");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImM0MmYyNzlhLTlhM2ItNDBiMi05NWJlLWYyNWIzMzdjYjNjYiJ9.cPgb_T9u22Rib20yAIHGls76r-ZmOzgn420oUaMQVDU");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6Ijc5NDk4N2NkLTI5ZTYtNDc4My05OWRjLTQ5YTRjZTc2ZWU4ZCJ9.yVSzHS-mp7nCXw30cSN0NDL1B49j-ZC8_20l7prO2rI");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImE0YTdjZTE2LTBkM2QtNGY0Ni04MmJmLWM4OGFhMjZiMDg5NSJ9.gZ3ArlC3DXdSp2lIaZKW8Zyle2bln1xj0kMqc1XjhXY");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImY5MzBjZTIwLWQ4OWItNDMwMy04NjM0LWUxYzM4YjdlYzNlOSJ9.Ph79EgUDZQjol2VSpdTstmSS82kDjF5uVS_4necYJ_I");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImE3OTg2NzlmLTZjN2ItNGU2MC05MzZkLTA5YTIxYTg1NzBlYyJ9.z6WE1h7WLBN6l6jnv39-pAiR9xwxaImvUtpKe-681Hc");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjA3ZmNjMGYwLWRkYzAtNDI2OS1iZDA4LTE1ZmNkMjFiNzhjNiJ9.ZFWi8ESb1R3W9pLiMCIU90CLctw24qGgNVZB_ufGI3o");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjNkZTBjNWYxLTBjMmYtNDcyNy04ZjdhLTRmYjJkMzgzNDcxNiJ9.nkn0cU039WmwpiakwBto472Su3RRS7gmYvBTL8Nbu-k");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjBkZmNjODNlLTNjY2UtNDJiYS1iNGZjLTQ4MTgzZTJlMzlmNiJ9.Js1MNyWN8UGVtUVhm1C5a0YgWarA4AJxGWgwKVdRPsQ");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6Ijg3MWVlN2Q0LWM0MzAtNDZiOS05MGU0LWJjMDRmNjZlMGUxOCJ9.LQWRdEAdFJ2J7amdkukkDKhIyODzinLblI-PdPFjQXo");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjgzNzA0ZWJjLWZhNjAtNDRiYy1iMzJkLTIwZjEwMDY4ZWFhOSJ9.N-GSgy4ZXfL21mNF4mtxAzyUb0dzc1NMy-y--cY2zxc");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjgzZDZjMmVkLTFhMGMtNDRiMC05Nzk0LWYyMzcxMjU5YTBjNCJ9.wsigfMmIkU03Wh-uSHlOToCPSZy4fguVR6EiEPUWGUY");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImU3M2JkNTUzLTIzMDAtNDA4ZS1hZmNjLTQ3MWY3MmFjNjc3NSJ9.ZAMvLZONc36jJ6_Z6FPs7VleL-0Pls7dEzpbX2oZ_4U");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImFmYWE4NTk5LTMwNWUtNGUwNi05Y2Y5LWNlYWQwM2Y5NjcyZSJ9.mNaSGyHxXKnbkngfmhyXAzRlu5gp68Vixn3E6dWSUnw");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjQyM2RiM2M1LTI2ZDgtNDIwNy1iM2Y1LWQ4MDgwZDYyYzZhNCJ9.VF8ykqOYPJGv8DgePKsjvtXPvm9IGhBCTOESG-knBis");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImYzNzU4OGFlLWY2YjUtNGNhNy1iOGU3LTQ1NGIwMjQwMWE1YSJ9.kJQI4hWgKaI8hKZ9mypdI-ftlGWWls9eEuQ4LyZQ8rs");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjkwYzZkMjY1LWU3ODgtNDBhNy05Yjk3LWU3YzExMTlhMWUzNiJ9.EZpy02_Bq0cRgfdKZSGEuNZf5iOE3L_G5fckmcgMigo");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjMwMGNjZjhkLTU0MTEtNDBlOS05OTFhLTQ4OWViMDllMmRhMSJ9.ideI9P2T0ff6JKH-oCZhibnYWA2UyverBs0DufsaHVQ");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjMwZmVmOTg0LTkwZDMtNDU4MC1hMmE0LWZkNmEzMzQxNmIyYyJ9.uUoyI1g6_nmd83XwYWimW1qpBsPfFz3qdSs35IpfNzY");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImVmMGQ4MzA3LWVkZDUtNGUzZi1hNTk5LTQ3YTk2ODQyODNkOSJ9.C2F97LdCbyWuBQgkXD2-iDuYq5J1NYtuncSHj5ImWkQ");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImFmZTQ0YTY5LTExODktNGIzOS1iNDc1LTI3Njg3NDM0Yzk3YSJ9.OV1XVn7_vC92Z18HffTN_UvdrOM2veyaNmJ-5Psi9p8");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjlhN2YwNjg2LTdiYTAtNDA4Yy04NDBjLTg0OWEwMjUwODJjOSJ9.xDdOJJc0BH1nbX_XQDFTHrhs2dI9g-8RjatB3CUEy_s");
    tokens.add(
        "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjFjYTNkODhkLWJkNmUtNDliYi05N2M4LTEwOTQ5Zjg2MWZhZiJ9.X_xp7lsfQuOzZT8lF34nkl9DV1vQiagnf2Bpt49hz2c");
  }

  // Метод для получения случайного токена
  public static String getRandomToken() {
    Random random = new Random();
    return tokens.get(random.nextInt(tokens.size()));
  }

  public static void main(String[] args) {
    // Пример вызова метода получения случайного токена
    System.out.println("Random Token: " + getRandomToken());
  }

}
