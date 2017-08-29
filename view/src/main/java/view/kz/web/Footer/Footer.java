package view.kz.web.Footer;

import view.kz.web.AppController.BundleManager;
import view.kz.web.Components.Item;
import view.kz.web.Components.Menu;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "footer")
@ViewScoped
public class Footer {
    List<Menu> footerPanel = new ArrayList<>();
    Style style = new Style();

    public List<Menu> getFooterPanels(){
        footerPanel.clear();
        Menu m = new Menu(BundleManager.getInterface("footerPanel1Title"));
        m.getMenuItems().add(new Item("Библиотека","http://library.kazatu.kz/jirbis2/index.php/ru/",""));
        m.getMenuItems().add(new Item("Портал Казату","http://portal.kazatu.kz/",""));
        m.getMenuItems().add(new Item("КазАТУ","http://kazatu.kz/",""));
        m.getMenuItems().add(new Item("Платонус","http://platonus.kazatu.kz/",""));
        footerPanel.add(m);
        m = new Menu(BundleManager.getInterface("footerPanel2Title"));
        m.getMenuItems().add(new Item("О нас","http://vtipo.ru/","ads"));
        footerPanel.add(m);
        m = new Menu(BundleManager.getInterface("footerPanel3Title"));
        m.getMenuItems().add(new Item("+7 707 1050996","#phone",""));
        m.getMenuItems().add(new Item("+7 777 7777777","#phone",""));
        footerPanel.add(m);
        m = new Menu(BundleManager.getInterface("footerPanel4Title"));
        m.getMenuItems().add(new Item("Что это за Сайт","#site",""));
        m.getMenuItems().add(new Item("История построении комании","#history",""));
        m.getMenuItems().add(new Item("Наши партнеры","#pattern",""));
        m.getMenuItems().add(new Item("Спонсоры","#sponsor",""));
        footerPanel.add(m);

        return footerPanel;
    }
    public Style getStyle() {
        return style;
    }
    public class Style{

        private String panelWidth;

        public String getPanelWidth() {
            panelWidth = (100/footerPanel.size())+"%";
            return panelWidth;
        }

        public void setPanelWidth(String panelWidth) {
            this.panelWidth = panelWidth;
        }
    }
}
