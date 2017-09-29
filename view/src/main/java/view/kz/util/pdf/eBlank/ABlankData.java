package view.kz.util.pdf.eBlank;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.DomDriver;
import view.kz.persistence.blank.Blank;

public abstract class ABlankData {
    @XStreamAlias("f1")
    private String number;
    @XStreamAlias("f2")
    private String date;
    @XStreamAlias("f3")
    private String xin;
    @XStreamOmitField
    private String qrData;
    @XStreamOmitField
    private String signerFio;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getXin() {
        return xin;
    }

    public void setXin(String xin) {
        this.xin = xin;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public String getSignerFio() {
        return signerFio;
    }

    public void setSignerFio(String signerFio) {
        this.signerFio = signerFio;
    }

    public static <T extends ABlankData> T getBlankData(Blank blank, Class<T> blankDataClass) {
        try {
            if(blank == null || blank.getXml() == null) {
                return blankDataClass.newInstance();
            }
            else {
                XStream xStream = new XStream(new DomDriver());
                xStream.processAnnotations(blankDataClass);
                xStream.ignoreUnknownElements();
                return blankDataClass.cast(xStream.fromXML(blank.getXml()));
            }
        }
        catch(InstantiationException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        catch(IllegalAccessException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <T extends ABlankData> String getXml(T blankData) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(blankData.getClass());
        return xStream.toXML(blankData);
    }

    public static <T extends ABlankData> String clearBlankDataXml(Blank blank, Class<T> blankDataClass) {
        T blankData = getBlankData(blank, blankDataClass);
        return getXml(blankData);
    }
}
