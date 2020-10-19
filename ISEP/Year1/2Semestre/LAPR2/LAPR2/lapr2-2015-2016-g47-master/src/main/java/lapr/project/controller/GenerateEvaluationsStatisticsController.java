/**
 * Package location for AssignStandsController concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Actor;
import lapr.project.model.ApplicationAnalysis;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;

/**
 * Controller to generate evaluations statistics
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class GenerateEvaluationsStatisticsController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The actor logged in.
     */
    private final Actor actor;

    /**
     * The selected exhibition.
     */
    private Exhibition exhibition;

    /**
     * Constructs an instance of generate evaluations statistics controller.
     *
     * @param exhibitionCenter the exhibition center.
     * @param actor the actor.
     */
    public GenerateEvaluationsStatisticsController(ExhibitionCenter exhibitionCenter, Actor actor) {
        this.exhibitionCenter = exhibitionCenter;
        this.actor = actor;
    }

    public List<Exhibition> getExhibitionsList() {
        ExhibitionsRegister exhibitionsRegister = this.exhibitionCenter.getExhibitionsRegister();
        List<Exhibition> exhibitions = null;

        if (this.actor instanceof ExhibitionsManager) {
            exhibitions = exhibitionsRegister.getDecidedExhibitions();
        } else if (this.actor instanceof Organizer) {
            exhibitions = exhibitionsRegister.getDecidedExhibitionsByOrganizer((Organizer) this.actor);
        }

        return exhibitions;
    }

    /**
     * Select an exhibition.
     *
     * @param exhibition selected exhibiton
     */
    public void selectExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public float getAcceptanceRate() {
        return this.exhibition.getAcceptanceRate();
    }

    public List<ApplicationAnalysis> getApplicationsAnalysis() {
        return this.exhibition.getApplicationsAnalysis();
    }
}
