package SearchProj;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class DSP_AED2 {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge_AED2[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices
    private int x;

    public DSP_AED2(EdgeWeightedDiGraph_AED2 G, int s, int x2) {
        x = x2;
        for (DirectedEdge_AED2 e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new DirectedEdge_AED2[G.V()];

        validateVertex(s);

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge_AED2 e : G.adj(v))
                relax(e);
        }

        // check optimality conditions
        assert check(G, s);
    }

    // relax edge e and update pq if changed
    private void relax(DirectedEdge_AED2 e) {
        if(x==0){
            int v = e.from(), w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }else if(x==1){
            int v = e.from(), w = e.to();
            if (distTo[w] > distTo[v] + e.distancia()) {
                distTo[w] = distTo[v] + e.distancia();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }else if(x==2){
            int v = e.from(), w = e.to();
            if (distTo[w] > distTo[v] + e.tempo()) {
                distTo[w] = distTo[v] + e.tempo();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
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

    public String pathTo(int v) {
        int x = 0;
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge_AED2> path = new Stack<DirectedEdge_AED2>();
        for (DirectedEdge_AED2 e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            x++;
            path.push(e);
        }
        String ola = x + "\n" + path.toString();
        return ola;
    }

    private boolean check(EdgeWeightedDiGraph_AED2 G, int s) {

        // check that edge weights are non-negative
        for (DirectedEdge_AED2 e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
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
            for (DirectedEdge_AED2 e : G.adj(v)) {
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
            DirectedEdge_AED2 e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }



}