/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a conflict.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Conflict implements Selectable, Serializable {

    /**
     * The type of the conflict.
     */
    private ConflictType conflictType;

    /**
     * The staff member which conflicts with.
     */
    private StaffMember staffMember;

    /**
     * Where the conflict occurs.
     */
    @XmlElements({
        @XmlElement(name = "exhibition_application", type = ExhibitionApplication.class),
        @XmlElement(name = "demonstration_application", type = DemonstrationApplication.class)
    })
    private Application application;

    /**
     * Constructs a conflict with its default values.
     */
    public Conflict() {
        this.conflictType = new ConflictType();
        this.staffMember = new StaffMember();
        this.application = new ExhibitionApplication();
    }

    /**
     * Constructs a conflict receiving a conflict type, a staff member and a
     * application.
     *
     * @param conflictType conflict type
     * @param staffMember staff member
     * @param application application
     */
    public Conflict(ConflictType conflictType, StaffMember staffMember, Application application) {
        this.conflictType = new ConflictType(conflictType);
        this.staffMember = new StaffMember(staffMember);
        this.application = application;
    }

    /**
     * Constructs a conflict copying another conflict.
     *
     * @param conflict another conflict
     */
    public Conflict(Conflict conflict) {
        this.conflictType = new ConflictType(conflict.conflictType);
        this.application = conflict.application;
        this.staffMember = conflict.staffMember;
    }

    /**
     * Gets the conflict type.
     *
     * @return conflict type
     */
    public ConflictType getConflictType() {
        return new ConflictType(conflictType);
    }

    /**
     * Sets the conflict type.
     *
     * @param conflictType conflictType
     */
    public void setConflictType(ConflictType conflictType) {
        this.conflictType = new ConflictType(conflictType);
    }

    /**
     * Gets the staff member.
     *
     * @return staff member
     */
    public StaffMember getStaffMember() {
        return new StaffMember(staffMember);
    }

    /**
     * Sets the staff member.
     *
     * @param staffMember staff member
     */
    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = new StaffMember(staffMember);
    }

    /**
     * Gets the application.
     *
     * @return application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application.
     *
     * @param application application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Return true if staff member is the same as the staff member passed as
     * parameter, false otherwise.
     *
     * @param staffMember staff member passed as parameter.
     *
     * @return true if staff member is the same as the staff member passed as
     * parameter, false otherwise.
     */
    public boolean isStaffMember(StaffMember staffMember) {
        return this.staffMember.equals(staffMember);
    }

    /**
     * It validates the conflict if it's staffMember and application and
     * conflictType is not null it return true, false otherwise
     *
     * @return true if it's staffMember and application and conflictType is not
     * null, false otherwise
     */
    public boolean validate() {
        return (this.staffMember != null && this.application != null && this.conflictType != null);
    }

    /**
     * Returns a textual representation for the conflict.
     *
     * @return textual representation for the conflict
     */
    @Override
    public String toString() {
        return String.format("Conflict{conflictType=%s;application=%s}", this.conflictType, this.application);
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
        Conflict otherConflict = (Conflict) otherObject;

        return this.conflictType.equals(otherConflict.conflictType) && this.staffMember.equals(otherConflict.staffMember) && this.application.equals(otherConflict.application);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.conflictType);
        hash = 47 * hash + Objects.hashCode(this.staffMember);
        hash = 47 * hash + Objects.hashCode(this.application);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return String.format("Conflict{conflictType=%s;application=%s}", this.conflictType.getDescription(), this.application.getDisplayInfo());
    }

}
