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

/**
 * Represents a resource
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resource implements Selectable, Serializable {

    /**
     * The Resource's designation.
     */
    @XmlAttribute
    private String designation;

    /**
     * The Resource's designation by default.
     */
    private static final String DEFAULT_DESIGNATION = "no designation";

    /**
     * Default constructor of a Product class.
     */
    public Resource() {
        this.designation = DEFAULT_DESIGNATION;
    }

    /**
     * Constructor that receives an instance of this class as parameter.
     *
     * @param designation Resource's designation
     */
    public Resource(String designation) {
        this.designation = designation;
    }

    /**
     * Obtain the Resource's designation.
     *
     * @return the Resource's designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the Resource's designation.
     *
     * @param designation the Resource's designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Validates resource.
     *
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        return this.designation.length() > 0;
    }

    /**
     * Returns a textual representation for the resource.
     *
     * @return textual representation for the resource
     */
    @Override
    public String toString() {
        return String.format("Resource{%ndesignation=%s%n", this.designation);
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
        Resource otherResource = (Resource) otherObject;

        return this.designation.equals(otherResource.designation);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.designation);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return this.getDesignation();
    }
}
