package group.jsjxh.community.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginManager {
    private static Logger logger= LoggerFactory.getLogger(LoginManager.class);

    @PostMapping("/callback")
    public String OAuthGithubCallback(String code,Integer state){
        logger.info(code);
        return "index";
    }
}
