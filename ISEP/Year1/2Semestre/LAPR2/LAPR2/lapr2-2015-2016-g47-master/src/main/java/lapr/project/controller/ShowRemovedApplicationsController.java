/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.Submittable;

/**
 * Represents the controller to show the removed removed applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ShowRemovedApplicationsController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The organizer.
     */
    private final Organizer organizer;

    /**
     * The exhibitions register.
     */
    private final ExhibitionsRegister exhibitionsRegister;

    /**
     * The list of submittables filtered by organizer.
     */
    private List<Submittable> submittablesListByOrganizer;

    /**
     * The list of removed applications filtered by organizer.
     */
    private List<Application> removedApplicationsListByOrganizer;

    /**
     * Creates an instance of ShowRemovedApplicationsController.
     *
     * @param exhibitionCenter the exhibition center
     * @param organizer the organizer
     */
    public ShowRemovedApplicationsController(ExhibitionCenter exhibitionCenter, Organizer organizer) {
        this.exhibitionCenter = exhibitionCenter;
        this.organizer = organizer;
        this.exhibitionsRegister = exhibitionCenter.getExhibitionsRegister();
    }

    /**
     * Get the removed applications, filtered by organizer.
     *
     * @param organizer the organizer used to filter
     * @return returns the removed applications list filtered by organizer
     */
    public List<Application> getRemovedApplicationsListByOrganizer(Organizer organizer) {
        this.submittablesListByOrganizer = this.exhibitionsRegister.getSubmittablesListByOrganizer(organizer);

        this.removedApplicationsListByOrganizer = this.exhibitionsRegister.getExhibitionApplicationsRemovedByOrganizer(submittablesListByOrganizer);
        this.removedApplicationsListByOrganizer.addAll(this.exhibitionsRegister.getDemonstraionApplicationsRemovedByOrganizer(submittablesListByOrganizer));

        return this.removedApplicationsListByOrganizer;
    }
}
