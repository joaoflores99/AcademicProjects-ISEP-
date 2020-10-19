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
import lapr.project.controller.UpdateDetectedConflictsController;
import lapr.project.model.Application;
import lapr.project.model.Conflict;
import lapr.project.model.ConflictType;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.StaffMember;
import lapr.project.model.Submittable;
import lapr.project.model.exhibition.ExhibitionDetectedConflictsState;
import lapr.project.model.mechanisms.detection.RelatedUserConflictMechanism;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogAddNewConflict;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to update detected conflicts.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UpdateDetectedConflictsUI extends JFrame {

    /**
     * The update detected conflicts controller.
     */
    private final UpdateDetectedConflictsController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The staff member.
     */
    private final StaffMember staffMember;

    /**
     * The submittables list.
     */
    private final List<Submittable> submittablesList;

    /**
     * The conflicts list.
     */
    private List<Conflict> conflictsList;

    /**
     * The applications list.
     */
    private List<Application> applicationsList;

    /**
     * The conflict types list.
     */
    private List<ConflictType> typeConflictsList;

    /**
     * The conflicts JList.
     */
    private JList conflictsJList;

    /**
     * The applications JList.
     */
    private JList applicationsJList;

    /**
     * The remove conflicts button.
     */
    private JButton removeConflictsButton;

    /**
     * The dialog to add new conflict.
     */
    private DialogAddNewConflict dialogAddNewConflict;

    /**
     * The selected submittable.
     */
    private final Submittable selectedSubmittable;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Update detected conflicts";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of define conflicts types user interface.
     *
     * @param exhibitionCenter the exhibition center
     * @param staffMember the staff member
     */
    public UpdateDetectedConflictsUI(ExhibitionCenter exhibitionCenter, StaffMember staffMember) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.staffMember = staffMember;
        this.controller = new UpdateDetectedConflictsController(this.exhibitionCenter, this.staffMember);

        this.submittablesList = this.controller.getSubmittableListByStaffMember();

        final String chooseSubmittableText = "Which submittable do you wish to update the detected conflits?";
        DialogSelectable dialogSelectable = new DialogSelectable(this, this.submittablesList, chooseSubmittableText);
        this.selectedSubmittable = (Submittable) dialogSelectable.getSelectedItem();

        if (selectedSubmittable != null) {

            this.controller.setSubmittable(selectedSubmittable);
            this.conflictsList = selectedSubmittable.getConflictListByStaffMember(staffMember);

            this.applicationsList = selectedSubmittable.getApplicationsList().getApplicationsList();
            this.typeConflictsList = exhibitionCenter.getConflictTypesRegister().getConflictTypesList();

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
        else{
            dispose();
            new DashboardUI(exhibitionCenter, staffMember);
        }
    }

    /**
     * Creates the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));
        componentsPanel.add(createTitleLabel(), BorderLayout.NORTH);
        componentsPanel.add(createConflictsListPanel(), BorderLayout.CENTER);
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
        return new JLabel("Conflicts list:", SwingConstants.CENTER);
    }

    /**
     * Creates the conflicts types panel.
     *
     * @return the conflicts types panel
     */
    private JPanel createConflictsListPanel() {

        JPanel conflictsListPanel = new JPanel(new BorderLayout());

        this.conflictsJList = new JList();
        this.conflictsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.conflictsJList.setModel(new ModelListSelectable(this.conflictsList));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.conflictsJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.conflictsJList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                UpdateDetectedConflictsUI.this.removeConflictsButton
                        .setEnabled(!UpdateDetectedConflictsUI.this.conflictsJList.isSelectionEmpty());
            }
        });

        JScrollPane conflictsJScrollPane = new JScrollPane(this.conflictsJList);
        conflictsListPanel.add(conflictsJScrollPane, BorderLayout.CENTER);

        return conflictsListPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createAddNewConflictButton());
        buttonsPanel.add(createRemoveConflictButton());
        buttonsPanel.add(createBackButton());
        return buttonsPanel;
    }

    /**
     * Creates the add conflict button.
     *
     * @return add conflict button
     */
    private JButton createAddNewConflictButton() {
        JButton addNewConflictButton = new JButton("Add new conflict");

        final String chooseApplicationAndTypeConflictText = "Choose from the lists to generate a new conflict?";

        addNewConflictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateDetectedConflictsUI.this.dialogAddNewConflict = new DialogAddNewConflict(UpdateDetectedConflictsUI.this, UpdateDetectedConflictsUI.this.applicationsList,
                        UpdateDetectedConflictsUI.this.typeConflictsList, chooseApplicationAndTypeConflictText, UpdateDetectedConflictsUI.this.controller);

                UpdateDetectedConflictsUI.this.updateDetectedConflictsList();
            }

        });

        return addNewConflictButton;
    }
    
    /**
     * Creates the back button.
     * @return the back button
     */
     public JButton createBackButton(){
        JButton button = new JButton("Back");
             button.setPreferredSize(new Dimension(150, 40));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardUI(exhibitionCenter, staffMember);
            }
        });
        return button;
    }
    /**
     * Updates the detected conflicts.
     */
    private void updateDetectedConflictsList() {
        this.conflictsList = controller.getConflictsListByStaffMember();

        this.conflictsJList.setModel(new ModelListSelectable(this.conflictsList));
    }

    /**
     * Creates the remove button for conflicts.
     *
     * @return conflicts remove button
     */
    private JButton createRemoveConflictButton() {
        this.removeConflictsButton = new JButton("Remove Conflict");
        this.removeConflictsButton.setEnabled(false);

        this.removeConflictsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Conflict conflict = UpdateDetectedConflictsUI.this.conflictsList
                        .get(UpdateDetectedConflictsUI.this.conflictsJList.getSelectedIndex());
                UpdateDetectedConflictsUI.this.conflictsList.remove(conflict);
                UpdateDetectedConflictsUI.this.updateConflictsList();
                JOptionPane.showMessageDialog(UpdateDetectedConflictsUI.this,
                        "The conflict was successful removed!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });

        return this.removeConflictsButton;
    }

    /**
     * Refresh the conflicts list.
     */
    private void updateConflictsList() {
        this.conflictsJList.setModel(new ModelListSelectable(this.conflictsList));
    }

    /**
     * Starting method for testing purposes.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        ExhibitionCenter exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        StaffMember staffMember = exhibitionCenter.getExhibitionsRegister().getExhibitionsList()
                .get(0).getStaffList().getStaffList().get(0);
        exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)
                .setState(new ExhibitionDetectedConflictsState(exhibitionCenter
                        .getExhibitionsRegister().getExhibitionsList().get(0)));

        Application a = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        ConflictType cta = new ConflictType(new RelatedUserConflictMechanism(), "Histórico na empresa");
        ConflictType ctb = new ConflictType(new RelatedUserConflictMechanism(), "Cunha");
        ConflictType ctc = new ConflictType(new RelatedUserConflictMechanism(), "Amizade");
        List<ConflictType> conflictsTypeList = new ArrayList();
        conflictsTypeList.add(cta);
        conflictsTypeList.add(ctb);
        conflictsTypeList.add(ctc);

        Conflict ca = new Conflict(cta, staffMember, a);

        StaffMember staffMember2 = exhibitionCenter.getExhibitionsRegister().getExhibitionsList()
                .get(0).getStaffList().getStaffList().get(1);

        Conflict cb = new Conflict(cta, staffMember2, a);
        List<Conflict> conflictsList = new ArrayList();
        conflictsList.add(ca);
        conflictsList.add(cb);

        exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getConflictsList().setConflictsList(conflictsList);
        exhibitionCenter.getConflictTypesRegister().setConflictTypesList(conflictsTypeList);

        new UpdateDetectedConflictsUI(exhibitionCenter, staffMember);
    }

}
