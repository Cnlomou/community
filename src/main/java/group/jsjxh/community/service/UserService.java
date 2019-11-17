package group.jsjxh.community.service;

import group.jsjxh.community.bean.GithubUserInfo;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.dao.UserDao;
import group.jsjxh.community.util.UserUtil;
import group.jsjxh.community.util.UuidUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    @Transactional
    public void saveUserInfo(@Nullable GithubUserInfo userInfo, HttpServletRequest request, HttpServletResponse response){
        String uuid= UuidUtil.uuid();
        User user = userDao.findUserByAccount_id(userInfo.getId());//数据库找不到该数据返回null
        if(user ==null){
            user= new User(0, userInfo.getId(), UserUtil.userName(userInfo.getName()), uuid, null, null);
            userDao.saveUser(user);
        }
        else{
            user.setToken(uuid);
            userDao.updateUser(user);
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
}
