package luokat;

import java.io.PrintStream;
import java.util.Random;

import fi.jyu.mit.ohj2.Mjonot;


/**
 * @author Verneri
 * @version 14 Mar 2023
 *
 */
public class Peli {
        private int     id              ;
        private int     hId             ;
        private String  win         = "";
        private int     kills       = 0 ;
        private int     deaths      = 0 ;
        private int     assists     = 0 ;
        private int     timeM           ;
        private int     timeS           ;
        private String  gameStyle       ;
        private static int     idCount     = 1 ;
        
        
        /**
         * @return varaa paikan ja palauttaa iideen
         */
        public int register() {
            id = idCount;
            idCount++;
            return id;
        }
        
        /**
         * palauttaa pelin id
         * @return pelin id
         */
        public int getId() {
            return id;
        }
        
        /**
         * palauttaa pelin hahmon id
         * @return hahmon id
         */
        public int getHid() {
            return hId;
        }
    
        /**
         * tulostus
         * @param out tulostus mihin tehdään
         */
        public void print(PrintStream out) {
            String oliko = olikoVoitto();
            out.print(String.format("%02d/%02d/%02d",kills,deaths,assists)+ "    " + gameStyle + "    " + String.format("%4s",oliko) + "    "+ String.format("%02d:%02d", timeM, timeS) + "   " + hId);
        }
        
        /**
         * @return palauttaa pelin hahmoId hakua varten
         */
        public int getHId() {
            return hId;
        }
       
        /**
         * @return Palauttaa tulostamista varten voiton tekstinä
         */
        public String olikoVoitto() {
            if (win == "WIN") return "WIN";
            return "LOST";
        }
        
        /**
         * asettaa hahmon id
         * @param i hahmon id
         */
        public void setHahmo (int i) {
            hId = i;
        }
        
        /**
         * Asiakasta varten, näytetään miten ohjelma toimii
         */
        public void perusTeemo() {       
            Random random = new Random();
            win = "WIN";
            kills = random.nextInt(20);
            deaths = random.nextInt(20);
            assists = random.nextInt(20);
            timeM = random.nextInt(40);;
            timeS = random.nextInt(60);;
            gameStyle = "RANKED";  
        }
        
        /**
         * asettaa pelin id
         * @param nr peli id
         */
        private void setTunnusNro(int nr) {
            id = nr;
            if ( id >= idCount ) idCount = id + 1;
        }


        /**
         * Palauttaa pelin tiedot merkkijonona jonka voi tallentaa tiedostoon.
         * @return pelin tolppaeroteltuna merkkijonona 
         * @example
         * <pre name="test">
         *   Peli peli = new Peli();
         *   peli.parse("1|11|WIN|12|2|3|43|33|RANKED");
         *   peli.toString()    === "1|11|WIN|12|2|3|43|33|RANKED";
         * </pre>
         */
        @Override
        public String toString() {
            return "" + 
        
            getId()  + "|"  +
            hId      + "|"  +
            win      + "|"  +
            kills    + "|"  +
            deaths   + "|"  +
            assists  + "|"  +
            timeM    + "|"  +
            timeS    + "|"  +
            gameStyle;
              
        }


        /**
         * Selvitää pelin tiedot | erotellusta merkkijonosta.
         * @param rivi josta pelin tiedot otetaan
         * @example
         * <pre name="test">
         *   Peli peli = new Peli();
         *   peli.parse("1|11|WIN|12|2|3|43|33|RANKED");
         *   peli.getId() === 1;
         *   peli.toString()    === "1|11|WIN|12|2|3|43|33|RANKED";
         * </pre>
         */
        public void parse(String rivi) {
            StringBuffer sb = new StringBuffer(rivi);
            setTunnusNro(Mjonot.erota(sb, '|', getId()));
            hId = Mjonot.erota(sb, '|', hId);
            win = Mjonot.erota(sb, '|', win);
            kills = Mjonot.erota(sb, '|', kills);
            deaths = Mjonot.erota(sb, '|', deaths);
            assists = Mjonot.erota(sb, '|', assists);
            timeM = Mjonot.erota(sb, '|', timeM);
            timeS = Mjonot.erota(sb, '|', timeS);
            gameStyle = Mjonot.erota(sb, '|', gameStyle);
        }
        
        /**
         * asettaa tekstistä pelin "uudelleen"
         * @param rivi teksti mistä asetetaan
         */
        public void aseta(String rivi) {
            StringBuffer sb = new StringBuffer(rivi);
            hId = Mjonot.erota(sb, '|', hId);
            win = Mjonot.erota(sb, '|', win);
            kills = Mjonot.erota(sb, '|', kills);
            deaths = Mjonot.erota(sb, '|', deaths);
            assists = Mjonot.erota(sb, '|', assists);
            timeM = Mjonot.erota(sb, '|', timeM);
            timeS = Mjonot.erota(sb, '|', timeS);
            gameStyle = Mjonot.erota(sb, '|', gameStyle);
        }


        @Override
        public boolean equals(Object obj) {
            if ( obj == null ) return false;
            return this.toString().equals(obj.toString());
        }
      
        

        @Override
        public int hashCode() {
            return id;
        }


        /**
        * Testiohjelma Harrastukselle.
        * @param args ei käytössä
        */
        public static void main(String[] args) {
            Peli har = new Peli();
            har.perusTeemo();

        }
        
        
        
        
        

}
