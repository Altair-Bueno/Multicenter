package App.Multicenter.Platform;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class LanguageManagerTest {
    @Test
    public void English(){
        LanguageManager.setLanguage(LanguageManager.ENGLISH);
        Assert.assertEquals("test1" ,  LanguageManager.getString("test_key"));
    }

    @Test
    public void Spanish() {
        LanguageManager.setLanguage(LanguageManager.SPANISH);
        Assert.assertEquals("test2" ,  LanguageManager.getString("test_key"));
    }
    @Test
    public void Appname(){
        LanguageManager.setLanguage(LanguageManager.ENGLISH);
        Assert.assertNotNull(LanguageManager.getString("app_name"));
    }
    @Test
    public void LocaleCode(){
        LanguageManager.setLanguage(LanguageManager.ENGLISH);
        Assert.assertEquals(LanguageManager.getActualLocale() , LanguageManager.ENGLISH);

        LanguageManager.setLanguage(LanguageManager.SPANISH);
        Assert.assertEquals(LanguageManager.getActualLocale() , LanguageManager.SPANISH);
    }
}
