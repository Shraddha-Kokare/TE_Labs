import java.util.Scanner;

public class MSTNetworkApplication {

    static final int INF = 999;

    static void primMST(int[][] graph, int n) {
        int[] visited = new int[n];
        int minCost = 0;

        visited[0] = 1; // start from node 0

        System.out.println("\nEdges in Minimum Spanning Tree:");
        System.out.println("--------------------------------");
        System.out.println("Edge\tCost");
        System.out.println("--------------------------------");

        for (int edges = 0; edges < n - 1; edges++) {
            int min = INF;
            int u = -1, v = -1;

            for (int i = 0; i < n; i++) {
                if (visited[i] == 1) {
                    for (int j = 0; j < n; j++) {
                        if (visited[j] == 0 && graph[i][j] < min) {
                            min = graph[i][j];
                            u = i;
                            v = j;
                        }
                    }
                }
            }

            visited[v] = 1;
            minCost += min;

            System.out.println(u + " - " + v + "\t" + min);
        }

        System.out.println("--------------------------------");
        System.out.println("Minimum Total Cost = " + minCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix (enter 999 if no edge):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        primMST(graph, n);

        sc.close();
    }
}

