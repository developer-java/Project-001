package view.kz.persistence;

import view.kz.persistence.common.Identifier;

import javax.persistence.*;

@Entity
@Table(name = "ORGANIZATION")
public class Organization extends Identifier {
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    @ManyToOne
    @JoinColumn(name = "HEAD")
    private SystemUser head;
    @ManyToOne
    @JoinColumn(name = "DEPUTY")
    private SystemUser deputy;
    private Address address;
}
