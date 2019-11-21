package group.jsjxh.community.provider;

import group.jsjxh.community.bean.GithubUserInfo;
import group.jsjxh.community.config.GithubAuthorizeInfoProvider;
import group.jsjxh.community.dto.AccessTokenDTO;
import group.jsjxh.community.dto.AccessTokenProvider;
import group.jsjxh.community.util.FormateUtil;
import okhttp3.*;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public class GithubUserInfoProvider {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static org.slf4j.Logger logger= LoggerFactory.getLogger(GithubUserInfoProvider.class);
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
