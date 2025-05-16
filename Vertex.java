/* Clase que representa un vértice en el grafo. Como atributo solo tiene
 * label que es su nombre y visited que es el vértice del que venimos.
 */

public class Vertex<E> {
    protected E label;
    protected boolean visited;

    public Vertex(E label) {
        this.label = label;
        this.visited = false;
    }

    public E label() {
        return label;
    }

    public boolean visit() {
        boolean wasVisited = visited;
        visited = true;
        return wasVisited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void reset() {
        visited = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex) {
            Vertex<?> v = (Vertex<?>) o;
            return label.equals(v.label());
        }
        return false;
    }
}
