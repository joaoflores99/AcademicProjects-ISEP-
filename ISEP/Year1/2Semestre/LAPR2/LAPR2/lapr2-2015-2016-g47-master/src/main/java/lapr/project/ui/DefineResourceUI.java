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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.DefineResourceController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.Resource;
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
public class DefineResourceUI extends JFrame {

    /**
     * The define resource controller.
     */
    private final DefineResourceController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitions manager logged in.
     */
    private final ExhibitionsManager exhibitionsManager;

    /**
     * The resources list.
     */
    private List<Resource> resourcesList;

    /**
     * Resources JList.
     */
    private JList resourcesJList;

    /**
     * Remove resource button.
     */
    private JButton removeResourceButton;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Define Resources";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of define resources user interface.
     *
     * @param exhibitionCenter the exhibition center
     */
    public DefineResourceUI(ExhibitionCenter exhibitionCenter, ExhibitionsManager exhibitionsManager) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager = exhibitionsManager;
        this.controller = new DefineResourceController(this.exhibitionCenter);

        this.resourcesList = this.controller.getResources();

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
        componentsPanel.add(createResourcesPanel(), BorderLayout.CENTER);
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
        return new JLabel("Available Resources:", SwingConstants.CENTER);
    }

    /**
     * Creates the resources panel.
     *
     * @return exhibition applications panel
     */
    private JPanel createResourcesPanel() {
        JPanel exhibitionApplicationsListPanel = new JPanel(new BorderLayout());

        this.resourcesJList = new JList();
        this.resourcesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.resourcesJList.setModel(new ModelListSelectable(this.resourcesList));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.resourcesJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.resourcesJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                DefineResourceUI.this.removeResourceButton.setEnabled(!DefineResourceUI.this.resourcesJList.isSelectionEmpty());
            }
        });

        JScrollPane exhibitionApplicationsJScrollPane = new JScrollPane(this.resourcesJList);
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

        buttonsPanel.add(createAddResourceButton());
        buttonsPanel.add(createRemoveResourceButton());
        buttonsPanel.add(createBackButton());

        return buttonsPanel;
    }

    /**
     * Creates the add resources button.
     *
     * @return add resources button
     */
    private JButton createAddResourceButton() {
        JButton addResourceButton = new JButton("Add Resource");

        addResourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String resource = JOptionPane.showInputDialog(DefineResourceUI.this,
                        "Insert the resource designation:",
                        "Add Resource",
                        JOptionPane.QUESTION_MESSAGE);

                if (DefineResourceUI.this.controller.newResource(resource) && resource != null) {

                    DefineResourceUI.this.controller.registerResource();
                    DefineResourceUI.this.updateResourcesList();

                } else if (resource != null) {

                    JOptionPane.showMessageDialog(DefineResourceUI.this,
                            "The resource is invalid or already exists.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return addResourceButton;
    }

    /**
     * Creates the remove resources button.
     *
     * @return remove resources button
     */
    private JButton createRemoveResourceButton() {
        this.removeResourceButton = new JButton("Remove Resource");
        this.removeResourceButton.setEnabled(false);

        this.removeResourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DefineResourceUI.this.resourcesJList.getSelectedIndex() >= 0) {

                    Resource resource = DefineResourceUI.this.resourcesList
                            .get(DefineResourceUI.this.resourcesJList.getSelectedIndex());

                    int confirmation = JOptionPane.showConfirmDialog(
                            DefineResourceUI.this,
                            "Are you sure that you want to delete the resource \"" + resource.getDesignation() + "\"?",
                            "Delete Confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmation == 0) {
                        DefineResourceUI.this.controller.removeResource(resource);
                        DefineResourceUI.this.updateResourcesList();
                    }

                }
            }
        });

        return this.removeResourceButton;
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
                new DashboardUI(DefineResourceUI.this.exhibitionCenter, DefineResourceUI.this.exhibitionsManager);
            }
        });

        return backButton;
    }

    /**
     * Refresh the resources list.
     */
    private void updateResourcesList() {
        this.resourcesList = controller.getResources();
        this.resourcesJList.setModel(new ModelListSelectable(this.resourcesList));
    }

    /**
     * Starting method for testing purposes.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ExhibitionCenter ec = DefaultInstantiator.createExhibitionCenter();
        ExhibitionsManager em = ec.getExhibitionsManagerRegister().getExhibitionsManagerList().get(0);
        new DefineResourceUI(ec, em);
    }

}
