/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a stands register to store stands.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class Stand implements Selectable, Serializable {

    /**
     * Stand's unique number identification.
     */
    private int numberID;

    /**
     * Stand's area.
     */
    private float area;

    /**
     * Stand's description.
     */
    private String description;

    /**
     * Stand's number ID counter.
     */
    private static int NUMBER_ID_COUNTER = 0;

    /**
     * Stand's default area.
     */
    private static final float DEFAULT_AREA = 0.0f;

    /**
     * Stand's default description.
     */
    private static final String DEFAULT_DESCRIPTION = "No description";

    /**
     * Default constructor of a Stand class.
     */
    public Stand() {
        this.area = DEFAULT_AREA;
        this.numberID = ++NUMBER_ID_COUNTER;
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Constructor of a Stand class.
     *
     * @param area Area of the stand
     * @param description Stand description
     */
    public Stand(float area, String description) {
        this.area = area;
        this.numberID = ++NUMBER_ID_COUNTER;
        this.description = description;
    }

    /**
     * Copy constructor of a Stand class.
     *
     * @param stand Stand to copy
     */
    public Stand(Stand stand) {
        this.area = stand.area;
        this.numberID = ++NUMBER_ID_COUNTER;
        this.description = stand.description;
    }

    /**
     * Obtain the stand's number ID.
     *
     * @return the stand's number ID
     */
    public int getNumberID() {
        return numberID;
    }

    /**
     * Set the stand's number ID.
     *
     * @param numberID the stand's number ID to set
     */
    @XmlAttribute
    public void setNumberID(int numberID) {
        this.numberID = numberID;
    }

    /**
     * Obtain the stand's area.
     *
     * @return the stand's area
     */
    public float getArea() {
        return area;
    }

    /**
     * Set the stand's area.
     *
     * @param area the stand's area to set
     */
    @XmlElement
    public void setArea(float area) {
        this.area = area;
    }

    /**
     * Obtain the stand's description.
     *
     * @return description the stand's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the stand's description.
     *
     * @param description the stand's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Validates stand.
     *
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        return this.description.length() > 0 && this.area > 0;
    }

    /**
     * Return the textual representation of a stand.
     *
     * @return the textual representation of a stand
     */
    @Override
    public String toString() {
        return String.format("Stand{%nnumberID=%d%narea=%.1f%ndescription=%s}", this.numberID, this.area, this.description);
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it is equal, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        Stand otherStand = (Stand) otherObject;

        return this.area == otherStand.area && this.description.equals(otherStand.description) && this.numberID == otherStand.numberID;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.numberID;
        hash = 47 * hash + Float.floatToIntBits(this.area);
        hash = 47 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return this.description;
    }

}
