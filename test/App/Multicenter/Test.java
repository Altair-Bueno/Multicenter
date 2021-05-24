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

}
