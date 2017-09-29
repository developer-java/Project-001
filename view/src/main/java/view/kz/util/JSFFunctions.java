package view.kz.util;


import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;
import view.kz.web.AppController.BundleManager;
import view.kz.web.AppController.LocaleModule;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSFFunctions {
    public static String base64Encode(String str) throws UnsupportedEncodingException {
        if(str == null) {
            return null;
        }
        return Base64.encode(str.getBytes("UTF-8"));
    }

    public static String base64UriEncode(String str) throws UnsupportedEncodingException {
        if(str == null) {
            return null;
        }
        return URLEncoder.encode(Base64.encode(str.getBytes("UTF-8")), "UTF-8");
    }

    public static String uriEncode(String str) throws UnsupportedEncodingException {
        if(str == null) {
            return null;
        }
        return URLEncoder.encode(str, "UTF-8");
    }

    public static String base64Decode(String str) throws UnsupportedEncodingException, Base64DecodingException, org.apache.xml.security.exceptions.Base64DecodingException {
        if(str == null) {
            return null;
        }
        return new String(Base64.decode(str), "UTF-8");
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, BundleManager.getInterface(msg), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static String getCookie(String name) {
        try {
            return ((Cookie)FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get(name)).getValue();
        }
        catch(NullPointerException ne) {}
        catch(Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static String getDateAsString(Object date) {
        if(date != null) {
            if(date instanceof XMLGregorianCalendar) {
                date = Util.gregorianCalendarToDateTime((XMLGregorianCalendar)date);
            }
            if(date instanceof Date) {
                return new SimpleDateFormat("dd.MM.yyyy").format(date);
            }
        }
        return null;
    }

    public static String getDateTimeAsString(Object date) {
        if(date != null) {
            if(date instanceof XMLGregorianCalendar) {
                date = Util.gregorianCalendarToDateTime((XMLGregorianCalendar)date);
            }
            if(date instanceof Date) {
                return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);
            }
        }
        return null;
    }

    public static String getLocalizedString(String valueRu, String valueKz) {
        return LocaleModule.getLocalized(valueRu, valueKz);
    }
}
