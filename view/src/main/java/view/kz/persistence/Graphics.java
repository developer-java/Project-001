package view.kz.persistence;

import view.kz.persistence.common.Identifier;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "GRAPHICS")
public class Graphics extends Identifier {
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private SystemUser user;
    @Column(name = "A08")
    private Boolean A08_00_09_00;
    @Column(name = "A09")
    private Boolean A09_00_10_00;
    @Column(name = "A10")
    private Boolean A10_00_11_00;
    @Column(name = "A11")
    private Boolean A11_00_12_00;
    @Column(name = "A12")
    private Boolean A12_00_13_00;
    @Column(name = "A13")
    private Boolean A13_00_14_00;
    @Column(name = "A14")
    private Boolean A14_00_15_00;
    @Column(name = "A15")
    private Boolean A15_00_16_00;
    @Column(name = "A16")
    private Boolean A16_00_17_00;
    @Column(name = "A17")
    private Boolean A17_00_18_00;
    @Column(name = "A18")
    private Boolean A18_00_19_00;
    @Column(name = "A19")
    private Boolean A19_00_20_00;
    @Column(name = "A20")
    private Boolean A20_00_21_00;
    @Column(name = "A21")
    private Boolean A21_00_22_00;
    @Column(name = "A22")
    private Boolean A22_00_23_00;

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public Boolean getA08_00_09_00() {
        return A08_00_09_00;
    }

    public void setA08_00_09_00(Boolean a08_00_09_00) {
        A08_00_09_00 = a08_00_09_00;
    }

    public Boolean getA09_00_10_00() {
        return A09_00_10_00;
    }

    public void setA09_00_10_00(Boolean a09_00_10_00) {
        A09_00_10_00 = a09_00_10_00;
    }

    public Boolean getA10_00_11_00() {
        return A10_00_11_00;
    }

    public void setA10_00_11_00(Boolean a10_00_11_00) {
        A10_00_11_00 = a10_00_11_00;
    }

    public Boolean getA11_00_12_00() {
        return A11_00_12_00;
    }

    public void setA11_00_12_00(Boolean a11_00_12_00) {
        A11_00_12_00 = a11_00_12_00;
    }

    public Boolean getA12_00_13_00() {
        return A12_00_13_00;
    }

    public void setA12_00_13_00(Boolean a12_00_13_00) {
        A12_00_13_00 = a12_00_13_00;
    }

    public Boolean getA13_00_14_00() {
        return A13_00_14_00;
    }

    public void setA13_00_14_00(Boolean a13_00_14_00) {
        A13_00_14_00 = a13_00_14_00;
    }

    public Boolean getA14_00_15_00() {
        return A14_00_15_00;
    }

    public void setA14_00_15_00(Boolean a14_00_15_00) {
        A14_00_15_00 = a14_00_15_00;
    }

    public Boolean getA15_00_16_00() {
        return A15_00_16_00;
    }

    public void setA15_00_16_00(Boolean a15_00_16_00) {
        A15_00_16_00 = a15_00_16_00;
    }

    public Boolean getA16_00_17_00() {
        return A16_00_17_00;
    }

    public void setA16_00_17_00(Boolean a16_00_17_00) {
        A16_00_17_00 = a16_00_17_00;
    }

    public Boolean getA17_00_18_00() {
        return A17_00_18_00;
    }

    public void setA17_00_18_00(Boolean a17_00_18_00) {
        A17_00_18_00 = a17_00_18_00;
    }

    public Boolean getA18_00_19_00() {
        return A18_00_19_00;
    }

    public void setA18_00_19_00(Boolean a18_00_19_00) {
        A18_00_19_00 = a18_00_19_00;
    }

    public Boolean getA19_00_20_00() {
        return A19_00_20_00;
    }

    public void setA19_00_20_00(Boolean a19_00_20_00) {
        A19_00_20_00 = a19_00_20_00;
    }

    public Boolean getA20_00_21_00() {
        return A20_00_21_00;
    }

    public void setA20_00_21_00(Boolean a20_00_21_00) {
        A20_00_21_00 = a20_00_21_00;
    }

    public Boolean getA21_00_22_00() {
        return A21_00_22_00;
    }

    public void setA21_00_22_00(Boolean a21_00_22_00) {
        A21_00_22_00 = a21_00_22_00;
    }

    public Boolean getA22_00_23_00() {
        return A22_00_23_00;
    }

    public void setA22_00_23_00(Boolean a22_00_23_00) {
        A22_00_23_00 = a22_00_23_00;
    }

    @Transient
    private HashMap<String,Boolean> grapthics;
    @Transient
    public HashMap<String,Boolean> getGraphics(){
        if(grapthics==null){
            grapthics = new HashMap<>();
            grapthics.put("08",getA08_00_09_00());
            grapthics.put("09",getA09_00_10_00());
            grapthics.put("10",getA10_00_11_00());
            grapthics.put("11",getA11_00_12_00());
            grapthics.put("12",getA12_00_13_00());
            grapthics.put("13",getA13_00_14_00());
            grapthics.put("14",getA14_00_15_00());
            grapthics.put("15",getA15_00_16_00());
            grapthics.put("16",getA16_00_17_00());
            grapthics.put("17",getA17_00_18_00());
            grapthics.put("18",getA18_00_19_00());
            grapthics.put("19",getA19_00_20_00());
            grapthics.put("20",getA20_00_21_00());
            grapthics.put("21",getA21_00_22_00());
            grapthics.put("22",getA22_00_23_00());
        }
        return grapthics;
    }
}
