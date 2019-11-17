package group.jsjxh.community.bean;

public class TagBean {
    private Integer no;
    private String tag;
    private Integer qes_no;

    public TagBean(Integer no, String tag, Integer qes_no) {
        this.no = no;
        this.tag = tag;
        this.qes_no = qes_no;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getQes_no() {
        return qes_no;
    }

    public void setQes_no(Integer qes_no) {
        this.qes_no = qes_no;
    }
}
