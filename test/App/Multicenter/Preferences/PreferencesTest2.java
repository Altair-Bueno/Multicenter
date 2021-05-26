package App.Multicenter.Preferences;

import java.util.Locale;

public class PreferencesTest2 {
    public static void main(String[] args) {
        Preferences.loadPreferences();
        System.out.println("LOADED: " + Preferences.getPropObject().toString());

        int tema = Preferences.getTheme();
        System.out.println("TEMA CARGADO: " + tema);

        String idioma = Preferences.getLanguage();
        System.out.println("IDIOMA CARGADO: " + idioma);

        if(idioma.equals("es")){
            Preferences.setLanguage("en");
            System.out.println("CHANGED LANGUAGE TO ENGLISH: " + Preferences.getPropObject().toString());
        } else {
            Preferences.setLanguage("es");
            System.out.println("CHANGED LANGUAGE TO SPANISH: " + Preferences.getPropObject().toString());
            System.out.println(Preferences.getLanguage());
        }

        Preferences.save();
    }
}
