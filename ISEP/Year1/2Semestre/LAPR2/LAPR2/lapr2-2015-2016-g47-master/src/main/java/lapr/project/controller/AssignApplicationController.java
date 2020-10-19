/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.MechanismsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.StaffAttribution;
import lapr.project.model.StaffAttributionMechanism;
import lapr.project.model.StaffAttributionsList;
import lapr.project.model.Submittable;
import lapr.project.model.mechanisms.attribution.EquitableLoadMechanism;

/**
 * Represents the controller to assign applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class AssignApplicationController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The organizer.
     */
    private final Organizer organizer;

    /**
     * The submittable selected.
     */
    private Submittable selectedSubmittable;

    /**
     * The staff attributions list.
     */
    private StaffAttributionsList staffAttributionsList;

    /**
     * The mechanisms register
     */
    private MechanismsRegister mechanismsRegister;

    /**
     * The staff attribution mechanism
     */
    private StaffAttributionMechanism staffAttributionMechanism;

    /**
     * Constructs a AssignApplicationController Class.
     *
     * @param exhibitionCenter Exhibition Center
     * @param organizer Organizer
     *
     */
    public AssignApplicationController(ExhibitionCenter exhibitionCenter, Organizer organizer) {

        this.exhibitionCenter = exhibitionCenter;
        this.organizer = organizer;
    }

    /**
     * Obtain the Exhibition Center.
     *
     * @return the Exhibition Center
     */
    public ExhibitionCenter getExhibitionCenter() {
        return this.exhibitionCenter;
    }

    /**
     * Gets the submittables filtering by an Organizer and InChangedConflicts
     * state.
     *
     * @param organizer organizer to filter submittables
     *
     * @return the list of the organizer's submittables which are
     * InChangedConflicts state
     */
    public List<Submittable> getSubmittablesInChangedConflictsByOrganizer(Organizer organizer) {
        ExhibitionsRegister exhibitionsRegister = this.exhibitionCenter.getExhibitionsRegister();
        return exhibitionsRegister.getSubmittablesInChangedConflictsByOrganizer(organizer);
    }

    /**
     * Set the submittable
     *
     * @param submittable the new submittable
     */
    public void setSubmittable(Submittable submittable) {
        this.selectedSubmittable = submittable;
        this.staffAttributionsList = this.selectedSubmittable.getStaffAttributionsList();
    }

    /**
     * Obtain the staff attributions list of the selected submittable
     *
     * @return the staff attributions list of the selected submittable
     */
    public StaffAttributionsList getStaffAttributionsList() {
        return this.selectedSubmittable.getStaffAttributionsList();
    }

    /**
     * Obtain the list of staff attribution mechanisms
     *
     * @return the list of staff attribution mechanisms
     */
    public List<StaffAttributionMechanism> getStaffAttributionMechanism() {
        this.mechanismsRegister = this.exhibitionCenter.getMechanismsRegister();
//        try {
//            return this.mechanismsRegister.getAttributionMechanismList();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AssignApplicationController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(AssignApplicationController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(AssignApplicationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        EquitableLoadMechanism mechanism = new EquitableLoadMechanism();
        List<StaffAttributionMechanism> attributionMechanisms = new ArrayList<>();
        attributionMechanisms.add(mechanism);
        return attributionMechanisms;
    }

    /**
     * Set the staffAttributionMechanism
     *
     * @param staffAttributionMechanism the new staffAttributionMechanism
     */
    public void setStaffAttributionMechanism(StaffAttributionMechanism staffAttributionMechanism) {
        this.staffAttributionMechanism = staffAttributionMechanism;
    }

    /**
     * Obtain the staff attributions list of the selected submittable
     *
     * @return the staff attributions list of the selected submittable
     */
    public List<StaffAttribution> getAttributionsList() {
        return this.staffAttributionMechanism.toAssign(this.selectedSubmittable);
    }

    /**
     * Saves list of staff attributions
     *
     */
    public void staffAttributionsRegister(List<StaffAttribution> staffAttributionsList) {
        this.selectedSubmittable.setStaffAttributionsList(new StaffAttributionsList(staffAttributionsList));
        setApplicationsInEvaluationState();
        setSubmittableSelectedInApplicationsInEvaluationState();
    }

    /**
     * set applications in evaluation state
     */
    public void setApplicationsInEvaluationState() {
        this.staffAttributionsList.setApplicationsInEvaluationState();
    }

    /**
     * set submittables in applicationsInEvaluation State
     */
    public void setSubmittableSelectedInApplicationsInEvaluationState() {
        this.selectedSubmittable.setSubmittableInApplicationsInEvaluationState();
    }

}
