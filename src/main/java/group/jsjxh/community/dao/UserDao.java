package group.jsjxh.community.dao;

import group.jsjxh.community.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    User findUserByToken(String token);
    void saveUser(User user);
    User findUserByAccount_id(Long account_id);
    void updateToken(User user);

    @Select("select * from tb_user where id=#{userNo}")
    User finUserByNo(Integer userNo);

}
