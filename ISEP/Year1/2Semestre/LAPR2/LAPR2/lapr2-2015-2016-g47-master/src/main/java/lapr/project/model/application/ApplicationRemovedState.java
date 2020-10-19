/**
 * Package location for Model related to application.
 */
package lapr.project.model.application;

import java.io.Serializable;
import lapr.project.model.Application;
import lapr.project.model.ApplicationState;

/**
 * Represents the application in removable state.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ApplicationRemovedState implements ApplicationState, Serializable {

    /**
     * the application of the removable.
     */
    private final Application application;

    /**
     * Constructs a application in attribution state.
     *
     * @param application application to be manipulated
     */
    public ApplicationRemovedState(Application application) {
        this.application = application;
    }

    /**
     * Returns false of application being on IntialState
     *
     * @return false
     */
    @Override
    public boolean isInitial() {
        return false;
    }

    /**
     * Changes the current application state to in submission state.
     *
     * @return true if the state is changed to in submission state, false
     * otherwise
     */
    @Override
    public boolean setInSubmission() {
        return false;
    }

    /**
     * Returns false of application being inSubmissionState
     *
     * @return false
     */
    @Override
    public boolean isInSubmission() {
        return false;
    }

    /**
     * Changes the current application state to removed state.
     *
     * @return true if the state is changed to removed state, false otherwise
     */
    @Override
    public boolean setRemoved() {
        return false;
    }

    /**
     * Returns false of application being inRemovedState
     *
     * @return false
     */
    @Override
    public boolean isRemoved() {
        return true;
    }

    /**
     * Changes the current application state to in attribution state.
     *
     * @return true if the state is changed to in attribution state, false
     * otherwise
     */
    @Override
    public boolean setInAttribution() {
        return false;
    }

    /**
     * Returns true of application being inAttributionState
     *
     * @return true
     */
    @Override
    public boolean isInAttribtion() {
        return false;
    }

    /**
     * Changes the current application state to in evaluation state.
     *
     * @return true if the state is changed to in evaluation state, false
     * otherwise
     */
    @Override
    public boolean setInEvaluation() {
        return false;
    }

    /**
     * Returns false of application being inEvaluationState
     *
     * @return false
     */
    @Override
    public boolean isInEvaluation() {
        return false;
    }

    /**
     * Changes the current application state to not evaluated state.
     *
     * @return true if the state is changed to not evaluated state, false
     * otherwise
     */
    @Override
    public boolean setNotEvaluated() {
        return false;
    }

    /**
     * Returns false of application being inNotEvaluatedState
     *
     * @return false
     */
    @Override
    public boolean isNotEvaluated() {
        return false;
    }

    /**
     * Changes the current application state to in decision state.
     *
     * @return true if the state is changed to in decision state, false
     * otherwise
     */
    @Override
    public boolean setInDecision() {
        return false;
    }

    /**
     * Returns false of application being inDecisionState
     *
     * @return false
     */
    @Override
    public boolean isInDecision() {
        return false;
    }

    /**
     * Changes the current application state to accepted state.
     *
     * @return true if the state is changed to accepeted state, false otherwise
     */
    @Override
    public boolean setAccepted() {
        return false;
    }

    /**
     * Returns false of application being inAcceptedState
     *
     * @return false
     */
    @Override
    public boolean isAccepted() {
        return false;
    }

    /**
     * Changes the current application state to declined state.
     *
     * @return true if the state is changed to declined state, false otherwise
     */
    @Override
    public boolean setDeclined() {
        return false;
    }

    /**
     * Returns false of application being inDelinedState
     *
     * @return false
     */
    @Override
    public boolean isDeclined() {
        return false;
    }

    /**
     * Changes the current application state to assigned stand state.
     *
     * @return true if the state is changed to assigned stand state, false
     * otherwise
     */
    @Override
    public boolean setAssignedStand() {
        return false;
    }

    /**
     * Returns false of application being inAssignedStandState
     *
     * @return false
     */
    @Override
    public boolean isAssignedStand() {
        return false;
    }

    /**
     * Changes the current application state to confirmed stand state.
     *
     * @return true if the state is changed to confirmed stand state, false
     * otherwise
     */
    @Override
    public boolean setConfirmedStand() {
        return false;
    }

    /**
     * Returns false of application being inConfirmedStand state
     *
     * @return false
     */
    @Override
    public boolean isConfirmedStand() {
        return false;
    }

    /**
     * Changes the current application state to not confirmed stand state.
     *
     * @return true if the state is changed to not confirmed stand state, false
     * otherwise
     */
    @Override
    public boolean setNotConfirmedStand() {
        return false;
    }

    /**
     * Returns false of application being in notConfirmedStand State
     *
     * @return false
     */
    @Override
    public boolean isNotConfirmedStand() {
        return false;
    }

    /**
     * Returns false, that is no next state for application
     *
     * @return false because that is no next state for application
     */
    @Override
    public boolean validate() {
        return false;
    }
}
