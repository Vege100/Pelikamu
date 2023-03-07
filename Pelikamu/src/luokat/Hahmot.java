package luokat;

public class Hahmot {
    private static final int MAX_HAHMOJA = 10;
    private Hahmo[] champions = new Hahmo[MAX_HAHMOJA];
    
    
    public Hahmo getChampion(int id) {
        return champions[id];
        
    }
    public Hahmot () {
        
    }
    

    
    private Hahmo[] getList() {
        return champions;
    }
    
    public String getName(int i) {
        return champions[i].toString();
    }
    
}
