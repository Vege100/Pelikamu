package luokat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Verneri
 * @version 14 Mar 2023
 * Luokka hahmoja varten
 */
public class Hahmot {
    
    private final Collection<Hahmo> champions        = new ArrayList<Hahmo>();
    
    
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
        champions.add(hahmo);
    }
    
    /**
     * Palauttaa viimeisimmän hahmon olemassa olevasta listasta
     * TODO joku muu kuin silmukka jos hahmoja paljon
     * @return viimeisin hahmo
     */
    public Hahmo getLast() {
        Hahmo hah = new Hahmo();
        for (Hahmo hahmo : champions)
            hah = hahmo;
        return hah;
    }
    
    /**
     * Ohjelma hahmojen listaamista varten
     * @return hahmot listana
     */
    public List<Hahmo> getList() {
        return new ArrayList<>(champions);
    }
    



    
    /**
     * @param id alkio
     * @return hahmon nimen
     */
    public Hahmo getChampion(int id) {
        Hahmo found = new Hahmo();
        for (Hahmo hahmo : champions)
            if (hahmo.getId() == id) found = hahmo;
        return found;
    }
    
    /**
     * Palauttaa hahmon nimen, kutsuu ohjelmaa getChampion.
     * @param i hahmon id
     * @return hahmon nimi
     */ 
    public String getChampionName(int i) {
        return getChampion(i).toString();
    }


    
}
