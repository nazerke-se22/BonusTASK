import java.util.*;
public class Graph {
    int vertices;
    List<Edge> edges = new ArrayList<>();

    public Graph(int vertices) {
        this.vertices = vertices;
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    // Disjoint Set Union (Union-Find)
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) parent[rx] = ry;
            else if (rank[rx] > rank[ry]) parent[ry] = rx;
            else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }

    // Kruskal's algorithm
    public List<Edge> buildMST() {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges);
        DSU dsu = new DSU(vertices);

        for (Edge e : edges) {
            if (dsu.find(e.src) != dsu.find(e.dest)) {
                mst.add(e);
                dsu.union(e.src, e.dest);
            }
        }
        return mst;
    }

    // Find replacement edge to reconnect components after removal
    public Edge findReplacementEdge(List<Edge> mst, Edge removedEdge) {
        // Build DSU for remaining edges
        DSU dsu = new DSU(vertices);
        for (Edge e : mst) {
            if (!e.equals(removedEdge)) {
                dsu.union(e.src, e.dest);
            }
        }

        Edge replacement = null;
        int minWeight = Integer.MAX_VALUE;

        for (Edge e : edges) {
            int r1 = dsu.find(e.src);
            int r2 = dsu.find(e.dest);
            if (r1 != r2 && e.weight < minWeight) {
                replacement = e;
                minWeight = e.weight;
            }
        }
        return replacement;
    }

    public void printComponents(List<Edge> mst, Edge removed) {
        DSU dsu = new DSU(vertices);
        for (Edge e : mst) {
            if (!e.equals(removed)) dsu.union(e.src, e.dest);
        }

        Map<Integer, List<Integer>> comps = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            int root = dsu.find(i);
            comps.putIfAbsent(root, new ArrayList<>());
            comps.get(root).add(i);
        }

        System.out.println("Components after removing " + removed + ":");
        for (List<Integer> comp : comps.values()) {
            System.out.println("  " + comp);
        }
    }
}
