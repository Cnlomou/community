package group.jsjxh.community.bean;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer id;
    private long account_id;
    private String name;
    private String token;
    private Date create_at;
    private Date update_at;
    private String picUrl;
}
