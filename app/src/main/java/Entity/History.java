package Entity;

public class History {

    private String uid,cid,date;

    public History() {
        super();
    }

    public History(String uid,String cid,String date) {
        super();
        this.uid = uid;
        this.cid = cid;
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
