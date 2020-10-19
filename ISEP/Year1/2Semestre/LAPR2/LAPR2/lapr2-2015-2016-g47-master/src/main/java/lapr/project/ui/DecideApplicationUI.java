/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import lapr.project.controller.DecideApplicationController;
import lapr.project.model.Application;
import lapr.project.model.Decisable;
import lapr.project.model.Evaluation;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.application.ApplicationInDecisionState;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
import static lapr.project.ui.AssignStandsUI.PADDING_BORDER;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to decide applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DecideApplicationUI extends JFrame {

    /**
     * The model list for selectable applications.
     */
    private ModelListSelectable modelSelectableApplications;

    /**
     * The model list for selectable evaluations.
     */
    private ModelListSelectable modelSelectableEvaluations;

    /**
     * The list of applications.
     */
    private List<Decisable> applicationsList;

    /**
     * The jList of applications.
     */
    private JList listApplications;
    /**
     * The Jlist of evaluations.
     */
    private JList listEvaluation;

    /**
     * Scroll size.
     */
    final Dimension SCROLL_SIZE = new Dimension(300, 500);
    /**
     * The window size.
     */
    final Dimension WINDOW_SIZE = new Dimension(500, 500);

    /**
     * This instances controller.
     */
    private DecideApplicationController decideApplicationController;
    /**
     * The window title.
     */
    private static final String WINDOW_TITLE = "Decide application";
    /**
     * The list of evaluations.
     */
    private List<Evaluation> evaluationsList;
    /**
     * The exhibition center.
     */
    private ExhibitionCenter exhibitionCenter;
    /**
     * The organizer.
     */
    private Organizer organizer;

    /**
     * Creates an instance of this class receiving an organizer and exhibition
     * center as parameters.
     *
     * @param organizer the organizer
     * @param exhibitionCenter the exhibition center
     */
    public DecideApplicationUI(Organizer organizer, ExhibitionCenter exhibitionCenter) {
        super(WINDOW_TITLE);
        this.exhibitionCenter=exhibitionCenter;
        this.organizer=organizer;
        this.decideApplicationController = new DecideApplicationController(this.organizer,this. exhibitionCenter);
        this.applicationsList = decideApplicationController.getDecisablesByOrganizer();
        DialogSelectable dialogSelectable = new DialogSelectable(this, applicationsList);
        if (dialogSelectable.getSelectedItem() != null) {
            this.decideApplicationController.setDecisable(((Decisable) dialogSelectable.getSelectedItem()));
            evaluationsList = decideApplicationController.getEvaluationsList();
            createComponents();
            CustomMenuBar customMenuBar = new CustomMenuBar(this.exhibitionCenter, this);
            setJMenuBar(customMenuBar);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    customMenuBar.exit();
                }
            });

            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            pack();
            setSize(WINDOW_SIZE);
            setMinimumSize(new Dimension(getWidth(), getHeight()));
            setLocationRelativeTo(null);
            setVisible(true);
        }
        else {
            dispose();
            new DashboardUI(this.exhibitionCenter, this.organizer);
        }
    }

    /**
     * Creates the jpanel of evaluations.
     *
     * @return the jpanel of evaluations
     */
    public JPanel createJPanelEvaluations() {
        listEvaluation = createJListEvaluations();
        JPanel panel = new JPanel(new BorderLayout(0, 3));
        panel.add(listEvaluation, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Evaluations list:", TitledBorder.LEFT, TitledBorder.TOP));
        JScrollPane scrollPane = new JScrollPane(listEvaluation);
        scrollPane.setBorder(PADDING_BORDER);
        panel.setMinimumSize(SCROLL_SIZE);
        panel.add(scrollPane);
        return panel;
    }

    /**
     * Creates the jpanel of applications.
     *
     * @return the jpanel of applications
     */
    public JPanel createJPanelApplications() {
        listApplications = createJListApplications();
        JPanel panel = new JPanel(new BorderLayout(0, 3));
        panel.add(listApplications, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Applications List:", TitledBorder.LEFT, TitledBorder.TOP));
        JScrollPane scrollPane = new JScrollPane(listApplications);
        scrollPane.setBorder(PADDING_BORDER);
        panel.setMinimumSize(SCROLL_SIZE);
        panel.add(scrollPane);
        return panel;
    }

    /**
     * Creates the jframe components.
     */
    public void createComponents() {
        JPanel panel = createPanelLists();
        JPanel panelButtons = createButtonsPanel();
        this.setLayout(new BorderLayout(0, 2));
        add(panel, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    /**
     * Creates the JList of applications.
     *
     * @return the jlist of applications
     */
    public JList createJListApplications() {
        JList list = new JList();
        list.setPreferredSize(new Dimension(100, 370));
        modelSelectableApplications = new ModelListSelectable(applicationsList);
        list.setModel(modelSelectableApplications);
        return list;
    }

    /**
     * Creates the JList of evaluations.
     *
     * @return the jList of evaluations
     */
    public JList createJListEvaluations() {
        JList list = new JList();
        list.setPreferredSize(new Dimension(100, 370));
        modelSelectableEvaluations = new ModelListSelectable(evaluationsList);
        list.setModel(modelSelectableEvaluations);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String questionsPlusAnswers = "";
                List<String> questionsList = ((Evaluation) modelSelectableEvaluations.getObject(listEvaluation.getSelectedIndex())).getQuestionsList();
                List<Integer> answersList = ((Evaluation) modelSelectableEvaluations.getObject(listEvaluation.getSelectedIndex())).getAnswersList();
                for (int i = 0; i < questionsList.size(); i++) {
                    questionsPlusAnswers += "Q: " + questionsList.get(i) + " A: " + answersList.get(i) + "\n";
                }
                JOptionPane.showMessageDialog(rootPane, questionsPlusAnswers, "List of questions and answers", JOptionPane.PLAIN_MESSAGE);
            }
        });
        return list;
    }

    /**
     * Creates the jPanel's lists.
     *
     * @return the jPanel that has the lists
     */
    public JPanel createPanelLists() {
        JPanel panel = new JPanel(new FlowLayout());
        JPanel panelApplications = createJPanelApplications();
        JPanel panelEvaluations = createJPanelEvaluations();

        panel.add(panelApplications);
        panel.add(panelEvaluations);
        return panel;
    }

    /**
     * Creates the panel that holds the buttons.
     *
     * @return the buttons panel
     */
    public JPanel createButtonsPanel() {
        JButton button = createInsertDecisionJButton();
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(button);
        panel.add(createBackButton());
        return panel;
    }
   /**
    * Creates the back button.
    * @return  the back button
    */
 public JButton createBackButton(){
        JButton button = new JButton("Back");
             button.setPreferredSize(new Dimension(150, 40));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardUI(exhibitionCenter, organizer);
            }
        });
        return button;
    }
    /**
     * Creates the insert decision button.
     *
     * @return the insert decision button
     */
    public JButton createInsertDecisionJButton() {
        JButton button = new JButton("Insert decision");
        button.setPreferredSize(new Dimension(150, 40));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean decision = false;
                String justificativeText;
                int result = JOptionPane.showConfirmDialog(rootPane, "Confirm this application?", "Decide application", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    decision = true;
                    justificativeText = JOptionPane.showInputDialog(rootPane, "Please insert a text to justify your decision.", "Insert justificative text", JOptionPane.QUESTION_MESSAGE);
                    decideApplicationController.setDecision(decision, justificativeText);
                    result = JOptionPane.showConfirmDialog(rootPane, "Decision was set! Do you wish to register this decision into the system?", "Register decision", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(rootPane, decideApplicationController.registerDecision() ? "The decision was registered sucessfully on the system!" : "Error while validating the decision", "Register decision", JOptionPane.PLAIN_MESSAGE);
                        applicationsList = decideApplicationController.getDecisablesByOrganizer();
                        modelSelectableApplications = new ModelListSelectable(applicationsList);
                        listApplications.setModel(modelSelectableApplications);
                    }
                }

            }
        });
        return button;
    }

    public static void main(String[] args) {
        ExhibitionCenter ex = DefaultInstantiator.createExhibitionCenter();
        Application application = ex.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        application.setState(new ApplicationInDecisionState(application));
        ex.getExhibitionsRegister().getExhibitionsList().get(0).setState(new ExhibitionDecidedApplicationsState(ex.getExhibitionsRegister().getExhibitionsList().get(0)));
        Organizer org = ex.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0);
        DecideApplicationUI assign = new DecideApplicationUI(org, ex);
    }
}
