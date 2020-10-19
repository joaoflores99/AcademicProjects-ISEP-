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
 * Represents an exhibitor responsible
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExhibitorResponsible implements Actor, Serializable {

    /**
     * the instance of user in this class
     */
  
    private User user;

    /**
     * Builds instances of ExhibitorResponsible with no params.
     */
    public ExhibitorResponsible() {
        this.user = new User();
    }

    /**
     * Builds instances of ExhibitorResponsible receiving an
     * ExhibitorResponsible as param.
     */
    public ExhibitorResponsible(ExhibitorResponsible exhibitor) {
        this.user = new User(exhibitor.user);
    }

    /**
     * Builds instances of ExhibitorResponsible receiving an user as param.
     */
    public ExhibitorResponsible(User user) {
        this.user = new User(user);
    }

    /**
     * returns this instance of user.
     */
    @Override
    public User getUser() {
        return this.user;
    }

    /**
     * sets the current value of this user
     */
    @Override
    public void setUser(User u) {
        this.user = new User(u);
    }

    /**
     * Returns the textual representation of the attributes of this class.
     *
     * @return the textual representation of the attributes of this class.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ExhibitorResponsible{");

        s.append(String.format("%s%n", this.user));

        s.append("}");
        return s.toString();
    }

    /**
     * Compares two ExhibitorResponsible objects.
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
        ExhibitorResponsible otherExhibitorResponsible = (ExhibitorResponsible) otherObject;

        return this.user.equals(otherExhibitorResponsible.user);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.user);
        return hash;
    }
}
