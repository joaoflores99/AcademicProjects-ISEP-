/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an staff attributions list
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StaffAttributionsList implements Serializable {

    /**
     * the list of staff attributions instances
     */
    @XmlElementWrapper(name = "staff_attributions_list")
    @XmlElement(name = "staff_attribution")
    private List<StaffAttribution> staffAttributionsList;

    /**
     * empty constructor of this class
     */
    public StaffAttributionsList() {
        staffAttributionsList = new ArrayList<>();
    }

    /**
     * constructor receiving a list of staff attribution as param.
     *
     * @param staffAttributionsList Staff Attributions List
     */
    public StaffAttributionsList(List<StaffAttribution> staffAttributionsList) {
        this.staffAttributionsList = new ArrayList(staffAttributionsList);
    }

    /**
     * constructor receiving a instance of this class as param
     *
     * @param staffAttributionsList StaffAttributionList to copy
     */
    public StaffAttributionsList(StaffAttributionsList staffAttributionsList) {
        this.staffAttributionsList = new ArrayList(staffAttributionsList.staffAttributionsList);
    }

    /**
     * Obtain the list of staff attributions.
     *
     * @return the list of staff attributions
     */
    public List<StaffAttribution> getStaffAttributionsList() {
        return this.staffAttributionsList;
    }

    /**
     * sets the staff attributions list.
     *
     * @param staffAttributionsList staff attributions list to set
     */
    public void setStaffAttributionsList(List<StaffAttribution> staffAttributionsList) {
        this.staffAttributionsList = new ArrayList<>(staffAttributionsList);
    }

    public List<StaffAttribution> getStaffAtributionsApplicationsInEvaluationByStaff(StaffMember staffMember) {
        List<StaffAttribution> staffAtributionsApplicationsInEvaluationBystaff = new ArrayList<>();

        for (StaffAttribution staffAttribution : this.staffAttributionsList) {
            if (staffAttribution.isStaffMember(staffMember) && staffAttribution.getApplication().getCurrentState().isInEvaluation()) {
                staffAtributionsApplicationsInEvaluationBystaff.add(staffAttribution);
            }
        }

        return staffAtributionsApplicationsInEvaluationBystaff;
    }

    /**
     * Creates new attribution.
     *
     * @param application application.
     * @param staffMember staffMember to evaluate
     * @return
     */
    public StaffAttribution newAttribution(Assingnable application, StaffMember staffMember) {

        return new StaffAttribution(application, staffMember);
    }

    /**
     * Verifies if the staffAttribution is valid
     *
     * @param staffAttribution staffAttribution being verified
     * @return true if valid, false otherwise
     */
    public boolean validateAttribution(StaffAttribution staffAttribution) {
        return (!this.staffAttributionsList.contains(staffAttribution));
    }

    /**
     * Set applications(Assignables)(exhibitions applications &&/||
     * demonstrations applications) inEvaluationState
     */
    public void setApplicationsInEvaluationState() {
        for (StaffAttribution attribution : this.staffAttributionsList) {
            Assingnable assignable = attribution.getApplication();
            assignable.setInEvaluation();
        }
    }

    /**
     * Return the textual representation of a staff attribution list.
     *
     * @return the textual representation of a staff attribution list
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("StaffAttributionsList{");
        for (StaffAttribution staffAttribution : staffAttributionsList) {
            s.append(String.format("%s%n", staffAttribution));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares two StaffAttributionsList objects.
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
        StaffAttributionsList otherStaffAttributionsList = (StaffAttributionsList) otherObject;

        return this.staffAttributionsList.equals(otherStaffAttributionsList.staffAttributionsList);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.staffAttributionsList);
        return hash;
    }

    boolean removeStaffAttribution(StaffAttribution staffAttribution) {
        return this.staffAttributionsList.remove(staffAttribution);
    }
}
