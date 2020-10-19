/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.DefineEffectiveDemosController;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.OrganizersList;
import lapr.project.model.User;
import lapr.project.model.demonstration.DemonstrationCreatedState;
import lapr.project.model.exhibition.ExhibitionCreatedState;
import lapr.project.model.exhibition.ExhibitionDecidedApplicationsState;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogEffectiveDemo;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to define effective demonstrations.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DefineEffectiveDemonstrationsUI extends JFrame {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The logged exhibitions manager.
     */
    private final Organizer organizer;

    /**
     * The controller to define effective demonstrations.
     */
    private final DefineEffectiveDemosController controller;

    /**
     * Effective Demonstrations JList component.
     */
    private JList effectiveDemosJList;

    /**
     * Remove a effective demonstration button.
     */
    private JButton removeEffectiveDemoBtn;

    /**
     * Open Applications Date Picker.
     */
    private JDateChooser openAppsDatePicker;

    /**
     * Close Applications Date Picker.
     */
    private JDateChooser closeAppsDatePicker;

    /**
     * Conflicts Limite Date Picker.
     */
    private JDateChooser conflictsLimitDatePicker;

    /**
     * Evaluations Limite Date Picker.
     */
    private JDateChooser evaluationsLimitDatePicker;

    /**
     * A boolean value trigger when common dates are valid.
     */
    private boolean flag;

    /**
     * Placeholder date.
     */
    private static final Date PLACEHOLDER_DATE = new Date();

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Define Effective Demonstrations";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(400, 500);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Field Top Margin.
     */
    final int FIELD_TOP_MARGIN = 0;

    /**
     * Field Bottom Margin.
     */
    final int FIELD_BOTTOM_MARGIN = 0;

    /**
     * Field Left Margin.
     */
    final int FIELD_LEFT_MARGIN = 10;

    /**
     * Field Right Margin.
     */
    final int FIELD_RIGHT_MARGIN = 0;

    /**
     * Field Width.
     */
    final int FIELD_WIDTH = 20;

    /**
     * Constructor of a Define Effective Demonstrations UI Class.
     *
     * @param exhibitionCenter the exhibition center
     * @param organizer the organizer logged in
     */
    public DefineEffectiveDemonstrationsUI(ExhibitionCenter exhibitionCenter, Organizer organizer) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.organizer = organizer;
        this.controller = new DefineEffectiveDemosController(exhibitionCenter);

        final String chooseExhibitionText = "Which exhibition do you wish to define demostrations?";
        DialogSelectable dialogSelectable = new DialogSelectable(this,
                controller.getExhibitionsList(organizer), chooseExhibitionText);
        Exhibition selectedExhibition = (Exhibition) dialogSelectable.getSelectedItem();

        if (selectedExhibition != null) {

            this.controller.setExhibitionAndDemonstrationsList(selectedExhibition);

            CustomMenuBar customMenuBar = new CustomMenuBar(exhibitionCenter, this);
            setJMenuBar(customMenuBar);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    customMenuBar.exit();
                }
            });
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            setLayout(new BorderLayout());
            createComponents();

            pack();
            setSize(WINDOW_DIMEMNSION);
            setMinimumSize(new Dimension(getWidth(), getHeight()));
            setLocationRelativeTo(null);
            setVisible(true);
        } else {
            dispose();
            new DashboardUI(exhibitionCenter, organizer);
        }

        this.flag = false;
    }

    /**
     * Create the UI components.
     */
    private void createComponents() {

        add(createSetCommonDatesPanel(), BorderLayout.NORTH);
        add(createDemonstrationsListAndButtonsPanel(), BorderLayout.CENTER);
    }

    /**
     * Create Panel with fields to set demonstrations common dates.
     *
     * @return Panel with fields to set demonstrations common dates
     */
    public JPanel createSetCommonDatesPanel() {

        JLabel selectedExhibitionLbl = new JLabel("Selected Exhibition: ", JLabel.RIGHT);
        JLabel openAppsLbl = new JLabel("Open Applications Date:", JLabel.RIGHT);
        JLabel closeAppsLbl = new JLabel("Close Applications Date:", JLabel.RIGHT);
        JLabel conflictsLimitLbl = new JLabel("Conflicts Limit Date:", JLabel.RIGHT);
        JLabel evaluationsLimitLbl = new JLabel("Evaluations Limit Date:", JLabel.RIGHT);

        JLabel exhibitionLbl = new JLabel(controller.getSelectedExhibition().getTitle(), JLabel.RIGHT);
        Font font = exhibitionLbl.getFont();
        exhibitionLbl.setFont(font.deriveFont(font.getStyle() | Font.BOLD));

        // Set Date Pickers
        openAppsDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        closeAppsDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        conflictsLimitDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        evaluationsLimitDatePicker = new JDateChooser(PLACEHOLDER_DATE);

        // Set date pickers in a panel
        JPanel openAppsDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        openAppsDatePanel.add(openAppsDatePicker);
        JPanel closeAppsDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        closeAppsDatePanel.add(closeAppsDatePicker);
        JPanel conflictsLimitDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        conflictsLimitDatePanel.add(conflictsLimitDatePicker);
        JPanel evaluationsLimitDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        evaluationsLimitDatePanel.add(evaluationsLimitDatePicker);

        // Set main panel
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);

        // Align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(selectedExhibitionLbl)
                        .addComponent(openAppsLbl)
                        .addComponent(closeAppsLbl)
                        .addComponent(conflictsLimitLbl)
                        .addComponent(evaluationsLimitLbl)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(exhibitionLbl)
                        .addComponent(openAppsDatePanel)
                        .addComponent(closeAppsDatePanel)
                        .addComponent(conflictsLimitDatePanel)
                        .addComponent(evaluationsLimitDatePanel)
                )
        );
        // Align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectedExhibitionLbl)
                        .addComponent(exhibitionLbl))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(openAppsLbl)
                        .addComponent(openAppsDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(closeAppsLbl)
                        .addComponent(closeAppsDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(conflictsLimitLbl)
                        .addComponent(conflictsLimitDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(evaluationsLimitLbl)
                        .addComponent(evaluationsLimitDatePanel)
                )
        );

        return panel;
    }

    /**
     * Create scroll panel for effective Demonstrations list.
     *
     * @return scroll panel for effective demonstrations list
     */
    public JPanel createEffectiveDemosPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel listPanel = new JPanel(new GridLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Select Demo:", TitledBorder.LEFT, TitledBorder.TOP));

        ModelListSelectable effectiveDemosModel = new ModelListSelectable(controller.getEffectiveDemosList());
        effectiveDemosJList = new JList(effectiveDemosModel);

        effectiveDemosJList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                removeEffectiveDemoBtn.setEnabled(!effectiveDemosJList.isSelectionEmpty());
            }
        });

        JScrollPane scrollPane = new JScrollPane(effectiveDemosJList);
        scrollPane.setBorder(PADDING_BORDER);
        listPanel.setMinimumSize(scrollPane.getMinimumSize());
        listPanel.add(scrollPane);

        panel.add(listPanel, BorderLayout.NORTH);
        panel.add(createAddAndRemoveButtons(), BorderLayout.CENTER);

        return panel;
    }

    /**
     * Create panel with add & remove demos buttons.
     *
     * @return panel with add & remove demos buttons
     */
    private JPanel createAddAndRemoveButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.add(createAddEffectiveDemoButton());
        panel.add(createRemoveEffectiveDemoButton());

        return panel;
    }

    /**
     * Create Add Effective Demo Button.
     *
     * @return Add Effective Demo Button
     */
    private JButton createAddEffectiveDemoButton() {

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (!flag) {

                        Date openAppsDate = openAppsDatePicker.getDate();
                        Date closeAppsDate = closeAppsDatePicker.getDate();
                        Date conflictsLimitDate = conflictsLimitDatePicker.getDate();
                        Date evaluationsLimitDate = evaluationsLimitDatePicker.getDate();

                        controller.setCommonDates(openAppsDate, closeAppsDate, conflictsLimitDate, evaluationsLimitDate);
                        if (!controller.validateCommonDates()) {
                            throw new IllegalArgumentException("Invalid common dates! please enter valid dates.");
                        }
                        String message = "Do you confirm your common dates?";
                        int confirma = JOptionPane.showConfirmDialog(rootPane, message, "Confirmation", JOptionPane.YES_NO_OPTION);

                        if (confirma == JOptionPane.YES_OPTION) {
                            lockCommonDates();
                            flag = true;
                        } else {
                            throw new IllegalArgumentException("Correct your dates.");
                        }
                    }

                    List<Demonstration> demosList = controller.getDemonstrationsList();

                    for (Demonstration demo : controller.getEffectiveDemosList()) {
                        demosList.remove(demo);
                    }

                    if (demosList.isEmpty()) {
                        throw new IllegalArgumentException("There is no more demonstrations.");
                    }

                    DialogEffectiveDemo dialog = new DialogEffectiveDemo(DefineEffectiveDemonstrationsUI.this, controller, demosList, "Select a demonstration.");

                    updateEffectiveDemonstrationList();

                } catch (IllegalArgumentException ex) {

                    String warningTitle = "Error";

                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), warningTitle, JOptionPane.WARNING_MESSAGE);

                } catch (Exception ex) {

                    String warningMessage = "Something wen't wrong please try again.";
                    String warningTitle = "ERROR 404";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        return addBtn;
    }

    /**
     * Create Remove Resource Button.
     *
     * @return Remove Organizer Button
     */
    private JButton createRemoveEffectiveDemoButton() {

        removeEffectiveDemoBtn = new JButton("Remove");
        removeEffectiveDemoBtn.setEnabled(false);
        removeEffectiveDemoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int index = effectiveDemosJList.getSelectedIndex();
                    Demonstration removedDemo = controller.getEffectiveDemosList().get(index);

                    controller.revertDemonstration(removedDemo);

                    if (removedDemo.isCreated()) {
                        updateEffectiveDemonstrationList();
                        String successMessage = "Demonstration removed successfully!";
                        String successTitle = "Demonstration removed.";

                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        throw new IllegalArgumentException();
                    }
                } catch (Exception ex) {

                    String warningMessage = "Something wen't wrong please try again.";
                    String warningTitle = "ERROR 404";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
                }

                removeEffectiveDemoBtn.setEnabled(!effectiveDemosJList.isSelectionEmpty());
            }
        });
        return removeEffectiveDemoBtn;
    }

    /**
     * Create panel with effective demonstrations list & confirmation buttons.
     *
     * @return panel with effective demonstrations list & confirmation buttons
     */
    private JPanel createDemonstrationsListAndButtonsPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(PADDING_BORDER);

        panel.add(createEffectiveDemosPanel(), BorderLayout.NORTH);
        panel.add(createConfirmButtons(), BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Create panel with confirm & cancel buttons.
     *
     * @return panel with confirm & cancel buttons
     */
    private JPanel createConfirmButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panel.add(createConfirmButton());
        panel.add(createCancelButton());

        return panel;
    }

    /**
     * Create Confirm Button.
     *
     * @return Confirm Button
     */
    private JButton createConfirmButton() {

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (controller.getEffectiveDemosList().size() < 1) {
                        throw new IllegalArgumentException("You need to define at least one demonstration.");
                    }
                    int confirma = JOptionPane.showConfirmDialog(rootPane, "Confirm?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (confirma == JOptionPane.YES_OPTION) {
                        controller.updateDemosntrationList();
                        dispose();
                        new DashboardUI(exhibitionCenter, organizer);
                    }
                } catch (IllegalArgumentException ex) {

                    JOptionPane.showMessageDialog(
                            rootPane,
                            ex.getMessage(),
                            "No demonstrations where defined",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.rootPane.setDefaultButton(confirmBtn);

        return confirmBtn;
    }

    /**
     * Create Cancel Button.
     *
     * @return o botão cancelar
     */
    private JButton createCancelButton() {

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new DashboardUI(exhibitionCenter, organizer);
            }
        });
        return cancelBtn;
    }

    /**
     * Lock all date pickers.
     */
    private void lockCommonDates() {

        openAppsDatePicker.setEnabled(false);
        closeAppsDatePicker.setEnabled(false);
        conflictsLimitDatePicker.setEnabled(false);
        evaluationsLimitDatePicker.setEnabled(false);
    }

    /**
     * Refresh the effective demonstrations list.
     */
    private void updateEffectiveDemonstrationList() {
        this.effectiveDemosJList.setModel(new ModelListSelectable(controller.getEffectiveDemosList()));
    }

    /**
     * Starting method for testing purposes, later wil be removed.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ExhibitionCenter ec = DefaultInstantiator.createExhibitionCenter();

        Organizer o = new Organizer(new User("Daniel", "daniell", "email@dd23", "password", new ArrayList<>(), ""));
        List<Organizer> lo = new ArrayList<>();
        lo.add(o);

        Demonstration d1 = new Demonstration("test1");
        d1.setCurrentState(new DemonstrationCreatedState(d1));
        Demonstration d2 = new Demonstration("test2");
        d2.setCurrentState(new DemonstrationCreatedState(d2));
        Demonstration d3 = new Demonstration("test3");
        d3.setCurrentState(new DemonstrationCreatedState(d3));
        List<Demonstration> ld = new ArrayList<>();
        ld.add(d1);
        ld.add(d2);
        ld.add(d3);

        Exhibition e1 = new Exhibition();
        e1.setState(new ExhibitionDecidedApplicationsState(e1));
        e1.setOrganizersList(new OrganizersList(lo));
        e1.setDemonstrationsList(new DemonstrationsList(ld));

        Exhibition e2 = new Exhibition();
        e2.setState(new ExhibitionCreatedState(e2));
        e2.setOrganizersList(new OrganizersList(lo));

        List<Exhibition> le = new ArrayList<>();
        le.add(e1);
        le.add(e2);

        ec.setExhibitionsRegister(new ExhibitionsRegister(le));

        new DefineEffectiveDemonstrationsUI(ec, o);
    }
}
