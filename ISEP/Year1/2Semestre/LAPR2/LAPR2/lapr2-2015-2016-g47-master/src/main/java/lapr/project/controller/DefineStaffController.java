/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.StaffList;
import lapr.project.model.StaffMember;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;



/**
 * Represents the controller to define staff.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineStaffController {
    
    /**
     * The Exhibition Center
     */
    private final ExhibitionCenter exhibitionCenter;
    
    /**
     * The Exhibitions Register
     */
    private ExhibitionsRegister exhibitionsRegister;
    
    /**
     * The Exhibition
     */
    private Exhibition exhibition;
    
    /**
     * The Organizer
     */
    private final Organizer organizer;
    
    
    /**
     * The Staff Member
     */
    private StaffMember staffMember;
    
    /**
     *  The Staff List
     */
    private StaffList staffList;
    
    /**
     * Constructs a DefineStaffController Class.
     * 
     * @param organizer Organizer
     * @param exhibitionCenter Exhibition Center
     */
    public DefineStaffController(Organizer organizer, ExhibitionCenter exhibitionCenter){
        this.organizer = organizer;
        this.exhibitionCenter = exhibitionCenter;
        this.staffMember = new StaffMember();
    }
    
    /**
     * Gets the list of exhibitions where you are Organizer and they are without staff member defined state
     * @param organizer the organizer to check for
     * @return the list of exhibitions
     */
    public List<Exhibition> getExhibitionList(Organizer organizer){
        this.exhibitionsRegister=exhibitionCenter.getExhibitionsRegister();
        return this.exhibitionsRegister.getExhibitionsListWithoutStaffMemberByOrganizer(organizer);
        
    }
    
    /**
     * Return a users list.
     * 
     * @return usersList
     */
    public List<User> getUserList(){
        
        UsersRegister usersRegister = this.exhibitionCenter.getUsersRegister();
        
        List<User> usersList = usersRegister.getUsersList(true);
        
        for (Organizer organizer : exhibition.getOrganizersList().getOrganizersList()) {
            usersList.remove(organizer.getUser());
        }
        
        return usersList;
        
    }
    
    /**
     * Creates a new instance of staff member.
     * 
     * @param user 
     */
    public boolean newStaffMember(User user){
        
        this.staffList = exhibition.getStaffList();
        this.staffMember = staffList.newStaffMember(user);
        return true;
    }

    /**
     * Sets exhibition.
     * 
     * @param exhibition 
     */
    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }
    
    /**
     * Adds staff member.
     * @return True if added
     */
    public boolean addStaffMember(){
       return this.staffList.addStaffMember(staffMember);
    }
    
    /**
     * Sets the new state on the exhibition
     * @param exhibition the exhibition we want to set the state
     * @return true if state
     */
    public boolean setDefinedStaffMember(Exhibition exhibition){
         return exhibition.getState().setStaffDefined();
    }
}
