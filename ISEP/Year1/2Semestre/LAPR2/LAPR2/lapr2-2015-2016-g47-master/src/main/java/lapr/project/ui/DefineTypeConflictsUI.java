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
import lapr.project.controller.DefineTypeConflictsController;
import lapr.project.model.ConflictType;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.User;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to define resources.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineTypeConflictsUI extends JFrame {

    /**
     * The define conflicts types controller.
     */
    private final DefineTypeConflictsController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitions manager.
     */
    private final ExhibitionsManager exhibitionsManager;

    /**
     * The conflict types list.
     */
    private List<ConflictType> typeConflictsList;

    /**
     * The conflict types JList.
     */
    private JList typeConflictsJList;

    /**
     * Remove conflict types button.
     */
    private JButton removeTypeConflictButton;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Define Conflicts Types";

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
     * @param exhibitionsManager the exhibition manager
     */
    public DefineTypeConflictsUI(ExhibitionCenter exhibitionCenter, ExhibitionsManager exhibitionsManager) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager = exhibitionsManager;
        this.controller = new DefineTypeConflictsController(this.exhibitionCenter, this.exhibitionsManager);

        this.typeConflictsList = this.controller.getConflictTypesList();

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
        componentsPanel.add(createConflictsTypesPanel(), BorderLayout.CENTER);
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
        return new JLabel("Conflicts Types already defined:", SwingConstants.CENTER);
    }

    /**
     * Creates the conflicts types panel.
     *
     * @return the conflicts types panel
     */
    private JPanel createConflictsTypesPanel() {
        JPanel conflictsTypesListPanel = new JPanel(new BorderLayout());

        this.typeConflictsJList = new JList();
        this.typeConflictsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.typeConflictsJList.setModel(new ModelListSelectable(this.typeConflictsList));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.typeConflictsJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.typeConflictsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {

            }
        });

        JScrollPane conflictsTypesJScrollPane = new JScrollPane(this.typeConflictsJList);
        conflictsTypesListPanel.add(conflictsTypesJScrollPane, BorderLayout.CENTER);

        return conflictsTypesListPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createAddTypeConflictButton());
        buttonsPanel.add(createBackButton());

        return buttonsPanel;
    }

    /**
     * Creates the add conflicts types button.
     *
     * @return add conflicts types button
     */
    private JButton createAddTypeConflictButton() {
        JButton addTypeConflictButton = new JButton("Add type conflict");

        addTypeConflictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.newConflictType();

                String typeConflict = JOptionPane.showInputDialog(DefineTypeConflictsUI.this,
                        "Insert the TypeConflict designation:",
                        "Add TypeConflict",
                        JOptionPane.QUESTION_MESSAGE);

                controller.setDataConflictType(typeConflict);

                if (controller.registerTypeConflict()) {
                    DefineTypeConflictsUI.this.updateTypeConflictsList();
                } else if (typeConflict != null) {

                    JOptionPane.showMessageDialog(DefineTypeConflictsUI.this,
                            "The type conflict is invalid or already exists.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return addTypeConflictButton;
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
                new DashboardUI(exhibitionCenter, exhibitionsManager);
            }
        });

        return backButton;
    }

    /**
     * Refresh the typeConflicts list.
     */
    private void updateTypeConflictsList() {
        this.typeConflictsList = controller.getConflictTypesList();
        this.typeConflictsJList.setModel(new ModelListSelectable(this.typeConflictsList));
    }

    /**
     * Starting method for testing purposes.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ExhibitionCenter ec = DefaultInstantiator.createExhibitionCenter();
        ConflictType cta = new ConflictType("Histórico na empresa");
        ConflictType ctb = new ConflictType("Cunha");
        ConflictType ctc = new ConflictType("Amizade");
        List<ConflictType> ctList = new ArrayList();
        ctList = ec.getConflictTypesRegister().getConflictTypesList();
        ctList.add(cta);
        ctList.add(ctb);
        ctList.add(ctc);
        ec.getConflictTypesRegister().setConflictTypesList(ctList);
        User user = ec.getUsersRegister().getUsersList().get(0);
        ExhibitionsManager em = new ExhibitionsManager(new User(user));
        new DefineTypeConflictsUI(ec, em);
    }

}
