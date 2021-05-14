package projeto_LP2_AED2;

import Search.BST_AED2_2021;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GestaoAcessoObjeto implements GestaoObjetos{
    //FIELDS/CAMPOS
    private int numObjeto = 1;
    private int numTb = 1;
    public BST_AED2_2021<Integer, Objeto> objetos = new BST_AED2_2021<>();
    public BST_AED2_2021<Integer, TravelBug> travelBug = new BST_AED2_2021<>();

    //GETTERS AND SETTERS
    public int getNumTb() {
        return numTb;
    }

    public void setNumTb(int numTb) {
        this.numTb = numTb;
    }

    public BST_AED2_2021<Integer, TravelBug> getTravelBug() {
        return travelBug;
    }

    public void setTravelBug(BST_AED2_2021<Integer, TravelBug> travelBug) {
        this.travelBug = travelBug;
    }

    public int getNumObjeto() {
        return numObjeto;
    }

    public void setNumObjeto(int numObjeto) {
        this.numObjeto = numObjeto;
    }

    public BST_AED2_2021<Integer, Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(BST_AED2_2021<Integer, Objeto> objetos) {
        this.objetos = objetos;
    }

    /**
     * Metodo de teste da Api de insercao, remocao e edicao de objetos/travelBugs
     * @param ga - acesso a todos os aventureiros
     * @param gc - acesso a todas as caches
     * @throws AventureiroNaoExisteException
     * @throws AventureiroNaoHabilitado
     * @throws JaExisteObjetoNumaCacheException
     * @throws ParseException
     * @throws CacheNaoExisteException
     */
    public void menuGestaoObjeto(GestaoAcessoAventureiro ga, GestaoAcessoCache gc) throws AventureiroNaoExisteException, AventureiroNaoHabilitado, JaExisteObjetoNumaCacheException, ParseException, CacheNaoExisteException {
        boolean f = true;
        while (f){
            Scanner sc = new Scanner(System.in);
            System.out.println("[1]-Adicionar Objeto/TravelBug");
            System.out.println("[2]-Remover Objeto/TravelBug");
            System.out.println("[3]-Editar Objeto/TravelBug");
            System.out.println("[4]-Voltar");
            int aux2 = sc.nextInt();
            switch (aux2){
                case 1:
                    sc.nextLine();
                    System.out.println("Insira o tipo do objeto:");
                    String tipo = sc.nextLine();
                    boolean correto = false;
                    while(!correto) {
                        if (tipo.equals("TravelBug")) { //se for Premium
                            System.out.println("Insira o nome do TravelBug:");
                            String nome = sc.nextLine();
                            TravelBug tb = new TravelBug(nome);
                            regista(tb);
                            correto = true;
                        } else if (tipo.equals("Objeto")) { //se for Basic
                            System.out.println("Insira o nome do Objeto:");
                            String nome = sc.nextLine();
                            Objeto o = new Objeto(nome);
                            regista(o);
                            correto = true;
                        } else {
                            System.out.println("Volte a inserir o tipo de Objeto:");
                            tipo = sc.nextLine();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Quer remover um Objeto ou TravelBug:");
                    System.out.println("[1]-Objeto:");
                    System.out.println("[2]-TravelBug:");
                    int r = sc.nextInt();
                    if(r == 1){
                        System.out.println("Insira o id do objeto:");
                        removeO(sc.nextInt());
                    }
                    else if(r == 2){
                        System.out.println("Insira o id do travelBug:");
                        removeTb(sc.nextInt());
                    }
                    break;
                case 3:
                    System.out.println("Quer editar um Objeto ou TravelBug:");
                    System.out.println("[1]-Objeto:");
                    System.out.println("[2]-TravelBug:");
                    r = sc.nextInt();
                    if(r == 1){
                        System.out.println("Insira o id do objeto:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Qual o novo nome do objeto:");
                        String nome = sc.nextLine();
                        editarO(id, nome);
                    }
                    else if(r == 2){
                        System.out.println("Insira o id do travelBug:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Qual o novo nome do travelBug:");
                        String nome = sc.nextLine();
                        editarTb(id, nome);
                    }
                    break;
                case 4:
                    guardarObjeto(gc, ga);
                    gc.guardarCache(ga, this);
                    ga.guardarAventureiros(gc, this);
                    Main.clientTeste13(ga, gc, this, 0);
                    f = false;
                    break;
            }
        }
    }

    /**
     * Metodo para editar informacao de um Objeto
     * @param id - id do objeto a editar
     * @param nome - novo nome do objeto
     * @return true se o objeto for editado com sucesso
     */
    @Override
    public boolean editarO(int id, String nome){
        if(objetos.get(id) != null){ // verificacao se o objeto existe
            objetos.get(id).setNome(nome);
            return true;
        }
        return false;
    }

    /**
     * Metodo para editar informacao de um TravelBug
     * @param id - id do TravelBug a editar
     * @param nome - novo nome do TravelBug
     * @return true se o TravelBug for editardo com sucesso
     */
    @Override
    public boolean editarTb(int id, String nome){
        if(travelBug.get(id) != null){ // verificacao se o TravelBug existe
            travelBug.get(id).setNome(nome);
            return true;
        }
        return false;
    }

    /**
     * Metodo para registar um objeto
     * @param objeto - objeto a ser registado
     * @return true se conseguirmos regista lo
     */
    @Override
    public boolean regista(Objeto objeto) {
        objeto.setIdObjeto(numObjeto);
        objetos.put(numObjeto,objeto);
        numObjeto ++;
        return true;
    }

    /**
     * Metodo para registar um TravelBug
     * @param tb - TravelBug a ser registado
     * @return true se conseguirmos regista lo
     */
    @Override
    public boolean regista(TravelBug tb) {
        tb.setIdObjeto(numTb);
        travelBug.put(numTb,tb);
        numTb ++;
        return true;
    }

    /**
     * Metodo para remover um objeto da Bst de Objetos
     * @param idObjeto - e o id do objeto que queremos remover
     * @return true se conseguirmos remove-lo
     */
    @Override
    public boolean removeO(Integer idObjeto) {
        if(objetos.contains(idObjeto)){ // verificaçao da existencia do objeto
            objetos.delete(idObjeto);
            return true;
        }
        return false;
    }

    /**
     * Metodo para remover um TravelBug da BSt de TravelBugs
     * @param idTb - e o id do TravelBug que queremos remover
     * @return true se conseguirmos remove-lo
     */
    @Override
    public boolean removeTb(Integer idTb) {
        if(travelBug.contains(idTb)){ // verificacao da existencia deste travelBug
            travelBug.delete(idTb);
            return true;
        }
        return false;
    }

    /**
     * MEtodo para verificar a existencia de um objeto
     * @param idObjeto - id do objeto que queremos saber se existe
     * @return true se ele existir
     */
    @Override
    public boolean existeO(Integer idObjeto) {
        return objetos.contains(idObjeto);
    }

    /**
     * MEtodo para verificar a existencia de um travelBug
     * @param idObjeto - id do TravelBug que queremos saber se existe
     * @return true se ele existir
     */
    public boolean existeTb(Integer idObjeto) {
        return travelBug.contains(idObjeto);
    }

    /**
     * Metodo que retorna travelBug com maior numero de caches Visitadas
     * @return id do travelBug com maior numero de caches Visitadas
     */
    public int travelBugVisits(){
        int x = 1, id = 0, num = 0;
        while(x < travelBug.size()){ // percorre todos os travelBugs da BST
            if(travelBug.get(x).getNumCachesPres() > num){ // comparacao para saber se o travelBug recorrente tem mais caches visitadas que o anterior
                id = travelBug.get(x).getIdObjeto();
                num = travelBug.get(x).getNumCachesPres();
            }
            x++;
        }
        return id;
    }

    /**
     * Metodo para ler Objetos do ficheiro e po los na BST
     */
    public void lerObjeto(){
        In infile = new In("data/Objeto.txt");
        String line = null;
        while((line = infile.readLine()) != null){ // enquanto tiver linhas para ler no ficheiro
            String[] parts = line.split(" ");
            String part0 = parts[0]; // id
            int id = Integer.parseInt(part0);
            String part1 = parts[1]; // nome
            Objeto o = new Objeto(part1);
            o.setIdObjeto(numObjeto);
            getObjetos().put(numObjeto, o);
            numObjeto++;
        }
    }

    /**
     * Metodo para ler TravelBugs do ficheiro e po los na BST
     */
    public void lerTb(){
        In infile = new In("data/TravelBug.txt");
        String line = null;
        while((line = infile.readLine()) != null){ // enquanto tiver linhas para ler no ficheiro
            String[] parts = line.split(" ");
            String part0 = parts[0]; // id
            int id = Integer.parseInt(part0);
            String part1 = parts[1]; // nome
            String part2 = parts[2]; // numCachePres
            int numCachePres = Integer.parseInt(part2);
            String part3 = parts[2+numCachePres+1]; // numAvent
            int numAvent = Integer.parseInt(part3);
            String part4 = parts[2+numCachePres+1+numAvent+2]; // numMissao
            int numMissao = Integer.parseInt(part4);
            StringBuilder missao = new StringBuilder(numMissao + " " + parts[2+numCachePres+1+numAvent+2+1]); // Missao
            int i = 2+numCachePres+1+numAvent+2+1+1;
            while(!parts[i].equals(".")){
                missao.append(" ").append(parts[i]);
                i++;
            }
            TravelBug t = new TravelBug(part1, missao.toString());
            t.setIdObjeto(numTb);
            getTravelBug().put(numTb, t);
            numTb++;
        }
    }

    /**
     * Metodo para ler o resto da informacao dos travelBugs (informacao relativa a caches e aventureiros)
     * @param gc - acesso a todas as caches
     * @param ga - acesso a todos os aventureiros
     */
    public void lerTbHist(GestaoAcessoCache gc, GestaoAcessoAventureiro ga){
        In infile = new In("data/TravelBug.txt");
        String line = null;
        ArrayList<Integer> idCaches = new ArrayList<>();
        ArrayList<Integer> idAventureiros = new ArrayList<>();
        while((line = infile.readLine()) != null){ // enquanto tiver linhas para ler no ficheiro
            idAventureiros.removeAll(idAventureiros);
            idCaches.removeAll(idCaches);
            String[] parts = line.split(" ");
            int id = Integer.parseInt(parts[0]);
            int numCachePres = Integer.parseInt(parts[2]);
            for (int i = 3; i <= 3+numCachePres-1; i++) { // ler todos os ids das caches visitadas pelo Travelbug
                idCaches.add(Integer.parseInt(parts[i]));
            }
            int numAvent = Integer.parseInt(parts[3+numCachePres]);
            for (int i = 3+numCachePres+1; i < 3+numCachePres+1+numAvent; i++) { // ler todos os ids de aventureiros que tiveram este travelBug
                idAventureiros.add(Integer.parseInt(parts[i]));
            }
            String viajar = parts[3+numCachePres+1+numAvent];
            int x = 0;
            if(viajar.equals("true")){ //  se o travelBug estiver a viajar
                while (idCaches.size()>x){ //  insere todas as caches na BST de cachesvis com os ids
                    getTravelBug().get(id).getListaCachesPresente().put(getTravelBug().get(id).getNumCachesPres(), (PremiumCache)gc.getCaches().get(idCaches.get(x)));
                    getTravelBug().get(id).setNumCachesPres(getTravelBug().get(id).getNumCachesPres()+1);
                    x++;
                }
                getTravelBug().get(id).setViajar(true);
            }else if(viajar.equals("false")){
                while (idCaches.size()-1>x){  //  insere todas as caches na BST de cachesvis com os ids
                    getTravelBug().get(id).getListaCachesPresente().put(getTravelBug().get(id).getNumCachesPres(), (PremiumCache)gc.getCaches().get(idCaches.get(x)));
                    getTravelBug().get(id).setNumCachesPres(getTravelBug().get(id).getNumCachesPres()+1);
                    x++;
                }
                getTravelBug().get(id).setViajar(false);
            }
            x = 0;
            while (idAventureiros.size()>x){ // insere todos os aventuiros na BST de HIStAventureiros
                getTravelBug().get(id).getListaAventureiros().put(getTravelBug().get(id).getNumAventureiros(), ga.getAventureiros().get(idAventureiros.get(x)));
                getTravelBug().get(id).setNumAventureiros(getTravelBug().get(id).getNumAventureiros()+1);
                x++;
            }
            int j = 1;
            if(viajar.equals("false")){
                while (getTravelBug().get(id).getNumCachesPres()-1 > j){ // parte da funcao para por ordem as caches que o TB visitou
                        PremiumCache temp = getTravelBug().get(id).getListaCachesPresente().get(j);
                        getTravelBug().get(id).getListaCachesPresente().put(j, getTravelBug().get(id).getListaCachesPresente().get(j+1));
                        getTravelBug().get(id).getListaCachesPresente().put(j+1, temp);
                    j++;
                }
            }
        }
    }

    /**
     * Metodo para guardar um objeto no ficheiro
     * @param gc - acesso a todas as caches
     * @param ga - acesso a todos os aventureiros
     */
    public void guardarObjeto(GestaoAcessoCache gc, GestaoAcessoAventureiro ga){
        if(objetos.size()>0){ // se houver objetos vai guarda-los
            Out outfile = new Out("data/Objeto.txt");
            int x = 1, k = 1;
            while(k <= objetos.size()){ // percorre todos os objetos
                if(objetos.get(x) != null) {
                    String toSave = objetos.get(x).getIdObjeto() + " " + objetos.get(x).getNome();
                    outfile.println(toSave);
                    k++;
                }
                x++;
            }
        }
        if(travelBug.size()>0){ // se houver TB vai guarda-los
            Out outfile = new Out("data/TravelBug.txt");
            int x = 1, k = 1;
            while(k <= travelBug.size()){ //  percorre todos os TB
                if(travelBug.get(x) != null){
                    StringBuilder toSave = new StringBuilder(travelBug.get(x).getIdObjeto() + " " + travelBug.get(x).getNome());
                    StringBuilder temp = new StringBuilder();
                    int j = 1;
                    while(travelBug.get(x).getListaCachesPresente().size() >= j){ // Guarda todos as caches em que ele esteve presente
                        if(gc.getCaches().contains(travelBug.get(x).getListaCachesPresente().get(j).getIdCache())) // verificacao se a cache existe
                            temp.append(travelBug.get(x).getListaCachesPresente().get(j).getIdCache()).append(" ");
                        else
                            travelBug.get(x).setNumCachesPres(travelBug.get(x).getNumCachesPres()-1);
                        j++;
                    }
                    if(travelBug.get(x).getListaCachesPresente().size() <=0 ){  // se o numero de cache presente for menor que 0, pomos a 0 no ficheiro
                        toSave.append(" ").append(0);
                    }else{
                        toSave.append(" ").append(travelBug.get(x).getListaCachesPresente().size());
                        toSave.append(" ").append(temp);
                    }
                    j=0;
                    temp = new StringBuilder();
                    while (travelBug.get(x).getListaAventureiros().size() > j){ //  percorre todos os aventureiros em que ele esteve
                        if(ga.getAventureiros().contains(travelBug.get(x).getListaAventureiros().get(j).getIdAventureiro())) // verificacao se todos os aventureiros existem
                            temp.append(" ").append(travelBug.get(x).getListaAventureiros().get(j).getIdAventureiro());
                        else
                            travelBug.get(x).setNumAventureiros(travelBug.get(x).getNumAventureiros()-1);
                        j++;
                    }
                    if(travelBug.get(x).getNumAventureiros() <=0 ){ // se o numero de aventureiros for menor que 0, pomos a 0 no ficheiro
                        toSave.append(0);
                    }else{
                        toSave.append(travelBug.get(x).getNumAventureiros());
                        toSave.append(temp);
                    }
                    toSave.append(" ").append(travelBug.get(x).isViajar());
                    toSave.append(" ").append(travelBug.get(x).getMissao()).append(" .");
                    outfile.println(toSave);
                    k++;
                }
                x++;
            }

        }
    }

    /**
     * Metodo que nos informa sobre todas as informaçoes de todos os TB
     */
    public void now(){
        int x = 1;
        while(travelBug.size() >= x) { // percorremos todos os TB
            System.out.println("==============================================================================");
            System.out.println("Nome do TravelBug: " + travelBug.get(x).getNome());
            System.out.println("A localizacao atual é: " + travelBug.get(x).getListaCachesPresente().get(travelBug.get(x).getNumCachesPres() - 1).getLocal().getLocalizacao());
            int p = travelBug.get(x).getNumCachesPres()-1;
            System.out.println("Numero de caches percorridas: " + p);
            if(travelBug.get(x).isViajar()) { // se ele estiver a viajar no momento
                System.out.println("O TravelBug esta a ser transportado neste momento pelo aventureiro: " + travelBug.get(x).getListaAventureiros().get(travelBug.get(x).getNumAventureiros() - 1).getNome());
            }
            else if(travelBug.get(x).getNumAventureiros() > 0){ // verificar se ele ja teve algum aventureiro
                if (travelBug.get(x).getListaAventureiros().get(travelBug.get(x).getNumAventureiros() - 1).getNome() != null)
                    System.out.println("O ultimo Aventureiro que o transportou foi: " + travelBug.get(x).getListaAventureiros().get(travelBug.get(x).getNumAventureiros() - 1).getNome());
            }
            if(travelBug.get(x).getNumCachesPres() == 2){ // verificar se o TB so esteve numa cache
                System.out.println("O TravelBug so esteve na sua cache atual: " + travelBug.get(x).getListaCachesPresente().get(travelBug.get(x).getNumCachesPres()-1).getIdCache());
            }else{ // se tiver tido mais que uma , diz qual e a atual e a primeira
                if(travelBug.get(x).getListaCachesPresente().get(travelBug.get(x).getNumCachesPres()-1) != null && !travelBug.get(x).isViajar()){
                    System.out.println("A Cache atual: " + travelBug.get(x).getListaCachesPresente().get(travelBug.get(x).getNumCachesPres()-1).getIdCache());
                }
                if(travelBug.get(x).getListaCachesPresente().get(1) != null){
                    System.out.println("A primeira cache: " + travelBug.get(x).getListaCachesPresente().get(1).getIdCache());
                }
            }
            if(travelBug.get(x).getNumCachesPres() > 2){ // verificar se ele esteve em mais que uma cache
                int k = 1;
                while(k < travelBug.get(x).getNumCachesPres()-1){ // ciclo para printar todos os caminhos percorridos pelo TB
                    System.out.println("O " + k + " caminho percorrido foi de " + travelBug.get(x).getListaCachesPresente().get(k).getLocal().getLocalizacao() + " para " + travelBug.get(x).getListaCachesPresente().get(k+1).getLocal().getLocalizacao());
                    double dist = travelBug.get(x).getListaCachesPresente().get(k).getLocal().distancia(travelBug.get(x).getListaCachesPresente().get(k+1).getLocal());
                    //System.out.println("A distancia percorrida foi de: " + dist);
                    System.out.printf("A distancia percorrida foi de %.2f metros.\n", dist);
                    k++;
                }
            }
            System.out.println("==============================================================================");
            x++;
        }
    }

    /**
     * Metodo que ordena os ids dos travel bug num ArrayList por ordem crescente de numero de caches presentes
     */
    public void topTravelBug(){
        // cada posicao destes dois arrayList correspondem ao mesmo objeto um do outro, ex(posicao 0 do temp e do mesmo objeto da posicao 0 do numTB)
        ArrayList<TravelBug> temp = new ArrayList<>(); // ArrayList para guardar o TB
        ArrayList<Integer> numTb = new ArrayList<>(); // ArrayList para guardar o numero de caches visitadas,
        int x = 1, k = 1;
        while(getTravelBug().size()>x){ //  percorre todos os TB
            int numC = getTravelBug().get(x).getNumCachesPres(); // numero de caches em que o TB esteve
            if(getTravelBug().get(k).getNumCachesPres() > 1){ // verificar se o Tb esteve em alguma cache
                if(numTb.isEmpty()) { //  se nao tiver nenhum TB podemos por logo um
                    temp.add(getTravelBug().get(k));
                    numTb.add(numC);
                }else { // se o array ja noa estiver vazio vamos comparar as posicoes
                    for (int o = 0; o < numTb.size(); o++){ //vai percorrer o arraylist para comparar o TB atual com os que ja estao na arrayList
                        if(numTb.get(o)<numC){ //se o TB tiver mais caches visitadas do que o que estamos a comparar
                            // vamos trocar as posiçoes
                            if(numTb.size() == o+1){  // pomos na ultima posicao
                                temp.add(temp.get(o));
                                numTb.add(numTb.get(o));
                            }else{ // senao comecamos a trocar desde o fim para o inicio
                                for (int j = numTb.size(); j>o; j--){ // partimos da ultima posicao do arrayList e vmaos ate a posicao emque estmos
                                    if(j == numTb.size()){ // se ainda houver ele continua a trocar
                                        temp.add(j, temp.get(j-1));
                                        numTb.add(j, numTb.get(j-1));
                                    }else{ // se ja nao houver mais ele adiciona o TB
                                        temp.set(j, temp.get(j-1));
                                        numTb.set(j, numTb.get(j-1));
                                    }
                                }
                            }
                            temp.set(o, getTravelBug().get(k));
                            numTb.set(o, numC);
                            o = numTb.size();
                        }else if(o == numTb.size()-1){ // se ele for mais pequeno que todos pomo lo no final
                            numTb.add(numC);
                            temp.add(getTravelBug().get(k));
                            o = numTb.size();
                        }
                    }
                }
                x++;
            }
            k++;
        }
        //printar a lista ordenada
        for (TravelBug t : temp){
            int i =t.getNumCachesPres()-1;
            System.out.println("Id Tb: " + t.getIdObjeto() + ", numero localizacoes: " + i);
        }
    }

}
