/**
 * Package location for Model related to application.
 */
package lapr.project.model.application;

import java.io.Serializable;
import lapr.project.model.Application;
import lapr.project.model.ApplicationState;
import lapr.project.model.ExhibitionApplication;

/**
 * Represents the assigned stand state for application.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationAssignedStandState implements ApplicationState, Serializable {

    /**
     * The application to be manipulated.
     */
    private final Application application;

    /**
     * Constructs a application assigned stand state.
     *
     * @param application application to be manipulated
     */
    public ApplicationAssignedStandState(Application application) {
        this.application = application;
    }

    @Override
    public boolean isInitial() {
        return false;
    }

    @Override
    public boolean setInSubmission() {
        return false;
    }

    @Override
    public boolean isInSubmission() {
        return false;
    }

    @Override
    public boolean setRemoved() {
        return false;
    }

    @Override
    public boolean isRemoved() {
        return false;
    }

    @Override
    public boolean setInAttribution() {
        return false;
    }

    @Override
    public boolean isInAttribtion() {
        return false;
    }

    @Override
    public boolean setInEvaluation() {
        return false;
    }

    @Override
    public boolean isInEvaluation() {
        return false;
    }

    @Override
    public boolean setNotEvaluated() {
        return false;
    }

    @Override
    public boolean isNotEvaluated() {
        return false;
    }

    @Override
    public boolean setInDecision() {
        return false;
    }

    @Override
    public boolean isInDecision() {
        return false;
    }

    @Override
    public boolean setDeclined() {
        return false;
    }

    @Override
    public boolean isDeclined() {
        return false;
    }

    @Override
    public boolean setAccepted() {
        return false;
    }

    @Override
    public boolean isAccepted() {
        return false;
    }

    @Override
    public boolean setAssignedStand() {
        return false;
    }

    @Override
    public boolean isAssignedStand() {
        return true;
    }

    @Override
    public boolean setConfirmedStand() {
        if (validate()) {

            ApplicationConfirmedStandState newState = new ApplicationConfirmedStandState(application);
            application.setState(newState);
            return true;
        }
        return false;
    }

    @Override
    public boolean isConfirmedStand() {
        return false;
    }

    @Override
    public boolean setNotConfirmedStand() {
        if (validateNotConfirmedStand()) {

            ApplicationNotConfirmedStandState newState = new ApplicationNotConfirmedStandState(application);
            application.setState(newState);
            return true;
        }
        return false;
    }

    @Override
    public boolean isNotConfirmedStand() {
        return false;
    }

    @Override
    public boolean validate() {
        return this.application instanceof ExhibitionApplication ? ((ExhibitionApplication) application).validateStand() : false;
    }

    /**
     * Validate if the applications gathers the conditions to switch to not
     * confirmed stand state.
     *
     * @return true if it does, false otherwise
     */
    public boolean validateNotConfirmedStand() {
        return true;
    }
}
