/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Removable;

/**
 * Represents the controller to remove applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class RemoveApplicationController {
    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;
    
    /**
     * The exhibitor responsible
     */
    private final ExhibitorResponsible exhibitorResponsible;
    
    /**
     * The removables list
     */
    private List<Removable> removablesList;

    /**
     * Contructs a RemovableApplicationController class.
     * @param exhibitionCenter Exhibition Center
     * @param exhibitorResponsible exhibitor responsible
     */
    public RemoveApplicationController(ExhibitionCenter exhibitionCenter, ExhibitorResponsible exhibitorResponsible) {
        this.exhibitionCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
    }
    
    /**
     * Gets the removables filtering by an exhibitor responsible and filtering by exhibition states(ExhibitionOpenApplicationsState and/or 
     * @param exhibitorResponsible
     * @return 
     */
    public List<Removable> getRemovables(ExhibitorResponsible exhibitorResponsible){
        this.removablesList =  this.exhibitionCenter.getExhibitionsRegister().getRemovables(exhibitorResponsible);
        List<Removable> removablesListTmp = new ArrayList();
        for (Removable removable1 : removablesList) {
            if (!removable1.isRemoved()) {
                removablesListTmp.add(removable1);
            }
        }
        return removablesListTmp;
    }
    
    /**
     * It sets removable state to the removable received from the parameter
     * @param removable the removable to set the removable state
     * @return returns true if the removable changes his state to setInRemoved and it is in the removables list, false otherwise
     */
    public boolean remove(Removable removable){
        for (Removable removable1 : removablesList) {
            if (removable.equals(removable1)) {
                removable1.setInRemoved();
                return true;
            }
        }       
        return false;
    }
    
    
}
