package group.jsjxh.community.dao;

import group.jsjxh.community.bean.TagBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface TagDao {

    @Options(keyProperty = "no",useGeneratedKeys = true)
    @Insert("insert into tb_tag values(default,#{tag},#{qes_no})")
    void insertTag(TagBean tagBean);
}
