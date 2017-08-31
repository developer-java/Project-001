package view.kz.persistence;

import view.kz.persistence.parentTemplate.ENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SYSTEM_USERS")
public class SystemUser extends ENTITY {
    @Column(name = "F")
    private String firstname;
    @Column(name = "L")
    private String lastname;
    @Column(name = "P")
    private String patronmyc;
    @Column(name = "ADDRESS")
    private String Address;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;

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
