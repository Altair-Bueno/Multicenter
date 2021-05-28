package App.Multicenter.Space;

import App.Multicenter.Widget.CountdownWidget;
import App.Multicenter.Widget.EmbeddedWidget;
import App.Multicenter.Widget.VoicenoteWidget;
import org.junit.Before;
import org.junit.Test;
import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Preferences.Preferences;

import java.util.Arrays;
import java.util.Date;


public class TestPersonalSpace {
    @Test
    public void testeo(){
        Preferences.loadPreferences();
        PersonalSpace p = new PersonalSpace(Preferences.getSpacesFolder());
        p.addWidget(new VoicenoteWidget());
        p.addWidget(new CountdownWidget(new Date(), "pruebaEvento"));
    }
}
