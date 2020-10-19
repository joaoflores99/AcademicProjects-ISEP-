/**
 * Package location for UI concepts.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import lapr.project.controller.AssignStandsController;
import lapr.project.model.Application;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.Stand;
import lapr.project.model.Submittable;
import lapr.project.model.application.ApplicationAcceptedState;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Represents a stand assignment.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class AssignStandsUI extends JFrame {

    /**
     * The controller of exhibition importing mechanism.
     */
    private AssignStandsController assignStandsController;
    /**
     * The exhibition selected.
     */
    private Exhibition selectedExhibition;

    /**
     * The list of applications.
     */
    private List<ExhibitionApplication> applicationsList;

    /**
     * The list of stands.
     */
    private List<Stand> standsList;
    /**
     * Label size.
     */
    final Dimension LBL_SIZE = new Dimension(94, 16);
    /**
     * Window size.
     */
    final Dimension WINDOW_SIZE = new Dimension(800, 450);
    /**
     * Field margins.
     */
    final int MARGIN_S_FIELD = 0, MARGIN_I_FIELD = 0,
            MARGIN_E_FIELD = 10, MARGIN_D_FIELD = 0;
    /**
     * Field width.
     */
    final int FIELD_TXT_WIDTH = 20, FIELD_NUM_HEIGHT = 6;
    /**
     * Empty border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);
    /**
     * Scroll size.
     */
    final Dimension SCROLL_SIZE = new Dimension(300, 500);

    /**
     * The list of applications.
     */
    private JList listApplications;

    /**
     * The list of stands.
     */
    private JList listStands;

    /**
     * The list model of selectables.
     */
    ModelListSelectable modelSelectable;

    /**
     * The list model of selectables.
     */
    ModelListSelectable modelSelectableStands;
    /**
     * The exhibition center.
     */
    private ExhibitionCenter exhibitionCenter;
    /**
     * The organizer.
     */
    private Organizer organizer;

    /**
     * Builds instance of this class receiving organizer and exhibition center
     * as parameters.
     *
     * @param organizer the organizer
     * @param exhibitionCenter the exhibition center
     */
    public AssignStandsUI(Organizer organizer, ExhibitionCenter exhibitionCenter) {
        this.exhibitionCenter=exhibitionCenter;
        this.organizer=organizer;
        this.assignStandsController = new AssignStandsController(this.organizer, this.exhibitionCenter);
        List<Submittable> submittableList = new ArrayList(assignStandsController.getExhibitionsListByOrganizerInApplicationsDecidedState(organizer));
        setTitle("Assign Stands");
        DialogSelectable dialogSelectable = new DialogSelectable(this, submittableList);
        this.selectedExhibition = (Exhibition) dialogSelectable.getSelectedItem();
        if (this.selectedExhibition != null) {
            this.applicationsList = assignStandsController.getApplicationsList(selectedExhibition);
            this.standsList = assignStandsController.getStandsList();
            createComponents();
            CustomMenuBar customMenuBar = new CustomMenuBar(exhibitionCenter, this);
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
     * Creates the jframe components.
     */
    public void createComponents() {
        JPanel panel = createPanelLists();
        this.setLayout(new BorderLayout(0, 2));
        add(panel, BorderLayout.CENTER);
        JPanel panelButton = createButtonPanel();
        add(panelButton, BorderLayout.SOUTH);
    }

    /**
     * Creates the jPanel's lists.
     *
     * @return the jPanel that has the lists
     */
    public JPanel createPanelLists() {

        JPanel panel = new JPanel(new FlowLayout());
        JPanel panelApplications = createJPanelApplications();
        JPanel panelStands = createJPanelStands();
        panel.add(panelApplications);
        panel.add(panelStands);
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
     * Creates the Jpanel of stands.
     *
     * @return The jpanel of stands
     */
    public JPanel createJPanelStands() {
        listStands = createJListStands();
        JPanel panel = new JPanel(new BorderLayout(0, 3));
        panel.add(listStands, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Stands list:", TitledBorder.LEFT, TitledBorder.TOP));
        JScrollPane scrollPane = new JScrollPane(listStands);
        scrollPane.setBorder(PADDING_BORDER);
        panel.setMinimumSize(SCROLL_SIZE);
        panel.add(scrollPane);
        return panel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return the buttons panel
     */
    public JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton button = createConfirmJButton();
        JButton buttonBack =createBackButton();
        panel.add(button);
        panel.add(buttonBack);
        return panel;
    }

    /**
     * Createsthe back button.
     * @return the button
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
     * Creates the JList of applications.
     *
     * @return the jlist of applications
     */
    public JList createJListApplications() {
        JList list = new JList();
        list.setPreferredSize(new Dimension(100, 370));
        modelSelectable = new ModelListSelectable(applicationsList);
        list.setModel(modelSelectable);
        return list;
    }

    /**
     * Creates the jlist of stands.
     *
     * @return the jlist of stands
     */
    public JList createJListStands() {
        JList list = new JList();
        list.setPreferredSize(new Dimension(200, 370));
        modelSelectableStands = new ModelListSelectable(standsList);
        list.setModel(modelSelectableStands);
        return list;
    }

    /**
     * Creates the confirm button.
     *
     * @return the confirm button
     */
    public JButton createConfirmJButton() {
        JButton button = new JButton("Pair selected application and stand");
        button.setPreferredSize(new Dimension(250, 40));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(rootPane, "Do you wish to assign the selected stand to the selected application?", "Stand assignment", JOptionPane.YES_NO_OPTION);
                if (listApplications.getSelectedValue() != null && listStands.getSelectedValue() != null) {
                    if (result == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(rootPane, assignStandsController.setStand((ExhibitionApplication) modelSelectable.getObject(listApplications.getSelectedIndex()), (Stand) modelSelectableStands.getObject(listStands.getSelectedIndex())) ? "Operation performed sucessfully!" : "Error performing the desired operation", "Stand assignment", JOptionPane.PLAIN_MESSAGE);
                        applicationsList = assignStandsController.getApplicationsList(selectedExhibition);
                        modelSelectable = new ModelListSelectable(applicationsList);
                        listApplications.setModel(modelSelectable);
                        standsList.remove(listStands.getSelectedIndex());
                        modelSelectableStands = new ModelListSelectable(standsList);
                        listStands.setModel(modelSelectableStands);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please select one item from each list.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return button;
    }

    public static void main(String[] args) {
        ExhibitionCenter ex = DefaultInstantiator.createExhibitionCenter();
        Application application = ex.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        application.setState(new ApplicationAcceptedState(application));
        ex.getExhibitionsRegister().getExhibitionsList().get(0).setState(new ExhibitionDecidedApplicationsState(ex.getExhibitionsRegister().getExhibitionsList().get(0)));
        Organizer org = ex.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0);
        AssignStandsUI assign = new AssignStandsUI(org, ex);
    }
}
