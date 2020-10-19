/**
 * Package location for Model concepts.
 */
package lapr.project.model;

/**
 * Represents a object that could be selected from a list (UI).
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Selectable {

    /**
     * Info to be shown on a JList.
     *
     * @return info to be shown on a JList
     */
    String getDisplayInfo();

}
