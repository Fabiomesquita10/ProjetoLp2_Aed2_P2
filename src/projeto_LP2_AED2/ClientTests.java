package projeto_LP2_AED2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ClientTests {
    public static void main(String[] args) throws JaExisteObjetoNumaCacheException, AventureiroNaoExisteException, CacheNaoExisteException, AventureiroNaoHabilitado, ParseException, MissaoNaoCompletadaComExitoException {
        GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
        GestaoAcessoCache gc = new GestaoAcessoCache();
        GestaoAcessoObjeto go = new GestaoAcessoObjeto();
        //clientTestR3(ga, gc, go, 1); //inserir, remover, editar informacao das classes
        //clientTestR6R7(ga, gc, go); //input e output de ficheiros
        //clientTestAdmin(ga, gc, go); //testamos as funcoes admin
        //clientTestR8_a(ga, gc, go); //funcoes de pesquisa sobre informaçao especifica
        //clientTestR8_b(ga, gc, go); //funcoes de pesquisa sobre informaçao especifica
        //clientTestR8_c(ga, gc, go); //funcoes de pesquisa sobre informaçao especifica
        //clientTestR8_d(ga, gc, go); //funcoes de pesquisa sobre informaçao especifica
        //clientTestR8_e(ga, gc, go); //funcoes de pesquisa sobre informaçao especifica
        //clientTestR8_f(ga, gc, go); //funcoes de pesquisa sobre informaçao especifica
        //clientTestR9(ga, gc, go); //funcoes now
        clientTestMissoes(ga, gc, go); //testa a funcao de missoes
        //clientTestGeral(ga, gc, go); //este client test programamos as movimentaçoes entre caches, etc e depois guardamos a informacao no ficheior txt
    }

    private static void clientTestR3(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go, int detail) throws AventureiroNaoHabilitado, AventureiroNaoExisteException, JaExisteObjetoNumaCacheException, ParseException, CacheNaoExisteException {
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
            System.out.println("CAcHE");
            //gc.menuGestaoCache(ga, go)
        }else if(aux == 3){
            System.out.println("Objeto");
            //gc.menuGestaoObjeto(ga, go)
        }else{
            System.out.println("ACABOU!!!");
        }
        go.guardarObjeto(gc, ga);
        gc.guardarCache(ga, go);
        ga.guardarAventureiros(gc, go);
    }

    private static void clientTestR6R7(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado, CacheNaoExisteException, AventureiroNaoExisteException {
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

    private static void clientTestAdmin(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        Admin a = new Admin("Fabio", 12, 123, "lisboa");
        int id = 1;
        String regiao = "porto";
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE TODAS AS CACHES NA REGIAO " + regiao.toUpperCase(Locale.ROOT));
        System.out.println("==============================================================================");
        a.verTodasCachesRegiao(gc, "porto");
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE TODOS OS AVENTUREIROS EXISTENTES");
        System.out.println("==============================================================================");
        a.verTodosAventureiros(ga);
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE LOCALIZACAO DO AVENTUREIRO " + ga.getAventureiros().get(id).getNome().toUpperCase(Locale.ROOT));
        System.out.println("==============================================================================");
        a.verLocalizacaoAventureiro(ga.getAventureiros().get(id));
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE TODAS AS CACHES EXISTENTES");
        System.out.println("==============================================================================");
        a.verTodasCaches(gc);
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE LOCALIZACAO DA CACHE " + gc.getCaches().get(id));
        System.out.println("==============================================================================");
        a.verLocalizacaoCache(gc.getCaches().get(1));
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE LOCALIZACAO DE UM TRAVELBUG " + go.getTravelBug().get(id));
        System.out.println("==============================================================================");
        a.verLocalizacaoTravelBug(go, id);
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE TODOS OS OBJETOS");
        System.out.println("==============================================================================");
        a.verTodosOsObjetos(go);
        System.out.println("==============================================================================");
        System.out.println("ADMIN VE TODOS OS TRAVELBUG");
        System.out.println("==============================================================================");
        a.verTodosOsTravelBug(go);
    }

    private static void clientTestR8_a(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        int id = 1;
        String regiao = "Mocambique";
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS NO GLOBAL PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesVisGlobal(id);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS EM " + regiao + " PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesVisReg(id, regiao);
        ga.verTodasCachesVisGlobal(id);
    }

    private static void clientTestR8_b(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        int id = 1;
        String regiao = "Mocambique";
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS NO GLOBAL PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesVisGlobal(id);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS EM " + regiao.toUpperCase(Locale.ROOT) + " PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesVisReg(id, regiao);
        ga.verTodasCachesNaoVisReg(gc, id, regiao);
        ga.verTodasCachesNaoVisGlobal(gc, id);
    }

    private static void clientTestR8_c(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        int idc = 9;
        System.out.println("==============================================================================");
        System.out.println("VER TODOS AVENTUREIROS QUE VISITARAM A CACHE " + idc);
        System.out.println("==============================================================================");
        gc.getCaches().get(idc).verTodosAventVis();
    }

    private static void clientTestR8_d(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        int idc = 9;
        System.out.println("==============================================================================");
        System.out.println("TODAS AS CACHES PREMIUM COM PELO MENOS UM OBJETO " + idc);
        System.out.println("==============================================================================");
        gc.getCaches().get(idc).verTodosAventVis();
    }

    private static void clientTestR8_e(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        Date i = new Date(1, 1, 2021), f = new Date(3, 2, 2021);
        System.out.println("==============================================================================");
        System.out.println("TOP 5 AVENTUREIROS COM MAIS CACHES VISITADAS ENTRE AS DATAS " + i + " " + f);
        System.out.println("==============================================================================");
        ga.topAventureiros(i, f);
    }

    private static void clientTestR8_f(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        System.out.println("==============================================================================");
        System.out.println("TOP TRAVELBUG COM MAIS LOCALIZACOES PERCORRIDAS");
        System.out.println("==============================================================================");
        go.topTravelBug();
    }

    private static void clientTestR9(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        ga.lerAventureiros();
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        System.out.println("==============================================================================");
        System.out.println("METODO NOW()");
        go.now();
    }

    private static void clientTestMissoes(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws MissaoNaoCompletadaComExitoException, AventureiroNaoHabilitado {
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

    private static void clientTestGeral(GestaoAcessoAventureiro ga, GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoExisteException, CacheNaoExisteException, MissaoNaoCompletadaComExitoException, AventureiroNaoHabilitado {
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

        String regiao = "Mocambique";
        int id = 1, idc = 9;
        Date i = new Date(1, 1, 2021), f = new Date(3, 2, 2021);
        System.out.println("==============================================================================");
        System.out.println("METODO NOW()");
        go.now();
        System.out.println("TOP 5 AVENTUREIROS COM MAIS CACHES VISITADAS ENTRE AS DATAS " + i + " " + f);
        System.out.println("==============================================================================");
        ga.topAventureiros(i, f);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS NO GLOBAL PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesVisGlobal(id);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES VISITADAS EM " + regiao + " PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesVisReg(id, regiao);
        System.out.println("==============================================================================");
        System.out.println("VER TODOS AVENTUREIROS QUE VISITARAM A CACHE " + idc);
        System.out.println("==============================================================================");
        gc.getCaches().get(idc).verTodosAventVis();
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS NO GLOBAL PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesNaoVisGlobal(gc, id);
        System.out.println("==============================================================================");
        System.out.println("VER TODAS AS CACHES NAO VISITADAS EM " + regiao.toUpperCase(Locale.ROOT) + " PELO AVENTUREIRO " + ga.getAventureiros().get(id).getNome());
        System.out.println("==============================================================================");
        ga.verTodasCachesNaoVisReg(gc, id, regiao);
        System.out.println("==============================================================================");
        System.out.println("TOP TRAVELBUG COM MAIS LOCALIZACOES PERCORRIDAS");
        System.out.println("==============================================================================");
        go.topTravelBug();
        System.out.println("==============================================================================");
        go.guardarObjeto(gc, ga);
        ga.guardarAventureiros(gc, go);
        gc.guardarCache(ga, go);
    }

}
