package luokat;

import 

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
            out.println(String.format("%02d/%02d%%02d",kills,deaths,assists) + timeM + ":" +  timeS +" ");
        }
        
        public int getHId() {
            return hId;
        }
        
        public void perusTeemo() {
            this.hId = 1;
            this.win = false;
            kills = 13;
            deaths = 2;
            assists = 0;
            timeM = 23;
            timeS = 45;
            gameStyle = "RANKED";
            
            
        }
        
        
        
        
        
        

}
