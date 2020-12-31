package Entity;

public class Car {

    private String cid,cname,cprice,ccount,cstatus;

    public Car() {
        super();
    }

    public Car(String cid,String cname,String cprice,String ccount,String cstatus) {
        super();
        this.cid = cid;
        this.cname = cname;
        this.cprice = cprice;
        this.ccount = ccount;
        this.cstatus = cstatus;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCprice() {
        return cprice;
    }

    public void setCprice(String cprice) {
        this.cprice = cprice;
    }

    public String getCcount() {
        return ccount;
    }

    public void setCcount(String ccount) {
        this.ccount = ccount;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }
}
