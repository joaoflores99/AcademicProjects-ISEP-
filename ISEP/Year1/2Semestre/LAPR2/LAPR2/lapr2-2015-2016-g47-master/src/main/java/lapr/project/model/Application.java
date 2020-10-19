/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.List;

/**
 * A markup interface for an application.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Application extends Selectable, Removable {

    /**
     * Gets the evaluations list.
     *
     * @return evaluations list
     */
    List<Evaluation> getEvaluationsList();

    /**
     * Gets the keywords list.
     *
     * @return keywords list
     */
    List<Keyword> getKeywordsList();

    /**
     * Changes the state of the application.
     *
     * @param newState new state to be active
     */
    void setState(ApplicationState newState);

    /**
     * Verify if an application is valid.
     *
     * @return true if it is valid, false otherwise
     */
    public boolean validate();

    /**
     * Verify if the application is decided state.
     *
     * @return true if accepted
     */
    public boolean isDecided();

    /**
     * Verify if the application is accepted.
     *
     * @return true if accepted
     */
    public boolean isDecisionAccepted();

    /**
     * Verify if the application is declined.
     *
     * @return true if rejected
     */
    public boolean isDesionDeclined();
    
    /**
     * Gets the exhibitor.
     * @return returns the exhibitor
     */
    public Exhibitor getExhibitor();
    
}
