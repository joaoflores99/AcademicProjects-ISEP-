/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a users register to store exhibition managers.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionsManagerRegister implements Serializable {

    /**
     * The list of exhibitions managers.
     */
    List<ExhibitionsManager> exhibitionsManagerList;

    /**
     * The default constructor of this class.
     */
    public ExhibitionsManagerRegister() {
        exhibitionsManagerList = new ArrayList<>();
    }

    /**
     * Builds an instance of this class receiving a list of exhibitions manager
     * as parameter.
     *
     * @param exhibitionsManagerList the list of exhibitions manager
     */
    public ExhibitionsManagerRegister(List<ExhibitionsManager> exhibitionsManagerList) {
        this.exhibitionsManagerList = exhibitionsManagerList;
    }

    /**
     * Builds instance of this class receiving another instance of this class as
     * parameter.
     *
     * @param exhibitionsManagerRegister the other instance of this class
     */
    public ExhibitionsManagerRegister(ExhibitionsManagerRegister exhibitionsManagerRegister) {
        exhibitionsManagerList = exhibitionsManagerRegister.exhibitionsManagerList;
    }

    /**
     * Gets the exhibitions Manager list.
     *
     * @return the list of exhibitions manager
     */
    public List<ExhibitionsManager> getExhibitionsManagerList() {
        return exhibitionsManagerList;
    }

    /**
     * Verify if there is any exhibitions manager.
     *
     * @return true if there is at least one exhibitions manager, false
     * otherwise
     */
    public boolean hasAnyExhibitionManager() {
        return !this.exhibitionsManagerList.isEmpty();
    }

    public void addExhibitionManager(User user){
        this.exhibitionsManagerList.add(new ExhibitionsManager(user));
    }
    /**
     * Return the textual representation of this exhibitions manager register.
     *
     * @return the textual representation of this exhibitions manager register
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ExhibitionsManagerRegister{");
        for (ExhibitionsManager manager : this.exhibitionsManagerList) {
            s.append(String.format("%s%n", manager));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares if the given object is equal to this exhibitions manager
     * register.
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
        ExhibitionsManagerRegister otherExhibitionsManagerRegister = (ExhibitionsManagerRegister) otherObject;

        return this.exhibitionsManagerList.equals(otherExhibitionsManagerRegister.exhibitionsManagerList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.exhibitionsManagerList);
        return hash;
    }

}
