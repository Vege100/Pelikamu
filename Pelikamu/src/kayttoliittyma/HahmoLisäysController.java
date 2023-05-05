package kayttoliittyma;


import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import luokat.Hahmo;
import luokat.Pelikamu;
import luokat.apuException;

/**
 * @author Verneri
 * @version 5 May 2023
 * Luokka Hahmon lisäämistä varten
 */
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
     * alustus: TYHJÄ!
     */
    public void alusta() {
        //
    }
    
    /**
     * Metodi hahmon lisäämistä varten
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

