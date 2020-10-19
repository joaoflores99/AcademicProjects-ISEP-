/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.CreateExhibitionController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.Organizer;
import lapr.project.model.Place;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;

/**
 * Graphic user interface to create exhibitions.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateExhibitionUI extends JFrame {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The logged exhibitions manager.
     */
    private final ExhibitionsManager exhibitionsManager;

    /**
     * The controller to create exhibitions.
     */
    private final CreateExhibitionController controller;

    /**
     * Title Textfield component.
     */
    private JTextField txtFieldTitle;

    /**
     * Description Textfield component.
     */
    private JTextField txtFieldDescription;

    /**
     * Place Textfield component.
     */
    private JTextField txtFieldPlace;

    /**
     * Start Date Picker.
     */
    private JDateChooser startDatePicker;

    /**
     * End Date Picker.
     */
    private JDateChooser endDatePicker;

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
     * Placeholder date.
     */
    private static final Date PLACEHOLDER_DATE = new Date();

    /**
     * Users JList component.
     */
    private JList organizersJList;

    /**
     * Remove a organizer button.
     */
    private JButton removeOrganizerBtn;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Create Exhibiton";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(800, 400);

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
     * Constructor of a Create Exhibition UI Class.
     *
     * @param exhibitionCenter the exhibition center
     */
    public CreateExhibitionUI(ExhibitionCenter exhibitionCenter, ExhibitionsManager exhibitionsManager) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager = exhibitionsManager;
        this.controller = new CreateExhibitionController(exhibitionCenter);

        // Create a new exhibition
        this.controller.newExhibition();

        CustomMenuBar customMenuBar = new CustomMenuBar(exhibitionCenter, this);
        setJMenuBar(customMenuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customMenuBar.exit();
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLayout(new GridLayout(1, 2));
        createComponents();

        pack();
        setSize(WINDOW_DIMEMNSION);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Create the UI components.
     */
    private void createComponents() {

        add(createSetDataPanel());
        add(createOrganizersListAndButtonsPanel());
    }

    /**
     * Create Panel with fields to set exhibition's data.
     *
     * @return Panel with fields to set exhibition's data
     */
    public JPanel createSetDataPanel() {

        JLabel titleLbl = new JLabel("Title:", JLabel.RIGHT);
        JLabel descriptionLbl = new JLabel("Description:", JLabel.RIGHT);
        JLabel placeLbl = new JLabel("Location:", JLabel.RIGHT);
        JLabel startDateLbl = new JLabel("Start Date:", JLabel.RIGHT);
        JLabel endDateLbl = new JLabel("End Date:", JLabel.RIGHT);
        JLabel openAppsDateLbl = new JLabel("Open Applications Date:", JLabel.RIGHT);
        JLabel closedAppsDateLbl = new JLabel("Close Applications Date:", JLabel.RIGHT);
        JLabel conflictsDateLbl = new JLabel("Conflicts Limit Date:", JLabel.RIGHT);
        JLabel evaluationsDateLbl = new JLabel("Evaluations Limit Date:", JLabel.RIGHT);

        txtFieldTitle = new JTextField(FIELD_WIDTH);
        txtFieldDescription = new JTextField(FIELD_WIDTH);
        txtFieldPlace = new JTextField(FIELD_WIDTH);
        startDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        endDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        openAppsDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        closeAppsDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        conflictsLimitDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        evaluationsLimitDatePicker = new JDateChooser(PLACEHOLDER_DATE);

        // Set date pickers in a panel 
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        startDatePanel.setSize(txtFieldDescription.getSize());
        startDatePanel.add(startDatePicker);
        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        endDatePanel.add(endDatePicker);
        JPanel openAppsDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        openAppsDatePanel.add(openAppsDatePicker);
        JPanel closedAppsDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        closedAppsDatePanel.add(closeAppsDatePicker);
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
                        .addComponent(titleLbl)
                        .addComponent(descriptionLbl)
                        .addComponent(placeLbl)
                        .addComponent(startDateLbl)
                        .addComponent(endDateLbl)
                        .addComponent(openAppsDateLbl)
                        .addComponent(closedAppsDateLbl)
                        .addComponent(conflictsDateLbl)
                        .addComponent(evaluationsDateLbl)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtFieldTitle)
                        .addComponent(txtFieldDescription)
                        .addComponent(txtFieldPlace)
                        .addComponent(startDatePanel)
                        .addComponent(endDatePanel)
                        .addComponent(openAppsDatePanel)
                        .addComponent(closedAppsDatePanel)
                        .addComponent(conflictsLimitDatePanel)
                        .addComponent(evaluationsLimitDatePanel)
                )
        );

        // Align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(titleLbl)
                        .addComponent(txtFieldTitle))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(descriptionLbl)
                        .addComponent(txtFieldDescription)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(placeLbl)
                        .addComponent(txtFieldPlace))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(startDateLbl)
                        .addComponent(startDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(endDateLbl)
                        .addComponent(endDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(openAppsDateLbl)
                        .addComponent(openAppsDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(closedAppsDateLbl)
                        .addComponent(closedAppsDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(conflictsDateLbl)
                        .addComponent(conflictsLimitDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(evaluationsDateLbl)
                        .addComponent(evaluationsLimitDatePanel)
                )
        );

        return panel;
    }

    /**
     * Create scroll panel for organizers list.
     *
     * @return scroll panel for organizers list
     */
    public JPanel createOrganizersPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel listPanel = new JPanel(new GridLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Select Organizers:", TitledBorder.LEFT, TitledBorder.TOP));

        ModelListSelectable organizersModel = new ModelListSelectable(controller.getOrganizersList());
        organizersJList = new JList(organizersModel);

        organizersJList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                removeOrganizerBtn.setEnabled(!organizersJList.isSelectionEmpty());
            }
        });

        JScrollPane scrollPane = new JScrollPane(organizersJList);
        scrollPane.setBorder(PADDING_BORDER);
        listPanel.setMinimumSize(scrollPane.getMinimumSize());
        listPanel.add(scrollPane);

        panel.add(listPanel, BorderLayout.NORTH);
        panel.add(createAddAndRemoveButtons(), BorderLayout.CENTER);

        return panel;
    }

    /**
     * Create panel with add & remove organizers buttons.
     *
     * @return panel with add & remove organizers buttons
     */
    private JPanel createAddAndRemoveButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.add(createAddOrganizerButton());
        panel.add(createRemoveOrganizerButton());

        return panel;
    }

    /**
     * Create Add Organizer Button.
     *
     * @return Add Organizer Button
     */
    private JButton createAddOrganizerButton() {

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    List<User> usersList = controller.getUsersList();

                    for (Organizer organizer : controller.getOrganizersList()) {
                        usersList.remove(organizer.getUser());
                    }

                    if (usersList.isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    DialogSelectable dialogoNewOrganizer = new DialogSelectable(CreateExhibitionUI.this, usersList, "Select User from list:");
                    User selectedUser = (User) dialogoNewOrganizer.getSelectedItem();
                    if (selectedUser == null) {
                        throw new NullPointerException();
                    }
                    if (controller.newOrganizer(selectedUser)) {
                        updateOrganizersList();
                        String successMessage = "Organizer added successfully!";
                        String successTitle = "Organizer added.";

                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NullPointerException ex) {

                } catch (IllegalArgumentException ex) {

                    String warningMessage = "There is no more users to add";
                    String warningTitle = "No more users in system";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);

                } catch (Exception ex) {

                    String warningMessage = "Something went wrong please try again.";
                    String warningTitle = "ERROR 404";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        return addBtn;
    }

    /**
     * Create Remove Organizer Button.
     *
     * @return Remove Organizer Button
     */
    private JButton createRemoveOrganizerButton() {

        removeOrganizerBtn = new JButton("Remove");
        removeOrganizerBtn.setEnabled(false);
        removeOrganizerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int index = organizersJList.getSelectedIndex();

                    if (controller.removeOrganizer(index)) {
                        updateOrganizersList();
                        String successMessage = "Organizer removed successfully!";
                        String successTitle = "Organizer removed.";

                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        throw new IllegalArgumentException();
                    }
                } catch (Exception ex) {

                    String warningMessage = "Something wen't wrong please try again.";
                    String warningTitle = "ERROR 404";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
                }

                removeOrganizerBtn.setEnabled(!organizersJList.isSelectionEmpty());
            }
        });
        return removeOrganizerBtn;
    }

    /**
     * Create panel with organizers list & confirmation buttons.
     *
     * @return panel with organizers list & confirmation buttons
     */
    private JPanel createOrganizersListAndButtonsPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(PADDING_BORDER);

        panel.add(createOrganizersPanel(), BorderLayout.NORTH);
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

                    if (controller.getOrganizersList().size() < 2) {
                        throw new IllegalArgumentException("You need to select at least 2 organizers");
                    }

                    String title = txtFieldTitle.getText();
                    String description = txtFieldDescription.getText();
                    Place place = new Place(description);
                    Date startDate = startDatePicker.getDate();
                    Date endDate = endDatePicker.getDate();
                    Date openAppsDate = openAppsDatePicker.getDate();
                    Date closedAppsDate = closeAppsDatePicker.getDate();
                    Date conflictsDate = conflictsLimitDatePicker.getDate();
                    Date evaluationsDate = evaluationsLimitDatePicker.getDate();

                    controller.setData(title, description, place, startDate, endDate, openAppsDate, closedAppsDate, conflictsDate, evaluationsDate);

                    if (!controller.validateExhibition()) {
                        throw new IllegalArgumentException("Invalid Data, please verify");
                    }
                    String data = String.format("Title: %s%n"
                            + "Description: %s%n"
                            + "Start Date: %s%n"
                            + "End Date: %s%n"
                            + "Open Applications Date: %s%n"
                            + "Closed Applications Date: %s%n"
                            + "Conflicts Limit Date: %s%n"
                            + "Evaluations Limit Date: %s%n",
                            controller.getExhibition().getTitle(), controller.getExhibition().getDescription(),
                            controller.getExhibition().getStartDate(), controller.getExhibition().getEndDate(),
                            controller.getExhibition().getSubStartDate(), controller.getExhibition().getSubEndDate(),
                            controller.getExhibition().getConflictLimitDate(), controller.getExhibition().getEvaluationLimitDate());

                    String question = String.format("%n%nPlease confirm the new exibition?");

                    StringBuilder message = new StringBuilder();
                    message.append(String.format("New Exhibition%n%n"));
                    message.append(data);
                    message.append(String.format("%nOrganizers:%n"));
                    for (Organizer organizer : controller.getExhibition().getOrganizersList().getOrganizersList()) {
                        message.append(String.format("%s%n", organizer.getUser().getName()));
                    }
                    message.append(question);

                    int confirma = JOptionPane.showConfirmDialog(rootPane, message);

                    if (confirma == JOptionPane.YES_OPTION) {
                        controller.registerExhibition();
                        dispose();
                        new DashboardUI(CreateExhibitionUI.this.exhibitionCenter, CreateExhibitionUI.this.exhibitionsManager);
                    }
                } catch (IllegalArgumentException ex) {

                    JOptionPane.showMessageDialog(
                            rootPane,
                            ex.getMessage(),
                            "Invalid Data",
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
                new DashboardUI(CreateExhibitionUI.this.exhibitionCenter, CreateExhibitionUI.this.exhibitionsManager);
            }
        });
        return cancelBtn;
    }

    /**
     * Refresh the organizers list.
     */
    private void updateOrganizersList() {
        this.organizersJList.setModel(new ModelListSelectable(controller.getOrganizersList()));
    }

    /**
     * Starting method for testing purposes, later will be removed.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ExhibitionCenter ec = new ExhibitionCenter();
        ExhibitionsManager em = new ExhibitionsManager();

        List<User> lu = new ArrayList<>();
        lu.add(new User("Daniel", "daniell", "email@dd23", "password", new ArrayList<>(), ""));
        lu.add(new User("Fabio", "fabioA", "email@dd24", "password", new ArrayList<>(), ""));
        lu.add(new User("Andre", "andree", "email@dd25", "password", new ArrayList<>(), ""));
        lu.add(new User("Jonas", "pistolas", "email@dd26", "password", new ArrayList<>(), ""));
        ec.setUsersRegister(new UsersRegister(lu));

        new CreateExhibitionUI(ec, em);
    }
}
