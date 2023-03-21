package luokat;

import java.util.List;

/**
 * @author Verneri
 * @version 14 Mar 2023
 * Luokka pelikamua ja muiden luokkien jäsentämistä varten
 */
public class Pelikamu {
        private final Hahmot hahmot = new Hahmot();
        private final Pelit pelit = new Pelit();
        
        
        /**
         * Palauttaa pelien määrän
         * @return pelien määrä kokonaislukuna
         */
        public int getPelit() {
            return pelit.getLkm();
        }
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
        
        
        
        /**
         * @param hId Hahmon id
         * @return listan tietyn peleistä
         */
        public List<Peli> givePelit(int hId) {
            return Pelit.annaPelit(hId);
        }
        
        /**
         * TODO tallennus ja luku kesken
         * @param nimi .
         */
        public void readFile(String nimi) {
           //  pelit.readFile();
           // hahmot.readFile();
        }
        
        /**
         * TODO tallennus ja luku kesken
         */
        public void save() {
            // hahmot.save();
            // pelit.save();
        }
        
        /**
         * Palauttaa hahmot listana
         * @return hahmot listana
         */
        public List<Hahmo> getChampionsList() {
            return hahmot.getList();
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
