package kayttoliittyma;

import java.awt.Button;
import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import luokat.Hahmo;
import luokat.Hahmot;
import luokat.Peli;
import luokat.Pelikamu;
import luokat.apuException;

public class HahmoLisäysController implements ModalControllerInterface<Pelikamu> {
    


    @FXML private TextField hahmoTulos;
    
    @FXML
    void handlePeruuta(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        ModalController.closeStage(source);
    }

    @FXML
    void handleVahvista(ActionEvent event) {
        Node  source = (Node)  event.getSource(); 
        lisääHahmo();
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
    

    private Pelikamu pelikamu;
    
    /**
     * 
     */
    public void alusta() {
        //
    }
    
    /**
     * Metodi pelin lisäämistä varten
     */
    protected void lisääHahmo() {
        Hahmo hahmo = new Hahmo();
        hahmo.register();
        hahmo.setName(hahmoTulos.getText());
        try {
            pelikamu.add(hahmo);
        }
        catch (apuException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }

        }
    }

