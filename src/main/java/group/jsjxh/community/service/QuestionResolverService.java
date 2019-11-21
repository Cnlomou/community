package group.jsjxh.community.service;

import com.github.pagehelper.PageHelper;
import group.jsjxh.community.dto.DiscoverQuestionDto;
import group.jsjxh.community.bean.QuestionBean;
import group.jsjxh.community.bean.TagBean;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.dao.QuestionDao;
import group.jsjxh.community.dao.TagDao;
import group.jsjxh.community.exception.ParamNoFoundException;
import group.jsjxh.community.util.AssertUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

@Service
public class QuestionResolverService {
    @Resource
    QuestionDao questionDao;

    @Resource
    UserService userService;
    @Resource
    TagDao tagDao;

    @Transactional
    public QuestionBean publishQuestion(QuestionBean questionBean,String[] tags) throws ParamNoFoundException {
        AssertUtil.Null(questionBean.getContent(),"没有内容");
        AssertUtil.Null(questionBean.getTitle(),"没有标题");
        questionDao.insertQuestion(questionBean);
        for(String tag:tags){
            TagBean tagBean = this.getTag(tag);     //在数据库中为找到指定的数据会返回null
            if(tagBean==null){
                tagBean=new TagBean();
                tagBean.setName(tag);
                tagBean.setCreate_at(new Date());
                tagDao.insertTag(tagBean);
            }
            tagDao.insertQuesTag(questionBean.getNo(),tagBean.getNo());
        }
        return questionBean;
    }

    @Cacheable(cacheNames = "tag",key = "#name",condition = "#result!=null")
    public TagBean getTag(String name){
        return tagDao.getTagByName(name);
    }

    @Cacheable(cacheNames = "tag",key ="#questionNo" )
    public List<TagBean> getTag(Integer questionNo){
        return tagDao.findTagByQuestionNo(questionNo);
    }


    @Cacheable(cacheNames = "ques",key = "methodName+'['+#pageNo+','+#pageSize+']'")
    public List<DiscoverQuestionDto> discoberQuestionAll(Integer pageNo, Integer pageSize){
        List<QuestionBean> questionAll = this.getQuestionAll(pageNo, pageSize);
        List<DiscoverQuestionDto> discoverQuestionDtos =new ArrayList<>(questionAll.size());
        Integer questionNo,author;
        for(QuestionBean questionBean:questionAll){
            DiscoverQuestionDto discoverQuestionDto = new DiscoverQuestionDto();
            questionNo=questionBean.getNo();
            author=questionBean.getAuthor();
            //获取每个问题的标记
            List<TagBean> tag = this.getTag(questionNo);
            String[] tags=new String[tag.size()];
            for(int i=0;i<tags.length;i++)
                tags[i]=tag.get(i).getName();
            //获取每个问题的发布者
            User userByNo = userService.getUserByNo(author);
            //设置到对象中
            discoverQuestionDto.setQuestionBean(questionBean);
            discoverQuestionDto.setTags(tags);
            discoverQuestionDto.setAuthor(userByNo.getName());
            discoverQuestionDto.setPicurl(userByNo.getPicurl());
            //装入集合
            discoverQuestionDtos.add(discoverQuestionDto);
        }
        return discoverQuestionDtos;
    }

    @Cacheable(cacheNames = "ques",key = "'['+#pageNo+','+#pageSize+']'")
    public List<QuestionBean> getQuestionAll(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return questionDao.findQuestionAll();
    }
}
