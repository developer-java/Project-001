package view.kz.persistence.blank;


import view.kz.persistence.common.AFileAttach;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BLANK")
public class Blank extends AFileAttach {
    @Column(name = "CREATE_DATE")
    protected Date createDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "BLANK_TYPE")
    protected BlankType blankType;
    @Column(name = "SIGNER_FIO")
    protected String signerFio;
    @Column(name = "XML")
    protected String xml;
    @Column(name = "IS_DELETED")
    protected Boolean deleted;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BlankType getBlankType() {
        return blankType;
    }

    public void setBlankType(BlankType blankType) {
        this.blankType = blankType;
    }

    public String getSignerFio() {
        return signerFio;
    }

    public void setSignerFio(String signerFio) {
        this.signerFio = signerFio;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String getRelativeFilePath() {
        return "blank/" + sdf.format(getCreateDate()) + "/" + super.getRelativeFilePath();
    }
}
