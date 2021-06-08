package projeto_LP2_AED2;

public class Admin extends Aventureiro {
    /**
     * Construtor do Admin
     * @param nome do admin
     * @param x - localizacao x
     * @param y - localizacao y
     * @param local localizacao
     */
    public Admin(String nome, int x, int y, String local, String pass) {
        super(nome, x, y, local, pass);
    }
    public Admin(String nome, int x, int y, String local) {
        super(nome, x, y, local);
    }
    /**
     * Metodo em que o admin tem acesso a todas as caches
     * @param gc - acesso a todas as caches
     */
    public void verTodasCaches(GestaoAcessoCache gc){
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
    }

    /**
     * Metodo em que o admin tem acesso a todos os aventureiros
     * @param ga - acesso a todas os aventureiros
     */
    public void verTodosAventureiros(GestaoAcessoAventureiro ga){
        ga.getAventureiros().printInOrder(ga.getAventureiros().getRoot());
    }

    /**
     * Metodo para ver localizacao de uma cache em especifico
     * @param c - a cache que queremos saber a localizaçao
     */
    public void verLocalizacaoCache(Cache c){
        System.out.println("coordenadas x: " + c.getLocal().getCoordenadaX() + ", coordenados y: " + c.getLocal().getCoordenadaY());
    }

    /**
     * Metodo para ver localizacao de um aventureiro em especifico
     * @param a - o aventureiro que queremos saber a localizaçao
     */
    public void verLocalizacaoAventureiro(Aventureiro a){
        System.out.println("coordenadas x: " + a.getLocal().getCoordenadaX() + ", coordenados y: " + a.getLocal().getCoordenadaY());
    }

    /**
     * Metodo para ver localizaçao de um travelBug em especifico
     * @param go - acesso a todos os objetos
     * @param id - id do travelbug
     */
    public void verLocalizacaoTravelBug(GestaoAcessoObjeto go, int id){
        if(go.getTravelBug().get(id)!=null){ //  ve se o travelBug existe
            if(go.getTravelBug().get(id).isViajar()){ // se tiver a viajar
                System.out.println("Esta a viajar com o aventureiro: " + go.getTravelBug().get(id).getListaAventureiros().get(go.getTravelBug().get(id).getNumAventureiros()-1));
            }else{
                System.out.println("Esta na cache " + go.getTravelBug().get(id).getListaCachesPresente().get(go.getTravelBug().get(id).getNumCachesPres()-1) + " e esta localizado em " + go.getTravelBug().get(id).getListaCachesPresente().get(go.getTravelBug().get(id).getNumCachesPres()-1).getLocal().getLocalizacao());
            }
        }
    }

    /**
     * Metodo me que o admin pode ver todas as caches que estao disponiveis numa regiao
     * @param gc - acesso a todas as caches
     * @param local - local onde vamos ver todas as caches
     */
    public void verTodasCachesRegiao(GestaoAcessoCache gc, String local){
        int x = 1;
        while(gc.getCaches().size() >= x){ // percorre a redBlack de cache
            if(gc.getCaches().get(x).getLocal().getLocalizacao().equals(local)){ // se a localizacao da cache for igual a que pretendemos damos print
                System.out.println(gc.getCaches().get(x));
            }
            x++;
        }
    }

    /**
     * Metodo em que o admin consegue ver todos os objetos do tipo objeto
     * @param go - acesso a todos os objetos
     */
    public void verTodosOsObjetos(GestaoAcessoObjeto go){
        int x = 1;
        System.out.println("Objetos: ");
        while(go.getObjetos().size() >= x) { // percorre a BST de objetos
            System.out.println(go.getObjetos().get(x).getNome());
            x++;
        }
    }

    /**
     * Metodo em que o admin consegue ver todos os travelBug
     * @param go - acesso a todos os objetos
     */
    public void verTodosOsTravelBug(GestaoAcessoObjeto go){
        int x = 1;
        System.out.println("TravelBugs: ");
        while(go.getTravelBug().size() >= x) { //  percorre a BST de travelBug
            System.out.println(go.getTravelBug().get(x).getNome());
            x++;
        }
    }

}