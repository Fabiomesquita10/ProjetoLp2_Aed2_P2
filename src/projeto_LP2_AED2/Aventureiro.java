package projeto_LP2_AED2;

import Search.BST_AED2_2021;

import java.io.Serializable;

public abstract class Aventureiro implements Serializable {

    //FIELDS/CAMPOS
    private Integer idAventureiro;
    private String nome;
    private LogsDiario diario = new LogsDiario();
    private Localizacao local;
    private int numCacheVis;
    private int numCacheEsc;
    private int numObj;
    private int numTb;
    private Date data = new Date();
    private String password;

    private BST_AED2_2021<Integer, Cache> listCacheVisit = new BST_AED2_2021<>(); //BST de Caches(lista de Caches Visitadas)
    private BST_AED2_2021<Integer, Date> datas = new BST_AED2_2021<>(); //BST de Datas(lista de Datas)
    private BST_AED2_2021<Integer, Cache> listCacheEsc = new BST_AED2_2021<>(); //BST de Caches(lista de Caches Escondidas)
    private BST_AED2_2021<Integer, Objeto> listObjetos = new BST_AED2_2021<>(); //BST de Objetos(lista de Objetos que já teve)
    private BST_AED2_2021<Integer, TravelBug> listTravelBug = new BST_AED2_2021<>(); //BST de TravelBugs(lista de TravelBugs que já teve)

    /**
     * Construtor Aventureiro(nome e localização)
     * @param nome - nome do Aventureiro
     * @param x - coordX
     * @param y - coordY
     */
    public Aventureiro(String nome, int x, int y) {
        this.nome = nome;
        this.local = new Localizacao(x, y);
    }

    public Aventureiro(String nome, int x, int y, String l) {
        this.nome = nome;
        this.local = new Localizacao(x, y, l);
    }

    public Aventureiro(String nome, int x, int y, String local, String pass) {
        this.nome = nome;
        this.local = new Localizacao(x, y, local);
        this.password = pass;
    }


    //GETTERS AND SETTERS
    public int getNumObj() {
        return numObj;
    }

