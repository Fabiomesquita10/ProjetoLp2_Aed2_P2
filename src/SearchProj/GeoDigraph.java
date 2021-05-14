package SearchProj;

import SearchProj.BSP_AED2;
import SearchProj.DirectedEdge_AED2;
import SearchProj.EdgeWeightedDiGraph_AED2;
import edu.princeton.cs.algs4.BellmanFordSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.TransitiveClosure;
import projeto_LP2_AED2.Cache;
import Search.BST_AED2_2021;

public class GeoDigraph extends EdgeWeightedDiGraph_AED2 {

    private int numCache;
    public BST_AED2_2021<Integer, Cache> caches = new BST_AED2_2021<>();


    public int getNumCache() {
        return numCache;
    }

    public void setNumCache(int numCache) {
        this.numCache = numCache;
    }

    public BST_AED2_2021<Integer, Cache> getCaches() {
        return caches;
    }

    public void setCaches(BST_AED2_2021<Integer, Cache> caches) {
        this.caches = caches;
    }

    public GeoDigraph(GeoDigraph gDg) {
        super(gDg);
    }

    public GeoDigraph(int nVertices){
        super(nVertices);
    }

    public GeoDigraph(GeoDigraph gDg, int newSize){
        super(newSize);
        for (int v = 0; v < gDg.V(); v++) {
            for (DirectedEdge_AED2 adj2: gDg.adj(v)){
                this.addEdge(adj2);
            }
        }
    }

    public boolean containsEdge(int v, int a){
        for(DirectedEdge_AED2 adj2: this.adj(v))
            if(adj2.to() == a) return true;

        return false;
    }

    public boolean adicionaCache(Cache cache) {
        getCaches().put(numCache, cache);
        cache.setIdCache(numCache); //set ID(ID criado/adicionado consoante a variável numCache)
        caches.put(numCache,cache); //adicionar a nova cache na RedBlack de Caches
        numCache++;
        //iteramos a cada nova adição de uma cache(essencial para a criação dos ID´s)
        // codigo novo
        new GeoDigraph(this,numCache);
        return true;
    }

    //ainda temos de pensar melhor nisto, ou falar com os stores
    /*
    public boolean removeCache(Integer idCache){
        if(caches.contains(idCache)){ //verificamos se a mesma existe na RedBlack de caches
            caches.delete(idCache); //removemos a cache especifica
            numCache--;
            for(int i=0; i<this.V(); i++){

            }
            return true;
        }
        return false;
    }*/



    //TransitiveClosure tc = new TransitiveClosure(grafo);
    //BellmanFordSP
    public static void main(String[] args) {
        GeoDigraph go = new GeoDigraph(4);
        Cache c1 = new Cache(4,4,4,"lisboa");
        Cache c2 = new Cache(4,4,4,"cete");
        Cache c3 = new Cache(4,4,4,"gaia");
        Cache c4 = new Cache(4,4,4,"feira");
        c1.setIdCache(0);
        c2.setIdCache(1);
        c3.setIdCache(2);
        c4.setIdCache(3);
        go.getCaches().put(0, c1);
        go.getCaches().put(1, c2);
        go.getCaches().put(2, c3);
        go.getCaches().put(3, c4);
        go.setNumCache(4);

        DirectedEdge_AED2 d1 = new DirectedEdge_AED2(0,1,20.0, 10, 20, 30);
        DirectedEdge_AED2 d2 = new DirectedEdge_AED2(1,2,30.0, 20, 250, 310);
        DirectedEdge_AED2 d3 = new DirectedEdge_AED2(2,3,40.0, 30, 220, 340);
        go.addEdge(d1);
        go.addEdge(d2);
        go.addEdge(d3);
        System.out.println(go.retString());
        System.out.println();
        go.toString();
        GeoDigraph go2 = new GeoDigraph(go, 5);
        System.out.println(go2.retString());
        System.out.println();

        System.out.println(go2.containsEdge(0,1));
        System.out.println(go2.containsEdge(1,0));

        System.out.println("antes de add");
        go.getCaches().printInOrder(go.getCaches().getRoot());

        Cache c = new Cache(4,5,6,"porto");
        go.adicionaCache(c);
        System.out.println("depois de add");
        go.getCaches().printInOrder(go.getCaches().getRoot());

        BSP_AED2 BSP = new BSP_AED2(go, 1);
        System.out.println();


        if(BSP.distTo(0) == Double.POSITIVE_INFINITY){
            System.out.println("impossivel, nao ha caminho");
        }else
            System.out.println(BSP.distTo(0));
        System.out.println(BSP.distTo(3));

        if(BSP.hasPathTo(0)){
            System.out.println(BSP.distTo(0));
        }



    }
}


