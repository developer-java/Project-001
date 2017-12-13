package view.kz.persistence;

import view.kz.persistence.parentTemplate.Request;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REQUEST_TYPE_1")
public class RequestType1 extends Request {
    @ManyToOne
    @JoinColumn(name = "J_USER_ID")
    private SystemUser journalist;
    @ManyToOne
    @JoinColumn(name = "O_USER_ID")
    private SystemUser operator;

    public SystemUser getJournalist() {
        return journalist;
    }

    public void setJournalist(SystemUser journalist) {
        this.journalist = journalist;
    }

    public SystemUser getOperator() {
        return operator;
    }

    public void setOperator(SystemUser operator) {
        this.operator = operator;
    }
}
