/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.Evaluable;
import lapr.project.model.Evaluation;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Record;
import lapr.project.model.StaffAttribution;
import lapr.project.model.StaffAttributionsList;
import lapr.project.model.StaffMember;
import lapr.project.model.Submittable;
import lapr.project.utils.Calculator;

/**
 * Represents the controller to evaluate applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class EvaluateApplicationsController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The selected submittable.
     */
    private Submittable submittable;

    /**
     * The staff attribution.
     */
    private StaffAttribution staffAttribution;

    /**
     * The evaluable.
     */
    private Evaluable evaluable;

    /**
     * The staff member evaluation.
     */
    private Evaluation evaluation;

    /**
     * Constructs a evaluate applications controller.
     *
     * @param exhibitionCenter Exhibition Center
     */
    public EvaluateApplicationsController(ExhibitionCenter exhibitionCenter) {

        this.exhibitionCenter = exhibitionCenter;
    }

    /**
     * Gets the submittables filtering by a staff member.
     *
     * @param staffMember the staff member to be filtered
     * @return the staff member's submittables
     */
    public List<Submittable> getSubmittablesByStaff(StaffMember staffMember) {
        ExhibitionsRegister exhibitionsRegister = exhibitionCenter.getExhibitionsRegister();
        return exhibitionsRegister.getSubmittablesByStaff(staffMember);
    }

    /**
     * Sets the submittable.
     *
     * @param submittable submittable to be setted
     */
    public void setSubmittable(Submittable submittable) {
        this.submittable = submittable;
    }

    /**
     * Gets the attributions by a staff
     *
     * @param staffMember staff member to be filtered
     * @return list of attributions
     */
    public List<StaffAttribution> getAttributionsByStaff(StaffMember staffMember) {
        StaffAttributionsList staffAttributionsList = this.submittable.getStaffAttributionsList();
        return staffAttributionsList.getStaffAtributionsApplicationsInEvaluationByStaff(staffMember);
    }

    /**
     * Gets the evaluable from an attribution
     *
     * @param staffAttribution attribution
     * @return evaluable
     */
    public Evaluable getEvaluableByAttribution(StaffAttribution staffAttribution) {
        this.staffAttribution = staffAttribution;
        this.evaluable = (Evaluable) this.staffAttribution.getApplication();
        return this.evaluable;
    }

    /**
     * Creates the new evaluation and returns the questions list.
     *
     * @return questions list
     */
    public List<String> newEvaluation() {
        this.evaluation = evaluable.newEvaluation();
        return this.evaluation.getQuestionsList();
    }

    /**
     * Sets the evaluation
     *
     * @param answersList the answers to the questions
     * @return true if the evaluation is valida, false otherwise
     */
    public boolean setEvaluation(List<Integer> answersList) {
        this.evaluation.setStaffMember(this.staffAttribution.getStaffMember());
        this.evaluation.setAnswersList(answersList);
        return this.evaluation.validate();
    }

    /**
     * Register the evaluation.
     *
     * @return true if the registration is successfull, false otherwise
     */
    public boolean registerEvaluation() {
        return this.evaluable.registerEvaluation(evaluation);
    }

    /**
     * Adds the evaluation to the recod.
     */
    public void addToRecord() {
        Record record = this.exhibitionCenter.getRecord();
        float average = Calculator.calculateListAverage(this.evaluation.getAnswersList());
        StaffMember staffMember = this.staffAttribution.getStaffMember();
        Application application = (Application) this.staffAttribution.getApplication();
        record.addEvaluation(average, staffMember, application);
    }

    /**
     * Removes the staff attribution from list after the evaluation is done.
     *
     * @return true if it is successful removed
     */
    public boolean removeStaffAttributions() {
        return this.submittable.removeAttribution(this.staffAttribution);
    }

}
