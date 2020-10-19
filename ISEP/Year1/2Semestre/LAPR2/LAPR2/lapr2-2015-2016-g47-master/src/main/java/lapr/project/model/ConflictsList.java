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
 * Represents a conflicts list.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConflictsList implements Serializable {

    /**
     * List of conflicts users.
     */
    @XmlElementWrapper(name = "conflicts_list")
    @XmlElement(name = "conflict")
    private List<Conflict> conflictsList;

    /**
     * Creates an instance of ConflictsList with its default values.
     */
    public ConflictsList() {
        this.conflictsList = new ArrayList<>();
    }

    /**
     * Creates an instance of ConflictsList receiving the list of conflicts
     *
     * @param conflictsList list of conflicts users
     */
    public ConflictsList(List<Conflict> conflictsList) {
        this.conflictsList = new ArrayList<>(conflictsList);
    }

    /**
     * Creates an instance of ConflictsList copying another conflicts list.
     *
     * @param conflictsList another conflicts list
     */
    public ConflictsList(ConflictsList conflictsList) {
        this.conflictsList = new ArrayList<>(conflictsList.conflictsList);
    }

    /**
     * Gets the list of conflicts.
     *
     * @return list of conflicts
     */
    public List<Conflict> getConflictsList() {
        return new ArrayList<>(conflictsList);
    }

    /**
     * Gets the list of conflicts filtered by staff member.
     *
     * @param staffMember staffed member passed as parameter used to filter
     * conflicts.
     *
     * @return the list of conflict filtered by staff member.
     *
     */
    public List<Conflict> getConflictsListByStaffMember(StaffMember staffMember) {
        List<Conflict> conflictsListByStaffMember = new ArrayList();
        for (Conflict conflict : conflictsList) {
            if (conflict.isStaffMember(staffMember)) {
                conflictsListByStaffMember.add(conflict);
            }
        }
        return conflictsListByStaffMember;
    }

    /**
     * Sets the list of conflicts.
     *
     * @param conflictsList list of conflicts
     */
    public void setConflictsList(List<Conflict> conflictsList) {
        this.conflictsList = new ArrayList<>(conflictsList);
    }

    /**
     * Creates a new conflict with staffMember, conflictType and application
     * passed as parameters
     *
     * @param staffMember the staffMember
     * @param conflictType the conflict type
     * @param application the application
     * @return returns new conflict
     */
    public Conflict newConflict(StaffMember staffMember, ConflictType conflictType, Application application) {
        Conflict conflict;
        conflict = new Conflict(conflictType, staffMember, application);
        return conflict;
    }

    /**
     * Add a conflict to the list.
     *
     * @param conflict the conflict to add
     * @return true if the conflict is successfully added.
     */
    public boolean addConflict(Conflict conflict) {

        return this.conflictsList.add(conflict);
    }

    /**
     * Remove a conflict from the conflicts list.
     *
     * @param conflict the conflict to remove
     * @return true if the conflict is successfully removed.
     */
    public boolean removeConflict(Conflict conflict) {
        return this.conflictsList.remove(conflict);
    }

    /**
     * Register conflict.
     *
     * @param staffMember Staff Member in conflict
     * @param application Application in conflict
     * @param conflictType Conflict type
     *
     * @return true if conflict is successfully added.
     */
    public boolean registerConflict(StaffMember staffMember, Application application, ConflictType conflictType) {

        Conflict conflict = new Conflict(conflictType, staffMember, application);

        return (validateConflict(conflict)) ? addConflict(conflict) : false;
    }

    /**
     * Validate if the list doesn't contain a conflict already.
     *
     * @param conflict the conflict to validate
     * @return true if the list doesn't contain the conflict
     */
    public boolean validateConflict(Conflict conflict) {
        return !this.conflictsList.contains(conflict);
    }

    /**
     * Return the textual representation of a conflicts list.
     *
     * @return the textual representation of a conflicts list
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ConflictsList{");
        for (Conflict conflict : this.conflictsList) {
            s.append(String.format("%s%n", conflict));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares two ConflictsList objects.
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
        ConflictsList otherConflictsList = (ConflictsList) otherObject;

        return this.conflictsList.equals(otherConflictsList.conflictsList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.conflictsList);
        return hash;
    }
}
