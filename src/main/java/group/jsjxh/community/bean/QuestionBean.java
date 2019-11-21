package group.jsjxh.community.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class QuestionBean {
    private Integer no;
    private String title;
    private String content;
    private Date create_at;
    private Date update_at;
    private Integer read;
    private Integer like;
    private Integer shit;
    private Integer author;

}
