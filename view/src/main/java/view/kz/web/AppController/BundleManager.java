package view.kz.web.AppController;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class BundleManager {

    public static final String LOCALE_NAME_RU = "ru";
    public static final String LOCALE_NAME_KK = "kaz";
    public static final String LOCALE_NAME_EN = "eng";

    public static String getInterface(String key){
        return ResourceBundle.getBundle("bundle/strings", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString(key);
    }

    public static String getInterface(String key, String locale){
        return ResourceBundle.getBundle("bundle/strings", new Locale(locale)).getString(key);
    }

    public static String getInterface(String key, String locale, String... params){
        return MessageFormat.format(ResourceBundle.getBundle("bundle/strings", new Locale(locale)).getString(key), params);
    }

    public static String getMessage(String key){
        return ResourceBundle.getBundle("/jsf/jsf-messages", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString(key);
    }
}
