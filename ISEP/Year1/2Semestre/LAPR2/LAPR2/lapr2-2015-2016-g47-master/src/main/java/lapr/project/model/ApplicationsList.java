/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javafx.util.Pair;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a applications list
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
public class ApplicationsList implements Serializable {

    /**
     * List of applications.
     */
    @XmlElementWrapper(name = "applications_list")
    @XmlElements({
        @XmlElement(name = "exhibition_application", type = ExhibitionApplication.class),
        @XmlElement(name = "demonstration_application", type = DemonstrationApplication.class)
    })
    private List<Application> applicationsList;

    /**
     * Creates an instance of ApplicationsList with its default values.
     */
    public ApplicationsList() {
        this.applicationsList = new ArrayList<>();
    }

    /**
     * Creates an instance of ApplicationsList receiving the list of
     * applications to an exhibition.
     *
     * @param applicationsList list of applications
     */
    public ApplicationsList(List<Application> applicationsList) {
        this.applicationsList = new ArrayList<>(applicationsList);
    }

    /**
     * Creates an instance of ApplicationsList copying another list of
     * applications.
     *
     * @param applicationsList list of applications
     */
    public ApplicationsList(ApplicationsList applicationsList) {
        this.applicationsList = new ArrayList<>(applicationsList.applicationsList);
    }

    /**
     * Gets the list of applications.
     *
     * @return list of applications
     */
    @XmlTransient
    public List<Application> getApplicationsList() {
        return this.applicationsList;
    }

    /**
     * Sets the list of applications.
     *
     * @param applicationsList list of applications
     */
    public void setApplicationsList(List<Application> applicationsList) {
        this.applicationsList = new ArrayList<>(applicationsList);
    }

    /**
     * Creates a new exhibition application.
     *
     * @return the new exhibition application
     */
    public Application newExhibitionApplication() {
        Application application = new ExhibitionApplication();
        return application;
    }

    /**
     * Creates a new demonstration application.
     *
     * @return the new demonstration application
     */
    public Application newDemonstrationApplication() {
        Application application = new DemonstrationApplication();
        return application;
    }

    /**
     * Gets applications in accepted state.
     *
     * @return the list of applications
     */
    public List<ExhibitionApplication> getApplicationsInAcceptedState() {
        List<ExhibitionApplication> tmpApplications = new ArrayList<>();
        for (Application application : applicationsList) {
            //ONLY TO VERIFY ALTHOUGH ALL APPLICATIONS ARE EXHIBITION APPLICATIONS 
            if (application instanceof ExhibitionApplication) {
                boolean isAccepted = ((ExhibitionApplication) application).getCurrentState().isAccepted();
                if (isAccepted) {
                    tmpApplications.add((ExhibitionApplication) application);
                }
            }
        }
        return tmpApplications;
    }

    /**
     * Validates the demonstration application.
     *
     * @param application the application to validate
     * @return true if all OK
     */
    public boolean validateDemonstrationApplication(Application application) {
        return !this.applicationsList.contains(application) && application.validate();
    }

    /**
     * Sets this demonstration state and adds this demonstration application to
     * the list.
     *
     * @param application the application to add.
     */
    public void registerDemonstrationApplication(Application application) {
        if (((DemonstrationApplication) application).getCurrentState().setInSubmission()) {
            this.applicationsList.add(application);
        }
    }

