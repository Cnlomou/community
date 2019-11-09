package group.jsjxh.community.config;

public class GithubAuthorizeInfoProvider {
    private AuthorizeProperties.Github githubProperties;
    public GithubAuthorizeInfoProvider (AuthorizeProperties authorizeProperties){
        githubProperties=authorizeProperties.getGithub();
    }

    public AuthorizeProperties.Github getGithubProperties() {
        return githubProperties;
    }
    public String getClientId(){
        return this.githubProperties.getClientId();
    }
    public String getClientSecret(){
        return this.githubProperties.getClientSecret();
    }
    public String getAccesTokenUrl(){
        return this.githubProperties.getAccesTokenUrl();
    }
    public String getAccessUserUrl(){
        return this.githubProperties.getAccessUserUrl();
    }
}
