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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.ShowRemovedApplicationsController;
import lapr.project.model.Application;
import lapr.project.model.Evaluable;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.application.ApplicationRemovedState;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSeeApplication;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphical interface to show removed applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ShowRemovedApplicationsUI extends JFrame {

    /**
     * The show removed applications controller.
     */
    private final ShowRemovedApplicationsController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The organizer.
     */
    private final Organizer organizer;

    /**
     * The removed applications list.
     */
    private final List<Application> removedApplicationsList;

    /**
     * The removedApplicationsJList JList.
     */
    private JList removedApplicationsJList;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Removed Applications";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of show removed applications user interface.
     *
     * @param exhibitionCenter the exhibition center
     * @param organizer the organizer
     */
    public ShowRemovedApplicationsUI(ExhibitionCenter exhibitionCenter, Organizer organizer) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.organizer = organizer;
        this.controller = new ShowRemovedApplicationsController(this.exhibitionCenter, this.organizer);
        this.removedApplicationsList = this.controller.getRemovedApplicationsListByOrganizer(organizer);

        CustomMenuBar customMenuBar = new CustomMenuBar(this.exhibitionCenter, this);
        setJMenuBar(customMenuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customMenuBar.exit();
            }
        });
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        createComponents();

        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setSize(WINDOW_DIMEMNSION);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));
        componentsPanel.add(createTitleLabel(), BorderLayout.NORTH);
        componentsPanel.add(createRemovedApplicationsPanel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the title label.
     *
     * @return title label
     */
    private JLabel createTitleLabel() {
        return new JLabel("The removed applications", SwingConstants.CENTER);
    }

    /**
     * Creates the removed applications panel.
     *
     * @return the removed applications panel
     */
    private JPanel createRemovedApplicationsPanel() {
        JPanel removedApplicationsListPanel = new JPanel(new BorderLayout());

        this.removedApplicationsJList = new JList();
        this.removedApplicationsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.removedApplicationsJList.setModel(new ModelListSelectable(this.removedApplicationsList));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.removedApplicationsJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.removedApplicationsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        JScrollPane removedApplicationsJScrollPane = new JScrollPane(this.removedApplicationsJList);
        removedApplicationsListPanel.add(removedApplicationsJScrollPane, BorderLayout.CENTER);

        return removedApplicationsListPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createSeeApplicationButton());
        buttonsPanel.add(createBackButton());

        return buttonsPanel;
    }

    /**
     * Creates the see application button.
     *
     * @return the see application button
     */
    public JButton createSeeApplicationButton() {
        JButton button = new JButton("See application info");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Application application = ShowRemovedApplicationsUI.this.removedApplicationsList
                            .get(ShowRemovedApplicationsUI.this.removedApplicationsJList.getSelectedIndex());
                    DialogSeeApplication dialogSeeApplication = new DialogSeeApplication((Evaluable) application, ShowRemovedApplicationsUI.this);
                } catch (NullPointerException exception) {
                    JOptionPane.showMessageDialog(rootPane, "Something wen´t wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return button;
    }

    /**
     * Creates the back button.
     *
     * @return back button
     */
    private JButton createBackButton() {
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardUI(exhibitionCenter, organizer);
            }
        });

        return backButton;
    }

    //Method to test UI
    public static void main(String[] args) {

        Organizer organizer;
        ExhibitionCenter ec;
        List<Application> applicationsList = new ArrayList();
        Application application;

        ec = DefaultInstantiator.createExhibitionCenter();
        organizer = ec.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(0);

        application = ec.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        ec.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0).setState(new ApplicationRemovedState(application));

        new ShowRemovedApplicationsUI(ec, organizer);

    }

}
