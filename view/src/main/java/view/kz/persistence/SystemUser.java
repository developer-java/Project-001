package view.kz.persistence;

import view.kz.persistence.common.Identifier;
import view.kz.persistence.parentTemplate.ENTITY;
import view.kz.persistence.types.PositionTypes;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "SYSTEM_USERS")
public class SystemUser extends Identifier {
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "PATRONMYC")
    private String patronmyc;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "POSITION")
    private PositionTypes position;
    @Column(name = "IS_TEST_USER")
    private boolean testUser;

    public boolean isTestUser() {
        return testUser;
    }

    public void setTestUser(boolean testUser) {
        this.testUser = testUser;
    }

    public PositionTypes getPosition() {
        return position;
    }

    public void setPosition(PositionTypes position) {
        this.position = position;
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
        if (patronmyc == null) {
            patronmyc = "";
        }
        return patronmyc;
    }

    public void setPatronmyc(String patronmyc) {
        this.patronmyc = patronmyc;
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
    public String getFIO() {
        return String.format("%s %s %s", getFirstname(), getLastname(), getPatronmyc());
    }
}
