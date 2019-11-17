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
        for(String tag:questionBean.getTag())
            tagDao.insertTag(new TagBean(0,tag,questionBean.getNo()));
        return questionBean;
    }
}
