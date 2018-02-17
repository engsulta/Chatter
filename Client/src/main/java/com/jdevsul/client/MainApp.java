package com.jdevsul.client;

import com.jdevsul.client.util.ClientUtil;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
                 ClientUtil.loadWindow(getClass().getResource("/fxml/ClientLogin.fxml"), null, "Login");
         // ClientUtil.loadWindow(getClass().getResource("/fxml/ClientLogin.fxml"), stage, "Login");
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/ClientLogin.fxml"));       
        //Scene scene = new Scene(null);
        //scene.getStylesheets().add("/styles/Styles.css");
        //GoChat_JAXB newobj=new GoChat_JAXB();
//         GoChat_JAXB.readXml();
//         GoChat_JAXB.writeXml();
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
