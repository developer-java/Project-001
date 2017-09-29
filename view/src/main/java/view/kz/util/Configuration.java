package view.kz.util;


import view.kz.ejb.SettingManagement;
import view.kz.persistence.SystemSetting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {
    private static Configuration configuration = new Configuration();
    private Configuration() {}

    public static synchronized Configuration getInstance() {
        return configuration;
    }

    private SettingManagement settingManagementEjb;

    private SettingManagement getSettingManagementEjb() {
        if(settingManagementEjb == null) {
            settingManagementEjb = EjbLocator.lookUp(SettingManagement.class);
        }
        return settingManagementEjb;
    }

    private static Map<String, String> SETTINGS;

    private Map<String, String> getSettings() {
        if(SETTINGS == null) {
            SETTINGS = new HashMap<String, String>();
            List<SystemSetting> settingList = getSettingManagementEjb().getSettings();
            for(SystemSetting param : settingList) {
                SETTINGS.put(param.getParam(), param.getValue());
            }
        }
        return SETTINGS;
    }

    public String getValueByParam(String param) {
        return getSettings().get(param);
    }

    public void refreshSettings() {
        SETTINGS = null;
    }
}
