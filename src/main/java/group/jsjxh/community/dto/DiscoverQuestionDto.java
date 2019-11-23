package group.jsjxh.community.dto;

import group.jsjxh.community.bean.Question;
import lombok.Data;

@Data
public class DiscoverQuestionDto {
    Question question;
    String[] tags;
    String author;
    String picurl;
}
