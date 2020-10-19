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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an evaluation.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Evaluation implements Selectable, Serializable {

    /**
     * The question's list.
     */
    @XmlElementWrapper(name = "questions_list")
    @XmlElement(name = "question")
    private final List<String> questionsList;

    /**
     * The answer's list.
     */
    @XmlElementWrapper(name = "answers_list")
    @XmlElement(name = "answer")
    private List<Integer> answersList;

    /**
     * The evaluation´s attribution.
     */
    private StaffMember staffMember;

    /**
     * The number of existing answers.
     */
    private static final int NUMBER_OF_ANSWERS = 5;

    /**
     * The first question.
     */
    private static final String FIRST_QUESTION = "What is your knowledge about the exhibition theme?";

    /**
     * The second question.
     */
    private static final String SECOND_QUESTION = "What is the application adequation to the exhibition?";

    /**
     * The third question.
     */
    private static final String THIRD_QUESTION = "What is the application adequation to the demonstrations?";

    /**
     * The fourth question.
     */
    private static final String FOURTH_QUESTION = "What is the invite number adequation to the application?";

    /**
     * The fifth question.
     */
    private static final String FIFTH_QUESTION = "What is global recommendation?";

    /**
     * Constructs a evaluation with its default values.
     */
    public Evaluation() {
        this.staffMember = new StaffMember();
        this.questionsList = createQuestions();
        this.answersList = new ArrayList<>();
    }

    /**
     * Constructs a evaluation receiving a decision, a justification and a staff
     * attribution.
     *
     * @param answersList the answers for the questions
     * @param staffMember staff attribution
     */
    public Evaluation(List<Integer> answersList, StaffMember staffMember) {
        this.staffMember = staffMember;
        this.questionsList = createQuestions();
        this.answersList = new ArrayList<>(answersList);
    }

    /**
     * Creates a evaluation receiving another evaluation.
     *
     * @param evaluation another evaluation
     */
    public Evaluation(Evaluation evaluation) {
        this.staffMember = evaluation.staffMember;
        this.questionsList = new ArrayList<>(evaluation.questionsList);
        this.answersList = new ArrayList<>(evaluation.answersList);
    }

    /**
     * Gets the staff member.
     *
     * @return staff member
     */
    public StaffMember getStaffMember() {
        return staffMember;
    }

    /**
     * Sets the staff member.
     *
     * @param staffMember staff member
     */
    public void setStaffMember(StaffMember staffMember) {
        this.staffMember = staffMember;
    }

    /**
     * Gets the anwer's list.
     *
     * @return answer's list
     */
    public List<Integer> getAnswersList() {
        return new ArrayList<>(answersList);
    }

    /**
     * Sets the answer's list.
     *
     * @param answersList answer's list
     */
    public void setAnswersList(List<Integer> answersList) {
        this.answersList = new ArrayList<>(answersList);
    }

    /**
     * Gets the question's list.
     *
     * @return question's list
     */
    public List<String> getQuestionsList() {
        return new ArrayList<>(questionsList);
    }

    /**
     * Validate if the evaluation is valid.
     *
     * @return true if it is valid, false otherwise
     */
    public boolean validate() {
        return this.staffMember != null && this.answersList.size() == NUMBER_OF_ANSWERS && this.questionsList.size() == NUMBER_OF_ANSWERS;
    }

    /**
     * Creates the question list.
     *
     * @return question list
     */
    private List<String> createQuestions() {
        List<String> questionsListCreator = new ArrayList<>();
        questionsListCreator.add(FIRST_QUESTION);
        questionsListCreator.add(SECOND_QUESTION);
        questionsListCreator.add(THIRD_QUESTION);
        questionsListCreator.add(FOURTH_QUESTION);
        questionsListCreator.add(FIFTH_QUESTION);
        return questionsListCreator;
    }

    /**
     * Returns a textual representation of the evaluation.
     *
     * @return textual representation of the evaluation
     */
    @Override
    public String toString() {
        return String.format("Evaluation{questionsList=%s;answersList=%s;staffAttribution=%s}", this.questionsList, this.answersList, this.staffMember);
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
        Evaluation otherEvaluation = (Evaluation) otherObject;

        return this.questionsList.equals(otherEvaluation.questionsList) && this.answersList.equals(otherEvaluation.answersList)
                && this.staffMember.equals(otherEvaluation.staffMember);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.questionsList);
        hash = 89 * hash + Objects.hashCode(this.answersList);
        hash = 89 * hash + Objects.hashCode(this.staffMember);
        return hash;
    }

    @Override
    public String getDisplayInfo() {
        return "Evaluation";
    }

}
