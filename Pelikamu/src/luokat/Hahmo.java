package luokat;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Verneri
 * @version 14 Mar 2023
 * Luokka hahmojen tallentamista varten
 */
public class Hahmo {
    private String name;
    private int id;
    
    private static int nextId = 1;
    
    
    @Override 
    public String toString() {
        return "" + name + "|" + getId();
    }
    
    /**
     * @param i id asettaminen jos manuaalisesti lisätty lista uusista hahmoista
     */
    public static void setCount(int i) {
        nextId = i;
    }
    
    /**
     * metodi hahmon parsimista varten
     * @param rivi teksti mistä luetaan hahmon tiedot
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        name = Mjonot.erota(sb, '|', name);
        id = Mjonot.erota(sb, '|', id);
    }
    
    /**
     * palauttaa hahmon nimen
     * @return hahmon nimi
     */
    public String getName() {
        return name;
    }
    
    /**
     * asettaa hahmon nimen
     * @param s nimi
     */
    public void setName(String s) {
        this.name = s;
    }
    
    /**
     *  Ei alustusta
     */
    public Hahmo() {
        //
    }
    
    
    /**
     * Estää päällekkäisyyksiä
     * @return id palauttaa hahmon id
     */
    public int register() {
        id = nextId;
        nextId++;
        return id;
    }
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setId(int nr) {
        id = nr;
        if (id >= nextId) id = nextId + 1;
    }

    

    /**
     * @return palauttaa id
     */
    public int getId() {
        return id;
        
    }
}
