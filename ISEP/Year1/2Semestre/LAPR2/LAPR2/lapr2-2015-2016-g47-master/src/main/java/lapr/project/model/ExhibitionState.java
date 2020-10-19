/**
 * Package location for Model concepts.
 */
package lapr.project.model;

/**
 * Represents the ExhibitionState
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface ExhibitionState {

    /**
     * Verify if the exhibition is in the initial state.
     *
     * @return true if the exhibition is in the initial state, false otherwise
     */
    boolean isInicial();

    /**
     * Changes the current exhibtion state to created state.
     *
     * @return true if the state is changed to created state, false otherwise
     */
    boolean setCreated();

    /**
     * Verify if the exhibition is in the created state.
     *
     * @return true if the exhibition is created, false otherwise
     */
    boolean isCreated();

    /**
     * Changes the current exhibtion state to staff defined state.
     *
     * @return true if the state is changed to staff defined state, false
     * otherwise
     */
    boolean setStaffDefined();

    /**
     * Verify if the exhibition has staff defined.
     *
     * @return true if the staff is defined, false otherwise
     */
    boolean isStaffDefined();

    /**
     * Changes the current exhibtion state to demonstrations defined state.
     *
     * @return true if the state is changed to demonstrations defined state,
     * false otherwise
     */
    boolean setDemonstrationsDefined();

    /**
     * Verify if the exhibition has demonstrations defined.
     *
     * @return true if the demonstrations are defined, false otherwise
     */
    boolean isDemonstrationsDefined();

    /**
     * Changes the current exhibtion state to open applications state.
     *
     * @return true if the state is changed to open applications state, false
     * otherwise
     */
    boolean setOpenApplication();

    /**
     * Verify if the exhibition's applications are opened.
     *
     * @return true if the exhibition's applications are opened, false otherwise
     */
    boolean isOpenApplications();

    /**
     * Changes the current exhibtion state to closed applications state.
     *
     * @return true if the state is changed to closed applications state, false
     * otherwise
     */
    boolean setClosedApplications();

    /**
     * Verify if the exhibition's applications are closed.
     *
     * @return true if the exhibition's applications are closed, false otherwise
     */
    boolean isClosedApplications();

    /**
     * Changes the current exhibtion state to detected conflicts state.
     *
     * @return true if the state is changed to detected conflicts state, false
     * otherwise
     */
    boolean setDetectedConflicts();

    /**
     * Verify if the exhibition has detected conflicts.
     *
     * @return true if the exhibition has detected conflicts, false otherwise
     */
    boolean isDetectedConficts();

    /**
     * Changes the current exhibtion state to changed conflicts state.
     *
     * @return true if the state is changed to changed conflicts state, false
     * otherwise
     */
    boolean setChangedConflicts();

    /**
     * Verify if the exhibition has changed conflicts.
     *
     * @return true if the exhibition has changed conflicts, false otherwise
     */
    boolean isChangedConflitcts();

    /**
     * Changes the current exhibtion state to applications in evaluation state.
     *
     * @return true if the state is changed to applications in evaluation state,
     * false otherwise
     */
    boolean setApplicationsInEvaluation();

    /**
     * Verify if the exhibition's applications are in evaluation.
     *
     * @return true if the exhibition's applications are in evaluation, false
     * otherwise
     */
    boolean isApplicationsInEvaluation();

    /**
     * Changes the current exhibtion state to applications in decision state.
     *
     * @return true if the state is changed to applications in decision state,
     * false otherwise
     */
    boolean setApplicationsInDecision();

    /**
     * Verify if the exhibition's applications are in decision.
     *
     * @return true if the exhibition's applications are in decision, false
     * otherwise
     */
    boolean isApplicationsInDecision();

    /**
     * Changes the current exhibtion state to applications decided state.
     *
     * @return true if the state is changed to applications decided state, false
     * otherwise
     */
    boolean setApplicationsDecided();

    /**
     * Verify if the exhibition's applications are decided.
     *
     * @return true if the exhibition's applications are decided, false
     * otherwise
     */
    boolean isApplicationsDecided();

    /**
     * Validate if the the state gathers all necessary information to transit to
     * next state.
     *
     * @return true if the state gathers the information to transit, false
     * otherwise
     */
    boolean validate();
}
