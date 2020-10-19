/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.Conflict;
import lapr.project.model.ConflictType;
import lapr.project.model.ConflictTypesRegister;
import lapr.project.model.ConflictsList;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.StaffMember;
import lapr.project.model.Submittable;

/**
 * Represents the controller to update conflicts.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UpdateDetectedConflictsController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionsCenter;

    private final StaffMember staffMember;

    /**
     * The exhibitions register.
     */
    private ExhibitionsRegister exhibitionsRegister;

    /**
     * The submittable.
     */
    private Submittable submittable;

    /**
     * The selected submittable.
     */
    private Submittable selectedSubmittable;

    /**
     * The conflicts list
     */
    private ConflictsList conflictsList;

    /**
     * Constructs an update conflicts controller.
     *
     * @param exhibitionCenter exhibition center
     *
     * @param staffMember staff member
     */
    public UpdateDetectedConflictsController(ExhibitionCenter exhibitionCenter, StaffMember staffMember) {
        this.exhibitionsCenter = exhibitionCenter;
        this.staffMember = staffMember;
    }

    /**
     * Set the submittable
     *
     * @param submittable the submittable selected (the new submittable)
     */
    public void setSubmittable(Submittable submittable) {
        this.selectedSubmittable = submittable;
    }

    /**
     * Returns the submittables list filtered by staff member.
     *
     * @return the submittables list filtered by staff member.
     */
    public List<Submittable> getSubmittableListByStaffMember() {
        this.exhibitionsRegister = this.exhibitionsCenter.getExhibitionsRegister();
        return this.exhibitionsRegister.getSubmittablesInDetectedConflictsByStaffMember(this.staffMember);
    }

    /**
     * Returns the conflicts list filtered by staff member and filtered by
     * selected submittable.
     *
     * @return the conflicts list filtered by staff member and filtered by
     * selected submittable.
     */
    public List<Conflict> getConflictsListByStaffMember() {
        return this.selectedSubmittable.getConflictListByStaffMember(this.staffMember);
    }

    /**
     * Returns applications list of the selected submittable.
     *
     * @return applications list of the selected submittable.
     */
    public List<Application> getListApplications() {
        return this.selectedSubmittable.getApplicationsList().getApplicationsList();
    }

    /**
     * Returns the list of conflicts types.
     *
     * @return the list of conflicts types.
     */
    public List<ConflictType> getListConflictType() {
        ConflictTypesRegister conflictTypesRegister = this.exhibitionsCenter.getConflictTypesRegister();
        return conflictTypesRegister.getConflictTypesList();
    }

    /**
     * Returns true if creates new conflict in the conflicts list of the
     * selected submittable, false otherwise.
     *
     * @param application the new application of the new conflict created.
     * @param conflictType the new conflict type of the new conflict created.
     * @return true if creates new conflict in the conflicts list of the
     * selected submittable, false otherwise.
     */
    public boolean newConflict(Application application, ConflictType conflictType) {
        Conflict conflict = new Conflict();
        this.conflictsList = this.selectedSubmittable.getConflictsList();
        conflict = this.conflictsList.newConflict(this.staffMember, conflictType, application);
        if (conflict.validate() && this.conflictsList.validateConflict(conflict)) {
            this.conflictsList.addConflict(conflict);
            return true;
        }
        return false;
    }

    /**
     * Returns true if removes the conflict selected(conflict passed as
     * parameter) in the conflicts list of the selected submittable of the staff
     * member logged in, false otherwise
     *
     * @param conflict the conflict selected(conflict passed as parameter)
     * @return true if removes the conflict selected(conflict passed as
     * parameter) in the conflicts list of the selected submittable of the staff
     * member logged in, false otherwise
     */
    public boolean removeConflict(Conflict conflict) {
        this.conflictsList = this.selectedSubmittable.getConflictsList();
        return this.conflictsList.removeConflict(conflict);
    }

}
