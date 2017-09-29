package view.kz.persistence;

import view.kz.persistence.common.Identifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCEPT")
public class Accept extends Identifier {
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "URL")
    private String url;
    @Column(name = "TYPE")
    private AcceptType type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AcceptType getType() {
        return type;
    }

    public void setType(AcceptType type) {
        this.type = type;
    }

    public static enum AcceptType{
        EDUCATIONAL,
        STATE,
        PRIVATE;
    }

}

