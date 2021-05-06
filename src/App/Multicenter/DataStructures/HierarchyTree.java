package App.Multicenter.DataStructures;


import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class HierarchyTree <E> implements Tree<E> {

    // Variables de clase
    Map<E,E> relation; // Nodo(hijo) -> Nodo(padre)

    // Constructores
    // TODO

    // Operaciones

    @Override
    public Tree<E> getSubTree(E elem) {
        // TODO
        return null;
    }

    @Override
    public Set<E> getChildren(E father) {
        // TODO
        return null;
    }

    @Override
    public E getFather(E element) {
        // TODO
        return null;
    }

    @Override
    public boolean setChildren(Collection<E> children, E father) {
        // TODO
        return false;
    }

    @Override
    public boolean addChildren(E children, E father) {
        // TODO
        return false;
    }

    @Override
    public boolean addChildren(Collection<E> children, E father) {
        // TODO
        return false;
    }

    @Override
    public boolean removeElement(E element) {
        // TODO
        return false;
    }

    @Override
    public boolean removeElement(Collection<E> element) {
        // TODO
        return false;
    }

    @Override
    public boolean removeAllChildren(E element) {
        // TODO
        return false;
    }

    @Override
    public boolean removeAllChildren(Collection<E> element) {
        // TODO
        return false;
    }

    @Override
    public boolean contains(E element) {
        // TODO
        return false;
    }

    @Override
    public <R> Tree<R> map(Function<E, R> function) {
        // TODO
        return null;
    }

    @Override
    public int getSize() {
        // TODO
        return 0;
    }

    /**
     * Representa un elemento almacenado dentro de un árbol HierarchyTree
     * @param <E> Tipo de HierarchyTree
     */
    class Node<E>{
        // Variables de clase
        Set<E> children;
        E element;
        // Constructores
        // TODO

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
