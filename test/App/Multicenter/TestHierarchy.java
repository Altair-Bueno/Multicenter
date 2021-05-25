package App.Multicenter;


import App.Multicenter.DataStructures.HierarchyTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;

public class TestHierarchy {
    public static void main(String[] args) {
        /*
                    5
                1   2   3
                   /\
                  4 6
         */
        HierarchyTree arbol = new HierarchyTree(5);
        HashMap<Integer,Integer> mapF = new HashMap<>();
        HashMap<Integer, Set<Integer>> mapC = new HashMap<>();
        mapF.put(5,5);
        mapF.put(1,5);
        mapF.put(2,5);
        mapF.put(3,5);
        mapF.put(4,2);
        mapF.put(6,2);
        Set<Integer> conj = new HashSet<>(); conj.add(1); conj.add(2); conj.add(3);
        mapC.put(5,conj);
        HierarchyTree arbol2 = new HierarchyTree(mapF,mapC,5);
        System.out.println("Hijos de 5: ");
        conj = arbol2.getChildren(5);
        System.out.println(conj);

        conj.clear(); conj.add(4); conj.add(6);
        mapC.put(2,conj);
        System.out.println("Hijos de 2: ");
        conj = arbol2.getChildren(2);
        System.out.println(conj);

    }
}
