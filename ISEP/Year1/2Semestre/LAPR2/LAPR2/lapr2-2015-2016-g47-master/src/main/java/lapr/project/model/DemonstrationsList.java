/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a demonstrations list.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
public class DemonstrationsList implements Serializable {

    /**
     * List of demonstrations.
     */
    @XmlElementWrapper(name = "demonstrations_list")
    @XmlElement(name = "demonstration")
    private List<Demonstration> demonstrationList;

    /**
     * Demonstration's application submissions start date.
     */
    private Date subStartDate;

    /**
     * Demonstration's application submissions end date.
     */
    private Date subEndDate;

    /**
     * Demonstration's attribution conflicts resolution limit date.
     */
    private Date conflictLimitDate;

    /**
     * Demonstration's evaluations limite date.
     */
    private Date evaluationLimitDate;

    /**
     * Demonstration's default application submissions start date.
     */
    private static final Date DEFAULT_SUB_START_DATE = new Date(2016, 1, 1);

    /**
     * Demonstration's default application submissions end date.
     */
    private static final Date DEFAULT_SUB_END_DATE = new Date(2016, 1, 1);

    /**
     * Demonstration's default attribution conflicts resolution limit date.
     */
    private static final Date DEFAUL_CONFLICT_LIMITE_DATE = new Date(2016, 1, 1);

    /**
     * Demonstration's default evaluations limite date.
     */
    private static final Date DEFAUL_EVALUATION_LIMITE_DATE = new Date(2016, 1, 1);

    /**
     * Creates an instance of DemonstrationsList with its default values.
     */
    public DemonstrationsList() {
        this.demonstrationList = new ArrayList<>();
        this.subStartDate = DEFAULT_SUB_START_DATE;
        this.subEndDate = DEFAULT_SUB_END_DATE;
        this.conflictLimitDate = DEFAUL_CONFLICT_LIMITE_DATE;
        this.evaluationLimitDate = DEFAUL_EVALUATION_LIMITE_DATE;
    }

    /**
     * Creates an instance of DemonstrationsList receiving the list of
     * demonstrations
     *
     * @param demonstrationList list of demonstrations
     */
    public DemonstrationsList(List<Demonstration> demonstrationList) {
        this.demonstrationList = new ArrayList<>(demonstrationList);
        this.subStartDate = DEFAULT_SUB_START_DATE;
        this.subEndDate = DEFAULT_SUB_END_DATE;
        this.conflictLimitDate = DEFAUL_CONFLICT_LIMITE_DATE;
        this.evaluationLimitDate = DEFAUL_EVALUATION_LIMITE_DATE;
    }

    /**
     * Creates a demonstrations list receiving their attributes.
     *
     * @param demonstrationList demonstrations list
     * @param startDate start date
     * @param endDate end date
     * @param subStartDate application submissions sart date
     * @param subEndDate application submissions end date
     * @param conflictLimitDate conflict limite date
     * @param evaluationLimitDate evaluations limite date
     */
    public DemonstrationsList(List<Demonstration> demonstrationList, Date startDate, Date endDate, Date subStartDate, Date subEndDate, Date conflictLimitDate, Date evaluationLimitDate) {
        this.demonstrationList = demonstrationList;
        this.subStartDate = subStartDate;
        this.subEndDate = subEndDate;
        this.conflictLimitDate = conflictLimitDate;
        this.evaluationLimitDate = evaluationLimitDate;
    }

    /**
     * Creates an instance of DemonstrationsList copying another demonstrations
     * list.
     *
     * @param demonstrationList another demonstrations list
     */
    public DemonstrationsList(DemonstrationsList demonstrationList) {
        this.demonstrationList = new ArrayList<>(demonstrationList.demonstrationList);
        this.subStartDate = demonstrationList.subStartDate;
        this.subEndDate = demonstrationList.subEndDate;
        this.conflictLimitDate = demonstrationList.conflictLimitDate;
        this.evaluationLimitDate = demonstrationList.evaluationLimitDate;
    }

    /**
     * Gets the list of demonstrations.
     *
     * @return list of demonstrations
     */
    public List<Demonstration> getDemonstrationsList() {
        return new ArrayList<>(demonstrationList);
    }

    /**
     * Sets the list of demonstrations.
     *
     * @param demonstrationList list of demonstrations
     */
    public void setDemonstrationsList(List<Demonstration> demonstrationList) {
        this.demonstrationList = new ArrayList<>(demonstrationList);
    }

    /**
     * Obtain the application submissions start date.
     *
     * @return the application submissions start date
     */
    public Date getSubStartDate() {
        return subStartDate;
    }

