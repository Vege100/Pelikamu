package kayttoliittyma;

import java.awt.Desktop;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import luokat.Hahmo;
import luokat.Hahmot;
import luokat.Peli;
import luokat.Pelikamu;
import luokat.apuException;

/**
 * @author Verneri
 * @version 1.2.2023
 *
 */
public class PaaValikkoController implements Initializable{
    @FXML
    private Pane Hahmosivu;
    // @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private ScrollPane panelPelit;  
    @FXML private Pane rootpane;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }


    @FXML
    void handleHahmot(ActionEvent event) {
        // Pane pane = FXMLLoader.load(getClass().getResource("Hahmot.fxml"));
        // rootpane.getChildren().setAll(pane);
        // showPeli(1);
        lisääHahmo();
        // ModalController.showModal(PaaValikkoController.class.getResource("Hahmot.fxml"), "Hahmot", null, pelikamu);
    }
    @FXML
    void handleLisääPeli(ActionEvent event) {
        ModalController.showModal(PaaValikkoController.class.getResource("PeliLisäys.fxml"), "Peli", null, pelikamu);
        //  lisääPeli();
    }
  //  TODO järkevästi lista hahmoista
  //  @FXML 
  //  void handleHahmoCb(ActionEvent event) {
  //      ;
  //  }

    @FXML
    void handleLopeta(ActionEvent event) {
            Platform.exit();
    }

    @FXML
    void handlePoistaPeli(ActionEvent event) {
            // TODO poistaminen
    }

    @FXML
    void handleTulosta(ActionEvent event) {
            showPeli();
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
 
    
    private Pelikamu pelikamu = new Pelikamu();
    private TextArea areaPeli = new TextArea();
    

    
    /**
     * 
     */
    protected void lisääHahmo() {
        Hahmo hahmo = new Hahmo();
        hahmo.register();
        hahmo.perusAnnie();
        try {
            pelikamu.add(hahmo);            
        }
        catch (apuException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
    }

    
    /**
     * Graafinen alustus
     */
    protected void alusta() {
        panelPelit.setContent(areaPeli);
        areaPeli.setFont(new Font("Courier New", 12));
        panelPelit.setFitToHeight(true);
        
    }
    
    /**
     * Tulostaa pelit ruutuun
     */
    protected void showPeli() {
        areaPeli.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPeli)) {
            for (int i = 0; i < pelikamu.getPelit(); i++)
                print(os,pelikamu.getPeli(i));
               
        }
    }
    
    /**
     *  tulostaa "valitun" hahmon pelit ruutuun
     * @param hId valitun hahmon id
     */
    protected void showPeli(int hId) {
        List<Peli> pelit = pelikamu.givePelit(hId);
        areaPeli.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPeli)) {
            for (Peli peli : pelit) {
                print(os,peli);
        }
    }
        
    }
        
    /**
     * Hetkellinen tulostus tekstinä näyttöön
     * @param os tulostus ulos
     * @param peli joka tulostetaan
     */
    public void print(PrintStream os, final Peli peli) {
        os.println("----------------------------------------------");
        peli.print(os);
        os.println(" " + pelikamu.getChampionName(peli.getHid()));
        os.println("----------------------------------------------");
        
    }
    
    /**
     * alustus ohjelma, EI KÄYTÖSSÄ
     * @param pelikamu joka alustetaan
     */
    public void setPelikamu(Pelikamu pelikamu) {
        this.pelikamu = pelikamu;
    }
    
    
}