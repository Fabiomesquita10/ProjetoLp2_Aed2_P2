package projeto_LP2_AED2;

import Search.BST_AED2_2021;
import Search.RedBlack_AED2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestaoAcessoAventureiro implements GestaoAventureiro {

    //FIELDS/CAMPOS
    private RedBlack_AED2<Integer,Aventureiro> aventureiros = new RedBlack_AED2<>(); //RedBlack de aventureiros
    private int numAventureiros = 1;
    private LogsDiario diario = new LogsDiario();
    private Date data = new Date();

    //GETTERS AND SETTERS
    public RedBlack_AED2<Integer, Aventureiro> getAventureiros() {
        return aventureiros;
    }

    public void setAventureiros(RedBlack_AED2<Integer, Aventureiro> aventureiros) {
        this.aventureiros = aventureiros;
    }

    /**
     * Método teste da API de inserção, edição e remoção de aventureiros
     * @param gc - Acesso a todas as Caches
     * @param go - Acesso a todos os Objetos/TravelBugs
     * @throws AventureiroNaoExisteException
     * @throws CacheNaoExisteException
     */
    public void menuGestaoAventureiros(GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoExisteException, CacheNaoExisteException, AventureiroNaoHabilitado, ParseException, JaExisteObjetoNumaCacheException {
        boolean f = true;
        while (f){
            Scanner sc = new Scanner(System.in);
            System.out.println("[1]-Adicionar Aventureiro");
            System.out.println("[2]-Remover Aventureiro");
            System.out.println("[3]-Editar Aventureiro");
            System.out.println("[4]-Voltar");
            int aux2 = sc.nextInt();
            switch (aux2){
                case 1:
                    sc.nextLine();
                    System.out.println("Insira o nome:");
                    String nome = sc.nextLine();
                    System.out.println("Insira a localizacao x:");
                    int x = sc.nextInt();
                    System.out.println("Insira a localizacao y:");
                    int y = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Insira o tipo de aventureiro:");
                    String tipo = sc.nextLine();
                    boolean correto = false;
                    while(!correto) {
                        if (tipo.equals("Premium")) { //se for Premium
                            Premium p = new Premium(nome, x, y, "asdasd");
                            regista(p);
                            correto = true;
                        } else if (tipo.equals("Basic")) { //se for Basic
                            Basic p = new Basic(nome, x, y, "porto");
                            regista(p);
                            correto = true;
                        } else if (tipo.equals("Admin")) { //se for Admin
                            Admin p = new Admin(nome, x, y, "lisboa");
                            regista(p);
                            correto = true;
                        } else {
                            System.out.println("Volte a inserir o tipo de Aventureiro:");
                            tipo = sc.nextLine();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Insira o id do Aventureiro");
                    int id = sc.nextInt();
                    remove(id); //remove o respetivo aventureiro cujo ID inseri
                    break;
                case 3:
                    System.out.println("Insira o id do Aventureiro:");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Insira o nome:");
                    nome = sc.nextLine(); //novo nome
                    System.out.println("Insira as coordenadas X:");
                    x = sc.nextInt(); //nova coordX
                    System.out.println("Insira as coordenadas Y:");
                    y = sc.nextInt(); //nova coordY
                    editar(id, nome, x, y); //chamamos a função editar e mandamos os parametros a editar
                    break;
                case 4:
                    go.guardarObjeto(gc, this); //guardar Objeto
                    gc.guardarCache(this, go); //guardar Cache
                    guardarAventureiros(gc, go); //guardar Aventureiros
                    Main.clientTeste13(this, gc, go, 0);
                    f = false;
                    break;
            }
        }
    }

    /**
     * Método que regista um Basic aventureiro recebido como parametro
     * @param aventureiro - Aventureiro Basic a registar
     * @return
     */
    @Override
    public boolean regista(Basic aventureiro) {
        aventureiro.setIdAventureiro(numAventureiros); //set ID(atribuido consoante o numAventureiros)
        aventureiros.put(numAventureiros, aventureiro); //adiciono o novo aventureiro à RedBlack de Aventureiros
        numAventureiros++; //itero o numero de aventureiros
        String toDiario = "Adicionou: " + aventureiro.toString(); //Imprime a cache que adicionou
        diario.adicionaLog(toDiario, data, "data/LogsAventureiro"); //Escreve o print para o ficheiro de Logs
        return true;
    }

    /**
     * Método que regista um Admin aventureiro recebido como parametro
     * @param aventureiro - Aventureiro Admin a registar
     * @return
     */
    @Override
    public boolean regista(Admin aventureiro) {
        aventureiro.setIdAventureiro(numAventureiros); //set ID(atribuid consoante o numAventureiros)
        aventureiros.put(numAventureiros, aventureiro); //adiciono o novo aventureiro à RedBlack de Aventureiros
        numAventureiros++; //itero o numero de aventureiros
        String toDiario = "Adicionou: " + aventureiro.toString(); //Imprime a cache que adicionou
        diario.adicionaLog(toDiario, data, "data/LogsAventureiro"); //Escreve o print para o ficheiro de Logs
        return true;

    }

    /**
     * Método para registar um Premium aventureiro recebido como parametro
     * @param aventureiro - Aventureiro Premium a registar
     * @return
     */
    @Override
    public boolean regista(Premium aventureiro) {
        aventureiro.setIdAventureiro(numAventureiros); //set ID(atribuid consoante o numAventureiros)
        aventureiros.put(numAventureiros, aventureiro); //adiciono o novo aventureiro à RedBlack de Aventureiros
        numAventureiros++; //itero o numero de aventureiros
        String toDiario = "Adicionou: " + aventureiro.toString(); //Imprime a cache que adicionou
        diario.adicionaLog(toDiario, data, "data/LogsAventureiro");//Escreve o print para o ficheiro de Logs
        return true;
    }

    /**
     * Método que remove um determinado aventureiro recebido por parametro
     * @param idAventureiro - ID do Aventureiro a remover
     * @return
     * @throws AventureiroNaoExisteException
     */
    @Override
    public boolean remove(Integer idAventureiro) throws AventureiroNaoExisteException {
        if(aventureiros.contains(idAventureiro)){ //se esse aventureiro existir
            String nome = aventureiros.get(idAventureiro).getNome(); //obtemos o nome do mesmo
            aventureiros.delete(idAventureiro); //removemos o Aventureiro
            String toDiario = "Removeu o Aventureiro com id: " + idAventureiro; //Log para o Diario
            String toArquivo = "Foi removido o Aventureiro com id : " + idAventureiro + " e com o nome : " + nome; //Log para o Arquivo de remoções
            diario.adicionaLog(toDiario, data, "data/LogsAventureiro");
            diario.adicionaLog(toArquivo, data, "data/Arquivo.txt");
            return true;
        }
        throw new AventureiroNaoExisteException("Aventureiro Não Existe");
    }

    /**
     * Método que retorna true ou false se um determinado aventureiro recebido como parametro existir
     * @param idAventureiro - ID do Aventureiro a verificar se existe
     * @return
     */
    @Override
    public boolean existe(Integer idAventureiro) {
        return aventureiros.contains(idAventureiro); //vou à RedBlack de aventureiros e vejo se o aventureiro existe
    }

    /**
     * Método usado para editar um determinado aventureiro
     * @param idAventureiro - ID do Aventureiro a editar
     * @param nome - novo nome do Aventureiro a editar
     * @param x - nova coordX do Aventureiro a editar
     * @param y - nova coordY do Aventureiro a editar
     * @return
     */
    @Override
    public boolean editar(Integer idAventureiro, String nome, int x, int y) {
        if(aventureiros.get(idAventureiro)!=null){ //se existir
            aventureiros.get(idAventureiro).setNome(nome); //altero-lhe o nome antigo para o novo
            aventureiros.get(idAventureiro).getLocal().setCoordenadaX(x); //altero-lhe a antiga coordX para a nova coordX
            aventureiros.get(idAventureiro).getLocal().setCoordenadaY(y); //altero-lhe a antiga coordY para a nova coordX
            return true;
        }
        return false;
    }

    /**
     * Método para guardar todos os Aventureiros em ficheiro
     * @param gc - Acesso a todas as Caches
     * @param go - Acesso a todas os Objetos/TravelBugs
     * @return
     * @throws AventureiroNaoExisteException
     */
    @Override
    public boolean guardarAventureiros(GestaoAcessoCache gc, GestaoAcessoObjeto go) throws AventureiroNaoExisteException {
        if(aventureiros.size() > 0) {
            Out outfile = new Out("data/Aventureiros.txt");
            int x = 1, k = 1;
            while (k <= aventureiros.size()){
                if(aventureiros.get(x) != null){
                    StringBuilder toSave = null;
                    if(aventureiros.get(x) instanceof Basic) //Basic
                        toSave = new StringBuilder("basic" + " " + aventureiros.get(x).getIdAventureiro() + " " + aventureiros.get(x).getNome() + " " + aventureiros.get(x).getLocal().getCoordenadaX() + " " + aventureiros.get(x).getLocal().getCoordenadaY());
                    if(aventureiros.get(x) instanceof Admin) //Admin
                        toSave = new StringBuilder("admin" + " " + aventureiros.get(x).getIdAventureiro() + " " + aventureiros.get(x).getNome() + " " + aventureiros.get(x).getLocal().getCoordenadaX() + " " + aventureiros.get(x).getLocal().getCoordenadaY());
                    if(aventureiros.get(x) instanceof Premium) //Premium
                        toSave = new StringBuilder("premium" + " " + aventureiros.get(x).getIdAventureiro() + " " + aventureiros.get(x).getNome() + " " + aventureiros.get(x).getLocal().getCoordenadaX() + " " + aventureiros.get(x).getLocal().getCoordenadaY());

                    //Em relação ás Caches visitadas pelo Aventureiro para guardar-mos o número total e o ID de cada uma
                    int j = 0;
                    StringBuilder cache = new StringBuilder();
                    while(aventureiros.get(x).getListCacheVisit().size() > j){ //percorre lista de Caches Visitadas
                        if(gc.getCaches().contains(aventureiros.get(x).getListCacheVisit().get(j).getIdCache())) { //se a cache ainda existir(pode ter sido removida)
                            cache.append(" ").append(aventureiros.get(x).getListCacheVisit().get(j).getIdCache()); //printamos o ID da cache
                            cache.append(" ").append(aventureiros.get(x).getDatas().get(j).toString()); //seguido da Data da mesma
                        }else{
                            aventureiros.get(x).setNumCacheVis(aventureiros.get(x).getNumCacheVis()-1); //se a cache nao existir decrementamos o numero de Caches Visitadas
                        }
                        j++;
                    }
                    if(aventureiros.get(x).getNumCacheVis() <=0 ){ //se não tiver qualquer Cache visitada
                        toSave.append(" ").append(0); //imprimos 0
                    }else{
                        toSave.append(" ").append(aventureiros.get(x).getNumCacheVis());
                        toSave.append(cache);
                    }
                    //Em relação ás Caches escondidas pelo Aventureiro para guardar-mos o número total e o ID de cada uma
                    cache = new StringBuilder();
                    j=0;
                    while(aventureiros.get(x).getListCacheEsc().size() > j){ //percorre lista de Caches Escondidas
                        if(gc.getCaches().contains(aventureiros.get(x).getListCacheEsc().get(j).getIdCache())){ //se a cache ainda existir(pode ter sido removida)
                            cache.append(" ").append(aventureiros.get(x).getListCacheEsc().get(j).getIdCache()); //printamos o ID da cache
                        }else{
                            aventureiros.get(x).setNumCacheEsc(aventureiros.get(x).getNumCacheEsc()-1); //se a cache nao existir decrementamos o numero de Caches Visitadas
                        }
                        j++;
                    }
                    if(aventureiros.get(x).getNumCacheEsc()<=0){ //se não tiver qualquer Cache escondida
                        toSave.append(" ").append(0); //imprimos 0
                    }else{
                        toSave.append(" ").append(aventureiros.get(x).getNumCacheEsc());
                        toSave.append(cache);
                    }

                    //Em relação ao numero de TravelBugs ou Objetos que o Aventureiro tem para guardar-mos o ID do mesmo
                    int aux = 0;
                    if(aventureiros.get(x).getListTravelBug().size() > 0) { //se tiver TravelBug
                        if(go.getTravelBug().contains(aventureiros.get(x).getListTravelBug().get(0).getIdObjeto()))
                            toSave.append(" ").append("tb").append(" ").append(aventureiros.get(x).getListTravelBug().get(0).getIdObjeto()); //printo o ID do TravelBug
                        aux++;
                    }
                    if(aventureiros.get(x).getListObjetos().size() > 0) { //se tiver Objeto
                        if(go.getObjetos().contains(aventureiros.get(x).getListObjetos().get(0).getIdObjeto()))
                            toSave.append(" ").append("o").append(" ").append(aventureiros.get(x).getListObjetos().get(0).getIdObjeto()); //printo o ID do Objeto
                    }
                    if(aux==0){
                        toSave.append(" ").append("none"); //se nao tiver nada colocamos "none"
                    }
                    outfile.println(toSave.toString());
                    k++;
                }
                x++;
            }
            return true;
        }
        throw new AventureiroNaoExisteException("Nao ha aventureiros no jogo!");
    }

    /**
     * Método para ler todos os aventureiros do ficheiro de Aventureiros(todos os dados que englobam o mesmo)
     * @param gc - Acesso a todas as Caches
     * @param go - Acesso a todas os Objetos/TravelBugs
     */
    public void lerAventureirosHist(GestaoAcessoCache gc, GestaoAcessoObjeto go){
        In infile = new In("data/Aventureiros.txt");
        String line = null;
        ArrayList<Integer> cVis = new ArrayList<>(); //arrayList de Caches Visitadas
        ArrayList<String> data = new ArrayList<>(); //arrayList de Datas (para as Caches Visitadas)
        ArrayList<Integer> cEsc = new ArrayList<>(); //arrayList de Caches Escondidas
        while((line = infile.readLine()) != null){
            String[] parts = line.split(" ");
            int id = Integer.parseInt(parts[1]); //ID aventureiro
            int numCacheVisit = Integer.parseInt(parts[5]); //numero de Caches Visitadas
            for (int i = 6; i < 6+numCacheVisit+numCacheVisit; i+=2) { //percorrer as Caches Visitadas
                cVis.add(Integer.parseInt(parts[i])); //Adicionar ao Array cVis
                data.add(parts[i+1]); //Adicionar ao Array data (ambos os arrays estao interligados)
            }
            int numCacheEsc = Integer.parseInt(parts[6+numCacheVisit+numCacheVisit]); //numero de Caches Escondidas
            for (int i = 6+numCacheVisit+numCacheVisit+1; i < 6+numCacheVisit+numCacheVisit+1+numCacheEsc; i++) { //percorrer as Caches Visitadas
                cEsc.add(Integer.parseInt(parts[i])); //Adicionar ao Array cEsc
            }
            String tipoObjeto = parts[6+numCacheVisit+numCacheVisit+1+numCacheEsc]; //tipo de Objeto
            int idO=0;
            if(!tipoObjeto.equals("none"))
                idO = Integer.parseInt(parts[6+numCacheVisit+numCacheVisit+1+numCacheEsc+1]); //ID do objeto/travelbug
            int x = 0;
            while (cVis.size()>x){
                String[] datas = data.get(x).split("/"); //separar o dia, mês e ano das datas
                int d = Integer.parseInt(datas[0]);
                int m = Integer.parseInt(datas[1]);
                int a = Integer.parseInt(datas[2]);
                aventureiros.get(id).getListCacheVisit().put(aventureiros.get(id).getNumCacheVis(), gc.getCaches().get(cVis.get(x))); //adicionar à listas de caches visitadas
                aventureiros.get(id).getDatas().put(aventureiros.get(id).getNumCacheVis(), new Date(d, m, a)); //adicionar datas
                aventureiros.get(id).setNumCacheVis(aventureiros.get(id).getNumCacheVis()+1); //iterar o numero de caches visitadas
                x++;
            }
            if(tipoObjeto.equals("tb")){ //se for TravelBug
                aventureiros.get(id).getListTravelBug().put(0, go.getTravelBug().get(idO));
                aventureiros.get(id).setNumTb(1);
            }
            if(tipoObjeto.equals("o")){ //se for Objeto
                aventureiros.get(id).getListObjetos().put(0, go.getObjetos().get(idO));
                aventureiros.get(id).setNumObj(1);
            }
            cEsc.removeAll(cEsc); //reset ao Array  de Caches escondidas
            cVis.removeAll(cVis); //reset ao Array  de Caches Visitadas
            data.removeAll(data); //reset ao Array  de Datas
        }
    }

    /**
     * Método para ler todos os aventureiros do ficheiro de Aventureiros(só os dados mais pessoais, tipo, id, nome, coords)
     */
    @Override
    public void lerAventureiros() {
        In infile = new In("data/Aventureiros.txt");
        String line = null;
        while((line = infile.readLine()) != null){
            String[] parts = line.split(" ");
            String part0 = parts[0]; // Tipo de Aventureiro
            String part1 = parts[1]; // id
            int id = Integer.parseInt(part1);
            String part2 = parts[2]; // nome
            String part3 = parts[3]; // coordenadas x
            int cX = Integer.parseInt(part3);
            String part4 = parts[4]; // coordenadas y
            int cY = Integer.parseInt(part4);
            if(part0.equals("basic")){ //Basic
                Basic u = new Basic(part2, cX, cY, "porto"); //criar Aventureiro
                u.setIdAventureiro(numAventureiros); //set ID consoante o numero de aventureiros já existentes
                aventureiros.put(numAventureiros, u); //adicionar à lista de Aventureiros
                numAventureiros++; //iterar aventureiros
            }else if(part0.equals("premium")){ //Premium
                Premium u = new Premium(part2, cX, cY, "porto"); //criar Aventureiro
                u.setIdAventureiro(numAventureiros); //set ID consoante o numero de aventureiros já existentes
                aventureiros.put(numAventureiros, u); //adicionar à lista de Aventureiros
                numAventureiros++; //iterar aventureiros
            }else if(part0.equals("admin")){ //Admin
                Admin u = new Admin(part2, cX, cY, "lisboa"); //criar Aventureiro
                u.setIdAventureiro(numAventureiros); //set ID consoante o numero de aventureiros já existentes
                aventureiros.put(numAventureiros, u); //adicionar à lista de Aventureiros
                numAventureiros++; //iterar aventureiros
            }
        }
    }

    /**
     * Método para Printar todas as Caches Visitadas
     * @param id - ID do Aventureiro cujo Caches Visitadas queremos printar
     */
    public void PrintTodasCachesVisitadas(int id){
        int x = 0;
        while(getAventureiros().get(id).getListCacheVisit().size() > x){ //percorre as Caches visitadas desse Aventureiro
            System.out.println(getAventureiros().get(id).getListCacheVisit().get(x).getIdCache()); //printa o ID da mesma
            x++;
        }
    }

    /**
     * Método que retorna todos os Aventureiros que visitarm determinada Cache
     * @param c - Cache
     * @return
     */
    public ArrayList<Aventureiro> aventureirosVisitCache(Cache c){
        int x = 1;
        ArrayList<Aventureiro> temp = new ArrayList<>();
        while (aventureiros.size() >= x){
            int k = 0;
            while(aventureiros.get(x).getListCacheVisit().size() > k){
                if(aventureiros.get(x).getListCacheVisit().get(k).getIdCache().equals(c.idCache))
                    temp.add(aventureiros.get(x));
                k++;
            }
            x++;
        }
        return temp;
    }

    /**
     * Método para saber o top Aventureiros com mais caches visitadas
     * @param i - Data Inicial
     * @param f - Data Final
     */
    public void topAventureiros(Date i, Date f){
        // cada posicao destes dois arrayList correspondem ao mesmo aventureiro um do outro, ex(posicao 0 do temp e do mesmo aventureiro da posicao 0 do cv)
        ArrayList<Aventureiro> temp = new ArrayList<>(); // arrayList para guardar os aventureiros
        ArrayList<Integer> cv = new ArrayList<>(); // arrayList para guardar o numero de caches visitadas pelo aventureiro
        int x = 1, k = 1;
        while(getAventureiros().size() >= x){ //  percorre todos os aventureiros
            if(getAventureiros().get(k)!=null){ // verifica se existe este aventureiro
                int numVis = NumCacheVisData(getAventureiros().get(k).getListCacheVisit(), i, f, getAventureiros().get(k).getDatas()); // numero de caches visitadas do aventureiro em que estamos entre as datas dadas
                if(cv.isEmpty()) { // se o arrayList temp estiver vazio vai logo para o 1 lugar
                    cv.add(numVis);
                    temp.add(getAventureiros().get(k));
                }else{ // se ja tiver algo no array vamos comparar cada posicao com o atual
                    for (int o = 0; o < cv.size(); o++){ // vai percorrer o arrayList e comparar com o aventureiro
                        if(cv.get(o) < numVis){ // se a posicao que estamos a comparar tiver menos caches visitadas que o aventureiro que estamos
                            //este if e else faz como que cada posicao ande um para a frente
                            if(cv.size() == o+1){ // aqui e quando estamos a comparar com a ultima posicao do arraylisst
                                cv.add(cv.get(o));
                                temp.add(temp.get(o));
                            }else{ // aqui estamos a comparar com uma posicao do meio do arraylist e temos de os iterar todos uma posicao para a frente
                                for(int j = cv.size(); j > o; j--) {
                                    if(j == cv.size()){
                                        cv.add(cv.get(j-1));
                                        temp.add(temp.get(j-1));
                                    }else{
                                        cv.set(j, cv.get(j-1));
                                        temp.set(j, temp.get(j-1));
                                    }
                                }
                            }
                            temp.set(o, getAventureiros().get(k));
                            cv.set(o, numVis);
                            o = cv.size();
                        }
                        else if(o == cv.size()-1){ // se ele tiver menos que todos pomo-lo no final
                            cv.add(numVis);
                            temp.add(getAventureiros().get(k));
                            o = cv.size();
                        }
                    }
                }
                x++;
            }
            k++;
        }
        //printar a lista ordenada
        System.out.println("O top 5 de utilizadores com mais caches visitadas entre as datas: " + i + " e " + f + " sao: ");
        for (int j = 0; j < 5; j++) {
            System.out.println(temp.get(j).getNome() + " visitou no total : " + temp.get(j).getNumCacheVis() + " caches e visitou: " + cv.get(j) + " caches entres estas datas.");
        }
    }

    /**
     * Metodo que retorna o numero de caches visitadas entre determinadas datas de um aventureiro
     * @param cache - BST de caches
     * @param i - Data inicial
     * @param f - Data Final
     * @param data - BST de datas que cada posicao corresponde a mesma posica da BST de caches, ex(cache(0) foi na data(0))
     * @return numero de caches visitadas
     */
    public int NumCacheVisData(BST_AED2_2021<Integer, Cache> cache, Date i, Date f, BST_AED2_2021<Integer, Date> data){
        int k = 0, count = 0;
        while(cache.size() > k){// percorre todas as caches
            if(f.afterDate(data.get(k)) && i.beforeDate(data.get(k))){ // se a visita tiver sido dentro das datas incrementa o count
                count++;
            }
            k++;
        }
        return count;
    }

    /**
     * Método que nos diz o número total de Caches já visitadas por um Aventureiro no Global
     * @param id - ID do Aventureiro
     */
    public void verTodasCachesVisGlobal(int id){
        if(aventureiros.get(id) != null) //se Aventureiro existir
            aventureiros.get(id).getListCacheVisit().printInOrder(aventureiros.get(id).getListCacheVisit().getRoot()); //printamos as Caches Visitadas pelo mesmo Aventureiro
    }

    /**
     * Método que nos diz o número total de Caches já visitadas por um Aventureiro numa Região
     * @param id - ID do Aventureiro
     * @param reg - Região especifica a verificar
     */
    public void verTodasCachesVisReg(int id, String reg){
        int k = 0;
        if(aventureiros.get(id) != null) //pesquiso o Aventureiro com o respetivo ID recebido
            while(aventureiros.get(id).getListCacheVisit().size() > k){ //percorre as caches visitadas
                if(aventureiros.get(id).getListCacheVisit().get(k).getLocal().getLocalizacao().equals(reg)) //se a região for igual à recebida
                    System.out.println(aventureiros.get(id).getListCacheVisit().get(k).toString()); //printamos a Cache
            k++;
            }
    }

    /**
     * Método para saber todas as Caches ainda não visitadas por um Aventureiro
     * @param gc - Acesso a todas as Caches
     * @param id - ID do Aventureiro
     */
    public void verTodasCachesNaoVisGlobal(GestaoAcessoCache gc, int id){
        int j = 1, x, count = 0;
        while (gc.getCaches().size() >= j){
            x = 0;
            while(aventureiros.get(id).getListCacheVisit().size() > x){ //percorre a lista de Caches Visitadas pelo Aventureiro
                if(gc.getCaches().get(j).getIdCache().equals(aventureiros.get(id).getListCacheVisit().get(x).getIdCache())) //comparamos um ID da lista total de Caches com um ID da lista de Caches do Aventureiro
                    count++;
                x++;
            }
            if(count == 0)
                System.out.println(gc.getCaches().get(j).toString());
            count = 0;
            j++;
        }
    }

    /**
     * Método para ver Todas as Caches nao visitadas numa determinada região
     * @param gc
     * @param id
     * @param reg
     */
    public void verTodasCachesNaoVisReg(GestaoAcessoCache gc, int id, String reg){
        int j = 1, x, count = 0;
        while (gc.getCaches().size() >= j){ //percorro a lista de Caches
            if(gc.getCaches().get(j).getLocal().getLocalizacao().equals(reg)){ //comparo as regiões das caches com a região enviada por parametro
                x = 0;
                while(aventureiros.get(id).getListCacheVisit().size() > x){ //percorro a lista de Caches visitadas
                    if(gc.getCaches().get(j).getIdCache().equals(aventureiros.get(id).getListCacheVisit().get(x).getIdCache())) //vejo os Aventureiroa que já visitaram a cache
                        count++;
                    x++;
                }
                if(count == 0) //se não tiver qualquer Aventureiro visitado determinada Cache numa região já determinada
                    System.out.println(gc.getCaches().get(j).toString()); //printamos essa Cache
                count = 0;
            }
            j++;
        }
    }



}