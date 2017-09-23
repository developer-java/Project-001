package view.kz.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "redirectModel")
@SessionScoped
public class RedirectModel {
    public String goToMainPage(){
        return "/index.xhtml?faces-redirect=true";
    }
    public String goToDefaultPage(){
        return "/form/service.xhtml?faces-redirect=true";
    }
}
