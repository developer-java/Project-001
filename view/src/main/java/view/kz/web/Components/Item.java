package view.kz.web.Components;

public class Item {

    public Item(String text, String link, String img) {
        this.text = text;
        this.link = link;
        this.img = img;
    }

    private String text;
    private String link;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
