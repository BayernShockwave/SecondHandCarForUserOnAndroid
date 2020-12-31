package Entity;

public class FindPwd {

    private String userid,userphone,useremail;

    public FindPwd() {
        super();
    }

    public FindPwd(String userid,String userphone,String useremail) {
        super();
        this.userid = userid;
        this.userphone = userphone;
        this.useremail = useremail;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
