package Entity;

public class Shopcar {

    private String uid,cid,count;

    public Shopcar() {
        super();
    }

    public Shopcar(String uid,String cid,String count) {
        super();
        this.uid = uid;
        this.cid = cid;
        this.count = count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
