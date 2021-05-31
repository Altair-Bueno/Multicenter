package App.Multicenter.Space;

import App.Multicenter.Preferences.Preferences;

import java.io.File;

public class RandomNameGeneratorTest {
    public static void main(String[] args) {

        RandomNameGenerator rng = new RandomNameGenerator();
        String id = rng.generate(new File(System.getProperty("user.home")));
        System.out.println("GENERATED NAME: " + id);

        Preferences.loadPreferences();
        PersonalSpace espacio = new PersonalSpace("Test", Preferences.getSpacesFolder());
        System.out.println("ESPACIO CREADO CON ID: " + espacio.getId());
    }
}
