/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a conflict types register to store conflict types.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ConflictTypesRegister implements Serializable {

    /**
     * List of conflict types.
     */
    private List<ConflictType> conflictTypesList;

    /**
     * Default constructor of a ConflictTypesRegister class.
     */
    public ConflictTypesRegister() {

        this.conflictTypesList = new ArrayList<>();
    }

    /**
     * Constructor of a ConflictTypesRegister class.
     *
     * @param conflictTypesList list of conflict types
     */
    public ConflictTypesRegister(List<ConflictType> conflictTypesList) {

        this.conflictTypesList = new ArrayList<>(conflictTypesList);
    }

    /**
     * Copy constructor of a ConflictTypesRegister class.
     *
     * @param conflictTypesRegister ConflictTypesRegister to copy
     */
    public ConflictTypesRegister(ConflictTypesRegister conflictTypesRegister) {

        this.conflictTypesList = new ArrayList<>(conflictTypesRegister.conflictTypesList);
    }

    /**
     * Obtain the conflict types list.
     *
     * @return the conflict types list
     */
    public List<ConflictType> getConflictTypesList() {
        return new ArrayList<>(this.conflictTypesList);
    }

    /**
     * Set the conflict types list.
     *
     * @param conflictTypesList the conflict types list to set
     */
    public void setConflictTypesList(List<ConflictType> conflictTypesList) {
        this.conflictTypesList = new ArrayList<>(conflictTypesList);
    }

    /**
     * Constructs new Conflict type
     *
     * @return the new Conflict type
     */
    public ConflictType newConflictType() {
        ConflictType conflictType = new ConflictType();
        return conflictType;
    }

    /**
     * Constructs new Conflict type
     *
     * @param description the description of the new conflict type
     * @return the new Conflict type
     */
    public ConflictType newConflictType(String description) {
        ConflictType conflictType = new ConflictType(description);
        return conflictType;
    }

    /**
     * Return true if the conflictTypesList does not contain the conflictType
     * passed as parameter, false otherwise
     *
     * @param conflictType the conflict type passed as parameter
     * @return true if the conflictTypesList does not contain the conflictType
     * passed as parameter, false otherwise
     */
    public boolean validateTypeConflict(ConflictType conflictType) {
        return !this.conflictTypesList.contains(conflictType);
    }

    /**
     * It adds the new conflict type passed as parameter to the
     * conflictTypesList
     *
     * @param conflictType the new conflict type passed as parameter
     * @return if it added returns true, false otherwise
     */
    public boolean registerTypeConflict(ConflictType conflictType) {
        return this.conflictTypesList.add(conflictType);
    }

    /**
     * Return the textual representation of a conflictTypesRegister.
     *
     * @return the textual representation of a conflictTypesRegister
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ConflictTypesRegister{");
        for (ConflictType conflictType : this.conflictTypesList) {
            s.append(String.format("%s%n", conflictType));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares two Conflicts Types Register objects.
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
        ConflictTypesRegister otherConflictsTypesRegister = (ConflictTypesRegister) otherObject;

        return this.conflictTypesList.equals(otherConflictsTypesRegister.conflictTypesList);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.conflictTypesList);
        return hash;
    }
}
