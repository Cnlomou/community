package group.jsjxh.community.dao;

import group.jsjxh.community.bean.TagBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

public interface TagDao {

    @Options(keyProperty = "no",useGeneratedKeys = true)
    @Insert("insert into tb_tag values(default,#{name},#{create_at})")
    void insertTag(TagBean tagBean);

    @Select("select count(*) from tb_tag where tname=#{name}")
    Integer isHasByName(String name);
    @Insert("insert into tb_ques_tag values(#{quesno},#{tagno})")
    void insertQuesTag(Integer quesno,Integer tagno);

    @Select("select tb_tag.tno,tb_tag.tname,tb_tag.tcreate_at from tb_ques_tag left join tb_tag on tb_ques_tag.tagno=tb_tag.tno where tb_ques_tag.qesno=#{questionNo}")
    @Results({
            @Result(property = "no",column = "tno"),
            @Result(property = "name",column = "tname"),
            @Result(property = "create_at",column = "tcreate_at"),
    })
    List<TagBean> findTagByQuestionNo(Integer questionNo);

    @Select("select * from tb_tag where tname=#{name}")
    @Results({
            @Result(property = "no",column = "tno"),
            @Result(property = "name",column = "tname"),
            @Result(property = "create_at",column = "tcreate_at",jdbcType = JdbcType.TIMESTAMP,javaType = Date.class)
    })
    TagBean getTagByName(String name);


}
