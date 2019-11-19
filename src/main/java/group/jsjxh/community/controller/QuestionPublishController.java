package group.jsjxh.community.controller;

import group.jsjxh.community.bean.QuestionBean;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.exception.ParamNoFoundException;
import group.jsjxh.community.service.QuestionResolverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class QuestionPublishController {
    @Resource
    QuestionResolverService questionResolverService;

    @GetMapping("/publish/")
    public String publishPage(){
        return "publish";
    }

    @PostMapping("/publishaction")
    @ResponseBody
    public Map<String ,String> publishAction(@RequestParam("title") String title,
                                             @RequestParam("content") String content,
                                             @RequestParam("tag") String tag,
                                             HttpSession session) throws ParamNoFoundException {
        HashMap<String , String> objectObjectHashMap = new HashMap<>();
        String[] tags=tag.split(",");
        if(session.getAttribute("user")==null){
            objectObjectHashMap.put("code","no");
            objectObjectHashMap.put("msg","请登陆");
            return  objectObjectHashMap;
        }
        Integer author=((User)session.getAttribute("user")).getId();
        QuestionBean questionBean = new QuestionBean();
        questionBean.setTitle(title);
        questionBean.setContent(content);
        questionBean.setAuthor(author);
        questionBean = questionResolverService.publishQuestion(questionBean,tags);
        if(questionBean.getNo()!=0){
            objectObjectHashMap.put("code","ok");
            objectObjectHashMap.put("msg","发布成功");
        }
        return objectObjectHashMap;
    }
    @GetMapping("/ajax/getQuestion")
    @ResponseBody
    public Map<String,Object> getDiscoverQuestion(@RequestParam("pageNo") Integer pageNo,
                                                  @RequestParam("pageSize") Integer pageSize){
        Map<String,Object> res=new HashMap<>();
        if(pageNo==null||pageSize==null){
            res.put("code","no");
            res.put("msg","参数传递错误");
            return res;
        }
        res.put("code","ok");
        res.put("ques",questionResolverService.discoberQuestionAll(pageNo,pageSize));
        return  res;
    }
}
