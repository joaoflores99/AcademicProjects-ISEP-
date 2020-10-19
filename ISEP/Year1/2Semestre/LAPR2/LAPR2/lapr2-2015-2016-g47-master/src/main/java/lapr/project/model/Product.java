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
 * Represents a product.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Selectable, Serializable {

    /**
     * The product's designation.
     */
    @XmlAttribute
    private String designation;

    /**
     * The product's designation by default.
     */
    private static final String DEFAULT_DESIGNATION = "no designation";

    /**
     * Default constructor of a Product class.
     */
    public Product() {
        this.designation = DEFAULT_DESIGNATION;
    }

    /**
     * Constructor that receives an instance of this class as parameter.
     *
     * @param designation product's designation
     */
    public Product(String designation) {
        this.designation = designation;
    }

    /**
     * Obtain the product's designation.
     *
     * @return the product's designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the product's designation.
     *
     * @param designation the product's designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
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
        Product otherProduct = (Product) otherObject;
        return this.designation.equals(otherProduct.designation);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.designation);
        return hash;
    }

    public boolean validate() {
        return this.designation != null;
    }

    @Override
    public String toString() {
        return String.format("Product{%ndesignation=%s%n", this.designation);
    }

    @Override
    public String getDisplayInfo() {
        return this.getDesignation();
    }

}
