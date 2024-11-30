package com.notepad.settings;

import java.util.prefs.Preferences;

public class PreferencesManager {
    private static PreferencesManager instance;
    private Preferences prefs;

    private static final String FONT_SIZE_KEY = "font_size";
    private static final String THEME_KEY = "theme";

    private PreferencesManager(){
        prefs = Preferences.userNodeForPackage(PreferencesManager.class);
    }

    public static PreferencesManager getInstance(){
        if(instance == null){
            instance = new PreferencesManager();
        }

        return instance;
    }

    public int getFontSize(){
        return prefs.getInt(FONT_SIZE_KEY, 12);
    }

    public void setFontSize(int fontSize){
        prefs.putInt(FONT_SIZE_KEY, fontSize);
    }

    public String getTheme(){
        return prefs.get(THEME_KEY, "Light");
    }

    public void setTheme(String theme){
        prefs.put(THEME_KEY, theme);
    }
}
