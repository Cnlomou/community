package group.jsjxh.community.service;

import group.jsjxh.community.bean.GithubUserInfo;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.dao.UserDao;
import group.jsjxh.community.util.UserUtil;
import group.jsjxh.community.util.UuidUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    @Transactional
    public void saveUserInfo(@Nullable GithubUserInfo userInfo, HttpServletRequest request, HttpServletResponse response){
        String uuid= UuidUtil.uuid();
        User user = this.getUserByAccount_id(userInfo.getId());//数据库找不到该数据返回null
        if(user ==null){
            user= new User(0, userInfo.getId(), UserUtil.userName(userInfo.getName()), uuid, null, null);
            userDao.saveUser(user);
        }
        else{
            String oletoken=user.getToken();
            user.setToken(uuid);
            user.setUpdate_at(new Date());
            this.updateToken(user, oletoken);       //传入久toke让旧token不能登陆
        }
        Cookie token = new Cookie("_token", uuid);
        token.setMaxAge(3*60*10);       //30分钟免登录
        response.addCookie(token);
        request.getSession().setAttribute("user",user );
    }

    @Cacheable(cacheNames = "user",key = "account_id",condition = "#result!=null")
    public  User getUserByAccount_id(Long account_id){
        return  userDao.findUserByAccount_id(account_id);
    }
    @Cacheable(cacheNames = "user",key = "token",condition = "#result!=null")
    public User getUserByTokne(String token){
        return  userDao.findUserByToken(token);
    }

    @Transactional
    @Caching(put = {
            @CachePut(cacheNames = "user",key ="oldToken",condition = "oldToken!=null"),
            @CachePut(cacheNames = "user",key = "user.account_id")
    })
    public User updateToken(User user,String oldToken){
        userDao.updateToken(user);
        return user;
    }
}