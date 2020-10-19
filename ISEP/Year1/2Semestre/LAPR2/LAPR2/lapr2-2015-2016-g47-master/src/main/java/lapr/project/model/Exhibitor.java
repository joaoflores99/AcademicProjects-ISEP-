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
 * Represents an Exhibitor
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Exhibitor implements Serializable {

    /**
     * The exhibitor´s name.
     */
    @XmlAttribute
    private String name;

    /**
     * The exhibitor´s address.
     */
    private String address;

    /**
     * The exhibitor´s mobile number.
     */
    private String mobileNumber;

    /**
     * The exhibitor's responsible
     */
    private ExhibitorResponsible exhibitorResponsible;

    /**
     * The exhibitor´s name by default.
     */
    private static final String DEFAULT_NAME = "no name";

    /**
     * The exhibitor´s address by default.
     */
    private static final String DEFAULT_ADDRESS = "address";

    /**
     * The exhibitor´s mobile number by default.
     */
    private static final String DEFAULT_MOBILE_NUMBER = "mobileNumber";

    /**
     * Default constructor of a exhibitor class.
     */
    public Exhibitor() {
        this.address = DEFAULT_ADDRESS;
        this.mobileNumber = DEFAULT_MOBILE_NUMBER;
        this.name = DEFAULT_NAME;
        this.exhibitorResponsible = new ExhibitorResponsible();
    }

    /**
     * Constructor of a exhibitor class.
     *
     * @param name exhibitor's name
     * @param address exhibitor's address
     * @param mobileNumber exhibitor's mobile number
     */
    public Exhibitor(String name, String address, String mobileNumber, ExhibitorResponsible exhibitorResponsible) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.exhibitorResponsible = exhibitorResponsible;
    }

    /**
     * Constructor of a exhibitor class copying another instance of exhibitor
     * class.
     *
     * @param exhibitor
     */
    public Exhibitor(Exhibitor exhibitor) {
        this.address = exhibitor.address;
        this.mobileNumber = exhibitor.mobileNumber;
        this.name = exhibitor.name;
        this.exhibitorResponsible = exhibitor.exhibitorResponsible;
    }

    /**
     * Obtain the exhibitor's name.
     *
     * @return the exhibitor's name
     */
    public String getName() {
        return name;
    }

    /**
     * Obtain the exhibitor's address.
     *
     * @return the exhibitor's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Obtain the exhibitor's mobile number.
     *
     * @return the exhibitor's mobile number.
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Set the exhibitor's name.
     *
     * @param name the exhibitor's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the exhibitor's address.
     *
     * @param address the exhibitor's address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set the exhibitor's mobile number.
     *
     * @param mobileNumber the exhibitor's mobile number to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public ExhibitorResponsible getExhibitorResponsible() {
        return exhibitorResponsible;
    }

    public void setExhibitorResponsible(ExhibitorResponsible exhibitorResponsible) {
        this.exhibitorResponsible = exhibitorResponsible;
    }

    /**
     * Compares two objects
     *
     * @param otherObject the other object to compare with
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        Exhibitor otherExhibitor = (Exhibitor) otherObject;
        return (this.mobileNumber.equals(otherExhibitor.mobileNumber) && this.address.equals(otherExhibitor.address) && this.name.equals(otherExhibitor.name));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.address);
        hash = 11 * hash + Objects.hashCode(this.mobileNumber);
        return hash;
    }

    public boolean validate() {
        return (!this.name.isEmpty() && !this.address.isEmpty() && !this.mobileNumber.isEmpty());
    }

    /**
     * Return the textual representation of an exhibitor.
     *
     * @return the textual representation of an exhibitor
     */
    @Override
    public String toString() {
        return String.format("Exhibitor{%nname=%s%naddress=%s%nmobile number=%s}", this.name, this.address, this.mobileNumber);
    }

}
