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
    private String              tiedostonNimi   = "";
    private static Peli[]       alkiot          = new Peli[MAX_PELEJA];

    
    /**
     * Oletusmuodostaja
     */
    public Pelit() {
        
    }
    
    /**
     * @param peli 
     * @throws apuException  
     */
    public void add(Peli peli) throws apuException {
        alkiot[lkm] = peli;
        lkm++;
    }
    
    public Peli get(int i) {
        return alkiot[i];
    }
    
    public void readFile(String hakemisto) {
        tiedostonNimi = hakemisto + "/pelit.dat";
        
    }
    
    public void save() {
        ;
    }
    
    public static List<Peli> annaPelit(int hahmoId) {
        List<Peli> lista = new ArrayList<Peli>();
        for (Peli peli: alkiot) {
            if (peli == null) return lista;
            if (peli.getHId() == hahmoId) lista.add(peli);
        }
        return lista;
    }
    
    public int getLkm() {
        return lkm;
    }
    
    
    
    
   
}


