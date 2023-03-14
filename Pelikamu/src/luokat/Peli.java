package luokat;

import java.io.PrintStream;
import java.util.Random;


public class Peli {
        private int id;
        private int hId;
        private boolean win = false;
        private int kills = 0;
        private int deaths = 0;
        private int assists = 0;
        private int timeM;
        private int timeS;
        private String gameStyle;
        private int idCount = 1;
        
        
        public int register() {
            id = idCount;
            idCount++;
            return id;
        }
        
        public int getId() {
            return id;
        }
    
        public void print(PrintStream out) {
            String oliko = olikoVoitto();
            out.println(String.format("%02d/%02d/%02d",kills,deaths,assists)+ "    " + gameStyle + "    " + String.format("%4s",oliko) + "    "+ String.format("%02d:%02d", timeM, timeS) + "   " + hId);
        }
        
        public int getHId() {
            return hId;
        }
       
        public String olikoVoitto() {
            if (win) return "WIN";
            return "LOST";
        }
        
        public void perusTeemo() {
            
            Random random = new Random();
            hId = random.nextInt(3);
            win = random.nextBoolean();
            kills = random.nextInt(20);
            deaths = random.nextInt(20);
            assists = random.nextInt(20);
            timeM = random.nextInt(40);;
            timeS = random.nextInt(60);;
            gameStyle = "RANKED";
            
            
        }
        
        
        
        
        
        

}
