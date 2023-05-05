package kayttoliittyma;



import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import luokat.Hahmo;

import luokat.Peli;
import luokat.Pelikamu;
import luokat.apuException;

/**
 * @author Verneri
 * @version 5 May 2023
 * Luokka pelin lisäystä varten
 */
public class PeliLisäysController implements ModalControllerInterface<Pelikamu> {
    

    @FXML TextField aTulos;
    @FXML TextField dTulos;
    @FXML TextField kTulos;
    @FXML TextField minutes;
    @FXML TextField seconds;
    @FXML private ChoiceBox<String> pelityyliChoice;
    @FXML private ChoiceBox<Hahmo> hahmoChoice;
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
        if (hahmoChoice.getSelectionModel().getSelectedItem() == null) {
            Dialogs.showMessageDialog("Lisää hahmo");
            return;
        }
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
    private final ObservableList<String> tulokset = FXCollections.observableArrayList();
    private final ObservableList<String> pelityyli = FXCollections.observableArrayList();
    private Pelikamu pelikamu;
    
    /**
     * Alustaa "laatikot"
     */
    public void alusta() {
        for (Hahmo hahmo : pelikamu.getChampionsList())
        {
            alkiot.add(hahmo);
        }
        hahmoChoice.setItems(alkiot);
        tulokset.add("WIN");
        tulokset.add("LOST");
        tulokset.add("REMAKE");
        tulosChoice.setItems(tulokset);
        pelityyli.add("RANKED");
        pelityyli.add("ARAM");
        pelityyli.add("NORMAL");
        pelityyliChoice.setItems(pelityyli);
    }
    
    /**
     * Metodi pelin lisäämistä varten
     */
    protected void lisääPeli() {
        Peli uusi = new Peli();
        uusi.register();
        StringBuffer sb = new StringBuffer();
        if (hahmoChoice.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        sb.append(hahmoChoice.getSelectionModel().getSelectedItem().getId());sb.append("|");
        sb.append(tulosChoice.getSelectionModel().getSelectedItem());sb.append("|");
        sb.append(kTulos.getText());sb.append("|");
        sb.append(dTulos.getText());sb.append("|");
        sb.append(aTulos.getText());sb.append("|");
        sb.append(minutes.getText());sb.append("|");
        sb.append(seconds.getText());sb.append("|");
        sb.append(pelityyliChoice.getSelectionModel().getSelectedItem());
        String s = sb.toString();
        uusi.aseta(s);
        try {
            pelikamu.add(uusi);            
        }
        catch (apuException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;

        }
    }
}
