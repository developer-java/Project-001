package view.kz.web.request;

import view.kz.ejb.UserManagment;
import view.kz.persistence.Graphics;
import view.kz.persistence.SystemUser;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EditModel {

    @EJB
    private UserManagment userManagment;

    @ManagedProperty(value = "#{requestMainModel}")
    private MainModel mainModel;

}
