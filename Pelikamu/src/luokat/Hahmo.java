package luokat;

import java.util.Random;

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
        return name;
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
     * @return palauttaa id
     */
    public int getId() {
        return id;
        
    }
}
