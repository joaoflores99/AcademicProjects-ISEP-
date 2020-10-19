/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lapr.project.ui.EvaluateApplicationUI;

/**
 * Dialog to evaluate an application info.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DialogEvaluateApplication extends JDialog {

    /**
     * The questions list.
     */
    private final List<String> questionsList;

    /**
     * The answersList list.
     */
    private List<Integer> answersList;

    /**
     * The answersList JList.
     */
    private List<JComboBox> answersListJComboBox;

    /**
     * The window title.
     */
    private static final String WINDOW_TITLE = "Evaluate Application";

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates a dialog to evaluate an evaluable.
     *
     * @param questionList questions list
     * @param parentFrame parent's frame
     */
    public DialogEvaluateApplication(List<String> questionList, EvaluateApplicationUI parentFrame) {
        super(parentFrame, WINDOW_TITLE, true);

        this.questionsList = questionList;

        createComponents();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Creates the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(createQuestionsAndAnswersLabel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsLabel(), BorderLayout.SOUTH);
        componentsPanel.setBorder(PADDING_BORDER);

        add(componentsPanel);
    }

    /**
     * Creates the questions and answersList panel.
     *
     * @return questions and answersList panel
     */
    private JPanel createQuestionsAndAnswersLabel() {

        JPanel questionsAndAnswersPanel = new JPanel(new GridLayout(questionsList.size(), 1));

        List<JLabel> questionsListJLabel = new ArrayList<>();
        this.answersListJComboBox = new ArrayList<>();
        Integer possibleAnswers[] = {0, 1, 2, 3, 4, 5};

        for (String question : this.questionsList) {
            questionsListJLabel.add(new JLabel(String.format("Q: %s", question)));
            this.answersListJComboBox.add(new JComboBox<>(possibleAnswers));
        }

        for (int i = 0; i < questionsListJLabel.size(); i++) {
            JPanel questionAnswersPanel = new JPanel();
            questionAnswersPanel.add(questionsListJLabel.get(i));
            questionAnswersPanel.add(this.answersListJComboBox.get(i));
            questionsAndAnswersPanel.add(questionAnswersPanel);

        }

        return questionsAndAnswersPanel;
    }

    /**
     * Creates the buttons label.
     *
     * @return buttons label
     */
    private JPanel createButtonsLabel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        buttonsPanel.add(createSubmitEvaluationButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the submit evaluation button.
     *
     * @return submit evaluation button
     */
    private JButton createSubmitEvaluationButton() {
        JButton submitEvaluationButton = new JButton("Submeter Avaliação");

        submitEvaluationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DialogEvaluateApplication.this.answersList = new ArrayList<>();
                for (JComboBox answersComboBox : DialogEvaluateApplication.this.answersListJComboBox) {
                    DialogEvaluateApplication.this.answersList.add((Integer) answersComboBox.getSelectedItem());
                }
                dispose();
            }
        });

        return submitEvaluationButton;
    }

    /**
     * Creates the cancel button.
     *
     * @return cancel button
     */
    private JButton createCancelButton() {
        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogEvaluateApplication.this.answersList = null;
                dispose();
            }
        });

        return cancelButton;
    }

    /**
     * Gets the answers list.
     *
     * @return answers list
     */
    public List<Integer> getAnswersList() {
        return this.answersList;
    }

}
