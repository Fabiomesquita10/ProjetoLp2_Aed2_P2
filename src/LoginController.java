import SearchProj.GeoDigraph;
import SearchProj.GestaoAcessoCacheGraph;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
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
        int radius = 30;
        for(int i=0; i<gcg.getGrafo().getNumCache(); i++){
            Circle c = new Circle(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX(), gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY(), radius);
            c.setFill(Color.LIGHTBLUE);

            StackPane stack = new StackPane();
            String txt = ""+gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY() + " " + gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX();
            stack.setLayoutX(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaX()-radius);
            stack.setLayoutY(gcg.getGrafo().getCaches().get(i).getLocal().getCoordenadaY()-radius);
            //stack.getChildren().addAll(c, new Text(gcg.getGrafo().getCaches().get(i).getLocal().getLocalizacao()));
            stack.getChildren().addAll(c, new Text(txt));

            graphGroup.getChildren().add(stack);
            /*
            if(gcg.getGrafo().E() > 0){
                for(Integer adj: gcg.getGrafo().adj(i)){
                    Line line = new Line(gcg.getGrafo().getVertexPosX(i), gc.getGrafo().getVertexPosY(i), gc.getGrafo().getVertexPosX(adj), gc.getGrafo().getVertexPosY(adj));
                    graphGroup.getChildren().add(line);
                }
            }*/
        }
    }

    public void carregarFicheiro() throws AventureiroNaoHabilitado {
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

        creatGraphGroup(ga, gcg, go);

    }

    public void handleAventureiros(ActionEvent actionEvent) {

    }

    public void carregarAvent(ActionEvent actionEvent) throws AventureiroNaoHabilitado {

    }

    public void carregarAvent2(ActionEvent actionEvent) {

    }
}
