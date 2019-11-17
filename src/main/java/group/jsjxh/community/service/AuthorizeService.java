package group.jsjxh.community.service;

import group.jsjxh.community.bean.GithubUserInfo;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.dao.UserDao;
import group.jsjxh.community.util.UserUtil;
import group.jsjxh.community.util.UuidUtil;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthorizeService {

    @Resource
    UserDao userDao;

    @Transactional
    public void saveUserInfo(@Nullable GithubUserInfo userInfo, HttpServletRequest request, HttpServletResponse response){
        String uuid= UuidUtil.uuid();
        User user = userDao.findUserByAccount_id(userInfo.getId());
        if(user ==null){
            user= new User(userInfo.getId(), UserUtil.userName(userInfo.getName()), uuid, null, null);
            userDao.saveUser(user);
        }
        else{
            user.setToken(uuid);
            userDao.updateUser(user);
        }
        Cookie token = new Cookie("_token", uuid);
        token.setMaxAge(3*60);
        response.addCookie(token);
        if(StringUtils.isEmpty(user.getName()))
            user.setName("小白");
        request.getSession().setAttribute("user",user );
    }
}
