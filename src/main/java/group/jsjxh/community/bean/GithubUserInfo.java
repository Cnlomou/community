package group.jsjxh.community.bean;


import com.fasterxml.jackson.annotation.JacksonInject;

public class GithubUserInfo {

    private long id;
    private String name;
    private String bio;

    @Override
    public String toString() {
        return "GithubUserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
