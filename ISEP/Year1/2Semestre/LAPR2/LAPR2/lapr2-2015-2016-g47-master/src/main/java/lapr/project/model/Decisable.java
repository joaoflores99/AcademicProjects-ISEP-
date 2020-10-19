/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.List;

/**
 * Interface for decisable applications.
 * 
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Decisable extends Selectable{
    
    /**
     * Returns true if the decisable is in decision.
     * @return true if decisable is in decision
     */
    public   boolean isInDecision();
    
    /**
     * Returns the list of evaluations of a decisable.
     * @return the evaluations list
     */
    public  List<Evaluation> getEvaluationsList();
    
    /**
     * Creates a new decision.
     * 
     */
    public void newDecision();
    
    /**
     * Sets the decision attributes.
     * @param decision boolean depicting the decision
     * @param justificativeText text aiding the decision
     */
    public void setDecision(boolean decision,String justificativeText);
    
    /**
     * Returs if this decision is valid
     * @return true if all O.K.
     */
    public boolean validateDecision();
    
    /**
     * Sets the decisable as decided
     * @return true if all OK
     */
    public boolean setDecided();
}
