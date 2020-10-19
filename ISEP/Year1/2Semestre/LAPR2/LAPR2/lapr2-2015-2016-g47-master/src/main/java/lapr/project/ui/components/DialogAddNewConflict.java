/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
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

/**
 * Represents a dialog to add new conflict.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DialogAddNewConflict extends JDialog {

    /**
     * The parent JFrame.
     */
    private final JFrame parentFrame;

    /**
     * The application.
     */
    private Application application;

    /**
     * The Conflict Type.
     */
    private ConflictType conflictType;

    /**
     * The applications list.
     */
    private final List<Application> applicationsList;

    /**
     * The applications JList.
     */
    private JList<Application> applicationsJList;

    /**
     * The ConflictType list.
     */
    private final List<ConflictType> conflictTypeList;

    /**
     * The ConflictType JList.
     */
    private JList<ConflictType> conflictTypeJList;

    /**
     * The select button.
     */
    private JButton selectButton;

    /**
     * The UpdateDetectedConflictsController.
     */
    private UpdateDetectedConflictsController controller;

    /**
     * The conflict.
     */
    private Conflict conflict;

    /**
     * Text to be shown on the top of the list.
     */
    private final String chooseText;

    /**
     * Window title.
     */
    private static final String WINDOW_TITLE = "Add new Conflict";

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of choose selectable dialog.
     *
     * @param parentFrame parent JFrame
     * @param applicationsList
     * @param conflictTypesList
     * @param chooseText
     */
    public DialogAddNewConflict(JFrame parentFrame, List<Application> applicationsList, List<ConflictType> conflictTypesList, String chooseText, UpdateDetectedConflictsController controller) {
        super(parentFrame, WINDOW_TITLE, true);

        this.parentFrame = parentFrame;
        this.applicationsList = applicationsList;
        this.conflictTypeList = conflictTypesList;
        this.chooseText = chooseText;
        this.controller = controller;

        createComponents();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Creates the UI components to this dialog.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(0, 10));
        componentsPanel.add(createChooseTextLabel(), BorderLayout.NORTH);
        componentsPanel.add(createListsPanel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the top label with the text to choose a selectable.
     *
     * @return choose selectable JLabel
     */
    private JLabel createChooseTextLabel() {
        return new JLabel(this.chooseText, SwingConstants.CENTER);
    }

    /**
     * Creates the lists and buttons panel.
     *
     * @return lists and buttons panel
     */
    private JPanel createListsPanel() {
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 4, 4));

        listsPanel.add(createApplicationsListPanel());
        listsPanel.add(createConflictsTypeListPanel());

        return listsPanel;
    }

    /**
     * Creates the products list panel.
     *
     * @return products list panel
     */
    private JPanel createApplicationsListPanel() {
        JPanel applicationsListPanel = new JPanel(new BorderLayout());

        this.applicationsJList = new JList();
        this.applicationsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.applicationsJList.setModel(new ModelListSelectable(this.applicationsList));

        this.applicationsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                DialogAddNewConflict.this.application = DialogAddNewConflict.this.applicationsList
                        .get(DialogAddNewConflict.this.applicationsJList.getSelectedIndex());
            }
        });

        JScrollPane applicationsJScrollPane = new JScrollPane(this.applicationsJList);
        applicationsListPanel.add(applicationsJScrollPane, BorderLayout.CENTER);

        return applicationsListPanel;
    }

    /**
     * Creates the products list panel.
     *
     * @return products list panel
     */
    private JPanel createConflictsTypeListPanel() {
        JPanel conflictsTypeListPanel = new JPanel(new BorderLayout());

        this.conflictTypeJList = new JList();
        this.conflictTypeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.conflictTypeJList.setModel(new ModelListSelectable(this.conflictTypeList));

        this.conflictTypeJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                DialogAddNewConflict.this.conflictType = DialogAddNewConflict.this.conflictTypeList
                        .get(DialogAddNewConflict.this.conflictTypeJList.getSelectedIndex());
            }
        });

        JScrollPane conflictTypeJScrollPane = new JScrollPane(this.conflictTypeJList);
        conflictsTypeListPanel.add(conflictTypeJScrollPane, BorderLayout.CENTER);

        return conflictsTypeListPanel;
    }

    /**
     * Create the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        buttonsPanel.add(generateNewConflictButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the cancel button.
     *
     * @return cancel button
     */
    private JButton generateNewConflictButton() {
        JButton generateNewConflictButton = new JButton("Generate conflict");

        generateNewConflictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (DialogAddNewConflict.this.controller.newConflict(DialogAddNewConflict.this.application, DialogAddNewConflict.this.conflictType)) {
                    JOptionPane.showMessageDialog(DialogAddNewConflict.this,
                            "The conflict was successful generated!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                else{
                     JOptionPane.showMessageDialog(DialogAddNewConflict.this,
                            "Error while generating conflict!",
                            "Error",JOptionPane.ERROR_MESSAGE);
                    dispose();
                }

            }
        });

        return generateNewConflictButton;

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
                dispose();
            }
        });

        return cancelButton;
    }

    public Application getApplication() {
        return this.application;
    }

    public ConflictType getConflictType() {
        return this.conflictType;
    }

    public Conflict getConflict() {
        return this.conflict;
    }

}
