package App.Multicenter.Buddy;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
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
        XMLBuddy<Set<Integer>> x = new XMLBuddy<>();
        x.parseTreeStructure(file,set1);

        Set<Integer> set2 = x.parseXMLFile(file);

        Assert.assertEquals(set1,set2);
    }
}
