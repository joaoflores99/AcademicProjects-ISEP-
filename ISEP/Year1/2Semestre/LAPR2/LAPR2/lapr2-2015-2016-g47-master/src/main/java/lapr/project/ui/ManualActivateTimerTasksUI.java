/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.Submittable;
import lapr.project.model.exhibition.ExhibitionCompleteState;
import lapr.project.model.timers.ChangeToApplicationsInDecision;
import lapr.project.model.timers.ChangeToChangedConflicts;
import lapr.project.model.timers.ChangeToClosedApplications;
import lapr.project.model.timers.ChangeToOpenApplications;
import lapr.project.model.timers.DetectConflictsTask;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to manual activate timer tasks.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ManualActivateTimerTasksUI extends JFrame {

    private ExhibitionsManager exhibitionsManager;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The submttables list.
     */
    private final List<Submittable> submittablesList;

    /**
     * The submittables JList.
     */
    private JList<Submittable> submittablesJList;

    /**
     * The editable demonstrations JList.
     */
    private JList tasksJList;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Manual activate timer task";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     *
     *
     * @param exhibitionCenter the exhibition center
     * @param exhibitionsManager the exhibitons manager logged in
     */
    public ManualActivateTimerTasksUI(ExhibitionCenter exhibitionCenter, ExhibitionsManager exhibitionsManager) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager = exhibitionsManager;

        submittablesList = exhibitionCenter.getExhibitionsRegister().getAllSubmittables();

        CustomMenuBar customMenuBar = new CustomMenuBar(this.exhibitionCenter, this);
        setJMenuBar(customMenuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customMenuBar.exit();
            }
        });

        setLayout(new BorderLayout(10, 10));

        createComponents();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setSize(WINDOW_DIMEMNSION);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Create the UI components.
     */
    private void createComponents() {

        JPanel panelLeft = new JPanel(new BorderLayout());
        this.submittablesJList = new JList(new ModelListSelectable(submittablesList));
        panelLeft.add(this.submittablesJList, BorderLayout.CENTER);
        panelLeft.setBorder(PADDING_BORDER);

        String[] tasks = {"To Open Applications", "To Closed Applications", "To Detect Conflicts", "To Changed Conflicts", "To Applications In Decision"};
        JPanel panelRight = new JPanel(new BorderLayout());
        this.tasksJList = new JList(tasks);
        this.tasksJList.setBorder(PADDING_BORDER);
        panelRight.add(this.tasksJList, BorderLayout.CENTER);
        panelRight.setBorder(PADDING_BORDER);

        add(panelLeft, BorderLayout.WEST);
        add(panelRight, BorderLayout.EAST);
        add(createConfirmButtons(), BorderLayout.SOUTH);
    }

    /**
     * Create panel with confirm & cancel buttons.
     *
     * @return panel with confirm & cancel buttons
     */
    private JPanel createConfirmButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(createConfirmButton());
        panel.add(createCancelButton());

        return panel;
    }

    /**
     * Create Confirm Button.
     *
     * @return Confirm Button
     */
    private JButton createConfirmButton() {

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = submittablesJList.getSelectedIndex();
                Submittable submittable = submittablesList.get(index);

                int index2 = tasksJList.getSelectedIndex();
                String selectedTask = (String) tasksJList.getModel().getElementAt(index2);

                switch (selectedTask) {
                    case "To Open Applications":

                        new ChangeToOpenApplications(submittable).run();

                        break;
                    case "To Closed Applications":

                        new ChangeToClosedApplications(submittable).run();

                        break;
                    case "To Detect Conflicts":

                        new DetectConflictsTask(submittable, exhibitionCenter).run();

                        break;
                    case "To Changed Conflicts":

                        new ChangeToChangedConflicts(submittable).run();

                        break;
                    case "To Applications In Decision":

                        new ChangeToApplicationsInDecision(submittable).run();

                        break;
                }

                dispose();
                new DashboardUI(ManualActivateTimerTasksUI.this.exhibitionCenter, ManualActivateTimerTasksUI.this.exhibitionsManager);

            }
        });

        this.rootPane.setDefaultButton(confirmBtn);

        return confirmBtn;
    }

    /**
     * Create Cancel Button.
     *
     * @return o botão cancelar
     */
    private JButton createCancelButton() {

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardUI(ManualActivateTimerTasksUI.this.exhibitionCenter, ManualActivateTimerTasksUI.this.exhibitionsManager);
            }
        });
        return cancelBtn;
    }

    /**
     * Starting method for testing purposes.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        ExhibitionCenter ec = DefaultInstantiator.createExhibitionCenter();

        ec.getExhibitionsRegister().getExhibitionsList()
                .get(0).setState(new ExhibitionCompleteState(
                                ec.getExhibitionsRegister().getExhibitionsList().get(0)));

        new ManualActivateTimerTasksUI(ec, new ExhibitionsManager());
    }

}
