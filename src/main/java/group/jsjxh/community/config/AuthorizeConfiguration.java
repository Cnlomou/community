package group.jsjxh.community.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AuthorizeProperties.class)
public class AuthorizeConfiguration {

    @Bean
    public GithubAuthorizeInfoProvider githubAuthorizeInfoProvider(AuthorizeProperties authorizeProperties){
        return new GithubAuthorizeInfoProvider(authorizeProperties);
    }
}