    /**
     * Verify if the exhibitor responsible has an editable in submission.
     *
     * @param exhibitorResponsible exhibitor responsible
     * @return true if the exhibitors repsible's editable is in submission
     */
    public boolean isEditableOfExhibitorResponsibleInSubmission(ExhibitorResponsible exhibitorResponsible) {
        boolean result = false;

        for (Application application : applicationsList) {
            Editable editable = (Editable) application;
            if (editable.isExhibitorResponsible(exhibitorResponsible) && editable.isInSubmission()) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Gets the application referring to a exhibitor responsible
     *
     * @param exhibitorResponsible the exhibitor responsible
     * @return the application
     */
    public Application getApplicationByExhibitorResponsible(ExhibitorResponsible exhibitorResponsible) {
        for (Application application : applicationsList) {
            //no need to check instance of; whenever this method is called its always from a exhibition application perspective
            boolean isExhibitorResponsible = ((ExhibitionApplication) application).isExhibitorResponsible(exhibitorResponsible);
            if (isExhibitorResponsible) {
                return application;
            }
        }
        return null;
    }

    /**
     * Gets the editable by a exhibitor responsible.
     *
     * @param exhibitorResponsible exhibitor responsible
     * @return the editable
     */
    public Editable getEditableByExhibitorResponsible(ExhibitorResponsible exhibitorResponsible) {
        Editable editable = null;

        for (Application application : applicationsList) {
            if (((Editable) application).isExhibitorResponsible(exhibitorResponsible)) {
                editable = (Editable) application;
            }
        }

        return editable;
    }

    /**
     * Makes a clone of a given editable.
     *
     * @param editable editable to clone
     * @return clone of the given editable
     */
    public Editable cloneEditable(Editable editable) {
        return editable.cloneToEditable();
    }

    /**
     * Validate if the new editable is valid, ignoring the old one.
     *
     * @param editableNew new editable
     * @param editableOld old editable
     * @return true if it is valid, false otherwise
     */
    public boolean validateEditable(Editable editableNew, Editable editableOld) {
        return editableNew.validate() && validateNewEditable(editableNew, editableOld);
    }

    /**
     * Validate if the new editable fits in the applications list, ignoring the
     * old one.
     *
     * @param editableNew new editable
     * @param editableOld old editable
     * @return true if it fits, false otherwise
     */
    private boolean validateNewEditable(Editable editableNew, Editable editableOld) {
        return editableNew.equals(editableOld) ? true : !this.applicationsList.contains((Application) editableNew);
    }

    /**
     * Modify the given old editable with the new editable.
     *
     * @param newEditable new editable to be defined
     * @param oldEditable old editable to be removed
     * @return true if it is added with successfull, false otherwise
     */
    public boolean modifyEditable(Editable newEditable, Editable oldEditable) {
        this.applicationsList.remove((Application) oldEditable);
        return this.applicationsList.add((Application) newEditable);
    }

    /**
     * Verify if the exhibitor responsible application is on assigned stand
     * state.
     *
     * @param exhibitorResponsible exhibitor responsible to search
     * @return true if it is, false otherwise
     */
    public boolean isExhibitorResponsibleApplicaitonOnAssignedStand(ExhibitorResponsible exhibitorResponsible) {
        for (Application application : applicationsList) {
            if (((ExhibitionApplication) application).isExhibitorResponsible(exhibitorResponsible) && ((ExhibitionApplication) application).isAssignedStand()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the exhibitor resposible application.
     *
     * @param exhibitorResponsible exhibitor responsible
     * @return application
     */
    public Application getExhibitorResponsibleApplication(ExhibitorResponsible exhibitorResponsible) {
        for (Application application : applicationsList) {
            if (((ExhibitionApplication) application).isExhibitorResponsible(exhibitorResponsible)) {
                return application;
            }
        }
        return null;
    }

    /**
     * Calculate the accepted applcations keywords ranking.
     *
     * @return the accepted applcations keywords ranking
     */
    public List<Pair<Keyword, Integer>> calculateAcceptedAppsKeywordsRankings() {

        List<Keyword> keywordRanking = new ArrayList<>();
        List<Integer> frequency = new ArrayList<>();

        for (Application application : this.applicationsList) {

            boolean isAccepted = application.isDecisionAccepted();

            if (isAccepted) {

                List<Keyword> keywordsList = application.getKeywordsList();

                createRanking(keywordsList, keywordRanking, frequency);
            }
        }
        return populateRankingPair(keywordRanking, frequency);
    }

    /**
     * Calculate the declined applcations keywords ranking.
     *
     * @return the declined applcations keywords ranking
     */
    public List<Pair<Keyword, Integer>> calculateDeclinedAppsKeywordsRankings() {

        List<Keyword> keywordRanking = new ArrayList<>();
        List<Integer> frequency = new ArrayList<>();

        for (Application application : this.applicationsList) {

            boolean isDeclined = application.isDesionDeclined();

            if (isDeclined) {

                List<Keyword> keywordsList = application.getKeywordsList();

                createRanking(keywordsList, keywordRanking, frequency);
            }
        }
        return populateRankingPair(keywordRanking, frequency);
    }

    /**
     * Reorganizes the keywordsRanking & frequency into a List of Pairs.
     *
     * @param keywordRanking
     * @param frequency
     * @return the list of a keyword & its frequency pair.
     */
    private List<Pair<Keyword, Integer>> populateRankingPair(List<Keyword> keywordRanking, List<Integer> frequency) {

        List<Pair<Keyword, Integer>> ranking = new ArrayList<>();

        for (int i = 0; i < keywordRanking.size(); i++) {

            Pair<Keyword, Integer> pair = new Pair<>(keywordRanking.get(i), frequency.get(i));
            ranking.add(pair);
        }

        // TODO : Verify if the requirements is alphabetic order
        Comparator alfabeticOrder = (Comparator) (Object o1, Object o2) -> {
            Keyword keyword1 = (Keyword) ((Pair) o1).getKey();
            Keyword keyword2 = (Keyword) ((Pair) o2).getKey();

            return keyword1.compareTo(keyword2);
        };

        Collections.sort(ranking, alfabeticOrder);

        return ranking;
    }

    /**
     * Creates the ranking of keywords of all applications.
     *
     * @param keywordsList application keywords
     * @param keywordRanking keyword ranking
     * @param frequency frequency of the keywords
     */
    private void createRanking(List<Keyword> keywordsList, List<Keyword> keywordRanking, List<Integer> frequency) {

        for (Keyword keyword : keywordsList) {

            if (keywordRanking.contains(keyword)) {

                int index = keywordRanking.indexOf(keyword);

                Integer integer = frequency.get(index);
                integer++;
                frequency.set(index, integer);
            } else {
                keywordRanking.add(keyword);
                frequency.add(1);
            }
        }
    }

    /**
     * Return the textual representation of a ApplicationsList.
     *
     * @return the textual representation of a ApplicatiosList
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ExhibitionApplicationList{");
        for (Application application : this.applicationsList) {
            s.append(String.format("%s%n", application));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares two ApplicationsList objects.
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
        ApplicationsList otherApplicationsList = (ApplicationsList) otherObject;

        return this.applicationsList.equals(otherApplicationsList.applicationsList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.applicationsList);
        return hash;
    }
}