    /**
     * Set the application submissions start date.
     *
     * @param subStartDate the application submissions start date to set
     */
    public void setSubStartDate(Date subStartDate) {
        this.subStartDate = subStartDate;
    }

    /**
     * Obtain the application submissions end date.
     *
     * @return the application submissions start date
     */
    public Date getSubEndDate() {
        return subEndDate;
    }

    /**
     * Set the application submissions end date.
     *
     * @param subEndDate the application submissions end date to set
     */
    public void setSubEndDate(Date subEndDate) {
        this.subEndDate = subEndDate;
    }

    /**
     * Obtain the conflicts limite date.
     *
     * @return the conflicts limite date
     */
    public Date getConflictLimitDate() {
        return conflictLimitDate;
    }

    /**
     * Set the conflicts limite date.
     *
     * @param conflictLimitDate the conflicts limite date to set
     */
    public void setConflictLimitDate(Date conflictLimitDate) {
        this.conflictLimitDate = conflictLimitDate;
    }

    /**
     * Obtain the evaluations limite date.
     *
     * @return the evaluations Limite Date
     */
    public Date getEvaluationLimitDate() {
        return evaluationLimitDate;
    }

    /**
     * Set the evaluations limite date.
     *
     * @param evaluationLimitDate the evaluations limite date to set
     */
    public void setEvaluationLimitDate(Date evaluationLimitDate) {
        this.evaluationLimitDate = evaluationLimitDate;
    }

    /**
     * Gets the list of demonstrations in changed conflicts state
     *
     * @return list of demonstrations in changed conflicts state
     */
    public List<Demonstration> getDemonstrationsListInChangedConflicts() {
        List<Demonstration> demonstrationsInChangedConflitctsStateList = new ArrayList<>();
        for (Demonstration demonstration : this.demonstrationList) {
            if (demonstration.getCurrentState().isChangedConflicts()) {
                demonstrationsInChangedConflitctsStateList.add(demonstration);
            }
        }
        return demonstrationsInChangedConflitctsStateList;
    }

    /**
     * Obtain the demonstrations filtering by Applications Decided state.
     *
     * @return the demonstrations filtering by Applications Decided state
     */
    public List<Demonstration> getDecidedApplicationsDemonstrations() {
        List<Demonstration> demonstrationsList = new ArrayList<>();

        for (Demonstration demonstration : this.demonstrationList) {

            boolean isApplicationsDecided = demonstration.isApplicationsDecided();

            if (isApplicationsDecided) {

                demonstrationsList.add(demonstration);
            }
        }
        return demonstrationsList;
    }

    /**
     * Obtain the demonstrations filtering by Decided state.
     *
     * @return the demonstrations filtering by Decided state
     */
    public List<Demonstration> getDecidedDemonstrations() {
        List<Demonstration> demonstrationsList = new ArrayList<>();

        for (Demonstration demonstration : this.demonstrationList) {

            boolean isDecided = demonstration.isDecided();

            if (isDecided) {

                demonstrationsList.add(demonstration);
            }
        }
        return demonstrationsList;
    }

    /**
     * Returns the list of removables of this demonstration list filtered out by
     * exhibitor responsible
     *
     * @param exhibitorResponsible exhibitor responsible to filter removables
     * @return
     */
    public List<Removable> getRemovables(ExhibitorResponsible exhibitorResponsible) {
        List<Removable> removablesList = new ArrayList();
        List<Application> applicationsList = new ArrayList();
        Removable removable;
        for (Demonstration demonstration : this.demonstrationList) {
            applicationsList = demonstration.getApplicationsList().getApplicationsList();
            if (demonstration.getCurrentState().isOpenedApplications()) {
                for (Application application : applicationsList) {
                    removable = (Removable) application;
                    if (removable.getExhibitorResponsible().equals(exhibitorResponsible)) {
                        if (!removable.isRemoved()) {
                            removablesList.add(removable);
                        }

                    }
                }
            }
        }
        return removablesList;
    }

    /**
     * Create a new demonstration.
     *
     * @return a new demonstration
     */
    public Demonstration newDemonstration() {

        return new Demonstration();
    }

    /**
     * Create a new demonstration object.
     *
     * @param description associated to the new demonstration
     * @return a new demonstration object
     */
    public Demonstration newDemonstration(String description) {

        return new Demonstration(description);
    }

    /**
     * Add & validate a demonstration to the list.
     *
     * @param demonstration the demonstration to validate
     * @return true if demonstration is sucessfully added.
     */
    public boolean addAndValidateDemonstration(Demonstration demonstration) {

        return (demonstration.validate() && validateDemonstration(demonstration)) ? addDemonstration(demonstration) : false;
    }

