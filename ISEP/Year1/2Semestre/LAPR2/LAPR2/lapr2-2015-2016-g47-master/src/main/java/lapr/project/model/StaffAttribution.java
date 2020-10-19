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
 * Represents a staff attribution.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StaffAttribution implements Selectable, Serializable {

    /**
     * The staffAttribution's application.
     */
    @XmlElements({
        @XmlElement(name = "exhibition_application", type = ExhibitionApplication.class),
        @XmlElement(name = "demonstration_application", type = DemonstrationApplication.class)
    })
    private Assingnable application;

    /**
     * The staffAttribution's staffMember.
     */
    private StaffMember staffMember;

    /**
     * Default constructor of a staffAttribution class.
     */
    public StaffAttribution() {
        this.application = new ExhibitionApplication();
        this.staffMember = new StaffMember();
    }

    /**
     * Constructor of a staffAttribution class.
     *
     * @param application staffAttribution's application
     * @param staffMember staffAttribution's staffMember
     */
    public StaffAttribution(Assingnable application, StaffMember staffMember) {
        this.application = application;
        this.staffMember = new StaffMember(staffMember);

    }

    /**
     * Constructor that receives an instance of this class as parameter.
     *
     * @param staffAttribution staff attribution
     */
    public StaffAttribution(StaffAttribution staffAttribution) {
        this.application = staffAttribution.application;
        this.staffMember = new StaffMember(staffAttribution.staffMember);
    }

    /**
     * Obtain the staffAttribution's application.
     *
     * @return staffAttribution application
     */
    public Assingnable getApplication() {
        return this.application;
    }

    /**
     * Set the staffApplication's application.
     *
     * @param application the staffAttribution application to set
     */
    public void setApplication(Assingnable application) {

        this.application = application;
    }

    /**
     * Obtain the staffAttribution's staffMember.
     *
     * @return staffAttribution staffMember
     */
    public StaffMember getStaffMember() {
        return this.staffMember;
    }

    /**
     * Set the staffApplication's staffMember.
     *
     * @param staffMember the staffAttribution staffMember to set
     */
    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    /**
     * Verify if a received staff member is the staff member of this
     * attribution.
     *
     * @param staffMember staff member to verify
     * @return true if he/she is, false otherwise
     */
    public boolean isStaffMember(StaffMember staffMember) {
        return this.staffMember.equals(staffMember);
    }

    /**
     * Return the textual representation of a StaffAttribution.
     *
     * @return the textual representation of a StaffAttribution
     */
    @Override
    public String toString() {
        return String.format("StaffAttribution{%napplication=%s%nstaffMember=%s%n}", this.application, this.staffMember);
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
        StaffAttribution otherStaffAttribution = (StaffAttribution) otherObject;

        return this.application.equals(otherStaffAttribution.application) && this.staffMember.equals(otherStaffAttribution.staffMember);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.application);
        hash = 53 * hash + Objects.hashCode(this.staffMember);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return this.application.getTitle();
    }

}
