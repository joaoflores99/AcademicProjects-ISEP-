/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lapr.project.model.demonstration.DemonstrationInicialState;
import lapr.project.model.timers.ChangeToApplicationsInDecision;
import lapr.project.model.timers.ChangeToChangedConflicts;
import lapr.project.model.timers.ChangeToClosedApplications;
import lapr.project.model.timers.ChangeToOpenApplications;
import lapr.project.model.timers.DetectConflictsTask;

/**
 * Represents a demonstration.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Demonstration implements Submittable, Selectable, Serializable {

    /**
     * Unique demoonstration ID.
     */
    @XmlAttribute
    private final String demonstrationID;

    /**
     * The title of the demonstration.
     */
    private String title;

    /**
     * The descriptive text of this demonstration.
     */
    private String description;

    /**
     * The place for the demonstration.
     */
    private Place place;

    /**
     * The exhibition's timer.
     */
    private transient Timer timer;

    /**
     * The demonstration's staff list.
     */
//    @XmlElementWrapper(name = "staff_list")
    private StaffList staffList;

    /**
     * Exhibition's organizers list.
     */
//    @XmlElementWrapper(name = "organizers_list")
    private OrganizersList organizersList;

    /**
     * The demonstration applications list
     */
//    @XmlElementWrapper(name = "applications_list")
    private ApplicationsList applicationsList;

    /**
     * The resources list.
     */
    @XmlElementWrapper(name = "resources_list")
    @XmlElement(name = "resource")
    private List<Resource> resourcesList;

    /**
     * Demonstration's staff attributions list.
     */
