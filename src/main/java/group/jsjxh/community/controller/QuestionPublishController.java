package group.jsjxh.community.controller;

import group.jsjxh.community.bean.QuestionBean;
import group.jsjxh.community.bean.User;
import group.jsjxh.community.exception.ParamNoFoundException;
import group.jsjxh.community.service.QuestionResolverService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
        QuestionBean questionBean = new QuestionBean(0,title,content,tags,new Date(),author);
        questionBean = questionResolverService.publishQuestion(questionBean);
        if(questionBean.getNo()!=0){
            objectObjectHashMap.put("code","ok");
            objectObjectHashMap.put("msg","发布成功");
        }
        return objectObjectHashMap;
    }
}
