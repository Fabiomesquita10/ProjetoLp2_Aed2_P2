import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogCont implements Initializable {
    public Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void fecharStage(){
        Main.getStage().close();
    }

    public void handlerLogin(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void handlerRegister(ActionEvent actionEvent) {
    }
}
