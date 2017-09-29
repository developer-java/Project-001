package view.kz.persistence.blank;

import javax.persistence.Entity;

@Entity
public class RequestType1Blank extends Blank {
    public RequestType1Blank() {
        blankType = BlankType.REQUEST_TYPE1;
    }
}
