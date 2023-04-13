package luokat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    
    private String tiedostonPerusNimi = "";
    private boolean muutettu = false;


    
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
        alkiot[lkm++] = peli;
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
     * @param tied mistä luetaan
     * @throws apuException apu
     */
    public void lueTiedostosta(String tied) throws apuException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Peli pel = new Peli();
                pel.parse(rivi); // voisi olla virhekäsittely
                add(pel);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new apuException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new apuException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    public Peli last() {
        return alkiot[lkm-1];
    }

    
    /**
     * Tallentaa jäsenistön tiedostoon.  
     * TODO Kesken.
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws apuException jos tulee poikkeus
     */
    public void lueTiedostosta() throws apuException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    /**
     * 
     * @throws apuException apuheitto
     */
    public void tallenna() throws apuException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); //  if ... System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); //  if ... System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Peli pel : alkiot) {
                fo.println(pel.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new apuException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new apuException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    /**
     * Asettaa tiedoston perusnimen ilan tarkenninta
     * @param tied tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonPerusNimi = tied;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonPerusNimi + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
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


