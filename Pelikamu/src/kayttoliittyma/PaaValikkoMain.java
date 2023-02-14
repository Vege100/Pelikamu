package kayttoliittyma;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Verneri
 * @version 1.2.2023
 *
 */
public class PaaValikkoMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("PaaValikkoView.fxml"));
            final Pane root = ldr.load();
            //final PaaValikkoController paavalikkoCtrl = (PaaValikkoController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("paavalikko.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("PaaValikko");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei kytss
     */
    public static void main(String[] args) {
        launch(args);
    }
}