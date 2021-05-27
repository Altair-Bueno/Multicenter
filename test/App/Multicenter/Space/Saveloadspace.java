package App.Multicenter.Space;

import App.Multicenter.Buddy.XMLBuddy;
import App.Multicenter.Preferences.Preferences;
import App.Multicenter.Widget.NotesWidget;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Saveloadspace {
    @Before
    public void p(){
        Preferences.loadPreferences();
        Preferences.setSpacesFolder(new File("/Users/compux72/Downloads/TestMulticenter"));
    }
    @Test
    public void t1() throws IOException {
        PersonalSpace ps = new PersonalSpace();
        NotesWidget notes = new NotesWidget();
        notes.setAlignmentX(23);
        notes.toggleEditMode();
        notes.setSize(new Dimension(20,3));
        notes.setLayer(3000);
        notes.setId("holaqtal");
        ps.addWidget(notes);
        ps.setCarpeta(new File("test"));
        XMLBuddy<PersonalSpace> xm = new XMLBuddy<>();
        //Gson gson = new Gson();
        //gson.toJson(ps,new FileWriter(new File(Preferences.getSpacesFolder(),"Test.json")));
        xm.saveToFile(new File(Preferences.getSpacesFolder(),"Test.xml"),ps);

    }
}
