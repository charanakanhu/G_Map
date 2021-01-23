package com.kanhucharan.apimap.Model;

public class ProfileModel {
    String id, name,address;
    String cat;
    String cat_id;
    String dec;
    String cont_no;
    String emp_id;
    String img;

    public ProfileModel(String id, String name, String address, String cat, String cat_id, String dec, String cont_no, String emp_id, String img) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cat = cat;
        this.cat_id = cat_id;
        this.dec = dec;
        this.cont_no = cont_no;
        this.emp_id = emp_id;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getCont_no() {
        return cont_no;
    }

    public void setCont_no(String cont_no) {
        this.cont_no = cont_no;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
