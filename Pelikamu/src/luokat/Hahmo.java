package luokat;

import java.util.Random;

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
    
    public static void setCount(int i) {
        nextId = i;
    }
    
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        name = Mjonot.erota(sb, '|', name);
        id = Mjonot.erota(sb, '|', id);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String s) {
        this.name = s;
    }
    
    /**
     *  Vielä ei tarvitse alustaa
     */

    public Hahmo() {
        //
    }
    
    
    /**
     * Vastaa parametreihin annien tiedot
     */
    public void perusAnnie() {
        name = "annie";
    }
    
    /**
     * Estää päällekkäisyyksiä
     * @return id 
     */
    public int register () {
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
