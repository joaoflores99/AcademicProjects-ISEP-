/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lapr.project.controller.ExportExhibitionController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Organizer;
import lapr.project.model.exhibition.ExhibitionStaffWithoutDemosState;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;
import lapr.project.utils.Exportable;

/**
 * Graphic user interface to export exhibition.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExportExhibitionUI extends JFrame {

    /**
     * The define stand controller.
     */
    private final ExportExhibitionController controller;

    /**
     * The logged exhibitor responsible.
     */
    private final Organizer organizer;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibition applications list.
     */
    private final List<Exportable> exportablesList;

    /**
     * Exportables JList.
     */
    private JList exportablesJList;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Export Exhibition";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of export exhibition user interface.
     *
     * @param exhibitionCenter the exhibition center
     * @param organizer the organizer responsible logged in
     */
    public ExportExhibitionUI(ExhibitionCenter exhibitionCenter, Organizer organizer) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.organizer = organizer;
        this.controller = new ExportExhibitionController(this.organizer, this.exhibitionCenter);

        this.exportablesList = controller.getListExportablesByOrganizer(organizer);

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
        componentsPanel.add(createExportablesListPanel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the title label. Later if needed could be an exportable written
     * here..
     *
     * @return title label
     */
    private JLabel createTitleLabel() {
        return new JLabel("Select an exhibition to export:", SwingConstants.CENTER);
    }

    /**
     * Creates the exportables list panel.
     *
     * @return the exportables list panel
     */
    private JPanel createExportablesListPanel() {
        JPanel exportablesListPanel = new JPanel(new BorderLayout());

        this.exportablesJList = new JList();
        this.exportablesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.exportablesJList.setModel(new ModelListSelectable(this.exportablesList));

        this.exportablesJList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (exportablesJList.getSelectedIndex() >= 0) {
                    Exportable selectedExportable = exportablesList.get(exportablesJList.getSelectedIndex());
                    controller.setExportable(selectedExportable);
                    int result = JOptionPane.showConfirmDialog(rootPane, "Do you wish to export this exportable?\n" + selectedExportable.getData(), "Export", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        JFileChooser chooser = new JFileChooser();
                        result = chooser.showSaveDialog(rootPane);
                        if (result == JFileChooser.APPROVE_OPTION) {

                            controller.exportExportable(chooser.getCurrentDirectory().getAbsolutePath() + "\\" + chooser.getSelectedFile().getName());
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JScrollPane exportablesJScrollPane = new JScrollPane(this.exportablesJList);
        exportablesListPanel.add(exportablesJScrollPane, BorderLayout.CENTER);

        return exportablesListPanel;
    }

    /**
     * Create the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createBackButton());

        return buttonsPanel;
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
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new DashboardUI(exhibitionCenter, organizer);
            }
        });

        return backButton;
    }

    /**
     * to test the UI
     *
     * @param args
     */
    public static void main(String[] args) {
        ExhibitionCenter exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        Organizer organizer;
        exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).setState(new ExhibitionStaffWithoutDemosState(exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0)));
        organizer = exhibitionCenter.getExhibitionsRegister().getExhibitionsList().get(0).getOrganizersList().getOrganizersList().get(1);
        ExportExhibitionUI exportExhibitionUI = new ExportExhibitionUI(exhibitionCenter, organizer);

    }
}
