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
import javafx.util.Pair;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lapr.project.model.exhibition.ExhibitionApplicationsInDecisionState;
import lapr.project.model.exhibition.ExhibitionChangedConflictsState;
import lapr.project.model.exhibition.ExhibitionClosedApplicationsState;
import lapr.project.model.exhibition.ExhibitionCompleteState;
import lapr.project.model.exhibition.ExhibitionInicialState;
import lapr.project.model.exhibition.ExhibitionOpenApplicationsState;

import lapr.project.utils.Exportable;
import lapr.project.utils.Importable;

/**
 * Represents a exhibitions register
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionsRegister implements Importable, Serializable {

    /**
     * exhibitions List of ExhibitionsRegister
     */
    private List<Exhibition> exhibitionsList;

    /**
     * Builds Exhibition Register instances with no params.
     *
     *
     */
    public ExhibitionsRegister() {
        this.exhibitionsList = new ArrayList<>();
    }

    /**
     * Builds Exhibition Register instances with arrayList of exhibitions as
     * param.
     *
     *
     * @param exhibitionsList exhibitions list
     */
    public ExhibitionsRegister(List<Exhibition> exhibitionsList) {
        this.exhibitionsList = new ArrayList<>(exhibitionsList);
    }

    /**
     * Builds Exhibition Register instances with arrayList of exhibitions as
     * param.
     *
     *
     * @param exhibitionRegister a instance of this class, copy constructor
     */
    public ExhibitionsRegister(ExhibitionsRegister exhibitionRegister) {
        this.exhibitionsList = new ArrayList<>(exhibitionRegister.exhibitionsList);
    }

    /**
     * Creates a new instance of a Exhibition.
     *
     * @return a new instance of a Exhibition
     */
    public Exhibition newExhibition() {

        return new Exhibition();
    }

    /**
     * Returns the whole exhibitions list referring to this instance.
     *
     * @return the list of exhibitions referring to this instance
     */
    public List<Exhibition> getExhibitionsList() {

        return new ArrayList(exhibitionsList);
    }

    /**
     * Sets the exhibitions list.
     *
     * @param exhibitionsList exhibitions list
     */
    public void setExhibitionsList(List<Exhibition> exhibitionsList) {
        this.exhibitionsList = new ArrayList(exhibitionsList);
    }

    /**
     * Validate if the list doesn't contain an exhibition.
     *
     * @param exhibition the exhibition to validate
     * @return true if list doesn't contain the exhibition
     */
    private boolean validateExhibition(Exhibition exhibition) {

        return !this.exhibitionsList.contains(exhibition);
    }

    /**
     * Add an exhibition to the list.
     *
     * @param exhibition the exhibition to add
     * @return true if the exhibition is sucessfully added.
     */
    private boolean addExhibition(Exhibition exhibition) {

        return this.exhibitionsList.add(exhibition);
    }

    /**
     * Register exhibition.
     *
     * @param exhibition Exhibition to add
     * @param exhibitionCenter the exhibition center necessary to deploy the
     * detect conlicts controller
     *
     * @return true if exhibition is successfully added.
     */
    public boolean registerExhibition(Exhibition exhibition, ExhibitionCenter exhibitionCenter) {

        exhibition.createTimers(exhibitionCenter);

        return (exhibition.setCreated() && validateExhibition(exhibition)) ? addExhibition(exhibition) : false;
    }

    /**
     * Registers the imported exhibition.
     *
     * @param exhibition the imported exhibition
     * @return true if validated
     */
    public boolean registerImportedExhibition(Exhibition exhibition) {
        return (validateExhibition(exhibition) ? addExhibition(exhibition) : false);
    }

    /**
     * Gets the submittables filtering by a staff member.
     *
     * @param staffMember staff member to be filtered
     * @return the list of the staff member's submittables
     */
    public List<Submittable> getSubmittablesByStaff(StaffMember staffMember) {
        List<Submittable> submittableList = new ArrayList<>();

        for (Exhibition exhibition : exhibitionsList) {
            if (exhibition.getStaffList().isStaffMember(staffMember)) {
                submittableList.add(exhibition);
            }

            for (Demonstration demonstration : exhibition.getDemonstrationsList().getDemonstrationsList()) {
                if (demonstration.getStaffList().isStaffMember(staffMember)) {
                    submittableList.add(demonstration);
                }
            }
        }

        return submittableList;
    }

    /**
     * Gets exhibitions list with applications in submission of this exhibitor
     * responsible
     *
     * @param exhibitorResponsible the exhibition responsible
     * @return the list of exhibitions
     */
    public List<Exhibition> getExhibitionsListWithApplicationsInSubmissionByExhibitorResponsible(ExhibitorResponsible exhibitorResponsible) {
        List<Exhibition> exhibitionsList = new ArrayList();
        boolean isExhibitorResponsible = false;
        for (Exhibition exhibition : this.exhibitionsList) {
            boolean isInSubmittingApplication = exhibition.getState().isApplicationsDecided();
            ApplicationsList applicationsList = exhibition.getApplicationsList();
            List<Application> applicationsArrayList = applicationsList.getApplicationsList();

            for (Application application : applicationsArrayList) {
                isExhibitorResponsible = ((ExhibitionApplication) application).isExhibitorResponsible(exhibitorResponsible);
                if (isExhibitorResponsible) {
                    break;
                }
            }
            if (isInSubmittingApplication && isExhibitorResponsible) {
                exhibitionsList.add(exhibition);
            }
        }
        return exhibitionsList;
    }

    /**
     * Gets the exhibitions list without StaffMember by Organizer.
     *
     * @param organizer the organizer to check for
     * @return the exhibition list
     */
    public List<Exhibition> getExhibitionsListWithoutStaffMemberByOrganizer(Organizer organizer) {
        List<Exhibition> tempExhibitionList = new ArrayList();
        for (Exhibition exhibition : exhibitionsList) {
            boolean isStaffMemberDefined = exhibition.getState().isCreated() || exhibition.getState().isDemonstrationsDefined() && !exhibition.getState().isStaffDefined();

            if (isStaffMemberDefined && exhibition.getOrganizersList().hasOrganizer(organizer)) {
                tempExhibitionList.add(exhibition);
            }
        }
        return tempExhibitionList;
    }

    /**
     * Get the exhibitions list without Demonstrations defined by Organizer.
     *
     * @param organizer the organizer to filter exhibitions
     * @return the exhibition list without Demonstrations defined by Organizer
     */
    public List<Exhibition> getExhibitionsListWithoutDemosDefined(Organizer organizer) {

        List<Exhibition> exhibitions = new ArrayList();

        for (Exhibition exhibition : this.exhibitionsList) {

            if (exhibition.isNotDemonstrationsDefined() && exhibition.hasOrganizer(organizer)) {
                exhibitions.add(exhibition);
            }
        }
        return exhibitions;
    }

    /**
     * Get the exhibitions list with Applications Decided (and Demos in created
     * state) by Organizer.
     *
     * @param organizer the organizer to filter exhibitions
     * @return the exhibitions list with Applications Decided (and Demos in
     * created state) by Organizer
     */
    public List<Exhibition> getExhibitionsAppsDecidedAndDemosCreated(Organizer organizer) {

        List<Exhibition> exhibitions = new ArrayList();

        for (Exhibition exhibition : this.exhibitionsList) {

            if (exhibition.hasOrganizer(organizer) && exhibition.isApplicationsDecided()) {
                if (exhibition.isDemonstrationsInCreatedState()) {
                    exhibitions.add(exhibition);
                }
            }
        }
        return exhibitions;
    }

    /**
     * Gets the exhibitions list with application Decided by Organizer.
     *
     * @param organizer the organizer to check for
     * @return the exhibition list
     */
    public List<Exhibition> getExhibitionsListWithApplicationsDecidedByOrganizer(Organizer organizer) {
        List<Exhibition> tempExhibitionList = new ArrayList();
        for (Exhibition exhibition : exhibitionsList) {
            boolean isApplicationsDecided = exhibition.getState().isApplicationsDecided();

            if (isApplicationsDecided && exhibition.getOrganizersList().hasOrganizer(organizer)) {
                tempExhibitionList.add(exhibition);
            }
        }
        return tempExhibitionList;
    }

    /**
     * Gets the submittables filtering by an Organizer and InChangedConflicts
     * state.
     *
     * @param organizer organizer to filter submittables
     *
     * @return the list of the organizer's submittables which are
     * InChangedConflicts state
     */
    public List<Submittable> getSubmittablesInChangedConflictsByOrganizer(Organizer organizer) {
        List<Submittable> submittablesList = new ArrayList<>();

        for (Exhibition exhibition : exhibitionsList) {
            if ((exhibition.getOrganizersList().hasOrganizer(organizer)) && (exhibition.getState().isChangedConflitcts())) {
                submittablesList.add(exhibition);
            }
            if (exhibition.getState().isApplicationsDecided()) {
                List<Demonstration> demoListInChangedConflicts = exhibition.getDemonstrationsList().getDemonstrationsListInChangedConflicts();
                submittablesList.addAll(demoListInChangedConflicts);
            }
        }
        return submittablesList;
    }

    /**
     * Obtain the submittables filtering by Decided state.
     *
     * @return the submittables filtering by Decided state
     */
    public List<Submittable> getDecidedSubmittables() {
        List<Submittable> submittablesList = new ArrayList<>();

        for (Exhibition exhibition : exhibitionsList) {

            boolean isApplicationsDecided = exhibition.isApplicationsDecided();

            if (isApplicationsDecided) {

                submittablesList.add(exhibition);

                DemonstrationsList demonstrationsList = exhibition.getDemonstrationsList();
                List<Demonstration> decidedDemosntrations = demonstrationsList.getDecidedApplicationsDemonstrations();

                submittablesList.addAll(decidedDemosntrations);
            }
        }
        return submittablesList;
    }

    /**
     * Gets the decided exhibitions.
     *
     * @return decided exhibitions
     */
    public List<Exhibition> getDecidedExhibitions() {
        List<Exhibition> decidedExhibitions = new ArrayList<>();

        for (Exhibition exhibition : exhibitionsList) {
            if (exhibition.isApplicationsDecided()) {
                decidedExhibitions.add(exhibition);
            }
        }

        return decidedExhibitions;
    }

    /**
     * Gets the decided exhibitions filtering by organizer.
     *
     * @param organizer the organizer
     * @return decided exhibitions filtered by organizer
     */
    public List<Exhibition> getDecidedExhibitionsByOrganizer(Organizer organizer) {
        List<Exhibition> decidedExhibitionsByOrganizer = new ArrayList<>();

        for (Exhibition exhibition : exhibitionsList) {
            if (exhibition.isApplicationsDecided() && exhibition.hasOrganizer(organizer)) {
                decidedExhibitionsByOrganizer.add(exhibition);
            }
        }

        return decidedExhibitionsByOrganizer;
    }

    /**
     * Gets the exhibitions acceptance rate.
     *
     * @param exhibitionsList the list with exhibitions
     * @return exhibitions with acceptance rate
     */
    public List<Pair<Exhibition, Float>> getPairExhibitionAcceptanceRate(List<Exhibition> exhibitionsList) {
        List<Pair<Exhibition, Float>> pairExhibitionAcceptanceRate = new ArrayList<>();

        for (Exhibition exhibition : exhibitionsList) {
            float acceptanceRate = exhibition.getAcceptanceRate();
            pairExhibitionAcceptanceRate.add(new Pair<>(exhibition, acceptanceRate));
        }

        return pairExhibitionAcceptanceRate;
    }

    /**
     * Gets the decisable list of this organizer in exhibition and demonstration
     * applications.
     *
     * @param organizer the organizer to check for
     * @return the list of decisables
     */
    public List<Decisable> getDecisableListByOrganizer(Organizer organizer) {
        ApplicationsList applicationsList;
        List<Decisable> decisableList = new ArrayList<>();
        for (Exhibition exhibition : exhibitionsList) {
            boolean isOrganizer = exhibition.getOrganizersList().hasOrganizer(organizer);
            applicationsList = exhibition.getApplicationsList();
            decisableList.addAll(loopDecisables(applicationsList, isOrganizer));
            DemonstrationsList demonstrationsList = exhibition.getDemonstrationsList();
            List<Demonstration> demonstrationArrayList = demonstrationsList.getDemonstrationsList();
            for (Demonstration demonstration : demonstrationArrayList) {
                isOrganizer = demonstration.getOrganizersList().hasOrganizer(organizer);
                applicationsList = demonstration.getApplicationsList();
                decisableList.addAll(loopDecisables(applicationsList, isOrganizer));
            }
        }

        return decisableList;
    }

    /**
     * Gets the submittables filtering by a Staff Member and InDetectedConflicts
     * state.
     *
     * @param staffMember staffMember to filter submittables
     *
     * @return the list of the staffMember's submittables which are
     * InDetectedConflicts state
     */
    public List<Submittable> getSubmittablesInDetectedConflictsByStaffMember(StaffMember staffMember) {
        List<Submittable> submittableListByOrganizer = new ArrayList();

        for (Exhibition exhibition : exhibitionsList) {
            if ((exhibition.isStaffMember(staffMember)) && (exhibition.getState().isDetectedConficts())) {
                submittableListByOrganizer.add(exhibition);
            }

            for (Demonstration demonstration : exhibition.getDemonstrationsList().getDemonstrationsList()) {
                if ((demonstration.isStaffMember(staffMember)) && (demonstration.getCurrentState().isDetectedConflicts())) {
                    submittableListByOrganizer.add(demonstration);
                }
            }
        }

        return submittableListByOrganizer;
    }

    /**
     * Returns the exhibitions list with application in submitting state.
     *
     * @return returns the exhibitions list with application in submitting state
     */
    public List<Exhibition> getExhibitionListWithApplicationInSubmittingState() {

        List<Exhibition> exhibitionListInRightState = new ArrayList();
        boolean validated;
        for (Exhibition exhibition : this.exhibitionsList) {

            validated = exhibition.getState().isOpenApplications();

            if (validated) {
                exhibitionListInRightState.add(exhibition);
            }
        }
        return exhibitionListInRightState;

    }

    /**
     * Gets the submittable filtering by the exhibitor responsible and
     * application in submission state
     *
     * @param exhibitorResponsible exhibitor responsible
     * @return list of submittable filtering by the exhibitor responsible and
     * application in submission state
     */
    public List<Submittable> getSubmittablesApplicationInSubmissionByExhibitorResponsible(ExhibitorResponsible exhibitorResponsible) {
        List<Submittable> submittables = new ArrayList<>();

        for (Exhibition exhibition : this.exhibitionsList) {

            ApplicationsList applicationsListExhibition = exhibition.getApplicationsList();
            if (applicationsListExhibition.isEditableOfExhibitorResponsibleInSubmission(exhibitorResponsible)) {
                submittables.add(exhibition);
            }

            DemonstrationsList demonstrationsList = exhibition.getDemonstrationsList();
            List<Demonstration> demonstrations = demonstrationsList.getDemonstrationsList();

            for (Demonstration demonstration : demonstrations) {

                ApplicationsList applicationsListDemonstration = demonstration.getApplicationsList();
                if (applicationsListDemonstration.isEditableOfExhibitorResponsibleInSubmission(exhibitorResponsible)) {
                    submittables.add(demonstration);
                }
            }
        }

        return submittables;
    }

    /**
     * Returns the removables list filtered out by an exhibitor responsible
     *
     * @param exhibitorResponsible exhibitor responsible to filter removables
     * @return the removables list filtered out by an exhibitor responsible
     */
    public List<Removable> getRemovables(ExhibitorResponsible exhibitorResponsible) {
        List<Removable> removablesList = new ArrayList();
        List<Application> applicationsList = new ArrayList();
        Removable removable;

        for (Exhibition exhibition : this.exhibitionsList) {
            applicationsList = exhibition.getApplicationsList().getApplicationsList();
            if (exhibition.getState().isOpenApplications()) {
                for (Application application : applicationsList) {
                    removable = (Removable) application;
                    if (removable.getExhibitorResponsible().equals(exhibitorResponsible)) {
                        if (!removable.isRemoved()) {
                            removablesList.add(removable);
                        }
                    }
                }
            }
            if (exhibition.getState().isApplicationsDecided()) {
                removablesList.addAll(exhibition.getDemonstrationsList().getRemovables(exhibitorResponsible));
            }
        }
        return removablesList;
    }

    /**
     * Method that loops a application list to get the decisables
     *
     * @param applicationsList the list of applications
     * @param isOrganizer true if current actor is organizer on a application
     * @return list of decisables
     */
    private List<Decisable> loopDecisables(ApplicationsList applicationsList, boolean isOrganizer) {
        List<Decisable> decisableList = new ArrayList<>();
        List<Decisable> applicationsArrayList = new ArrayList(applicationsList.getApplicationsList());
        for (Decisable decisable : applicationsArrayList) {
            boolean isInDecision = decisable.isInDecision();
            if (isOrganizer && isInDecision) {
                decisableList.add(decisable);
            }
        }
        return decisableList;
    }

    /**
     * Imports a exhibition from a XML file.
     *
     * @param fileName the name of the file with path
     * @return a exhibition created from the file
     */
    @Override
    public Exhibition importByFileName(String fileName, ExhibitionCenter exhibitionCenter) {
        Exhibition exhibition;
        try {
            File xmlFile = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Exhibition.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            exhibition = (Exhibition) jaxbUnmarshaller.unmarshal(xmlFile);
            prepareImportedExhibition(exhibition, exhibitionCenter);
            return exhibition;

        } catch (JAXBException ex) {
            JOptionPane.showMessageDialog(null, "Error ocurred while importing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Prepares the imported exhibition states and timers.
     *
     * @param exhibition the exhibition
     */
    private void prepareImportedExhibition(Exhibition exhibition, ExhibitionCenter exhibitionCenter) {

        boolean validatedState;
        Date date = new Date();

        exhibition.createTimers(exhibitionCenter);
        if (exhibition.getEvaluationLimitDate().before(date)) {
            exhibition.setState(new ExhibitionApplicationsInDecisionState(exhibition));
            exhibition.getState().setApplicationsDecided();
        } else if (exhibition.getConflictLimitDate().before(date)) {
            exhibition.setState(new ExhibitionChangedConflictsState(exhibition));
        } else if (exhibition.getSubEndDate().before(date)) {
            exhibition.setState(new ExhibitionClosedApplicationsState(exhibition));
        } else if (exhibition.getSubStartDate().before(date)) {
            exhibition.setState(new ExhibitionOpenApplicationsState(exhibition));
        } else {

            exhibition.setState(new ExhibitionInicialState(exhibition));
            exhibition.getState().setCreated();
            if (exhibition.getState().setDemonstrationsDefined()) {

            } else if (exhibition.getState().setStaffDefined()) {

            } else if (!exhibition.getStaffList().getStaffList().isEmpty() && !exhibition.getDemonstrationsList().getDemonstrationsList().isEmpty()) {
                exhibition.setState(new ExhibitionCompleteState(exhibition));
            }

            exhibition.getState().setDetectedConflicts();

            exhibition.getState().setApplicationsInEvaluation();

            exhibition.getState().setApplicationsDecided();
        }

    }

    /**
     * Gets the exhibition applications on assigned stand state filtering by
     * exhibitor responsible.
     *
     * @param exhibitorResponsible the exhibitor responsible
     * @return exhibition applications on assigned stand state filtered by
     * exhibitor responsible
     */
    public List<ExhibitionApplication> getExhibitionApplicationsAssignedStandByExhibitorResponsible(ExhibitorResponsible exhibitorResponsible) {
        List<ExhibitionApplication> exhibitionApplications = new ArrayList<>();

        for (Exhibition exhibition : this.exhibitionsList) {
            ApplicationsList applicationsList = exhibition.getApplicationsList();
            if (applicationsList.isExhibitorResponsibleApplicaitonOnAssignedStand(exhibitorResponsible)) {
                exhibitionApplications.add((ExhibitionApplication) applicationsList.getExhibitorResponsibleApplication(exhibitorResponsible));
            }
        }

        return exhibitionApplications;
    }

    /**
     * Gets the exhibitions filtering by organizer.
     *
     * @param organizer the organizer
     * @return the exhibitions filtered by organizer
     */
    public List<Exportable> getExhibitionsListByOrganizer(Organizer organizer) {
        List<Exportable> exhibitionsByOrganizer = new ArrayList();

        for (Exhibition exhibition : exhibitionsList) {
            if (exhibition.hasOrganizer(organizer)) {
                exhibitionsByOrganizer.add(exhibition);
            }
        }
        return exhibitionsByOrganizer;
    }

    /**
     * Gets the submittables filtered by organizer.
     *
     * @param organizer the organizer used to filter
     * @return returns the submittables filtered by organizer
     */
    public List<Submittable> getSubmittablesListByOrganizer(Organizer organizer) {
        List<Submittable> submittablesList = new ArrayList();
        List<Demonstration> demonstrationsList = new ArrayList();

        for (Exhibition exhibition : exhibitionsList) {
            if (exhibition.hasOrganizer(organizer)) {
                submittablesList.add(exhibition);
                demonstrationsList = exhibition.getDemonstrationsList().getDemonstrationsList();
                for (Demonstration demonstration : demonstrationsList) {
                    submittablesList.add(demonstration);
                }
            }
        }
        return submittablesList;
    }

    /**
     * Gets the exhibitionApplications removed, filtered by organizer.
     *
     * @param submittablesListByOrganizer the submittables list used to get the
     * list of exhibition applications removed, filtered by organizer
     * @return the list of exhibition applications removed, filtered by
     * organizer
     */
    public List<Application> getExhibitionApplicationsRemovedByOrganizer(List<Submittable> submittablesListByOrganizer) {
        List<Application> applicationsList = new ArrayList();
        List<Application> exhibitionApplicationsRemovedList = new ArrayList();

        for (Submittable submittable : submittablesListByOrganizer) {
            if (submittable.isExhibition()) {
                applicationsList = submittable.getApplicationsList().getApplicationsList();
                for (Application application : applicationsList) {
                    if (application.isRemoved()) {
                        exhibitionApplicationsRemovedList.add(application);
                    }
                }
            }
        }
        return exhibitionApplicationsRemovedList;
    }

    /**
     * Gets the demonstrationApplications removed, filtered by organizer.
     *
     * @param submittablesListByOrganizer the submittables list used to get the
     * list of demonstration applications removed, filtered by organizer
     * @return the list of demonstration applications removed, filtered by
     * organizer
     */
    public List<Application> getDemonstraionApplicationsRemovedByOrganizer(List<Submittable> submittablesListByOrganizer) {
        List<Application> applicationsList = new ArrayList();
        List<Application> demonstrationApplicationsRemovedList = new ArrayList();

        for (Submittable submittable : submittablesListByOrganizer) {
            if (submittable.isDemonstration()) {
                applicationsList = submittable.getApplicationsList().getApplicationsList();
                for (Application application : applicationsList) {
                    if (application.isRemoved()) {
                        demonstrationApplicationsRemovedList.add(application);
                    }
                }
            }
        }
        return demonstrationApplicationsRemovedList;
    }

    /**
     * Returns the textual interpretation of the objects and attributes of this
     * class
     *
     * @return textual representation for this object
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ExhibitionsRegister{");
        for (Exhibition ex : exhibitionsList) {
            s.append(String.format("%s%n", ex));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it represents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        ExhibitionsRegister otherExhibitionsRegister = (ExhibitionsRegister) otherObject;

        return this.exhibitionsList.equals(otherExhibitionsRegister.exhibitionsList);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.exhibitionsList);
        return hash;
    }

    public List<Submittable> getAllSubmittables() {

        List<Submittable> submittables = new ArrayList<>();

        for (Exhibition exhibition : this.exhibitionsList) {

            submittables.add(exhibition);

            for (Demonstration demo : exhibition.getDemonstrationsList().getDemonstrationsList()) {
                submittables.add(demo);
            }
        }
        return submittables;
    }

}
