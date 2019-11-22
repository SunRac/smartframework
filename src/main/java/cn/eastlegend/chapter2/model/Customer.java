package cn.eastlegend.chapter2.model;

/**
 * @author java_shj
 * @desc 客户模型
 * @createTime 2019/11/21 23:26
 **/
public class Customer {
    private long id;
    private String name;
    //联系人
    private  String contact;
    private String telephone;
    private  String email;
    //备注
    private  String remark;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
