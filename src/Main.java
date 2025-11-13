import java.util.*;
public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 4);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 5);
        g.addEdge(2, 4, 11);
        g.addEdge(3, 4, 2);
        g.addEdge(3, 5, 10);
        g.addEdge(4, 5, 1);

        List<Edge> mst = g.buildMST();
        System.out.println("Original MST edges:");
        for (Edge e : mst) System.out.println("  " + e);

        Edge removed = mst.get(2); // choose any edge to remove
        System.out.println("\nRemoving edge: " + removed);

        g.printComponents(mst, removed);

        Edge replacement = g.findReplacementEdge(mst, removed);
        System.out.println("\nReplacement edge found: " + replacement);

        List<Edge> newMST = new ArrayList<>(mst);
        newMST.remove(removed);
        newMST.add(replacement);

        System.out.println("\nNew MST edges:");
        for (Edge e : newMST) System.out.println("  " + e);
    }
}
