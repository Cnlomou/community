package group.jsjxh.community.bean;

import java.util.Date;

public class User {
    private long account_id;
    private String name;
    private String token;
    private Date create_at;
    private Date update_at;

    public long getAccount_id() {
        return account_id;
    }

    public User(long account_id, String name, String token, Date create_at, Date update_at) {
        this.account_id = account_id;
        this.name = name;
        this.token = token;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}
