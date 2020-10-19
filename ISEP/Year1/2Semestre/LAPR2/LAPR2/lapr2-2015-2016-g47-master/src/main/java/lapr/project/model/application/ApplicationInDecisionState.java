/**
 * Package location for Model related to application.
 */
package lapr.project.model.application;

import java.io.Serializable;
import lapr.project.model.Application;
import lapr.project.model.ApplicationState;

/**
 * Represents the in decision state for application.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationInDecisionState implements ApplicationState, Serializable {

    /**
     * The application to be manipulated.
     */
    private final Application application;

    /**
     * Constructs a application in decision state.
     *
     * @param application application to be manipulated
     */
    public ApplicationInDecisionState(Application application) {
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
        return true;
    }

    @Override
    public boolean setAccepted() {
        return false;
    }

    @Override
    public boolean isAccepted() {
        if (validate()) {
            ApplicationAcceptedState newState = new ApplicationAcceptedState(application);
            application.setState(newState);
            return true;
        }
        return false;
    }

    @Override
    public boolean setDeclined() {
        if (validateDeclined()) {
            ApplicationDeclinedState newState = new ApplicationDeclinedState(application);
            application.setState(newState);
            return true;
        }
        return false;
    }

    @Override
    public boolean isDeclined() {
        return false;
    }

    @Override
    public boolean setAssignedStand() {
        return false;
    }

    @Override
    public boolean isAssignedStand() {
        return false;
    }

    @Override
    public boolean setConfirmedStand() {
        return false;
    }

    @Override
    public boolean isConfirmedStand() {
        return false;
    }

    @Override
    public boolean setNotConfirmedStand() {
        return false;
    }

    @Override
    public boolean isNotConfirmedStand() {
        return false;
    }

    @Override
    public boolean validate() {
        // TODO
        return true;
    }

    /**
     * Validate if the application can switch to declined mode.
     *
     * @return true if it does, false otherwise
     */
    public boolean validateDeclined() {
        // TODO
        return true;
    }

}
