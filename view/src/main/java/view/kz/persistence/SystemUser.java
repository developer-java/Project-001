package view.kz.persistence;

import view.kz.persistence.parentTemplate.ENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
