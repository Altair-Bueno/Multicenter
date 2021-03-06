package multicenter.preferences;

import app.multicenter.platform.LanguageManager;
import app.multicenter.platform.ThemeManager;
import app.multicenter.preferences.Preferences;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.File;

public class PreferencesTest {
    @Test
    public void def() {
        Preferences.loadPreferences();
        Preferences.setLanguage(LanguageManager.DEFAULT);
        Assert.assertEquals(LanguageManager.DEFAULT, Preferences.getLanguage());
        Assert.assertEquals(ThemeManager.LIGHT, Preferences.getTheme());
        Assert.assertEquals(new File(System.getProperty("user.home"), "Multicenter Files"), Preferences.getSpacesFolder());
        Assert.assertEquals(new Dimension(800, 800), Preferences.getWindowsSize());
        Preferences.save();
    }
}
