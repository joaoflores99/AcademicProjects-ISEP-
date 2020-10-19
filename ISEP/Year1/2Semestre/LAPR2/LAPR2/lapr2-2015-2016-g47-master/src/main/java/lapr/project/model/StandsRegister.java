/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a stands register to store stands.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class StandsRegister implements Serializable {

    /**
     * List of stands.
     */
    private List<Stand> standsList;

    /**
     * Default constructor of a StandsRegister class.
     */
    public StandsRegister() {

        this.standsList = new ArrayList<>();
    }

    /**
     * Constructor of a StandsRegister class.
     *
     * @param standsList list of stands
     */
    public StandsRegister(List<Stand> standsList) {

        this.standsList = new ArrayList<>(standsList);
    }

    /**
     * Copy constructor of a StandsRegister class.
     *
     * @param standsRegister StandsRegister to copy
     */
    public StandsRegister(StandsRegister standsRegister) {

        this.standsList = new ArrayList<>(standsRegister.getStandsList());
    }

    /**
     * Obtain the stands list.
     *
     * @return the stands list
     */
    public List<Stand> getStandsList() {
        return new ArrayList<>(this.standsList);
    }

    /**
     * Set the stands list.
     *
     * @param standsList the stands list to set
     */
    public void setStandsList(List<Stand> standsList) {
        this.standsList = new ArrayList<>(standsList);
    }

    /**
     * Creates a new instance of stand.
     *
     * @param area
     * @param description
     * @return new stand
     */
    public Stand newStand(float area, String description) {
        return new Stand(area, description);
    }

    /**
     * Registers a stand
     *
     * @param stand stand to be registered
     * @return true if is successfully registered, false otherwise
     */
    public boolean registerStand(Stand stand) {
        return !this.standsList.contains(stand) ? addStand(stand) : false;
    }

    /**
     * Adds a stand to the stands list.
     *
     * @param stand stand to be added
     * @return true if it is successfully added, false otherwise
     */
    private boolean addStand(Stand stand) {
        return this.standsList.add(stand);
    }
    
    /**
     * Removes a given stand.
     * 
     * @param stand stand to be removed
     * @return true if is successfully removed and false otherwise
     */
    public boolean removeStand(Stand stand) {
        return this.standsList.contains(stand) ? this.standsList.remove(stand) : false;
    }

    /**
     * Return the textual representation of a standsRegister.
     *
     * @return the textual representation of a standsRegister
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("StandsRegister{");
        for (Stand stand : this.standsList) {
            s.append(String.format("%s%n", stand));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares if the given object is equal to this stands register.
     *
     * @param otherObject Object to compare
     * @return true if the objects are equals, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        StandsRegister otherStandsRegister = (StandsRegister) otherObject;

        return this.standsList.equals(otherStandsRegister.standsList);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.standsList);
        return hash;
    }
}
