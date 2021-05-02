package App.Multicenter.DataStructures;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

/**
 * Representa un árbol jerárquico. Cada uno de los elementos tiene asignado
 * un único padre y un número indeterminado de hijos. El primer elemento del
 * árbol recibe el nombre de "raíz" y es padre de si mismo. Los elementos
 * situados al final del árbol son conocidos como "hojas" y no presentan hijos
 * @param <E> Tipo de los datos almacenados
 */
public interface Tree <E>{
    /**
     * Devuelve un conjunto con los elementos que mantienen una relación de hijos
     * con el elemento padre
     *
     * @param father Elemento padre
     * @return Lista de sus hijos o null en caso de que el elemento padre no esté almacenado
     */
    Set<E> getChildren(E father);

    /**
     * Devuelve el padre de un elemento. Si dicho elemento no está en
     * almacenado en el árbol devuelve null. Si el elemento padre es
     * la raíz del árbol, se devolverá a si mismo
     *
     * @param element Elemento hijo
     * @return Elemento padre o null en caso de que el elemento hijo no esté almacenado
     */
    E getFather(E element);

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
    boolean setChildren(Collection<E> children , E father);

    /**
     * Añade el elemento children como hijo del elemento father. Si ya
     * existía dicho elemento, se sobreescribirá su contenido con el
     * del elemento recibido
     *
     * @param children Elemento a añadir
     * @param father Elemento padre
     * @return true si la operación es satisfactoria, false en cualquier otro caso
     */
    boolean addChildren(E children , E father);

    /**
     * Añade todos los elementos de la colección como hijo elemento de father.
     * Si ya existía dicho elemento, se sobreescribirá su contenido con
     * el del elemento recibido
     *
     * @param children Collección de elementos a añadir
     * @param father Elemento padre
     * @return true si la operación es satisfactoria en todos los elementos, false en cualquier otro caso
     */
    boolean addChildren(Collection <E> children, E father);

    /**
     * Elimina el elemento del árbol. Los hijos del elemento pasarán a formar
     * parte de su padre.
     *
     * @param element Elemento a borrar
     * @return true si la operación es satisfactoria, false en cualquier otro caso
     */
    boolean removeElement(E element);

    /**
     * Elimina todos los elementos recibidos del árbol. Los hijos del elemento pasarán
     * a formar parte de su padre
     *
     * @param element Colección de elementos a borrar
     * @return true si la operación es satisfactoria en todos los elementos, false en cualquier otro caso
     */
    boolean removeElement(Collection<E> element);

    /**
     * Elimina el elemento recibido del árbol. Los hijos del elemento también
     * serán borrados de forma recursiva
     *
     * @param element Elemento a borrar
     * @return true si la operación es satisfactoria, false en cualquier otro caso
     */
    boolean removeAllChildren(E element);

    /**
     * Elimina todos los elementos recibidos del árbol. Los hijos del elemento
     * también serán borrados de forma recursiva
     *
     * @param element Colección de elementos a borrar
     * @return true si la operación es satisfactoria en todos los elementos, false en cualquier otro caso
     */
    boolean removeAllChildren(Collection <E> element);

    /**
     * Indica si un elemento está almacenado en el árbol o no
     *
     * @param element Elemento a conocer
     * @return true si está o false si no
     */
    boolean contains(E element);

    /**
     * Aplica una función a todos los elementos del árbol, almacenando
     * el resultado en otro árbol que mantiene la misma estructura que el árbol
     *
     * original. Esta operación NO modifica el árbol original
     * @param function Función de dos entradas
     * @param <R> Tipo de salida para el nuevo árbol
     * @return Árbol de tipo R que conserva la estructura del árbol original
     */
    <R> Tree<R> map (Function<E,R> function);

    /**
     * Devuelve el número de elementos almacenados en el árbol
     * @return Nº de elementos almacenados
     */
    int getSize();
}
