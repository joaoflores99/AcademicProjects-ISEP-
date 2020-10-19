/**
 * Package location for Login Controller concepts.
 */
package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.ApplicationsList;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.OrganizersList;
import lapr.project.model.StaffList;
import lapr.project.model.StaffMember;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;

/**
 * Represents a login controller
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class LoginController {

    /**
     * The exhibitions center.
     */
    ExhibitionCenter exhibitionCenter;
    /**
     * The users register.
     */
    UsersRegister usersRegister;

    /**
     * The exhibitions register
     */
    ExhibitionsRegister exhibitionsRegister;

    /**
     * Builds instance of this class receiving an exhibition center as param.
     *
     * @param exhibitionCenter the exhibition center
     */
    public LoginController(ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsRegister = exhibitionCenter.getExhibitionsRegister();
    }

    /**
     * Verifies if the enter information belongs to a user.
     *
     * @param userName the username
     * @param password the password
     * @return the user
     */
    public User verifyLogin(String userName, String password) {
        usersRegister = this.exhibitionCenter.getUsersRegister();

        for (User user : usersRegister.getUsersList()) {
            if (user.getConfirmedStatus()) {
                if (userName.equals(user.getUsername()) && user.comparePassword(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * Verifies if the user is a staff member.
     *
     * @param user the user
     * @return true if user is a staff member, false otherwise
     */
    public boolean verifyUserByStaffMember(User user) {
        for (Exhibition exhibition : this.exhibitionsRegister.getExhibitionsList()) {
            StaffList staffList = exhibition.getStaffList();
            for (StaffMember staffMember : staffList.getStaffList()) {
                if (user.equals(staffMember.getUser())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifies if the user is an Organizer.
     *
     * @param user the user
     * @return true if user is an organizer, false otherwise
     */
    public boolean verifyUserByOrganizer(User user) {
        for (Exhibition exhibition : exhibitionsRegister.getExhibitionsList()) {
            OrganizersList organizersList = exhibition.getOrganizersList();
            for (Organizer organizer : organizersList.getOrganizersList()) {
                if (user.equals(organizer.getUser())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifies if the user is an Exhibitor responsible.
     *
     * @param user the user
     * @return true if user is an Exhibitor responsible, false otherwise
     */
    public boolean verifyUserByExhibitorResponsible(User user) {
        for (Exhibition exhibition : exhibitionsRegister.getExhibitionsList()) {
            ApplicationsList applicationsList = exhibition.getApplicationsList();
            for (Application application : applicationsList.getApplicationsList()) {
                if (user.equals(application.getExhibitor().getExhibitorResponsible().getUser())) {
                    return true;
                }
            }
            DemonstrationsList demonstrationsList = exhibition.getDemonstrationsList();
            for (Demonstration demonstration : demonstrationsList.getDemonstrationsList()) {
                applicationsList = demonstration.getApplicationsList();
                for (Application application : applicationsList.getApplicationsList()) {
                    if (user.equals(application.getExhibitor().getExhibitorResponsible().getUser())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verifies user by exhibitions Manager.
     *
     * @param user the user
     * @return the exhibitions manager if user is one
     */
    public boolean verifyUserByExhibitionsManager(User user) {
        for (ExhibitionsManager exhibitionsManager : exhibitionCenter.getExhibitionsManagerRegister().getExhibitionsManagerList()) {
            if (user.equals(exhibitionsManager.getUser())) {
                return true;
            }
        }
        return false;
    }

}
