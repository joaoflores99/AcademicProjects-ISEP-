/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.List;

/**
 * Represents an interface of StaffAttributionMechanism
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface StaffAttributionMechanism extends Selectable {

    /**
     * Creates attributions list
     *
     * @param submittable
     * @return attributionsList
     */
    List<StaffAttribution> toAssign(Submittable submittable);
}
