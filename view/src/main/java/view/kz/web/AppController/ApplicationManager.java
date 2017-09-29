package view.kz.web.AppController;

import view.kz.util.Configuration;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.ByteArrayOutputStream;
import java.util.*;

@ManagedBean(name = "applicationModel")
@ApplicationScoped
public class ApplicationManager {
    private TimeZone timeZone = TimeZone.getDefault();
    private static String applicationUrl;
    private static String serverUrlForEaias;
    private static String systemAlert;
    private static List<String> specUsersIinList;
    private static List<String> avfUsersIinList;
    private static String fileContainerPath;
    private static String proxyIp;
    private static Integer proxyPort;
    private static Map<String, String[]> avfParam;
    private static Map<String, String> hearingBaseUrlMap;
    private static List<String> lawyersIinList;
    private static List<String> avfHosts;
    private static String appStoreLink;
    private static String googlePlayLink;
    private static String winAppLink;
    private static Boolean findLawsuitTechWorks;
    private static Boolean useLocalIp;
    private static String jabberUrl;
    private static String lawsuitFindMsg;
    private static List<String> trustedReportUserXinList;
    private static Boolean useFindReCaptcha;
    private static Boolean hideSocialTest;
    private static Boolean useNewTorelikService;
    private static List<String> accessHostList;
    private static Boolean checkCertCrl;
    private static String tumSocketApiKey;

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public static String APPLICATION_URL() throws Exception {
        if(applicationUrl == null) {
            applicationUrl = Configuration.getInstance().getValueByParam("applicationUrl");
            if(applicationUrl == null) {
                applicationUrl = "http://127.0.0.1";
            }
            applicationUrl = applicationUrl + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        }
        return applicationUrl;
    }

    public static String SERVER_URL_FOR_EAIAS() {
        if(serverUrlForEaias == null) {
            serverUrlForEaias = Configuration.getInstance().getValueByParam("serverUrlForEaias");
            if(serverUrlForEaias == null) {
                serverUrlForEaias = "http://127.0.0.1";
            }
            serverUrlForEaias = serverUrlForEaias + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        }
        return serverUrlForEaias;
    }

    public String getApplicationUrl() throws Exception {
        return APPLICATION_URL();
    }

    public String getSystemAlert() {
        if(systemAlert == null) {
            systemAlert = Configuration.getInstance().getValueByParam("systemAlert");
            if(systemAlert == null) {
                systemAlert = "";
            }
        }
        return systemAlert;
    }


    public static List<String> getSpecUsersIinList() {
        if(specUsersIinList == null) {
            specUsersIinList = new ArrayList<String>();
            String iin = Configuration.getInstance().getValueByParam("specUsersIin");
            if(iin != null && !iin.isEmpty()) {
                specUsersIinList.addAll(Arrays.asList(iin.split(";")));
            }
        }
        return specUsersIinList;
    }

    public static boolean checkSpecUser(String iin) {
        return getSpecUsersIinList().contains(iin);
    }

    public static List<String> getAvfUsersIinList() {
        if(avfUsersIinList == null) {
            avfUsersIinList = new ArrayList<String>();
            String iin = Configuration.getInstance().getValueByParam("avfUsersIin");
            if(iin != null && !iin.isEmpty()) {
                avfUsersIinList.addAll(Arrays.asList(iin.split(";")));
            }
        }
        return avfUsersIinList;
    }

    public static boolean checkAvfUser(String iin) {
        return getAvfUsersIinList().contains(iin);
    }


    public static String getFileContainerPath() {
        if(fileContainerPath == null) {
            fileContainerPath = Configuration.getInstance().getValueByParam("fileContainerPath");
        }
        return fileContainerPath;
    }

    public static String PROXY_IP() {
        if(proxyIp == null) {
            proxyIp = Configuration.getInstance().getValueByParam("proxyIp");
            if(proxyIp == null) {
                proxyIp = "";
            }
        }
        return proxyIp;
    }

    public static Integer PROXY_PORT() {
        if(proxyPort == null) {
            String value = Configuration.getInstance().getValueByParam("proxyPort");
            if(value == null || value.isEmpty()) {
                proxyPort = 80;
            }
            else {
                proxyPort = Integer.valueOf(value);
            }
        }
        return proxyPort;
    }

    public static Map<String, String[]> getAvfParam() {
        if(avfParam == null) {
            avfParam = new HashMap<String, String[]>();
            String avfParam = Configuration.getInstance().getValueByParam("avfParam");
            if(avfParam != null && !avfParam.isEmpty()) {
                String[] parts = avfParam.split(";");
                if(parts != null && parts.length > 0) {
                    for(String part : parts) {
                        String[] params = part.split(",");
                        if(params != null && params.length == 3) {
                            ApplicationManager.avfParam.put(params[0], new String[]{params[1], params[2]});
                        }
                    }
                }
            }
        }
        return avfParam;
    }

    public static String getHearingBaseUrl(String key) {
        if(hearingBaseUrlMap == null) {
            hearingBaseUrlMap = new HashMap<String, String>();
        }
        String baseUrl = hearingBaseUrlMap.get(key);
        if(baseUrl == null) {
            baseUrl = Configuration.getInstance().getValueByParam(key);
            if(baseUrl == null) {
                baseUrl = key;
            }
            hearingBaseUrlMap.put(key, baseUrl);
        }
        return baseUrl;
    }
    private static final String ACCEPTED_TYPES = ".pdf,.doc,.docx,.jpeg,.jpg,.tif,.tiff";

