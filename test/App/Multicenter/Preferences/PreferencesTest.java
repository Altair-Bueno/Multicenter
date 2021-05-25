package App.Multicenter.Preferences;

import App.Multicenter.Platform.LanguageManager;
import App.Multicenter.Platform.ThemeManager;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.File;

public class PreferencesTest {
    @Test
    public void def(){
        Preferences.loadPreferences();
        Assert.assertEquals(LanguageManager.DEFAULT,Preferences.getLanguage());
        Assert.assertEquals(ThemeManager.LIGHT,Preferences.getTheme());
        Assert.assertEquals(new File(System.getProperty("user.dir")),Preferences.getSpacesFolder());
        Assert.assertEquals(new Dimension(800,800),Preferences.getWindowsSize());
        Preferences.save();
    }
/*
    @Before
    public void save(){
        Preferences.loadPreferences();
        Preferences.setLanguage("es");
        Preferences.save();
    }

 */
}
