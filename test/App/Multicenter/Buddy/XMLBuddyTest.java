package App.Multicenter.Buddy;

import App.Multicenter.Preferences.Preferences;
import App.Multicenter.Space.PersonalSpace;
import App.Multicenter.Widget.NotesWidget;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class XMLBuddyTest {
    File file = new File("test/App/Multicenter/Buddy/test.xml");
    @Test
    public void test() throws FileNotFoundException {
        // TODO Test sobre el árbol
        Set<Integer> set1 = new HashSet<>();
        Collections.addAll(set1, 1,2,3,3,4);
        Object[] ob = new Object[2];
        ob[1] = set1;
        ob[0] = new Dimension(200,44);
        XMLBuddy<Object[]> x = new XMLBuddy<>();
        x.saveToFile(file,ob);

        Object [] ob2 = x.readFromFile(file);

        System.out.println(Arrays.toString(ob));
        System.out.println(Arrays.toString(ob2));
    }
    @Test
    public void sfljkas(){
        Preferences.loadPreferences();
        Preferences.setSpacesFolder(new File("/Users/compux72/Downloads/TestMulticenter"));
        NotesWidget nw = new NotesWidget();
        nw.setAlignmentX(10);
        nw.setSize(new Dimension(20,3));
        nw.setLayer(30);
        XMLBuddy<NotesWidget> sa  =new XMLBuddy<>();
        sa.saveToFile(new File(Preferences.getSpacesFolder(),"notas.txt"),nw);
    }
}
