package group.jsjxh.community.controller;


import group.jsjxh.community.bean.GithubUserInfo;
import group.jsjxh.community.config.GithubAuthorizeInfoProvider;
import group.jsjxh.community.dto.AccessTokenDTO;
import group.jsjxh.community.dto.AccessTokenProvider;
import group.jsjxh.community.provider.GithubUserInfoProvider;
import group.jsjxh.community.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AuthorizeController {
    private static final Logger logger= LoggerFactory.getLogger(AuthorizeController.class);
    @Resource
    GithubAuthorizeInfoProvider githubAuthorizeInfoProvider;
    @Resource
    UserService userService;

    @GetMapping("/callback")
    public String githubCallbock(String code, String state,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        String referer=request.getHeader("Referer");
        referer=referer.substring(referer.indexOf("/"));
        GithubUserInfoProvider githubUserInfoProvider=new GithubUserInfoProvider();
        GithubUserInfo userInfo=null;
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(githubAuthorizeInfoProvider.getClientSecret());
        accessTokenDTO.setClient_id(githubAuthorizeInfoProvider.getClientId());
        AccessTokenProvider accessToken = githubUserInfoProvider.getAccessToken(accessTokenDTO, githubAuthorizeInfoProvider.getAccesTokenUrl());
        try {
            userInfo=githubUserInfoProvider.getUserInfo(accessToken,githubAuthorizeInfoProvider.getAccessUserUrl(), GithubUserInfo.class);
            if(logger.isInfoEnabled())
                logger.info(userInfo.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
       try{
           userService.saveUserInfo(userInfo,request,response);
       }catch (Throwable ignored){
           ignored.printStackTrace();
       }
        return "redirect:"+referer;     //回到登陆之前得页面
    }
}
