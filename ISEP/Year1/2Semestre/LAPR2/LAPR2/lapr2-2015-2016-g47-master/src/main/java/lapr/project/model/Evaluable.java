/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.List;

/**
 * Interface for evaluable applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Evaluable {

    /**
     * Returns a new evaluation.
     *
     * @return new evaluation
     */
    Evaluation newEvaluation();

    /**
     * Validate if a evaluation is valid.
     *
     * @param evaluation evaluation to be validated
     * @return true if it is valid, false otherwise
     */
    boolean validateEvaluation(Evaluation evaluation);

    /**
     * Register an evaluation.
     *
     * @param evaluation evaluation to be registered
     * @return true if it is registered with success, false otherwise
     */
    boolean registerEvaluation(Evaluation evaluation);

    /**
     * Gets the evaluables title.
     *
     * @return evaluables title
     */
    String getTitle();

    /**
     * Gets the list of keywords.
     *
     * @return keywords list
     */
    List<Keyword> getKeywordsList();

    /**
     * Gets the exhibitor.
     *
     * @return exhibitor
     */
    Exhibitor getExhibitor();

    /**
     * Gets the area.
     *
     * @return area
     */
    float getArea();

    /**
     * Returns the invitations number.
     *
     * @return invitations number.
     */
    int getNumberInvitations();

    /**
     * Gets the products list.
     *
     * @return products list
     */
    List<Product> getProductsList();
}
