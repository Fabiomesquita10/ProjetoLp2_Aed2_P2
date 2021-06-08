
package SearchProj;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.StdOut;

public class DirectedEdge_AED2 extends DirectedEdge {
    private final int distancia;
    private final int tempo;
    private final int elevacao;

    public DirectedEdge_AED2(int v, int w, double weight, int t, int d, int e) {
        super(v, w, weight);
        this.tempo = t;
        this.distancia = d;
        this.elevacao = e;
    }

    public int distancia(){return  distancia;}
    public int elevacao(){return  elevacao;}
    public int tempo(){return  tempo;}

    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    @Override
    public String toString() {
        return super.toString() +
                ", distancia: " + distancia +
                ", tempo: " + tempo +
                ", elevacao: " + elevacao + "\n";
    }
}