    public String getAcceptedTypes() {
        return ACCEPTED_TYPES;
    }

    public static List<String> getLawyersIinList() {
        if(lawyersIinList == null) {
            lawyersIinList = new ArrayList<String>();
            String iin = Configuration.getInstance().getValueByParam("lawyersIin");
            if(iin != null && !iin.isEmpty()) {
                lawyersIinList.addAll(Arrays.asList(iin.split(";")));
            }
        }
        return lawyersIinList;
    }

    public static boolean checkLawyerUser(String iin) {
        return getLawyersIinList().contains(iin);
    }

    public static List<String> getAvfHosts() {
        if(avfHosts == null) {
            avfHosts = new ArrayList<String>();
            String avfHostsStr = Configuration.getInstance().getValueByParam("avfHosts");
            if(avfHostsStr != null && !avfHostsStr.isEmpty()) {
                avfHosts.addAll(Arrays.asList(avfHostsStr.split(";")));
            }
        }
        return avfHosts;
    }

    public String getAvfHostsForJs() {
        StringBuilder sb = new StringBuilder();
        for(String host : getAvfHosts()) {
            if(sb.length() > 0) {
                sb.append(",");
            }
            sb.append("'").append(host).append("'");
        }
        sb.insert(0, "[").append("]");
        return sb.toString();
    }

    public String getAppStoreLink() {
        if(appStoreLink == null) {
            appStoreLink = Configuration.getInstance().getValueByParam("appStoreLink");
        }
        return appStoreLink;
    }

    public String getGooglePlayLink() {
        if(googlePlayLink == null) {
            googlePlayLink = Configuration.getInstance().getValueByParam("googlePlayLink");
        }
        return googlePlayLink;
    }

    public String getWinAppLink() {
        if(winAppLink == null) {
            winAppLink = Configuration.getInstance().getValueByParam("winAppLink");
        }
        return winAppLink;
    }

    public boolean getFindLawsuitTechWorks() {
        if(findLawsuitTechWorks == null) {
            findLawsuitTechWorks = Configuration.getInstance().getValueByParam("findLawsuitTechWorks") != null;
        }
        return findLawsuitTechWorks;
    }

    public boolean isUseLocalIp() {
        if(useLocalIp == null) {
            useLocalIp = Configuration.getInstance().getValueByParam("useLocalIp") != null;
        }
        return useLocalIp;
    }

    public static String JABBER_URL() {
        if(jabberUrl == null) {
            jabberUrl = Configuration.getInstance().getValueByParam("JABBER_SERVER_URL");
        }
        return jabberUrl;
    }

    public String getJabberUrl() {
        return JABBER_URL();
    }

    public String getLawsuitFindMsg() {
        if(lawsuitFindMsg == null) {
            lawsuitFindMsg = Configuration.getInstance().getValueByParam("LAWSUIT_FIND_MSG");
        }
        return lawsuitFindMsg;
    }

    public static List<String> getTrustedReportUserXinList() {
        if(trustedReportUserXinList == null) {
            trustedReportUserXinList = new ArrayList<String>();
            String iin = Configuration.getInstance().getValueByParam("trustedReportUserXin");
            if(iin != null && !iin.isEmpty()) {
                trustedReportUserXinList.addAll(Arrays.asList(iin.split(";")));
            }
        }
        return trustedReportUserXinList;
    }

    public static boolean checkTrustedReportUser(String xin) {
        return getTrustedReportUserXinList().contains(xin);
    }

    public static Boolean IS_USE_FIND_RECAPTCHA() {
        if(useFindReCaptcha == null) {
            useFindReCaptcha = Configuration.getInstance().getValueByParam("useFindReCaptcha") != null;
        }
        return useFindReCaptcha;
    }

    public boolean isUseFindReCaptcha() {
        return IS_USE_FIND_RECAPTCHA();
    }

    public Boolean getHideSocialTest() {
        if(hideSocialTest == null) {
            hideSocialTest = Configuration.getInstance().getValueByParam("hideSocialTest") != null;
        }
        return hideSocialTest;
    }

    public static Boolean IS_USE_NEW_TORELIK_SERVICE() {
        return true;
    }

    public static Boolean IS_USE_NEW_TORELIK_SERVICE_FOR_ADM() {
        if(useNewTorelikService == null) {
            useNewTorelikService = Configuration.getInstance().getValueByParam("useNewTorelikService") != null;
        }
        return useNewTorelikService;
    }

    public static List<String> getAccessHostList() {
        if(accessHostList == null) {
            String accessHostListStr = Configuration.getInstance().getValueByParam("accessHostList");
            if(accessHostListStr == null) {
                accessHostList = new ArrayList<String>();
            }
            else {
                accessHostList = Arrays.asList(accessHostListStr.split(";"));
            }
        }
        return accessHostList;
    }

    public static Boolean IS_CHECK_CERT_CRL() {
        if(checkCertCrl == null) {
            checkCertCrl = Configuration.getInstance().getValueByParam("checkCertCrl") != null;
        }
        return checkCertCrl;
    }

    public String getTumSocketApiKey() {
        if(tumSocketApiKey == null) {
            tumSocketApiKey = Configuration.getInstance().getValueByParam("tumSocketApiKey");
        }
        return tumSocketApiKey;
    }

    public String getBuild() {
        return "20170815";
    }

    public static Long TIME_AS_LONG() {
        return new Date().getTime();
    }

    public Long getTimeAsLong() {
        return TIME_AS_LONG();
    }
}
