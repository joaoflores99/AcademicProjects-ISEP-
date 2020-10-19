/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.Date;
import java.util.List;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.demonstration.DemonstrationCreatedState;

/**
 * Represents the controller to define demonstrations controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineEffectiveDemosController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The selected exhibition.
     */
    private Exhibition selectedExhibition;

    /**
     * The exhibitions demonstrations list.
     */
    private DemonstrationsList demonstrationsList;

    /**
     * The efective demonstration.
     */
    private Demonstration efectiveDemo;

    /**
     * Constructs a DefineEfectiveDemosController Class.
     *
     * @param exhibitionCenter Exhibition Center
     */
    public DefineEffectiveDemosController(ExhibitionCenter exhibitionCenter) {

        this.exhibitionCenter = exhibitionCenter;
        this.demonstrationsList = new DemonstrationsList();
    }

    /**
     * Obtain the Exhibition Center.
     *
     * @return the Exhibition Center
     */
    public ExhibitionCenter getExhibitionCenter() {
        return this.exhibitionCenter;
    }

    /**
     * Get the exhibitions list with Applications Decided (and Demos in created
     * state) by Organizer.
     *
     * @param organizer the organizer to filter exhibitions
     * @return the exhibitions list with Applications Decided (and Demos in
     * created state) by Organizer
     */
    public List<Exhibition> getExhibitionsList(Organizer organizer) {

        ExhibitionsRegister exhibitionsRegister = exhibitionCenter.getExhibitionsRegister();

        return exhibitionsRegister.getExhibitionsAppsDecidedAndDemosCreated(organizer);
    }

    /**
     * Obtain selected exhibition.
     *
     * @return the selected exhibition
     */
    public Exhibition getSelectedExhibition() {
        return selectedExhibition;
    }

    /**
     * Set the demonstration list class.
     *
     * @param exhibition exhibtion that contains the selected demonstrations
     * list class
     */
    public void setExhibitionAndDemonstrationsList(Exhibition exhibition) {
        this.selectedExhibition = exhibition;
        this.demonstrationsList = exhibition.getDemonstrationsList();
    }

    /**
     * Obtain the list of demonstrations.
     *
     * @return the list of demonstrations
     */
    public List<Demonstration> getDemonstrationsList() {
        return this.demonstrationsList.getDemonstrationsList();
    }

    /**
     * Obtain the list of already defined demonstrations.
     *
     * @return the list of already defined demonstrations
     */
    public List<Demonstration> getEffectiveDemosList() {
        return this.demonstrationsList.getDecidedDemonstrations();
    }

    /**
     * Set the demonstrations common dates.
     *
     * @param startSubDate Demonstration's applications submission start date
     * @param endSubDate Demonstration's applications submission end date
     * @param conflictLimitDate Demonstration's conflicts limit date
     * @param evaluationLimiteDate Demonstration's applications evaluation limit
     * date
     */
    public void setCommonDates(Date startSubDate,
            Date endSubDate, Date conflictLimitDate, Date evaluationLimiteDate) {

        this.demonstrationsList.setSubStartDate(startSubDate);
        this.demonstrationsList.setSubEndDate(endSubDate);
        this.demonstrationsList.setConflictLimitDate(conflictLimitDate);
        this.demonstrationsList.setEvaluationLimitDate(evaluationLimiteDate);
    }

    /**
     * Set the selected efective demonstration.
     *
     * @param demonstration selected efective demonstration to set
     */
    public void setEfectiveDemonstration(Demonstration demonstration) {

        this.efectiveDemo = demonstration;
    }

    /**
     * Obtain a short info of a demnstration to display on the UI.
     *
     * @return a short info of a demnstration to display on the UI
     */
    public String getInfo() {

        return this.efectiveDemo.getDisplayInfo();
    }

    /**
     * Set demosntration's start and end dates
     *
     * @param startDate demonstration's start date
     * @param endDate demonstration's end date
     *
     * @return true if dates are valid
     */
    public boolean setDemonstrationDates(Date startDate, Date endDate) {

        this.efectiveDemo.setStartDate(startDate);
        this.efectiveDemo.setEndDate(endDate);

        return this.demonstrationsList.validateDates(efectiveDemo);
    }

    /**
     * Validate demonstrations common dates
     *
     * @return true if dates are valid
     */
    public boolean validateCommonDates() {

        return this.demonstrationsList.validateCommonDates(selectedExhibition.getStartDate());
    }

    /**
     * Update a demonstration in the demonstrations list.
     *
     * @return true if demonstration is sucessfully updated.
     */
    public boolean updateDemonstration() {

        boolean isUpdated;

        isUpdated = this.efectiveDemo.setDecided();

        if (isUpdated) {
            this.demonstrationsList.setTimers(this.efectiveDemo, exhibitionCenter);
        }
        return isUpdated;
    }

    /**
     * Revert to previous state.
     *
     * @param demonstration demonstration to revert.
     */
    public void revertDemonstration(Demonstration demonstration) {
        demonstration.setCurrentState(new DemonstrationCreatedState(demonstration));
    }

    /**
     * Updates the selected exhibition's demonstrations list.
     *
     * @return true if all demonstrations are updated.
     */
    public boolean updateDemosntrationList() {

        boolean isUpdated = this.demonstrationsList.updateDemonstrationsList();

        selectedExhibition.setDemonstrationsList(demonstrationsList);

        return isUpdated;
    }

}
