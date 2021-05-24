package App.Multicenter;

import App.Multicenter.DataStructures.HierarchyTree;
import App.Multicenter.DataStructures.Tree;
import App.Multicenter.Preferences.Preferences;

import java.awt.*;
import java.util.Arrays;
import java.io.*;
import org.junit.*;

/**
 * Test basico intelliJ y JUnit
 */
public class Test {

    Tree<Integer> tree;
    @Before
    public void b(){
        tree = new HierarchyTree<>();
    }

    @org.junit.Test
    public void test() {
        Assert.assertNotEquals("Fallo",tree,null);
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Creando preferences por defecto...");
        System.out.println(System.getProperty("user.dir"));
        Preferences p = new Preferences();
        String h = "{window_size=600-600, working_directory=C:\\Users\\frank\\Documents\\Multicenter, theme=2, lang=0}";
        h = h.substring(1, h.length()-1);
        for(String s : h.split(",")){
            String[] settings = s.split("=");
            System.out.println(Arrays.toString(settings));
        }

        p.push();
        p.load();

    }

}
