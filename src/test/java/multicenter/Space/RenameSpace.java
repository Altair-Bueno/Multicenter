package multicenter.space;

import app.multicenter.preferences.Preferences;
import app.multicenter.space.PersonalSpace;
import app.multicenter.widget.AbstractWidget;
import app.multicenter.widget.NotesWidget;
import app.multicenter.widget.Widget;
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
