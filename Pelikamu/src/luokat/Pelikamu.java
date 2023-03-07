package luokat;

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
         */
        public void add(Peli peli) {
            pelit.add(peli);
        }
        /**
         * Lisää uuden hahmon
         * @param hahmo joka lisätään
         */
        public void add(Hahmo hahmo) {
            // TODO mahdollisesti toista kautta, sillä hahmoja lisätään harvoin ja tarvitsevat ehkä kuvan..
            hahmot.add(hahmo);
        }
        
        public Peli getPeli(int i) {
            return pelit.get(i);
        }
        
        
        
        public List<Peli> givePelit(Hahmo hahmo) {
            return Pelit.annaPelit(hahmo.getId());
        }
        
        public void readFile(String nimi) {
            pelit.readFile();
            hahmot.readFile();
        }
        
        public void save() {
            hahmot.save();
            pelit.save();
        }
        
        public Hahmo getChampion(int id) {
            return hahmot.getChampion(id);
        }
        
        public String getChampionName(int i) {
            return hahmot.getName(i);
        }
        
        //public static void main(String args[]) {
        // TODO mahdolliset testit ja alustus asiakasta varten    
        //}
        
        
        
        
}
