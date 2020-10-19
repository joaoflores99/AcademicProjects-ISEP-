/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lapr.project.model.exhibition.ExhibitionInicialState;
import lapr.project.model.timers.ChangeToApplicationsInDecision;
import lapr.project.model.timers.ChangeToChangedConflicts;
import lapr.project.model.timers.ChangeToClosedApplications;
import lapr.project.model.timers.ChangeToOpenApplications;
import lapr.project.model.timers.DetectConflictsTask;
import lapr.project.utils.Exportable;

/**
 * Represents an Exhibition.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Exhibition implements Submittable, Exportable, Serializable {

    /**
     * Exhibition's title.
     */
    @XmlAttribute
    private String title;

    /**
     * Exhibition's description.
     */
    private String description;

    /**
     * Exhibition's start date.
     */
    private Date startDate;

    /**
     * Exhibition's end date.
     */
    private Date endDate;

    /**
     * Exhibition's application submissions start date.
     */
    private Date subStartDate;

    /**
     * Exhibition's application submissions end date.
     */
    private Date subEndDate;

    /**
     * Exhibition's attribution conflicts resolution limit date.
     */
    private Date conflictLimitDate;

    /**
     * Exhibition's evaluations limite date.
     */
    private Date evaluationLimitDate;

    /**
     * Exhibition's location.
     */
    private Place place;

    /**
     * Exhibition's staff list.
     */
//    @XmlElementWrapper(name = "staff_list")
    private StaffList staffList;

    /**
     * Exhibition's organizers list.
     */
//    @XmlElementWrapper(name = "organizers_list")
    private OrganizersList organizersList;

    /**
     * Exhibition's applications list.
     */
//    @XmlElementWrapper(name = "applications_list")
    private ApplicationsList applicationsList;

    /**
     * Exhibition's demonstrations list.
     */
//    @XmlElementWrapper(name = "demonstrations_list")
    private DemonstrationsList demonstrationsList;

    /**
     * Exhibition's staff attributions list.
     */
