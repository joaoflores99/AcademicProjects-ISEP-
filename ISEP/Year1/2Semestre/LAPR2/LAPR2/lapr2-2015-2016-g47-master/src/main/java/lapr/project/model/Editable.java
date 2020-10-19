/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.List;

/**
 * Interface for editable applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public interface Editable {

    /**
     * Verify is a given exhibitor responsivle is the exhibitor responsible of
     * the editable.
     *
     * @param exhibitorResponsible exhibitor responsible
     * @return true if he/she is the exhibitor responsible, false otherwise
     */
    boolean isExhibitorResponsible(ExhibitorResponsible exhibitorResponsible);

    /**
     * Verify if the editable is in submission state.
     *
     * @return true if it is on submission state, false otherwise
     */
    boolean isInSubmission();

    /**
     * Validate if this editable is valid.
     *
     * @return true if it is valid, false otherwise
     */
    boolean validate();

    /**
     * Clone this editable.
     *
     * @return copy of the editable
     */
    public Editable cloneToEditable();

    /**
     * Gets the products lists.
     *
     * @return products lists
     */
    public List<Product> getProductsList();

    /**
     * Gets the title of the application.
     *
     * @return application title
     */
    public String getTitle();

    /**
     * Gets the invitations number.
     *
     * @return invitations number
     */
    public int getNumberInvitations();

    /**
     * Gets the keywords list.
     *
     * @return keywords list
     */
    public List<Keyword> getKeywordsList();

    /**
     * Gets the keywords in comma separated value format.
     * 
     * @return keywords in comma separated value format
     */
    public String getKeywordsCSV();

    public void setTitle(String title);

    public void setNumberInvitations(int invitationsNumber);

    public void setKeywordsList(List<Keyword> keywords);

    public void setProductsList(List<Product> productsList);
}
