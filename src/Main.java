import SearchProj.GestaoAcessoCacheGraph;
import javafx.application.Application;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projeto_LP2_AED2.Admin;
import projeto_LP2_AED2.GestaoAcessoAventureiro;
import projeto_LP2_AED2.GestaoAcessoCache;
import projeto_LP2_AED2.GestaoAcessoObjeto;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("login_fxml.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

        GestaoAcessoAventureiro ga = new GestaoAcessoAventureiro();
        GestaoAcessoCache gc = new GestaoAcessoCache();
        GestaoAcessoObjeto go = new GestaoAcessoObjeto();
        ga.lerAventureiros(); // mudar funcao de leitura e escrita para por o local
        go.lerObjeto();
        go.lerTb();
        gc.lerCache(ga, go);
        go.lerTbHist(gc, ga);
        ga.lerAventureirosHist(gc, go);
        ga.regista(new Admin("fabio",15,61,"penafiel", "12345678"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}