//    @XmlElementWrapper(name = "staff_attributions_list")
    private StaffAttributionsList staffAttributionsList;

    /**
     * Exhibition's conflicts list.
     */
    private ConflictsList conflictsList;

    /**
     * The exhibition's current state.
     */

    @XmlTransient
    private ExhibitionState currentState;

    /**
     * The exhibition's timer.
     */
    private transient Timer timer;

    /**
     * Exhibition's default title.
     */
    private static final String DEFAULT_TITLE = "no title";

    /**
     * Exhibition's default description.
     */
    private static final String DEFAULT_DESCRIPTION = "no description";

    /**
     * Exhibition's default start date.
     */
    private static final Date DEFAULT_START_DATE = new Date(2016, 1, 1);

    /**
     * Exhibition's default end date.
     */
    private static final Date DEFAULT_END_DATE = new Date(2016, 1, 1);

    /**
     * Exhibition's default application submissions start date.
     */
    private static final Date DEFAULT_SUB_START_DATE = new Date(2016, 1, 1);

    /**
     * Exhibition's default application submissions end date.
     */
    private static final Date DEFAULT_SUB_END_DATE = new Date(2016, 1, 1);

    /**
     * Exhibition's default attribution conflicts resolution limit date.
     */
    private static final Date DEFAUL_CONFLICT_LIMITE_DATE = new Date(2016, 1, 1);

    /**
     * Exhibition's default evaluations limite date.
     */
    private static final Date DEFAUL_EVALUATION_LIMITE_DATE = new Date(2016, 1, 1);

    /**
     * Exhibition's default location.
     */
    private static final Place DEFAULT_PLACE = new Place();

    /**
     * Default constructor of a Exhibition class.
     */
    public Exhibition() {
        this.title = DEFAULT_TITLE;
        this.description = DEFAULT_DESCRIPTION;
        this.startDate = DEFAULT_START_DATE;
        this.endDate = DEFAULT_END_DATE;
        this.subStartDate = DEFAULT_SUB_START_DATE;
        this.subEndDate = DEFAULT_SUB_END_DATE;
        this.conflictLimitDate = DEFAUL_CONFLICT_LIMITE_DATE;
        this.evaluationLimitDate = DEFAUL_EVALUATION_LIMITE_DATE;
        this.place = DEFAULT_PLACE;
        this.staffList = new StaffList();
        this.organizersList = new OrganizersList();
        this.applicationsList = new ApplicationsList();
        this.demonstrationsList = new DemonstrationsList();
        this.staffAttributionsList = new StaffAttributionsList();
        this.conflictsList = new ConflictsList();
        this.currentState = new ExhibitionInicialState(this);
        this.timer = new Timer();
    }

    /**
     * Constructor of a Exhibition class.
     *
     * @param title Exhibition's title
     * @param description Exhibition's description
     * @param startDate Exhibition's start date
     * @param endDate Exhibition's end date
     * @param subStartDate Exhibition's application submissions sart date
     * @param subEndDate Exhibition's application submissions end date
     * @param conflictsLimiteDate Exhibition's conflict limite date
     * @param evaluationLimitDate Exhibition's evaluations limite date
     * @param place Exhibition's location
     * @param staffList Exhibition's staff list
     * @param organizersList Exhibition's organizers list
     * @param applicationsList
     * @param demonstrationsList Exhibition's demonstrations list
     * @param staffAttributionsList Exhibition's staff attributions list
     * @param conflictsList Exhibition's conflicts list
     */
    public Exhibition(String title, String description, Date startDate, Date endDate, Date subStartDate,
            Date subEndDate, Date conflictsLimiteDate, Date evaluationLimitDate, Place place, StaffList staffList,
            OrganizersList organizersList, ApplicationsList applicationsList, DemonstrationsList demonstrationsList,
            StaffAttributionsList staffAttributionsList, ConflictsList conflictsList) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subStartDate = subStartDate;
        this.subEndDate = subEndDate;
        this.place = place;
        this.conflictLimitDate = conflictsLimiteDate;
        this.evaluationLimitDate = evaluationLimitDate;
        this.staffList = new StaffList(staffList);
        this.organizersList = new OrganizersList(organizersList);
        this.applicationsList = new ApplicationsList(applicationsList);
        this.demonstrationsList = new DemonstrationsList(demonstrationsList);
        this.staffAttributionsList = new StaffAttributionsList(staffAttributionsList);
        this.conflictsList = new ConflictsList(conflictsList);
        this.currentState = new ExhibitionInicialState(this);
        this.timer = new Timer();
    }

    /**
     * Copy Constructor of a Exhibition.
     *
     * @param exhibition Exhibition to copy
     */
    public Exhibition(Exhibition exhibition) {
        this.title = exhibition.title;
        this.description = exhibition.description;
        this.startDate = exhibition.startDate;
        this.endDate = exhibition.endDate;
        this.subStartDate = exhibition.subStartDate;
        this.subEndDate = exhibition.subEndDate;
        this.place = exhibition.place;
        this.conflictLimitDate = exhibition.conflictLimitDate;
        this.evaluationLimitDate = exhibition.evaluationLimitDate;
        this.staffList = new StaffList(exhibition.staffList);
        this.organizersList = new OrganizersList(exhibition.organizersList);
        this.applicationsList = new ApplicationsList(exhibition.applicationsList);
        this.demonstrationsList = new DemonstrationsList(exhibition.demonstrationsList);
        this.staffAttributionsList = new StaffAttributionsList(exhibition.staffAttributionsList);
        this.conflictsList = new ConflictsList(exhibition.conflictsList);
        this.currentState = exhibition.currentState;
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
     * Obtain the Exhibition's title.
     *
     * @return the Exhibition's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the Exhibition's title.
     *
     * @param title the Exhibition's title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtain the Exhibition's description.
     *
     * @return the Exhibition's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the Exhibition's description.
     *
     * @param description the Exhibition's description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtain the Exhibition's start date.
     *
     * @return the Exhibition's start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the Exhibition's start date.
     *
     * @param startDate the Exhibition's start date to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Obtain the Exhibition's end date.
     *
     * @return the Exhibition's end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the Exhibition's end date.
     *
     * @param endDate the Exhibition's end date to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Obtain the Exhibition's application submissions start date.
     *
     * @return the Exhibition's application submissions start date
     */
    public Date getSubStartDate() {
        return subStartDate;
    }

    /**
     * Set the Exhibition's application submissions start date.
     *
     * @param subStartDate the Exhibition's application submissions start date
     * to set
     */
    public void setSubStartDate(Date subStartDate) {
        this.subStartDate = subStartDate;
    }

    /**
     * Obtain the Exhibition's application submissions end date.
     *
     * @return the Exhibition's application submissions start date
     */
    public Date getSubEndDate() {
        return subEndDate;
    }

    /**
     * Set the Exhibition's application submissions end date.
     *
     * @param subEndDate the Exhibition's application submissions end date to
     * set
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
     * Obtain the Exhibition's place.
     *
     * @return the Exhibition's place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Set the Exhibition's place.
     *
     * @param place the Exhibition's place to set
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    /**
     * Obtain the Exhibition's staff list.
     *
     * @return the Exhibition's staff list
     */
    @Override
    public StaffList getStaffList() {
        return staffList;
    }

    /**
     * Set the Exhibition's staff list.
     *
     * @param staffList the Exhibition's staff list to set
     */
    public void setStaffList(StaffList staffList) {
        this.staffList = new StaffList(staffList);
    }

    /**
     * Obtain the Exhibition's organizers list.
     *
     * @return the Exhibition's organizers list
     */
    public OrganizersList getOrganizersList() {
        return organizersList;
    }

    /**
     * Set the Exhibition's organizers list.
     *
     * @param organizersList the Exhibition's organizers list to set
     */
    public void setOrganizersList(OrganizersList organizersList) {
        this.organizersList = new OrganizersList(organizersList);
    }

    /**
     * Obtain the Exhibition's applications list.
     *
     * @return the Exhibition's applications list
     */
    @Override
    public ApplicationsList getApplicationsList() {
        return applicationsList;
    }

    /**
     * Set the Exhibition's applications list.
     *
     * @param applicationsList the Exhibition's applications list to set
     */
    public void setApplicationsList(ApplicationsList applicationsList) {
        this.applicationsList = new ApplicationsList(applicationsList);
    }

    /**
     * Obtain the Exhibition's demonstrations list.
     *
     * @return the Exhibition's demonstrations list
     */
    public DemonstrationsList getDemonstrationsList() {
        return this.demonstrationsList;
    }

    /**
     * Set the Exhibition's demonstrations list.
     *
     * @param demonstrationsList the Exhibition's demonstrations list to set
     */
    public void setDemonstrationsList(DemonstrationsList demonstrationsList) {
        this.demonstrationsList = new DemonstrationsList(demonstrationsList);
    }

    /**
     * Returns the staff attributions list.
     *
     * @return staff attribtions list
     */
    @Override
    public StaffAttributionsList getStaffAttributionsList() {
        return staffAttributionsList;
    }

    /**
     * Set the Exhibition's staff attributions list.
     *
     * @param staffAttributionsList the Exhibition's staff attributions list to
     * set
     */
    @Override
    public void setStaffAttributionsList(StaffAttributionsList staffAttributionsList) {
        this.staffAttributionsList = new StaffAttributionsList(staffAttributionsList);
    }

    /**
     * Obtain the Exhibition's conflicts list.
     *
     * @return conflicts list
     */
    @Override
    public ConflictsList getConflictsList() {
        return this.conflictsList;
    }

    /**
     * Set the Exhibition's conflicts list.
     *
     * @param conflictsList the Exhibition's conflicts list to set
     */
    public void setConflictsList(ConflictsList conflictsList) {
        this.conflictsList = new ConflictsList(conflictsList);
    }

    @Override
    public List<Conflict> getConflictListByStaffMember(StaffMember staffMember) {
        return this.conflictsList.getConflictsListByStaffMember(staffMember);
    }

    /**
     * Returns the current exhibition state.
     *
     * @return the actual state of the exhibition
     */
    public ExhibitionState getState() {
        return this.currentState;
    }

    /**
     * Sets the new exhibition state.
     *
     * @param currentState the new exhibition state
     */
    public void setState(ExhibitionState currentState) {
        this.currentState = currentState;
    }

    /**
     * Changes to state to created.
     *
     * @return true if the state successfully changes
     */
    public boolean setCreated() {

        return this.currentState.setCreated();
    }

    /**
     * Changes to next state DemosWithoutStaff or Complete.
     *
     * @return true if the state successfully changes
     */
    public boolean setDefinedDemos() {

        return this.currentState.setDemonstrationsDefined();
    }

    /**
     * Changes to open applications state.
     *
     * @return true if the state successfully changes
     */
    @Override
    public boolean setOpenApplications() {

        return this.currentState.setOpenApplication();
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
    public boolean setDetectedConflicts() {

        return this.currentState.setDetectedConflicts();
    }

    /**
     * Returns true if exhibition has already defined the demonstations.
     *
     * @return true if exhibition has already defined the demonstations
     */
    public boolean isNotDemonstrationsDefined() {

        return this.currentState.isCreated() || this.currentState.isStaffDefined();
    }

    /**
     * Return true if exhibition's current state is in detectedConflictsState,
     * false otherwise.
     *
     * @return true if exhibition's current state is in detectedConflictsState,
     * false otherwise
     */
    public boolean isDetectedConflicts() {
        return this.currentState.isDetectedConficts();
    }

    /**
     * Returns true if the staff member is in the staff list of this exhibition,
     * false otherwise.
     *
     * @param staffMember staff member that is passed as parameter to check if
     * he is in the staff list of this exhibition.
     *
     * @return true if the staff member is in the staff list of this exhibition,
     * false otherwise.
     */
    public boolean isStaffMember(StaffMember staffMember) {
        return this.staffList.getStaffList().contains(staffMember);

    }

    /**
     * Returns true if exhibition's applications are decided.
     *
     * @return true if exhibition's applications are decided
     */
    public boolean isApplicationsDecided() {

        return this.currentState.isApplicationsDecided();

    }

    /**
     * Verify if demonstrations are in created state.
     *
     * @return true if demonstrations are in created state
     */
    public boolean isDemonstrationsInCreatedState() {

        return demonstrationsList.isDemonstrationsInCreatedState();
    }

    /**
     * Validate the Exhibition.
     *
     * @return true if the exhibition is valid
     */
    public boolean validate() {

        // TODO: Create Validate Util Class
        return !this.title.trim().isEmpty()
                && !this.description.trim().isEmpty()
                // TODO : Insert validate method in Place class
                && this.startDate.before(this.endDate)
                && this.subStartDate.before(this.startDate)
                && this.subEndDate.after(this.subStartDate)
                && this.conflictLimitDate.after(this.subEndDate)
                && this.evaluationLimitDate.after(this.conflictLimitDate)
                && this.evaluationLimitDate.before(this.startDate)
                && this.organizersList.getOrganizersList().size() > 1;
    }

    /**
     * Schedules the state changes.
     *
     * @param exhibitionCenter the exhibition center necessary to deploy the
     * detect conlicts controller
     */
    public void createTimers(ExhibitionCenter exhibitionCenter) {

        ChangeToOpenApplications taskOpenApplications = new ChangeToOpenApplications(this);

        this.timer.schedule(taskOpenApplications, this.subStartDate);

        ChangeToClosedApplications taskClosedApplications = new ChangeToClosedApplications(this);

        this.timer.schedule(taskClosedApplications, this.subEndDate);

        DetectConflictsTask taskDetectConflicts = new DetectConflictsTask(this, exhibitionCenter);

        this.timer.schedule(taskDetectConflicts, this.subEndDate);

        ChangeToChangedConflicts taskChangeToChangedConflicts = new ChangeToChangedConflicts(this);

        this.timer.schedule(taskChangeToChangedConflicts, this.conflictLimitDate);

        ChangeToApplicationsInDecision taskChangeToApplicationsInDecision = new ChangeToApplicationsInDecision(this);

        this.timer.schedule(taskChangeToApplicationsInDecision, this.evaluationLimitDate);

    }

    /**
     * Gets the acceptance rate of the exhibition.
     *
     * @return acceptance rate
     */
    public float getAcceptanceRate() {
        float acceptanceRate = 0;

        List<Application> applications = this.applicationsList.getApplicationsList();

        for (Application application : applications) {
            if (((ExhibitionApplication) application).getDecision().isDecisionTrue()) {
                acceptanceRate++;
            }
        }

        return acceptanceRate / applications.size();
    }

    /**
     * Verify if the organizer belongs to this exhibition.
     *
     * @param organizer organizer to be verified
     * @return true if the organizer belongs to that exhibition, false otherwise
     */
    public boolean hasOrganizer(Organizer organizer) {
        return this.organizersList.getOrganizersList().contains(organizer);
    }

    @Override
    public String getData() {
        return String.format("Exhibition: %s (%s) (%s)", this.title, this.startDate, this.endDate);
    }

    /**
     * Gets a list of all applications analysis.
     *
     * @return list of all applications analysis
     */
    public List<ApplicationAnalysis> getApplicationsAnalysis() {
        List<ApplicationAnalysis> applicationAnalysisesList = new ArrayList<>();
        List<Application> applications = this.applicationsList.getApplicationsList();
        for (Application application : applications) {
            applicationAnalysisesList.add(new ApplicationAnalysis(application));
        }
        return applicationAnalysisesList;
    }

    /**
     * Export this exhibition to xml file
     *
     * @param path the path where the file will be saved
     */
    @Override
    public void jaxbObjectExportableToXML(String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Exhibition.class);

            Marshaller m = context.createMarshaller();
            //for pretty print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //write to system.out for debugging
            m.marshal(this, System.out);

            //write to file
            m.marshal(this, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDisplayInfo() {
        return String.format("Exhibition: %s (%s)", this.title, this.startDate);
    }

    /**
     * Set the current exhibition state of the exhibition to
     * applicationsInEvaluation state
     */
    @Override
    public void setSubmittableInApplicationsInEvaluationState() {
        this.currentState.setApplicationsInEvaluation();
    }

    @Override
    public String[] getInfo() {
        String[] info = new String[3];
        info[0] = this.getTitle();
        info[1] = this.getStartDate().toString();
        info[2] = this.getEndDate().toString();
        return info;
    }

    @Override
    public boolean removeAttribution(StaffAttribution staffAttribution) {
        return this.staffAttributionsList.removeStaffAttribution(staffAttribution);

    }

    @Override
    public boolean isDemonstration() {
        return false;
    }

    @Override
    public boolean isExhibition() {
        return true;
    }

    /**
     * Return the textual representation of a exhibition.
     *
     * @return the textual representation of a exhibition
     */
    @Override
    public String toString() {

        // TODO : Redo toString with all new attributes.
        return String.format("Exhibition{%ntitle=%s%ndescription=%s%n"
                + "startDate=%s%n"
                + "endDate=%s%n"
                + "subStartDate=%s%n"
                + "subEndDate=%s%n"
                + "place=%s%n"
                + "staffList=%s%n"
                + "organizersList=%s%n"
                + "applicationsList=%s%n"
                + "demonstrationsList=%s%n"
                + "staffAttributionsList=%s%n}",
                this.title, this.description, this.startDate, this.endDate, this.subStartDate,
                this.subEndDate, this.place, this.staffList, this.organizersList, this.applicationsList,
                this.demonstrationsList, this.staffAttributionsList);
    }

    /**
     * Compares two Exhibition objects.
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
        Exhibition otherExhibition = (Exhibition) otherObject;

        return this.title.equals(otherExhibition.title)
                && this.description.equals(otherExhibition.description)
                && this.startDate.equals(otherExhibition.startDate)
                && this.endDate.equals(otherExhibition.endDate)
                && this.subStartDate.equals(otherExhibition.subStartDate)
                && this.subEndDate.equals(otherExhibition.subEndDate)
                && this.place.equals(otherExhibition.place)
                && this.organizersList.equals(otherExhibition.organizersList)
                && this.staffList.equals(otherExhibition.staffList)
                && this.applicationsList.equals(otherExhibition.applicationsList)
                && this.demonstrationsList.equals(otherExhibition.demonstrationsList)
                && this.staffAttributionsList.equals(otherExhibition.staffAttributionsList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.description);
        hash = 17 * hash + Objects.hashCode(this.startDate);
        hash = 17 * hash + Objects.hashCode(this.endDate);
        hash = 17 * hash + Objects.hashCode(this.subStartDate);
        hash = 17 * hash + Objects.hashCode(this.subEndDate);
        hash = 17 * hash + Objects.hashCode(this.place);
        hash = 17 * hash + Objects.hashCode(this.staffList);
        hash = 17 * hash + Objects.hashCode(this.organizersList);
        hash = 17 * hash + Objects.hashCode(this.applicationsList);
        hash = 17 * hash + Objects.hashCode(this.demonstrationsList);
        hash = 17 * hash + Objects.hashCode(this.staffAttributionsList);
        return hash;
    }

    @Override
    public String getName() {
        return getTitle();
    }

}
