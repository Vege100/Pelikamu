package kayttoliittyma;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;

import fi.jyu.mit.fxgui.ModalController;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import luokat.Hahmo;

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
    // @FXML private hahmoListaChooser<String> cbKentat;
    @FXML private ScrollPane panelPelit;  
    @FXML private Pane rootpane;

    @FXML private ListView<Peli> peliSivu;
    @FXML private ListView<Hahmo> hahmotSivu;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }


    @FXML
    void handleHahmot(ActionEvent event) {
        ModalController.showModal(PaaValikkoController.class.getResource("HahmoLisäys.fxml"), "Hahmo", null, pelikamu);
        lisääHahmo();
    }
    @FXML
    void handleLisääPeli(ActionEvent event) {
        ModalController.showModal(PaaValikkoController.class.getResource("PeliLisäys.fxml"), "Peli", null, pelikamu);
        asetaKaikkiPelit();
    }

    @FXML
    void handleLopeta(ActionEvent event) {
        tallenna();
        Platform.exit();
    }


    @FXML
    void handleTulosta(ActionEvent event) {
        ;
    }
    
    @FXML
    void kaikkiPelit(ActionEvent event) {
          asetaKaikkiPelit();
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
   // private TextArea areaPeli = new TextArea();
    private String pelikamunimi = "tallennus";
    

    



    /**
     * Lisää visuaalisesti ohjelmaan lisätyn hahmon
     */
    protected void lisääHahmo() {
        Hahmo hahmo = pelikamu.viimeisinHahmo();
        hahmotSivu.getItems().add(hahmo);
        hahmotSivu.refresh();
    }
    
  


    
    /**
     * Graafinen alustus
     */
    protected void alusta() {
       // panelPelit.setContent(areaPeli);
       // areaPeli.setFont(new Font("Courier New", 12));
       // panelPelit.setFitToHeight(true);
        lueTiedosto("tallennus");
        // Lisää kohteita hahmotSivuun
        hahmotSivu.getItems().addAll(pelikamu.getChampionsListOb());
        peliSivu.setCellFactory(new Callback<ListView<Peli>, ListCell<Peli>>() {
            @Override
            public ListCell<Peli> call(ListView<Peli> listView) {
                return new PeliListCell();
            }
        });
        
        
        for (Peli peli : pelikamu.getAllGames()) {
            peliSivu.getItems().add(peli);
        }
        
        peliSivu.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                Peli selectedItem = peliSivu.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    System.out.println("Valittu peli: " + selectedItem);
                    PeliMuokkausController.avaa(pelikamu, selectedItem);
                }
            }
        });

        // Hae valittu kohde hahmotSivusta
        hahmotSivu.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                asetaHahmonPelit(newValue);
                System.out.println("Valittu hahmo: " + newValue);
        });
        pelikamu.setHahmoCount();
    }
    /**
     * Alustaa pelikamun lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        pelikamunimi = nimi;
        try {
            pelikamu.lueTiedostosta(nimi);
            return null;
        } catch (apuException e) {
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
     }
    
    /**
     * Asettaa näyttöön valitun hahmon pelit.
     * @param hahmo valittu hahmo
     */
    public void asetaHahmonPelit(Hahmo hahmo) {
        peliSivu.getItems().clear();
        peliSivu.refresh();
        for (Peli peli : pelikamu.getAllGamesH(hahmo)) {
            peliSivu.getItems().add(peli);
        }
        peliSivu.refresh();
    }
    
    /**
     * Palauttaa kaikki pelit näyttöön
     */
    public void asetaKaikkiPelit() {
        peliSivu.getItems().clear();
        peliSivu.refresh();
        for (Peli peli : pelikamu.getAllGames()) {
            peliSivu.getItems().add(peli);
        }
        peliSivu.refresh();
    }
    
    /**
     * Tietojen tallennus
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            pelikamu.tallenna();
            return null;
        } catch (apuException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }
    
        
    /**
     * @author Verneri
     * @version 5 May 2023
     * Luokka pelien näyttämistä listcellinä
     */
    public class PeliListCell extends ListCell<Peli> {
        private GridPane gridPane = new GridPane();
        private Text idText = new Text();
        private Text hIdText = new Text();
        private Text winText = new Text();
        private Text killsText = new Text();
        private Text deathsText = new Text();
        private Text assistsText = new Text();
        private Text timeMText = new Text();
        private Text timeSText = new Text();
        private Text gameStyleText = new Text();

        /**
         * Alustus ja muotoilu
         */
        public PeliListCell() {
            gridPane.setHgap(10);
            gridPane.setVgap(5);
            gridPane.setPadding(new Insets(5, 5, 5, 5));
            
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(0);
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(30);
            ColumnConstraints col3 = new ColumnConstraints();
            col3.setPercentWidth(10);
            ColumnConstraints col4 = new ColumnConstraints();
            col4.setPercentWidth(5);
            ColumnConstraints col5 = new ColumnConstraints();
            col5.setPercentWidth(5);
            ColumnConstraints col6 = new ColumnConstraints();
            col6.setPercentWidth(5);
            ColumnConstraints col7 = new ColumnConstraints();
            col7.setPercentWidth(5);
            ColumnConstraints col8 = new ColumnConstraints();
            col8.setPercentWidth(5);
            ColumnConstraints col9 = new ColumnConstraints();
            col9.setPercentWidth(35);

            gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);

            
            gridPane.add(idText, 0, 0);
            gridPane.add(hIdText, 1, 0);
            gridPane.add(winText, 2, 0);
            gridPane.add(killsText, 3, 0);
            gridPane.add(deathsText, 4, 0);
            gridPane.add(assistsText, 5, 0);
            gridPane.add(timeMText, 6, 0);
            gridPane.add(timeSText, 7, 0);
            gridPane.add(gameStyleText, 8, 0);
            setGraphic(gridPane);

            Font boldFont = Font.font("Verdana", FontWeight.BOLD, 12);
            idText.setFont(boldFont);
            hIdText.setFont(boldFont);
            winText.setFont(boldFont);
            killsText.setFont(boldFont);
            deathsText.setFont(boldFont);
            assistsText.setFont(boldFont);
            timeMText.setFont(boldFont);
            timeSText.setFont(boldFont);
            gameStyleText.setFont(boldFont);
            
            idText.setTextAlignment(TextAlignment.CENTER);
            hIdText.setTextAlignment(TextAlignment.CENTER);
            winText.setTextAlignment(TextAlignment.CENTER);
            killsText.setTextAlignment(TextAlignment.CENTER);
            deathsText.setTextAlignment(TextAlignment.CENTER);
            assistsText.setTextAlignment(TextAlignment.CENTER);
            timeMText.setTextAlignment(TextAlignment.CENTER);
            timeSText.setTextAlignment(TextAlignment.CENTER);
            gameStyleText.setTextAlignment(TextAlignment.CENTER);
        }
    
            
        @Override
        protected void updateItem(Peli peli, boolean empty) {
            super.updateItem(peli, empty);

            if (empty || peli == null) {
                setText(null);
                setGraphic(null);
            } else {
                String[] values = peli.toString().split("\\|");

                idText.setText("");
                hIdText.setText(pelikamu.getChampionName(Integer.valueOf(values[1])));
                winText.setText(values[2]);
                killsText.setText(values[3]);
                deathsText.setText(values[4]);
                assistsText.setText(values[5]);
                timeMText.setText(values[6]);
                timeSText.setText(values[7]);
                gameStyleText.setText(values[8]);
                
              
                
                if (values[2].equals("WIN")) {
                    gridPane.setStyle("-fx-background-color: #90EE90;"); // green background color
                } else {
                    gridPane.setStyle("-fx-background-color: #FFA07A;"); // red background color
                }

                
                setGraphic(gridPane);
            }
        }    
    }
}