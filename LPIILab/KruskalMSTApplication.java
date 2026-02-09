import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalMSTApplication {

    static int findParent(int v, int[] parent) {
        if (parent[v] == v)
            return v;

        return findParent(parent[v], parent);
    }

    static void kruskalMST(Edge[] edges, int vertices, int edgeCount) {

        Arrays.sort(edges);

        int[] parent = new int[vertices];

        for (int i = 0; i < vertices; i++)
            parent[i] = i;

        Edge[] result = new Edge[vertices - 1];

        int e = 0; // count of edges in MST
        int i = 0; // index for sorted edges

        int minCost = 0;

        while (e < vertices - 1) {

            Edge current = edges[i++];

            int sourceParent = findParent(current.src, parent);
            int destParent = findParent(current.dest, parent);

            if (sourceParent != destParent) {
                result[e++] = current;
                parent[sourceParent] = destParent;
                minCost += current.weight;
            }
        }

        System.out.println("\nSelected Roads in Minimum Spanning Tree:");
        System.out.println("---------------------------------------");
        System.out.println("City1 - City2 : Cost");
        System.out.println("---------------------------------------");

        for (i = 0; i < e; i++) {
            System.out.println(result[i].src + " - " +
                               result[i].dest + " : " +
                               result[i].weight);
        }

        System.out.println("---------------------------------------");
        System.out.println("Minimum Total Road Construction Cost = " + minCost);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of possible roads: ");
        int edgesCount = sc.nextInt();

        Edge[] edges = new Edge[edgesCount];

        System.out.println("Enter roads in format: city1 city2 cost");

        for (int i = 0; i < edgesCount; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();

            edges[i] = new Edge(s, d, w);
        }

        kruskalMST(edges, vertices, edgesCount);

        sc.close();
    }
}
