import Search.BST_AED2_2021;
import Search.RedBlack_AED2;
import SearchProj.*;
import edu.princeton.cs.algs4.AcyclicLP;
import edu.princeton.cs.algs4.AcyclicSP;
import figGeo.Arrow;
import figGeo.Point;
import figGeo.Rectangle;
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
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import projeto_LP2_AED2.*;
import projeto_LP2_AED2.Date;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    /**
     * Variáveis identificadoras de cada tabela e coluna
     */
    //AVENTUREIROS
    public TableView<Aventureiro> aventTables;
    public TableColumn<Aventureiro, String> nomeCol;
    public TableColumn<Aventureiro, String> idCol;
    public TableColumn<Aventureiro, String> localCol;
    public TableColumn<Aventureiro, String> objetoCol;
    public TableColumn<Aventureiro, String> cacheCol;
    public TableColumn<Aventureiro, String> cacheVisCol;
    public TableColumn<Aventureiro, String> cacheEscCol;
    public TableColumn<Aventureiro, String> tipoCol;
    private ArrayList<Aventureiro> AventArrayList;

    //CACHES
    public TableView<Cache> cacheTables;
    public TableColumn<Cache, String> idCCol;
    public TableColumn<Cache, String> dificCol;
    public TableColumn<Cache, String> tipoCCol;
    public TableColumn<Cache, String> criadorCol;
    public TableColumn<Cache, String> objetoCCol;
    public TableColumn<Cache, String> localCCol;
    public TableColumn<Cache, String> numACol;
    public TableColumn<Cache, String> ultimoACol;
    private ArrayList<Cache> CacheArrayList;

    //OBJETOS
    public TableView<Objeto> objetoTables;
    public TableColumn<Objeto, String> idOCol;
    public TableColumn<Objeto, String> viajarOCol;
    public TableColumn<Objeto, String> nomeOCol;
    public TableColumn<Objeto, String> cacheOCol;
    public TableColumn<Objeto, String> aventOCol;
    public TableColumn<Objeto, String> localOCol;
    private ArrayList<Objeto> ObjetoArrayList;

    //TRAVELBUG
    public TableView<TravelBug> tbTables;
    public TableColumn<TravelBug, String> idTCol;
    public TableColumn<TravelBug, String> nomeTCol;
    public TableColumn<TravelBug, String> viajarTCol;
    public TableColumn<TravelBug, String> missaoCol;
    public TableColumn<TravelBug, String> ultimaCCol;
    public TableColumn<TravelBug, String> ultimoATCol;
    public TableColumn<TravelBug, String> numCCol;
    public TableColumn<TravelBug, String> numATCol;
    private ArrayList<TravelBug> TbArrayList;

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

    //AVENTUREIROS
    //adicionar
    public ComboBox<String> comboBoxAv;
    public TextField nomeAvent;
    public TextField localAvent;
    public TextField PassAvent;
    //remover
    public TextField remIdAvent;
    //editar
    public TextField idEdit;
    public TextField nomeEdit;
    public TextField localEdit;
    //caxeiro-viajante
    public TextField tempoL;
    public TextField cacheP;
    public TextArea textCaixeiro;

    //OBJETOS
    //add
    public TextField addNomeO;
    //remove
    public TextField remIdO;
    //edit
    public TextField editIdO;
    public TextField editNomeO;

    //TRAVELBUGS
    //add
    public TextField addNomeTb;
    //remove
    public TextField remIdTb;
    //edit
    public TextField editIdTb;
    public TextField editNomeTb;

    //CACHE
    //remocao
    public TextField remIdCacheC;
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

    //limites mapa
    public final int xleftLimit = 150;
    public final int xRightLimit = 550;
    public final int yUpperLimit = 50;
    public final int yLowerLimit = 450;


    /**
     * funcao para inicilizar as tabelas de informacoa e tambem toda a informacao a cerca do grafo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //AVENTUREIROS
        AventArrayList = new ArrayList<>();

        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());

        nomeCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        nomeCol.setCellFactory(TextFieldTableCell.forTableColumn());

        localCol.setCellValueFactory(new PropertyValueFactory<>("Loc"));
        localCol.setCellFactory(TextFieldTableCell.forTableColumn());

        objetoCol.setCellValueFactory(new PropertyValueFactory<>("Objeto"));
        objetoCol.setCellFactory(TextFieldTableCell.forTableColumn());

        cacheCol.setCellValueFactory(new PropertyValueFactory<>("Cache"));
        cacheCol.setCellFactory(TextFieldTableCell.forTableColumn());

        cacheVisCol.setCellValueFactory(new PropertyValueFactory<>("CacheVis"));
        cacheVisCol.setCellFactory(TextFieldTableCell.forTableColumn());

        cacheEscCol.setCellValueFactory(new PropertyValueFactory<>("CacheEsc"));
        cacheEscCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tipoCol.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tipoCol.setCellFactory(TextFieldTableCell.forTableColumn());

        //CACHES
        CacheArrayList = new ArrayList<>();

        idCCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        idCCol.setCellFactory(TextFieldTableCell.forTableColumn());

        dificCol.setCellValueFactory(new PropertyValueFactory<>("Dific"));
        dificCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tipoCCol.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tipoCCol.setCellFactory(TextFieldTableCell.forTableColumn());

        criadorCol.setCellValueFactory(new PropertyValueFactory<>("Criador"));
        criadorCol.setCellFactory(TextFieldTableCell.forTableColumn());

        objetoCCol.setCellValueFactory(new PropertyValueFactory<>("Obj"));
        objetoCCol.setCellFactory(TextFieldTableCell.forTableColumn());

        localCCol.setCellValueFactory(new PropertyValueFactory<>("Loc"));
        localCCol.setCellFactory(TextFieldTableCell.forTableColumn());

        numACol.setCellValueFactory(new PropertyValueFactory<>("NumAv"));
        numACol.setCellFactory(TextFieldTableCell.forTableColumn());

        ultimoACol.setCellValueFactory(new PropertyValueFactory<>("UltimoAvent"));
        ultimoACol.setCellFactory(TextFieldTableCell.forTableColumn());

        //OBJETOS
        ObjetoArrayList = new ArrayList<>();

        idOCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        idOCol.setCellFactory(TextFieldTableCell.forTableColumn());

        viajarOCol.setCellValueFactory(new PropertyValueFactory<>("Viajar"));
        viajarOCol.setCellFactory(TextFieldTableCell.forTableColumn());

        nomeOCol.setCellValueFactory(new PropertyValueFactory<>("NomeO"));
        nomeOCol.setCellFactory(TextFieldTableCell.forTableColumn());

        cacheOCol.setCellValueFactory(new PropertyValueFactory<>("CacheO"));
        cacheOCol.setCellFactory(TextFieldTableCell.forTableColumn());

        aventOCol.setCellValueFactory(new PropertyValueFactory<>("AventO"));
        aventOCol.setCellFactory(TextFieldTableCell.forTableColumn());

        localOCol.setCellValueFactory(new PropertyValueFactory<>("LocalO"));
        localOCol.setCellFactory(TextFieldTableCell.forTableColumn());

        //TRAVELBUG
        TbArrayList = new ArrayList<>();

        idTCol.setCellValueFactory(new PropertyValueFactory<>("IdT"));
        idTCol.setCellFactory(TextFieldTableCell.forTableColumn());

        nomeTCol.setCellValueFactory(new PropertyValueFactory<>("NomeT"));
        nomeTCol.setCellFactory(TextFieldTableCell.forTableColumn());

        missaoCol.setCellValueFactory(new PropertyValueFactory<>("MissaoT"));
        missaoCol.setCellFactory(TextFieldTableCell.forTableColumn());

        viajarTCol.setCellValueFactory(new PropertyValueFactory<>("ViajarT"));
        viajarTCol.setCellFactory(TextFieldTableCell.forTableColumn());

        ultimaCCol.setCellValueFactory(new PropertyValueFactory<>("UltimaCT"));
        ultimaCCol.setCellFactory(TextFieldTableCell.forTableColumn());

        ultimoATCol.setCellValueFactory(new PropertyValueFactory<>("UltimaAT"));
        ultimoATCol.setCellFactory(TextFieldTableCell.forTableColumn());

        numCCol.setCellValueFactory(new PropertyValueFactory<>("NumCT"));
        numCCol.setCellFactory(TextFieldTableCell.forTableColumn());

        numATCol.setCellValueFactory(new PropertyValueFactory<>("NumAT"));
        numATCol.setCellFactory(TextFieldTableCell.forTableColumn());

        String p = "Premium";
        String b = "Basic";
        String a = "Admin";

        comboBoxAv.getItems().addAll(p,b,a);
        comboBoxTipoCache.getItems().addAll(p,b);

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
     * @param ga gestao acesso aventureiro
     * @param gcg gestao acesso do grafo e das caches
     * @param go gestao acesso dos objetos
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
     * @throws AventureiroNaoHabilitado se o aventurerio nao poder criar cache, so esta aqui po estar a carregar os ficheiros
     * @throws AventureiroNaoExisteException se o aventureiro nao existir
     * @throws CacheNaoExisteException se a cache nao existir
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
        /*
        */
        atualizarAvent();
        atualizarCaches();
        atualizarObjeto();

        //teste de directedEdge
        gcg.lerCaminhos();

        creatGraphGroup(ga, gcg, go);

    }

    /**
     * Handler para Pesquisar determinada cache consoate x campos inseridos
     * @param actionEvent - Ao clicarmos no botao
     */
    public void handlerPesquisaCache(ActionEvent actionEvent){
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
     * @param t bst de travel bug onde vamos procurar
     * @param id - id da cache
     * @param k - tb
     * @return true se ele estiver na bst
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
     * @param actionEvent sempre que ocorrer um evento
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

        LogsDiario diario = new LogsDiario();

        int cp = Integer.parseInt(cachePartida.getText());  //cache de partida
        int cc = Integer.parseInt(cacheChegada.getText());  //cache de chegada

        switch (tipoPesquisa){
            case "distancia":
                DSP_AED2 DSPD = new DSP_AED2(gcg.getGrafo(), cp, 1);// saca o caminho com menor custo(Dijkstra)
                if(DSPD.hasPathTo(cc)){
                    String s = formatarString(DSPD.pathTo(cc), 3);
                    String Pesq = "Menor caminho a percorrer em termos de distancia entre a cache " + cp + " e a cache " + cc + ":";
                    Pesq = Pesq + "\n" + s;
                    diario.adicionaLog(Pesq, new Date(), "data/Pesquisas.txt");
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "CAMINHO A PERCORRER: \n" + s + "\n\n");
                }

                break;
            case "tempo":
                DSP_AED2 DSPT = new DSP_AED2(gcg.getGrafo(), cp, 2);// saca o caminho com menor custo(Dijkstra)
                if(DSPT.hasPathTo(cc)){
                    String s = formatarString(DSPT.pathTo(cc), 5);
                    String Pesq = "Menor caminho a percorrer em termos de tempo entre a cache " + cp + " e a cache " + cc + ":";
                    Pesq = Pesq + "\n" + s;
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "CAMINHO A PERCORRER: \n" + s + "\n\n");
                    //s = s.substring(0,s.length()-2);
                    diario.adicionaLog(Pesq, new Date(), "data/Pesquisas.txt");
                }
                break;
            case "peso":
                // fazer de outra maneira porque pode ter valores negativos
                break;
            case "elevacao":
                DSP_AED2 DSPE = new DSP_AED2(gcg.getGrafo(), cp, 1);// saca o caminho com menor custo(Dijkstra)
                BSP_AED2 BFS = new BSP_AED2(gcg.getGrafo(), cp, 1);
                if(BFS.hasPathTo(cc)){
                    String caminho = formatarString(DSPE.pathTo(cc), 3);
                    String s = "A variacao da elevacao e o caminho a percorrer entre a cache: " + cp + " e a cache " + cc + ":\n" + caminho + "Elevacao: " + BFS.elevTo(cc);
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "CAMINHO A PERCORRER: \n" + caminho );
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "Variacao da elevacao: \n" + BFS.elevTo(cc) + "\n\n");
                    diario.adicionaLog(s,new Date(), "data/Pesquisas.txt");
                }
                break;
            default:
                System.out.println("Escolha um tipo de pesquisa!");
                break;
        }
    }

    /**
     * Funcao para formatar uma string retornada do algoritno Bellsman-Ford
     * @param s string que vamos formatar
     * @return retorna uma string como o caminho mais curto em relacao a variacao da elevacao
     */
    public static String formatarString(String s){
        String[] parts = s.split("\n");
        StringBuilder finalString = new StringBuilder();
        int elevacao = 0;
        for (int i = 0; i < parts.length-1; i++) {
            String[] parts2 = parts[i].split(" ");
            if(i == 0) {
                finalString.append(parts2[0]).append(" ");
                elevacao = elevacao + Integer.parseInt(parts2[7]);
            }else{
                finalString.append(parts2[1]).append(" ");
                elevacao = elevacao + Integer.parseInt(parts2[8]);
            }
        }
        finalString.append("\n").append("Elevaçao: ").append(elevacao);
        return finalString.toString();
    }

    /**
     * Funcao para formatar uma String retornada do algoritmo Dijsktra
     * @param s - String que vamos formatar
     * @param x - se o x for igual a 3 retorna a distancia, se for igual a 5 retorna a tempo
     * @return retorna uma string com o caminho do menor percurso em relacao ao tempo ou a distancia
     */
    public static String formatarString(String s, int x){
        System.out.println(s);
        String[] parts = s.split("\n");
        StringBuilder f = new StringBuilder();
        int p1 = 0;
        int valorFinal = 0;
        for (int j = 1; j<=Integer.parseInt(parts[0]); j++){
            if(j==1){
                String[] parts2 = parts[j].split(" ");
                String p = parts2[x];
                System.out.println(p);
                p = p.substring(0, p.length()-1);
                p1 = Integer.parseInt(p);
                valorFinal+=p1;
                f.append(parts2[0]).append(", ");
            }else{
                String[] parts2 = parts[j].split(" ");
                String p = parts2[x+1];
                System.out.println(p);
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
        }else if(x == 5) {
            f = new StringBuilder(f.substring(0, f.length() - 2));
            f.append("\nTempo: ").append(valorFinal);
        }
        return f.toString();
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
     * handler para adicionar aventureiro
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerAddAvent(ActionEvent actionEvent) {
        int x = 230, y=459;
        //se for selecionado na combobox premium
        if(comboBoxAv.getValue().compareTo("Premium") == 0){
            Premium p = new Premium(nomeAvent.getText(),x,y,localAvent.getText());
            ga.regista(p);
            consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionado um aventureiro: Id: " + p.getId() + ", Nome: " + p.getNome());
        }
        if(comboBoxAv.getValue().compareTo("Basic") == 0){
            Basic b = new Basic(nomeAvent.getText(),x,y,localAvent.getText());
            ga.regista(b);
            consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionado um aventureiro: Id: " + b.getId() + ", Nome: " + b.getNome());
        }

        if(comboBoxAv.getValue().compareTo("Admin") == 0){
            Admin a = new Admin(nomeAvent.getText(),x,y,localAvent.getText(),PassAvent.getText());
            ga.regista(a);
            consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionado um aventureiro: Id: " + a.getId() + ", Nome: " + a.getNome());
        }
        atualizarAvent();
    }

    /**
     * handler para remover um aventureiro, e todas as suas caches e objetos
     * @param actionEvent sempre que ocorrer um evento
     * @throws AventureiroNaoExisteException se o aventureiro nao existir ocorre uma excepcao
     * @throws CacheNaoExisteException se a cache nao existir ocorre uma excepcao
     */
    public void handlerRemoverAvent(ActionEvent actionEvent) throws AventureiroNaoExisteException, CacheNaoExisteException {
        int id = Integer.parseInt(remIdAvent.getText());
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi removido o aventureiro: Id: " + ga.getAventureiros().get(id).getId() + ", Nome: " + ga.getAventureiros().get(id).getNome());
        ga.remove(id);
        int x = 1, k = 1;
        // ao remover um aventureiro, vamos remover tambem as suas caches e objetos
        while (k<=gc.getCaches().size()){
            if(gc.getCaches().get(x) != null){
                if(Integer.parseInt(gc.getCaches().get(x).getCriador()) == id){
                    if(gc.getCaches().get(x).getTravelbug()!=null)
                        go.removeTb(gc.getCaches().get(x).getTravelbug().getIdObjeto());
                    if(gc.getCaches().get(x).getObjeto()!=null)
                        go.removeTb(gc.getCaches().get(x).getObjeto().getIdObjeto());
                    remCache(gc.getCaches().get(x).getIdCache());
                    atualizarObjeto();
                }
                k++;
            }
            x++;
        }
        atualizarAvent();
    }

    /**
     * handler para editar um aventureiro
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerEditAv(ActionEvent actionEvent) {
        int id = Integer.parseInt(idEdit.getText());
        ga.editarTab(id,nomeEdit.getText(),localEdit.getText());
        atualizarAvent();
    }

    /**
     * handler para calcular o caminho que o caixeiro viajante vai procorrer em X tempo
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerCalcular(ActionEvent actionEvent) {
        int tempo = Integer.parseInt(tempoL.getText()); // tempo maximo
        int currPos = 0;
        int linha = Integer.parseInt(cacheP.getText()); // cache em que ele vai comecar
        CaixeiroViajante gfg = new CaixeiroViajante();
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
        StringBuilder message = new StringBuilder();
        message.append("O caixeiro viajante para percorrer o maximo de caixas num\ntempo limite de: ").append(tempo).append(" min, tera de percorrer:\n");
        textCaixeiro.setText(textCaixeiro.getText() + "\n" + "O caixeiro viajante para percorrer o maximo de caixas num\ntempo limite de: " + tempo + " min, tera de percorrer:\n");
        for (; i>=0; i--) {
            if(i!=0) {
                message.append(parts3[i]).append("->");
                textCaixeiro.setText(textCaixeiro.getText() + parts3[i] + "->");
            }else{
                message.append(parts3[i]);
                textCaixeiro.setText(textCaixeiro.getText() + parts3[i]);
            }
        }
        LogsDiario diario = new LogsDiario();

        diario.adicionaLog(message.toString(), new Date(), "data/Pesquisas.txt");
    }

    /**
     * handler para adicionar um objeto
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerAddObjeto(ActionEvent actionEvent) {
        String nome = addNomeO.getText();
        Objeto o = new Objeto(nome);
        go.regista(o);
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionado um objeto: Id: " + o.getId() + ", Nome: " + o.getNome());
        atualizarObjeto();
    }

    /**
     * handler para remover um objeto
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerRemObjeto(ActionEvent actionEvent) {
        int id = Integer.parseInt(remIdO.getText());
        if(go.getObjetos().get(id).isViajar()){
            int idA = go.getObjetos().get(id).getAventureiro().getIdAventureiro();
            int j = 0, x = 0;
            while (j<=ga.getAventureiros().get(idA).getListObjetos().size()){
                if(ga.getAventureiros().get(idA).getListObjetos().get(x)!=null){
                    if(ga.getAventureiros().get(idA).getListObjetos().get(x).getIdObjeto() == id)
                        ga.getAventureiros().get(idA).getListObjetos().delete(x);
                    j++;
                }
                x++;
            }
        }
        else{
            int idC = go.getObjetos().get(id).getCache().getIdCache();
            gc.getCaches().get(idC).setObjeto(null);
        }
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi removido um objeto: Id: " + go.getObjetos().get(id).getId() + ", Nome: " + go.getObjetos().get(id).getNome());
        go.removeO(id);
        atualizarAvent();
        atualizarObjeto();
        atualizarCaches();
    }

    /**
     * handler para editar um objeto
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerEditObjeto(ActionEvent actionEvent) {
        int id = Integer.parseInt(editIdO.getText());
        String nome = editNomeO.getText();
        go.editarO(id,nome);
        atualizarObjeto();
    }

    /**
     * handler para adiconar um travelBug
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerAddTb(ActionEvent actionEvent) {
        String nome = addNomeTb.getText();
        TravelBug tb = new TravelBug(nome);
        go.regista(tb);
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionado um TravelBug: Id: " + tb.getId() + ", Nome: " + tb.getNome());
        int x = 1;
        int k = 1;
        if(tbTables!=null)
            tbTables.getItems().clear();
        TbArrayList.clear();
        if(go.getTravelBug().size()>0){
            while(k<=go.getTravelBug().size()){
                if(go.getTravelBug().get(x) != null) {
                    TbArrayList.add(go.getTravelBug().get(x));
                    k++;
                }
                x++;
            }
            tbTables.getItems().addAll(TbArrayList);
        }
    }

    /**
     * handler para remover um travelBug
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerRemTb(ActionEvent actionEvent) {
        int id = Integer.parseInt(remIdTb.getText());
        if(go.getTravelBug().get(id).isViajar()){
            int idA = go.getTravelBug().get(id).getAventureiro().getIdAventureiro();
            int j = 0, x = 0;
            while (j<=ga.getAventureiros().get(idA).getListTravelBug().size()){
                if(ga.getAventureiros().get(idA).getListTravelBug().get(x)!=null){
                    if(ga.getAventureiros().get(idA).getListTravelBug().get(x).getIdObjeto() == id)
                        ga.getAventureiros().get(idA).getListTravelBug().delete(x);
                    j++;
                }
                x++;
            }
        }
        else{
            int idC = go.getTravelBug().get(id).getCache().getIdCache();
            gc.getCaches().get(idC).setTravelbug(null);
        }
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi removido um TravelBug: Id: " + go.getTravelBug().get(id).getId() + ", Nome: " + go.getTravelBug().get(id).getNome());
        go.removeTb(id);
        atualizarAvent();
        atualizarCaches();
        atualizarObjeto();
    }

    /**
     * handler para editar TravelBug
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerEditTb(ActionEvent actionEvent) {
        String nome = editNomeTb.getText();
        int id = Integer.parseInt(editIdTb.getText());
        go.editarTb(id,nome);
        atualizarObjeto();
    }

    /**
     * handler para remover cache
     * @param actionEvent sempre que ocorrer um evento
     * @throws CacheNaoExisteException se a cache nao existir ocorre uma exceçao
     */
    public void handlerRemCache(ActionEvent actionEvent) throws CacheNaoExisteException {
        int id = Integer.parseInt(remIdCacheC.getText());
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi removida uma cache: Id: " + gc.getCaches().get(id).getId() + ", Local: " + gc.getCaches().get(id).getLocal().getLocalizacao());
        remCache(id);
    }

    /**
     * funcao qeu remove uma cache
     * @param id da cahce a ser removida
     * @throws CacheNaoExisteException se a cache nao existir
     */
    public void remCache(int id) throws CacheNaoExisteException {
        if(gc.getCaches().get(id).getObjeto() != null){
            int idO = gc.getCaches().get(id).getObjeto().getIdObjeto();
            go.removeO(idO);
        }
        if(gc.getCaches().get(id).getTravelbug() != null){
            int idTb = gc.getCaches().get(id).getTravelbug().getIdObjeto();
            go.removeTb(idTb);
        }
        gc.removeCache(id);
        gcg.removeCache(id);
        graphGroup.getChildren().clear();
        gcg.setGrafo(new GeoDigraph(gc.getNumCache()));
        atualizarCaches();
        atualizarObjeto();
        gcg.lerCaminhos();
        creatGraphGroup(ga, gcg, go);
        gcg.getGrafo().getCaches().get(4);
    }

    /**
     * handler para printar todos os caminhos, numa consola da aplicacao
     * @param actionEvent sempre que ocorrer um evento
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
     * @param actionEvent sempre que ocorrer um evento
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
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerAddCaminho(ActionEvent actionEvent) {
        int idP = Integer.parseInt(cachePartidaAC.getText());
        int idC = Integer.parseInt(cacheChegadaAC.getText());
        int dist = Integer.parseInt(distanciaAC.getText());
        int temp = Integer.parseInt(tempoAC.getText());
        int elevacao = Integer.parseInt(elevacaoAC.getText());
        if(gcg.getGrafo().getCaches().contains(idP) && gcg.getGrafo().getCaches().contains(idC)){
            DirectedEdge_AED2 d = new DirectedEdge_AED2(idP, idC, 1000, temp, dist, elevacao);
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
        if(cacheTables!=null)
            cacheTables.getItems().clear();
        CacheArrayList.clear();
        if(gc.getCaches().size()>0){
            gcg.setGrafo(new GeoDigraph(gc.getNumCache()));
            while(k<=gc.getCaches().size()){
                if(gc.getCaches().contains(x)){
                    gcg.getGrafo().adicionaCache(gc.getCaches().get(x));
                    CacheArrayList.add(gc.getCaches().get(x));
                    k++;
                }
                x++;
            }
            cacheTables.getItems().addAll(CacheArrayList);
        }
        gcg.lerCaminhos();
        creatGraphGroup(ga, gcg, go);
        gcg.getGrafo().getCaches().get(4);
    }

    /**
     * metodo para atualizar as informacoes que estao na tabelas dos aventureiros, apartir da gestaAventureiros
     */
    public void atualizarAvent(){
        int av = 1, k= 1;
        if(aventTables!=null)
            aventTables.getItems().clear();
        AventArrayList.clear();
        if(ga.getAventureiros().size()>0){
            while(k<=ga.getAventureiros().size()){
                if(ga.getAventureiros().get(av) != null){
                    AventArrayList.add(ga.getAventureiros().get(av));
                    k++;
                }
                av++;
            }
            aventTables.getItems().addAll(AventArrayList);
        }
    }

    /**
     * metodo para atualizar as informacoes que estao na tabelas dos objetos, apartir da gestaObjetos
     */
    public void atualizarObjeto(){
        //OBJETOS
        int x = 1;
        int k = 1;
        if(objetoTables!=null)
            objetoTables.getItems().clear();
        ObjetoArrayList.clear();
        if(go.getObjetos().size()>0){
            while(k<=go.getObjetos().size()){
                if(go.getObjetos().get(x) != null){
                    ObjetoArrayList.add(go.getObjetos().get(x));
                    k++;
                }
                x++;
            }
            objetoTables.getItems().addAll(ObjetoArrayList);
        }

        //TRAVELBUG
        x = 1;
        k = 1;
        if(tbTables!=null)
            tbTables.getItems().clear();
        TbArrayList.clear();
        if(go.getTravelBug().size()>0){
            while(k<=go.getTravelBug().size()){
                if(go.getTravelBug().get(x) != null) {
                    TbArrayList.add(go.getTravelBug().get(x));
                    k++;
                }
                x++;
            }
            tbTables.getItems().addAll(TbArrayList);
        }
    }

    /**
     * handler para adicionar uma caches ao grafo
     * @param actionEvent sempre que ocorrer um evento
     * @throws AventureiroNaoHabilitado se o aventureiro na for premium nao pode criar cache
     */
    public void handlerAddCache(ActionEvent actionEvent) throws AventureiroNaoHabilitado, ForaDaRegiaoException {
        //toda a informacao da cache nova
        int idA = Integer.parseInt(addIdAventC.getText());
        int idO = 0;
        if(!addIdObjetoC.getText().isEmpty())
            idO = Integer.parseInt(addIdObjetoC.getText());
        int dif = Integer.parseInt(addDificC.getText());
        String l = addLocalC.getText();
        String[] parts = l.split(";");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        String local = "";
        if (y>=50 && y<200){
            local = "Norte";
        }
        else if (y >= 200 && y < 300){
            local = "Centro";
        }
        else if (y >= 300 && y < 450){
            local = "Sul";
        }
        if(verificarLimitesRegiao(x,y)){
            //verificacao se a cache e premium ou nao
            if(comboBoxTipoCache.getValue().compareTo("Premium") == 0) {
                PremiumCache c = null;
                if(idO != 0){
                    if(go.getTravelBug().get(idO).getCache() == null){
                        c = new PremiumCache(dif, ga.getAventureiros().get(idA), go.getTravelBug().get(idO),x, y, local);
                        go.getTravelBug().get(idO).getListaCachesPresente().put(go.getTravelBug().get(idO).getNumCachesPres(),c);
                        go.getTravelBug().get(idO).setNumCachesPres(go.getTravelBug().get(idO).getNumCachesPres() + 1);
                    }
                }
                else{
                    c = c = new PremiumCache(dif, ga.getAventureiros().get(idA),x, y, local);
                }
                gc.adicionaCache(c);
                consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionada uma cache: Id: " + c.getId() + ", Local: " + c.getLocal().getLocalizacao());
            }else if(comboBoxTipoCache.getValue().compareTo("Basic") == 0){
                BasicCache c = null;
                if(idO != 0) {
                    if(go.getTravelBug().get(idO).getCache() == null){
                        c = new BasicCache(dif, ga.getAventureiros().get(idA), go.getObjetos().get(idO), x, y, local);
                        go.getObjetos().get(idO).setCache(c);
                    }
                }else{
                    c = new BasicCache(dif, ga.getAventureiros().get(idA), x, y, local);
                }
                gc.adicionaCache(c);
                consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "Foi adicionada uma cache: Id: " + c.getId() + ", Local: " + c.getLocal().getLocalizacao());
            }
            atualizarAvent();
            atualizarCaches();
            atualizarObjeto();
        }else
            throw new ForaDaRegiaoException("Coordenadas fora do nosso alcance");
    }

    /**
     * funcao para ter a certeza que as caches sao colocadas entre os limites da nossa aplicaçao
     * @param x coordenada x da localizaçao da cache
     * @param y coordenada y da localizaçao da cache
     * @return true se poder colocar a cache, false se for fora do range da nossa aplicaçao
     */
    public boolean verificarLimitesRegiao(int x, int y){
        Point p1 = new Point(xleftLimit, yUpperLimit);
        Point p2 = new Point(xRightLimit, yLowerLimit);
        Rectangle r = new Rectangle(p1, p2, null);
        Point p3 = new Point(x, y);
        return r.isInside(p3);
    }

    /**
     * handler para editar informaçao de uma cache
     * @param actionEvent sempre que ocorrer um evento
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
     * @param actionEvent sempre que ocorrer um evento
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
     * @param actionEvent sempre que ocorrer um evento
     */
    public void handlerEncontrouCacheJ(ActionEvent actionEvent) {
        int id = Integer.parseInt(idAventJ.getText());
        int idC = Integer.parseInt(cacheEncJ.getText());

        if(gc.getCaches().get(idC).getTravelbug() != null){
            ga.getAventureiros().get(id).encontrouCache(gc.getCaches().get(idC), new Date(), go);
            gc.getCaches().get(idC).getHistAventureiros().put(gc.getCaches().get(idC).getNumAvent(),ga.getAventureiros().get(id));
            gc.getCaches().get(idC).setNumAvent(gc.getCaches().get(idC).getNumAvent() + 1);
            consolaMapa.setText("O aventureiro " + id + " econtrou uma cache\ncom um travel bug: \n");
            consolaMapa.setText(consolaMapa.getText() + "\n" +ga.getAventureiros().get(id).getListTravelBug().get(ga.getAventureiros().get(id).getNumTb()-1).getMissao());
            ArrayList<Cache> cachesRet = ga.getAventureiros().get(id).getListTravelBug().get(ga.getAventureiros().get(id).getNumTb()-1).interpetarMissao(gc, ga); // caches que podemos levar o tb, por causa da sua missao
            if (cachesRet.size() > 0){
                if (cachesRet.size() == 1){
                    for (Cache c : cachesRet){
                        consolaMapa.setText(consolaMapa.getText() + "\n" + "Pode levar para a cache com o ID: " + c.getIdCache());
                        ids.add(c.getIdCache());
                        BFS_AED2 BFS = new BFS_AED2(gcg.getGrafo(), idC);
                        DSP_AED2 DSP = new DSP_AED2(gcg.getGrafo(), idC, 1);
                        consolaMapa.setText(consolaMapa.getText()+ "\nCache id: " + c.getIdCache());
                        consolaMapa.setText(consolaMapa.getText()+ "\nCaminho mais curto: " + BFS.pathTo(c.getIdCache()));
                        consolaMapa.setText(consolaMapa.getText()+ "\nDistancia: " + DSP.distTo(c.getIdCache()) + "\n");
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
                        consolaMapa.setText(consolaMapa.getText()+ "\nDistancia: " + DSP.distTo(c.getIdCache()) + "\n");
                    }
                }
            }
        }else{
            ga.getAventureiros().get(id).encontrouCache(gc.getCaches().get(idC), new Date(), go);
            consolaMapa.setText("O aventureiro " + id + " econtrou uma cache\ncom um objeto: \n");
        }
        atualizarCaches();
        atualizarObjeto();
        atualizarAvent();
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "O Aventureiro: " + id + " encontrou a cache: " + idC);
    }

    /**
     * hanler para o aventureiro depositar o tb ou objeto
     * @param actionEvent sempre que ocorrer um evento
     * @throws MissaoNaoCompletadaComExitoException verifica se for um tb se a missa foi completada
     */
    public void handlerDepositouCacheJ(ActionEvent actionEvent) throws MissaoNaoCompletadaComExitoException {
        int idC = Integer.parseInt(cacheDepJ.getText());
        int id = Integer.parseInt(idAventJ.getText());
        // mudar as informacoes do tb
        Random num = new Random();
        int posicao = -1;
        while(posicao == -1 || posicao == 4 || posicao == 5 || posicao == 6){
            posicao = num.nextInt(9);
        }
        int idTb = ga.getAventureiros().get(id).getListTravelBug().get(ga.getAventureiros().get(id).getNumTb()-1).getIdObjeto();
        String nome = ga.getAventureiros().get(id).getListTravelBug().get(ga.getAventureiros().get(id).getNumTb()-1).getNome();
        ga.getAventureiros().get(id).encontrouCache((PremiumCache) gc.getCaches().get(idC), ga.getAventureiros().get(id).getListTravelBug().get(ga.getAventureiros().get(id).getNumTb()-1), new Date(20, 03, 2021), posicao);
        gc.getCaches().get(idC).getHistAventureiros().put(gc.getCaches().get(idC).getNumAvent(),ga.getAventureiros().get(id));
        gc.getCaches().get(idC).setNumAvent(gc.getCaches().get(idC).getNumAvent() + 1);
        int x = 1, j = 1;
        while(j<=go.getTravelBug().size()){
            if(go.getTravelBug().get(x) != null){
                if(go.getTravelBug().get(x).getNome().equals(nome)){
                    go.getTravelBug().get(x).getListaCachesPresente().put(go.getTravelBug().get(x).getNumCachesPres(), (PremiumCache) gc.getCaches().get(idC));
                    go.getTravelBug().get(x).setNumCachesPres(go.getTravelBug().get(x).getNumCachesPres()+1);
                    go.getTravelBug().get(x).setViajar(false);
                    go.getTravelBug().get(x).getListaAventureiros().put(go.getTravelBug().get(x).getNumAventureiros(), ga.getAventureiros().get(id));
                    go.getTravelBug().get(x).setNumAventureiros(go.getTravelBug().get(x).getNumAventureiros()+1);
                    go.getTravelBug().get(x).lerMissao(posicao+1);
                    gc.getCaches().get(idC).setTravelbug(go.getTravelBug().get(x));
                }
                j++;
            }
            x++;
        }
        atualizarCaches();
        atualizarObjeto();
        atualizarAvent();
        consolaAplicacao.setText(consolaAplicacao.getText() + "\n" + "O Aventureiro: " + id + " encontrou a cache: " + idC);
    }

    /**
     * handler para guardar toda a informaçao das caches, aventureiros e objetos
     * @param actionEvent sempre que ocorrer um evento
     * @throws AventureiroNaoExisteException se o aventureiro nao existir
     * @throws CacheNaoExisteException se a cache nao existir
     */
    public void handlerSave(ActionEvent actionEvent) throws AventureiroNaoExisteException, CacheNaoExisteException {
        ga.guardarAventBin();
        go.guardarObjBin();
        gc.guardarCachesBin();
        gcg.guardarCaminhos();
    }

    public static void main(String[] args) throws AventureiroNaoHabilitado {
        GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
        GestaoAcessoCache gc = new GestaoAcessoCache();
        GestaoAcessoObjeto go = new GestaoAcessoObjeto();
        GestaoAcessoCacheGraph gcg = new GestaoAcessoCacheGraph();

        ga.lerAventBin();
        go.lerObjectBin();
        gc.lerCachesBin();
        ga.regista(new Admin("fabio",15,61,"penafiel", "12345678"));
        int x = 1;
        if(gc.getCaches().size()>0){
            gcg.setGrafo(new GeoDigraph(gc.getNumCache()));
            while(x<=gc.getCaches().size()){
                gcg.getGrafo().adicionaCache(gc.getCaches().get(x));
                x++;
            }
        }
        gcg.lerCaminhos();

        /**
         * parte1
         int x = 1;
         if(ga.getAventureiros().size()>0){
         gcg.setGrafo(new GeoDigraph(gc.getNumCache()));
         while(x<=gc.getCaches().size()){
         gcg.getGrafo().adicionaCache(gc.getCaches().get(x));
         x++;
         }
         }
         gcg.lerCaminhos();
         int from = 0;
         int to = 5;
         //TopologicalX
         BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), from);// forma de sacar os menores valores de peso, distancia, etc
         DFS_AED2 dfs = new DFS_AED2(gcg.getGrafo(), from);//inutil pode nao ser inutil
         BFS_AED2 BFS = new BFS_AED2(gcg.getGrafo(), from);// saca o caminho com menos vertices
         DSP_AED2 DSPW = new DSP_AED2(gcg.getGrafo(), from, 0);// saca o caminho com menor custo
         DSP_AED2 DSPD = new DSP_AED2(gcg.getGrafo(), from, 1);// saca o caminho com menor custo
         DSP_AED2 DSPT = new DSP_AED2(gcg.getGrafo(), from, 2);// saca o caminho com menor custo

         System.out.println();

         System.out.println("\nTodos os edges: ");
         for (DirectedEdge_AED2 d: gcg.getGrafo().edges()){
         System.out.print(d);
         }

         if(BSP.distTo(0) == Double.POSITIVE_INFINITY){
         System.out.println("impossivel, nao ha caminho");
         }else
         System.out.println(BSP.distTo(0));

         System.out.println();
         System.out.println("o vertice 0 tem caminho ate: ");
         for (int v = 0; v<gcg.getGrafo().V(); v++){
         BSP = new BSP_AED2(gcg.getGrafo(), 0);
         if(BSP.hasPathTo(v))
         System.out.println(v+" ");
         }

         CC_AED2 c = new CC_AED2(gcg.getGrafo());
         System.out.println(c.connected(0,9));

         System.out.println("\nTeste de DFS e BSP:");
         if(BSP.hasPathTo(to)) {
         System.out.println("\nBSP: ");
         System.out.println("O caminho entre o vertice: " + from + " e o vertice: " + to +":");
         System.out.println("Peso: "+BSP.distTo(to));
         System.out.println("Tempo: "+BSP.tempoTo(to));
         System.out.println("Elevacao: "+BSP.elevTo(to));
         System.out.println("\nDFS: ");
         System.out.println("Se tem caminho: "+dfs.hasPathTo(to));
         System.out.println("Caminho DFS: "+dfs.pathTo(to));
         System.out.println("\nBFS: ");
         System.out.println("Caminho BFS: "+BFS.pathTo(to));
         System.out.println("\nDSP ve o caminho menor a partir do peso:");
         System.out.println("Caminho: "+DSPW.pathTo(to));
         System.out.println("Tem caminho: "+DSPW.hasPathTo(to));
         System.out.println("Custo minima: "+DSPW.distTo(to));
         System.out.println("\nDSP ve o caminho menor a partir da distancia:");
         System.out.println("Caminho: "+DSPD.pathTo(to));
         System.out.println("Tem caminho: "+DSPD.hasPathTo(to));
         System.out.println("Distancia minima: "+DSPD.distTo(to));
         System.out.println("\nDSPT ve o caminho menor a partir do tempo:");
         System.out.println("Caminho: "+DSPT.pathTo(to).toString());
         System.out.println("Tem caminho: "+DSPT.hasPathTo(to));
         System.out.println("Tempo minima: "+DSPT.distTo(to));
         String ola = LoginController.formatarString(DSPD.pathTo(to), 3);
         System.out.println(ola);

         }*/

        /**
         * parte2
         GFG gfg = new GFG();
         int linha = 7;
         int[][] matrix = gcg.getGrafo().graphToMatrix();
         matrix = gfg.exchangeAnyTwoRows(matrix, 1, linha+1);
         gfg.printMatrix(matrix);
         boolean[] v = new boolean[gcg.getGrafo().V()-1];
         int tempo = 300;
         int currPos = 0;
         int ans = Integer.MAX_VALUE;
         int count = 0, pos = 0;
         v[currPos] = true;
         //ans = gfg.tsp(matrix, v, 0, gcg.getGrafo().V()-1, 1, 0, ans, dist);
         String cachesPerc = gfg.retStrin(matrix, v, currPos, gcg.getGrafo().V()-1, 1, 0, ans, tempo, linha);
         //System.out.println(cachesPerc);
         //System.out.println("tamanho: "+cachesPerc.length());
         String []parts = cachesPerc.split("\n");
         System.out.println(cachesPerc);
         System.out.println("numero de linhas: "+parts.length);
         for (int i = 0; i < parts.length; i++) {
         String []parts2 = parts[i].split(" ");
         //System.out.println("numero de caches percorridas: "+parts2.length);
         if(parts2.length>count) {
         count = parts2.length;
         pos = i;
         }
         //System.out.println(parts[i]);
         }
         //System.out.println(pos + " " + count);
         parts[pos] = parts[pos].substring(2,parts[pos].length());
         parts[pos] = parts[pos] + linha;
         //System.out.println(parts[pos]);
         //System.out.println(parts[pos].length());
         int i = 100;
         String []parts3 = parts[pos].split(" ");
         i = parts3.length-1;
         System.out.println("o caixeiro viajante para percorrer o maximo de caixas num tempo limite de: " + tempo +" min, tera de percorrer: ");
         for (; i>=0; i--) {

         if(i!=0)
         System.out.print(parts3[i] + "->");
         else
         System.out.print(parts3[i]);
         }*/

        /**
         * parte3
         LoginController l = new LoginController();
         int o = 120, u = 4007;
         System.out.println(l.verificarLimitesRegiao(o, u));
         */
    }
}