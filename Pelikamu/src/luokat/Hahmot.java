package luokat;

import java.util.Arrays;
import java.util.List;

public class Hahmot {
    private static final int MAX_HAHMOJA = 10;
    private Hahmo[] champions = new Hahmo[MAX_HAHMOJA];
    
    
    public Hahmo getChampion(int id) {
        return champions[id];
        
    }
    public Hahmot () {
        
    }
    
    public List<Hahmo> getAllHahmot() {
        return Arrays.asList(champions);
    }
    

    
    private Hahmo[] getList() {
        return champions;
    }
    
    public String getName(int i) {
        return champions[i].toString();
    }
    
    public void lisaaPari() {
        
        champions[0] = new Hahmo();
        champions[1] = new Hahmo();
        champions[0].addRandom();
        champions[1].addRandom();
    }
    
}
