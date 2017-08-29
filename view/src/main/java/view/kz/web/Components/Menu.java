package view.kz.web.Components;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Item> menuItems;
    private String title;
    private String styleClass;

    public Menu(String title) {
        this.menuItems = new ArrayList<>();
        this.title = title;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public List<Item> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Item> menuItems) {
        this.menuItems = menuItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
