package App.Multicenter.DataStructures;


import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class HierarchyTree <E> implements Tree<E>, Serializable {
    // Variables de clase
    Map<E,E> relation; // Nodo(hijo) -> Nodo(padre)

    // TODO Constructores

    // Operaciones

    @Override
    public E getRoot() {
        return null;
    }

    @Override
    public Tree<E> getSubTree(E elem) {
        // TODO HierarchyTree getSubtree
        return null;
    }

    @Override
    public Set<E> getChildren(E father) {
        // TODO HierarchyTree getChildren
        return null;
    }

    @Override
    public E getFather(E element) {
        // TODO HierarchyTree getFather
        return null;
    }

    @Override
    public boolean setChildren(Collection<E> children, E father) {
        // TODO HierarchyTree setChildren
        return false;
    }

    @Override
    public boolean addChildren(E children, E father) {
        // TODO HierarchyTree addChildren
        return false;
    }

    @Override
    public boolean addChildren(Collection<E> children, E father) {
        // TODO HierarchyTree addChildren
        return false;
    }

    @Override
    public boolean removeElement(E element) {
        // TODO HierarchyTree removeElement
        return false;
    }

    @Override
    public boolean removeElement(Collection<E> element) {
        // TODO HierarchyTree removeElement
        return false;
    }

    @Override
    public boolean removeAllChildren(E element) {
        // TODO HierarchyTree removeAllChildren
        return false;
    }

    @Override
    public boolean removeAllChildren(Collection<E> element) {
        // TODO HierarchyTree removeAllChildren
        return false;
    }

    @Override
    public boolean contains(E element) {
        // TODO HierarchyTree contains
        return false;
    }

    @Override
    public <R> Tree<R> map(Function<E, R> function) {
        // TODO HierarchyTree map
        return null;
    }

    @Override
    public int getSize() {
        // TODO HierarchyTree getSize
        return 0;
    }

    @Override
    public Set<E> getNodes() {
        return null;
    }

    /**
     * Representa un elemento almacenado dentro de un árbol HierarchyTree
     * @param <E> Tipo de HierarchyTree
     */
    class Node<E>{
        // Variables de clase
        Set<E> children;
        E element;
        // TODO Node Constructores

        // Operaciones


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return element.equals(node.element);
        }

        @Override
        public int hashCode() {
            return element.hashCode();
        }
    }
}
