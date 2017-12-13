package view.kz.persistence.types;

public enum PositionTypes {
    OPERATOR("postion.operator"),JOURNALIST("postion.journalist"),INSTALLER("postion.produser"),PRODUCER("postion.installer");

    private String bundleName;

    private PositionTypes(String bundleName){
        this.bundleName = bundleName;
    }

    public String getBundleName() {
        return bundleName;
    }
}
