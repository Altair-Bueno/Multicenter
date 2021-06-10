package multicenter.Space;

import app.multicenter.Preferences.Preferences;
import app.multicenter.Space.PersonalSpace;
import app.multicenter.Widget.AbstractWidget;
import app.multicenter.Widget.NotesWidget;
import app.multicenter.Widget.Widget;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class RenameSpace {
    @Test
    public void rename() throws IOException {
        Preferences.loadPreferences();

        System.out.println("Spaces folder " + Preferences.getSpacesFolder());
        PersonalSpace personalSpace = new PersonalSpace("Test", Preferences.getSpacesFolder());

        Widget widget = new NotesWidget(personalSpace.getCarpeta());
        personalSpace.addWidget(widget);
        personalSpace.movePersonalSpacesToFolder(new File("/Users/compux72/Downloads/TestMulticenter"));
        ((AbstractWidget) widget).close();
    }
}
