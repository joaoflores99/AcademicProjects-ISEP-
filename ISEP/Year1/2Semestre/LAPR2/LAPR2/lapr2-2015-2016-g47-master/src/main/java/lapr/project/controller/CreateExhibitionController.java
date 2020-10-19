/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.Date;
import java.util.List;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.Place;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;

/**
 * Represents the controller to create exhibitions.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateExhibitionController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The new exhibition object.
     */
    private Exhibition exhibition;

    /**
     * Constructs a CreateExhibitionController Class.
     *
     * @param exhibitionCenter Exhibition Center
     */
    public CreateExhibitionController(ExhibitionCenter exhibitionCenter) {

        this.exhibitionCenter = exhibitionCenter;
    }

    /**
     * Creates a new exhibition object.
     */
    public void newExhibition() {

        ExhibitionsRegister exhibitionRegister = getExhibitionCenter().getExhibitionsRegister();

        this.exhibition = exhibitionRegister.newExhibition();
    }

    /**
     * Obtain the Exhibition Center.
     *
     * @return the Exhibition Center
     */
    public ExhibitionCenter getExhibitionCenter() {
        return this.exhibitionCenter;
    }

    /**
     * Obtain the new Exhibition.
     *
     * @return the Exhibition
     */
    public Exhibition getExhibition() {
        return this.exhibition;
    }

    /**
     * Set the Exhibition.
     *
     * @param exhibition the exhibtion to set
     */
    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    /**
     * Obtain users list.
     *
     * @return users list
     */
    public List<User> getUsersList() {

        UsersRegister usersRegister = exhibitionCenter.getUsersRegister();

        return usersRegister.getUsersList(true);
    }

    /**
     * Obtain organizers list.
     *
     * @return organizers list
     */
    public List<Organizer> getOrganizersList() {

        return this.exhibition.getOrganizersList().getOrganizersList();
    }

    /**
     * Set the exhibition data.
     *
     * @param title Exhibition's title
     * @param description Exhibition's description
     * @param place Exhibition's location
     * @param startDate Exhibition's start date
     * @param endDate Exhibition's end date
     * @param startSubDate Exhibition's applications submission start date
     * @param endSubDate Exhibition's applications submission end date
     * @param conflictLimitDate Exhibition's conflicts limit date
     * @param evaluationLimiteDate Exhibition's applications evaluation limit
     * date
     */
    public void setData(String title, String description, Place place,
            Date startDate, Date endDate, Date startSubDate, Date endSubDate,
            Date conflictLimitDate, Date evaluationLimiteDate) {

        this.exhibition.setTitle(title);
        this.exhibition.setDescription(description);
        this.exhibition.setPlace(place);
        this.exhibition.setStartDate(startDate);
        this.exhibition.setEndDate(endDate);
        this.exhibition.setSubStartDate(startSubDate);
        this.exhibition.setSubEndDate(endSubDate);
        this.exhibition.setConflictLimitDate(conflictLimitDate);
        this.exhibition.setEvaluationLimitDate(evaluationLimiteDate);
    }

    /**
     * Create, validate & add a new organizer object to the Exhibition's
     * organizersList.
     *
     * @param user associated to the new organizer
     * @return true if organizer is added to list.
     */
    public boolean newOrganizer(User user) {

        Organizer newOrganizer = exhibition.getOrganizersList().newOrganizer(user);

        return exhibition.getOrganizersList().addAndValidateOrganizer(newOrganizer);
    }

    /**
     * Remove a organizer from the Exhibition's organizersList.
     *
     * @param index index of the organizer to remove.
     * @return true if organizer is removed from the list.
     */
    public boolean removeOrganizer(int index) {

        return exhibition.getOrganizersList().removeOrganizer(index);
    }

    /**
     * Validate the Exhibition.
     *
     * @return true if the exhibition is valid
     */
    public boolean validateExhibition() {
        return this.exhibition.validate();
    }

    /**
     * Register exhibition.
     *
     * @return true if exhibition is successfully added & validated.
     */
    public boolean registerExhibition() {

        return this.exhibitionCenter.getExhibitionsRegister().registerExhibition(this.exhibition, this.exhibitionCenter);
    }
}
