package view.kz.web.Registration;

import view.kz.ejb.UserManagment;
import view.kz.persistence.SystemUser;
import view.kz.web.AutorizationModel;
import view.kz.web.RedirectModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class RegistrationModel {
    private SystemUser user;

    @Inject
    private RedirectModel redirect;
    @Inject
    AutorizationModel autorizationModel;
    @EJB
    private UserManagment um;

    public SystemUser getUser() {
        if(user==null){
            user = new SystemUser();
        }
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public UserManagment getUm() {
        return um;
    }

    public void setUm(UserManagment um) {
        this.um = um;
    }

    public String registration(){
        if(false){
            return null;
        }
//        um.addUser(user);
        autorizationModel.setUser(user);
        return redirect.goToDefaultPage();
    }

}
