/**
 * Package location for Import Exhibition Controller concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.Conflict;
import lapr.project.model.Demonstration;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.StaffAttribution;
import lapr.project.model.StaffMember;
import lapr.project.model.UsersRegister;

/**
 * Represents a import exhibitions controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ImportExhibitionController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

//    /**
//     * The exhibition manager.
//     */
//    private ExhibitionsManager exhibitionsManager;
    /**
     * The exhibitions Register.
     */
    private ExhibitionsRegister exhibitionsRegister;

    /**
     * Temporary exhibition variable.
     */
    private Exhibition exhibition;

    /**
     * Builds instance of this class receiving an exhibitions manager and
     * exhibition center as parameters.
     *
     * @param exhibitionsManager the exhibitions manager
     * @param exhibitionCenter the exhibition center
     */
    public ImportExhibitionController(ExhibitionsManager exhibitionsManager, ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
//        this.exhibitionsManager = exhibitionsManager;
    }

    /**
     * Gets the exhibitions register for this instance of exhibition center.
     */
    private void getExhibitionsRegister() {
        this.exhibitionsRegister = this.exhibitionCenter.getExhibitionsRegister();

    }

    /**
     * Reads a exhibition from a file.
     *
     * @param filePath The file path on the system
     */
    public boolean readExhibitionFromFile(String filePath) {
        getExhibitionsRegister();
        this.exhibition = this.exhibitionsRegister.importByFileName(filePath, this.exhibitionCenter);
        return exhibition != null;
    }

    /**
     * Registers the selected exhibition.
     *
     * @param exhibition The exhibition to register
     */
    public boolean registerExhibition(Exhibition exhibition) {

        boolean registered = this.exhibitionsRegister.registerImportedExhibition(exhibition);

        if (registered) {
            registerUsers(exhibition);
        }

        return registered;
    }

    private void registerUsers(Exhibition exhibition) {

        UsersRegister usersRegister = exhibitionCenter.getUsersRegister();

        for (Organizer organizer : exhibition.getOrganizersList().getOrganizersList()) {

            usersRegister.registerUser(organizer.getUser());
        }
        for (StaffMember member : exhibition.getStaffList().getStaffList()) {
            usersRegister.registerUser(member.getUser());
        }
        for (Application application : exhibition.getApplicationsList().getApplicationsList()) {
            usersRegister.registerUser(application.getExhibitor().getExhibitorResponsible().getUser());
        }

    }

    /**
     * Gets current exhibition
     *
     * @return this exhibition
     */
    public Exhibition getExhibition() {
        return exhibition;
    }

    /**
     * Gets the staff list.
     *
     * @param exhibition the exhibition
     * @return the staff list
     */
    public List<StaffMember> getStaffList(Exhibition exhibition) {
        return exhibition.getStaffList().getStaffList();
    }

    /**
     * Gets the organizers list.
     *
     * @param exhibition the exhibition
     * @return the organizers list
     */
    public List<Organizer> getOrganizersList(Exhibition exhibition) {
        return exhibition.getOrganizersList().getOrganizersList();
    }

    /**
     * Gets the applications list.
     *
     * @param exhibition the exhibition
     * @return the applications list
     */
    public List<Application> getApplicationsList(Exhibition exhibition) {
        return exhibition.getApplicationsList().getApplicationsList();
    }

    /**
     * Gets the demonstrations list.
     *
     * @param exhibition the exhibition
     * @return the demonstrations list
     */
    public List<Demonstration> getDemonstrationsList(Exhibition exhibition) {
        return exhibition.getDemonstrationsList().getDemonstrationsList();
    }

    /**
     * Gets the staff attributions list.
     *
     * @param exhibition the exhibition
     * @return the staff attributions list
     */
    public List<StaffAttribution> getStaffAttributionsList(Exhibition exhibition) {
        return exhibition.getStaffAttributionsList().getStaffAttributionsList();
    }

    /**
     * Gets the conflicts list.
     *
     * @param exhibition the exhibition
     * @return the conflicts list
     */
    public List<Conflict> getConflictsList(Exhibition exhibition) {
        return exhibition.getConflictsList().getConflictsList();
    }

}
