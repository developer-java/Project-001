package view.kz.persistence.parentTemplate;

import view.kz.persistence.SystemUser;
import view.kz.persistence.common.Identifier;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Request extends Identifier{
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "FINISH_DATE")
    private Date finishDate;

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
