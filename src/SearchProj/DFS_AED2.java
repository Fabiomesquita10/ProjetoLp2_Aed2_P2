package SearchProj;
import edu.princeton.cs.algs4.Stack;
//dfs
public class DFS_AED2 {
    private boolean[] marked;  // marked[v] = true iff v is reachable from s
    private int[] edgeTo;      // edgeTo[v] = last edge on path from s to v
    private final int s;       // source vertex
    GeoDigraph g;

    public DFS_AED2(GeoDigraph G, int s) {
        g = G;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(GeoDigraph G, int v) {
        marked[v] = true;
        for (DirectedEdge_AED2 w : G.adj(v)) {
            if (!marked[w.to()]) {
                edgeTo[w.to()] = v;
                dfs(G, w.to());
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }


    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        BSP_AED2 a;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
