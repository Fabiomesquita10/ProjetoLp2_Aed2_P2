package projeto_LP2_AED2;

import Search.BST_AED2_2021;
import Search.RedBlack_AED2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class GestaoAcessoCache implements GestaoCache{

    //FIELDS/CAMPOS
    private RedBlack_AED2<Integer, Cache> caches = new RedBlack_AED2<>(); //RedBlack de caches
    GestaoAcessoObjeto gao = new GestaoAcessoObjeto();
    private int numCache = 1;
    private LogsDiario diario = new LogsDiario();
    private Date data = new Date();

    //GETTERS AND SETTERS
    public int getNumCache() {
        return numCache;
    }

    public void setNumCache(int numCache) {
        this.numCache = numCache;
    }

    public RedBlack_AED2<Integer, Cache> getCaches() {
        return caches;
    }

    public void setCaches(RedBlack_AED2<Integer, Cache> caches) {
        this.caches = caches;
    }

    /**
     * Método para adicionar uma determinada cache
     * @param cache - Cache a adicionar
     * @return
     */
    @Override
    public boolean adicionaCache(Cache cache) {
        cache.setIdCache(numCache); //set ID(ID criado/adicionado consoante a variável numCache)
        caches.put(numCache,cache); //adicionar a nova cache na RedBlack de Caches
        numCache++; //iteramos a cada nova adição de uma cache(essencial para a criação dos ID´s)
        String toDiario = "Adicionada cache com o ID " + cache.getIdCache(); //mensagem a escrever no ficheiro(Adição de nova cache)
        diario.adicionaLog(toDiario, data, "data/LogsCache"); //escrevo no ficheiro LogsCache através da função "adicionaLog" criada na classe LogsDiario
        return true;
    }

    /**
     * Método para remover uma determinada cache
     * @param idCache - ID da Cache a remover
     * @return
     * @throws CacheNaoExisteException
     */
    @Override
    public boolean removeCache(Integer idCache) throws CacheNaoExisteException{
        if(caches.contains(idCache)){ //verificamos se a mesma existe na RedBlack de caches
            caches.delete(idCache); //removemos a cache especifica
            String toDiario = "Removeu a cache com o ID " + idCache; //Mensagem a escrever no ficheiro(Remoção de uma cache)
            //System.out.println(toDiario);
            diario.adicionaLog(toDiario, data, "data/Arquivo.txt"); //Escrevo no ficheiro Arquivo através da função "adicionaLog" criada na classe LogsDiario
            diario.adicionaLog(toDiario, data, "data/LogsCache"); //Escrevo no ficheiro LogsCache através da função "adicionaLog" criada na classe LogsDiario
            return true;
        }
        throw new CacheNaoExisteException("Cache a remover não existe!!");
    }

    /**
     * Método que retorna True ou False se a cache existe ou não
     * @param idCache - ID da Cache para verificar-mos se existe
     * @return
     */
    @Override
    public boolean existeCache(Integer idCache) {
        if(caches.contains(idCache)){ //se o array contêm a cache recebida retornamos True
            return true;
        }
        return false;
    }

    /**
     * Método para depositarmos determinado Objeto numa determinada Cache
     * @param objeto - Objeto a depositar
     * @param Cache - Cache a visitar
     * @return
     * @throws JaExisteObjetoNumaCacheException
     */
    @Override
    public boolean depositaObjeto(Objeto objeto, Cache Cache) throws JaExisteObjetoNumaCacheException {
        int x = 1;
        while(x <= caches.size()) { //percorre o array de caches
            if (caches.get(x).getIdCache().equals(Cache.idCache)) { //encontro a cache
                caches.get(x).setObjeto(objeto); //coloco o objeto
                String toDiario = "Depositou o " + objeto.toString() + " numa Cache com o ID " + Cache.idCache;
                diario.adicionaLog(toDiario, data, "data/LogsCache"); //escrevo no ficheiro este mesmo Log(Depositar objeto numa cache)
                return true;
            }
            x++;
        }
        throw new JaExisteObjetoNumaCacheException("Já existe Objeto na Cache!!");

    }

    /**
     * Metodo para teste da API de insercao, remocao e edicao de Caches
     * @param ga - acesso a todos os aventureiros
     * @param go - acesso a todos os objetos
     * @throws AventureiroNaoExisteException
     * @throws AventureiroNaoHabilitado
     * @throws JaExisteObjetoNumaCacheException
     * @throws ParseException
     * @throws CacheNaoExisteException
     */
    public void menuGestaoCache(GestaoAcessoAventureiro ga, GestaoAcessoObjeto go) throws AventureiroNaoExisteException, AventureiroNaoHabilitado, JaExisteObjetoNumaCacheException, ParseException, CacheNaoExisteException {
        boolean f = true;
        while (f){
            Scanner sc = new Scanner(System.in);
            System.out.println("[1]-Adicionar Cache");
            System.out.println("[2]-Remover Cache");
            System.out.println("[3]-Editar Cache");
            System.out.println("[4]-Voltar");
            int aux2 = sc.nextInt();
            switch (aux2){
                case 1:
                    sc.nextLine();
                    System.out.println("Insira o id do aventureiro:");
                    int idA = sc.nextInt();
                    System.out.println("Insira a localizacao x:");
                    int x = sc.nextInt();
                    System.out.println("Insira a localizacao y:");
                    int y = sc.nextInt();
                    System.out.println("Insira a dificuldade da cache:");
                    int dif = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Insira o local em que a cache se encontra:");
                    String local = sc.nextLine();
                    System.out.println("Insira o tipo de cache:");
                    String tipo = sc.nextLine();
                    boolean correto = false;
                    while(!correto) {
                        if (tipo.equals("Premium")) { //se for Premium
                            System.out.println("Insira o id do travelBug:");
                            int idT = sc.nextInt();
                            PremiumCache c = new PremiumCache(dif, ga.getAventureiros().get(idA), go.getTravelBug().get(idT), x, y, local);
                            adicionaCache(c);
                            correto = true;
                        } else if (tipo.equals("Basic")) { //se for Basic
                            System.out.println("Insira o id do Objeto:");
                            int ido = sc.nextInt();
                            BasicCache c = new BasicCache(dif, ga.getAventureiros().get(idA), go.getObjetos().get(ido), x, y, local);
                            adicionaCache(c);
                            correto = true;
                        } else {
                            System.out.println("Volte a inserir o tipo de Cache:");
                            tipo = sc.nextLine();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Insira o id da cache");
                    int id = sc.nextInt();
                    removeCache(id);
                    break;
                case 3:
                    System.out.println("Qual o id da cache:");
                    id = sc.nextInt();
                    System.out.println("Insira a dificuldade da Cache:");
                    dif = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Insira o nome da localizacao:");
                    local = sc.nextLine();
                    System.out.println("Insira as coordenadas X:");
                    x = sc.nextInt();
                    System.out.println("Insira as coordenadas Y:");
                    y = sc.nextInt();
                    editarCache(id, dif, x, y, local);
                    break;
                case 4:
                    go.guardarObjeto(this, ga);
                    guardarCache(ga, go);
                    ga.guardarAventureiros(this, go);
                    Main.clientTeste13(ga, this, go, 0);
                    f = false;
                    break;
            }
        }
    }

    @Override
    public boolean editarCache(Integer id, int dif, int x, int y, String local){
        if(caches.get(id) != null){
            caches.get(id).setDificuldade(dif);
            caches.get(id).getLocal().setCoordenadaX(x);
            caches.get(id).getLocal().setCoordenadaY(y);
            caches.get(id).getLocal().setLocalizacao(local);
            return true;
        }
        return false;
    }

    /**
     * Método que "retorna" o número de Objeto/TravelBugs presentes em determinada cache
     */
    public void cachePremiumComObjeto(){
        int x = 1, j = 1;
        ArrayList<Integer> ids = new ArrayList<>(); //array de ID´s dos objetos presentes
        while (caches.size()>=j){
            if(caches.get(x)!=null && caches.get(x) instanceof PremiumCache){
                if(caches.get(x).getObjeto()!=null)
                    ids.add(caches.get(x).getObjeto().getIdObjeto());
                if (caches.get(x).getTravelbug()!=null)
                    ids.add(caches.get(x).getTravelbug().getIdObjeto());
                j++;
            }
            x++;
        }
        for (Integer i : ids)
            System.out.println("Cache: " + i); //printamos os ID´s das caches presentes
    }

    /**
     * Método para retirar um objeto de uma determinada Cache
     * @param Cache - Cache onde vamos retirar o Objeto
     * @return
     * @throws JaExisteObjetoNumaCacheException
     */
    @Override
    public boolean retiraObjeto(Cache Cache) throws JaExisteObjetoNumaCacheException {
        int x = 1;
        while(x <= caches.size()) { //percorre a RedBlack de caches
            if(caches.get(x).getIdCache().equals(Cache.idCache)){ //encontro a cache
                String toDiario = "Retirou o " + caches.get(x).getObjeto().toString() + " na Cache com o id: " + Cache.idCache;
                caches.get(x).setObjeto(null); //coloco o Objeto dessa Cache a null
                diario.adicionaLog(toDiario, data, "data/LogsCache"); //escrevo no ficheiro este mesmo Log(Retirar objeto de uma cache)
                return true;
            }
            x++;
        }
        throw new JaExisteObjetoNumaCacheException("Não existe objeto na cache!!");
    }

    /**
     * Método para guardar todas as caches existentes em Ficheiro
     * @return
     * @throws CacheNaoExisteException
     */
    public boolean guardarCache(GestaoAcessoAventureiro ga, GestaoAcessoObjeto go) throws CacheNaoExisteException{
        if(caches.size() > 0 ){ //se existir Caches no array de Caches
            Out outfile = new Out("data/Caches.txt");
            int x = 1, k = 1;
            while (k <= caches.size()){
                if(caches.get(x) != null){
                    if(caches.get(x).getAventureiro() != null){
                        if(caches.get(x) instanceof BasicCache){ //se a Cache for uma instancia de BasicCache
                            //Escrevo os dados dessa mesma Cache(ID, Dificuldade, IDObjeto, IDAventureiro, Coordenadas, Local)
                            StringBuilder toSave = new StringBuilder("Basic " + caches.get(x).getIdCache() + " " + caches.get(x).getDificuldade() + " " + caches.get(x).getObjeto().getIdObjeto() + " " + caches.get(x).getAventureiro().getIdAventureiro() + " " + caches.get(x).getLocal().getCoordenadaX() + " " + caches.get(x).getLocal().getCoordenadaY() + " " + caches.get(x).getLocal().getLocalizacao());
                            int numAvent = caches.get(x).getNumAvent();
                            int j = 0;
                            StringBuilder avent = new StringBuilder();
                            while(j < caches.get(x).getHistAventureiros().size()) { //percorro o array de aventureiros dessa cache e imprimo o ID dos mesmos
                                //verificacao da existencia do aventureiro
                                if(ga.getAventureiros().contains(caches.get(x).getHistAventureiros().get(j).getIdAventureiro()))
                                    avent.append(" ").append(caches.get(x).getHistAventureiros().get(j).getIdAventureiro());
                                else {
                                    caches.get(x).setNumAvent(caches.get(x).getNumAvent()-1);
                                }
                                j++;
                            }
                            if(caches.get(x).getNumAvent() <=0 ){
                                toSave.append(" ").append(0);
                            }else{
                                toSave.append(" ").append(caches.get(x).getNumAvent()); //de seguida escrevo o numero de aventureiros que essa mesma cache já teve
                                toSave.append(avent);
                            }
                            outfile.println(toSave.toString());
                            k++;
                        }else if(caches.get(x) instanceof PremiumCache){ //se a Cache for uma instancia de PremiumCahce
                            //Escrevo os dados dessa mesma Cache(ID, Dificuldade, IDObjeto, IDAventureiro, Coordenadas, Local)
                            StringBuilder toSave = new StringBuilder("Premium " + caches.get(x).getIdCache() + " " + caches.get(x).getDificuldade() + " " + caches.get(x).getTravelbug().getIdObjeto() + " " + caches.get(x).getAventureiro().getIdAventureiro() + " " + caches.get(x).getLocal().getCoordenadaX() + " " + caches.get(x).getLocal().getCoordenadaY() + " " + caches.get(x).getLocal().getLocalizacao()); ;
                            int numAvent = caches.get(x).getNumAvent();
                            int j = 0;
                            StringBuilder avent = new StringBuilder();
                            while(j < caches.get(x).getHistAventureiros().size()) { //percorro o array de aventureiros dessa cache e imprimo o ID dos mesmos
                                //verificacao da existencia do aventureiro
                                if(ga.getAventureiros().contains(caches.get(x).getHistAventureiros().get(j).getIdAventureiro()))
                                    avent.append(" ").append(caches.get(x).getHistAventureiros().get(j).getIdAventureiro());
                                else {
                                    caches.get(x).setNumAvent(caches.get(x).getNumAvent()-1);
                                }
                                j++;
                            }
                            if(caches.get(x).getNumAvent() <=0 ){
                                toSave.append(" ").append(0);
                            }else{
                                toSave.append(" ").append(caches.get(x).getNumAvent()); //de seguida escrevo o numero de aventureiros que essa mesma cache já teve
                                toSave.append(avent);
                            }
                            outfile.println(toSave.toString());
                            k++;
                        }
                    }
                }
                x++;
            }
            return true;
        }
        throw new CacheNaoExisteException("Cache nao Existe!");
    }

    /**
     * Método para ler todas as Caches e criá-las através da leitura do Ficheiro
     * @param ga - Acesso a todos os Aventureiros
     * @param go - Acesso a todos os Objetos
     * @throws AventureiroNaoHabilitado
     */
    @Override
    public void lerCache(GestaoAcessoAventureiro ga, GestaoAcessoObjeto go) throws AventureiroNaoHabilitado {
        In infile = new In("data/Caches.txt");
        ArrayList<Integer> idAventureiro = new ArrayList<>(); //crio um array que recebe os ID´s dos Aventureiros que a cache já teve
        int k = 1, j = 1;
        String line = null;
        while((line = infile.readLine()) != null){ //até lermos o ficheiro completo
            idAventureiro.removeAll(idAventureiro);
            //Repartimos por " "
            String[] parts = line.split(" ");
            String idCache = parts[1];
            int idC = Integer.parseInt(idCache);
            String dif = parts[2];
            int dific = Integer.parseInt(dif);
            String idObjeto = parts[3];
            int idO = Integer.parseInt(idObjeto);
            String idAvent = parts[4];
            int idA = Integer.parseInt(idAvent);
            String coordX = parts[5];
            int cX = Integer.parseInt(coordX);
            String coordY = parts[6];
            int cY = Integer.parseInt(coordY);
            String local = parts[7];
            String numA = parts[8];
            int numAvent = Integer.parseInt(numA);
            int p = numAvent, i = 9;
            while(p>0){
                idAventureiro.add(Integer.parseInt(parts[i])); //adicionao ao array criado acima os ID´s dos Aventureiros
                i++;
                p--;
            }
            if(parts[0].equals("Premium")){ //se for Premium
                PremiumCache c = new PremiumCache(dific, ga.getAventureiros().get(idA), go.getTravelBug().get(idO), cX, cY, local);
                int x = 0;
                while (idAventureiro.size() > x) {
                    c.getHistAventureiros().put(c.getNumAvent(), ga.getAventureiros().get(idAventureiro.get(x))); //adiciono ao array do HistAventureiros(envio o numAvent(funciona como ID) e mando o Aventureiro)
                    c.setNumAvent(c.getNumAvent()+1); //iterar o número de aventureiros
                    x++;
                }
                c.setIdCache(numCache); //set ID(consoante o número de caches existentes)
                getCaches().put(numCache, c); //coloco a cache na RedBlack
                setNumCache(getNumCache()+1); //iterar o número de caches
            }else if(parts[0].equals("Basic")){ //se for Basic
                BasicCache c = new BasicCache(dific, ga.getAventureiros().get(idA), go.getObjetos().get(idO), cX, cY, local);
                int x = 0;
                while (idAventureiro.size() > x) {
                    c.getHistAventureiros().put(c.getNumAvent(), ga.getAventureiros().get(idAventureiro.get(x))); //adiciono ao array do HistAventureiros(envio o numAvent(funciona como ID) e mando o Aventureiro)
                    c.setNumAvent(c.getNumAvent()+1); //iterar o número de aventureiros
                    x++;
                }
                c.setIdCache(numCache); //set ID(consoante o número de caches existentes)
                getCaches().put(numCache, c); //coloco a cache na RedBlack
                setNumCache(getNumCache()+1); //iterar o número de caches
            }
        }
    }
}
