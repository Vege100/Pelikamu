package kayttoliittyma;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import luokat.Hahmo;
import luokat.Hahmot;
import luokat.Peli;
import luokat.Pelikamu;
import luokat.apuException;

public class PeliLisäysController implements ModalControllerInterface<Pelikamu> {
    
    @FXML private ChoiceBox<Hahmo> hahmoChoice;
    @FXML private ChoiceBox<String> pelityyliChoice;
    @FXML private ChoiceBox<String> tulosChoice;
    
    @FXML
    void handlePeruuta(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        ModalController.closeStage(source);
    }

    @FXML
    void handleVahvista(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        lisääPeli();
        ModalController.closeStage(source);
    }
    
    @Override
    public void handleShown() {
        alusta();
    }
    @Override
    public Pelikamu getResult() {
        return pelikamu;
    } 
    
    @Override
    public void setDefault(Pelikamu oletus) {
             pelikamu = oletus;
    }



    
    
    
    
    //--------------------------------------------------------------------------------------
    
    private final ObservableList<Hahmo> alkiot = FXCollections.observableArrayList();
    private Pelikamu pelikamu;
    
    /**
     * 
     */
    public void alusta() {
        for (Hahmo hahmo : pelikamu.getChampionsList())
        {
            alkiot.add(hahmo);
        }
        hahmoChoice.setItems(alkiot);
    }
    
    /**
     * Metodi pelin lisäämistä varten
     */
    protected void lisääPeli() {
        Peli uusi = new Peli();
        uusi.register();
        uusi.perusTeemo();
        uusi.setHahmo(pelikamu.viimeisinHahmo().getId());
        try {
            pelikamu.add(uusi);            
        }
        catch (apuException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;

        }
    }
}
