package multicenter.space;

import app.multicenter.buddy.XMLBuddy;
import app.multicenter.preferences.Preferences;
import app.multicenter.space.PersonalSpace;
import app.multicenter.space.PersonalSpaceData;
import app.multicenter.widget.NotesWidget;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class TestPersonalSpace {
    @Test
    public void testeo() throws FileNotFoundException {
        Preferences.loadPreferences();

        PersonalSpace ps = new PersonalSpace("Test", Preferences.getSpacesFolder());

        ps.addWidget(new NotesWidget(ps.getCarpeta()));
        ps.addWidget(new NotesWidget(ps.getCarpeta()));

        PersonalSpace ps2 = new PersonalSpace("Test", Preferences.getSpacesFolder());
        ps2.addWidget(new NotesWidget(ps2.getCarpeta()));

        HashSet<PersonalSpaceData> set = new HashSet<>();

        set.add(ps2.getPersonalSpaceDataInstance());
        set.add(ps.getPersonalSpaceDataInstance());


        XMLBuddy<HashSet<PersonalSpaceData>> xml = new XMLBuddy<>();

        xml.saveToFile(Preferences.getSpacesSaveFile(), set);


        Set<PersonalSpaceData> personalSpaceData = xml.readFromFile(Preferences.getSpacesSaveFile());

        Set<PersonalSpace> nuevo = personalSpaceData.stream().map(PersonalSpace::loadPersonalSpaces).collect(Collectors.toSet());
        System.out.println(nuevo);
        System.out.println(set);

    }
}
