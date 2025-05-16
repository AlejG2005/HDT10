public class Edge<V, E> {
    protected V vtx1, vtx2;
    protected E label;
    protected boolean visited;
    protected boolean directed;

    public Edge(V vtx1, V vtx2, E label, boolean directed) {
        this.vtx1 = vtx1;
        this.vtx2 = vtx2;
        this.label = label;
        this.directed = directed;
        this.visited = false;
    }

    public V here() {
        return vtx1;
    }

    public V there() {
        return vtx2;
    }

    public void setLabel(E label) {
        this.label = label;
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

    public boolean isDirected() {
        return directed;
    }

    public void reset() {
        visited = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge) {
            Edge<?, ?> e = (Edge<?, ?>) o;
            return vtx1.equals(e.vtx1) && vtx2.equals(e.vtx2);
        }
        return false;
    }
}
