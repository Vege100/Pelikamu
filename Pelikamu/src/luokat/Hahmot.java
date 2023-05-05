package luokat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Verneri
 * @version 14 Mar 2023
 * Luokka hahmoja varten
 */
public class Hahmot implements Iterable<Hahmo> {
    
    private final Collection<Hahmo> champions        = new ArrayList<Hahmo>();
    private String tiedostonPerusNimi = "hahmot";
    private boolean muutettu = false;

    
    
    /**
     * ei alustusta
     */
    public Hahmot() {
        //
    }
    
    /**
     * @param hahmo lisättävä
     * @throws apuException oletus virheilmoitus
     */
    public void add(Hahmo hahmo) throws apuException {
        champions.add(hahmo);
        muutettu = true;
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
     * palauttaa hahmojen lukumäärän
     * @return määrä
     */
    public int getLkm() {
        return champions.size();
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
        return getChampion(i).getName();
    }
    
    
    /**
     * @param tied tiedosto josta luetaan
     * @throws apuException virheilmoitus
     */
    public void lueTiedostosta(String tied) throws apuException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Hahmo hah = new Hahmo();
                hah.parse(rivi); // voisi olla virhekäsittely
                add(hah);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new apuException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new apuException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }

    
    
    /**
     * ohjaa perus lukemisen tiedostosta
     * @throws apuException virheilmoitus
     */
    public void lueTiedostosta() throws apuException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    
    /**
     * tallentaa
     * @throws apuException virheilmoitus
     */
    public void tallenna() throws apuException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); //  if ... System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); //  if ... System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Hahmo har : this) {
                fo.println(har.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new apuException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new apuException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    /**
     * @param hakuehto ehdot: ei käytössä
     * @param k parametri mistä luokasta etsitään
     * @return loytyneet hahmot
     */
    @SuppressWarnings("unused")
    public Collection<Hahmo> etsi(String hakuehto, int k) { 
        Collection<Hahmo> loytyneet = new ArrayList<Hahmo>(); 
        for (Hahmo Hahmo : this) { 
            loytyneet.add(Hahmo);  
        } 
        return loytyneet; 
    }


    
    
    /**
     * Palauttaa perus tiedostonnimen
     * @return tiedostonnimen
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param nimi tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String nimi) {
        tiedostonPerusNimi = nimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return getTiedostonPerusNimi() + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    





    /**
     * Palautetaan iteraattori jäsenistään.
     * @return jäsen iteraattori
     */
    @Override
    public Iterator<Hahmo> iterator() {
        return champions.iterator();
    }

    
}