//    @XmlElementWrapper(name = "staff_attributions_list")
    private StaffAttributionsList staffAttributionsList;

    /**
     * Demonstration's conflicts list.
     */
    private ConflictsList conflictsList;

    /**
     * The demonstration current state.
     */
    @XmlTransient
    private DemonstrationState currentState;

    /**
     * A counter of demonstrations.
     */
    private static int demoCounter = 1;

    /**
     * Demonstration's start date.
     */
    private Date startDate;

    /**
     * Demonstration's end date.
     */
    private Date endDate;

    /**
     * Demonstration's default start date.
     */
    private static final Date DEFAULT_START_DATE = new Date(2016, 1, 1);

    /**
     * Demonstration's default end date.
     */
    private static final Date DEFAULT_END_DATE = new Date(2016, 1, 1);

    /**
     * Demonstration ID prefix.
     */
    private static final String ID_PREFIX = "Demonstration-";

    /**
     * Demonstration's default title.
     */
    private static final String DEFAULT_TITLE = "Untitled";

    /**
     * Demonstration's default description.
     */
    private static final String DEFAULT_DESCRIPTION = "No description.";

    /**
     * Empty constructor, assigns the default value to attributes
     */
    public Demonstration() {
        this.title = DEFAULT_TITLE;
        this.description = DEFAULT_DESCRIPTION;
        // TODO : Review to count demos per exhibition only. 
        this.demonstrationID = ID_PREFIX + demoCounter++;

        this.startDate = DEFAULT_START_DATE;
        this.endDate = DEFAULT_END_DATE;
        this.place = new Place();
        this.staffList = new StaffList();
        this.organizersList = new OrganizersList();
        this.applicationsList = new ApplicationsList();
        this.resourcesList = new ArrayList<>();
        this.staffAttributionsList = new StaffAttributionsList();
        this.conflictsList = new ConflictsList();
        this.currentState = new DemonstrationInicialState(this);
        this.timer = new Timer();
    }

    /**
     * Creates a demonstration receiving the description.
     *
     * @param description demonstration's description
     */
    public Demonstration(String description) {
        this.title = DEFAULT_TITLE;
        this.description = description;
        this.demonstrationID = ID_PREFIX + demoCounter++;

        this.startDate = DEFAULT_START_DATE;
        this.endDate = DEFAULT_END_DATE;
        this.place = new Place();
        this.staffList = new StaffList();
        this.organizersList = new OrganizersList();
        this.applicationsList = new ApplicationsList();
        this.resourcesList = new ArrayList<>();
        this.staffAttributionsList = new StaffAttributionsList();
        this.conflictsList = new ConflictsList();
        this.currentState = new DemonstrationInicialState(this);
        this.timer = new Timer();
    }

    /**
     * Creates an instance of demonstration receiving their attributes.
     *
     * @param title demonstration's title
     * @param description demonstrations's description
     * @param startDate Demonstrations start date
     * @param endDate Demonstrations end date
     * @param place demonstration's place
     * @param staffList staff list
     * @param organizersList organizers list
     * @param applicationsList applications list
     * @param resourcesList resources list
     * @param staffAttributionsList staff attributions list
     * @param conflictsList conficts list
     */
    public Demonstration(String title, String description, Date startDate, Date endDate, Place place, StaffList staffList,
            OrganizersList organizersList, ApplicationsList applicationsList, List<Resource> resourcesList,
            StaffAttributionsList staffAttributionsList, ConflictsList conflictsList) {
        this.title = title;
        this.description = description;
        this.demonstrationID = ID_PREFIX + demoCounter++;

        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.staffList = new StaffList(staffList);
        this.organizersList = new OrganizersList(organizersList);
        this.applicationsList = new ApplicationsList(applicationsList);
        this.resourcesList = new ArrayList<>(resourcesList);
        this.staffAttributionsList = new StaffAttributionsList(staffAttributionsList);
        this.conflictsList = new ConflictsList(conflictsList);
        this.currentState = new DemonstrationInicialState(this);
        this.timer = new Timer();
    }

    /**
     * Creates an instance of demonstration copying another demonstration.
     *
     * @param demonstration another demonstration
     */
    public Demonstration(Demonstration demonstration) {
        this.title = demonstration.title;
        this.description = demonstration.description;
        this.demonstrationID = ID_PREFIX + demoCounter++;

        this.startDate = demonstration.startDate;
        this.endDate = demonstration.endDate;
        this.place = demonstration.place;
        this.staffList = new StaffList(demonstration.staffList);
        this.organizersList = new OrganizersList(demonstration.organizersList);
        this.applicationsList = new ApplicationsList(demonstration.applicationsList);
        this.resourcesList = new ArrayList<>(demonstration.resourcesList);
        this.staffAttributionsList = new StaffAttributionsList(demonstration.staffAttributionsList);
        this.currentState = demonstration.currentState;
        this.conflictsList = new ConflictsList(demonstration.conflictsList);
        this.timer = new Timer();
    }

    /**
     * Set the timer.
     *
     * @param timer the timer to set
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Gets the demonstration's title.
     *
     * @return demonstartion's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the demonstartion's title.
     *
     * @param title demonstration's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * returns the current value of the descriptive text
     *
     * @return demonstration's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * sets the current value of the descriptive text
     *
     * @param description demonstration's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtain the demonstration's start date.
     *
     * @return the demonstration's start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the demonstration's start date.
     *
     * @param startDate the demonstration's start date to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Obtain the demonstration's end date.
     *
     * @return the demonstration's end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the demonstration's end date.
     *
     * @param endDate the demonstration's end date to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the demonstration's place.
     *
     * @return demonstration's place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Sets the demonstration's place.
     *
     * @param place demonstration's place
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    /**
     * Gets the organizers list.
     *
     * @return organizers list
     */
    public OrganizersList getOrganizersList() {
        return organizersList;
    }

    /**
     * Sets the organizers list.
     *
     * @param organizersList organizers list
     */
    public void setOrganizersList(OrganizersList organizersList) {
        this.organizersList = organizersList;
    }

    /**
     * Gets the resources list.
     *
     * @return resources list
     */
    public List<Resource> getResourcesList() {
        return resourcesList;
    }

    /**
     * Sets the resources list.
     *
     * @param resourcesList resources list.
     */
    public void setResourcesList(List<Resource> resourcesList) {
        this.resourcesList = resourcesList;
    }

    /**
     * Gets the staff list.
     *
     * @return staff list
     */
    @Override
    public StaffList getStaffList() {
        return staffList;
    }

    /**
     * Sets the staff list.
     *
     * @param staffList staff list
     */
    public void setStaffList(StaffList staffList) {
        this.staffList = staffList;
    }

    /**
     * Obtain the Demonstration's conflicts list.
     *
     * @return the Demonstration's conflicts list
     */
    @Override
    public ConflictsList getConflictsList() {
        return this.conflictsList;
    }

    /**
     * Set the Demonstration's conflicts list.
     *
     * @param conflictsList the Demonstration's conflicts list to set
     */
    public void setConflictsList(ConflictsList conflictsList) {
        this.conflictsList = new ConflictsList(conflictsList);
    }

    @Override
    public List<Conflict> getConflictListByStaffMember(StaffMember staffMember) {
        return this.conflictsList.getConflictsListByStaffMember(staffMember);
    }

    /**
     * Adds a resource to the list if it doesn't already contain that resource.
     *
     * @param resource the resource to add
     * @return true if the resource is added
     */
    public boolean addResource(Resource resource) {

        return this.resourcesList.contains(resource) ? false : this.resourcesList.add(resource);
    }

    /**
     * Removes a resource from the list if it contain that resource.
     *
     * @param index index of the resource to remove
     * @return true if the resource is removed
     */
    public boolean removeResource(int index) {

        return this.resourcesList.remove(resourcesList.get(index));
    }

    /**
     * Returns the current demonstration state.
     *
     * @return the actual state of the demonstration
     */
    public DemonstrationState getCurrentState() {
        return this.currentState;
    }

    /**
     * Sets the new demonstration state.
     *
     * @param currentState the new demonstration state
     */
    public void setCurrentState(DemonstrationState currentState) {
        this.currentState = currentState;
    }

    @Override
    public boolean setDetectedConflicts() {

        return this.currentState.setDetectedConflicts();
    }

    /**
     * Validates the demonstration.
     *
     * @return true if the description is valid, false otherwise
     */
    public boolean validate() {

        // TODO: Is it possible to not have any resource defined?
        return !this.description.isEmpty();
    }

    /**
     * Returns the staff attribtions list.
     *
     * @return staff attribtions list
     */
    @Override
    public StaffAttributionsList getStaffAttributionsList() {
        return new StaffAttributionsList(this.staffAttributionsList);
    }

    /**
     * Returns the applications list of the demonstration.
     *
     * @return applications list.
     */
    @Override
    public ApplicationsList getApplicationsList() {
        return this.applicationsList;
    }

    /**
     * @param applicationsList the applicationsList to set
     */
    public void setApplicationsList(ApplicationsList applicationsList) {
        this.applicationsList = applicationsList;
    }

    /**
     * Set the staff attributions list
     *
     * @param staffAttributionsList the news staff attributions list
     */
    @Override
    public void setStaffAttributionsList(StaffAttributionsList staffAttributionsList) {
        this.staffAttributionsList = new StaffAttributionsList(staffAttributionsList);
    }

    /**
     * Return true if demonstration's current state is in
     * detectedConflictsState, false otherwise.
     *
     * @return true if demonstration's current state is in
     * detectedConflictsState, false otherwise
     */
    public boolean isDetectedConflictsState() {
        return this.currentState.isDetectedConflicts();
    }

    /**
     * Returns true if the staff member is in the staff list of this
     * demonstration, false otherwise.
     *
     * @param staffMember staff member that is passed as parameter to check if
     * he is in the staff list of this demonstration.
     *
     * @return true if the staff member is in the staff list of this
     * demonstration, false otherwise.
     */
    public boolean isStaffMember(StaffMember staffMember) {
        return this.staffList.getStaffList().contains(staffMember);
    }

