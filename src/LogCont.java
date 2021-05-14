//import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto_LP2_AED2.AventureiroNaoHabilitado;
import projeto_LP2_AED2.GestaoAcessoAventureiro;
import projeto_LP2_AED2.GestaoAcessoCache;
import projeto_LP2_AED2.GestaoAcessoObjeto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogCont implements Initializable {
    public Button btnLogin;
    public TextField usernameField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void fecharStage(){
        Main.getStage().close();
    }

    public void handlerLogin(ActionEvent actionEvent) throws IOException, AventureiroNaoHabilitado {
        if(verificarLogIn()){
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        }else
            System.out.println("ola");
    }

    public boolean verificarLogIn() throws AventureiroNaoHabilitado {
        GestaoAcessoAventureiro tempA = new GestaoAcessoAventureiro();
        GestaoAcessoCache tempC = new GestaoAcessoCache();
        GestaoAcessoObjeto tempO = new GestaoAcessoObjeto();
        tempA.lerAventureiros(); // mudar funcao de leitura e escrita para por o local
        tempO.lerObjeto();
        tempO.lerTb();
        tempC.lerCache(tempA, tempO);
        tempO.lerTbHist(tempC, tempA);
        tempA.lerAventureirosHist(tempC, tempO);
        String nome = usernameField.getText();
        usernameField.setText("");
        int count = 0, x = 1;
        while(x<=tempA.getAventureiros().size()){
            if(nome.equals(tempA.getAventureiros().get(x).getNome())){
                count++;
            }
            x++;
        }
        if(count>0)
            return true;
        else
            return false;
    }

    public void handlerRegister(ActionEvent actionEvent) {
    }
}
