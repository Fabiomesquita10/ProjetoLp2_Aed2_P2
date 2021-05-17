package projeto_LP2_AED2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JaExisteObjetoNumaCacheException, AventureiroNaoExisteException, CacheNaoExisteException, AventureiroNaoHabilitado, ParseException, MissaoNaoCompletadaComExitoException {
        GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
        GestaoAcessoCache gc = new GestaoAcessoCache();
        GestaoAcessoObjeto go = new GestaoAcessoObjeto();
        //clientTeste1(ga, gc, go);
        //clientTeste2(ga, gc, go);
        //clientTeste3(ga, gc, go);
        //clientTeste4(ga, gc, go);
        //clientTeste5(ga, gc, go);
        //clientTeste6(ga, gc, go);
        //clientTeste7(ga, gc, go);
        //clientTeste8(ga, gc, go);
        //clientTeste9(ga, gc, go);
        //clientTeste10(ga, gc, go);
        //clientTeste11(ga, gc, go);
        //clientTeste12(ga, gc, go);
        //clientTeste13(ga, gc, go, 1);
        clientTeste14(ga, gc, go);
    }

    private static void clientTeste14(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);

        ga.guardarAventBin();
    }

    public static void clientTeste1(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoExisteException, CacheNaoExisteException, AventureiroNaoHabilitado {

        Premium a1 = new Premium("jonas", 1, 2, "asdasd");
        Premium a2 = new Premium("carlos", 3, 4, "asdasd");
        Premium a3 = new Premium("miguel", 5, 6, "asdasd");
        Premium a4 = new Premium("antonio", 5, 6, "asdasd");
        Admin a5 = new Admin("roscas", 8, 9, "lisboa");
        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        ga.regista(a4);
        ga.regista(a5);

        Objeto pilha = new Objeto("pilha");
        pilha.setIdObjeto(1);
        Objeto corno = new Objeto("corno");
        corno.setIdObjeto(2);
        Objeto gato = new Objeto("gato");
        gato.setIdObjeto(3);
        Cache c1 = new Cache(5, a1, pilha, 1, 3, "porto");
        Cache c2 = new Cache(5, a2, corno, 4, 5, "porto");
        Cache c3 = new Cache(5, a2, gato, 5, 6, "lisboa");

        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.adicionaCache(c3);

        Objeto corda = new Objeto("corda");

        //a2.encontrouCache(c1, corda, new Date());
        System.out.println("====================Lista objetos no a2============================");
        a2.getListObjetos().printInOrder(a2.getListObjetos().getRoot());
        System.out.println("===================================================================");
        System.out.println("====================Estado das caches==============================");
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        System.out.println("===================================================================");
        a2.encontrouCache(c1, new Date());
        a2.encontrouCache(c2, new Date());
        a2.encontrouCache(c3, new Date());
        System.out.println("=====================Lista objetos no a2===========================");
        a2.getListObjetos().printInOrder(a2.getListObjetos().getRoot());
        System.out.println("===================================================================");
        System.out.println("=====================Estado das caches=============================");
        System.out.println(gc.getCaches().get(1).getObjeto().toString());
        System.out.println("===================================================================");

    }
    public static void clientTeste2(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) {
        System.out.println("MACACO");
    }
    public static void clientTeste3(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        Basic a1 = new Basic("jonas", 1, 2, "porto");
        Admin a2 = new Admin("coxis", 1, 2, "lisboa");
        Premium a3 = new Premium("jota", 3, 4, "asdasd");
        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        Objeto pilha = new Objeto("pilha");
        Cache c2 = new Cache(4, a2, pilha, 1, 5, "porto");
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        ga.getAventureiros().printInOrder(ga.getAventureiros().getRoot());
    }
    public static void clientTeste4(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        TravelBug tb1 = new TravelBug("oculos");
        TravelBug tb2 = new TravelBug("corda");
        TravelBug tb3 = new TravelBug("piscina");
        TravelBug tb4 = new TravelBug("monitor");
        TravelBug tb5 = new TravelBug("estojo", "Dar uma cabecada ao kinito");

        Premium a1 = new Premium("jota", 3, 4, "asdasd");
        ga.regista(a1);

        PremiumCache pc = new PremiumCache(3, a1, tb1, 1, 2, "porto");
        gc.adicionaCache(pc);

        System.out.println("\n\n\n\n");

        gc.getCaches().printInOrder(gc.getCaches().getRoot());


        System.out.println("\n\n\n");
        Objeto o = new Objeto("mosca");

        go.regista(tb1);
        go.regista(tb2);
        go.regista(tb3);
        go.regista(tb4);
        go.regista(tb5);
        go.regista(o);
        go.getObjetos().printInOrder(go.getObjetos().getRoot());
    }
    public static void clientTeste5(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        Premium a1 = new Premium("jonas", 1, 2, "asdasd");
        Premium a2 = new Premium("carlos", 3, 4, "asdasd");
        Premium a3 = new Premium("miguel", 5, 6, "asdasd");
        Premium a4 = new Premium("antonio", 5, 6, "asdasd");
        Admin a5 = new Admin("roscas", 8, 9, "lisboa");
        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        ga.regista(a4);
        ga.regista(a5);
        Objeto pilha = new Objeto("pilha");
        Objeto corno = new Objeto("corno");
        Objeto gato = new Objeto("gato");
        Cache c1 = new Cache(5, a1, pilha, 1, 3, "porto");
        Cache c2 = new Cache(5, a2, corno, 4, 5, "porto");
        Cache c3 = new Cache(5, a2, gato, 5, 6, "lisboa");
        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.adicionaCache(c3);

        a1.encontrouCache(c2, corno, new Date());
        a1.getListCacheVisit().printInOrder(a1.getListCacheVisit().getRoot());

        System.out.println("\n\tteste das funcoes do admin\n");
        System.out.println("\n\t\tVER LOCALIZACAO DE CACHE ESPECIFICA: ");
        a5.verLocalizacaoCache(c1);
        System.out.println("\n\t\tVER LOCALIZACAO DE AVENTUREIRO ESPECIFICO: ");
        a5.verLocalizacaoAventureiro(a1);
        System.out.println("\n\t\tVER TODAS AS CACHES: ");
        a5.verTodasCaches(gc);
        System.out.println("\n\t\tVER TODOS OS AVENTUREIROS: ");
        a5.verTodosAventureiros(ga);
        System.out.println("\n\t\tVER CACHE POR REGIAO: ");
        a5.verTodasCachesRegiao(gc, "lisboa");
        System.out.println("\n\t\tVER TODAS AS CACHES VISITADAS POR UM AVENTUREIRO: ");
        ga.PrintTodasCachesVisitadas(1);
        System.out.println("\n\tFIM\n");

    }
    //missoes
    public static void clientTeste6(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException, MissaoNaoCompletadaComExitoException {
        ArrayList<Cache> cachesRet = new ArrayList<>();
        Premium fabio = new Premium("fabio", 12, 4, "asdasd");
        Premium goncalo = new Premium("Goncalo", 2, 5, "asdasd");
        ga.regista(fabio);
        ga.regista(goncalo);

        TravelBug rato = new TravelBug( "rato");
        TravelBug gato = new TravelBug("gato");
        TravelBug cao = new TravelBug( "cao");
        TravelBug passaro = new TravelBug( "passaro");
        TravelBug tb = new TravelBug( "tb");
        TravelBug tb2 = new TravelBug( "tb2");
        TravelBug tb3 = new TravelBug( "tb3");
        TravelBug tb4 = new TravelBug( "tb4");
        TravelBug tb5 = new TravelBug( "tb5");
        go.regista(rato);
        go.regista(gato);
        go.regista(cao);
        go.regista(passaro);
        go.regista(tb);
        go.regista(tb2);
        go.regista(tb3);
        go.regista(tb4);
        go.regista(tb5);

        PremiumCache cache = new PremiumCache(5, fabio, rato, 4, 6, "porto");
        PremiumCache c1 = new PremiumCache(4, fabio, gato, 21, 2, "lisboa");
        PremiumCache c2 = new PremiumCache(4, fabio, cao, 5, 2, "guarda");
        PremiumCache c3 = new PremiumCache(4, fabio, passaro, 15, 2, "penafiel");
        PremiumCache c4 = new PremiumCache(4, fabio, tb2, 15, 2, "penafiel");
        PremiumCache c5 = new PremiumCache(4, fabio, tb3, 15, 2, "penafiel");
        PremiumCache c6 = new PremiumCache(4, fabio, tb4, 15, 2, "penafiel");
        PremiumCache c7 = new PremiumCache(4, fabio, tb5, 15, 2, "penafiel");
        gc.adicionaCache(cache);
        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.adicionaCache(c3);
        gc.adicionaCache(c4);
        gc.adicionaCache(c5);
        gc.adicionaCache(c6);
        gc.adicionaCache(c7);

        System.out.println("Estados das caches antes:");
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        System.out.println();

        tb.getDatas().put(1, new Date(21, 3, 2021));
        goncalo.getListTravelBug().put(0, tb);
        goncalo.getListTravelBug().get(0).getListaAventureiros().put(goncalo.getListTravelBug().get(0).getNumAventureiros(), goncalo);
        goncalo.getListTravelBug().get(0).setNumAventureiros(goncalo.getListTravelBug().get(0).getNumAventureiros()+1);

        goncalo.encontrouCache(cache, tb, new Date(23, 3, 2021));
        System.out.println("Missao: " + goncalo.getListTravelBug().get(0).getMissao());

        System.out.println("Caches em que o rato ja esteve: ");
        rato.getListaCachesPresente().printInOrder(rato.getListaCachesPresente().getRoot());
        System.out.println();

        // se o utilizador quiser realizar a missar tera de interpetar a missao
        // mudanca de nome da funcao para AceitarMissa, so e chamada se o Aventureiro aceitar a missao
        cachesRet = goncalo.getListTravelBug().get(0).interpetarMissao(gc, ga);
        if (cachesRet.size() > 0){
            if (cachesRet.size() == 1){
                for (Cache c : cachesRet){
                    System.out.println("Pode levar para a cache com o ID: " + c.getIdCache());
                }
            }
            else{
                System.out.print("Pode levar para as caches com o ID:");
                for (Cache c : cachesRet){
                    System.out.print(" " + c.getIdCache());
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        goncalo.encontrouCache((PremiumCache)gc.getCaches().get(id), goncalo.getListTravelBug().get(0), new Date(26, 3, 2021));

        //goncalo.getListTravelBug().get(0).depositar(gc); // possivel funcao para testar as merdas
        //Resultados
        System.out.println("Caches em que o rato ja esteve: ");
        rato.getListaCachesPresente().printInOrder(rato.getListaCachesPresente().getRoot());
        System.out.println();
        System.out.println("Estados das caches depois:");
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        System.out.println();
        System.out.println();
        go.now();
        System.out.println();
    }
    public static void clientTeste7(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException {
        Premium a1 = new Premium("jonas", 1, 2, "asdasd");
        Premium a2 = new Premium("carlos", 3, 4, "asdasd");
        Premium a3 = new Premium("miguel", 5, 6, "asdasd");
        Premium a4 = new Premium("antonio", 5, 6, "asdasd");
        Admin a5 = new Admin("roscas", 8, 9, "lisboa");
        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        ga.regista(a4);
        ga.regista(a5);
        TravelBug pilha = new TravelBug("pilha", "mosca is not programmer");
        TravelBug corno = new TravelBug("corno", "kini gay");
        Objeto gato = new Objeto("gato");
        PremiumCache c1 = new PremiumCache(5, a1, pilha, 1, 3, "porto");
        PremiumCache c2 = new PremiumCache(5, a2, corno, 4, 5, "porto");
        BasicCache c3 = new BasicCache(5, a2, gato, 5, 6, "lisboa");
        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.removeCache(c2.getIdCache());
        gc.adicionaCache(c3);

        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        gc.guardarCache(ga, go);
        ga.guardarAventureiros(gc, go);
    }
    public static void clientTeste8(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException {
        Premium a1 = new Premium("jonas", 1, 2, "asdasd");
        Premium a2 = new Premium("carlos", 3, 4, "asdasd");
        Premium a3 = new Premium("miguel", 5, 6, "asdasd");
        Premium a4 = new Premium("antonio", 5, 6, "asdasd");
        Admin a5 = new Admin("roscas", 8, 9, "lisboa");

        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        ga.regista(a4);
        ga.regista(a5);

        ga.getAventureiros().printInOrder(ga.getAventureiros().getRoot());
        ga.remove(4);
        ga.getAventureiros().printInOrder(ga.getAventureiros().getRoot());
        ga.guardarAventureiros(gc, go);
    }
    public static void clientTeste9(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException, JaExisteObjetoNumaCacheException {
        Premium a1 = new Premium("jonas", 1, 2, "asdasd");
        Premium a2 = new Premium("carlos", 3, 4, "asdasd");
        Premium a3 = new Premium("miguel", 5, 6, "asdasd");
        Premium a4 = new Premium("antonio", 5, 6, "asdasd");
        Admin a5 = new Admin("roscas", 8, 9, "lisboa");

        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        ga.regista(a4);
        ga.regista(a5);

        TravelBug pilha = new TravelBug("pilha", "mosca is not programmer");
        TravelBug corno = new TravelBug("corno", "kini gay");
        Objeto gato = new Objeto("gato");
        Objeto pau = new Objeto("pau");
        Objeto tele = new Objeto("tele");

        PremiumCache c1 = new PremiumCache(5, a1, pilha, 1, 3, "porto");
        PremiumCache c2 = new PremiumCache(5, a2, corno, 4, 5, "porto");
        BasicCache c3 = new BasicCache(5, a2, gato, 5, 6, "lisboa");
        BasicCache c4 = new BasicCache(5, a2, pau, 5, 6, "porto");

        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.removeCache(c2.getIdCache());
        gc.adicionaCache(c3);
        gc.adicionaCache(c4);

        ga.getAventureiros().get(1).encontrouCache(c4, tele, new Date());

        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        gc.guardarCache(ga, go);

    }
    public static void clientTeste10(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException, JaExisteObjetoNumaCacheException, ParseException, MissaoNaoCompletadaComExitoException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Premium a1 = new Premium("jonas", 1, 2, "asdasd");
        Premium a2 = new Premium("carlos", 3, 4, "asdasd");
        Premium a3 = new Premium("joao", 4, 5, "asdasd");
        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        TravelBug pilha = new TravelBug("pilha");
        TravelBug cao = new TravelBug("cao");
        TravelBug gato = new TravelBug("gato");
        TravelBug rato = new TravelBug("gato");
        TravelBug corno = new TravelBug("corno");
        TravelBug corno2 = new TravelBug("corno2");
        TravelBug corno3 = new TravelBug("corno3");
        TravelBug corno4 = new TravelBug("corno3");
        go.regista(pilha);
        go.regista(cao);
        go.regista(gato);
        go.regista(rato);
        go.regista(corno);
        go.regista(corno2);
        go.regista(corno3);
        go.regista(corno4);
        PremiumCache c1 = new PremiumCache(5, a1, pilha, 1, 3, "porto");
        PremiumCache c2 = new PremiumCache(5, a1, cao, 1, 3, "lisboa");
        PremiumCache c3 = new PremiumCache(5, a1, gato, 1, 3, "porto");
        PremiumCache c4 = new PremiumCache(5, a1, rato, 1, 3, "porto");
        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.adicionaCache(c3);
        gc.adicionaCache(c4);
        a3.getListTravelBug().put(0, corno4);
        a2.getListTravelBug().put(0, corno);
        a2.encontrouCache(c1, corno, new Date());
        System.out.println("confirmacao");
        a2.getListTravelBug().printInOrder(a2.getListTravelBug().getRoot());
        System.out.println("fim");
        a2.encontrouCache(c2, pilha, new Date());
        a3.encontrouCache(c2, corno4, new Date());
        a3.encontrouCache(c1, pilha, new Date());
        ga.getAventureiros().printInOrder(ga.getAventureiros().getRoot());
        System.out.println();
        ga.getAventureiros().get(2).getDatas().printInOrder(ga.getAventureiros().get(2).getDatas().getRoot());
        System.out.println();
        ga.getAventureiros().get(2).getListCacheVisit().printInOrder(ga.getAventureiros().get(2).getListCacheVisit().getRoot());
        System.out.println();
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        System.out.println();
        go.getTravelBug().printInOrder(go.getTravelBug().getRoot());
        System.out.println();
        System.out.println(go.travelBugVisits());
        System.out.println();
        System.out.println(go.getTravelBug().get(go.travelBugVisits()).getNumCachesPres());
        System.out.println();
        go.getTravelBug().get(1).getListaCachesPresente().printInOrder(go.getTravelBug().get(1).getListaCachesPresente().getRoot());
        System.out.println();
        int x = 0;
        System.out.println("c1");
        while (c1.getNumAvent() > x){
            System.out.println(c1.getHistAventureiros().get(x).getNome());
            x++;
        }
        x = 0;
        System.out.println("c2");
        while (c2.getNumAvent() > x){
            System.out.println(c2.getHistAventureiros().get(x).getNome());
            x++;
        }
        x = 0;
        System.out.println("c3");
        while (c3.getNumAvent() > x){
            System.out.println(c3.getHistAventureiros().get(x).getNome());
            x++;
        }
        x = 0;
        System.out.println("c4");
        while (c4.getNumAvent() > x){
            System.out.println(c4.getHistAventureiros().get(x).getNome());
            x++;
        }
        ga.aventureirosVisitCache(c1);
        System.out.println(ga.aventureirosVisitCache(c1));
    }
    //teste leitura de ficheiros
    public static void clientTeste11(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException, JaExisteObjetoNumaCacheException, ParseException {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        System.out.println("==============================================================================");
        System.out.println("METODO NOW()");
        go.now();
        System.out.println("TOP 5 AVENTUREIROS");
        System.out.println("==============================================================================");
        ga.topAventureiros(new Date(1, 1, 2021), new Date(3, 2, 2021));
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS POR 1 AVENTUREIRO GLOBAL");
        System.out.println("==============================================================================");
        ga.verTodasCachesVisGlobal(1);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS POR 1 AVENTUREIRO REGIAO");
        System.out.println("==============================================================================");
        ga.verTodasCachesVisReg(1, "Mocambique");
        System.out.println("==============================================================================");
        System.out.println("VER TODOS AVENTUREIROS QUE VISITARAM UMA CERTA CACHE");
        System.out.println("==============================================================================");
        gc.getCaches().get(9).verTodosAventVis();
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS POR 1 AVENTUREIRO GLOBAL");
        System.out.println("==============================================================================");
        ga.verTodasCachesNaoVisGlobal(gc, 1);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS POR 1 AVENTUREIRO REGIAO");
        System.out.println("==============================================================================");
        ga.verTodasCachesNaoVisReg(gc, 1, "Mocambique");
        System.out.println("==============================================================================");
        System.out.println("TOP TRAVELBUG COM MAIS LOCALIZACOES PERCORRIDAS");
        System.out.println("==============================================================================");
        go.topTravelBug();
        System.out.println("==============================================================================");
        go.guardarObjeto(gc, ga);
        gc.guardarCache(ga, go);
        ga.guardarAventureiros(gc, go);
    }
    //teste metodo now e do metodo do top 5 utilizadores (POINTO 8 e 9)
    public static void clientTeste12(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException, JaExisteObjetoNumaCacheException, ParseException, MissaoNaoCompletadaComExitoException {
        Premium a1 = new Premium("jonas", 1, 2, "asdasd");
        Premium a2 = new Premium("fabio", 1, 2, "asdasd");
        Premium a3 = new Premium("Ricardo", 1, 2, "asdasd");
        Premium a4 = new Premium("kinito", 1, 2, "asdasd");
        Premium a5 = new Premium("joao", 1, 2, "asdasd");
        Premium a6 = new Premium("jorge", 1, 2, "asdasd");
        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        ga.regista(a4);
        ga.regista(a5);
        ga.regista(a6);

        TravelBug cartas = new TravelBug("cartas");
        TravelBug sumo = new TravelBug("sumo");
        TravelBug gato = new TravelBug("gato");
        TravelBug rato = new TravelBug("rato");
        TravelBug radio = new TravelBug("radio");
        TravelBug tele = new TravelBug("tele");
        TravelBug pao = new TravelBug("pao");
        TravelBug pizza = new TravelBug("pizza");
        TravelBug mac = new TravelBug("mac");
        TravelBug teste = new TravelBug("teste");
        TravelBug teste2 = new TravelBug("teste2");
        TravelBug teste3 = new TravelBug("teste3");
        TravelBug teste4 = new TravelBug("teste4");
        TravelBug teste5 = new TravelBug("teste5");
        TravelBug teste6 = new TravelBug("teste6");
        go.regista(cartas);
        go.regista(sumo);
        go.regista(gato);
        go.regista(rato);
        go.regista(radio);
        go.regista(tele);
        go.regista(pao);
        go.regista(pizza);
        go.regista(mac);
        go.regista(teste);
        go.regista(teste2);
        go.regista(teste3);
        go.regista(teste4);
        go.regista(teste5);
        go.regista(teste6);

        PremiumCache c1 = new PremiumCache(5, a1, cartas, 10, 32, "porto");
        PremiumCache c2 = new PremiumCache(5, a2, sumo, 112, 323, "lisboa");
        PremiumCache c3 = new PremiumCache(5, a3, gato, 1123, 4513, "porto");
        PremiumCache c4 = new PremiumCache(5, a4, rato, 1453, 213, "aliados");
        PremiumCache c5 = new PremiumCache(5, a5, radio, 1123, 513, "Mocambique");
        PremiumCache c6 = new PremiumCache(5, a1, tele, 516, 323, "lisboa");
        PremiumCache c7 = new PremiumCache(5, a2, pao, 231, 533, "Mocambique");
        PremiumCache c8 = new PremiumCache(5, a3, pizza, 311, 3123, "Angola");
        PremiumCache c9 = new PremiumCache(5, a4, teste, 1231,125, "Mocambique");
        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.adicionaCache(c3);
        gc.adicionaCache(c4);
        gc.adicionaCache(c5);
        gc.adicionaCache(c6);
        gc.adicionaCache(c7);
        gc.adicionaCache(c8);
        gc.adicionaCache(c9);

        a1.getListTravelBug().put(0, mac);
        a1.getListTravelBug().get(0).getListaAventureiros().put(a1.getListTravelBug().get(0).getNumAventureiros(), a1);
        a1.getListTravelBug().get(0).setNumAventureiros(a1.getListTravelBug().get(0).getNumAventureiros()+1);
        a2.getListTravelBug().put(0, teste2);
        a2.getListTravelBug().get(0).getListaAventureiros().put(a2.getListTravelBug().get(0).getNumAventureiros(), a2);
        a2.getListTravelBug().get(0).setNumAventureiros(a2.getListTravelBug().get(0).getNumAventureiros()+1);
        a3.getListTravelBug().put(0, teste3);
        a3.getListTravelBug().get(0).getListaAventureiros().put(a3.getListTravelBug().get(0).getNumAventureiros(), a3);
        a3.getListTravelBug().get(0).setNumAventureiros(a3.getListTravelBug().get(0).getNumAventureiros()+1);
        a4.getListTravelBug().put(0, teste4);
        a4.getListTravelBug().get(0).getListaAventureiros().put(a4.getListTravelBug().get(0).getNumAventureiros(), a4);
        a4.getListTravelBug().get(0).setNumAventureiros(a4.getListTravelBug().get(0).getNumAventureiros()+1);
        a5.getListTravelBug().put(0, teste5);
        a5.getListTravelBug().get(0).getListaAventureiros().put(a5.getListTravelBug().get(0).getNumAventureiros(), a5);
        a5.getListTravelBug().get(0).setNumAventureiros(a5.getListTravelBug().get(0).getNumAventureiros()+1);
        a6.getListTravelBug().put(0, teste6);
        a6.getListTravelBug().get(0).getListaAventureiros().put(a6.getListTravelBug().get(0).getNumAventureiros(), a6);
        a6.getListTravelBug().get(0).setNumAventureiros(a6.getListTravelBug().get(0).getNumAventureiros()+1);

        a1.encontrouCache(c7, mac, new Date(20, 12, 2020));
        a1.encontrouCache(c9, pao, new Date(23,1,2021));
        a1.encontrouCache(c6, teste, new Date(24, 1, 2021));
        a1.encontrouCache(c9, tele, new Date(25, 1 , 2021));
        a1.encontrouCache(c4, pao, new Date(3,2,2021));
        a2.encontrouCache(c1, teste2, new Date(12,1,2021));
        a2.encontrouCache(c3, cartas, new Date(24,1,2021));
        a3.encontrouCache(c5, teste3, new Date(24,1,2021));
        a4.encontrouCache(c6, teste4, new Date(24,1,2021));
        a5.encontrouCache(c8, teste5, new Date(24,1,2021));
        a5.encontrouCache(c2, pizza, new Date(24,1,2021));
        a6.encontrouCache(c2, teste6, new Date(24,1,2021));
        a6.encontrouCache(c3, pizza, new Date(25,1,2021));
        a6.encontrouCache(c6, cartas, new Date(26,1,2021));
        a5.encontrouCache(c6, sumo, new Date(25, 1 , 2021));
        a5.encontrouCache(c7, cartas, new Date(27, 1, 2021));
        System.out.println("==============================================================================");
        System.out.println("METODO NOW()");
        go.now();
        System.out.println("TOP 5 AVENTUREIROS");
        System.out.println("==============================================================================");
        ga.topAventureiros(new Date(1, 1, 2021), new Date(3, 2, 2021));
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS POR 1 AVENTUREIRO GLOBAL");
        System.out.println("==============================================================================");
        ga.verTodasCachesVisGlobal(1);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS POR 1 AVENTUREIRO REGIAO");
        System.out.println("==============================================================================");
        ga.verTodasCachesVisReg(1, "Mocambique");
        System.out.println("==============================================================================");
        System.out.println("VER TODOS AVENTUREIROS QUE VISITARAM UMA CERTA CACHE");
        System.out.println("==============================================================================");
        gc.getCaches().get(9).verTodosAventVis();
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS POR 1 AVENTUREIRO GLOBAL");
        System.out.println("==============================================================================");
        ga.verTodasCachesNaoVisGlobal(gc, 1);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS POR 1 AVENTUREIRO REGIAO");
        System.out.println("==============================================================================");
        ga.verTodasCachesNaoVisReg(gc, 1, "Mocambique");
        System.out.println("==============================================================================");
        System.out.println("TOP TRAVELBUG COM MAIS LOCALIZACOES PERCORRIDAS");
        System.out.println("==============================================================================");
        go.topTravelBug();
        System.out.println("==============================================================================");
        System.out.println("cachePremiumComObjeto");
        System.out.println("==============================================================================");
        gc.cachePremiumComObjeto();
        System.out.println("==============================================================================");
        go.guardarObjeto(gc, ga);
        ga.guardarAventureiros(gc, go);
        gc.guardarCache(ga, go);
    }
    public static void clientTeste13(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go, int detail) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException, JaExisteObjetoNumaCacheException, ParseException {
        if(detail == 1){
            ga.lerAventureiros();
            go.lerObjeto();
            go.lerTb();
            gc.lerCache(ga, go);
            go.lerTbHist(gc, ga);
            ga.lerAventureirosHist(gc, go);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("\t\tMENU GEOCACHING\n");
        System.out.println("[1]-Gestao Aventureiros:");
        System.out.println("[2]-Gestao Cache:");
        System.out.println("[3]-Gestao Objetos:");
        System.out.println("[4]-Sair:");
        int aux = sc.nextInt();
        if(aux == 1) {
            ga.menuGestaoAventureiros(gc, go);
        }else if(aux == 2) {
            //System.out.println("CAcHE");
            gc.menuGestaoCache(ga, go);
        }else if(aux == 3){
            //System.out.println("Objeto");
            go.menuGestaoObjeto(ga, gc);
        }else{
            System.out.println("ACABOU!!!");
        }
        go.guardarObjeto(gc, ga);
        gc.guardarCache(ga, go);
        ga.guardarAventureiros(gc, go);
    }
}