package com.su.community.provider;

import com.alibaba.fastjson.JSON;
import com.su.community.dto.AccessTokenDTO;
import com.su.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String rs=response.body().string();
            String token = rs.split("&")[0].split("=")[1];
//            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().header("Authorization","Bearer " + accessToken)   //变更
                .url("https://api.github.com/user")
                .build();

        try {
            Response response = client.newCall(request).execute();
            String rs=response.body().string();
            GithubUser githubUser = JSON.parseObject(rs, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
