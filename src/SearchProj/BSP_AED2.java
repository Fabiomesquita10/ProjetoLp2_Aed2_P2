package SearchProj;
import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
//Bellsman
public class BSP_AED2 {
    // for floating-point precision issues
    private static final double EPSILON = 1E-14;

    private double[] distTo;               // distTo[v] = distance  of shortest s->v path
    private double[] distTo2;
    private double[] tempoTo;
    private double[] elevTo;
    private DirectedEdge_AED2[] edgeTo;         // edgeTo[v] = last edge on shortest s->v path
    private boolean[] onQueue;             // onQueue[v] = is v currently on the queue?
    private Queue<Integer> queue;          // queue of vertices to relax
    private int cost;                      // number of calls to relax()
    private Iterable<DirectedEdge_AED2> cycle;  // negative cycle (or null if no such cycle)
    ArrayList<Integer> path = new ArrayList<Integer>();

    public BSP_AED2(EdgeWeightedDiGraph_AED2 G, int s) {
        distTo  = new double[G.V()];
        distTo2  = new double[G.V()];
        tempoTo  = new double[G.V()];
        elevTo  = new double[G.V()];
        edgeTo  = new DirectedEdge_AED2[G.V()];
        onQueue = new boolean[G.V()];
        path.add(s);
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // Bellman-Ford algorithm
        queue = new Queue<Integer>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }

        assert check(G, s);
    }

    public BSP_AED2(EdgeWeightedDiGraph_AED2 G, int s, int x) {
        distTo  = new double[G.V()];
        distTo2  = new double[G.V()];
        tempoTo  = new double[G.V()];
        elevTo  = new double[G.V()];
        edgeTo  = new DirectedEdge_AED2[G.V()];
        onQueue = new boolean[G.V()];
        path.add(s);
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // Bellman-Ford algorithm
        queue = new Queue<Integer>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            if(x == 1)
                relaxE(G, v);
        }

        assert check(G, s);
    }

    // relax vertex v and put other endpoints on queue if changed
    private void relax(EdgeWeightedDiGraph_AED2 G, int v) {
        for (DirectedEdge_AED2 e : G.adj(v)) {
            int w = e.to();

            if (distTo[w] > distTo[v] + e.weight() + EPSILON) {
                distTo[w] = distTo[v] + e.weight();
                distTo2[w] = distTo2[v] + e.distancia();
                tempoTo[w] = tempoTo[v] + e.tempo();
                elevTo[w] = elevTo[v] + e.elevacao();
                edgeTo[w] = (DirectedEdge_AED2) e;
                int t = path.size();
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if (++cost % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return;  // found a negative cycle
            }
        }
    }

    private void relaxE(EdgeWeightedDiGraph_AED2 G, int v) {
        for (DirectedEdge_AED2 e : G.adj(v)) {
            int w = e.to();

            if (distTo[w] > distTo[v] + e.elevacao() + EPSILON) {
                distTo[w] = distTo[v] + e.elevacao();
                distTo2[w] = distTo2[v] + e.distancia();
                tempoTo[w] = tempoTo[v] + e.tempo();
                elevTo[w] = elevTo[v] + e.elevacao();
                edgeTo[w] = (DirectedEdge_AED2) e;
                int t = path.size();
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if (++cost % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return;  // found a negative cycle
            }
        }
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
        //return false;
    }

    public Iterable<DirectedEdge_AED2> negativeCycle() {
        return cycle;
    }

    // by finding a cycle in predecessor graph
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDiGraph_AED2 spt = new EdgeWeightedDiGraph_AED2(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);

        EdgeWDC_AED2 finder = new EdgeWDC_AED2(spt);
        cycle = finder.cycle();
    }

    public ArrayList<Integer> p(int v){
        path.add(v);
        return path;
    }

    public double distTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[v];
    }

    public double distTo2(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo2[v];
    }

    public double tempoTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return tempoTo[v];
    }

    public double elevTo(int v) {
        validateVertex(v);
        return elevTo[v];
    }


    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge_AED2> pathTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge_AED2> path = new Stack<DirectedEdge_AED2>();
        for (DirectedEdge_AED2 e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private boolean check(EdgeWeightedDiGraph_AED2 G, int s) {

        // has a negative cycle
        if (hasNegativeCycle()) {
            double weight = 0.0;
            for (DirectedEdge_AED2 e : negativeCycle()) {
                weight += e.weight();
            }
            if (weight >= 0.0) {
                System.err.println("error: weight of negative cycle = " + weight);
                return false;
            }
        }

        // no negative cycle reachable from source
        else {

            // check that distTo[v] and edgeTo[v] are consistent
            if (distTo[s] != 0.0 || edgeTo[s] != null) {
                System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
                return false;
            }
            for (int v = 0; v < G.V(); v++) {
                if (v == s) continue;
                if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                    System.err.println("distTo[] and edgeTo[] inconsistent");
                    return false;
                }
            }

            // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if (distTo[v] + e.weight() < distTo[w]) {
                        System.err.println("edge " + e + " not relaxed");
                        return false;
                    }
                }
            }

            // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
            for (int w = 0; w < G.V(); w++) {
                if (edgeTo[w] == null) continue;
                DirectedEdge e = edgeTo[w];
                int v = e.from();
                if (w != e.to()) return false;
                if (distTo[v] + e.weight() != distTo[w]) {
                    System.err.println("edge " + e + " on shortest path not tight");
                    return false;
                }
            }
        }

        StdOut.println("Satisfies optimality conditions");
        StdOut.println();
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
