package group.jsjxh.community.dao;

import group.jsjxh.community.bean.QuestionBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface QuestionDao {

    @Options(keyProperty = "no",useGeneratedKeys = true)
    @Insert("insert into tb_ques values(default,#{title},#{content},now(),#{author},now(),default,default,default)")
    void insertQuestion(QuestionBean questionBean);

    @Select("select * from tb_ques")
    @Results({
            @Result(property = "no",column = "qno"),
            @Result(property = "title",column = "qtitle"),
            @Result(property = "content",column = "qcontent"),
            @Result(property = "create_at",column = "qcreate_at"),
            @Result(property = "update_at",column = "qupdate_at"),
            @Result(property = "read",column = "qread"),
            @Result(property = "like",column = "qlike"),
            @Result(property = "shit",column = "qshit"),
            @Result(property = "author",column = "qauthor")
    })
    List<QuestionBean> findQuestionAll();
}
