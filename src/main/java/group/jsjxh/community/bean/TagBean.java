package group.jsjxh.community.bean;

import java.util.Date;

public class TagBean {
    private Integer no;
    private String name;
    private Date create_at;

    public TagBean() {
    }

    public TagBean(Integer no, String name, Date create_at) {
        this.no = no;
        this.name = name;
        this.create_at = create_at;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
