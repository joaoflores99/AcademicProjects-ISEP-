/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ApplicationsList;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Editable;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Submittable;

/**
 * Represents the controller to edit applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class EditApplicationController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitor responsible logged in.
     */
    private final ExhibitorResponsible exhibitorResponsible;

    /**
     * The selected submittable.
     */
    private Submittable submittable;

    /**
     * The applications list of the selected submittable.
     */
    private ApplicationsList applicationsList;

    /**
     * The editable in the previous conditions.
     */
    private Editable oldEditable;

    /**
     * The editable with the new modifications.
     */
    private Editable newEditable;

    /**
     * Construct an edit application controller.
     *
     * @param exhibitionCenter the exhibition center
     * @param exhibitorResponsible the exhibitor responsible
     */
    public EditApplicationController(ExhibitionCenter exhibitionCenter, ExhibitorResponsible exhibitorResponsible) {
        this.exhibitionCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
    }

    /**
     * Gets the submittable by exhibitor responsible.
     *
     * @return submittables list
     */
    public List<Submittable> getSubmittablesByExhibitorResponsible() {
        ExhibitionsRegister exhibitionsRegister = this.exhibitionCenter.getExhibitionsRegister();
        return exhibitionsRegister.getSubmittablesApplicationInSubmissionByExhibitorResponsible(this.exhibitorResponsible);
    }

    /**
     * Seletc the submittable
     *
     * @param submittable submittable
     */
    public void setSubmittable(Submittable submittable) {
        this.submittable = submittable;
    }

    /**
     * Clones the editable of the exhibitor responsible.
     *
     * @return
     */
    public Editable cloneEditable() {
        this.applicationsList = this.submittable.getApplicationsList();
        this.oldEditable = this.applicationsList.getEditableByExhibitorResponsible(exhibitorResponsible);
        return this.applicationsList.cloneEditable(this.oldEditable);
    }

    /**
     * Validate if the edited editable is valid.
     *
     * @param newEditable edited
     * @return true if it is valid, false otherwise
     */
    public boolean validate(Editable newEditable) {
        this.newEditable = newEditable;
        return this.applicationsList.validateEditable(newEditable, this.oldEditable);
    }

    /**
     * Modify the editable.
     *
     * @return true if it is modified with success, false otherwise.
     */
    public boolean modifyEditable() {
        return this.applicationsList.modifyEditable(this.newEditable, this.oldEditable);
    }
    
    /**
     * Gets the available demonstrations in the exhibition.
     * 
     * @return available demonstrations in the exhibition
     */
    public List<Demonstration> getAvailableDemonstrationsInExhibition(){
        if (this.submittable instanceof Exhibition) {
            DemonstrationsList demonstrationsList = ((Exhibition) this.submittable).getDemonstrationsList();
            return demonstrationsList.getDemonstrationsList();
        }
        return null;
    }
}
