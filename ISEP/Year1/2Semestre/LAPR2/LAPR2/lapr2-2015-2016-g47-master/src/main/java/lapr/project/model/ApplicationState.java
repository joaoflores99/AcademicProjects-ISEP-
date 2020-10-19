/**
 * Package location for Model concepts.
 */
package lapr.project.model;

/**
 * Represents the application state.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface ApplicationState {

    /**
     * Verify if the current application state is initial state.
     *
     * @return true if the application state is initial state, false otherwise
     */
    boolean isInitial();

    /**
     * Changes the current application state to in submission state.
     *
     * @return true if the state is changed to in submission state, false
     * otherwise
     */
    boolean setInSubmission();

    /**
     * Verify if the current application state is in submission state.1
     *
     * @return true if the application state is in submission state, false
     * otherwise
     */
    boolean isInSubmission();

    /**
     * Changes the current application state to removed state.
     *
     * @return true if the state is changed to removed state, false otherwise
     */
    boolean setRemoved();

    /**
     * Verify if the current application state is removed state.
     *
     * @return true if the application state is removed state, false otherwise
     */
    boolean isRemoved();

    /**
     * Changes the current application state to in attribution state.
     *
     * @return true if the state is changed to in attribution state, false
     * otherwise
     */
    boolean setInAttribution();

    /**
     * Verify if the current application state is in attribution state.
     *
     * @return true if the application state is in attribution state, false
     * otherwise
     */
    boolean isInAttribtion();

    /**
     * Changes the current application state to in evaluation state.
     *
     * @return true if the state is changed to in evaluation state, false
     * otherwise
     */
    boolean setInEvaluation();

    /**
     * Verify if the current application state is in evaluation state.
     *
     * @return true if the application state is in evaluation state, false
     * otherwise
     */
    boolean isInEvaluation();

    /**
     * Changes the current application state to not evaluated state.
     *
     * @return true if the state is changed to not evaluated state, false
     * otherwise
     */
    boolean setNotEvaluated();

    /**
     * Verify if the current application state is not evaluated state.
     *
     * @return true if the application state is not evaluated state, false
     * otherwise
     */
    boolean isNotEvaluated();

    /**
     * Changes the current application state to in decision state.
     *
     * @return true if the state is changed to in decision state, false
     * otherwise
     */
    boolean setInDecision();

    /**
     * Verify if the current application state is in decision state.
     *
     * @return true if the application state is in decision state, false
     * otherwise
     */
    boolean isInDecision();

    /**
     * Changes the current application state to declined state.
     *
     * @return true if the state is changed to declined state, false otherwise
     */
    boolean setDeclined();

    /**
     * Verify if the current application state is declined state.
     *
     * @return true if the application state is declined state, false otherwise
     */
    boolean isDeclined();

    /**
     * Changes the current application state to accepted state.
     *
     * @return true if the state is changed to accepeted state, false otherwise
     */
    boolean setAccepted();

    /**
     * Verify if the current application state is accepted state.
     *
     * @return true if the application state is accepted state, false otherwise
     */
    boolean isAccepted();

    /**
     * Changes the current application state to assigned stand state.
     *
     * @return true if the state is changed to assigned stand state, false
     * otherwise
     */
    boolean setAssignedStand();

    /**
     * Verify if the current application state is assigned stand state.
     *
     * @return true if the application state is assigned stand state, false
     * otherwise
     */
    boolean isAssignedStand();

    /**
     * Changes the current application state to confirmed stand state.
     *
     * @return true if the state is changed to confirmed stand state, false
     * otherwise
     */
    boolean setConfirmedStand();

    /**
     * Verify if the current application state is confirmed stand state.
     *
     * @return true if the application state is confirmed stand state, false
     * otherwise
     */
    boolean isConfirmedStand();

    /**
     * Changes the current application state to not confirmed stand state.
     *
     * @return true if the state is changed to not confirmed stand state, false
     * otherwise
     */
    boolean setNotConfirmedStand();

    /**
     * Verify if the current application state is not confirmed stand state.
     *
     * @return true if the application state is not confirmed stand state, false
     * otherwise
     */
    boolean isNotConfirmedStand();

    /**
     * Validate if the the state gathers all necessary information to transit to
     * next state.
     *
     * @return true if the state gathers the information to transit, false
     * otherwise
     */
    boolean validate();
}
