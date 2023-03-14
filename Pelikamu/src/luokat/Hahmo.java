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
    
    
    @Override 
    public String toString() {
        return name;
    }
    
    /**
     * Asiakasta varten random hahmo
     */
    public void addRandom() {
        Random r = new Random();
        if (r.nextInt(2) > 0) name = "teemo";
        else name = "annie"; 
        id = r.nextInt(2);
    }
    
    /**
     * @return palauttaa id
     */
    public int getId() {
        return id;
        
    }
} //Toimiiko?
