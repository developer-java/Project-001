package view.kz.persistence.blank;

import view.kz.util.pdf.eBlank.RequestType1BlankData;

public enum BlankType {
    REQUEST_TYPE1(RequestType1BlankData.class),
    ;

    private Class blankDataClass;

    public Class getBlankDataClass() {
        return blankDataClass;
    }

    private BlankType(Class blankDataClass) {
        this.blankDataClass = blankDataClass;
    }
}
