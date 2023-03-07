package luokat;
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
    private Peli[]              alkiot          = new Peli[MAX_PELEJA];

    
    /**
     * Oletusmuodostaja
     */
    public Pelit() {
        
    }
    
    public void add(Peli peli) {
        alkiot[lkm] = peli;
        lkm++;
    }
    
    public Peli get(int i) {
        return alkiot[i];
    }
    
    public void readFile()
    
    
    /// 
    
}


