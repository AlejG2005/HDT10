/* Algoritmo de la ruta más corta que será utilizado. Tomada de 'Java Structures – Duane A. Bailey'
 */
public class FloydAlgorithm<V, E extends Number> {

    public void ejecutar(Graph<V, E> g) {
        for (V w : g) {
            for (V u : g) {
                for (V v : g) {
                    if (g.containsEdge(u, w) && g.containsEdge(w, v)) {
                        E uw = g.getEdge(u, w).label();
                        E wv = g.getEdge(w, v).label();
                        E uv = g.containsEdge(u, v) ? g.getEdge(u, v).label() : null;

                        double nuevaDist = uw.doubleValue() + wv.doubleValue();

                        if (uv == null || nuevaDist < uv.doubleValue()) {
                            g.addEdge(u, v, (E) Double.valueOf(nuevaDist));
                        }
                    }
                }
            }
        }
    }

    public V calcularCentro(Graph<V, E> g) {
        V centro = null;
        double menorExcentricidad = Double.MAX_VALUE;

        for (V u : g) {
            double max = 0;
            for (V v : g) {
                if (g.containsEdge(u, v)) {
                    double dist = g.getEdge(u, v).label().doubleValue();
                    if (dist > max) max = dist;
                }
            }

            if (max < menorExcentricidad) {
                menorExcentricidad = max;
                centro = u;
            }
        }

        return centro;
    }
}
