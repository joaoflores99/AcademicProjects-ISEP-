/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.Place;
import lapr.project.model.Resource;
import lapr.project.model.ResourcesRegister;

/**
 * Represents the controller to create a newDemonstration.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateDemonstrationController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The organizer logged in this session.
     */
    private final Organizer organizer;

    /**
     * The selected exhibition.
     */
    private Exhibition selectedExhibition;

    /**
     * The new newDemonstration
     */
    private Demonstration newDemonstration;

    /**
     * Constructs a CreateDemonstrationController Class.
     *
     * @param exhibitionCenter Exhibition Center
     * @param organizer Logged in organizer
     */
    public CreateDemonstrationController(ExhibitionCenter exhibitionCenter, Organizer organizer) {
        this.exhibitionCenter = exhibitionCenter;
        this.organizer = organizer;
    }

    /**
     * Obtain the exhibition center.
     *
     * @return the exhibition center
     */
    public ExhibitionCenter getExhibitionCenter() {
        return exhibitionCenter;
    }

    /**
     * Obtain the selected exhibition.
     *
     * @return the selected exhibition
     */
    public Exhibition getSelectedExhibition() {
        return selectedExhibition;
    }

    /**
     * Set the selected exhibition.
     *
     * @param selectedExhibition the selected exhibition to set
     */
    public void setSelectedExhibition(Exhibition selectedExhibition) {
        this.selectedExhibition = selectedExhibition;
    }

    /**
     * Obtain the newDemonstration.
     *
     * @return the newDemonstration
     */
    public Demonstration getDemonstration() {
        return newDemonstration;
    }

    /**
     * Set the newDemonstration.
     *
     * @param demonstration the newDemonstration to set
     */
    public void setDemonstration(Demonstration demonstration) {
        this.newDemonstration = demonstration;
    }

    /**
     * Obtain a list of filtered exhibitions by organizer & state.
     *
     * @param organizer to filter
     * @return a list of filtered exhibitions by organizer & state
     */
    public List<Exhibition> getExhibitionsList(Organizer organizer) {

        ExhibitionsRegister exhibitionsRegister = this.getExhibitionCenter().getExhibitionsRegister();

        return exhibitionsRegister.getExhibitionsListWithoutDemosDefined(organizer);
    }

    /**
     * Create a new newDemonstration.
     */
    public void newDemonstration() {

        DemonstrationsList demonstrationsList = this.getSelectedExhibition().getDemonstrationsList();

        this.setDemonstration(demonstrationsList.newDemonstration());
    }

    /**
     * Set Demonstration Data.
     *
     * @param title Demonstration's title
     * @param description Demonstration's description
     * @param place Demonstration's location
     */
    public void setData(String title, String description, Place place) {

        this.newDemonstration.setDescription(description);
        this.newDemonstration.setTitle(title);
        this.newDemonstration.setPlace(place);
    }

    /**
     * Obtain resouces list.
     *
     * @return resouces list
     */
    public List<Resource> getResoucesList() {

        ResourcesRegister resourcesRegister = this.exhibitionCenter.getResourcesRegister();

        return resourcesRegister.getResourcesList();
    }

    /**
     * Adds a resouce to the list if it doesn't already contain that resouce.
     *
     * @param resource the resource to add
     * @return true if the resource is added
     */
    public boolean addResource(Resource resource) {

        return this.newDemonstration.addResource(resource);
    }

    /**
     * Removes a resouce from the list if it contain that resouce.
     *
     * @param index index of the resource to remove
     * @return true if the resource is removed
     */
    public boolean removeResource(int index) {

        return this.newDemonstration.removeResource(index);
    }

    /**
     * Validate and change state of new Demonstration.
     *
     * @return true if new Demonstration is valid and in created state.
     */
    public boolean validateAndChangeStateDemonstration() {

        if (this.newDemonstration.validate()) {
            return this.newDemonstration.setCreated();
        }
        return false;
    }

    /**
     * Register new Demonstration.
     *
     * @return true if Demonstration is successfully added & validated.
     */
    public boolean registerDemonstration() {

        return this.selectedExhibition.getDemonstrationsList().addAndValidateDemonstration(this.newDemonstration);
    }

    /**
     * Sets the new state of the selected exhibition.
     * 
     * @return true if state changes
     */
    public boolean setDemonstrationDefined() {

        return this.selectedExhibition.setDefinedDemos();
    }
}
