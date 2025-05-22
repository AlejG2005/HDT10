
/*Prueba JUNit para verificar el funcionamiento de los métodos básicos para añadir y eliminar aristas y vértices. Además
 * prueba el algortimo de floyd.
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private GraphMatrix<String, Double> grafo;

    @BeforeEach
    public void setup() {
        grafo = new GraphMatrix<>(true); // Grafo dirigido

        // Vértices base
        grafo.add("A");
        grafo.add("B");
        grafo.add("C");

        // Aristas base
        grafo.addEdge("A", "B", 3.0);
        grafo.addEdge("B", "C", 5.0);
    }

    @Test
    //Verifica que se añadan los vértices de forma correcta.
    public void testAgregarVertices() {
        assertTrue(grafo.contains("A"));
        assertTrue(grafo.contains("B"));
        assertTrue(grafo.contains("C"));

        grafo.add("D");
        assertTrue(grafo.contains("D"));
    }

    @Test
    //verifica que se genere correctamente una arista entre 2 vértices.
    public void testAgregarArista() {
        assertTrue(grafo.containsEdge("A", "B"));
        assertEquals(3.0, grafo.getEdge("A", "B").label());

        grafo.addEdge("C", "A", 2.0);
        assertTrue(grafo.containsEdge("C", "A"));
        assertEquals(2.0, grafo.getEdge("C", "A").label());
    }

    @Test
    //verifica correctamente la eliminación de una arista y el reordenamiento,
    public void testEliminarArista() {
        assertTrue(grafo.containsEdge("A", "B"));
        grafo.removeEdge("A", "B");
        assertFalse(grafo.containsEdge("A", "B"));
    }

    @Test
    //verifica que el algoritmo de floyd encuentre la ruta más corta.
    public void testFloydWarshallActualizaDistancias() {
        FloydAlgorithm<String, Double> floyd = new FloydAlgorithm<>();
        floyd.ejecutar(grafo);

        // A → B = 3.0, B → C = 5.0 ⇒ A → C debería ser 8.0 tras Floyd
        assertTrue(grafo.containsEdge("A", "C"));
        assertEquals(8.0, grafo.getEdge("A", "C").label());
    }
}
