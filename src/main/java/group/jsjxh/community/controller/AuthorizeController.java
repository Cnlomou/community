package group.jsjxh.community.controller;


import group.jsjxh.community.bean.GithubUserInfo;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.config.GithubAuthorizeInfoProvider;
import group.jsjxh.community.dao.UserDao;
import group.jsjxh.community.dto.AccessTokenDTO;
import group.jsjxh.community.dto.AccessTokenProvider;
import group.jsjxh.community.provider.GithubUserInfoProvider;
import group.jsjxh.community.util.UserUtil;
import group.jsjxh.community.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class AuthorizeController {
    private static final Logger logger= LoggerFactory.getLogger(AuthorizeController.class);
    @Resource
    GithubAuthorizeInfoProvider githubAuthorizeInfoProvider;
    @Resource
    UserDao userDao;

    @GetMapping("/callback")
    public String githubCallbock(String code, String state,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
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
            logger.info(userInfo.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(userInfo!=null){
            request.getSession().setAttribute("user", StringUtils.isEmpty(userInfo.getName())?"小白":userInfo.getName());
            String uuid=UuidUtil.uuid();

            Cookie token = new Cookie("_token", uuid);
            token.setMaxAge(3*60);
            response.addCookie(token);
            userDao.saveUser(new User(userInfo.getId(), UserUtil.userName(userInfo.getName()),uuid ,null,null));
        }
        return "redirect:/";
    }
    @GetMapping("/getuser/{token}")
    @ResponseBody
    public User gitUser(@PathVariable("token") String token){
       return userDao.findUserByToken(token);
    }
}
