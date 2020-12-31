package Entity;

public class User {

    private String uid,uname,upwd;

    public User() {
        super();
    }

    public User(String uid,String uname,String upwd) {
        super();
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
}
