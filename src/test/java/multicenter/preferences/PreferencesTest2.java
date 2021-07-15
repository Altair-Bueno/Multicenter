package multicenter.preferences;

import app.multicenter.preferences.Preferences;

public class PreferencesTest2 {
    public static void main(String[] args) {
        Preferences.loadPreferences();
        System.out.println("LOADED: " + Preferences.getPropObject().toString());

        int tema = Preferences.getTheme();
        System.out.println("TEMA CARGADO: " + tema);

        String idioma = Preferences.getLanguage();
        System.out.println("IDIOMA CARGADO: " + idioma);


        if (idioma.equals("es-ES")) {
            Preferences.setLanguage("en");
            System.out.println("CHANGED LANGUAGE TO ENGLISH: " + Preferences.getPropObject().toString());
        } else {
            Preferences.setLanguage("es");
            System.out.println("CHANGED LANGUAGE TO SPANISH: " + Preferences.getPropObject().toString());
        }

        Preferences.save();
    }
}
