
package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.db.DB;

/**
 *
 * @author herrmann
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage stage) {
        
        if (DB.getConnection() != null) {
            System.out.println("teste");
        }
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Menu principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
