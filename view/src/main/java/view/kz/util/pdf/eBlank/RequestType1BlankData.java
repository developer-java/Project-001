package view.kz.util.pdf.eBlank;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("data")
public class RequestType1BlankData extends ABlankData {
    @XStreamAlias("f4")
    private String court;
    @XStreamAlias("f5")
    private String district;
    @XStreamAlias("f6")
    private String declarant;
    @XStreamAlias("f7")
    private String core;
    @XStreamImplicit(itemFieldName="file")
    private List<String> files;

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDeclarant() {
        return declarant;
    }

    public void setDeclarant(String declarant) {
        this.declarant = declarant;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public List<String> getFiles() {
        if(files == null) {
            files = new ArrayList<String>();
        }
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
