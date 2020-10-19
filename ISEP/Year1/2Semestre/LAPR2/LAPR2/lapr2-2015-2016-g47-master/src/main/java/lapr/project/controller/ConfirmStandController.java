/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.ExhibitorResponsible;

/**
 * Represents the controller to confirm stand.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ConfirmStandController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitor responsible logged in.
     */
    private final ExhibitorResponsible exhibitorResponsible;

    /**
     * The exhibition applicaiton to decide stand.
     */
    private ExhibitionApplication exhibitionApplication;

    /**
     * The stand decision.
     */
    private boolean standDecision;

    /**
     * Create an instance of confirm stand controller.
     *
     * @param exhibitionCenter the exhibition center
     * @param exhibitorResponsible the exhibitor responsible
     */
    public ConfirmStandController(ExhibitionCenter exhibitionCenter, ExhibitorResponsible exhibitorResponsible) {
        this.exhibitionCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
    }

    /**
     * Gets the exhibition applications on assigned stand state by exhibitor
     * responsible.
     *
     * @return exhibition applications list
     */
    public List<ExhibitionApplication> getExhibitionApplicationsByExhibitorResponsible() {
        ExhibitionsRegister exhibitionsRegister = this.exhibitionCenter.getExhibitionsRegister();
        return exhibitionsRegister.getExhibitionApplicationsAssignedStandByExhibitorResponsible(this.exhibitorResponsible);
    }

    /**
     * Sets the selected exhibition application.
     *
     * @param exhibitionApplication the exhibition application
     */
    public void setExhibitionApplication(ExhibitionApplication exhibitionApplication) {
        this.exhibitionApplication = exhibitionApplication;
    }

    /**
     * Sets the decision to the stand.
     *
     * @param decision decision to the stand
     */
    public void setStandDecision(boolean decision) {
        this.standDecision = decision;
    }

    /**
     * Confirms the decision to the stand.
     */
    public void confirmStandDecision() {
        this.exhibitionApplication.confirmStand(this.standDecision);
    }
}