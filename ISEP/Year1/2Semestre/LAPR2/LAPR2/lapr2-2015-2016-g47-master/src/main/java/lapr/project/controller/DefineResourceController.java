/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Resource;
import lapr.project.model.ResourcesRegister;

/**
 * Represents the controller to define resource.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineResourceController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * the resources register.
     */
    private final ResourcesRegister resourcesRegister;

    /**
     * The resource to be defined.
     */
    private Resource resource;

    /**
     * Constructs a define resource controller.
     *
     * @param exhibitionCenter exhibition center
     */
    public DefineResourceController(ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
        this.resourcesRegister = this.exhibitionCenter.getResourcesRegister();
    }

    /**
     * Gets the resources list.
     *
     * @return resources list
     */
    public List<Resource> getResources() {
        return this.resourcesRegister.getResourcesList();
    }

    /**
     * Creates a new resource with a description, validating the resource.
     *
     * @param description description for the resource
     * @return true if the resource is valid, false otherwise
     */
    public boolean newResource(String description) {
        this.resource = this.resourcesRegister.newResource();
        this.resource.setDesignation(description);
        return this.resourcesRegister.validateResource(this.resource);
    }

    /**
     * Register a resource.
     *
     * @return true if it is successful registered, false otherwise
     */
    public boolean registerResource() {
        return this.resourcesRegister.registerResource(this.resource);
    }

    /**
     * Removes a given resource.
     *
     * @param resource resource to be removed
     * @return true if it is removed with success, false otherwise
     */
    public boolean removeResource(Resource resource) {
        return this.resourcesRegister.removeResource(resource);
    }
}
