/**
 * Package location for Model concepts.
 */
package lapr.project.model;

/**
 * Interface for assignable applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Assingnable {

    /**
     * Gets the current state of the application.
     *
     * @return application current state
     */
    ApplicationState getCurrentState();

    /**
     * set assignable in evaluation state
     */
    void setInEvaluation();

    /**
     * Gets the assignable title.
     *
     * @return assignable's title
     */
    String getTitle();
}