    public void setNumObj(int numObj) {
        this.numObj = numObj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObjeto(){
        if(numObj>0)
            return ""+this.listObjetos.get(0).getNome();
        if(numTb>0)
            return ""+this.listTravelBug.get(numTb-1).getIdObjeto();
        return "empty";
    }

    public String getCache(){
        if(numCacheVis==0)
            return "empty";
        return this.listCacheVisit.get(0).getIdCache().toString();
    }

    public String getCacheVis(){
        return ""+numCacheVis;
    }

    public String getCacheEsc(){
        return ""+numCacheEsc;
    }

    public String getTipo(){
        if(this instanceof Premium)
            return "Premium";
        else if(this instanceof Admin)
            return "Admin";
        else if(this instanceof Basic)
            return "Basic";
        else return "bugado";
    }

    public String CachesEsc(){return ""+numCacheEsc;}

    public int getNumTb() {
        return numTb;
    }

    public void setNumTb(int numTb) {
        this.numTb = numTb;
    }

    public BST_AED2_2021<Integer, Date> getDatas() {
        return datas;
    }

    public void setDatas(BST_AED2_2021<Integer, Date> datas) {
        this.datas = datas;
    }

    public BST_AED2_2021<Integer, TravelBug> getListTravelBug() { return listTravelBug; }

    public void setListTravelBug(BST_AED2_2021<Integer, TravelBug> listTravelBug) { this.listTravelBug = listTravelBug; }

    public BST_AED2_2021<Integer, Objeto> getListObjetos() {
        return listObjetos;
    }

    public void setListObjetos(BST_AED2_2021<Integer, Objeto> listObjetos) {
        this.listObjetos = listObjetos;
    }

    public int getNumCacheVis() {
        return numCacheVis;
    }

    public void setNumCacheVis(int numCacheVis) {
        this.numCacheVis = numCacheVis;
    }

    public int getNumCacheEsc() {
        return numCacheEsc;
    }

    public void setNumCacheEsc(int numCacheEsc) {
        this.numCacheEsc = numCacheEsc;
    }

    public Localizacao getLocal() {
        return local;
    }

    public String getLoc(){return this.local.getLocalizacao();}

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    public String getId() {
        return idAventureiro.toString();
    }

    public int getIdAventureiro() {
        return idAventureiro;
    }

    public void setIdAventureiro(Integer idAventureiro) {
        this.idAventureiro = idAventureiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BST_AED2_2021<Integer, Cache> getListCacheVisit() {
        return listCacheVisit;
    }

    public BST_AED2_2021<Integer, Cache> getListCacheEsc() {
        return listCacheEsc;
    }

    /**
     * Método para adicionar uma cache visitada ao Aventureiro
     * @param c - Cache visitada
     * @param d - Data
     */
    public void addCacheVis(Cache c, Date d){
        //Temos duas BST´s, uma com as caches e outra com as datas(as posições das duas BST´s estão interligadas)
        //Na pos1 da BST de caches á cache inserida, corresponde a data na pos1 da BST de Datas
        listCacheVisit.put(numCacheVis,c); //coloco a nova cache na BST listCacheVisit
        datas.put(numCacheVis, d); //coloco na mesma posicão mas agora a Data na BST de datas
        numCacheVis++; //iteramos o numero de Caches Visitadas
        String toDiario ="O utilizador " + this.getNome() + " visitou esta cache: " + c.toString(); //imprime a cache que visitou
        diario.adicionaLog(toDiario, data,"data/LogsAventureiro"); //Escrever para o ficheiro de Logs
        toDiario = "Dados do utilizador: " + this.toString(); //de seguida imprime os dados desse mesmo Utilizador
        diario.adicionaLog(toDiario,data,"data/LogsAventureiro"); //Escrever para o ficheiro de Logs

    }

    /**
     * Método para adicionar Cache escondida
     * @param c - Cache escondida
     * @return
     * @throws AventureiroNaoHabilitado
     */
    public boolean addCacheEsc(Cache c) throws AventureiroNaoHabilitado {
        if(this instanceof Premium || this instanceof Admin){ //Se o Aventureiro for Premium ou Admin(só estes podem esconder uma Cache)
            listCacheEsc.put(numCacheEsc,c); //adiciono á BST de Caches escondidas
            numCacheEsc++; //itero o número de Caches Escondidas
            String toDiario = "O utilizador " + this.getNome() + " criou e escondeu esta cache: " + c.toString(); //imprime a cache que escondeu
            diario.adicionaLog(toDiario, data, "data/LogsAventureiro"); //Escrever para o ficheiro de Logs
            toDiario = "Dados do utilizador: " + this.toString(); //de seguida imprime os dados desse mesmo Utilizador
            diario.adicionaLog(toDiario,data,"data/LogsAventureiro"); //Escrever para o ficheiro de Logs
            return true;
        }
        throw new AventureiroNaoHabilitado("Nao esta habilitado a criar cache");
    }

    /**
     * Método para remover Cache Escondida
     * @param c - Cache a remover
     * @throws CacheNaoExisteException
     */
    public void removeCacheEsc(Cache c) throws CacheNaoExisteException {
        if(this.getListCacheEsc().contains(c.idCache)){ //vou há minha lista de caches escondidas e vejo se contem a Cache que pretendo remover
            this.getListCacheEsc().delete(c.idCache); //removo
        }
        throw new CacheNaoExisteException("Esta a remover uma cache que nao existe");
    }

    /**
     * Método que aborda todo o processo de encontrar uma cache (retiramos objeto e adicionamos objeto)
     * @param c - Cache encontrada
     * @param o - Objeto a colocar
     * @param d - Data
     */
    public void encontrouCache(Cache c, Objeto o, Date d){
        this.listObjetos.put(numObj, c.getObjeto()); //coloco na minha lista de Objetos o Objeto que estava na cache
        numObj++; //itero o número de Objetos
        c.getObjeto().setAventureiro(this); //dar set do Aventureiro no Objeto
        c.getObjeto().setViajar(true); //Objeto que estava na Cache e passou para o Aventureiro passa a estar em "viagem"
        o.setViajar(false); //Objeto que coloquei para ficar na Cache para a "nao estar em viagem"
        c.getHistAventureiros().put(c.getNumAvent(), this); //coloco no histórico de Aventureiros da cache o Aventureiro que a visitou
        c.setNumAvent(c.getNumAvent()+1); //itera o número de Aventureiros que ja la passaram
        c.removeObjeto(c.getObjeto()); //remove o Objeto da Cache
        this.addCacheVis(c, d); //adiciono ao Aventureiro uma nova Cache visitada
        c.removeObjeto(c.getObjeto()); //remove o Objeto da Cache
        c.setObjeto(o); //Coloco o novo Objeto na Cache
         //decremento o número de objetos
    }

    /**
     * Método que aborda todo o processo de encontrar uma cache(só retiramos objeto da Cache)
     * @param c - Cache encontrada
     * @param d - Data
     */
    public void encontrouCache(Cache c, Date d, GestaoAcessoObjeto go){
        if(c.getObjeto()!=null){
            go.getObjetos().get(c.getObjeto().getIdObjeto()).setAventureiro(this);
            go.getObjetos().get(c.getObjeto().getIdObjeto()).setViajar(true);;
            this.listObjetos.put(numObj, c.getObjeto()); //coloco na minha lista de Objetos o Objeto que estava na cache
            numObj++; //itero o número de Objetos
            c.removeObjeto(c.getObjeto()); //remove o Objeto da Cache
        }
        else if(c.getTravelbug() != null){
            go.getTravelBug().get(c.getTravelbug().getIdObjeto()).setAventureiro(this);
            go.getTravelBug().get(c.getTravelbug().getIdObjeto()).setViajar(true);;
            this.listTravelBug.put(numTb, c.getTravelbug());
            numTb++;
            c.removeTravelBug();
        }

        c.getHistAventureiros().put(c.getNumAvent(), this); //coloco no histórico de Aventureiros da cache o Aventureiro que a visitou
        c.setNumAvent(c.getNumAvent()+1); //itera o número de Aventureiros que ja la passaram
        this.addCacheVis(c, d); //adiciono ao Aventureiro uma nova Cache visitada
        c.removeObjeto(c.getObjeto()); //remove o Objeto da Cache
    }


    /**
     * Método que aborda todo o processo de encontrar/visitar uma cache
     * @param c - Cache encontrada
     * @param bg - TravelBug a colocar
     * @param d - Data
     * @throws MissaoNaoCompletadaComExitoException
     */
    public void encontrouCache(PremiumCache c, TravelBug bg, Date d) throws MissaoNaoCompletadaComExitoException {
        int count = 0, j = 0;
        for (int i = 1; i < bg.getNumCachesPres(); i++) {
            System.out.println(bg.getListaCachesPresente().get(i).getIdCache());
        }
        while(bg.getTbMission().size() > j){
            if(bg.getTbMission().get(j).getIdCache().equals(c.getIdCache()))
                count++;
            j++;
        }
        if(count != 0 || bg.getTbMission().size()==0) {
            if (this.getListTravelBug().get(0).getIdObjeto().equals(bg.getIdObjeto())) {
                c.getTravelbug().getListaAventureiros().put(c.getTravelbug().getNumAventureiros(), this);
                c.getTravelbug().setNumAventureiros(c.getTravelbug().getNumAventureiros() + 1);
                this.listTravelBug.put(numTb, c.getTravelbug());
                numTb++;
                c.getTravelbug().setViajar(true);
                bg.setViajar(false);
                this.addCacheVis(c, d);
                c.getHistAventureiros().put(c.getNumAvent(), this);
                c.setNumAvent(c.getNumAvent() + 1);
                c.removeObjeto(c.getObjeto());
                c.setTravelbug(bg);
                bg.getListaCachesPresente().put(bg.getNumCachesPres(), c);
                bg.setNumCachesPres(bg.getNumCachesPres() + 1);
            }
            bg.getTbMission().removeAll(bg.getTbMission());
            return;
        }
        throw new MissaoNaoCompletadaComExitoException("Esta cache nao e uma das caches que pode receber o TravelBug!");
    }

    /**
     * Método toString do Aventureiro
     * @return
     */
    @Override
    public String toString() {
        return "Id: " + idAventureiro +
                ", nome: " + nome +
                ", local: " + local +
                ", numCacheVis: " + numCacheVis +
                ", numCacheEsc: " + numCacheEsc;
    }
}