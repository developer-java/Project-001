package view.kz.web.AppController;

import view.kz.util.JSFFunctions;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

@ManagedBean(name = "localeModel")
@SessionScoped
public class LocaleModule {
    private static Locale ruLocale = new Locale(BundleManager.LOCALE_NAME_RU);
    private static Locale kkLocale = new Locale(BundleManager.LOCALE_NAME_KK);
    private static Locale enLocale = new Locale(BundleManager.LOCALE_NAME_EN);

    private Locale locale;

    public Locale getLocale() {
        if (locale == null) {
            if (BundleManager.LOCALE_NAME_RU.equals(JSFFunctions.getCookie("locale"))) {
                locale = ruLocale;
                return locale;
            }
            locale = ruLocale;  //TODO
        }
        return locale;
    }

    public boolean isKk() {
        return kkLocale.equals(locale);
    }

    public boolean isRu() {
        return ruLocale.equals(locale);
    }

    public boolean isEn() {
        return enLocale.equals(locale);
    }

    public String selectLanguage(String selectedLanguage) {
        if (BundleManager.LOCALE_NAME_RU.equals(selectedLanguage)) {
            locale = ruLocale;

        } else if (BundleManager.LOCALE_NAME_KK.equals(selectedLanguage)) {
            locale = kkLocale;
        } else if (BundleManager.LOCALE_NAME_EN.equals(selectedLanguage)) {
            locale = enLocale;
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        String params = getRedirectPage();
        if (params != null) {
            int ind = params.indexOf("?");
            if (ind >= 0) {
                params = params.substring(ind) + "&faces-redirect=true";
            } else {
                params = "?faces-redirect=true";
            }
        } else {
            params = "?faces-redirect=true";
        }
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + params;
    }

    public String selectLanguageWithoutRedirect(String selectedLanguage) {
        if (BundleManager.LOCALE_NAME_RU.equals(selectedLanguage)) {
            locale = ruLocale;

        } else if (BundleManager.LOCALE_NAME_KK.equals(selectedLanguage)) {
            locale = kkLocale;
        } else if (BundleManager.LOCALE_NAME_EN.equals(selectedLanguage)) {
            locale = enLocale;
        }
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        return null;
    }

    public static boolean IS_RU() {
        return ruLocale.equals(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public static boolean IS_EN() {
        return enLocale.equals(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    public static String getLocalized(String ru, String kk) {
        if (LocaleModule.IS_RU()) {
            return ru;
        } else if(LocaleModule.IS_EN()) {
            return ru;  //TODO
        } else {
            return kk;
        }
    }

    private String redirectPage;

    public String getRedirectPage() {
        return redirectPage;
    }

    public void setRedirectPage(String redirectPage) {
        this.redirectPage = redirectPage;
    }

    public static String getDicSortColumnName() {
        if (LocaleModule.IS_RU()) {
            return "valueRu";
        } else {
            return "valueKz";
        }
    }
}
