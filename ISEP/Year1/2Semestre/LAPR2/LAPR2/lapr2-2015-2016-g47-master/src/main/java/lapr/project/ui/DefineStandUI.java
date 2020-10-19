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
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.DefineStandController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.Stand;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to define stands.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineStandUI extends JFrame {

    /**
     * The define stand controller.
     */
    private final DefineStandController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitions manager logged in.
     */
    private final ExhibitionsManager exhibitionsManager;

    /**
     * The stand list.
     */
    private List<Stand> standsList;

    /**
     * Stands JList.
     */
    private JList standsJList;

    /**
     * Remove stand button.
     */
    private JButton removeStandButton;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Define Stand";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(600, 400);

    /**
     * The padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of define stand user interface.
     *
     * @param exhibitionCenter exhibition center
     */
    public DefineStandUI(ExhibitionCenter exhibitionCenter, ExhibitionsManager exhibitionsManager) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager = exhibitionsManager;
        this.controller = new DefineStandController(this.exhibitionCenter);

        this.standsList = this.controller.getStands();

        CustomMenuBar customMenuBar = new CustomMenuBar(this.exhibitionCenter, this);
        setJMenuBar(customMenuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customMenuBar.exit();
            }
        });

        createComponents();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setSize(WINDOW_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Creates the define stand UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));
        componentsPanel.add(createTitleLabel(), BorderLayout.NORTH);
        componentsPanel.add(createStandPanel(), BorderLayout.CENTER);
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
        return new JLabel("Available Stands:", SwingConstants.CENTER);
    }

    /**
     * Creates the stand panel.
     *
     * @return exhibition applications panel
     */
    private JPanel createStandPanel() {
        JPanel exhibitionApplicationsListPanel = new JPanel(new BorderLayout());

        this.standsJList = new JList();
        this.standsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.standsJList.setModel(new ModelListSelectable(this.standsList));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.standsJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.standsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                DefineStandUI.this.removeStandButton.setEnabled(!DefineStandUI.this.standsJList.isSelectionEmpty());
            }
        });

        JScrollPane exhibitionApplicationsJScrollPane = new JScrollPane(this.standsJList);
        exhibitionApplicationsListPanel.add(exhibitionApplicationsJScrollPane, BorderLayout.CENTER);

        return exhibitionApplicationsListPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createAddStandButton());
        buttonsPanel.add(createRemoveResourceButton());
        buttonsPanel.add(createBackButton());

        return buttonsPanel;
    }

    /**
     * Creates the add stands button.
     *
     * @return add stands button
     */
    private JButton createAddStandButton() {
        JButton addStandButton = new JButton("Add Stand");

        addStandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField descriptionText = new JTextField();
                JTextField areaText = new JTextField();
                Object[] message = {
                    "Designation:", descriptionText,
                    "Area:", areaText
                };

                int option = JOptionPane.showConfirmDialog(rootPane, message, "New Stand", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String description = descriptionText.getText();
                        float area = Float.parseFloat(areaText.getText());

                        boolean addedStand = (controller.newStand(area, description)) ? controller.registerStand() : false;
                        if (addedStand) {
                            JOptionPane.showMessageDialog(rootPane, "Stand added with success!", "Added new Stand", JOptionPane.INFORMATION_MESSAGE);
                            standsList = controller.getStands();
                            standsJList.setModel(new ModelListSelectable(standsList));
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Invalid area! Please try again.");

                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Missing data! Please try again.");
                    }
                }
            }

        });

        return addStandButton;

    }

    /**
     * Creates the remove stand button.
     *
     * @return
     */
    private JButton createRemoveResourceButton() {

        this.removeStandButton = new JButton("Remove Stand");
        this.removeStandButton.setEnabled(false);

        this.removeStandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DefineStandUI.this.standsJList.getSelectedIndex() >= 0) {

                    Stand stand = DefineStandUI.this.standsList
                            .get(DefineStandUI.this.standsJList.getSelectedIndex());

                    int confirmation = JOptionPane.showConfirmDialog(
                            DefineStandUI.this,
                            "Please confirm stand deletion \"" + stand.getDescription() + "\"?",
                            "Delete confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmation == 0) {
                        DefineStandUI.this.controller.removeStand(stand);
                        DefineStandUI.this.updateStandsList();
                    }
                }
            }
        });

        return this.removeStandButton;
    }

    /**
     * Creates back button.
     *
     * @return back button
     */
    private JButton createBackButton() {

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DashboardUI(DefineStandUI.this.exhibitionCenter, DefineStandUI.this.exhibitionsManager);
            }
        });

        return backButton;
    }

    /**
     * Refresh the stand list.
     */
    private void updateStandsList() {
        this.standsList = controller.getStands();
        this.standsJList.setModel(new ModelListSelectable(this.standsList));
    }

    /**
     * Main() for testing
     *
     * @params
     */
    public static void main(String[] args) {
        ExhibitionCenter ec = DefaultInstantiator.createExhibitionCenter();
        ExhibitionsManager em = ec.getExhibitionsManagerRegister().getExhibitionsManagerList().get(0);
        new DefineStandUI(ec, em);
    }

}
