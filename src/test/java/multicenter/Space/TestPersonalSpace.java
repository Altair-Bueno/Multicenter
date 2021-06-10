package multicenter.Space;

import app.multicenter.Buddy.XMLBuddy;
import app.multicenter.Preferences.Preferences;
import app.multicenter.Space.PersonalSpace;
import app.multicenter.Space.PersonalSpaceData;
import app.multicenter.Widget.NotesWidget;
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

        Set<PersonalSpaceData> set = new HashSet<>();

        set.add(ps2.getPersonalSpaceDataInstance());
        set.add(ps.getPersonalSpaceDataInstance());


        XMLBuddy<Set<PersonalSpaceData>> xml = new XMLBuddy<>();

        xml.saveToFile(Preferences.getSpacesSaveFile(), set);


        Set<PersonalSpaceData> personalSpaceData = xml.readFromFile(Preferences.getSpacesSaveFile());

        Set<PersonalSpace> nuevo = personalSpaceData.stream().map(PersonalSpace::loadPersonalSpaces).collect(Collectors.toSet());
        System.out.println(nuevo);
        System.out.println(set);

    }
}