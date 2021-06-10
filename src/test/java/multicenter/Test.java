package multicenter;

import app.multicenter.DataStructures.Tree;
import org.junit.Assert;
import org.junit.Before;

/**
 * Test basico intelliJ y JUnit
 */
public class Test {

    Tree<Integer> tree;

    @Before
    public void b() {
       // tree = new HierarchyTree<>();
    }

    @org.junit.Test
    public void test() {
        Assert.assertNotEquals("Fallo", tree, null);
    }

}
