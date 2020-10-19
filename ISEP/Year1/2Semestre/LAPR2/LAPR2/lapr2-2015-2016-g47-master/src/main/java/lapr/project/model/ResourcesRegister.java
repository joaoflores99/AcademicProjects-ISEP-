/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Resources Register.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ResourcesRegister implements Serializable {

    /**
     * resources List of ResourcesRegister
     */
    private List<Resource> resourcesList;

    /**
     * Creates an instance of ResourceRegister with its default values.
     */
    public ResourcesRegister() {
        resourcesList = new ArrayList<>();
    }

    /**
     * Creates an instance of ResourcesRegister receiving a list of resources.
     *
     * @param resourcesList list of resources
     */
    public ResourcesRegister(List<Resource> resourcesList) {
        this.resourcesList = new ArrayList<>(resourcesList);
    }

    /**
     * Creates an instance of ResourcesRegister copying another resources
     * register.
     *
     * @param resourcesRegister another resource register
     */
    public ResourcesRegister(ResourcesRegister resourcesRegister) {
        this.resourcesList = new ArrayList<>(resourcesRegister.resourcesList);
    }

    /**
     * Gets the resources list.
     *
     * @return List of resources
     */
    public List<Resource> getResourcesList() {
        return new ArrayList<>(resourcesList);
    }

    /**
     * Sets the resources list.
     *
     * @param resourcesList list of resources
     */
    public void setResourcesList(List<Resource> resourcesList) {
        this.resourcesList = new ArrayList<>(resourcesList);
    }

    /**
     * Creates an resouce with its default values.
     *
     * @return new resource
     */
    public Resource newResource() {
        return new Resource();
    }

    /**
     * Validate if a resource is valid.
     *
     * @param resource resource to be validate
     * @return true if it is valid, false otherwise
     */
    public boolean validateResource(Resource resource) {
        return resource.validate() && validate(resource);
    }

    /**
     * Validate if the resource can be defined on the resources list.
     *
     * @param resource the resource to be validate
     * @return true if its valid, false otherwise
     */
    private boolean validate(Resource resource) {
        return !this.resourcesList.contains(resource);
    }

    /**
     * Register a resource.
     *
     * @param resource resource to be registered
     * @return true if it is successfull registered, false otherwise
     */
    public boolean registerResource(Resource resource) {
        return !this.resourcesList.contains(resource) ? addResource(resource) : false;
    }

    /**
     * Adds a resource to the resources list.
     *
     * @param resource resource to be added
     * @return true if it is successfull added, false otherwise
     */
    private boolean addResource(Resource resource) {
        return this.resourcesList.add(resource);
    }

    /**
     * Removes a given resourse.
     *
     * @param resource resource to be removed.
     * @return true if it is successfull removed, false otherwise.
     */
    public boolean removeResource(Resource resource) {
        return this.resourcesList.contains(resource) ? this.resourcesList.remove(resource) : false;
    }

    /**
     * Return the textual representation of this resource register.
     *
     * @return the textual representation of this resource register
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ResourcesRegister{");
        for (Resource resource : resourcesList) {
            s.append(String.format("%s%n", resource));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares if the given object is equal to this resource register.
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
        ResourcesRegister otherResourcesRegister = (ResourcesRegister) otherObject;

        return this.resourcesList.equals(otherResourcesRegister.resourcesList);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.resourcesList);
        return hash;
    }

}
