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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import lapr.project.controller.ImportExhibitionController;
import lapr.project.model.Application;
import lapr.project.model.Conflict;
import lapr.project.model.Demonstration;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.Organizer;
import lapr.project.model.Selectable;
import lapr.project.model.StaffAttribution;
import lapr.project.model.StaffMember;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Represents an evaluation.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ImportExhibitionUI extends JFrame {

    /**
     * The controller of exhibition importing mechanism.
     */
    private ImportExhibitionController importExhibitionController;
    /**
     * The exhibition selected.
     */
    private Exhibition selectedExhibition;

    /**
     * The window title.
     */
    final static String WINDOW_TITLE = "Import exhibition";
    /**
     * Label size.
     */
    final Dimension LBL_SIZE = new Dimension(94, 16);
    /**
     * Window size.
     */
    final Dimension WINDOW_SIZE = new Dimension(1300, 600);
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
     * List of staff members.
     */
    private List<StaffMember> staffList;

    /**
     * List of organizers.
     */
    private List<Organizer> organizersList;

    /**
     * List of demonstrations.
     */
    private List<Demonstration> demonstrationsList;
    /**
     * List of applications.
     */
    private List<Application> applicationsList;
    /**
     * List of staff attributions.
     */
    private List<StaffAttribution> staffAttributionsList;
    /**
     * List of conflicts.
     */
    private List<Conflict> conflictsList;
    /**
     * JList of staff.
     */
    JList staffJList;
    /**
     * JList of organizers.
     */
    JList organizersJList;
    /**
     * JList of demonstrations.
     */
    JList demonstrationsJList;
    /**
     * JList of applicaitons.
     */
    JList applicationsJList;
    /**
     * JList of staff attributions.
     */
    JList staffAttributionJList;
    /**
     * JList of conflicts.
     */
    JList conflictsJList;
    /**
     * The exhibitions center.
     */
    ExhibitionCenter exhibitionCenter;
    /**
     * The exhibitions manager.
     */
    ExhibitionsManager exhibitionsManager;

    /**
     * Creates instance of this class.
     *
     * @param manager the exhibitions manager
     * @param exhibitionCenter the exhibitions center
     */
    public ImportExhibitionUI(ExhibitionsManager manager, ExhibitionCenter exhibitionCenter) {
        super(WINDOW_TITLE);
        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager=manager;
        this.importExhibitionController = new ImportExhibitionController(manager, this.exhibitionCenter);

        this.setSize(WINDOW_SIZE);
        this.staffList = new ArrayList();
        this.organizersList = new ArrayList();
        this.demonstrationsList = new ArrayList();
        this.applicationsList = new ArrayList();
        this.staffAttributionsList = new ArrayList();
        this.conflictsList = new ArrayList();

        createComponents();
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Creates the frame components.
     */
    public void createComponents() {
        JMenuBar menu = createJMenuBar();

        JPanel panel = createListsPanel();
        add(menu, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(createBackButton(),BorderLayout.SOUTH);
    }

    /**
     * Creates the JMenuBar.
     *
     * @return The jmenu bar
     */
    public JMenuBar createJMenuBar() {
        CustomMenuBar customMenuBar = new CustomMenuBar(exhibitionCenter, this);

        setJMenuBar(customMenuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customMenuBar.exit();
            }
        });
        JMenu menu = createJMenu();
        customMenuBar.add(menu);
        return customMenuBar;
    }

    /**
     * Creates the JMenu.
     *
     * @return the jmenu
     */
    public JMenu createJMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem itemMenu = createJMenuItemImport();
        menu.add(itemMenu);
        return menu;

    }

    /**
     * Creates the JMenu import.
     *
     * @return the jmenu import
     */
    public JMenuItem createJMenuItemImport() {
        JMenuItem itemMenu = new JMenuItem("Import exhibition from XML");
        itemMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(rootPane);
                if (result == JFileChooser.APPROVE_OPTION) {
                    if (importExhibitionController.readExhibitionFromFile(fileChooser.getSelectedFile().getAbsolutePath())) {
                        selectedExhibition = importExhibitionController.getExhibition();
                        updateLists(selectedExhibition);

                        result = JOptionPane.showConfirmDialog(rootPane, "Exhibition imported sucessfully. Do you wish to register it on the system?", "Sucess", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {

                            if (importExhibitionController.registerExhibition(selectedExhibition)) {
                                JOptionPane.showConfirmDialog(rootPane, "Exhibition registered sucessfully!", "Sucess", JOptionPane.PLAIN_MESSAGE);

                            }
                        }
                    }
                }
            }
        });
        return itemMenu;
    }

    /**
     * Creates the jlists of selectables
     *
     * @param selectableList list that implements Selectable
     * @param description the description of each list
     * @return the jList of selectables
     */
    public JList createJListSelectable(List<Selectable> selectableList, String description) {
        JList listSelectable = new JList();
        ModelListSelectable modelSelectable = new ModelListSelectable(selectableList);
        listSelectable.setModel(modelSelectable);
        listSelectable.setPreferredSize(new Dimension(200, 300));
        listSelectable.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                description, TitledBorder.LEFT, TitledBorder.TOP));

        return listSelectable;
    }

    /**
     * Creates panel for the jlists.
     *
     * @return the panel
     */
    public JPanel createListsPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        staffJList = createJListSelectable(new ArrayList<Selectable>(this.staffList), "Staff List:");
        organizersJList = createJListSelectable(new ArrayList<Selectable>(this.organizersList), "Organizers List:");
        applicationsJList = createJListSelectable(new ArrayList<Selectable>(this.applicationsList), "Applications List:");
        demonstrationsJList = createJListSelectable(new ArrayList<Selectable>(this.demonstrationsList), "Demonstrations List: ");
        staffAttributionJList = createJListSelectable(new ArrayList<Selectable>(this.staffAttributionsList), "Staff Attributions List: ");
        conflictsJList = createJListSelectable(new ArrayList<Selectable>(this.conflictsList), "Conflicts List: ");
        panel.add(staffJList);
        panel.add(organizersJList);
        panel.add(applicationsJList);
        panel.add(demonstrationsJList);
        panel.add(staffAttributionJList);
        panel.add(conflictsJList);
        return panel;
    }

    /**
     * Updates the lists.
     *
     * @param exhibition the exhibition whose data is going to be read
     */
    public void updateLists(Exhibition exhibition) {
        setTitle(exhibition.getTitle());
        staffList = importExhibitionController.getStaffList(exhibition);
        staffJList.setModel(new ModelListSelectable(staffList));
        organizersList = importExhibitionController.getOrganizersList(exhibition);
        organizersJList.setModel(new ModelListSelectable(organizersList));
        applicationsList = importExhibitionController.getApplicationsList(exhibition);
        applicationsJList.setModel(new ModelListSelectable(applicationsList));
        demonstrationsList = importExhibitionController.getDemonstrationsList(exhibition);
        demonstrationsJList.setModel(new ModelListSelectable(demonstrationsList));
        staffAttributionsList = importExhibitionController.getStaffAttributionsList(exhibition);
        staffAttributionJList.setModel(new ModelListSelectable(staffAttributionsList));
        conflictsList = importExhibitionController.getConflictsList(exhibition);
        conflictsJList.setModel(new ModelListSelectable(conflictsList));

    }
 
    public JButton createBackButton(){
        JButton button = new JButton("Back");
             button.setPreferredSize(new Dimension(100, 40));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardUI(exhibitionCenter, exhibitionsManager);
            }
        });
        return button;
    }
    public static void main(String[] args) {
        ExhibitionCenter exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        ExhibitionsManager manager = new ExhibitionsManager();
        ImportExhibitionUI test = new ImportExhibitionUI(manager, exhibitionCenter);
    }
}
