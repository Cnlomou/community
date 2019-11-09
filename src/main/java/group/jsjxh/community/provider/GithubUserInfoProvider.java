package group.jsjxh.community.provider;

import group.jsjxh.community.dto.AccessTokenDTO;
import group.jsjxh.community.dto.AccessTokenProvider;
import group.jsjxh.community.util.FormateUtil;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Field;

public class GithubUserInfoProvider {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public AccessTokenProvider getAccessToken(AccessTokenDTO accessTokenDTO,String accessTokenUrl){
        String json= FormateUtil.toJSON(accessTokenDTO);
        RequestBody body = RequestBody.create(json,JSON);
        Request request = new Request.Builder()
                .url(accessTokenUrl)
                .addHeader("accept","application/json")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return FormateUtil.toObject(response.body().string(), AccessTokenProvider.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public <T> T getUserInfo(AccessTokenProvider accessTokenDTO,String userInfoUrl,Class<T> c) throws IllegalAccessException {
        String url=formateGetRequest(accessTokenDTO,userInfoUrl);
        Request request=new Request.Builder()
                .url(url)
                .addHeader("accept","application/json")
                .build();
        try(Response response=client.newCall(request).execute()){
            return FormateUtil.toObject(response.body().string(),c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String formateGetRequest(AccessTokenProvider accessTokenProvider,String userInfoUrl) throws IllegalAccessException {
        Field[] declaredFields = accessTokenProvider.getClass().getDeclaredFields();
        StringBuffer buffer=new StringBuffer(64);
        buffer.append(userInfoUrl+"?");
        for( Field f: declaredFields){
            buffer.append(f.getName());
            buffer.append('=');
            f.setAccessible(true);
            buffer.append(f.get(accessTokenProvider));
            buffer.append('&');
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }
}
