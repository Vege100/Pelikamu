package luokat;

import java.util.ArrayList;
import java.util.List;

/**
 *  Hallitsee pelejä
 * @author Verneri
 * @version 6 Mar 2023
 *
 */
public class Pelit {
    private static final int    MAX_PELEJA      = 5;
    private int                 lkm             = 0;
    private String              tiedostonNimi   = "Tiedosto/";
    private static Peli[]       alkiot          = new Peli[MAX_PELEJA];

    
    /**
     * Oletusmuodostaja
     */
    public Pelit() {
        
    }
    
    /**
     * @param peli lisää pelin
     * @throws apuException oletus virheilmoitus
     */
    public void add(Peli peli) throws apuException {
        alkiot[lkm] = peli;
        lkm++;
    }
    
    /**
     * @param i alkio minkä peli halutaan
     * @return peli mikä halutaan
     */
    public Peli get(int i) {
        return alkiot[i];
    }
    
    /**
     * TODO koko aliohjelma
     * @param hakemisto Pohjahakemisto tiedoston hakua varten
     */
    public void readFile(String hakemisto) {
        tiedostonNimi = hakemisto + "/pelit.dat";
    }
    
    /**
     * TODO Pelien tallennus tiedostoon
     * Pohja tallennusta varten
     */
    public void save() {
        ;
    }
    
    /**
     * @param hahmoId Käyttöliittymmässä klikkattu hahmoa jonka pelit halutaan näyttää, hahmon id
     * @return tietyn hahmon pelit listana.
     */
    public static List<Peli> annaPelit(int hahmoId) {
        List<Peli> lista = new ArrayList<Peli>();
        for (Peli peli: alkiot) {
            if (peli == null) return lista;
            if (peli.getHId() == hahmoId) lista.add(peli);
        }
        return lista;
    }
    
    /**
     * @return palauttaa pelien lukumäärän
     */
    public int getLkm() {
        return lkm;
    }
    
    
    
    
   
}


