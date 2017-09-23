package view.kz.persistence.parentTemplate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.File;

@Entity
@Table(name = "IMAGE")
public class Image extends ENTITY{

    @Column(name = "IMAGE")
    private File img;

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }
}
