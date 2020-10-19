/**
 * Package location for Model and concepts.
 */
package lapr.project.model.demonstration;

import java.io.Serializable;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationState;

/**
 * Represents the changed conflicts state of a demonstration.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DemonstrationChangedConflictsState implements DemonstrationState, Serializable {

    /**
     * The demonstration to change state.
     */
    private final Demonstration demonstration;

    /**
     * Default constructor of an demonstration's changed conflicts state.
     *
     * @param demonstration Demonstration to change state
     */
    public DemonstrationChangedConflictsState(Demonstration demonstration) {

        this.demonstration = demonstration;
    }

    @Override
    public boolean isInicial() {
        return false;
    }

    @Override
    public boolean isCreated() {
        return false;
    }

    @Override
    public boolean setCreated() {
        return false;
    }

    @Override
    public boolean isDiscontinued() {
        return false;
    }

    @Override
    public boolean setDiscontinued() {
        return false;
    }

    @Override
    public boolean isDecided() {
        return false;
    }

    @Override
    public boolean setDecided() {
        return false;
    }

    @Override
    public boolean isOpenedApplications() {
        return false;
    }

    @Override
    public boolean setOpenApplications() {
        return false;
    }

    @Override
    public boolean isClosedApplications() {
        return false;
    }

    @Override
    public boolean setClosedApplications() {
        return false;
    }

    @Override
    public boolean isDetectedConflicts() {
        return false;
    }

    @Override
    public boolean setDetectedConflicts() {
        return false;
    }

    @Override
    public boolean isChangedConflicts() {
        return true;
    }

    @Override
    public boolean setChangedConflicts() {
        return false;
    }

    @Override
    public boolean isApplicationsInEvaluation() {
        return false;
    }

    @Override
    public boolean setApplicationsInEvaluation() {
        if (validate()) {
            demonstration.setCurrentState(new DemonstrationApplicationsInEvaluationState(demonstration));
            return true;
        }
        return false;
    }

    @Override
    public boolean isApplicationsInDecision() {
        return false;
    }

    @Override
    public boolean setApplicationsInDecision() {
        return false;
    }

    @Override
    public boolean isApplicationsDecided() {
        return false;
    }

    @Override
    public boolean setApplicationsDecided() {
        return false;
    }

    /**
     * Returns false, there is no next state for the demonstration.
     *
     * @return false because there is no next state for the demonstration
     */
    @Override
    public boolean validate() {

        return !demonstration.getStaffAttributionsList().getStaffAttributionsList().isEmpty();
    }
}
