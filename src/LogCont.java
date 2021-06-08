//import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto_LP2_AED2.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogCont implements Initializable {
    public Button btnLogin;
    public TextField usernameField;
    public TextField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void fecharStage(){
        Main.getStage().close();
    }

    public void handlerLogin(ActionEvent actionEvent) throws IOException, AventureiroNaoHabilitado {
        verificarLogIn();
    }

    /**
     * funcao que verifica o login na aplicacao
     * @throws AventureiroNaoHabilitado se o aventurerio nao poder dar login
     * @throws IOException input ou output tiver algum erro
     */
    public void verificarLogIn() throws AventureiroNaoHabilitado, IOException {
        GestaoAcessoAventureiro tempA = new GestaoAcessoAventureiro();
        GestaoAcessoCache tempC = new GestaoAcessoCache();
        GestaoAcessoObjeto tempO = new GestaoAcessoObjeto();
        tempA.lerAventBin();
        tempO.lerObjectBin();
        tempC.lerCachesBin();
        gerarpass(tempA);
        String nome = usernameField.getText();
        usernameField.setText("");
        String pass = passwordField.getText();
        passwordField.setText("");
        int count = 0, x = 1;
        boolean admin = false;
        while(x<=tempA.getAventureiros().size()){
            if(nome.equals(tempA.getAventureiros().get(x).getNome())) {
                if (pass.equals(tempA.getAventureiros().get(x).getPassword())){
                    count++;
                    if(tempA.getAventureiros().get(x) instanceof Admin)
                        admin = true;
                }
            }
            x++;
        }
        if(count>0 && admin) {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        }else if(count>0 && !admin){
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("fxml/MenuN.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        }
        else{
            usernameField.setText("CAMPOS ERRADOS");
            passwordField.setText("CAMPOS ERRADOS");
        }
    }

    public void gerarpass(GestaoAcessoAventureiro ga){
        int x = 1, k = 1;
        if(ga.getAventureiros().size()>0){
            while(k<=ga.getAventureiros().size()){
                if(ga.getAventureiros().get(x) != null){
                    ga.getAventureiros().get(x).setPassword("1234");
                    k++;
                }
                x++;
            }
        }
    }

    public void handlerRegister(ActionEvent actionEvent) {
    }
}
