/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import javafx.util.Pair;
import lapr.project.model.ApplicationsList;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Keyword;
import lapr.project.model.Submittable;

/**
 * Represents the controller to generate keyword rankings.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class GenerateKeywordRankingsController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The submittable's applications list.
     */
    private ApplicationsList applicationsList;

    /**
     * Constructs a GenerateKeywordRankingsController Class.
     *
     * @param exhibitionCenter Exhibition Center
     */
    public GenerateKeywordRankingsController(ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter = exhibitionCenter;
    }

    /**
     * Obtain the exhibition center.
     *
     * @return the exhibition center
     */
    public ExhibitionCenter getExhibitionCenter() {
        return exhibitionCenter;
    }

    /**
     * Obtain list of submittables.
     *
     * @return list of submittables.
     */
    public List<Submittable> getDecidedSubmittables() {

        ExhibitionsRegister exhibitionsRegister = exhibitionCenter.getExhibitionsRegister();

        return exhibitionsRegister.getDecidedSubmittables();
    }

    /**
     * Set the applications list to which we are going to calculate the
     * rankings.
     *
     * @param submittable the submittable that contains the required
     * applications list
     */
    public void setApplicationsList(Submittable submittable) {

        this.applicationsList = submittable.getApplicationsList();
    }

    /**
     * Calculate submittable's accepted applications keywords ranking.
     *
     * @return submittable's accepted applications keywords ranking.
     */
    public List<Pair<Keyword, Integer>> calcuateAcceptedAppsRanking() {

        return this.applicationsList.calculateAcceptedAppsKeywordsRankings();
    }

    /**
     * Calculate submittable's declined applications keywords ranking.
     *
     * @return submittable's declined applications keywords ranking.
     */
    public List<Pair<Keyword, Integer>> calcuateDeclinedAppsRanking() {

        return this.applicationsList.calculateDeclinedAppsKeywordsRankings();
    }
}
