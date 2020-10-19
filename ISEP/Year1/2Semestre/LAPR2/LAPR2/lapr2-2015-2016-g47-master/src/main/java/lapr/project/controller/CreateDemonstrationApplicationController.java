/**
 * Package location for Create Demonstration Application Controller concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationsList;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationApplication;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Keyword;
import lapr.project.model.Product;

/**
 * Represents the controller for a demonstration application.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateDemonstrationApplicationController {

    /**
     * The exhibitions register.
     */
    private ExhibitionsRegister exhibitionsRegister;

    /**
     * This instance's reference to exhibition center.
     */
    private ExhibitionCenter exhibitionCenter;

    /**
     * This instance's exhibitor responsible.
     */
    private ExhibitorResponsible exhibitorResponsible;

    /**
     * This instances demonstration application reference.
     */
    private DemonstrationApplication demonstrationApplication;
    /**
     * This instance's application of this exhibitor responsible reference.
     */
    private Application applicationOfExhibitorResponsible;
    /**
     * The applications list.
     */
    ApplicationsList applicationsList;

    /**
     * Creates instance of this class receiving an exhibition center and
     * exhibitor responsible as parameter.
     *
     * @param exhibitionCenter the exhibition center
     * @param exhibitorResponsible the exhibitor responsible.
     */
    public CreateDemonstrationApplicationController(ExhibitorResponsible exhibitorResponsible, ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
    }

    /**
     * Gets the exhibitions list with application in submitting state by
     * exhibitor responsible
     *
     * @return the list of exhibitions
     */
    public List<Exhibition> getExhibitionListWithApplicationsInSubmission() {
        this.exhibitionsRegister = exhibitionCenter.getExhibitionsRegister();
        List<Exhibition> exhibitionsList = exhibitionsRegister.getExhibitionsListWithApplicationsInSubmissionByExhibitorResponsible(this.exhibitorResponsible);
        return exhibitionsList;
    }

    /**
     * Gets the demonstrations list of an exhibitor responsible's application
     *
     * @param exhibition the exhibition to check his application
     * @return the demonstrations list
     */
    public List<Demonstration> getDemonstrationsList(Exhibition exhibition) {
        ApplicationsList applicationsList = exhibition.getApplicationsList();
        applicationOfExhibitorResponsible = applicationsList.getApplicationByExhibitorResponsible(this.exhibitorResponsible);
        return ((ExhibitionApplication) applicationOfExhibitorResponsible).getDemonstrationsList();
    }

    /**
     * Creates a new demonstration application.
     *
     * @param demonstration the demonstration to apply for
     */
    public void newDemonstrationApplication(Demonstration demonstration) {
        this.applicationsList = demonstration.getApplicationsList();
       this.demonstrationApplication = (DemonstrationApplication) this.applicationsList.newDemonstrationApplication();

    }

    /**
     * Sets the data of demonstration application
     *
     * @param title this demonstration title
     * @param numberInvitations this demonstration number of invitations
     */
    public void setData(String title, int numberInvitations) {
        this.demonstrationApplication.setTitle(title);
        this.demonstrationApplication.setNumberInvitations(numberInvitations);

    }

    /**
     * Sets the products list
     *
     * @param productsList
     *
     */
    public void setProductsList(List<Product> productsList) {
        this.demonstrationApplication.setProductsList(productsList);

    }

    /**
     * Sets the keywords list.
     *
     * @param keywordsList the new keywords list
     */
    public void setKeywordsList(List<Keyword> keywordsList) {
        this.demonstrationApplication.setKeywordsList(keywordsList);
    }

    /**
     * Returns the products list
     *
     * @return the products list
     */
    public List<Product> getProductsList() {
        return ((ExhibitionApplication) applicationOfExhibitorResponsible).getProductsList();
    }

    /**
     * Returns the keywords list.
     *
     * @return the keywords list.
     */
    public List<Keyword> getKeywordsList() {
        return ((ExhibitionApplication) applicationOfExhibitorResponsible).getKeywordsList();
    }

    /**
     * Validates and registers the demonstration application.
     * @return the demonstration application.
     */
    public boolean registerDemonstrationApplication() {
        if (this.applicationsList.validateDemonstrationApplication(this.demonstrationApplication)) {
            this.applicationsList.registerDemonstrationApplication(this.demonstrationApplication);
            return true;
        }
        return false;
    }

    /**
     * Returns the demonstration application.
     * @return the demonstration application
     */
    public DemonstrationApplication getDemonstrationApplication() {
        return demonstrationApplication;
    }

    /**
     * Gets the application of the exhibitor Responsible
     * @return the application
     */
    public ExhibitionApplication getApplicationOfExhibitorResponsible() {
        return (ExhibitionApplication)applicationOfExhibitorResponsible;
    }
    
    

}
