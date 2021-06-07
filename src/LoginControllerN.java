import Search.BST_AED2_2021;
import Search.RedBlack_AED2;
import SearchProj.*;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import projeto_LP2_AED2.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginControllerN implements Initializable {

    //vairavel para saber se aparecem as setas ou nao
    int setas = 1;

    public Group graphGroup;
    public Group temp;
    //tamanho dos circulos das caches
    int radius = 10;
    int radius2 = radius + 55;

    GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
    GestaoAcessoCache gc = new GestaoAcessoCache();
    GestaoAcessoObjeto go = new GestaoAcessoObjeto();
    GestaoAcessoCacheGraph gcg = new GestaoAcessoCacheGraph();

    //HANDLERS PESQUISAS GRAFOS
    public TextField tipoCache;
    public TextField difiCache;
    public TextField regiaoCache;
    public TextField PercTb;
    public TextField cachePartida;
    public TextField cacheChegada;
    public TextArea consolaMapa;

    private String tipoPesquisa;
    //caxeiro-viajante
    public TextField tempoL;
    public TextField cacheP;
    public TextArea textCaixeiro;
    //adiciao
    public ComboBox<String> comboBoxTipoCache;
    public TextField addLocalC;
    public TextField addIdAventC;
    public TextField addIdObjetoC;
    public TextField addDificC;
    //edicao
    public TextField editIdCacheC;
    public TextField editLocalC;
    public TextField editDificC;
    //Caminhos
    public TextArea consolaCaminhos;
    public TextField cachePartidaAC;
    public TextField distanciaAC;
    public TextField tempoAC;
    public TextField cacheChegadaAC;
    public TextField elevacaoAC;

    //Mapa Jogar
    public TextField idAventPA;
    public TextField cacheEncJ;
    public TextField idAventJ;
    public TextField cacheDepJ;
    ArrayList<Integer> ids = new ArrayList<>();

    //consola do jogo
    public TextArea consolaAplicacao;


    @Override
    /**
     *
     */
    public void initialize(URL location, ResourceBundle resources) {

        try {
            carregarFicheiro();
        } catch (AventureiroNaoHabilitado aventureiroNaoHabilitado) {
            aventureiroNaoHabilitado.printStackTrace();
        } catch (CacheNaoExisteException e) {
            e.printStackTrace();
        } catch (AventureiroNaoExisteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Método para criar grafo consoante numero de vertices e edges
     * Neste mesmo método temos as diferentes interações com o clicar numa cache(vértice)
     * ou num caminho(edge)
     * @param ga
     * @param gcg
     * @param go
     */
    public void creatGraphGroup(GestaoAcessoAventureiro ga, GestaoAcessoCacheGraph gcg, GestaoAcessoObjeto go){
        if(gcg.getGrafo().E() > 0 && setas == 1) {
            int w = 1, z = 1;
            while(z<gcg.getGrafo().getNumCache()){ //edges
                if(gcg.getGrafo().getCaches().contains(w)){
                    criar_direct_edge(w);// mandamos a cache
                    z++;
                }
                w++;
            }
        }
        int w = 1, z = 1;
        while(z<gcg.getGrafo().getNumCache()){ //vertices
            if(gcg.getGrafo().getCaches().contains(w)){
                criar_graph(w); //mandamos a cache
                z++;
            }
            w++;
        }

        graphGroup.addEventHandler(MouseEvent.MOUSE_RELEASED, evtScene -> { //MouseEvent
            EventTarget evtCircleTarget=evtScene.getTarget();
            if(evtCircleTarget instanceof Circle){
                int x = 1, k = 1;
                int id = 0;
                double cX = ((Circle) evtCircleTarget).getCenterX();
                double cY = ((Circle) evtCircleTarget).getCenterY();
                while(k<=gc.getCaches().size()){
                    if(gc.getCaches().contains(x)){
                        if(gc.getCaches().get(x).getLocal().getCoordenadaX() == cX)
                            if(gc.getCaches().get(x).getLocal().getCoordenadaY() == cY)
                                id = gc.getCaches().get(x).getIdCache();
                        k++;
                    }
                    x++;
                }
                //id++;
                //Quando clicarmos numa cache no grafo, criamos um novo circulo que nos mostra as caracteristicas dessa mesma cache
                Cache c1 = gc.getCaches().get(id);
                String info = "  Id: "+c1.getId() + "\n  Local: " + c1.getLocal().getLocalizacao() + "\n  Criador: " + c1.getAventureiro().getNome() +
                        "\n  Tipo: " + c1.getTipo();
                if(c1.getObjeto() != null){
                    info = info + "\n  Objeto: " + c1.getObjeto().getNome();
                }
                else if(c1.getTravelbug() != null){
                    info = info + "\n  Objeto: " + c1.getTravelbug().getNome();
                }
                else{
                    info = info + "\n  Objeto: Empty";
                }
                info = info + "\n  Dificuldade: " + c1.getDificuldade();
                Circle r = new Circle(100, 100, radius2);
                if(c1 instanceof BasicCache)
                    r.setFill(Color.SILVER);
                if(c1 instanceof PremiumCache)
                    r.setFill(Color.GOLD);
                StackPane stack2 = new StackPane();
                stack2.setLayoutX(cX-radius2);
                stack2.setLayoutY(cY-radius2);
                stack2.getChildren().addAll(r, new Text(info));
                System.out.println(info);
                temp = graphGroup;
                graphGroup.getChildren().add(stack2);
            }else if(evtCircleTarget instanceof Arrow){ //Se clicarmos na seta é-nos tambem apresentada informação da mesma
                if(((Arrow) evtCircleTarget).getFill() != Color.DARKRED)
                    ((Arrow) evtCircleTarget).setFill(Color.DARKRED);
                else{
                    String infoArrow = "Info da seta selecionada: \n" + "Peso: " + ((Arrow) evtCircleTarget).getW() +
                            "\nDistancia: " + ((Arrow) evtCircleTarget).getD() + "\nTempo: " + ((Arrow) evtCircleTarget).getT() +
                            "\nElevacao: " + ((Arrow) evtCircleTarget).getE();
                    consolaMapa.setText(consolaMapa.getText() + "\n" + infoArrow);
                    ((Arrow) evtCircleTarget).setFill(Color.WHITESMOKE);
                }
            }
            else{
                graphGroup.getChildren().clear();
                if(gcg.getGrafo().E() > 0 && setas == 1){
                    int a = 1;
                    int b = 1;
                    while(b<gcg.getGrafo().getNumCache()){
                        if(gcg.getGrafo().getCaches().contains(a)){
                            criar_direct_edge(a);
                            b++;
                        }
                        a++;
                    }
                }
                int a = 1, b = 1;
                while(b<gcg.getGrafo().getNumCache()){
                    if(gcg.getGrafo().getCaches().contains(a)){
                        criar_graph(a);
                        b++;
                    }
                    a++;
                }
            }
        });
    }

    /**
     * Criar os caminhos a partir da cache que estou(todas as adjacencias)
     * @param k - Respetiva cache
     */
    public void criar_direct_edge(int k){
        //por handler em cada seta
        for(DirectedEdge_AED2 adj: gcg.getGrafo().adj(k)){ //percorremos todas as adj da cache k e ligamo-la a essas adj
            int w = (int)adj.weight(); //peso
            int d = adj.distancia(); //distancia
            int t = adj.tempo(); //tempo
            int e = adj.elevacao(); //elevação
            Arrow a;
            if(gcg.getGrafo().getCaches().contains(k) && gcg.getGrafo().getCaches().contains(adj.to())){
                a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20, w,d,t,e);
                a.setFill(Color.DARKRED);
                graphGroup.getChildren().add(a);
            }
        }
    }

    /**
     * Método para criar o grafo com as caches(vértices)
     * @param i - Cache
     */
    public void criar_graph(int i){
        if(gcg.getGrafo().getCaches().contains(i)){
            Circle c = new Circle(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY(), radius); //criar circulo
            StackPane stack = new StackPane();
            String tipo = "";
            if(gcg.getGrafo().getCaches().get(i) instanceof BasicCache) //se for Basic Cache
                c.setFill(Color.SILVER);
            if(gcg.getGrafo().getCaches().get(i) instanceof PremiumCache) //se for Premium Cache
                c.setFill(Color.GOLD);
            String txt = ""+gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY() + " " + gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX();
            stack.setLayoutX(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX()-radius);
            stack.setLayoutY(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY()-radius);
            //stack.getChildren().addAll(c, new Text(gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao()));
            //stack.getChildren().addAll(c, new Text(txt));
            stack.getChildren().addAll(c);

            //por handler em cada cache

            graphGroup.getChildren().add(stack);
        }
    }

    /**
     * Função para carregar Ficheiros, necessario nas tabelas e na atualizaçao das mesmas
     * @throws AventureiroNaoHabilitado
     * @throws AventureiroNaoExisteException
     * @throws CacheNaoExisteException
     */
    public void carregarFicheiro() throws AventureiroNaoHabilitado, AventureiroNaoExisteException, CacheNaoExisteException {

        /*
        ga.lerAventureiros(); // mudar funcao de leitura e escrita para por o local
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        ga.guardarAventBin();
        gc.guardarCachesBin();
        go.guardarObjBin();
         */
        ga.lerAventBin();
        go.lerObjectBin();
        gc.lerCachesBin();
        ga.regista(new Admin("fabio",15,61,"penafiel", "12345678"));
        /*
        ga.guardarAventureiros(gc,go);
        go.guardarObjeto(gc,ga);
        gc.guardarCache(ga,go);
        */
        atualizarCaches();

        //teste de directedEdge
        gcg.lerCaminhos();

        creatGraphGroup(ga, gcg, go);

    }

    /**
     * Handler para Pesquisar determinada cache consoate x campos inseridos
     * @param actionEvent - Ao clicarmos no botao
     * @throws InterruptedException
     */
    public void handlerPesquisaCache(ActionEvent actionEvent) throws InterruptedException {
        //consolaMapa.setText(consolaMapa.getText() +  "PESQUISA CACHE\n");
        String tipo = "", regiao = "";
        int dif = 0, idTb = 0;
        //obter informação dos diferentes campos
        if(!tipoCache.getText().equals(""))
            tipo = tipoCache.getText();
        if(!regiaoCache.getText().equals(""))
            regiao = regiaoCache.getText();
        if(!difiCache.getText().equals(""))
            dif = Integer.parseInt(difiCache.getText());
        if(!PercTb.getText().equals(""))
            idTb = Integer.parseInt(PercTb.getText());
        //consolaMapa.setText(consolaMapa.getText() + tipo + " " + regiao + " " + dif);

        if(!tipo.equals("") && !regiao.equals("") && dif!=0){
            graphGroup.getChildren().clear();
            if(gcg.getGrafo().E() > 0 && setas == 1){
                for (int k = 1; k < gcg.getGrafo().getNumCache(); k++) { //percorre o numero de caches do grafo
                    if(gcg.getGrafo().getCaches().contains(k)){
                        if(tipo.equals("premium")) { //se for Premium
                            //procuramos a cache com a o tipo, regiao e a dificuldade insridas
                            if (gcg.getGrafo().getCaches().get(k) instanceof PremiumCache && gcg.getGrafo().getCaches().get(k).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(k).getLocal().getLocalizacao().equals(regiao)) {
                                //fazemos as respetivas ligaçoes para visualmente ser persetivel qual as caches dadas a uma determinada pesquisa
                                for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                    if (gcg.getGrafo().getCaches().get(adj.to()) instanceof PremiumCache && gcg.getGrafo().getCaches().get(adj.to()).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(adj.to()).getLocal().getLocalizacao().equals(regiao)) {
                                        int w = (int)adj.weight();
                                        int d = adj.distancia();
                                        int t = adj.tempo();
                                        int e = adj.elevacao();
                                        Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                                gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20, w,d,t,e);
                                        a.setFill(Color.DARKRED);
                                        graphGroup.getChildren().add(a);
                                    }
                                }
                            }
                        }
                        else if(tipo.equals("basic")) { //se for Basic
                            //procuramos a cache com a o tipo, regiao e a dificuldade inseridas
                            if (gcg.getGrafo().getCaches().get(k) instanceof BasicCache && gcg.getGrafo().getCaches().get(k).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(k).getLocal().getLocalizacao().equals(regiao)) {
                                //fazemos as respetivas ligaçoes para visualmente ser persetivel qual as caches dadas a uma determinada pesquisa
                                for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                    if (gcg.getGrafo().getCaches().get(adj.to()) instanceof BasicCache && gcg.getGrafo().getCaches().get(adj.to()).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(adj.to()).getLocal().getLocalizacao().equals(regiao)) {
                                        int w = (int)adj.weight();
                                        int d = adj.distancia();
                                        int t = adj.tempo();
                                        int e = adj.elevacao();
                                        Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                                gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20,w,d,t,e);
                                        a.setFill(Color.DARKRED);
                                        graphGroup.getChildren().add(a);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //Voltar a colocar o grafo completo
            for(int i=1; i<gcg.getGrafo().getNumCache(); i++){
                if(gcg.getGrafo().getCaches().contains(i)){
                    if(tipo.equals("premium")){
                        if(gcg.getGrafo().getCaches().get(i) instanceof PremiumCache && gcg.getGrafo().getCaches().get(i).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                            criar_graph(i);
                    }else if(tipo.equals("basic")){
                        if(gcg.getGrafo().getCaches().get(i) instanceof BasicCache && gcg.getGrafo().getCaches().get(i).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                            criar_graph(i);
                    }
                }
            }
            //Se inserimos só região
        }else if(!regiao.equals("")){
            graphGroup.getChildren().clear();
            if(gcg.getGrafo().E() > 0 && setas == 1){
                for (int k = 1; k <= gcg.getGrafo().getNumCache(); k++) {
                    if(gcg.getGrafo().getCaches().contains(k)){
                        if (gcg.getGrafo().getCaches().get(k).getLocal().getLocalizacao().equals(regiao)) {
                            for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                if (gcg.getGrafo().getCaches().get(adj.to()).getLocal().getLocalizacao().equals(regiao)) {
                                    int w = (int)adj.weight();
                                    int d = adj.distancia();
                                    int t = adj.tempo();
                                    int e = adj.elevacao();
                                    Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                            gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20,w,d,t,e);
                                    a.setFill(Color.DARKRED);
                                    graphGroup.getChildren().add(a);
                                }
                            }
                        }
                    }
                }
            }
            for(int i=1; i<= gcg.getGrafo().getNumCache(); i++){
                if(gcg.getGrafo().getCaches().contains(i)){
                    if(gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                        criar_graph(i);
                }

            }
            //Se inserimos só tipo
        }else if(!tipo.equals("")){
            graphGroup.getChildren().clear();
            if(gcg.getGrafo().E() > 0 && setas == 1){
                for (int k = 1; k <= gcg.getGrafo().getNumCache(); k++) {
                    if(gcg.getGrafo().getCaches().contains(k)){
                        if(tipo.equals("premium")) {
                            if (gcg.getGrafo().getCaches().get(k) instanceof PremiumCache) {
                                for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                    if (gcg.getGrafo().getCaches().get(adj.to()) instanceof PremiumCache ) {
                                        int w = (int)adj.weight();
                                        int d = adj.distancia();
                                        int t = adj.tempo();
                                        int e = adj.elevacao();
                                        Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                                gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20,w,d,t,e);
                                        a.setFill(Color.DARKRED);
                                        graphGroup.getChildren().add(a);
                                    }
                                }
                            }
                        }
                        else if(tipo.equals("basic")) {
                            if (gcg.getGrafo().getCaches().get(k) instanceof BasicCache) {
                                for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                    if (gcg.getGrafo().getCaches().get(adj.to()) instanceof BasicCache) {
                                        int w = (int)adj.weight();
                                        int d = adj.distancia();
                                        int t = adj.tempo();
                                        int e = adj.elevacao();
                                        Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                                gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20,w,d,t,e);
                                        a.setFill(Color.DARKRED);
                                        graphGroup.getChildren().add(a);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int i=1; i<=gcg.getGrafo().getNumCache(); i++){
                if(gcg.getGrafo().getCaches().contains(i)){
                    if(tipo.equals("premium")){
                        if(gcg.getGrafo().getCaches().get(i) instanceof PremiumCache)
                            criar_graph(i);
                    }else if(tipo.equals("basic")){
                        if(gcg.getGrafo().getCaches().get(i) instanceof BasicCache)
                            criar_graph(i);
                    }
                }
            }
            //Se inserimos só dificuldade
        }else if(dif != 0){
            graphGroup.getChildren().clear();
            if(gcg.getGrafo().E() > 0 && setas == 1){
                for (int k = 1; k <= gcg.getGrafo().getNumCache(); k++) {
                    if(gcg.getGrafo().getCaches().contains(k)){
                        if (gcg.getGrafo().getCaches().get(k).getDificuldade() == dif) {
                            for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                if (gcg.getGrafo().getCaches().get(adj.to()).getDificuldade() == dif) {
                                    int w = (int)adj.weight();
                                    int d = adj.distancia();
                                    int t = adj.tempo();
                                    int e = adj.elevacao();
                                    Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                            gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20,w,d,t,e);
                                    a.setFill(Color.DARKRED);
                                    graphGroup.getChildren().add(a);
                                }
                            }
                        }
                    }
                }
            }
            for(int i=1; i<=gcg.getGrafo().getNumCache(); i++){
                if(gcg.getGrafo().getCaches().contains(i)){
                    if(gcg.getGrafo().getCaches().get(i).getDificuldade() == dif)
                        criar_graph(i);
                }
            }
            //Se inserimos só idTb
        }else if(idTb != 0){
            if(go.getTravelBug().contains(idTb)){
                graphGroup.getChildren().clear();
                if(gcg.getGrafo().E() > 0 && setas == 1){
                    for (int k = 1; k <= gcg.getGrafo().getNumCache(); k++) {
                        if(gcg.getGrafo().getCaches().contains(k)){
                            int id = gcg.getGrafo().getCaches().get(k).getIdCache();
                            if (contain(go.getTravelBug(), idTb, id)) {
                                for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                    if (contain(go.getTravelBug(), idTb, adj.to())) {
                                        int w = (int)adj.weight();
                                        int d = adj.distancia();
                                        int t = adj.tempo();
                                        int e = adj.elevacao();
                                        Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                                gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20,w,d,t,e);
                                        a.setFill(Color.PURPLE);
                                        graphGroup.getChildren().add(a);
                                    }
                                }
                            }
                        }
                    }
                }
                for(int i=1; i<=gcg.getGrafo().getNumCache(); i++){
                    if(gcg.getGrafo().getCaches().contains(i)){
                        int id = gcg.getGrafo().getCaches().get(i).getIdCache();
                        if(contain(go.getTravelBug(), idTb, id))
                            criar_graph(i);
                    }
                }
            }
        }

    }

    /**
     * Método que retorna true ou false se um determinado Travel Bug teve na cache com o id recebido
     * @param t
     * @param id - id da cache
     * @param k - tb
     * @return
     */
    public boolean contain(BST_AED2_2021<Integer, TravelBug> t, int id, int k){
        int x = 1, count = 0;
        while(t.get(id).getListaCachesPresente().size()>=x){
            if(t.get(id).getListaCachesPresente().get(x).getIdCache() ==  k)
                count++;
            x++;
        }
        return count != 0;
    }

    /**
     * Handler para saber se existe caminho entre uma cacche de partida e uma de chegada
     * @param actionEvent
     */
    public void handlerExisteCaminho(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "EXISTE CAMINHO\n");
        int cp = Integer.parseInt(cachePartida.getText()); //cache de chegada
        int cc = Integer.parseInt(cacheChegada.getText()); //cache de partida
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        if (BSP.hasPathTo(cc)){ //se cp hasPathTo(cc)
            consolaMapa.setText(consolaMapa.getText() +  "EXISTE CAMINHO\n");
        }else {
            consolaMapa.setText(consolaMapa.getText() + "NÃO EXISTE CAMINHO\n");
        }
    }

    /**
     * Handler para a distancia
     * @param actionEvent - ao clicar no botao
     */
    public void handlerDistancia(ActionEvent actionEvent) {
        tipoPesquisa = "distancia";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: DISTANCIA");
    }

    /**
     * Handler para saber o numero de caches a percorrer consoante a distancia, tempo, peso e elevção
     * @param actionEvent - ao clicar no botao
     */
    public void handlerCachesPerc(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "CACHES A PERCORRER\n");

        int cp = Integer.parseInt(cachePartida.getText());  //cache de partida
        int cc = Integer.parseInt(cacheChegada.getText());  //cache de chegada

        switch (tipoPesquisa){
            case "distancia":
                DSP_AED2 DSPD = new DSP_AED2(gcg.getGrafo(), cp, 1);// saca o caminho com menor custo(Dijkstra)
                if(DSPD.hasPathTo(cc)){
                    String s = formatarString(DSPD.pathTo(cc), 3);
                    System.out.println(s);
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "CAMINHO A PERCORRER: \n" + s + "\n");
                }
                break;
            case "tempo":
                DSP_AED2 DSPT = new DSP_AED2(gcg.getGrafo(), cp, 2);// saca o caminho com menor custo(Dijkstra)
                if(DSPT.hasPathTo(cc)){
                    System.out.println(DSPT.pathTo(cc));
                    String s = formatarString(DSPT.pathTo(cc), 5);
                    System.out.println(s);
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "CAMINHO A PERCORRER: \n" + s + "\n");
                }
                break;
            case "peso":
                // fazer de outra maneira porque pode ter valores negativos
                break;
            case "elevacao":
                BSP_AED2 BFS = new BSP_AED2(gcg.getGrafo(), cp, 1);
                if(BFS.hasPathTo(cc)){
                    System.out.println(BFS.pathTo(cc).toString());
                    //String s = formatarString(BFS.pathTo(cc).toString(),7);
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "CAMINHO A PERCORRER: \n" + BFS.pathTo(cc) + "\n");
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "Elevacao: \n" + BFS.distTo(cc) + "\n");
                }
                break;
        }

    }

    /**
     * Funcao para formatar uma String
     * @param s - String
     * @param x
     * @return
     */
    public static String formatarString(String s, int x){
        String[] parts = s.split("\n");
        StringBuilder f = new StringBuilder();
        int p1 = 0;
        int valorFinal = 0;
        System.out.println("\n\n");
        for (int j = 1; j<=Integer.parseInt(parts[0]); j++){
            if(j==1){
                String[] parts2 = parts[j].split(" ");
                String p = parts2[x];
                p = p.substring(0, p.length()-1);
                p1 = Integer.parseInt(p);
                valorFinal+=p1;
                f.append(parts2[0]).append(", ");
            }else{
                String[] parts2 = parts[j].split(" ");
                String p = parts2[x+1];
                p = p.substring(0, p.length()-1);
                p1 = Integer.parseInt(p);
                valorFinal+=p1;
                f.append(parts2[1]).append(", ");
            }
        }
        if(x == 1){
            f = new StringBuilder(f.substring(0, f.length() - 2));
            f.append("\nPeso: ").append(valorFinal);
        }else if(x == 3){
            f = new StringBuilder(f.substring(0, f.length() - 2));
            f.append("\nDistancia: ").append(valorFinal);
        }else if(x == 5){
            f = new StringBuilder(f.substring(0, f.length() - 2));
            f.append("\nTempo: ").append(valorFinal);
        }else if(x == 7){
            f = new StringBuilder(f.substring(0, f.length() - 2));
            f.append("\nElevacao: ").append(valorFinal);
        }
        return f.toString();
    }

    /**
     * Handler para o peso
     * @param actionEvent - ao clicar no botao
     */
    public void handlerPeso(ActionEvent actionEvent) {
        tipoPesquisa = "peso";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: PESO");
    }

    /**
     * Handler para o tempo
     * @param actionEvent - ao clicar no botao
     */
    public void handlerTempo(ActionEvent actionEvent) {
        tipoPesquisa = "tempo";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: TEMPO");
    }

    /**
     * Handler para a elevacao
     * @param actionEvent - ao clicar no botao
     */
    public void handlerElevacao(ActionEvent actionEvent) {
        tipoPesquisa = "elevacao";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: ELEVACAO");

    }

    /**
     * Handler para limpar a consola
     * @param actionEvent - ao clicar no botao
     */
    public void handlerApagarConsola(ActionEvent actionEvent) {
        consolaMapa.setText("");
    }

    /**
     * handler para calcular o caminho que o caixeiro viajante vai procorrer em X tempo
     * @param actionEvent
     */
    public void handlerCalcular(ActionEvent actionEvent) {
        int tempo = Integer.parseInt(tempoL.getText()); // tempo maximo
        int currPos = 0;
        int linha = Integer.parseInt(cacheP.getText()); // cache em que ele vai comecar
        GFG gfg = new GFG();
        int[][] matrix = gcg.getGrafo().graphToMatrix();
        // trocar a linha 0 , pela cache que ele vai comecar
        matrix = gfg.exchangeAnyTwoRows(matrix, 1, linha + 1);
        boolean[] v = new boolean[gcg.getGrafo().V()-1];
        int ans = Integer.MAX_VALUE;
        int count = 0, pos = 0;
        v[currPos] = true;
        // string com as caches que ele via precorrer
        String cachesPerc = gfg.retStrin(matrix, v, currPos, gcg.getGrafo().V()-1, 1, 0, ans, tempo, linha);
        String []parts = cachesPerc.split("\n");
        for (int i = 0; i < parts.length; i++) { // ta a partir a string e a organiza la
            String []parts2 = parts[i].split(" ");
            if(parts2.length>count) {
                count = parts2.length;
                pos = i;
            }
        }
        parts[pos] = parts[pos].substring(2,parts[pos].length());
        parts[pos] = parts[pos] + linha;
        String []parts3 = parts[pos].split(" ");
        int i = (parts3.length-1);
        textCaixeiro.setText(textCaixeiro.getText() + "\n" + "O caixeiro viajante para percorrer o maximo de caixas num\ntempo limite de: " + tempo + " min, tera de percorrer:\n");
        for (; i>=0; i--) {
            if(i!=0)
                textCaixeiro.setText(textCaixeiro.getText() + parts3[i] + "->");
            else
                textCaixeiro.setText(textCaixeiro.getText() + parts3[i]);
        }
    }

    /**
     * handler para printar todos os caminhos, numa consola da aplicacao
     * @param actionEvent
     */
    public void handlerVerCaminhos(ActionEvent actionEvent) {
        consolaCaminhos.setText("");
        StringBuilder caminhos = new StringBuilder();
        for (int i = 1; i < gcg.getGrafo().V(); i++) {
            for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(i)){ // percorre todos os caminhos e printa os
                caminhos.append("De: ").append(i).append(", para: ").append(adj.to());
                caminhos.append("\nDistancia: ").append(adj.distancia());
                caminhos.append(", Tempo: ").append(adj.tempo());
                caminhos.append(", Elevacao: ").append(adj.elevacao()).append("\n");
            }
        }
        consolaCaminhos.setText(caminhos.toString());
    }

    /**
     * handler para por ou tirar as setas(caminhos) do mapa
     * @param actionEvent
     */
    public void handlerSetasMapa(ActionEvent actionEvent) {
        if(setas == 0)
            setas = 1;
        else
            setas = 0;
        graphGroup.getChildren().clear();
        if(setas == 1){
            if(gcg.getGrafo().E() > 0 && setas == 1) {
                int w = 1, z = 1;
                while(z<gcg.getGrafo().getNumCache()){
                    if(gcg.getGrafo().getCaches().contains(w)){
                        criar_direct_edge(w);
                        z++;
                    }
                    w++;
                }
            }
            int w = 1, z = 1;
            while(z<gcg.getGrafo().getNumCache()){
                if(gcg.getGrafo().getCaches().contains(w)){
                    criar_graph(w);
                    z++;
                }
                w++;
            }
        }if(setas == 0){
            int w = 1, z = 1;
            while(z<gcg.getGrafo().getNumCache()){
                if(gcg.getGrafo().getCaches().contains(w)){
                    criar_graph(w);
                    z++;
                }
                w++;
            }
        }

    }

    /**
     * handler para adicionar caminhos entre duas caches
     * @param actionEvent
     */
    public void handlerAddCaminho(ActionEvent actionEvent) {
        int idP = Integer.parseInt(cachePartidaAC.getText());
        int idC = Integer.parseInt(cacheChegadaAC.getText());
        int dist = Integer.parseInt(distanciaAC.getText());
        int temp = Integer.parseInt(tempoAC.getText());
        int elevacao = Integer.parseInt(elevacaoAC.getText());
        if(gcg.getGrafo().getCaches().contains(idP) && gcg.getGrafo().getCaches().contains(idC)){
            DirectedEdge_AED2 d = new DirectedEdge_AED2(idP, idC, 0, temp, dist, elevacao);
            consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionado um caminho: Cache inicial: " + idP + ", Cache Final: " + idC +
                    "\nDistancia: " + dist + " metros, Tempo: " + temp + " minutos, Elevacao: " + elevacao);
            gcg.getGrafo().addEdge(d);
            atualizarGraph();
        }
    }

    /**
     * funcao para atualizar o grafo que esta no mapa, vai criar um grafo novo com todas as caches que estao na gestaoCaches
     */
    public void atualizarGraph(){
        if(gcg.getGrafo().E() > 0 && setas == 1) {
            int w = 1, z = 1;
            while(z<gcg.getGrafo().getNumCache()){
                if(gcg.getGrafo().getCaches().contains(w)){
                    criar_direct_edge(w);
                    z++;
                }
                w++;
            }
        }
        int w = 1, z = 1;
        while(z<gcg.getGrafo().getNumCache()){
            if(gcg.getGrafo().getCaches().contains(w)){
                criar_graph(w);
                z++;
            }
            w++;
        }
    }

    /**
     * metodo para atualizar as informacoes que estao na tabelas de caches, apartir da gestaCaches
     */
    public void atualizarCaches(){
        int x = 1, k = 1;
        if(gc.getCaches().size()>0){
            gcg.setGrafo(new GeoDigraph(gc.getNumCache()));
            while(k<=gc.getCaches().size()){
                if(gc.getCaches().contains(x)){
                    gcg.getGrafo().adicionaCache(gc.getCaches().get(x));
                    k++;
                }
                x++;
            }
        }
        gcg.lerCaminhos();
        creatGraphGroup(ga, gcg, go);
        gcg.getGrafo().getCaches().get(4);
    }

    /**
     * handler para adicionar uma caches ao grafo
     * @param actionEvent
     * @throws AventureiroNaoHabilitado se o aventureiro na for premium nao pode criar cache
     */
    public void handlerAddCache(ActionEvent actionEvent) throws AventureiroNaoHabilitado {
        //toda a informacao da cache nova
        int idA = Integer.parseInt(addIdAventC.getText());
        int idO = 0;
        if(!addIdObjetoC.getText().isEmpty())
            idO = Integer.parseInt(addIdObjetoC.getText());
        int dif = Integer.parseInt(addDificC.getText());
        String l = addLocalC.getText();
        String[] parts = l.split(";");
        String local = parts[2];
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);

        //verificacao se a cache e premium ou nao
        if(comboBoxTipoCache.getValue().compareTo("Premium") == 0) {
            PremiumCache c = null;
            if(idO != 0)
                c = new PremiumCache(dif, ga.getAventureiros().get(idA), go.getTravelBug().get(idO),x, y, local);
            else{
                c = c = new PremiumCache(dif, ga.getAventureiros().get(idA),x, y, local);
            }
            gc.adicionaCache(c);
            consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionada uma cache: Id: " + c.getId() + ", Local: " + c.getLocal().getLocalizacao());
        }else if(comboBoxTipoCache.getValue().compareTo("Basic") == 0){
            BasicCache c = null;
            if(idO != 0) {
                c = new BasicCache(dif, ga.getAventureiros().get(idA), go.getObjetos().get(idO), x, y, local);
            }else{
                c = new BasicCache(dif, ga.getAventureiros().get(idA), x, y, local);
            }
            gc.adicionaCache(c);
            consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionada uma cache: Id: " + c.getId() + ", Local: " + c.getLocal().getLocalizacao());
        }
        atualizarCaches();
    }

    /**
     * handler para editar informaçao de uma cache
     * @param actionEvent
     */
    public void handlerEditCache(ActionEvent actionEvent) {
        int id = Integer.parseInt(editIdCacheC.getText());
        int dif = Integer.parseInt(editDificC.getText());
        String local = editLocalC.getText();
        if(gcg.getGrafo().getCaches().contains(id)){
            gcg.getGrafo().getCaches().get(id).getLocal().setLocalizacao(local);
            gcg.getGrafo().getCaches().get(id).setDificuldade(dif);
            gc.getCaches().get(id).getLocal().setLocalizacao(local);
            gc.getCaches().get(id).setDificuldade(dif);
            atualizarCaches();
        }
    }

    /**
     * handler para ver todas as caches em que um aventureiro ja esteve
     * @param actionEvent
     */
    public void handlerPesquisaAventPA(ActionEvent actionEvent) {
        int idA = Integer.parseInt(idAventPA.getText());
        if(idA != 0){
            if(ga.getAventureiros().contains(idA)){
                graphGroup.getChildren().clear();
                if(gcg.getGrafo().E() > 0 && setas == 1){
                    for (int k = 1; k <= gcg.getGrafo().getNumCache(); k++) { // ver todas as caches que existem no grafo
                        if(gcg.getGrafo().getCaches().contains(k)){
                            int id = gcg.getGrafo().getCaches().get(k).getIdCache();
                            if (contains(ga.getAventureiros(), idA, id)) { // verificar se esta(com id = id) cache foi visitada pelo aventureiro
                                for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                                    if (contains(ga.getAventureiros(), idA, adj.to())) { // verifica se este caminho, a cache de saida e entrada, ja foram visitadas pelo aventureiro, senoa nao a desenha
                                        int w = (int)adj.weight();
                                        int d = adj.distancia();
                                        int t = adj.tempo();
                                        int e = adj.elevacao();
                                        Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                                gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20,w,d,t,e);
                                        a.setFill(Color.PURPLE);
                                        graphGroup.getChildren().add(a);
                                    }
                                }
                            }
                        }
                    }
                }
                for(int i=1; i<=gcg.getGrafo().getNumCache(); i++){
                    if(gcg.getGrafo().getCaches().contains(i)){
                        int id = gcg.getGrafo().getCaches().get(i).getIdCache();
                        if (contains(ga.getAventureiros(), idA, id))
                            criar_graph(i);
                    }
                }
            }
        }
    }

    /**
     * funcao para verificar se a cache com id = idC, ja foi visitada pelo aventureiro idA
     * @param a RedBlac de Aventureiros
     * @param idA id do aventureiro
     * @param idC id da cache
     * @return true se o aventureiro ja tiver visitado esta cache
     */
    public boolean contains(RedBlack_AED2<Integer, Aventureiro> a, int idA, int idC){
        int x = 0, count = 0;
        // percorre todas as caches do aventureiro ja esteve, e ve se alguma tem o id da cache em questao, se sim retorna true
        while(a.get(idA).getListCacheVisit().size()>x){
            if(a.get(idA).getListCacheVisit().get(x).getIdCache() == idC)
                count++;
            x++;
        }
        return count != 0;
    }

    /**
     * handler de quando um aventureiro encontar uma cache, e se for um tb, ela retorna as caches que ele tem de levar o travelbug, interpetando as missoes dele
     * @param actionEvent
     */
    public void handlerEncontrouCacheJ(ActionEvent actionEvent) {
        int id = Integer.parseInt(idAventJ.getText());
        int idC = Integer.parseInt(cacheEncJ.getText());
        ga.getAventureiros().get(id).encontrouCache(gc.getCaches().get(idC), new Date(), go);

        consolaMapa.setText("O aventureiro " + id + " econtrou uma cache\ncom um travel bug: \n");
        consolaMapa.setText(consolaMapa.getText() + "\n" +ga.getAventureiros().get(id).getListTravelBug().get(0).getMissao());
        ArrayList<Cache> cachesRet = ga.getAventureiros().get(id).getListTravelBug().get(0).interpetarMissao(gc, ga); // caches que podemos levar o tb, por causa da sua missao
        if (cachesRet.size() > 0){
            if (cachesRet.size() == 1){
                for (Cache c : cachesRet){
                    consolaMapa.setText(consolaMapa.getText() + "\n" + "Pode levar para a cache com o ID: " + c.getIdCache());
                    ids.add(c.getIdCache());
                    BFS_AED2 BFS = new BFS_AED2(gcg.getGrafo(), idC);
                    DSP_AED2 DSP = new DSP_AED2(gcg.getGrafo(), idC, 1);
                    consolaMapa.setText(consolaMapa.getText()+ "\nCache id: " + c.getIdCache());
                    consolaMapa.setText(consolaMapa.getText()+ "\nBFS Caminho mais curto: " + BFS.pathTo(c.getIdCache()));
                    String s = formatarString(DSP.pathTo(c.getIdCache()),3);
                    consolaMapa.setText(consolaMapa.getText()+ "\nDistancia: " + DSP.distTo(c.getIdCache()));
                }
            }
            else{
                consolaMapa.setText(consolaMapa.getText() + "\n" + "Pode levar para as caches com o ID: ");
                ids.clear();
                for (Cache c : cachesRet){
                    ids.add(c.getIdCache());
                    BFS_AED2 BFS = new BFS_AED2(gcg.getGrafo(), idC);
                    DSP_AED2 DSP = new DSP_AED2(gcg.getGrafo(), idC, 1);
                    consolaMapa.setText(consolaMapa.getText()+ "\nCache id: " + c.getIdCache());
                    consolaMapa.setText(consolaMapa.getText()+ "\nBFS Caminho mais curto: " + BFS.pathTo(c.getIdCache()));
                    String s = formatarString(DSP.pathTo(c.getIdCache()),3);
                    consolaMapa.setText(consolaMapa.getText()+ "\nDistancia: " + DSP.distTo(c.getIdCache()));
                }
            }
        }
        atualizarCaches();
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "O Aventureiro: " + id + " encontrou a cache: " + idC);
    }

    /**
     * hanler para o aventureiro depositar o tb ou objeto
     * @param actionEvent
     * @throws MissaoNaoCompletadaComExitoException verifica se for um tb se a missa foi completada
     */
    public void handlerDepositouCacheJ(ActionEvent actionEvent) throws MissaoNaoCompletadaComExitoException {
        go.getTravelBug().get(12).getListaCachesPresente().printInOrder(go.getTravelBug().get(12).getListaCachesPresente().getRoot());
        int idC = Integer.parseInt(cacheDepJ.getText());
        int id = Integer.parseInt(idAventJ.getText());
        // mudar as informacoes do tb
        ga.getAventureiros().get(id).encontrouCache((PremiumCache) gc.getCaches().get(idC), ga.getAventureiros().get(id).getListTravelBug().get(0), new Date(20, 03, 2021));
        go.getTravelBug().get(ga.getAventureiros().get(id).getListTravelBug().get(0).getIdObjeto()).getListaCachesPresente().put(go.getTravelBug().get(ga.getAventureiros().get(id).getListTravelBug().get(0).getIdObjeto()).getNumCachesPres(), (PremiumCache) gc.getCaches().get(idC));
        go.getTravelBug().get(ga.getAventureiros().get(id).getListTravelBug().get(0).getIdObjeto()).setNumCachesPres(go.getTravelBug().get(ga.getAventureiros().get(id).getListTravelBug().get(0).getIdObjeto()).getNumCachesPres()+1);
        go.getTravelBug().get(ga.getAventureiros().get(id).getListTravelBug().get(0).getIdObjeto()).setViajar(false);
        go.getTravelBug().get(ga.getAventureiros().get(id).getListTravelBug().get(0).getIdObjeto()).getListaAventureiros().put(go.getTravelBug().get(ga.getAventureiros().get(id).getListTravelBug().get(0).getIdObjeto()).getNumAventureiros()-1, ga.getAventureiros().get(id));
        atualizarCaches();
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "O Aventureiro: " + id + " encontrou a cache: " + idC);
    }
}