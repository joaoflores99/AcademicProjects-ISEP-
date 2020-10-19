/**
 * Package location for Model concepts.
 */
package lapr.project.model;

/**
 * Mechanism to detect conflicts.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface ConflictDetectionMechanism {

    /**
     * Detects if there is a conflict between a staff member & a application
     *
     * @param staffMember the staff member to verify
     * @param application the application to verify
     * @return a conflict between a staff member & a application
     */
    boolean detectConflict(StaffMember staffMember, Application application);

    /**
     * Gets the description of the mechanism
     *
     * @return mechanism's description
     */
    String getDescription();
}
