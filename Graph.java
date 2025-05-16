/* Interfaz en la que se encuentran los métodos básicos que utilizará nuestra
 * clase grafo. Tomada de 'Java Structures – Duane A. Bailey'
 */

import java.util.Iterator;

public interface Graph<V, E> extends Iterable<V> {
    public void add(V label);
    public void addEdge(V vtx1, V vtx2, E label);
    public V remove(V label);
    public E removeEdge(V vLabel1, V vLabel2);
    public V get(V label);
    public Edge<V, E> getEdge(V label1, V label2);
    public boolean contains(V label);
    public boolean containsEdge(V vLabel1, V vLabel2);
    public boolean visit(V label);
    public boolean visitEdge(Edge<V, E> e);
    public boolean isVisited(V label);
    public boolean isVisitedEdge(Edge<V, E> e);
    public void reset();
    public int size();
    public int degree(V label);
    public int edgeCount();
    public Iterator<V> iterator();
    public Iterator<V> neighbors(V label);
    public Iterator<Edge<V, E>> edges();
    public void clear();
    public boolean isEmpty();
    public boolean isDirected();
}
