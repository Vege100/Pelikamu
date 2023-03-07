package kayttoliittyma;

import java.io.IOException;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import luokat.Pelikamu;

/**
 * @author Verneri
 * @version 1.2.2023
 *
 */
public class PaaValikkoController {
    @FXML
    private Pane Hahmosivu;
    
    @FXML
    private Pane rootpane;

    @FXML
    void handleHahmot(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Hahmot.fxml"));
        rootpane.getChildren().setAll(pane);
    }
    @FXML
    void handleLisääPeli(ActionEvent event) {
        ModalController.showModal(PaaValikkoController.class.getResource("PeliLisäys.fxml"), "Jäsen", null, "");
    }

    @FXML
    void handleLopeta(ActionEvent event) {
            Platform.exit();
    }

    @FXML
    void handlePoistaPeli(ActionEvent event) {

    }

    @FXML
    void handleTulosta(ActionEvent event) {

    }


    @FXML
    void Teemat(ActionEvent event) {
        Dialogs.showMessageDialog("Vielä ei osata poistaa jäsentä");
    }

    @FXML
    void Tietoja(ActionEvent event) {
        ModalController.showModal(PaaValikkoController.class.getResource("Aloitusnäyttö.fxml"), "Jäsen", null, "");
    }

    // --------------------------------------------------------------------------
 
    
   // private Pelikamu pelikamu;
    //private Peli peliKohdalla;
    
    
   // protected void alusta() {
       // ;
    //}
    
}