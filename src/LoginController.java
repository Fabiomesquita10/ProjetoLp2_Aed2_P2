import SearchProj.*;
import edu.princeton.cs.algs4.CC;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    public TableColumn<TravelBug, String> viajarTCol;
    public TableColumn<TravelBug, String> missaoCol;
    public TableColumn<TravelBug, String> ultimaCCol;
    public TableColumn<TravelBug, String> ultimoATCol;
    public TableColumn<TravelBug, String> numCCol;
    public TableColumn<TravelBug, String> numATCol;
    private ArrayList<TravelBug> TbArrayList;

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
    public TextField cachePartida;
    public TextField cacheChegada;
    public TextArea consolaMapa;



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

        try {
            carregarFicheiro();
        } catch (AventureiroNaoHabilitado aventureiroNaoHabilitado) {
            aventureiroNaoHabilitado.printStackTrace();
        }
    }

    public void creatGraphGroup(GestaoAcessoAventureiro ga, GestaoAcessoCacheGraph gcg, GestaoAcessoObjeto go){
        if(gcg.getGrafo().E() > 0){
            for (int k = 0; k < gcg.getGrafo().getNumCache(); k++) {
                criar_direct_edge(k);
            }
        }
        for(int i=0; i<gcg.getGrafo().getNumCache(); i++){
            criar_graph(i);
        }


        graphGroup.addEventHandler(MouseEvent.MOUSE_RELEASED, evtScene -> {
            EventTarget evtCircleTarget=evtScene.getTarget();
            if(evtCircleTarget instanceof Circle){
                int x = 1;
                int id = 0;
                double cX = ((Circle) evtCircleTarget).getCenterX();
                double cY = ((Circle) evtCircleTarget).getCenterY();
                while(x<=gc.getCaches().size()){
                    if(gc.getCaches().get(x).getLocal().getCoordenadaX() == cX)
                        if(gc.getCaches().get(x).getLocal().getCoordenadaY() == cY)
                            id = gc.getCaches().get(x).getIdCache();
                    x++;
                }
                id++;
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
                System.out.println("seta fodida");
                if(((Arrow) evtCircleTarget).getFill() == Color.WHITESMOKE)
                    ((Arrow) evtCircleTarget).setFill(Color.DARKRED);
                else
                    ((Arrow) evtCircleTarget).setFill(Color.WHITESMOKE);
            }
            else{
                graphGroup.getChildren().clear();
                if(gcg.getGrafo().E() > 0){
                    for (int k = 0; k < gcg.getGrafo().getNumCache(); k++) {
                        criar_direct_edge(k);
                    }
                }
                for (int j = 0; j < gcg.getGrafo().getNumCache(); j++) {
                    criar_graph(j);
                }
            }
        });
    }

    public void criar_direct_edge(int k){
        for(DirectedEdge_AED2 adj: gcg.getGrafo().adj(k)){
            Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                    gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20);
            a.setFill(Color.DARKRED);
            graphGroup.getChildren().add(a);
        }
    }

    public void criar_graph(int i){
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

    public void carregarFicheiro() throws AventureiroNaoHabilitado {

        ga.lerAventureiros(); // mudar funcao de leitura e escrita para por o local
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        ga.regista(new Admin("fabio",15,61,"penafiel", "12345678"));

        //AVENTUREIROS
        int x = 1;
        if(aventTables!=null)
            aventTables.getItems().clear();
        if(ga.getAventureiros().size()>0){
            while(x<=ga.getAventureiros().size()){
                AventArrayList.add(ga.getAventureiros().get(x));
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
        if(objetoTables!=null)
            objetoTables.getItems().clear();
        if(go.getObjetos().size()>0){
            while(x<=go.getObjetos().size()){
                ObjetoArrayList.add(go.getObjetos().get(x));
                x++;
            }
            objetoTables.getItems().addAll(ObjetoArrayList);
        }

        //TRAVELBUG
        x = 1;
        if(tbTables!=null)
            tbTables.getItems().clear();
        if(go.getTravelBug().size()>0){
            while(x<=go.getTravelBug().size()){
                TbArrayList.add(go.getTravelBug().get(x));
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

    public static void main(String[] args) throws AventureiroNaoHabilitado {
        GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
        GestaoAcessoCache gc = new GestaoAcessoCache();
        GestaoAcessoObjeto go = new GestaoAcessoObjeto();
        GestaoAcessoCacheGraph gcg = new GestaoAcessoCacheGraph();

        ga.lerAventureiros(); // mudar funcao de leitura e escrita para por o local
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        ga.regista(new Admin("fabio",15,61,"penafiel", "12345678"));
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
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), from);
        DFS_AED2 dfs = new DFS_AED2(gcg.getGrafo(), from);//inutil pode nao ser inutil
        BFS_AED2 BFS = new BFS_AED2(gcg.getGrafo(), from);
        System.out.println();

        System.out.println("\nTeste de DFS e BSP:");
        if(BSP.hasPathTo(to)) {
            System.out.println("O caminho entre o vertice: " + from + " e o vertice: " + to +":");
            System.out.println("Peso: "+BSP.distTo(to));
            System.out.println("Distancia: "+BSP.distTo2(to));
            System.out.println("Tempo: "+BSP.tempoTo(to));
            System.out.println("Elevacao: "+BSP.elevTo(to));
            System.out.println("Se tem caminho: "+dfs.hasPathTo(to));
            System.out.println("Caminho DFS: "+dfs.pathTo(to));
            System.out.println("Caminho BFS: "+BFS.pathTo(to));
        }
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

    }

    public void handlerPesquisaCache(ActionEvent actionEvent) {
        consolaMapa.setText(consolaMapa.getText() +  "PESQUISA CACHE\n");
        String tipo = "", regiao = "";
        int dif = 0;
        if(!tipoCache.getText().equals(""))
            tipo = tipoCache.getText();
        if(!regiaoCache.getText().equals(""))
            regiao = regiaoCache.getText();
        if(!difiCache.getText().equals(""))
            dif = Integer.parseInt(difiCache.getText());
        //consolaMapa.setText(consolaMapa.getText() + tipo + " " + regiao + " " + dif);

        graphGroup.getChildren().clear();
        if(gcg.getGrafo().E() > 0){
            for (int k = 0; k < gcg.getGrafo().getNumCache(); k++) {
                if(tipo.equals("premium")) {
                    if (gcg.getGrafo().getCaches().get(k) instanceof PremiumCache && gcg.getGrafo().getCaches().get(k).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(k).getLocal().getLocalizacao().equals(regiao)) {
                        for (DirectedEdge_AED2 adj : gcg.getGrafo().adj(k)) {
                            if (gcg.getGrafo().getCaches().get(adj.to()) instanceof PremiumCache && gcg.getGrafo().getCaches().get(adj.to()).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(adj.to()).getLocal().getLocalizacao().equals(regiao)) {
                                Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                        gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20);
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
                                Arrow a = new Arrow(gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(k).getLocal().getCoordenadaY(),
                                        gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(adj.to()).getLocal().getCoordenadaY(), 20);
                                a.setFill(Color.DARKRED);
                                graphGroup.getChildren().add(a);
                            }
                        }
                    }
                }
            }
        }
        for(int i=0; i<gcg.getGrafo().getNumCache(); i++){
            if(tipo.equals("premium")){
                if(gcg.getGrafo().getCaches().get(i) instanceof PremiumCache && gcg.getGrafo().getCaches().get(i).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                    criar_graph(i);
            }else if(tipo.equals("basic")){
                if(gcg.getGrafo().getCaches().get(i) instanceof BasicCache && gcg.getGrafo().getCaches().get(i).getDificuldade().equals(dif) && gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao().equals(regiao))
                    criar_graph(i);
            }
        }

    }

    public void handlerExisteCaminho(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "EXISTE CAMINHO\n");
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        if (BSP.hasPathTo(cp)){
            consolaMapa.setText(consolaMapa.getText() +  "EXISTE CAMINHO\n");
        }else {
            consolaMapa.setText(consolaMapa.getText() + "NÃO EXISTE CAMINHO\n");
        };
    }

    public void handlerDistancia(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "DISTANCIA CACHE\n");
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "DISTANCIA ENTRE CACHES: " + BSP.distTo2(cc) + " METROS\n");
    }

    public void handlerCachesPerc(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "CACHES A PERCORRER\n");
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());

        BFS_AED2 BFS = new BFS_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "CAMINHO A PERCORRER: " + BFS.pathTo(cc) + "\n");

    }

    public void handlerPeso(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "PESO CACHE\n");
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "PESO ENTRE CACHES: " + BSP.distTo(cc) + "\n");
    }

    public void handlerTempo(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "TEMPO CACHE\n");
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "TEMPO ENTRE CACHES: " + BSP.tempoTo(cc) + " MINUTOS\n");
    }

    public void handlerElevacao(ActionEvent actionEvent) {
        //consolaMapa.setText(consolaMapa.getText() +  "ELEVACAO CACHE\n");
        int cp = Integer.parseInt(cachePartida.getText());
        int cc = Integer.parseInt(cacheChegada.getText());
        BSP_AED2 BSP = new BSP_AED2(gcg.getGrafo(), cp);
        consolaMapa.setText(consolaMapa.getText() +  "ELEVACAO ENTRE CACHES: " + BSP.elevTo(cc) + "\n");
    }
}
