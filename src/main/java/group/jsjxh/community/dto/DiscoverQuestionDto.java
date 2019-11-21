package group.jsjxh.community.dto;

import group.jsjxh.community.bean.QuestionBean;
import lombok.Data;

@Data
public class DiscoverQuestionDto {
    QuestionBean questionBean;
    String[] tags;
    String author;
    String picurl;
}
