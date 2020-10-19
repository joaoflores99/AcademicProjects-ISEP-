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
import lapr.project.controller.RemoveApplicationController;
import lapr.project.model.Demonstration;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Removable;
import lapr.project.model.application.ApplicationInSubmissionState;
import lapr.project.model.exhibition.ExhibitionOpenApplicationsState;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphical interface to remove applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class RemoveApplicationUI extends JFrame {

    /**
     * The remove application controller.
     */
    private final RemoveApplicationController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitor responsible.
     */
    private final ExhibitorResponsible exhibitorResponsible;

    /**
     * The removables list.
     */
    private List<Removable> removablesList;

    /**
     * The removables JList.
     */
    private JList removablesJList;

    /**
     * The remove removable button.
     */
    private JButton removeRemovableButton;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Remove Application";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of remove application user interface.
     *
     * @param exhibitionCenter the exhibition center
     * @param exhibitorResponsible the exhibitor responsible
     */
    public RemoveApplicationUI(ExhibitionCenter exhibitionCenter, ExhibitorResponsible exhibitorResponsible) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
        this.controller = new RemoveApplicationController(this.exhibitionCenter, this.exhibitorResponsible);
        this.removablesList = this.controller.getRemovables(exhibitorResponsible);

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
        componentsPanel.add(createRemovablesPanel(), BorderLayout.CENTER);
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
        return new JLabel("Select an application that you wish to remove:", SwingConstants.CENTER);
    }

    /**
     * Creates the removables panel.
     *
     * @return the removables panel
     */
    private JPanel createRemovablesPanel() {
        JPanel removablesListPanel = new JPanel(new BorderLayout());

        this.removablesJList = new JList();
        this.removablesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.removablesJList.setModel(new ModelListSelectable(this.removablesList));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) this.removablesJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        this.removablesJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                RemoveApplicationUI.this.removeRemovableButton
                        .setEnabled(!RemoveApplicationUI.this.removablesJList.isSelectionEmpty());
            }
        });

        JScrollPane removablesJScrollPane = new JScrollPane(this.removablesJList);
        removablesListPanel.add(removablesJScrollPane, BorderLayout.CENTER);

        return removablesListPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createRemoveApplicationButton());
        buttonsPanel.add(createBackButton());

        return buttonsPanel;
    }

    /**
     * Creates the remove application button.
     *
     * @return the remove application button
     */
    private JButton createRemoveApplicationButton() {
        this.removeRemovableButton = new JButton("Remove Application");

        this.removeRemovableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Removable removable = RemoveApplicationUI.this.removablesList
                        .get(RemoveApplicationUI.this.removablesJList.getSelectedIndex());
                if (RemoveApplicationUI.this.controller.remove(removable)) {
                    RemoveApplicationUI.this.updateRemovablesList();
                    JOptionPane.showMessageDialog(RemoveApplicationUI.this,
                            "The application was successful removed!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(RemoveApplicationUI.this,
                            "Error while removing the application!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        return this.removeRemovableButton;
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
                new DashboardUI(exhibitionCenter, exhibitorResponsible);
            }
        });

        return backButton;
    }

    /**
     * Refresh the removables list.
     */
    private void updateRemovablesList() {
        // this.removablesList = controller.getRemovables(exhibitorResponsible);
        this.removablesJList.setModel(new ModelListSelectable(controller.getRemovables(exhibitorResponsible)));
    }

    //Method to test UI
    public static void main(String[] args) {
        ExhibitionCenter ec = DefaultInstantiator.createExhibitionCenter();
        ec.getExhibitionsRegister().getExhibitionsList().get(0).setState(new ExhibitionOpenApplicationsState(ec.getExhibitionsRegister().getExhibitionsList().get(0)));
        ExhibitorResponsible er = ec.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().
                getApplicationsList().get(0).getExhibitor().getExhibitorResponsible();

        ExhibitionApplication ea = (ExhibitionApplication) ec.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().
                getApplicationsList().get(0);
        List<Demonstration> demonstrationsList = new ArrayList();
        Demonstration d = new Demonstration("Demonstracao arroz");
        Demonstration d1 = new Demonstration("Demonstracao batatas");
        demonstrationsList.add(d);
        demonstrationsList.add(d1);
        ea.setDemonstrationsList(demonstrationsList);

        ec.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().
                getApplicationsList().get(0).setState(new ApplicationInSubmissionState(ea));

        new RemoveApplicationUI(ec, er);
    }

}
