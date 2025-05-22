/* Clase que se encarga de dar la implementación para poder manejar matrices de adyacencia */

import java.util.*;

public class GraphMatrix<V, E> implements Graph<V, E> {

    //Definimos el tamaño de la matriz (no es fijo, es dinámico), si es dirigido o no, la lista de vértices la matriz con aristas.
    private final int DEFAULT_CAPACITY = 10;
    private boolean directed;
    private List<V> vertices;
    private Edge<V, E>[][] adjMatrix;

    //Constructor, el usuario define si es dirigido o no.
    public GraphMatrix(boolean directed) {
        this.directed = directed;
        this.vertices = new ArrayList<>();
        this.adjMatrix = (Edge<V, E>[][]) new Edge[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    }

    @Override
    //Hacemos un override al método add, lo que hace es verificar si un vértice 'nuevo' existe, si no, lo crea.
    public void add(V label) {
        if (!vertices.contains(label)) {
            vertices.add(label);
            if (vertices.size() > adjMatrix.length) {
                expandMatrix();
            }
        }
    }
//En caso de añadir más vertices de los admisibles, se duplica el tamaño de la matriz.
    private void expandMatrix() {
        int newSize = adjMatrix.length * 2;
        Edge<V, E>[][] newMatrix = (Edge<V, E>[][]) new Edge[newSize][newSize];
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                newMatrix[i][j] = adjMatrix[i][j];
            }
        }
        adjMatrix = newMatrix;
    }

    @Override
    //Verifica que 2 vértices existan, de no hacerlo los crea. Determina sus posiciones y inserta una arista. Si no es dirigido la inserta en doble vía.
    public void addEdge(V vtx1, V vtx2, E label) {
        if (!contains(vtx1)) add(vtx1);
        if (!contains(vtx2)) add(vtx2);
        int i = vertices.indexOf(vtx1);
        int j = vertices.indexOf(vtx2);
        adjMatrix[i][j] = new Edge<>(vtx1, vtx2, label, directed);
        if (!directed) {
            adjMatrix[j][i] = new Edge<>(vtx2, vtx1, label, directed);
        }
    }

    @Override
    //Remove Elimina un vértice y luego ajusta la matriz.
    public V remove(V label) {
        int index = vertices.indexOf(label);
        if (index == -1) return null;
        vertices.remove(index);

        for (int i = 0; i < vertices.size() + 1; i++) {
            for (int j = index; j < vertices.size(); j++) {
                adjMatrix[i][j] = adjMatrix[i][j + 1];
            }
        }
        for (int i = index; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                adjMatrix[i][j] = adjMatrix[i + 1][j];
            }
        }
        return label;
    }

    @Override
    //Elimina una arista entre 2 vértices. 
    public E removeEdge(V v1, V v2) {
        int i = vertices.indexOf(v1);
        int j = vertices.indexOf(v2);
        if (adjMatrix[i][j] == null) return null;
        E label = adjMatrix[i][j].label();
        adjMatrix[i][j] = null;
        if (!directed) adjMatrix[j][i] = null;
        return label;
    }

    @Override
    //Obtiene un vértice.
    public V get(V label) {
        for (V v : vertices) {
            if (v.equals(label)) return v;
        }
        return null;
    }

    @Override
    //obtiene una arista,
    public Edge<V, E> getEdge(V v1, V v2) {
        int i = vertices.indexOf(v1);
        int j = vertices.indexOf(v2);
        return adjMatrix[i][j];
    }

    @Override
    //Verifica si existe un vértice.
    public boolean contains(V label) {
        return vertices.contains(label);
    }

    @Override
    //verifica que exista una arista entre vértices
    public boolean containsEdge(V v1, V v2) {
        int i = vertices.indexOf(v1);
        int j = vertices.indexOf(v2);
        return adjMatrix[i][j] != null;
    }

    @Override
    //Visita determinado vértice 
    public boolean visit(V label) {
        return false; // opcional
    }

    @Override
    //Visita determinada arista.
    public boolean visitEdge(Edge<V, E> e) {
        return e.visit();
    }

    @Override
    //Revisa si ya fue recorrido un vértice
    public boolean isVisited(V label) {
        return false; // opcional
    }

    @Override
    //Revisa si ya fue recorrida una arista.
    public boolean isVisitedEdge(Edge<V, E> e) {
        return e.isVisited();
    }

    @Override
    public void reset() {
       
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    //Determina cuántos vecinos tiene un vértice.
    public int degree(V label) {
        int index = vertices.indexOf(label);
        int count = 0;
        for (int j = 0; j < vertices.size(); j++) {
            if (adjMatrix[index][j] != null) count++;
        }
        return count;
    }

    @Override
    //De toda la matriz cuenta cuántas celdas NO son nulas.
    public int edgeCount() {
        int count = 0;
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (adjMatrix[i][j] != null) count++;
            }
        }
        return directed ? count : count / 2;
    }

    @Override
    //Permite poder recorrer el grafo.
    public Iterator<V> iterator() {
        return vertices.iterator();
    }

    @Override
    //Devuelve un iterador sobre los vértices conectados.
    public Iterator<V> neighbors(V label) {
        List<V> result = new ArrayList<>();
        int index = vertices.indexOf(label);
        for (int j = 0; j < vertices.size(); j++) {
            if (adjMatrix[index][j] != null) {
                result.add(vertices.get(j));
            }
        }
        return result.iterator();
    }

    @Override
    public Iterator<Edge<V, E>> edges() {
        List<Edge<V, E>> result = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (adjMatrix[i][j] != null) {
                    result.add(adjMatrix[i][j]);
                }
            }
        }
        return result.iterator();
    }

    @Override
    public void clear() {
        vertices.clear();
        adjMatrix = (Edge<V, E>[][]) new Edge[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public boolean isDirected() {
        return directed;
    }
}
