package kayttoliittyma;




import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;
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


/**
 * @author Verneri
 * @version 5 May 2023
 * Luokka pelin muokkausta varten
 */
public class PeliMuokkausController implements ModalControllerInterface<Pelikamu> {
    

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
    private static Peli peli;
    
    /**
     * 
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
        
        
        StringBuffer sb = new StringBuffer(peli.toString());
        Mjonot.erota(sb, '|' );
        String hahmoid = Mjonot.erota(sb, '|' );
        String voitto = Mjonot.erota(sb, '|' );
        kTulos.setText(Mjonot.erota(sb, '|'));
        dTulos.setText(Mjonot.erota(sb, '|'));
        aTulos.setText( Mjonot.erota(sb, '|' ));
        minutes.setText(Mjonot.erota(sb, '|' ));
        seconds.setText(Mjonot.erota(sb, '|' ));
        String ab = Mjonot.erota(sb, '|' );
        int i = 0;
        for (String sa : pelityyli) {
            if(ab.equals(sa)) break;
            i++;
        }
        int a = 0;
        Hahmo hahmo = pelikamu.getChampion(Integer.valueOf(hahmoid));
        for (Hahmo sa : alkiot) {
            if(hahmo == sa) break;
            a++;
        }
        int c = 0;
        for (String sa : tulokset) {
            if(voitto.equals(sa)) break;
            c++;
        }
        
        pelityyliChoice.getSelectionModel().select(i);
        hahmoChoice.getSelectionModel().select(a); 
        tulosChoice.getSelectionModel().select(c); 
        
    }
    
    /**
     * Metodi pelin lisäämistä varten
     */
    protected void lisääPeli() {
        StringBuffer sb = new StringBuffer();
        sb.append(hahmoChoice.getSelectionModel().getSelectedItem().getId());sb.append("|");
        sb.append(tulosChoice.getSelectionModel().getSelectedItem());sb.append("|");
        sb.append(kTulos.getText());sb.append("|");
        sb.append(dTulos.getText());sb.append("|");
        sb.append(aTulos.getText());sb.append("|");
        sb.append(minutes.getText());sb.append("|");
        sb.append(seconds.getText());sb.append("|");
        sb.append(pelityyliChoice.getSelectionModel().getSelectedItem());
        String s = sb.toString();
        peli.aseta(s);
    }

    public static void avaa(Pelikamu pelikamu2, Peli newValue) {
        peli = newValue;
        ModalController.showModal(PaaValikkoController.class.getResource("PeliMuokkaus.fxml"), "Peli", null, pelikamu2);
        
    }
}
