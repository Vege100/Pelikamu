package luokat;

/**
 * @author Verneri
 * @version 14 Mar 2023
 *
 */
public class apuException extends Exception{
    private static final long serialVersionUID = 1L;


    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public apuException(String viesti) {
        super(viesti);
    }

}
