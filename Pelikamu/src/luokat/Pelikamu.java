package luokat;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Verneri
 * @version 14 Mar 2023
 * Luokka pelikamua ja muiden luokkien jäsentämistä varten
 */
public class Pelikamu {
        private  Hahmot hahmot = new Hahmot();
        private  Pelit pelit = new Pelit();
        
        /**
         * Lisää uuden pelin
         * @param peli joka lisätään
         * @throws apuException Oletus virheilmoitus
         */
        public void add(Peli peli) throws apuException {
            pelit.add(peli);
        }
        
        /**
         * Palauttaa viimeiseksi lisätyn hahmon
         * @return viimeisin hahmo
         */
        public Hahmo viimeisinHahmo() {
            return hahmot.getLast();
        }
        /**
         * Lisää uuden hahmon
         * @param hahmo joka lisätään
         * @throws apuException oletus virheilmoitus
         */
        public void add(Hahmo hahmo) throws apuException {
            // TODO mahdollisesti toista kautta, sillä hahmoja lisätään harvoin ja tarvitsevat ehkä kuvan..
            hahmot.add(hahmo);
        }

        /**
         * @param i mikä alkio
         * @return peli mikä halutaan
         */
        public Peli getPeli(int i) {
            return pelit.get(i);
        }
        
        public int getPelit() {
            return pelit.getLkm();
        }
        
        /** 
         * Palauttaa "taulukossa" hakuehtoon vastaavien hahmojen viitteet 
         * @param hakuehto hakuehto  
         * @param k etsittävän kentän indeksi  
         * @return tietorakenteen löytyneistä jäsenistä 
         * @throws apuException Jos jotakin menee väärin
         */ 
        public Collection<Hahmo> etsi(String hakuehto, int k) throws apuException { 
            return hahmot.etsi(hakuehto, k); 
        } 

        /**
         * Asettaa tiedostojen perusnimet
         * @param nimi uusi nimi
         */
        public void setTiedosto(String nimi) {
            File dir = new File(nimi);
            dir.mkdirs();
            String hakemistonNimi = "";
            if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
            hahmot.setTiedostonPerusNimi(hakemistonNimi + "hahmot");
            pelit.setTiedostonPerusNimi(hakemistonNimi + "pelit");
        }
        
        public void lueTiedostosta(String nimi) throws apuException {
            hahmot = new Hahmot(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
            pelit = new Pelit();

            setTiedosto(nimi);
            hahmot.lueTiedostosta();
            pelit.lueTiedostosta();
        }
        
        
        public void tallenna() throws apuException {
            String virhe = "";
            try {
                hahmot.tallenna();
            } catch ( apuException ex ) {
                virhe = ex.getMessage();
            }

            try {
                pelit.tallenna();
            } catch ( apuException ex ) {
                virhe += ex.getMessage();
            }
            if ( !"".equals(virhe) ) throw new apuException(virhe);
        }


        

        
        
        /**
         * @param hId Hahmon id
         * @return listan tietyn peleistä
         */
        public List<Peli> givePelit(int hId) {
            return Pelit.annaPelit(hId);
        }
        
        public Peli getLastGame() {
            return pelit.last();
        }

        /**
         * Palauttaa hahmot listana
         * @return hahmot listana
         */
        public List<Hahmo> getChampionsList() {
            return hahmot.getList();
        }
        public ObservableList<Hahmo> getChampionsListOb(){
            ObservableList<Hahmo> alkiot = FXCollections.observableArrayList();
            for (Hahmo hahmo : getChampionsList())
            {
                alkiot.add(hahmo);
            }
            return alkiot;
        }
         
        /**
         * @param id hahmon id
         * @return halutun hahmon
         */
        public Hahmo getChampion(int id) {
            return hahmot.getChampion(id);
        }
        
        /**
         * @param i hahmoille
         * @return Stringinä hahmon nimi
         */
        public String getChampionName(int i) {
            return hahmot.getChampionName(i);
        }
        
        //public static void main(String args[]) {
        // TODO mahdolliset testit ja alustus asiakasta varten    
        //}
        
        
        
        
}
