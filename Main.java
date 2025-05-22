public class Main {
    public static void main(String[] args) {
        // Crear grafo dirigido
        GraphMatrix<String, Double> grafo = new GraphMatrix<>(true);

        // Agregar vértices (ciudades)
        grafo.add("A"); // Ciudad de Guatemala
        grafo.add("B"); // Zacapa
        grafo.add("C"); // Chiquimula
        grafo.add("D"); // Quetzaltenango
        grafo.add("E"); // Cobán

        // Agregar aristas (rutas con distancia)
        grafo.addEdge("A", "B", 3.0);
        grafo.addEdge("A", "D", 7.0);
        grafo.addEdge("B", "C", 1.0);
        grafo.addEdge("B", "E", 8.0);
        grafo.addEdge("C", "D", 2.0);
        grafo.addEdge("D", "E", 3.0);
        grafo.addEdge("E", "A", 4.0);

        // Mostrar las rutas iniciales
        System.out.println("\nRutas iniciales:");
        for (String u : grafo) {
            for (String v : grafo) {
                if (grafo.containsEdge(u, v)) {
                    System.out.println(u + " → " + v + " = " + grafo.getEdge(u, v).label());
                }
            }
        }

        // Ejecutar algoritmo de Floyd-Warshall
        FloydAlgorithm<String, Double> floyd = new FloydAlgorithm<>();
        floyd.ejecutar(grafo);

        System.out.println("\nDistancias mínimas después de Floyd-Warshall:");
        for (String u : grafo) {
            for (String v : grafo) {
                if (grafo.containsEdge(u, v)) {
                    System.out.println(u + " → " + v + " = " + grafo.getEdge(u, v).label());
                } else {
                    System.out.println(u + " → " + v + " = ∞");
                }
            }
        }

        // Calcular centro del grafo
        String centro = floyd.calcularCentro(grafo);
        System.out.println("\nCentro del grafo: " + centro);
    }
}
