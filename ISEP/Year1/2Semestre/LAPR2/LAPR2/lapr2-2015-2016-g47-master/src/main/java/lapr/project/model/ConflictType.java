/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lapr.project.model.mechanisms.detection.RelatedUserConflictMechanism;

/**
 * Represents a conflict type.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConflictType implements Selectable, Serializable {

    /**
     * The conflict detection mechanism.
     */
    @XmlTransient
    private ConflictDetectionMechanism conflictDetectionMechanism;

    /**
     * The description of the conflict type.
     */
    @XmlAttribute
    private String description;

    /**
     * The default value for the description.
     */
    private static final String DEFAULT_DESCRIPTION = "No description.";

    /**
     * Constructs a conflict type with its default values.
     */
    public ConflictType() {
        this.conflictDetectionMechanism = new RelatedUserConflictMechanism();
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Constructs a conflict type receiving a conflict detection mechanism and a
     * description.
     *
     * @param conflictDetectionMechanism conflict Detection Mechanism
     * @param description description
     */
    public ConflictType(ConflictDetectionMechanism conflictDetectionMechanism, String description) {
        this.conflictDetectionMechanism = conflictDetectionMechanism;
        this.description = description;
    }

    /**
     * Constructs a conflict type receiving a description.
     *
     * @param description description
     */
    public ConflictType(String description) {
        this.description = description;
        this.conflictDetectionMechanism = new RelatedUserConflictMechanism();
    }

    /**
     * Constructs a conflict type copying another conflict type.
     *
     * @param conflictType another conflict type
     */
    public ConflictType(ConflictType conflictType) {
        this.conflictDetectionMechanism = conflictType.conflictDetectionMechanism;
        this.description = conflictType.description;
    }

    /**
     * Gets the conflict detection mechanism.
     *
     * @return conflict detection mechanism
     */
    public ConflictDetectionMechanism getConflictDetectionMechanism() {
        return conflictDetectionMechanism;
    }

    /**
     * Sets the conflict detection mechanism.
     *
     * @param conflictDetectionMechanism conflict detection mechanism
     */
    public void setConflictDetectionMechanism(ConflictDetectionMechanism conflictDetectionMechanism) {
        this.conflictDetectionMechanism = conflictDetectionMechanism;
    }

    /**
     * Gets the description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns true if type conflicts has some description, false otherwise
     *
     * @return true if type conflicts has some description, false otherwise
     */
    public boolean validate() {
        return this.description.length() > 0;
    }

    /**
     * Returns a textual representation for the conflict type.
     *
     * @return textual representation for the conflict type
     */
    @Override
    public String toString() {
        return String.format("ConflictType{conflictDetectionMechanism=%s;description=%s]", this.conflictDetectionMechanism, this.description);
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it represents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        ConflictType otherConflictType = (ConflictType) otherObject;

        return this.conflictDetectionMechanism.equals(otherConflictType.conflictDetectionMechanism) && this.description.equals(otherConflictType.description);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.conflictDetectionMechanism);
        hash = 89 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return this.description;
    }

}
