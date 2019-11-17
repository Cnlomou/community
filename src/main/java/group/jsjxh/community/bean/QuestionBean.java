package group.jsjxh.community.bean;

import java.util.Date;

public class QuestionBean {
    private Integer no;
    private String title;
    private String content;
    private String[] tag;
    private Date create_at;
    private Integer author;

    public QuestionBean(Integer no, String title, String content, String[] tag, Date create_at,Integer author) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.create_at = create_at;
        this.author=author;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titl) {
        this.title = titl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }
}