//    public boolean setCreated() {
//        return this.currentState.
//    }
    /**
     * Returns a short version info of the demonstration.
     *
     * @return a short representation
     */
    @Override
    public String getDisplayInfo() {
        return String.format("Demonstration: %s", this.description);

    }

    /**
     * Set the current demonstration state of the demonstration to
     * applicationsInEvaluation state
     */
    @Override
    public void setSubmittableInApplicationsInEvaluationState() {
        this.currentState.setApplicationsInEvaluation();
    }

    @Override
    public String[] getInfo() {
        String[] info = new String[2];
        info[0] = this.getDisplayInfo();
        //TODO info[1] = this.getStartDate().toString();
        info[1] = "por definir";
        //TODO info[2] = this.getEndDate().toString();
        info[2] = "por definir";
        return info;
    }

    @Override
    public boolean removeAttribution(StaffAttribution staffAttribution) {
        return this.staffAttributionsList.removeStaffAttribution(staffAttribution);

    }

    /**
     * Schedules the state changes.
     *
     * @param exhibitionCenter the exhibition center necessary to deploy the
     * detect conlicts controller
     * @param dates dates necessary to deploy the tasks (order= subStartDate,
     * subEndDate, ConflictsLimiteDate, evaluationsLimiteDate).
     */
    public void createTimers(ExhibitionCenter exhibitionCenter, Date[] dates) {

        ChangeToOpenApplications taskOpenApplications = new ChangeToOpenApplications(this);

        this.timer.schedule(taskOpenApplications, dates[0]);

        ChangeToClosedApplications taskClosedApplications = new ChangeToClosedApplications(this);

        this.timer.schedule(taskClosedApplications, dates[1]);

        DetectConflictsTask taskDetectConflicts = new DetectConflictsTask(this, exhibitionCenter);

        this.timer.schedule(taskDetectConflicts, dates[1]);

        ChangeToChangedConflicts taskChangeToChangedConflicts = new ChangeToChangedConflicts(this);

        this.timer.schedule(taskChangeToChangedConflicts, dates[2]);

        ChangeToApplicationsInDecision taskChangeToApplicationsInDecision = new ChangeToApplicationsInDecision(this);

        this.timer.schedule(taskChangeToApplicationsInDecision, dates[3]);

    }

    /**
     * Equals method to check if two objects are the same
     *
     * @param otherObject the demonstration to compare to
     * @return true if equal
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Demonstration otherDemonstration = (Demonstration) otherObject;

        return this.description.equals(otherDemonstration.description)
                && this.place.equals(otherDemonstration.place)
                && this.organizersList.equals(otherDemonstration.organizersList)
                && this.staffList.equals(otherDemonstration.staffList)
                && this.applicationsList.equals(otherDemonstration.applicationsList)
                && this.resourcesList.equals(otherDemonstration.resourcesList)
                && this.staffAttributionsList.equals(otherDemonstration.staffAttributionsList);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.place);
        hash = 17 * hash + Objects.hashCode(this.staffList);
        hash = 17 * hash + Objects.hashCode(this.organizersList);
        hash = 17 * hash + Objects.hashCode(this.applicationsList);
        hash = 17 * hash + Objects.hashCode(this.resourcesList);
        hash = 17 * hash + Objects.hashCode(this.staffAttributionsList);
        return hash;
    }

    /**
     * Returns the textual representation of the attributes of this class.
     *
     * @return demonstration's textual representation
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Demonstration{");

        s.append(String.format("%s%n", this.description));

        s.append("}");
        return s.toString();
    }

    /**
     * Returns true if demonstration is created.
     *
     * @return true if demonstration is created
     */
    public boolean isCreated() {

        return this.currentState.isCreated();
    }

    /**
     * Returns true if demonstration's is valid to change to created state.
     *
     * @return true if demonstration's is valid to change to created state
     */
    public boolean setCreated() {

        return this.currentState.setCreated();
    }

    /**
     * Returns true if demonstration's applications are decided.
     *
     * @return true if demonstration's applications are decided
     */
    public boolean isApplicationsDecided() {

        return this.currentState.isApplicationsDecided();
    }

    /**
     * Returns true if demonstration is decided.
     *
     * @return true if demonstration is decided
     */
    public boolean isDecided() {

        return this.currentState.isDecided();
    }

    /**
     * Set the next stage to decided state.
     *
     * @return true if the change was successful
     */
    public boolean setDecided() {
        return this.currentState.setDecided();
    }

    /**
     * Set the next stage to discontinued state.
     *
     * @return true if the change was successful
     */
    public boolean setDiscontinued() {
        return this.currentState.setDiscontinued();
    }

    /**
     * Changes to open applications state.
     *
     * @return true if the state successfully changes
     */
    @Override
    public boolean setOpenApplications() {

        return this.currentState.setOpenApplications();
    }

    /**
     * Changes to closed applications state.
     *
     * @return true if the state successfully changes
     */
    @Override
    public boolean setClosedApplications() {

        return this.currentState.setClosedApplications();
    }

    /**
     * Changes to changed conflicts state.
     *
     * @return true if the state successfully changes
     */
    @Override
    public boolean setChangedConflicts() {

        return this.currentState.setChangedConflicts();
    }

    /**
     * Changes to applications in decision state.
     *
     * @return true if the state successfully changes
     */
    @Override
    public boolean setApplicationsInDecision() {

        return this.currentState.setApplicationsInDecision();
    }

    @Override
    public boolean isDemonstration() {
        return true;
    }

    @Override
    public boolean isExhibition() {
        return false;
    }

    @Override
    public String getName() {
        return getTitle();
    }
}
