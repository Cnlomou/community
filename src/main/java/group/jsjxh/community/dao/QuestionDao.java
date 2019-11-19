package group.jsjxh.community.dao;

import group.jsjxh.community.bean.QuestionBean;
import org.apache.ibatis.annotations.*;


public interface QuestionDao {

    @Options(keyProperty = "no",useGeneratedKeys = true)
    @Insert("insert into tb_ques values(default,#{title},#{content},now(),#{author},now(),default,default,default)")
    void insertQuestion(QuestionBean questionBean);
}
