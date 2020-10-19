/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lapr.project.model.application.ApplicationInitialState;

/**
 * Represents an demonstration application
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DemonstrationApplication implements Application, Conflictable, Assingnable, Decisable, Evaluable, Removable, Editable, Serializable {

    /**
     * The title of the application.
     */
    @XmlAttribute
    private String title;

    /**
     * The list of keywords.
     */
    @XmlElementWrapper(name = "keywords_list")
    @XmlElement(name = "keyword")
    private List<Keyword> keywordsList;

    /**
     * The exhibitor.
     */
    private Exhibitor exhibitor;

    /**
     * The area asked by the company.
     */
    private float exhibitorArea;

    /**
     * The number of invitations asked for.
     */
    private int numberInvitations;

    /**
     * The list of products the company wishes to expose.
     */
    @XmlElementWrapper(name = "products_list")
    @XmlElement(name = "product")
    private List<Product> productsList;

    /**
     * The evaluation lists of this application.
     */
    @XmlElementWrapper(name = "evaluations_list")
    @XmlElement(name = "evaluation")
    private List<Evaluation> evaluationsList;
    /**
     * This instances decision.
     */
    private Decision decision;

    /**
     * The state of the application.
     */
    @XmlTransient
    private ApplicationState currentState;

    /**
     * The default title.
     */
    private static final String DEFAULT_TITLE = "No Title";

    /**
     * The default area in float asked by the company.
     */
    private static final float DEFAULT_EXHIBITOR_AREA = 100.0f;
    /**
     * The default number of invitations asked for.
     */
    private static final int DEFAULT_NUMBER_INVITATION = 10;

    /**
     * Creates an instance of demonstration application with its default values.
     */
    public DemonstrationApplication() {
        this.title = DEFAULT_TITLE;
        this.keywordsList = new ArrayList<>();
        this.exhibitor = new Exhibitor();
        this.exhibitorArea = DEFAULT_EXHIBITOR_AREA;
        this.numberInvitations = DEFAULT_NUMBER_INVITATION;
        this.productsList = new ArrayList<>();
        this.evaluationsList = new ArrayList<>();
        this.currentState = new ApplicationInitialState(this);
    }

    /**
     * Constructs an instance of exhibition application receivong its
     * parameters.
     *
     * @param title title for the application
     * @param keyWords keywords list
     * @param exhibitor exhibitor repnsible
     * @param exhibitorArea exhibition area
     * @param numberInvitations number of invites
     * @param productList products list
     * @param evaluationsList evaluations list
     */
    public DemonstrationApplication(String title, List<Keyword> keyWords, Exhibitor exhibitor,
            float exhibitorArea, int numberInvitations,
            List<Product> productList, List<Evaluation> evaluationsList) {
        this.title = title;
        this.keywordsList = new ArrayList<>(keyWords);
        this.exhibitor = new Exhibitor(exhibitor);
        this.exhibitorArea = exhibitorArea;
        this.numberInvitations = numberInvitations;
        this.productsList = new ArrayList(productList);
        this.evaluationsList = new ArrayList(evaluationsList);
        this.currentState = new ApplicationInitialState(this);

    }

    /**
     * Constructs an instance of demonstration application copying another
     * demonstration application.
     *
     * @param demonstrationApplication another demonstration application
     */
    public DemonstrationApplication(DemonstrationApplication demonstrationApplication) {
        this.title = demonstrationApplication.title;
        this.keywordsList = demonstrationApplication.keywordsList;
        this.exhibitor = demonstrationApplication.exhibitor;
        this.exhibitorArea = demonstrationApplication.exhibitorArea;
        this.numberInvitations = demonstrationApplication.numberInvitations;
        this.productsList = new ArrayList(demonstrationApplication.productsList);
        this.evaluationsList = new ArrayList(demonstrationApplication.evaluationsList);
        this.currentState = demonstrationApplication.currentState;
    }

    /**
     * Sets the application's title.
     *
     * @param title application's title
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the exhibitor.
     *
     * @return exhibitor
     */
    @Override
    public Exhibitor getExhibitor() {
        return exhibitor;
    }

    /**
     * Gets the keywords list.
     *
     * @return keywords list
     */
    @Override
    public List<Keyword> getKeywordsList() {
        return keywordsList;
    }

    /**
     * Sets the keywords list.
     *
     * @param keywordsList keywords list
     */
    @Override
    public void setKeywordsList(List<Keyword> keywordsList) {
        this.keywordsList = new ArrayList<>(keywordsList);
    }

    /**
     * Sets the exhibitor.
     *
     * @param exhibitor exhibitor
     */
    public void setExhibitor(Exhibitor exhibitor) {
        this.exhibitor = exhibitor;
    }

    /**
     * Gets the exhibition area.
     *
     * @return the exhibition's area
     */
    public float getExhibitorArea() {
        return this.exhibitorArea;
    }

    /**
     * Gets the number of invitations.
     *
     * @return the number of invitations
     */
    @Override
    public int getNumberInvitations() {
        return this.numberInvitations;
    }

    /**
     * Gets the procuts list.
     *
     * @return the product's list
     */
    @Override
    public List<Product> getProductsList() {
        return this.productsList;
    }

    /**
     * Gets the evaluations list.
     *
     * @return the evaluations list
     */
    @Override
    public List<Evaluation> getEvaluationsList() {
        return this.evaluationsList;
    }

    /**
     * Sets the exhibition's area.
     *
     * @param exhibitorArea sets exhibitor area
     */
    public void setExhibitorArea(float exhibitorArea) {
        this.exhibitorArea = exhibitorArea;
    }

    /**
     * Sets the number of invitations.
     *
     * @param numberInvitations sets number of invitations
     */
    @Override
    public void setNumberInvitations(int numberInvitations) {
        this.numberInvitations = numberInvitations;
    }

    /**
     * Sets the decision.
     *
     * @param decision boolean indicating the decision
     */
    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    /**
     * Sets the products list.
     *
     * @param productsList products list
     */
    @Override
    public void setProductsList(List<Product> productsList) {
        this.productsList = new ArrayList(productsList);
    }

    /**
     * Sets the evaluations list.
     *
     * @param evaluationsList evaluations list
     */
    public void setEvaluationsList(List<Evaluation> evaluationsList) {
        this.evaluationsList = new ArrayList<>(evaluationsList);
    }

    /**
     * Returns a new evaluation.
     *
     * @return new evaluation
     */
    @Override
    public Evaluation newEvaluation() {
        return new Evaluation();
    }

    /**
     * Validate if a evaluation is valid.
     *
     * @param evaluation evaluation to be validated
     * @return true if it is valid, false otherwise
     */
    @Override
    public boolean validateEvaluation(Evaluation evaluation) {
        return !this.evaluationsList.contains(evaluation) && evaluation.validate();
    }

    /**
     * Register an evaluation.
     *
     * @param evaluation evaluation to be registered
     * @return true if it is registered with success, false otherwise
     */
    @Override
    public boolean registerEvaluation(Evaluation evaluation) {
        return this.evaluationsList.add(evaluation);
    }

    /**
     * Gets the application current state.
     *
     * @return application current state
     */
    @Override
    public ApplicationState getCurrentState() {
        return currentState;
    }

    /**
     * Sets the application current state.
     *
     * @param newState new stateto be setted
     */
    @Override
    public void setState(ApplicationState newState) {
        this.currentState = newState;
    }

    /**
     * Verify if this demonstation is valid.
     *
     * @return true if it is valid, false otherwise
     */
    @Override
    public boolean validate() {
        //TODO review this
        return this.title != null && this.exhibitor != null
                && this.currentState != null && this.evaluationsList != null
                && this.exhibitorArea > 0 && this.numberInvitations > 0
                && this.productsList != null;
    }

    /**
     * Set the current state of the demonstration application to
     * inEvaluationState
     */
    @Override
    public void setInEvaluation() {
        this.currentState.setInEvaluation();
    }

    /**
     * Gets the demonstation application title.
     *
     * @return title of the demonstration application
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public float getArea() {
        return this.exhibitorArea;
    }

    /**
     * Sets exhibition application state to removed.
     */
    @Override
    public boolean setInRemoved() {
        return this.currentState.setRemoved();
    }

    /**
     * Returns true if removable is in state removed, false otherwise.
     *
     * @return true if removable is in state removed, false otherwise
     */
    @Override
    public boolean isRemoved() {
        return this.currentState.isRemoved();
    }

    /**
     * Gets the exhibitor responsible of the exhibitor of this demonstration
     * application
     *
     * @return the exhibitor responsible of the exhibitor of this demonstration
     * application
     */
    @Override
    public ExhibitorResponsible getExhibitorResponsible() {
        return this.exhibitor.getExhibitorResponsible();
    }

    @Override
    public boolean isExhibitorResponsible(ExhibitorResponsible exhibitorResponsible) {
        return this.exhibitor.getExhibitorResponsible().equals(exhibitorResponsible);
    }

    @Override
    public boolean isInSubmission() {
        return this.currentState.isInSubmission();
    }

    @Override
    public Editable cloneToEditable() {
        return new DemonstrationApplication(this);
    }

    @Override
    public boolean isInDecision() {
        return this.currentState.isInDecision();
    }

    @Override
    public void newDecision() {
        this.decision = new Decision();

    }

    @Override
    public void setDecision(boolean decision, String justificativeText) {
        this.decision.setJustificativeText(justificativeText);
        this.decision.setDecision(decision);
    }

    @Override
    public boolean validateDecision() {
        return this.decision.validate() && validate();
    }

    @Override
    public boolean isDecisionAccepted() {
        return this.decision.isDecisionTrue();
    }

    @Override
    public boolean isDesionDeclined() {
        return !this.decision.isDecisionTrue();
    }

    @Override
    public boolean setDecided() {
        return this.decision.isDecisionTrue() ? this.currentState.setAccepted() : this.currentState.setDeclined();
    }

    @Override
    public boolean isDecided() {
        return this.currentState.isAccepted() || this.currentState.isDeclined();
    }

    @Override
    public String getKeywordsCSV() {
        StringBuilder s = new StringBuilder();

        boolean isFirst = true;

        for (Keyword keyword : this.keywordsList) {
            if (isFirst) {
                s.append(keyword.getDescription());
                isFirst = false;
            } else {
                s.append("," + keyword.getDescription());
            }
        }

        return s.toString();
    }

    /**
     * Returns the textual interpretation of the objects and attributes of this
     * class
     *
     * @return textual representation of this application
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("DemonstrationApplication{");
        s.append(String.format("%f%n", this.title));
        s.append(String.format("%f%n", this.exhibitor));
        s.append(String.format("%f%n", this.exhibitorArea));
        s.append(String.format("%d%n", this.numberInvitations));
        for (Product prod : productsList) {
            s.append(String.format("%s%n", prod));
        }
        for (Evaluation appl : evaluationsList) {
            s.append(String.format("%s%n", appl));
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it repreents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }
        DemonstrationApplication otherDemonstrationApplication = (DemonstrationApplication) otherObject;

        return this.title.equals(otherDemonstrationApplication.title) && this.exhibitor.equals(otherDemonstrationApplication.exhibitor)
                && this.evaluationsList.equals(otherDemonstrationApplication.evaluationsList)
                && this.productsList.equals(otherDemonstrationApplication.productsList) && this.exhibitorArea == otherDemonstrationApplication.exhibitorArea
                && this.numberInvitations == otherDemonstrationApplication.numberInvitations;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.title);
        hash = 11 * hash + Objects.hashCode(this.exhibitor);
        hash = 11 * hash + Float.floatToIntBits(this.exhibitorArea);
        hash = 11 * hash + this.numberInvitations;
        hash = 11 * hash + Objects.hashCode(this.productsList);
        hash = 11 * hash + Objects.hashCode(this.evaluationsList);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return this.getTitle();
    }

       
}
