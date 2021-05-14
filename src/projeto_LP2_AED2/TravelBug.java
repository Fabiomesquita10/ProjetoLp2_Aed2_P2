package projeto_LP2_AED2;

import Search.BST_AED2_2021;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.util.ArrayList;
import java.util.Random;

public class TravelBug extends Objeto {
    //FIELDS/CAMPOS
    private boolean viajar;
    public String missao;
    private BST_AED2_2021<Integer, PremiumCache> listaCachesPresente = new BST_AED2_2021<>();
    private BST_AED2_2021<Integer, Date> datas = new BST_AED2_2021<>();
    private BST_AED2_2021<Integer, Aventureiro> listaAventureiros = new BST_AED2_2021<>();
    private int numCachesPres = 1;
    private int numAventureiros;
    private ArrayList<Cache> TbMission = new ArrayList<>();
    private int time;
    private int demorou;

    //GETTERS AND SETTERS
    public int getDemorou() {
        return demorou;
    }

    public void setDemorou(int demorou) {
        this.demorou = demorou;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public BST_AED2_2021<Integer, Date> getDatas() {
        return datas;
    }

    public void setDatas(BST_AED2_2021<Integer, Date> datas) {
        this.datas = datas;
    }

    public ArrayList<Cache> getTbMission() {
        return TbMission;
    }

    public void setTbMission(ArrayList<Cache> tbMission) {
        TbMission = tbMission;
    }

    public boolean isViajar() {
        return viajar;
    }

    public void setViajar(boolean viajar) {
        this.viajar = viajar;
    }

    public String getMissao() {
        return missao;
    }

    public int getNumCachesPres() {
        return numCachesPres;
    }

    public void setNumCachesPres(int numCachesPres) {
        this.numCachesPres = numCachesPres;
    }

    public BST_AED2_2021<Integer, PremiumCache> getListaCachesPresente() {
        return listaCachesPresente;
    }

    public void setListaCachesPresente(BST_AED2_2021<Integer, PremiumCache> listaCachesPresente) {
        this.listaCachesPresente = listaCachesPresente;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public BST_AED2_2021<Integer, Aventureiro> getListaAventureiros() {
        return listaAventureiros;
    }

    public void setListaAventureiros(BST_AED2_2021<Integer, Aventureiro> listaAventureiros) {
        this.listaAventureiros = listaAventureiros;
    }

    public int getNumAventureiros() {
        return numAventureiros;
    }

    public void setNumAventureiros(int numAventureiros) {
        this.numAventureiros = numAventureiros;
    }

    /**
     * Construtor que recebe só o nome do TravelBug
     * @param nome - nome do TB
     */
    public TravelBug(String nome) {
        super(nome);
        Random num = new Random();
        int posicao = num.nextInt(9);
        lerMissao(3);
    }

    /**
     * Construtor que recebe o nome e missao do TravelBug
     * @param nome - nome do TB
     * @param m - Missao do TB
     */
    public TravelBug(String nome, String m) {
        super(nome);
        missao = m;
    }

    public void lerMissao(int posicao) {
        In infile = new In("data/Missoes.txt");
        String[] missoes = infile.readAllLines();
        int x = 0;
        while (missoes.length > x) {
            String[] parts = missoes[x].split("-");
            int pos = Integer.parseInt(parts[0]);
            StringBuilder m = new StringBuilder();
            if (pos == posicao) {
                m.append(parts[0]).append(" ").append(parts[1]);
                missao = m.toString();
                x = missoes.length;
            }
            x++;
        }
    }

    /**
     * Método para interpretar missão
     * @param gc - Acesso a todas as Caches
     * @param ga - Acesso a todos os Aventureiros
     * @return
     */
    public ArrayList<Cache> interpetarMissao(GestaoAcessoCache gc, GestaoAcessoAventureiro ga) {
        String[] parts = missao.split(" ");
        Integer idMissao = Integer.parseInt(parts[0]);
        ArrayList<Cache> tempCache = new ArrayList<>();
        switch (idMissao) {
            case 1:
                int x = 1, k = 1, id = 0;
                double dist = 0;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        if (this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal()) > dist) { //calcula-mos a distancia mais longe
                            if (gc.getCaches().get(x) instanceof PremiumCache) { //se for PremiumCache
                                dist = this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal());
                                id = x;
                            }
                        }
                        k++;
                    }
                    x++;
                }
                tempCache.add(gc.getCaches().get(id));
                TbMission = tempCache;
                return tempCache;
            case 2:
                x = 1;
                k = 1;
                dist = 1000000;
                id = -1;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        if (this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal()) < dist && this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal()) > 0) { //calcula-mos a distancia mais curta
                            dist = this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal());
                            id = x;
                        }
                        k++;
                    }
                    x++;
                }
                tempCache.add(gc.getCaches().get(id));
                TbMission = tempCache;
                return tempCache;
            case 3:
                ArrayList<String> regioes = new ArrayList<>(); //array que contem as regiões que temos
                x = 1;
                k = 1;
                String local;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        local = gc.getCaches().get(x).getLocal().getLocalizacao();
                        regioes.add(local);
                        k++;
                    }
                    x++;
                }
                Random rand = new Random();
                int posicao = rand.nextInt(regioes.size()); //randomiza-mos uma região entre as que temos
                k = 1;
                x = 1;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        if(gc.getCaches().get(x).getLocal().getLocalizacao().equals(regioes.get(posicao))){
                            tempCache.add(gc.getCaches().get(x));
                        }
                        k++;
                    }
                    x++;
                }
                TbMission = tempCache;
                return tempCache;
            case 4:
                ArrayList<String> regiao = new ArrayList<>(); //array que contem as regiões que temos
                x = 1;
                k = 1;
                String loc;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        loc = gc.getCaches().get(x).getLocal().getLocalizacao();
                        regiao.add(loc);
                        k++;
                    }
                    x++;
                }
                Random r = new Random();
                int pos = r.nextInt(regiao.size());
                k = 1;
                x = 1;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        if(!gc.getCaches().get(x).getLocal().getLocalizacao().equals(regiao.get(pos))){ //se for diferente da região
                            tempCache.add(gc.getCaches().get(x));
                        }
                        k++;
                    }
                    x++;
                }
                TbMission = tempCache;
                return tempCache;
            case 5:
                int j = 1;
                while (gc.getCaches().size() >= j){
                    if(!gc.getCaches().get(j).getIdCache().equals(getListaCachesPresente().get(numCachesPres-1).getIdCache()))
                        tempCache.add(gc.getCaches().get(j));
                    j++;
                }
                time = 1;
                TbMission = tempCache;
                return tempCache;
            case 6:
                x = 1;
                k = 1;
                dist = 1000000;
                id = -1;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        if (this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal()) < dist && this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal()) > 0) { //distancia mais curta
                            dist = this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal());
                            id = x;
                        }k++;
                    }x++;
                }
                time = 1;
                tempCache.add(gc.getCaches().get(id));
                TbMission = tempCache;
                return tempCache;
            case 7:
                x = 1;
                k = 1;
                dist = 0;
                id = 0;
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(x) != null) {
                        if (this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal()) > dist) { //distancia mais longe
                            if (gc.getCaches().get(x) instanceof PremiumCache) { //tem de ser Premium
                                dist = this.getCache().getLocal().distancia(gc.getCaches().get(x).getLocal());
                                id = x;
                            }
                        }
                        k++;
                    }
                    x++;
                }
                tempCache.add(gc.getCaches().get(id));
                TbMission = tempCache;
                time = 7;
                return tempCache;
            case 8:
                if (this.listaAventureiros.get(numAventureiros-1).getListCacheVisit().get(numCachesPres-1) == null) { //nao visitou nenhuma cache
                    System.out.println("Visto que não tens ainda nenhuma cache visitada, leva o TravelBug para uma cache qualquer.");
                }
                else if(this.listaAventureiros.get(numAventureiros-1).getListCacheVisit().get(numCachesPres-1) != null) {
                    tempCache.add(gc.getCaches().get(this.listaAventureiros.get(numAventureiros-1).getListCacheVisit().get(numCachesPres-1).getIdCache()));
                }
                TbMission = tempCache;
                return tempCache;
            case 9:
                if (this.listaAventureiros.get(numAventureiros-1).getListCacheVisit().get(numCachesPres-1) == null) { //se for a primeira Cache que visitou
                    System.out.println("Visto que esta é a tua primeira cache visitada, leva o TravelBug para uma cache qualquer.");
                }
                else if(this.listaAventureiros.get(numAventureiros-1).getListCacheVisit().get(numCachesPres-1) == null){
                    tempCache.add(gc.getCaches().get(this.listaAventureiros.get(numAventureiros-1).getListCacheVisit().get(0).getIdCache()));
                }
                TbMission = tempCache;
                return tempCache;
            case 10:
                x = 0;
                k = 1;
                int cache;
                ArrayList<Integer> cacheNpresentes = new ArrayList<>(); //array que guarda as Caches que o Aventureiro ainda não teve presente
                while (k <= gc.getCaches().size()) {
                    if (gc.getCaches().get(k) != null) {while (this.getListaAventureiros().get(numAventureiros - 1).getListCacheVisit().size() > x) {
                            if (!this.getListaAventureiros().get(numAventureiros - 1).getListCacheVisit().get(x).getIdCache().equals(gc.getCaches().get(k).getIdCache())) { //saber as Caches que ainda nao tive
                                cache = gc.getCaches().get(k).getIdCache();
                                cacheNpresentes.add(cache);
                            }
                            x++;
                        }
                        x = 0;
                    }
                    k++;
                }
                Random a = new Random();
                int posi = a.nextInt(cacheNpresentes.size());
                tempCache.add(gc.getCaches().get(cacheNpresentes.get(posi))); //escolhemos uma random entre as que não visitou e retornámo-la
                TbMission = tempCache;
                return tempCache;
            default:
                System.out.println("erro");
        }
        return null;
    }


    /**
     * Método toString do TravelBug
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + ", missao: " + missao;
    }
}