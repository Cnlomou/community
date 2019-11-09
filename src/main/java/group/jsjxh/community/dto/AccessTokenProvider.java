package group.jsjxh.community.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;

public class AccessTokenProvider {
    private String access_token;
    private String token_type;
    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "AccessTokenProvider{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
