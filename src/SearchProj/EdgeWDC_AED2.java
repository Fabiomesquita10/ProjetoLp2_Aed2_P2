//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package SearchProj;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import SearchProj.DirectedEdge_AED2;
import SearchProj.EdgeWeightedDiGraph_AED2;

import java.util.Iterator;

public class EdgeWDC_AED2 {
    private boolean[] marked;
    private DirectedEdge_AED2[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge_AED2> cycle;

    public EdgeWDC_AED2(EdgeWeightedDiGraph_AED2 G) {
        this.marked = new boolean[G.V()];
        this.onStack = new boolean[G.V()];
        this.edgeTo = new DirectedEdge_AED2[G.V()];

        for(int v = 0; v < G.V(); ++v) {
            if (!this.marked[v]) {
                this.dfs(G, v);
            }
        }

        assert this.check();

    }

    private void dfs(EdgeWeightedDiGraph_AED2 G, int v) {
        this.onStack[v] = true;
        this.marked[v] = true;
        Iterator var3 = G.adj(v).iterator();

        while(var3.hasNext()) {
            DirectedEdge_AED2 e = (DirectedEdge_AED2)var3.next();
            int w = e.to();
            if (this.cycle != null) {
                return;
            }

            if (!this.marked[w]) {
                this.edgeTo[w] = e;
                this.dfs(G, w);
            } else if (this.onStack[w]) {
                this.cycle = new Stack();

                DirectedEdge_AED2 f;
                for(f = e; f.from() != w; f = this.edgeTo[f.from()]) {
                    this.cycle.push(f);
                }

                this.cycle.push(f);
                return;
            }
        }

        this.onStack[v] = false;
    }

    public boolean hasCycle() {
        return this.cycle != null;
    }

    public Iterable<DirectedEdge_AED2> cycle() {
        return this.cycle;
    }

    private boolean check() {
        if (this.hasCycle()) {
            DirectedEdge_AED2 first = null;
            DirectedEdge_AED2 last = null;

            DirectedEdge_AED2 e;
            for(Iterator var3 = this.cycle().iterator(); var3.hasNext(); last = e) {
                e = (DirectedEdge_AED2) var3.next();
                if (first == null) {
                    first = e;
                }

                if (last != null && last.to() != e.from()) {
                    System.err.printf("cycle edges %s and %s not incident\n", last, e);
                    return false;
                }
            }

            if (last.to() != first.from()) {
                System.err.printf("cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        EdgeWeightedDiGraph_AED2 G = new EdgeWeightedDiGraph_AED2(V);
        int[] vertices = new int[V];

        int i;
        for(i = 0; i < V; vertices[i] = i++) {
        }

        StdRandom.shuffle(vertices);

        int v;
        int w;
        double weight;
        for(i = 0; i < E; ++i) {
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while(v >= w);

            weight = StdRandom.uniform();
            G.addEdge(new DirectedEdge_AED2(v, w, weight,0,0,0));
        }

        for(i = 0; i < F; ++i) {
            v = StdRandom.uniform(V);
            w = StdRandom.uniform(V);
            weight = StdRandom.uniform(0.0D, 1.0D);
            G.addEdge(new DirectedEdge_AED2(v, w, weight,0,0,0));
        }

        StdOut.println(G);
        EdgeWDC_AED2 finder = new EdgeWDC_AED2(G);
        if (finder.hasCycle()) {
            StdOut.print("Cycle: ");
            Iterator var11 = finder.cycle().iterator();

            while(var11.hasNext()) {
                DirectedEdge_AED2 e = (DirectedEdge_AED2) var11.next();
                StdOut.print(e + " ");
            }

            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }

    }
}
