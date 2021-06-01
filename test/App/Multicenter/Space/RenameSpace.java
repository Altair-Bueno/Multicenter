package App.Multicenter.Space;

import App.Multicenter.Preferences.Preferences;
import App.Multicenter.Widget.AbstractWidget;
import App.Multicenter.Widget.NotesWidget;
import App.Multicenter.Widget.Widget;
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
