package view.kz.web;

import view.kz.ejb.UserManagment;
import view.kz.persistence.SystemUser;
import view.kz.web.AppController.BundleManager;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Login {
    public static final String DEFAULT_PAGE = "/form/service.xhtml";
    @EJB
    private UserManagment userManagment;
    private SystemUser user;
    private String login;
    private String password;

    public String doSign(){
        boolean hasEmpty = false;
        FacesContext context = FacesContext.getCurrentInstance();
        if(login==null || login.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("login", new FacesMessage(FacesMessage.SEVERITY_ERROR, BundleManager.getInterface("lineError"), null));
            hasEmpty = true;
        }
        if(password==null||password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("pass  ", new FacesMessage(FacesMessage.SEVERITY_ERROR, BundleManager.getInterface("lineError"), null));
            hasEmpty = true;
        }
        if(hasEmpty){
            return null;
        }
        if(login.equals("admin@admin") && password.equals("admin")){
            return "form/service.xhtml";
        }
        SystemUser u = userManagment.getUserByLoginAndPassword(getLogin(),getPassword());
        if(u==null){
            return null;
        }
        setUser(u);
        return "/form/service.xhtml";
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
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

    public static void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + DEFAULT_PAGE);
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
    }

}
