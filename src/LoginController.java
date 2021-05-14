import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import projeto_LP2_AED2.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    }

    public void handleAventureiros(ActionEvent actionEvent) {

    }

    public void carregarAvent(ActionEvent actionEvent) throws AventureiroNaoHabilitado {

        /*
        GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
        Basic a1 = new Basic("fabio",1,2,  "porto");
        Basic a2 = new Basic("fabiaso",3,4,  "asd");
        Premium a3 = new Premium("asd", 5,6, "asdasd");
        Basic a4 = new Basic("kinigay",1,3,  "asdsa");
        Basic a5 = new Basic("213weqasd",4,5,  "asdasdsad");
        Admin a6 = new Admin("asdasd", 4,6,"lisboa");
        Objeto o1 = new Objeto("ola");

        Objeto o2 = new Objeto("porta");
        Objeto o3 = new Objeto("tele");
        Objeto o4 = new Objeto("pistola");
        Objeto o5 = new Objeto("cano");
        Objeto o6 = new Objeto("carro");
        GestaoAcessoObjeto go = new GestaoAcessoObjeto();
        go.regista(o1);
        go.regista(o2);
        go.regista(o3);
        go.regista(o4);
        go.regista(o5);
        go.regista(o6);

        a1.getListObjetos().put(0,o1);
        a2.getListObjetos().put(0,o2);
        a3.getListObjetos().put(0,o3);
        a4.getListObjetos().put(0,o4);
        a5.getListObjetos().put(0,o5);

        ga.regista(a1);
        ga.regista(a2);
        ga.regista(a3);
        ga.regista(a4);
        ga.regista(a5);
        ga.regista(a6);

        GestaoAcessoCache gc = new GestaoAcessoCache();
        BasicCache c1 = new BasicCache(4,a6,o6,2,4,"porto");
        BasicCache c2 = new BasicCache(4,a6,o6,2,4,"porto");
        BasicCache c3 = new BasicCache(4,a6,o6,2,4,"porto");
        BasicCache c4 = new BasicCache(4,a6,o6,2,4,"porto");
        BasicCache c5 = new BasicCache(4,a6,o6,2,4,"porto");

        gc.adicionaCache(c1);
        gc.adicionaCache(c2);
        gc.adicionaCache(c3);
        gc.adicionaCache(c4);
        gc.adicionaCache(c5);

        a1.getListCacheVisit().put(0, c1);
        a1.setNumCacheVis(1);
        a2.getListCacheVisit().put(0, c2);
        a2.setNumCacheVis(1);
        a3.getListCacheVisit().put(0, c3);
        a3.setNumCacheVis(1);
        a4.getListCacheVisit().put(0, c4);
        a4.setNumCacheVis(1);
        a5.getListCacheVisit().put(0, c5);
        a5.setNumCacheVis(1);

        a1.setNumCacheEsc(5);
        a2.setNumCacheEsc(3);
        a3.setNumCacheEsc(4);
        a4.setNumCacheEsc(15);
        a5.setNumCacheEsc(2);

        a1.setNumCacheVis(52);
        a2.setNumCacheVis(144);
        a3.setNumCacheVis(12);
        a4.setNumCacheVis(15);
        a5.setNumCacheVis(6);

*/
        GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
        GestaoAcessoCache gc = new GestaoAcessoCache();
        GestaoAcessoObjeto go = new GestaoAcessoObjeto();
        ga.lerAventureiros(); // mudar funcao de leitura e escrita para por o local
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
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


    }

    public void carregarAvent2(ActionEvent actionEvent) {

    }
}
