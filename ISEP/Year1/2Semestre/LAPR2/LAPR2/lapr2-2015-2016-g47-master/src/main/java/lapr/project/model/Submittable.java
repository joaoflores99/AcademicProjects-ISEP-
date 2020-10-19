/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.List;

/**
 * Interface for submittable applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Submittable extends Selectable {

    /**
     * Returns the staff attributions list.
     *
     * @return staff attributions list
     */
    StaffAttributionsList getStaffAttributionsList();

    /**
     * Return the applications list.
     *
     * @return applications list
     */
    ApplicationsList getApplicationsList();

    /**
     * Returns the staff list
     *
     * @return staff list
     */
    StaffList getStaffList();

    /**
     * Obtain the conflicts list
     *
     * @return the conflicts list
     */
    ConflictsList getConflictsList();

    /**
     * Returns a short version info of the submittable.
     *
     * @return a short representation
     */
    @Override
    String getDisplayInfo();
    
    /**
     * Returns the name of the submittable.
     *
     * @return the name of the submittable.
     */
    String getName();

    String[] getInfo();

    /**
     * Obtain the conflicts list filtered by staff member.
     *
     * @param staffMember the staff member used to filter the conflicts from
     * conflicts list.
     * @return the conflicts list filtered by staff member.
     */
    List<Conflict> getConflictListByStaffMember(StaffMember staffMember);

    /**
     * Set staff attributions list
     *
     * @param staffAttributionsList the new staff attributions list
     */
    void setStaffAttributionsList(StaffAttributionsList staffAttributionsList);

    /**
     * Set submittable to InApplicationsInEvaluation state
     */
    void setSubmittableInApplicationsInEvaluationState();

    /**
     * Set submittable to open Applications State.
     *
     * @return true if submittable changes state to open Applications State
     */
    boolean setOpenApplications();

    /**
     * Set submittable to Closed Applications State.
     *
     * @return true if submittable changes state to Closed Applications State
     */
    boolean setClosedApplications();

    /**
     * Set submittable to Detected Conflicts State.
     *
     * @return true if submittable changes state to Detected Conflicts State
     */
    boolean setDetectedConflicts();

    /**
     * Set submittable to Changed Conflicts State.
     *
     * @return true if submittable changes state to Changed Conflicts State
     */
    boolean setChangedConflicts();

    /**
     * Set submittable to Applications in Decison State.
     *
     * @return true if submittable changes state to Applications in Decison
     * State
     */
    boolean setApplicationsInDecision();

    /**
     * Remove a staff attribution.
     *
     * @param staffAttribution staff attribution
     * @return true if it is removed with successfull, false otherwise
     */
    boolean removeAttribution(StaffAttribution staffAttribution);
    
    /**
     * Returns true if the submittable is a demonstration, false otherwise.
     * 
     * @return true if the submittable is a demonstration, false otherwise
     */
    boolean isDemonstration();
    
    /**
     * Returns true if the submittable is an exhibition, false otherwise.
     * 
     * @return true if the submittable is an exhibition, false otherwise
     */
    boolean isExhibition();
}
