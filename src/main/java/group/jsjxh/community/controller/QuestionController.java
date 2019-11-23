package group.jsjxh.community.controller;

import group.jsjxh.community.bean.Question;
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
public class QuestionController {
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
        Question question=new Question();
        question.setQtitle(title);
        question.setQcontent(content);
        question.setQauthor(author);
       question=questionResolverService.publishQuestion(question,tags);
        return objectObjectHashMap;
    }
    @GetMapping("/ajax/getQuestion")
    @ResponseBody
    public Map<String,Object> getDiscoverQuestion(@RequestParam("pageNo") Integer pageNo,
                                                  @RequestParam("pageSize") Integer pageSize){
        Map<String,Object> res=new HashMap<>();
        if(pageNo==null||pageSize==null||pageSize.equals(0)){
            res.put("code","no");
            res.put("msg","参数传递错误");
            return res;
        }
        res.put("code","ok");
        res.put("ques",questionResolverService.discoberQuestionAll(pageNo,pageSize));
        res.put("pageCount",questionResolverService.getPageCount(pageSize).toString());
        return  res;
    }
}
