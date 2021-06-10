package multicenter;

import app.multicenter.DataStructures.HierarchyTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TestHierarchy {
    public static void main(String[] args) {
        /*
                    5
                1   2   3
                   /\
                  4 6
         */

        HashMap<Integer,Object[]> map = new HashMap<>();
        Set<Integer> hijos5 = new HashSet<>(); hijos5.add(1);hijos5.add(2);hijos5.add(3);
        Object[] obj5 = new Object[2];
        obj5[0] = 5;
        obj5[1] = hijos5;
        map.put(5,obj5);
        Object[] obj1 = new Object[2];
        obj1[0] = 5;
        obj1[1] = null;
        map.put(1,obj1);
        Object[] obj2 = new Object[2];
        Set<Integer> hijos2 = new HashSet<>(); hijos2.add(4);hijos2.add(6);
        obj2[0] = 5;
        obj2[1] = hijos2;
        map.put(2,obj2);
        Object[] obj3 = new Object[2];
        obj3[0] = 5;
        obj3[1] = null;
        map.put(3,obj3);
        Object[] obj4 = new Object[2];
        obj4[0] = 2;
        obj4[1] = null;
        map.put(4,obj4);
        Object[] obj6 = new Object[2];
        obj6[0] = 2;
        obj6[1] = null;
        map.put(6,obj6);

        Set<Integer> conj;
        HierarchyTree arbol = new HierarchyTree(map,5);
        System.out.println("Hijos de 5: ");
        conj = arbol.getChildren(5);
        System.out.println(conj);

        System.out.println("Hijos de 1: ");
        conj = arbol.getChildren(1);
        System.out.println(conj);

        System.out.println("Contiene 6: ");
        System.out.println(arbol.contains(6));

        System.out.println("Padre de 2: ");
        System.out.println(arbol.getFather(2));

        System.out.println("Añadir hijo (7) a 5: ");
        arbol.addChildren(7,5);
        System.out.println(arbol.getChildren(5));
         /*
        System.out.println("Establecer hijos (8,9) a 1: ");
        Set<Integer> nuevosHijos = new HashSet<>();nuevosHijos.add(8);nuevosHijos.add(9);
        arbol.setChildren(nuevosHijos,1);
        System.out.println(arbol.getChildren(1));
        */

    }
}
