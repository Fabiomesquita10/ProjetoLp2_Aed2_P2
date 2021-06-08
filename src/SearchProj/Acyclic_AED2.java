package SearchProj;

import edu.princeton.cs.algs4.*;

public class Acyclic_AED2 {
    private double[] distTo;         // distTo[v] = distance  of shortest s->v path
    private DirectedEdge_AED2[] edgeTo;   // edgeTo[v] = last edge on shortest s->v path

    public Acyclic_AED2(EdgeWeightedDiGraph_AED2 G, int s){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge_AED2[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // visit vertices in topological order
        Topological_AED2 topological = new Topological_AED2(G);
        if (!topological.hasOrder())
            throw new IllegalArgumentException("Digraph is not acyclic.");
        for (int v : topological.order()) {
            for (DirectedEdge_AED2 e : G.adj(v))
                relax(e);
        }
    }

    private void relax(DirectedEdge_AED2 e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.elevacao()) {
            distTo[w] = distTo[v] + e.elevacao();
            edgeTo[w] = e;
        }
    }

    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge_AED2> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge_AED2> path = new Stack<DirectedEdge_AED2>();
        for (DirectedEdge_AED2 e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
