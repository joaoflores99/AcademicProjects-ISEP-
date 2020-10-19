/**
 * Package location for Model related to application.
 */
package lapr.project.model.application;

import java.io.Serializable;
import lapr.project.model.Application;
import lapr.project.model.ApplicationState;
import lapr.project.model.ExhibitionApplication;

/**
 * Represents the in decided state for application.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationAcceptedState implements ApplicationState, Serializable {

    /**
     * the application to be manipulated.
     */
    private final Application application;

    /**
     * Constructs a application in decided state.
     *
     * @param application application to be manipulated
     */
    public ApplicationAcceptedState(Application application) {
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
    public boolean setAccepted() {
        return false;
    }

    @Override
    public boolean isAccepted() {
        return true;
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
    public boolean setAssignedStand() {
        if (validate()) {
            ApplicationAssignedStandState newState = new ApplicationAssignedStandState(application);
            application.setState(newState);
            return true;
        }
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
        return this.application instanceof ExhibitionApplication ? ((ExhibitionApplication) application).validateStand() : false;
    }

}
