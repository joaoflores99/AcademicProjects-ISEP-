/**
 * Package location for Model concepts.
 */
package lapr.project.model;

/**
 * Represents an actor in the system.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Actor {

    /**
     * Gets the actor's user.
     *
     * @return actor's user
     */
    public User getUser();
    
    public void setUser(User user);

}