    /**
     * Update all demonstrations that are not decided to discontinued state.
     *
     * @return true if the update is successful
     */
    public boolean updateDemonstrationsList() {

        boolean isAllUpdate = true;

        for (Demonstration demonstration : demonstrationList) {

            boolean isDecided = demonstration.isDecided();

            if (!isDecided) {
                isAllUpdate = demonstration.setDiscontinued();
            }

        }

        return isAllUpdate;
    }

    /**
     * Validate if the list doesn't contain a demonstration.
     *
     * @param demonstration the demonstration to validate
     * @return true if list doesn't contain the demonstration
     */
    private boolean validateDemonstration(Demonstration demonstration) {

        return !this.demonstrationList.contains(demonstration);
    }

    /**
     * Replace a demonstration in the list.
     *
     * @param demonstration the demonstration to replace
     * @return true if demonstration is sucessfully replaced.
     */
    private boolean replaceDemonstration(Demonstration demonstration) {

        return this.demonstrationList.remove(demonstration) ? this.demonstrationList.add(demonstration) : false;
    }

    /**
     * Add a demonstration to the list.
     *
     * @param demonstration the demonstration to add
     * @return true if demonstration is sucessfully added.
     */
    private boolean addDemonstration(Demonstration demonstration) {

        return this.demonstrationList.add(demonstration);
    }

    /**
     * Verify if a given demonstration is on the demonstration's list.
     *
     * @param demonstration demonstration to be verified
     * @return true if it is contained, false otherwise
     */
    public boolean isDemonstration(Demonstration demonstration) {
        return this.demonstrationList.contains(demonstration);
    }

    /**
     * Verify if demonstrations are in created state.
     *
     * @return true if demonstrations are in created state
     */
    public boolean isDemonstrationsInCreatedState() {

        return isFirstDemonstrationCreatedState();
    }

    /**
     * Verify if demonstrations (only need to verify one) are in created state.
     *
     * @return true if demonstrations are in created state
     */
    private boolean isFirstDemonstrationCreatedState() {

        // TODO : If there is time modify state diagrams to not use this method (Will effect many UCs implementations)
        return this.demonstrationList.isEmpty() ? false : this.demonstrationList.get(0).isCreated();
    }

    /**
     * Validate the Demonstration dates.
     *
     * @param demonstration the demosntration to validate
     * @return true if the Demonstration dates are valid
     */
    public boolean validateDates(Demonstration demonstration) {

        return this.evaluationLimitDate.before(demonstration.getStartDate())
                && demonstration.getStartDate().before(demonstration.getEndDate());
    }

    /**
     * Validate the Demonstration dates.
     *
     * @param exhibitionStartDate the exhibition's start date
     * @return true if the Demonstrations common dates are valid
     */
    public boolean validateCommonDates(Date exhibitionStartDate) {
        return this.subStartDate.before(this.subEndDate)
                && this.conflictLimitDate.after(this.subEndDate)
                && this.evaluationLimitDate.after(this.conflictLimitDate)
                && this.evaluationLimitDate.before(exhibitionStartDate);
    }

    /**
     * Schedule demonstration's timer with related tasks.
     *
     * @param demonstration demonstration to set Timer
     * @param exhibitionCenter the exhibiton center
     */
    public void setTimers(Demonstration demonstration, ExhibitionCenter exhibitionCenter) {

        Date[] dates = {this.subStartDate, this.subEndDate, this.conflictLimitDate, this.evaluationLimitDate};

        demonstration.createTimers(exhibitionCenter, dates);
    }

    /**
     * Return the textual representation of a demonstrations list.
     *
     * @return the textual representation of a demonstrations list
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("DemonstrationList{");
        for (Demonstration demonstration : this.demonstrationList) {
            s.append(String.format("%s%n", demonstration));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares two DemonstrationsList objects.
     *
     * @param otherObject Object to compare
     * @return true if the objects are equals.
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        DemonstrationsList otherDemonstrationsList = (DemonstrationsList) otherObject;

        return this.demonstrationList.equals(otherDemonstrationsList.demonstrationList)
                && this.subStartDate.equals(otherDemonstrationsList.subStartDate)
                && this.subEndDate.equals(otherDemonstrationsList.subEndDate)
                && this.conflictLimitDate.equals(otherDemonstrationsList.conflictLimitDate)
                && this.evaluationLimitDate.equals(otherDemonstrationsList.evaluationLimitDate);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.demonstrationList);
        hash = 79 * hash + Objects.hashCode(this.subStartDate);
        hash = 79 * hash + Objects.hashCode(this.subEndDate);
        hash = 79 * hash + Objects.hashCode(this.conflictLimitDate);
        hash = 79 * hash + Objects.hashCode(this.evaluationLimitDate);
        return hash;
    }
}
