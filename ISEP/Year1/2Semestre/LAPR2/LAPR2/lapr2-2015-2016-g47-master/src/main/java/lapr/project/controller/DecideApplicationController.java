/**
 * Package location for Decide Application Controller concepts.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Decisable;
import lapr.project.model.Evaluation;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;

/**
 * Represents the controller for a application decision.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DecideApplicationController {

    /**
     * This instance exhibition Center.
     */
    private ExhibitionCenter exhibitionCenter;

    /**
     * This instance's exhibition register.
     */
    private ExhibitionsRegister exhibitionsRegister;

    /**
     * This instance's organizer.
     */
    private Organizer organizer;

    /**
     * This instance's decisable.
     */
    private Decisable decisable;

    /**
     * The list of evaluations of this instance.
     */
    private List<Evaluation> evaluationsList;

    /**
     * Creates instance of this class receiving an organizer and exhibition
     * center as parameters.
     *
     * @param organizer the organizer
     * @param exhibitionCenter the exhibition center
     */
    public DecideApplicationController(Organizer organizer, ExhibitionCenter exhibitionCenter) {
        this.organizer = organizer;
        this.exhibitionCenter = exhibitionCenter;
    }

    /**
     * Returns the list of decisables by organizer.
     *
     * @return the list of decisables
     */
    public List<Decisable> getDecisablesByOrganizer() {
        List<Decisable> decisableList = new ArrayList<>();
        this.exhibitionsRegister = exhibitionCenter.getExhibitionsRegister();
        decisableList = exhibitionsRegister.getDecisableListByOrganizer(organizer);
        return decisableList;

    }

    /**
     * Gets the current decisable.
     *
     * @return the decisable
     */
    public Decisable getDecisable() {
        return decisable;
    }

    /**
     * Sets the current decisable
     *
     * @param decisable the decisable that gets set
     */
    public void setDecisable(Decisable decisable) {
        this.decisable = decisable;
    }

    /**
     * Gets the list of evaluations of a decisable
     *
     * @return the list of evaluations
     */
    public List<Evaluation> getEvaluationsList() {
        this.evaluationsList = this.decisable.getEvaluationsList();
        return this.evaluationsList;
    }

    /**
     * Creates the decision of this decisable.
     */
    public void createDecision() {
        this.decisable.newDecision();

    }

    /**
     * Sets the decision.
     *
     * @param decision boolean representing the decision
     * @param justificativeText text aiding the decision
     */
    public void setDecision(boolean decision, String justificativeText) {
        this.decisable.setDecision(decision, justificativeText);
    }

    /**
     * Validates this decisable.
     *
     * @return true if all OK with decisable
     */
    public boolean validateDecision() {
        return this.decisable.validateDecision();
    }

    public boolean registerDecision() {
        if(validateDecision()){
        return this.decisable.setDecided();
        }
        return false;
    }

}
