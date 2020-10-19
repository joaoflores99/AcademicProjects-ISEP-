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
 * Represents a Staff Member.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StaffMember implements Actor, Selectable, Serializable {

    /**
     * The user information associated to this Staff Member.
     */
    private User user;

    /**
     * Default constructor of a Staff Member class.
     */
    public StaffMember() {
        this.user = new User();
    }

    /**
     * Constructor of a Staff Member class.
     *
     * @param user the user associated to the staff member
     */
    public StaffMember(User user) {
        this.user = user;
    }

    /**
     * Copy constructor of a Staff Member class.
     *
     * @param staffMember the Staff Member to copy
     */
    public StaffMember(StaffMember staffMember) {
        this.user = staffMember.user;
    }

    /**
     * Obtain the user associated to the staff member.
     *
     * @return the user associated to the staff member
     */
    @Override
    public User getUser() {
        return user;
    }

    /**
     * Set the user associated to the staff member.
     *
     * @param user the user associated to the staff member to set
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Validates the User's user.
     *
     * @return true is valid
     */
    public boolean validate() {
        return this.user.validate();
    }

    /**
     * Return the textual representation of a staff member.
     *
     * @return the textual representation of a staff member
     */
    @Override
    public String toString() {
        return String.format("StaffMember{%nuser=%s}", this.user);
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it repreents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        StaffMember otherStaffMember = (StaffMember) otherObject;

        return this.user.equals(otherStaffMember.user);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return this.user.getName();
    }
}
