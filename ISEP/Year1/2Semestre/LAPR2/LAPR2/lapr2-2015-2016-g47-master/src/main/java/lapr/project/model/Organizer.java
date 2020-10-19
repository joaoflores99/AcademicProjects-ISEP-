/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an organizer.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Organizer implements Actor, Selectable, Serializable {

    /**
     * The user for the organizer.
     */
    private User user;

    /**
     * Creates an instance of organizer with its default values.
     */
    public Organizer() {
        user = new User();
    }

    /**
     * Creates an instance of organizer receiving an user.
     *
     * @param user user for the organizer
     */
    public Organizer(User user) {
        this.user = user;
    }

    /**
     * Creates an instance of organizer copying another organizer.
     *
     * @param organizer another organizer
     */
    public Organizer(Organizer organizer) {
        user = new User(organizer.user);
    }

    /**
     * Gets the user of the organizer.
     *
     * @return user of the organizer
     */
    @Override
    public User getUser() {
        return user;
    }

    /**
     * Sets the user of the organizer.
     *
     * @param user user of the organizer
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Validate the Organizer's user.
     *
     * @return true if user is valid
     */
    public boolean validate() {
        return this.user.validate();
    }

    /**
     * Return the textual representation of a organizer.
     *
     * @return the textual representation of a organizer
     */
    @Override
    public String toString() {
        return String.format("Organizer{%n%s%n}", user);
    }

    /**
     * Compares two Organizer objects.
     *
     * @param otherObject Object to compare
     * @return true if the objects are equals.
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Organizer otherOrganizer = (Organizer) otherObject;

        return this.user.equals(otherOrganizer.user);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return this.user.getDisplayInfo();
    }

}
