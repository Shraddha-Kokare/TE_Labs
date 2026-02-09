import java.util.Scanner;

public class ShortestPathApplication {

    static final int INF = 999;

    static int findMinVertex(int[] distance, boolean[] visited, int n) {
        int minVertex = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    static void dijkstra(int[][] graph, int n, int source) {

        int[] distance = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            distance[i] = INF;
            visited[i] = false;
        }

        distance[source] = 0;

        for (int i = 0; i < n - 1; i++) {

            int u = findMinVertex(distance, visited, n);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        distance[u] != INF &&
                        distance[u] + graph[u][v] < distance[v]) {

                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        System.out.println("\nShortest distances from source location " + source + ":");
        System.out.println("--------------------------------------");
        System.out.println("Location\tDistance");
        System.out.println("--------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println(source + " -> " + i + "\t\t" + distance[i]);
        }
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of locations: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix (0 if no direct road):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source location: ");
        int source = sc.nextInt();

        dijkstra(graph, n, source);

        sc.close();
    }
}
