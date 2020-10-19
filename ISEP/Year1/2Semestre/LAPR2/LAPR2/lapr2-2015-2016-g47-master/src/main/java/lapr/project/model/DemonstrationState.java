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
public interface DemonstrationState {

    /**
     * Verify if the demonstration is in the initial state.
     *
     * @return true if the demonstration is in the initial state, false
     * otherwise
     */
    boolean isInicial();

    /**
     * Verify if the demonstration is in the created state.
     *
     * @return true if the demonstration is in the created state, false
     * otherwise
     */
    boolean isCreated();

    /**
     * Changes the current demonstration state to created state.
     *
     * @return true if the state is changed to created state, false otherwise
     */
    boolean setCreated();

    /**
     * Verify if the current demonstration state is decided state.
     *
     * @return true if the demonstration state is decided state, false otherwise
     */
    boolean isDecided();

    /**
     * Changes the current demonstration state to decided state.
     *
     * @return true if the state is changed to decided state, false otherwise
     */
    boolean setDecided();

    /**
     * Verify if the current demonstration state is discontinued state.
     *
     * @return true if the demonstration state is discontinued state, false otherwise
     */
    boolean isDiscontinued();

    /**
     * Changes the current demonstration state to discontinued state.
     *
     * @return true if the state is changed to discontinued state, false otherwise
     */
    boolean setDiscontinued();

    /**
     * Verify if the current demonstration state is in opened applications
     * state.
     *
     * @return true if the demonstration state is in opened applications state,
     * false otherwise
     */
    boolean isOpenedApplications();

    /**
     * Changes the current demonstration state to OpenedApplications state.
     *
     * @return true if the state is changed to OpenedApplications state, false
     * otherwise
     */
    boolean setOpenApplications();

    /**
     * Verify if the current demonstration state is CLosedApplications state.
     *
     * @return true if the demonstration state is CLosedApplications state,
     * false otherwise
     */
    boolean isClosedApplications();

    /**
     * Changes the current demonstration state to ClosedApplications state.
     *
     * @return true if the state is changed to ClosedApplications state, false
     * otherwise
     */
    boolean setClosedApplications();

    /**
     * Verify if the current demonstration state is DetectedConflicts state.
     *
     * @return true if the demonstration state is in DetectedConflicts state,
     * false otherwise
     */
    boolean isDetectedConflicts();

    /**
     * Changes the current demonstration state to in DetectedConflicts state.
     *
     * @return true if the state is changed to in DetectedConflicts state, false
     * otherwise
     */
    boolean setDetectedConflicts();

    /**
     * Verify if the current demonstration state is in ChangedConflicts state.
     *
     * @return true if the demonstration state is in ChangedConflicts state,
     * false otherwise
     */
    boolean isChangedConflicts();

    /**
     * Changes the current demonstration state to ChangedConflicts state.
     *
     * @return true if the state is changed to ChangedConflicts state, false
     * otherwise
     */
    boolean setChangedConflicts();

    /**
     * Verify if the current application state is ApplicationsInEvaluation
     * state.
     *
     * @return true if the demonstration state is ApplicationsInEvaluation
     * state, false otherwise
     */
    boolean isApplicationsInEvaluation();

    /**
     * Changes the current demonstration state to ApplicationsInEvaluation
     * state.
     *
     * @return true if the state is changed to ApplicationsInEvaluation state,
     * false otherwise
     */
    boolean setApplicationsInEvaluation();

    /**
     * Verify if the current demonstration state is InDecisionPeriod state.
     *
     * @return true if the demonstration state is InDecisionPeriod state, false
     * otherwise
     */
    boolean isApplicationsInDecision();

    /**
     * Changes the current demonstration state to InDecisionPeriod state.
     *
     * @return true if the state is changed to InDecisionPeriod state, false
     * otherwise
     */
    boolean setApplicationsInDecision();

    /**
     * Verify if the current demonstration state is isApplicationsDecided state.
     *
     * @return true if the demonstration state is isApplicationsDecided state,
     * false otherwise
     */
    boolean isApplicationsDecided();

    /**
     * Changes the current demonstration state to isApplicationsDecided state.
     *
     * @return true if the state is changed to isApplicationsDecided state,
     * false otherwise
     */
    boolean setApplicationsDecided();

    /**
     * Validate if the the state gathers all necessary information to transit to
     * next state.
     *
     * @return true if the state gathers the information to transit, false
     * otherwise
     */
    boolean validate();
}
