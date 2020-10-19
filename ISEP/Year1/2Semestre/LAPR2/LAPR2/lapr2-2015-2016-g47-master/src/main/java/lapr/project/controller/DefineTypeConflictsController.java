/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ConflictType;
import lapr.project.model.ConflictTypesRegister;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;

/**
 * Represents the controller to define type conflicts.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineTypeConflictsController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * THe exhibitions manager.
     */
    private final ExhibitionsManager exhibitionsManager;

    /**
     * The conflict type.
     */
    private ConflictType conflictType;

    /**
     * The conflict Types Register.
     */
    private ConflictTypesRegister conflictTypesRegister;

    /**
     * The conflict types list.
     */
    private List<ConflictType> conflictTypesList;

    /**
     * Constructs a DefineTypeConflictsController Class.
     *
     * @param exhibitionCenter Exhibition Center
     * @param exhibitionsManger Exhibitions Manager
     */
    public DefineTypeConflictsController(ExhibitionCenter exhibitionCenter, ExhibitionsManager exhibitionsManger) {

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager = exhibitionsManger;
        this.conflictTypesRegister = this.exhibitionCenter.getConflictTypesRegister();
        this.conflictTypesList = this.conflictTypesRegister.getConflictTypesList();
    }

    /**
     * Gets the conflict types list.
     *
     * @return the conflict types list.
     */
    public List<ConflictType> getConflictTypesList() {
        return this.conflictTypesRegister.getConflictTypesList();
    }

    /**
     * Creates new conflict type and return the new conflict type
     *
     */
    public boolean newConflictType() {
        this.conflictType = this.conflictTypesRegister.newConflictType();
        return this.conflictType != null;
    }

    /**
     * Set the description to conflict type
     *
     * @param description the description passed as parameter to set to the
     * conflict type
     */
    public boolean setDataConflictType(String description) {
        this.conflictType.setDescription(description);
        return this.conflictType.validate();
    }

    /**
     * It return true if the conflict type is validated, false otherwise
     *
     * @return return true if the conflict type is validated, false otherwise
     */
    private boolean validateData() {
        return this.conflictTypesRegister.validateTypeConflict(this.conflictType);
    }

    /**
     * Registers the new conflict type
     *
     * @return
     */
    public boolean registerTypeConflict() {
        if (validateData()){
            return this.conflictTypesRegister.registerTypeConflict(this.conflictType);
        }
        return false;
    }

}
