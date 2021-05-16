package SearchProj;

import Search.RedBlack_AED2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;


public class GestaoAcessoCacheGraph {


    private int numCache;
    private GeoDigraph grafo = new GeoDigraph(numCache);

    //TransitiveClosure tc = new TransitiveClosure(grafo);
    //BellmanFordSP

    //GETTERS AND SETTERS
    public int getNumCache() {
        return numCache;
    }

    public void setNumCache(int numCache) {
        this.numCache = numCache;
    }

    public GeoDigraph getGrafo() {
        return grafo;
    }

    public void setGrafo(GeoDigraph grafo) {
        this.grafo = grafo;
    }

    public void lerCaminhos(){
        In infile = new In("data/caminhos.txt");
        String line = null;
        while ((line = infile.readLine())!=null){
            String parts[] = line.split(";");
            int v = Integer.parseInt(parts[0]);
            int e = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            int t = Integer.parseInt(parts[3]);
            int d = Integer.parseInt(parts[4]);
            int p = Integer.parseInt(parts[5]);
            DirectedEdge_AED2 de = new DirectedEdge_AED2(v,e,w,t,d,p);
            grafo.addEdge(de);
        }
    }

    public void guardarCaminhos(){
        Out outfile = new Out("data/caminhos.txt");
        //funcao para guardar os edges
    }

    /*
    public void adicionarCaminhos(int id , int[] edges){
        for (int i = 0; i < edges.length; i++) {
            grafo.addEdge(id, edges[i]);
        }
    }


    public int verMenorCaminho(int v, int e){
        if(getGrafo().containsEdge(v, e)){
            return (int)getCaches().get(v).getLocal().distancia(getCaches().get(e).getLocal());
        }else{
            int num = Integer.parseInt(String.valueOf(getGrafo().adj(v))); //  num de edges que tem cada vertice(unica forma que consegui chegar a este valor)
            return -1;
        }
    }

    public boolean removeCache(Integer idCache){
        if(caches.contains(idCache)){ //verificamos se a mesma existe na RedBlack de caches
            caches.delete(idCache); //removemos a cache especifica
            numCache--;
            //grafo = new GeoGraph(grafo, numCache, this, idCache);
            for(int i=0; i<grafo.V(); i++){
                if(i != idCache){
                    grafo.positionsX[i] = grafo.positionsX[i];
                    grafo.positionsY[i] = grafo.positionsY[i];
                }else{
                    grafo.positionsX[i] = 0;
                    grafo.positionsY[i] = 0;
                }
            }
            return true;
        }
        return false;
    }


    public boolean existeCache(Integer idCache) {
        if(caches.contains(idCache)){ //se o array contêm a cache recebida retornamos True
            return true;
        }
        return false;
    }*/
}
