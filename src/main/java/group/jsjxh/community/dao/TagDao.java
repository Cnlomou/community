package group.jsjxh.community.dao;

import group.jsjxh.community.bean.TagBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

public interface TagDao {

    @Options(keyProperty = "no",useGeneratedKeys = true)
    @Insert("insert into tb_tag values(default,#{name},#{create_at})")
    void insertTag(TagBean tagBean);

    @Select("select count(*) from tb_tag where tname=#{name}")
    Integer isHasByName(String name);
    @Insert("insert into tb_ques_tag values(#{quesno},#{tagno})")
    void insertQuesTag(Integer quesno,Integer tagno);

    @Select("select * from tb_tag where tname=#{name}")
    @Results({
            @Result(property = "no",column = "tno"),
            @Result(property = "name",column = "tname"),
            @Result(property = "create_at",column = "tcreate_at",jdbcType = JdbcType.TIMESTAMP,javaType = Date.class)
    })
    TagBean getTagByName(String name);


}
