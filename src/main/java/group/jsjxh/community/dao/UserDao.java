package group.jsjxh.community.dao;

import group.jsjxh.community.bean.User;

public interface UserDao {

    User findUserByToken(String token);
    void saveUser(User user);
}
