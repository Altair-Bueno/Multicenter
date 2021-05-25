package App.Multicenter.DataStructures;
import org.w3c.dom.Node;

import java.util.*;
import java.util.function.Function;

public class HierarchyTree <E> implements Tree<E> {

    // Variables de clase
    HashMap<E,Object[]> relation; // hijo -> padre Object[0] padre Object[1] Set hijos
    E root;

    // TODO Constructores
    public HierarchyTree(HashMap<E,Object[]> r,E root){
        relation = r;
        this.root = root;

    }
    public HierarchyTree(E root){
        relation = new HashMap<>();
        this.root = root;
    }
    // Operaciones

    /**
     * Devuelve un sub-árbol donde el elemento raíz es el nodo recibido como
     * parámetro. Si el árbol no contiene dicho elemento, devolverá null
     *
     * @param elem Nodo raíz del nuevo sub-árbol
     * @return Sub-árbol nuevo. Los cambios <b>no</b> afectarán al árbol original
     */
    public Tree<E> getSubTree(E elem) { //INCOMPLETO
        Tree<E> subarbol=null;
        Object[] objects = new Object[2];
        if(relation.containsKey(elem)){
            Set<E> hijos =getChildren(elem);
            subarbol.setChildren(hijos,elem);
        }
        return subarbol;
    }
    /*
    public void mostrarTree(Tree<E> arb,E raiz){
        Set<E> conj = arb.getChildren(raiz);
        System.out.println(conj);
        conj
    }
    */
    /**
     * Devuelve un conjunto con los elementos que mantienen una relación de hijos
     * con el elemento padre
     *
     * @param father Elemento padre
     * @return Lista de sus hijos o null en caso de que el elemento padre no esté almacenado
     */
    public Set<E> getChildren(E father) {
        Set<E> hijos = null;
        Object[] objetos;
        if(contains(father)){
            objetos = relation.get(father);
            hijos = (Set<E>) objetos[1];
        }
        return hijos;
    }

    /**
     * Devuelve el padre de un elemento. Si dicho elemento no está en
     * almacenado en el árbol devuelve null. Si el elemento padre es
     * la raíz del árbol, se devolverá a si mismo
     *
     * @param element Elemento hijo
     * @return Elemento padre o null en caso de que el elemento hijo no esté almacenado
     */
    public E getFather(E element) {
        return (E) relation.get(element)[0];
    }

    /**
     * Cambia los hijos actuales del elemento father por los recibidos como parámetro. Si ya
     * existía dicho elemento, se sobreescribirá su contenido con el del elemento recibido
     * Aquellos elementos hijos del elemento father serán eliminados utilizando removeAllChildren
     *
     * @see Tree removeAllChildren(Collection <E> element)
     * @param children Colección de nuevos hijos
     * @param father Elemento padre destino
     * @return true si la operación es satisfactoria en todos los elementos, false en cualquier otro caso
     */
    public boolean setChildren(Collection<E> children, E father) { ///????
        if (relation.containsKey(father)){
            removeAllChildren(father);
        }
        for(E child : children){
            addChildren(child,father);
        }
        return true;
    }

    /**
     * Añade el elemento children como hijo del elemento father. Si ya
     * existía dicho elemento, se sobreescribirá su contenido con el
     * del elemento recibido
     *
     * @param children Elemento a añadir
     * @param father Elemento padre
     * @return true si la operación es satisfactoria, false en cualquier otro caso
     */
    public boolean addChildren(E children, E father) {
        Object[] objects = new Object[2];
        E elem = null;
        if(relation.containsKey(children)){
            objects = relation.get(father);
            objects[0] = father;
            objects[1] = getChildren(father).add(children);
        }else{
            objects[0] = father;
            objects[1] = null;
            elem = (E) relation.put(children, objects);
        }

        return elem != null;
    }

    /**
     * Añade todos los elementos de la colección como hijo elemento de father.
     * Si ya existía dicho elemento, se sobreescribirá su contenido con
     * el del elemento recibido
     *
     * @param children Collección de elementos a añadir
     * @param father Elemento padre
     * @return true si la operación es satisfactoria en todos los elementos, false en cualquier otro caso
     */
    public boolean addChildren(Collection<E> children, E father) { //INCOMPLETO
        for (E child:children) {
            addChildren(child,father);
        }
        return true;
    }

    /**
     * Elimina el elemento del árbol. Los hijos del elemento pasarán a formar
     * parte de su padre.
     *
     * @param element Elemento a borrar
     * @return true si la operación es satisfactoria, false en cualquier otro caso
     */
    public boolean removeElement(E element) {
        // TODO HierarchyTree removeElement
        return false;
    }

    /**
     * Elimina todos los elementos recibidos del árbol. Los hijos del elemento pasarán
     * a formar parte de su padre
     *
     * @param element Colección de elementos a borrar
     * @return true si la operación es satisfactoria en todos los elementos, false en cualquier otro caso
     */
    public boolean removeElement(Collection<E> element) {
        // TODO HierarchyTree removeElement
        return false;
    }

    /**
     * Elimina el elemento recibido del árbol. Los hijos del elemento también
     * serán borrados de forma recursiva
     *
     * @param element Elemento a borrar
     * @return true si la operación es satisfactoria, false en cualquier otro caso
     */
    public boolean removeAllChildren(E element) {
        // TODO HierarchyTree removeAllChildren
        return false;
    }

    /**
     * Elimina todos los elementos recibidos del árbol. Los hijos del elemento
     * también serán borrados de forma recursiva
     *
     * @param element Colección de elementos a borrar
     * @return true si la operación es satisfactoria en todos los elementos, false en cualquier otro caso
     */
    public boolean removeAllChildren(Collection<E> element) {
        // TODO HierarchyTree removeAllChildren
        return false;
    }

    /**
     * Indica si un elemento está almacenado en el árbol o no
     *
     * @param element Elemento a conocer
     * @return true si está o false si no
     */
    public boolean contains(E element) {
        return relation.containsKey(element);
    }

    /**
     * Aplica una función a todos los elementos del árbol, almacenando
     * el resultado en otro árbol que mantiene la misma estructura que el árbol
     *
     * original. Esta operación NO modifica el árbol original
     * @param function Función de dos entradas
     * @param <R> Tipo de salida para el nuevo árbol
     * @return Árbol de tipo R que conserva la estructura del árbol original
     */
    public <R> Tree<R> map(Function<E, R> function) {
        // TODO HierarchyTree map
        return null;
    }

    /**
     * Devuelve el número de elementos almacenados en el árbol
     * @return Nº de elementos almacenados
     */
    public int getSize() {
        // TODO HierarchyTree getSize
        return 0;
    }

}