package luokat;

import java.util.Random;

public class Hahmo {
    private String name;
    private int id;
    
    
    @Override 
    public String toString() {
        return name;
    }
    
    public void addRandom() {
        Random r = new Random();
        if (r.nextInt(2) > 1) name = "teemo";
        else name = "annie"; 
        id = r.nextInt(2);
    }
    
    public int getId() {
        return id;
        
    }
} //Toimiiko?
