package view.kz.web.request;

import view.kz.ejb.UserManagment;
import view.kz.persistence.Graphics;
import view.kz.persistence.RequestType1;
import view.kz.persistence.SystemUser;
import view.kz.persistence.types.PositionTypes;
import view.kz.web.AutorizationModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "requestMainModel")
@SessionScoped
public class MainModel {

    @EJB
    private UserManagment userManagment;
    private Date selectedDate;
    private Long selectedUserId;
    private SystemUser selectedUser;
    private Graphics selectedUserGraphics;
    private RequestType1 request;
    @ManagedProperty(value = "#{autorizationModel}")
    private AutorizationModel autorizationModel;

    public AutorizationModel getAutorizationModel() {
        return autorizationModel;
    }

    public void setAutorizationModel(AutorizationModel autorizationModel) {
        this.autorizationModel = autorizationModel;
    }

    public SystemUser getSystemUser(){
        return autorizationModel.getUser();
    }

    public RequestType1 getRequest() {
        if(request == null){
            request = new RequestType1();
        }
        return request;
    }

    public void setRequest(RequestType1 request) {
        this.request = request;
    }

    public Graphics getSelectedUserGraphics() {
        if (selectedUserGraphics == null) {
            selectedUserGraphics = userManagment.getGraphicsByUserId(selectedUser.getId());
        }
        if(!selectedUserGraphics.getUser().getId().equals(selectedUser.getId())){
            selectedUserGraphics = userManagment.getGraphicsByUserId(selectedUser.getId());
        }
        return selectedUserGraphics;
    }

    public void setSelectedUserGraphics(Graphics selectedUserGraphics) {
        this.selectedUserGraphics = selectedUserGraphics;
    }
    public SystemUser getSelectedUser() {
        if (selectedUser == null) {
            selectedUser = userManagment.getUserById(selectedUserId == null ? 1 : selectedUserId);
        }
        return selectedUser;
    }

    public void setSelectedUser(SystemUser selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Long getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(Long selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<SystemUser> getSystemUsers() {
        return userManagment.getSystemUserIsFree(selectedDate == null ? new Date() : selectedDate);
    }

    public String goToService(SystemUser user) throws IOException {
        setSelectedUser(user);
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/form/request/service.xhtml?faces-redirect=true");
        return "/form/request/service.xhtml?faces-redirect=true";
    }
    public void send() throws IOException {
        userManagment.updateGraphics(selectedUserGraphics);
        getRequest().setJournalist(getSystemUser());
        request.setOperator(selectedUser);
        request.setCreateDate(new Date());
        userManagment.addRequestType1(request);
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/form/service.xhtml?faces-redirect=true");
    }
}