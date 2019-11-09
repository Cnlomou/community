package group.jsjxh.community.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth")
public class AuthorizeProperties {
    private Github github=new Github();

    public Github getGithub() {
        return github;
    }

    public static  class Github{
        private  String accesTokenUrl="https://github.com/login/oauth/access_token";
        private  String accessUserUrl="https://api.github.com/user";
        private  String clientId="e0e01a7e956becb968e5";
        private  String clientSecret="7e0a3c4fc15c26fbbe6334b64d049560cf1337f6";

        public  String getAccesTokenUrl() {
            return accesTokenUrl;
        }

        public  void setAccesTokenUrl(String accesTokenUrl) {
            this.accesTokenUrl = accesTokenUrl;
        }

        public  String getAccessUserUrl() {
            return accessUserUrl;
        }

        public  void setAccessUserUrl(String accessUserUrl) {
            this.accessUserUrl = accessUserUrl;
        }

        public  String getClientId() {
            return clientId;
        }

        public  void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public  String getClientSecret() {
            return clientSecret;
        }

        public  void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }
}
