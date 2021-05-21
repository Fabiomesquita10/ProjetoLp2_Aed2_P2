import Search.BST_AED2_2021;
import SearchProj.*;
import edu.princeton.cs.algs4.*;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projeto_LP2_AED2.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class LoginController implements Initializable {

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

    int setas = 1;
    public Group graphGroup;
    public Group temp;
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
    //Caminhos
    public TextArea consolaCaminhos;
    public TextField cachePartidaAC;
    public TextField distanciaAC;
    public TextField tempoAC;
    public TextField cacheChegadaAC;
    public TextField elevacaoAC;


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

    public void creatGraphGroup(GestaoAcessoAventureiro ga, GestaoAcessoCacheGraph gcg, GestaoAcessoObjeto go){
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

        graphGroup.addEventHandler(MouseEvent.MOUSE_RELEASED, evtScene -> {
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
            }else if(evtCircleTarget instanceof Arrow){
                if(((Arrow) evtCircleTarget).getFill() == Color.WHITESMOKE)
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

    public void criar_direct_edge(int k){
        for(DirectedEdge_AED2 adj: gcg.getGrafo().adj(k)){
            int w = (int)adj.weight();
            int d = adj.distancia();
            int t = adj.tempo();
            int e = adj.elevacao();
            Arrow a;
            if(gcg.getGrafo().getCaches().contains(k) && gcg.getGrafo().getCaches().contains(adj.to())){
                a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20, w,d,t,e);
                a.setFill(Color.DARKRED);
                graphGroup.getChildren().add(a);
            }
        }
    }

    public void criar_graph(int i){
        if(gcg.getGrafo().getCaches().contains(i)){
            Circle c = new Circle(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY(), radius);
            StackPane stack = new StackPane();
            String tipo = "";
            if(gcg.getGrafo().getCaches().get(i) instanceof BasicCache)
                c.setFill(Color.SILVER);
            if(gcg.getGrafo().getCaches().get(i) instanceof PremiumCache)
                c.setFill(Color.GOLD);
            String txt = ""+gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY() + " " + gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX();
            stack.setLayoutX(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX()-radius);
            stack.setLayoutY(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY()-radius);
            //stack.getChildren().addAll(c, new Text(gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao()));
            //stack.getChildren().addAll(c, new Text(txt));
            stack.getChildren().addAll(c);

            graphGroup.getChildren().add(stack);
        }
    }

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



        //AVENTUREIROS
        int x = 1, k= 1;
        if(aventTables!=null)
            aventTables.getItems().clear();
        AventArrayList.clear();
        if(ga.getAventureiros().size()>0){
            while(k<=ga.getAventureiros().size()){
                if(ga.getAventureiros().get(x) != null){
                    AventArrayList.add(ga.getAventureiros().get(x));
                    k++;
                }
                x++;
            }
            aventTables.getItems().addAll(AventArrayList);
        }

        //CACHES
        x = 1;
        if(cacheTables!=null)
            cacheTables.getItems().clear();
        if(gc.getCaches().size()>0){
            gcg.setGrafo(new GeoDigraph(gc.getNumCache()));
            while(x<=gc.getCaches().size()){
                gcg.getGrafo().adicionaCache(gc.getCaches().get(x));
                CacheArrayList.add(gc.getCaches().get(x));
                x++;
            }
            cacheTables.getItems().addAll(CacheArrayList);
        }

        //OBJETOS
        x = 1;
        k = 1;
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

        //teste de directedEdge
        gcg.lerCaminhos();

        creatGraphGroup(ga, gcg, go);

    }

    public void handleAventureiros(ActionEvent actionEvent) {

    }

    public void carregarAvent(ActionEvent actionEvent) throws AventureiroNaoHabilitado {

    }

    public void handlerPesquisaCache(ActionEvent actionEvent) throws InterruptedException {
        //consolaMapa.setText(consolaMapa.getText() +  "PESQUISA CACHE\n");
        String tipo = "", regiao = "";
        int dif = 0, idTb = 0;
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
                for (int k = 0; k < gcg.getGrafo().getNumCache(); k++) {
                    if(tipo.equals("premium")) {
                        if (gcg.getGrafo().getCaches().get(k) instanceof PremiumCache && gcg.getGrafo().getCaches().get(k).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(k).getLocal().getLocalizacao().equals(regiao)) {
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
                    else if(tipo.equals("basic")) {
                        if (gcg.getGrafo().getCaches().get(k) instanceof BasicCache && gcg.getGrafo().getCaches().get(k).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(k).getLocal().getLocalizacao().equals(regiao)) {
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
            for(int i=1; i<gcg.getGrafo().getNumCache(); i++){
                if(tipo.equals("premium")){
                    if(gcg.getGrafo().getCaches().get(i) instanceof PremiumCache && gcg.getGrafo().getCaches().get(i).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                        criar_graph(i);
                }else if(tipo.equals("basic")){
                    if(gcg.getGrafo().getCaches().get(i) instanceof BasicCache && gcg.getGrafo().getCaches().get(i).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                        criar_graph(i);
                }
            }
        }else if(!regiao.equals("")){
            graphGroup.getChildren().clear();
            if(gcg.getGrafo().E() > 0 && setas == 1){
                for (int k = 1; k < gcg.getGrafo().getNumCache(); k++) {
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
            for(int i=1; i<gcg.getGrafo().getNumCache(); i++){
                if(gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                    criar_graph(i);

            }
        }else if(!tipo.equals("")){
            graphGroup.getChildren().clear();
            if(gcg.getGrafo().E() > 0 && setas == 1){
                for (int k = 1; k < gcg.getGrafo().getNumCache(); k++) {
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
            for(int i=1; i<gcg.getGrafo().getNumCache(); i++){
                if(tipo.equals("premium")){
                    if(gcg.getGrafo().getCaches().get(i) instanceof PremiumCache)
                        criar_graph(i);
                }else if(tipo.equals("basic")){
                    if(gcg.getGrafo().getCaches().get(i) instanceof BasicCache)
                        criar_graph(i);
                }
            }
        }else if(dif != 0){
            graphGroup.getChildren().clear();
            if(gcg.getGrafo().E() > 0 && setas == 1){
                for (int k = 1; k < gcg.getGrafo().getNumCache(); k++) {
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
            for(int i=1; i<gcg.getGrafo().getNumCache(); i++){
                if(gcg.getGrafo().getCaches().get(i).getDificuldade() == dif)
                    criar_graph(i);
            }
        }else if(idTb != 0){
            if(go.getTravelBug().contains(idTb)){
                graphGroup.getChildren().clear();
                if(gcg.getGrafo().E() > 0 && setas == 1){
                    for (int k = 1; k < gcg.getGrafo().getNumCache(); k++) {
                        if (contain(go.getTravelBug(), idTb, k)) {
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
                for(int i=1; i<gcg.getGrafo().getNumCache(); i++){
                    System.out.println(gcg.getGrafo().getCaches().get(i).getIdCache());
                    int id = gcg.getGrafo().getCaches().get(i).getIdCache();
                    if(contain(go.getTravelBug(), idTb, id))
                        criar_graph(i);
                }
            }
        }

    }

    public boolean contain(BST_AED2_2021<Integer, TravelBug> t, int id, int k){
        int x = 1, count = 0;
        while(t.get(id).getListaCachesPresente().size()>=x){
            if(t.get(id).getListaCachesPresente().get(x).getIdCache() ==  k)
                count++;
            x++;
        }
        return count != 0;
    }

    public void handlerExisteCaminho(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "EXISTE CAMINHO\n");
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        if (BSP.hasPathTo(cc)){
            consolaMapa.setText(consolaMapa.getText() +  "EXISTE CAMINHO\n");
        }else {
            consolaMapa.setText(consolaMapa.getText() + "NÃO EXISTE CAMINHO\n");
        }
    }

    public void handlerDistancia(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "DISTANCIA CACHE\n");
        /*
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "DISTANCIA ENTRE CACHES: " + BSP.distTo2(cc) + " METROS\n");
        */
        tipoPesquisa = "distancia";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: DISTANCIA");

    }

    public void handlerCachesPerc(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "CACHES A PERCORRER\n");

        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BFS_AED2 BFS = new BFS_AED2(gcg.getGrafo(), cp);

        /*
        if(BFS.pathTo(cc)!=null)
            consolaMapa.setText(consolaMapa.getText() +  "CAMINHO A PERCORRER: " + BFS.pathTo(cc) + "\n");
        else
            consolaMapa.setText(consolaMapa.getText() +  "NAO HA CAMINHO ENTRE A CACHE " + cp + " E " + cc);
        */
        switch (tipoPesquisa){
            case "distancia":
                DSP_AED2 DSPD = new DSP_AED2(gcg.getGrafo(), cp, 1);// saca o caminho com menor custo
                if(DSPD.hasPathTo(cc)){
                    String s = formatarString(DSPD.pathTo(cc), 3);
                    System.out.println(s);
                    consolaMapa.setText(consolaMapa.getText() + "\n" +  "CAMINHO A PERCORRER: \n" + s + "\n");
                }
                break;
            case "tempo":
                DSP_AED2 DSPT = new DSP_AED2(gcg.getGrafo(), cp, 2);// saca o caminho com menor custo
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
                // igual ao de cima
                break;
        }

    }

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

    public void handlerPeso(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "PESO CACHE\n");
        /*
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "PESO ENTRE CACHES: " + BSP.distTo(cc) + "\n");
        */
        tipoPesquisa = "peso";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: PESO");
    }

    public void handlerTempo(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "TEMPO CACHE\n");
        /*
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "TEMPO ENTRE CACHES: " + BSP.tempoTo(cc) + " MINUTOS\n");
        */
        tipoPesquisa = "tempo";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: TEMPO");
    }

    public void handlerElevacao(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "ELEVACAO CACHE\n");
        /*
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "ELEVACAO ENTRE CACHES: " + BSP.elevTo(cc) + "\n");
        */
        tipoPesquisa = "elevacao";
        consolaMapa.setText(consolaMapa.getText() +  "PARAMETRO DE PESQUISA: ELEVACAO");

    }

    public void handlerApagarConsola(ActionEvent actionEvent) {
        consolaMapa.setText("");
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
        }
    }

    public void handlerAddAvent(ActionEvent actionEvent) {
        int x = 230, y=459;
        if(comboBoxAv.getValue().compareTo("Premium") == 0){
            Premium p = new Premium(nomeAvent.getText(),x,y,localAvent.getText());
            ga.regista(p);
        }

        if(comboBoxAv.getValue().compareTo("Basic") == 0){
            Basic b = new Basic(nomeAvent.getText(),x,y,localAvent.getText());
            ga.regista(b);
        }

        if(comboBoxAv.getValue().compareTo("Admin") == 0){
            Admin a = new Admin(nomeAvent.getText(),x,y,localAvent.getText(),PassAvent.getText());
            ga.regista(a);
        }

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

    public void handlerRemoverAvent(ActionEvent actionEvent) throws AventureiroNaoExisteException {
        int id = Integer.parseInt(remIdAvent.getText());
        ga.remove(id);

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

    public void handlerEditAv(ActionEvent actionEvent) {

        int id = Integer.parseInt(idEdit.getText());

        ga.editarTab(id,nomeEdit.getText(),localEdit.getText());

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

    public void handlerCalcular(ActionEvent actionEvent) {
        int tempo = Integer.parseInt(tempoL.getText());
        int currPos = 0;
        int linha = Integer.parseInt(cacheP.getText());

        GFG gfg = new GFG();
        int[][] matrix = gcg.getGrafo().graphToMatrix();
        matrix = gfg.exchangeAnyTwoRows(matrix, 1, linha + 1);
        boolean[] v = new boolean[gcg.getGrafo().V()-1];
        int ans = Integer.MAX_VALUE;
        int count = 0, pos = 0;
        v[currPos] = true;
        String cachesPerc = gfg.retStrin(matrix, v, currPos, gcg.getGrafo().V()-1, 1, 0, ans, tempo, linha);
        String []parts = cachesPerc.split("\n");

        for (int i = 0; i < parts.length; i++) {
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
        matrix = gfg.exchangeAnyTwoRows(matrix, linha+1, 1);
    }

    public void handlerAddObjeto(ActionEvent actionEvent) {
        String nome = addNomeO.getText();
        Objeto o = new Objeto(nome);
        go.regista(o);

        int x = 1, k = 1;
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
    }

    public void handlerRemObjeto(ActionEvent actionEvent) {
        int id = Integer.parseInt(remIdO.getText());
        go.removeO(id);

        int x = 1, k = 1;
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

    }

    public void handlerEditObjeto(ActionEvent actionEvent) {
        int id = Integer.parseInt(editIdO.getText());
        String nome = editNomeO.getText();
        go.editarO(id,nome);

        int x = 1, k = 1;
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
    }

    public void handlerAddTb(ActionEvent actionEvent) {
        String nome = addNomeTb.getText();
        TravelBug tb = new TravelBug(nome);
        go.regista(tb);

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

    public void handlerRemTb(ActionEvent actionEvent) {
        int id = Integer.parseInt(remIdTb.getText());
        go.removeTb(id);

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

    public void handlerEditTb(ActionEvent actionEvent) {
        String nome = editNomeTb.getText();
        int id = Integer.parseInt(editIdTb.getText());
        go.editarTb(id,nome);

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

    public void handlerAddCache(ActionEvent actionEvent) {
    }

    public void handlerRemCache(ActionEvent actionEvent) throws CacheNaoExisteException {
        System.out.println("GC: ");
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        System.out.println("GCG: ");
        gcg.getGrafo().getCaches().printInOrder(gcg.getGrafo().getCaches().getRoot());
        System.out.println("\n\n\n");
        int id = Integer.parseInt(remIdCacheC.getText());
        gc.removeCache(id);
        gcg.removeCache(id);
        System.out.println("GC: ");
        gc.getCaches().printInOrder(gc.getCaches().getRoot());
        System.out.println("GCG: ");
        gcg.getGrafo().getCaches().printInOrder(gcg.getGrafo().getCaches().getRoot());
        graphGroup.getChildren().clear();
        gcg.setGrafo(new GeoDigraph(gc.getNumCache()));
        System.out.println("\n\n\nCRIEI NOVO GRAPH");
        gcg.getGrafo().getCaches().printInOrder(gcg.getGrafo().getCaches().getRoot());

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
        System.out.println("\n\n\nCARREGUEI DE NOVO GRAPH");
        gcg.getGrafo().getCaches().printInOrder(gcg.getGrafo().getCaches().getRoot());
        creatGraphGroup(ga, gcg, go);
        gcg.getGrafo().getCaches().get(4);
        System.out.println();
    }

    public void handlerVerCaminhos(ActionEvent actionEvent) {
        consolaCaminhos.setText("");
        StringBuilder caminhos = new StringBuilder();
        for (int i = 1; i < gcg.getGrafo().V(); i++) {
            for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(i)){
                caminhos.append("De: ").append(i).append(", para: ").append(adj.to());
                caminhos.append("\nDistancia: ").append(adj.distancia());
                caminhos.append(", Tempo: ").append(adj.tempo());
                caminhos.append(", Elevacao: ").append(adj.elevacao()).append("\n");
            }
        }
        consolaCaminhos.setText(caminhos.toString());
    }

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

    public void handlerAddCaminho(ActionEvent actionEvent) {
        int idP = Integer.parseInt(cachePartidaAC.getText());
        int idC = Integer.parseInt(cacheChegadaAC.getText());
        int dist = Integer.parseInt(distanciaAC.getText());
        int temp = Integer.parseInt(tempoAC.getText());
        int elevacao = Integer.parseInt(elevacaoAC.getText());
        if(gcg.getGrafo().getCaches().contains(idP) && gcg.getGrafo().getCaches().contains(idC)){
            DirectedEdge_AED2 d = new DirectedEdge_AED2(idP, idC, 0, temp, dist, elevacao);
            gcg.getGrafo().addEdge(d);
            atualizarGraph();
        }
    }

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
}
