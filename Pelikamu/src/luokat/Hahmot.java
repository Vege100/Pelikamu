package luokat;

import java.util.Arrays;
import java.util.List;

/**
 * @author Verneri
 * @version 14 Mar 2023
 * Luokka hahmoja varten
 */
public class Hahmot {
    private static final int MAX_HAHMOJA = 10;
    private Hahmo[] champions = new Hahmo[MAX_HAHMOJA];
    private int lkm = 0;
    
    
    /**
     * @param id hahmon id
     * @return halutun hahmon
     */
    public Hahmo getChampion(int id) {
        return champions[id];
        
    }
    /**
     * alustus
     */
    public Hahmot() {
        // TODO alustaminen tiedostosta
    }
    
    /**
     * @param hahmo lisättävä
     * @throws apuException oletus virheilmoitus
     */
    public void add(Hahmo hahmo) throws apuException {
        champions[lkm] = hahmo;
        lkm++;
    }
    /**
     * @return palauttaa listana kaikki hahmot hakemista varten
     */
    public List<Hahmo> getAllHahmot() {
        return Arrays.asList(champions);
    }
    

    /**
     * 
     * @return palauttaa hahmot taulukkona
     */
    private Hahmo[] getList() {
        return champions;
    }
    
    /**
     * @param i alkio
     * @return hahmon nimen
     */
    public String getName(int i) {
        return champions[i].toString();
    }
    
    /**
     * Asiakasta varten
     */
    public void lisaaPari() {
        
        champions[0] = new Hahmo();
        champions[1] = new Hahmo();
        champions[0].addRandom();
        champions[1].addRandom();
    }
    
}
