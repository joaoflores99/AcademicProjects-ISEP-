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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a KeyWord.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Keyword implements Selectable, Comparable<Keyword>, Serializable {

    /**
     * Converts a String of keywords in comma separated value (csv) format, to a
     * List of keywords.
     *
     * @param keywordsCSV keywords in comma separated value format (csv)
     * @return keywords list
     * @throws java.lang.Exception keywords are invalid
     */
    public static List<Keyword> toKeywordsList(String keywordsCSV) throws Exception {
        List<Keyword> keywordsList = new ArrayList<>();

        String[] keywords = keywordsCSV.split(",");
        String pattern = "^[a-zA-Z][a-zA-Z0-9]+$";
        for (String keyword : keywords) {
            if (!keyword.trim().matches(pattern)) { //Improve with REGEX
                throw new Exception();
            } else {
                keywordsList.add(new Keyword(keyword.trim()));
            }
        }

        return keywordsList;
    }

    /**
     * the description of this keyword
     */
    @XmlAttribute
    private String description;

    /**
     * default value of description
     */
    private static final String DEFAULT_DESCRIPTION = "No description";

    /**
     * Empty constructor.
     */
    public Keyword() {
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Constructor with parameters.
     *
     * @param description the keyword to define.
     */
    public Keyword(String description) {
        this.description = description;
    }

    /**
     * Copy constructor receiving another KeyWord instance.
     *
     * @param k the instance of KeyWord to copy.
     */
    public Keyword(Keyword k) {
        this.description = k.description;
    }

    /**
     * Gets the string value of description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * sets the value of description
     *
     * @param description the value to set.
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Validates the keyword.
     *
     * @return true if is valid
     */
    public boolean validate() {
        return !this.description.isEmpty();
    }

    /**
     * Equals method to check if two objects are the same.
     *
     * @param otherObject the other object to compare to
     * @return true of equal
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        Keyword otherKeyword = (Keyword) otherObject;
        return this.description.equals(otherKeyword.description);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.description);
        return hash;
    }

    /**
     * Returns the textual interpretation of the objects and attributes of this
     * class
     */
    @Override
    public String toString() {

        return this.getDescription();
    }

    @Override
    public String getDisplayInfo() {
        return this.getDescription();
    }

    @Override
    public int compareTo(Keyword otherKeyword) {
        return this.getDescription().compareTo(otherKeyword.getDescription());
    }

}
