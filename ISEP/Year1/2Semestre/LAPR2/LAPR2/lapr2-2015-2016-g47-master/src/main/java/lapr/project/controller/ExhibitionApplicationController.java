/**
 * Package location for Controller concepts.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.ApplicationsList;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Keyword;
import lapr.project.model.Product;

/**
 * Represents the controller of exhibition application.
 *
 * @author Daniel GonÃ§alves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionApplicationController {

    /**
     * The exhibition center of this instance.
     */
    private ExhibitionCenter exhibitonCenter;

    /**
     * The exhibitions register referering to this exhibition center
     */
    private ExhibitionsRegister exhibitionsRegister;

    /**
     * The list of exhibitions contained in this instance.
     */
    private List<Exhibition> exhibitionList;

    /**
     * The class reference to applications list.
     */
    private ApplicationsList applicationList;

    /**
     * A temporary exhibition application reference.
     */
    private ExhibitionApplication exhibitionApplication;
    /**
     * The exhibitor responsible.
     */
    private ExhibitorResponsible exhibitorResponsible;

    /**
     * Constructor receiving a ExhibitionCenter as parameter.
     *
     * @param exhibitionCenter the exhibition center received by parameter.
     */
    public ExhibitionApplicationController(ExhibitorResponsible exhibitorResponsible, ExhibitionCenter exhibitionCenter) {
        this.exhibitonCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
    }

    /**
     * Gets this instance's exhibition application.
     *
     * @return the exhibiton application
     */
    public ExhibitionApplication getExhibitionApplication() {
        return exhibitionApplication;
    }

    /**
     * Gets the list of exhibitions contained in the ExhibitionCenter
     *
     * @return returns a new list containing the list of exhibitions
     */
    public List<Exhibition> getExhibitionList() {
        this.exhibitionsRegister = exhibitonCenter.getExhibitionsRegister();
        this.exhibitionList = exhibitionsRegister.getExhibitionListWithApplicationInSubmittingState();
        return new ArrayList(exhibitionList);
    }

    /**
     * Creates a new Application.
     */
    public void newApplication(Exhibition exhibition) {
        this.applicationList = exhibition.getApplicationsList();
        this.exhibitionApplication = (ExhibitionApplication) applicationList.newExhibitionApplication();
    }

    /**
     * Sets the data of the new application.
     */
    public void setData(String title, String companyName, String companyAddress, String companyCellphone, float exhibitorArea, int numberInvitations, ExhibitorResponsible exhibitorResponsible) {
        this.exhibitionApplication.setTitle(title);
        this.exhibitionApplication.newExhibitor(companyName, companyAddress, companyCellphone, exhibitorResponsible);
        this.exhibitionApplication.setExhibitorArea(exhibitorArea);
        this.exhibitionApplication.setNumberInvitations(numberInvitations);
    }

    /**
     * Creates a new product.
     *
     * @param designation the product designation
     */
    public boolean newProduct(String designation) {

        return this.exhibitionApplication.newProduct(designation);
    }

    /**
     * removes a product
     *
     * @param designation the product designation
     * @return true if removed
     */
    public boolean removeProduct(String designation) {
        return this.exhibitionApplication.removeProduct(designation);
    }

    /**
     * Lists the demonstrations on a exhibition
     *
     * @param exhibition the exhibition you want the demonstrations from
     * @return a copy of the demonstrations arraylist
     */
    public DemonstrationsList getDemonstrationsList(Exhibition exhibition) {
        return new DemonstrationsList(exhibition.getDemonstrationsList());
    }

    /**
     * sets the demonstraitons list.
     *
     * @param demonstrationsList the demonstrations list
     */
    public void setDemonstrationsList(List<Demonstration> demonstrationsList) {
        this.exhibitionApplication.setDemonstrationsList(demonstrationsList);
    }

    /**
     * Adds a demonstration to this application.
     *
     * @param demonstration the demonstration that is going to be added to the
     * list on the application
     */
    public void newDemonstrationApplication(Demonstration demonstration) {
        this.exhibitionApplication.newDemonstration(demonstration);
    }

    /**
     * Creates a new keyword and adds it to the list.
     *
     * @param description the value for the keyword
     */
    public boolean newKeyword(String description) {
        return this.exhibitionApplication.newKeyword(description);
    }

    /**
     * Validates this exhibition application locally and globally.
     *
     * @return true if all conditions to add the applications are fulfilled
     */
    public boolean validateExhibitionApplication() {
        return this.exhibitionApplication.validateApplication() && !this.applicationList.getApplicationsList().contains(this.exhibitionApplication);

    }

    /**
     * Sets the new state and adds the application to the list.
     *
     * @return true if all O.K.
     */
    public boolean registerExhibitionApplication() {
        if (this.exhibitionApplication.getCurrentState().setInSubmission()) {
            if (validateExhibitionApplication()) {
                this.applicationList.getApplicationsList().add(exhibitionApplication);
                 return true;
            }
           
        }
        return false;
    }

    /**
     * Returns the list of products
     *
     * @return the list of products
     */
    public List<Product> getProductsList() {
        return this.exhibitionApplication.getProductsList();
    }

    /**
     * Returns the keyword list.
     *
     * @return the list of keyword
     */
    public List<Keyword> getKeyWordList() {
        return this.exhibitionApplication.getKeywordsList();
    }
}
