/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Stand;
import lapr.project.model.StandsRegister;

/**
 * Represents the controller to define stand.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineStandController {
    
    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;
    
    /**
     * The stand register.
     */
    private StandsRegister standsRegister;
    
    /**
     * The stand to be defined.
     */
    private Stand stand;
    
    /**
     * Constructs a define stand controller.
     * 
     * @param exhibitionCenter exhibition center
     */
    public DefineStandController(ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
    }
    
    /**
     * Gets the stand list.
     * 
     * @return stand list
     */
    public List<Stand> getStands() {
        this.standsRegister=this.exhibitionCenter.getStandsRegister();
        return this.standsRegister.getStandsList();
    }
          
    /**
     * Creates a new stand with a description, validating the stand.
     * 
     * @param area area for the stand
     * @param description description for the stand
     * @return true if the stand is valid, false otherwise
     */
    public boolean newStand(float area, String description) {
        this.standsRegister = this.exhibitionCenter.getStandsRegister();
        this.stand = this.standsRegister.newStand(area, description);
        return this.stand.validate();
    }
    
    /**
     * Register a stand
     * 
     * @return true if it successful registered, and false otherwise
     */
    public boolean registerStand() {
        return this.standsRegister.registerStand(this.stand);
    }
    
    /**
     * Remove a stand
     * 
     * @param stand stand to be removed
     * @return true is if removed with success, and false otherwise
     */
    public boolean removeStand(Stand stand) {
        return this.standsRegister.removeStand(stand);
    }
    
}
