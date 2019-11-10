package group.jsjxh.community.dao;

import group.jsjxh.community.bean.User;

public interface UserDao {

    User findUserByToken(String token);
    void saveUser(User user);
    User findUserByAccount_id(Long account_id);
    void updateUser(User user);
}
