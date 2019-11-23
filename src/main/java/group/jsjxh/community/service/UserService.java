package group.jsjxh.community.service;

import group.jsjxh.community.bean.GithubUserInfo;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.bean.UserExample;
import group.jsjxh.community.dao.UserMapper;
import group.jsjxh.community.util.UserUtil;
import group.jsjxh.community.util.UuidUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Transactional
    public void saveUserInfo(@NotNull GithubUserInfo userInfo, HttpServletRequest request, HttpServletResponse response){
        String uuid= UuidUtil.uuid();
        User user = this.getUserByAccount_id(userInfo.getId());//数据库找不到该数据返回null
        if(user ==null){
            user= new User();
            user.setAccountId((int) userInfo.getId());
            user.setName(UserUtil.userName(userInfo.getName()));
            user.setToken(uuid);
            user.setPicurl(userInfo.getAvatar_url());
            userMapper.insertSelective(user);
        }
        else{
            String oletoken=user.getToken();
            user.setToken(uuid);
            this.updateToken(uuid, oletoken);       //传入久toke让旧token不能登陆
        }
        Cookie token = new Cookie("_token", uuid);
        token.setMaxAge(3*60*10);       //30分钟免登录
        response.addCookie(token);
        request.getSession().setAttribute("user",user );
    }

    @Cacheable(cacheNames = "user",key = "#root.methodName+#account_id")
    public  User getUserByAccount_id(Long account_id){
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(Math.toIntExact(account_id));
        return userMapper.selectByExample(example).get(0);

    }
    @Cacheable(cacheNames = "user",key = "#token")
    public User getUserByTokne(String token){
        UserExample example = new UserExample();
        example.createCriteria()
                .andTokenEqualTo(token);
        return userMapper.selectByExample(example).get(0);
    }

    @Cacheable(cacheNames = "user",key = "#root.methodName+'_'+#no")
    public User getUserByNo(Integer no){
        UserExample example = new UserExample();
        example.createCriteria()
                .andIdEqualTo(no);
        return userMapper.selectByExample(example).get(0);
    }

    @Transactional
    public User updateToken(String token,String oldToken){
        UserExample example = new UserExample();
        example.createCriteria()
                .andTokenEqualTo(oldToken);
        User user = new User();
        user.setToken(token);
        if(userMapper.updateByExampleSelective(user,example)>0)
            return user;
        return null;
    }
}
