package group.jsjxh.community.service;

import group.jsjxh.community.bean.QuestionBean;
import group.jsjxh.community.bean.TagBean;
import group.jsjxh.community.dao.QuestionDao;
import group.jsjxh.community.dao.TagDao;
import group.jsjxh.community.exception.ParamNoFoundException;
import group.jsjxh.community.util.AssertUtil;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class QuestionResolverService {
    @Resource
    QuestionDao questionDao;
    @Resource
    TagDao tagDao;

    @Transactional
    @CachePut(cacheNames = "ques",key = "#result.no")
    public QuestionBean publishQuestion(QuestionBean questionBean) throws ParamNoFoundException {
        AssertUtil.Null(questionBean.getContent(),"没有内容");
        AssertUtil.Null(questionBean.getTitle(),"没有标题");
        questionDao.insertQuestion(questionBean);
        for(String tag:questionBean.getTag()){
            TagBean tagBean = tagDao.getTagByName(tag);     //在数据库中为找到指定的数据会返回null
            if(tagBean==null)
                tagBean=new TagBean();
            tagBean.setName(tag);
            tagBean.setCreate_at(new Date());
            tagDao.insertTag(tagBean);
            tagDao.insertQuesTag(questionBean.getNo(),tagBean.getNo());
        }
        return questionBean;
    }

    @Cacheable(cacheNames = "tag",key = "name")
    public TagBean getTag(String name){
        return tagDao.getTagByName(name);
    }
}
