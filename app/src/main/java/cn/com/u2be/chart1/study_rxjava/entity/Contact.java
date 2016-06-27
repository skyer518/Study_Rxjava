package cn.com.u2be.chart1.study_rxjava.entity;

/**
 * Created by alek on 2016/6/27.
 */
public class Contact {

    private int id;
    private String name;
    private String tel;

    public Contact(int id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
