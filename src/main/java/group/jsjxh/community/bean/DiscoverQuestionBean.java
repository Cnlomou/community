package group.jsjxh.community.bean;

import lombok.Data;

@Data
public class DiscoverQuestionBean {
    QuestionBean questionBean;
    String[] tags;
    String author;
    String picurl;
}
