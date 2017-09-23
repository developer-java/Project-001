package view.kz.persistence;

import view.kz.persistence.parentTemplate.ENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SYSTEM_USERS")
public class SystemUser extends ENTITY {
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "PATRONMYC")
    private String patronmyc;
    @Column(name = "ADDRESS")
    private String Address;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "IS_STUDENT")
    private Boolean student;
    @Column(name = "FACULTET")
    private String facultet;
    @Column(name = "GROUP")
    private String group;
    @Column(name = "KAFEDRA")
    private String kafedra;
    @Column(name = "IIN")
    private String iin;

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    public String getFacultet() {
        return facultet;
    }

    public void setFacultet(String facultet) {
        this.facultet = facultet;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getKafedra() {
        return kafedra;
    }

    public void setKafedra(String kafedra) {
        this.kafedra = kafedra;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronmyc() {
        return patronmyc;
    }

    public void setPatronmyc(String patronmyc) {
        this.patronmyc = patronmyc;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Transient
    public String getFIO(){
        return String.format("%s %s %s",firstname,lastname,patronmyc);
    }
}
