package SearchProj;

import SearchProj.BSP_AED2;
import SearchProj.DirectedEdge_AED2;
import SearchProj.EdgeWeightedDiGraph_AED2;
import edu.princeton.cs.algs4.*;
import projeto_LP2_AED2.*;
import Search.BST_AED2_2021;

public class GeoDigraph extends EdgeWeightedDiGraph_AED2 {

    private int numCache = 1;
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
        getCaches().put(cache.getIdCache(), cache);
        //cache.setIdCache(numCache); //set ID(ID criado/adicionado consoante a variável numCache)
        caches.put(cache.getIdCache(),cache); //adicionar a nova cache na RedBlack de Caches
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
    public static void main(String[] args) throws AventureiroNaoHabilitado {
        GeoDigraph go = new GeoDigraph(7);
        PremiumCache c1 = new PremiumCache(4,new Premium("favio",2,3,"porto"),new TravelBug("asdasd", "oirt"),4,4,"lisboa");
        PremiumCache c2 = new PremiumCache(4,new Premium("daasd",2,3,"porto"),new TravelBug("asdasd", "oirt"),4,4,"lisboa");
        PremiumCache c3 = new PremiumCache(4,new Premium("asdasdasd",2,3,"porto"),new TravelBug("asdasd", "oirt"),4,4,"lisboa");
        PremiumCache c4 = new PremiumCache(4,new Premium("asdsad",2,3,"porto"),new TravelBug("asdasd", "oirt"),4,4,"lisboa");
        PremiumCache c5 = new PremiumCache(4,new Premium("asdasd",2,3,"porto"),new TravelBug("asdasd", "oirt"),4,4,"lisboa");
        PremiumCache c6 = new PremiumCache(4,new Premium("asdaxdzxc",2,3,"porto"),new TravelBug("asdasd", "oirt"),4,4,"lisboa");
        PremiumCache c7 = new PremiumCache(4,new Premium("asdszxczxcad",2,3,"porto"),new TravelBug("asdasd", "oirt"),4,4,"lisboa");
        c1.setIdCache(0);
        c2.setIdCache(1);
        c3.setIdCache(2);
        c4.setIdCache(3);
        c5.setIdCache(4);
        c6.setIdCache(5);
        c7.setIdCache(7);
        go.getCaches().put(0, c1);
        go.getCaches().put(1, c2);
        go.getCaches().put(2, c3);
        go.getCaches().put(3, c4);
        go.getCaches().put(4, c5);
        go.getCaches().put(5, c6);
        go.getCaches().put(6, c7);
        go.setNumCache(7);

        DirectedEdge_AED2 d1 = new DirectedEdge_AED2(0,1,20.0, 10, 20, 30);
        DirectedEdge_AED2 d2 = new DirectedEdge_AED2(1,2,30.0, 20, 250, 310);
        DirectedEdge_AED2 d3 = new DirectedEdge_AED2(2,3,40.0, 30, 220, 340);
        DirectedEdge_AED2 d4 = new DirectedEdge_AED2(0,2,50.0, 40, 320, 440);
        DirectedEdge_AED2 d5 = new DirectedEdge_AED2(2,5,60.0, 50, 420, 540);
        DirectedEdge_AED2 d6 = new DirectedEdge_AED2(5,1,70.0, 60, 520, 686);
        DirectedEdge_AED2 d7 = new DirectedEdge_AED2(0,5,80.0, 70, 13, 78);
        DirectedEdge_AED2 d8 = new DirectedEdge_AED2(1,6,90.0, 80, 510, 642);
        DirectedEdge_AED2 d9 = new DirectedEdge_AED2(6,2,110.0, 90, 432, 543);
        DirectedEdge_AED2 d10 = new DirectedEdge_AED2(2,4,120.0, 110, 23, 21);
        DirectedEdge_AED2 d11 = new DirectedEdge_AED2(4,5,130.0, 100, 51, 642);
        go.addEdge(d1);
        go.addEdge(d2);
        go.addEdge(d3);
        go.addEdge(d4);
        go.addEdge(d5);
        go.addEdge(d6);
        go.addEdge(d7);
        go.addEdge(d8);
        go.addEdge(d9);
        go.addEdge(d10);
        go.addEdge(d11);
        System.out.println(go.retString());
        System.out.println();
        go.toString();
        GeoDigraph go2 = new GeoDigraph(go, 8);
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


        int from = 1;
        int to = 5;
        BSP_AED2 BSP = new BSP_AED2(go, from);
        DFS_AED2 dfp = new DFS_AED2(go, from);//inutil pode nao ser inutil
        BFS_AED2 BFS = new BFS_AED2(go, from);
        System.out.println();

        System.out.println("\nTeste de DFS e BSP:");
        if(BSP.hasPathTo(to)) {
            System.out.println("O caminho entre o vertice: " + from + " e o vertice: " + to +":");
            System.out.println("Peso: "+BSP.distTo(to));
            System.out.println("Distancia: "+BSP.distTo2(to));
            System.out.println("Tempo: "+BSP.tempoTo(to));
            System.out.println("Elevacao: "+BSP.elevTo(to));
            System.out.println("Se tem caminho: "+dfp.hasPathTo(to));
            System.out.println("Caminho dfp: "+dfp.pathTo(to));
            System.out.println("Caminho BSP: "+BSP.p(to));
            System.out.println("Caminho BFS: "+BFS.pathTo(to));
        }
        System.out.println("\nTodos os edges: ");
        for (DirectedEdge_AED2 d: go.edges()){
            System.out.print(d);
        }

        if(BSP.distTo(0) == Double.POSITIVE_INFINITY){
            System.out.println("impossivel, nao ha caminho");
        }else
            System.out.println(BSP.distTo(0));
    }

    public int[][] graphToMatrix(){
        int[][] matrix = new int[this.V()][this.V()];
        for (int i = 1; i < this.V(); i++) {
            for (DirectedEdge_AED2 adj : adj(i)){
                matrix[i][adj.to()] = adj.tempo();
            }
        }
        return matrix;
    }

    public void printMatrix(int[][]matrix){
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("|");
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("|");
        }
    }
}


