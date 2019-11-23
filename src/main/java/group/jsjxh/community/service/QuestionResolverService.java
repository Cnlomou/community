package group.jsjxh.community.service;

import com.github.pagehelper.PageHelper;
import group.jsjxh.community.bean.*;
import group.jsjxh.community.dao.QuesTagMapper;
import group.jsjxh.community.dao.QuestionMapper;
import group.jsjxh.community.dao.TagMapper;
import group.jsjxh.community.dto.DiscoverQuestionDto;
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
    QuestionMapper questionMapper;

    @Resource
    UserService userService;
    @Resource
    TagMapper tagMapper;
    @Resource
    QuesTagMapper quesTagMapper;

    @Transactional
    public Question publishQuestion(Question question, String[] tags) throws ParamNoFoundException {
        AssertUtil.Null(question.getQcontent(),"没有内容");
        AssertUtil.Null(question.getQtitle(),"没有标题");
        questionMapper.insertSelective(question);
        for(String tag:tags){
            Tag tagBean = this.getTag(tag);     //在数据库中为找到指定的数据会返回null
            if(tagBean==null){
                tagBean=new Tag();
                tagBean.setTname(tag);
                tagBean.setTcreateAt(new Date());
                tagMapper.insertSelective(tagBean);
            }
            QuesTagKey record = new QuesTagKey();
            record.setQesno(question.getQno());
            record.setTagno(tagBean.getTno());
            quesTagMapper.insertSelective(record);
        }
        return question;
    }

    @Cacheable(cacheNames = "tag",key = "#name",condition = "#result!=null")
    public Tag getTag(String name){
        TagExample example = new TagExample();
        example.createCriteria()
                .andTnameEqualTo(name);
        return tagMapper.selectByExample(example).get(0);
    }

    @Cacheable(cacheNames = "tag",key ="#questionNo" )
    public List<Tag> getTag(Integer questionNo){
        return tagMapper.selectTagByQuestionNo(questionNo);
    }


    @Cacheable(cacheNames = "ques",key = "methodName+'['+#pageNo+','+#pageSize+']'")
    public List<DiscoverQuestionDto> discoberQuestionAll(Integer pageNo, Integer pageSize){
        List<Question> questionAll = this.getQuestionAll(pageNo, pageSize);
        List<DiscoverQuestionDto> discoverQuestionDtos =new ArrayList<>(questionAll.size());
        Integer questionNo,author;
        for(Question questionBean:questionAll){
            DiscoverQuestionDto discoverQuestionDto = new DiscoverQuestionDto();
            questionNo=questionBean.getQno();
            author=questionBean.getQauthor();
            //获取每个问题的标记
            List<Tag> tag = this.getTag(questionNo);
            String[] tags=new String[tag.size()];
            for(int i=0;i<tags.length;i++)
                tags[i]=tag.get(i).getTname();
            //获取每个问题的发布者
            User userByNo = userService.getUserByNo(author);
            //设置到对象中
            discoverQuestionDto.setQuestion(questionBean);
            discoverQuestionDto.setTags(tags);
            discoverQuestionDto.setAuthor(userByNo.getName());
            discoverQuestionDto.setPicurl(userByNo.getPicurl());
            //装入集合
            discoverQuestionDtos.add(discoverQuestionDto);
        }
        return discoverQuestionDtos;
    }

    @Cacheable(cacheNames = "ques",key = "'['+#pageNo+','+#pageSize+']'")
    public List<Question> getQuestionAll(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return questionMapper.selectByExampleWithBLOBs(new QuestionExample());
    }

    @Cacheable(cacheNames = "ques",key = "#root.methodName+'_'+#pageSize")
    public Integer getPageCount(Integer pageSize){
        QuestionExample example = new QuestionExample();
        long count = questionMapper.countByExample(example);
        return Math.toIntExact((count + pageSize - 1L) / pageSize);
    }
}
