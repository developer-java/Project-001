package view.kz.web;

import view.kz.ejb.UserManagment;
import view.kz.persistence.SystemUser;
import view.kz.web.AppController.BundleManager;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.SQLException;

@ManagedBean
@SessionScoped
public class AutorizationModel {
    public static final String DEFAULT_PAGE = "/form/service.xhtml?faces-redirect=true";
    @EJB
    private UserManagment userManagment;
    private SystemUser user;
    private String login;
    private String password;

    public String doSign(){
        boolean hasEmpty = false;
        FacesContext context = FacesContext.getCurrentInstance();
        if(login==null || login.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("loginModal:login", new FacesMessage(FacesMessage.SEVERITY_ERROR, BundleManager.getInterface("lineError"), null));
            hasEmpty = true;
        }
        if(password==null||password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("loginModal:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, BundleManager.getInterface("lineError"), null));
            hasEmpty = true;
        }
        if(hasEmpty){
            return null;
        }
        SystemUser u = null;
        try {
            u = userManagment.getUserByLoginAndPassword(getLogin(),getPassword());
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage("loginModal:login", new FacesMessage(FacesMessage.SEVERITY_ERROR, BundleManager.getInterface("lineError"), null));
        }
        if(u==null){
            return "#error";
        }
        setUser(u);
        return "/form/service.xhtml?faces-redirect=true";
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
    public void logOut(){
        setUser(null);
        redirect();
    }
    public void handle() throws IOException {
        if(getUser()==null){
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml?faces-redirect=true");
        }
    }
    public void goToService() throws IOException {
        if(getUser()!=null){
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + DEFAULT_PAGE);
        }
    }
}
