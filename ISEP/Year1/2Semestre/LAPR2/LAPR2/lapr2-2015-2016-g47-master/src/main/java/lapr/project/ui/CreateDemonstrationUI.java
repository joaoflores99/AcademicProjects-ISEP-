/**
 * Package location for UI classes.
 */
package lapr.project.ui;

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
import lapr.project.controller.CreateDemonstrationController;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.model.OrganizersList;
import lapr.project.model.Place;
import lapr.project.model.Resource;
import lapr.project.model.User;
import lapr.project.model.exhibition.ExhibitionCreatedState;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to create demonstrations.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateDemonstrationUI extends JFrame {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The organizer logged in.
     */
    private final Organizer organizer;

    /**
     * The controller to create demonstrations.
     */
    private final CreateDemonstrationController controller;

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
     * Resources JList component.
     */
    private JList resourcesJList;

    /**
     * Remove a resource button.
     */
    private JButton removeResourceBtn;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Create Demonstration";

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
     * Constructor of a Create Demonstration UI Class.
     *
     * @param exhibitionCenter the exhibition center
     * @param organizer the organizer logged in
     */
    public CreateDemonstrationUI(ExhibitionCenter exhibitionCenter, Organizer organizer) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.organizer = organizer;
        this.controller = new CreateDemonstrationController(exhibitionCenter, organizer);

        final String chooseExhibitionText = "Which exhibition do you wish to insert a new demostration?";
        DialogSelectable dialogSelectable = new DialogSelectable(this,
                controller.getExhibitionsList(organizer), chooseExhibitionText);
        Exhibition selectedExhibition = (Exhibition) dialogSelectable.getSelectedItem();

        if (selectedExhibition != null) {

            this.controller.setSelectedExhibition(selectedExhibition);

            // Create a new demonstration
            this.controller.newDemonstration();

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
    }

    /**
     * Create the UI components.
     */
    private void createComponents() {

        add(createSetDataPanel(), BorderLayout.NORTH);
        add(createResourcesListAndButtonsPanel(), BorderLayout.CENTER);
    }

    /**
     * Create Panel with fields to set demonstration's data.
     *
     * @return Panel with fields to set exhibition's data
     */
    public JPanel createSetDataPanel() {

        JLabel selectedExhibitionLbl = new JLabel("Selected Exhibition: ", JLabel.RIGHT);
        JLabel titleLbl = new JLabel("Title:", JLabel.RIGHT);
        JLabel descriptionLbl = new JLabel("Description:", JLabel.RIGHT);
        JLabel placeLbl = new JLabel("Location:", JLabel.RIGHT);

        JLabel exhibitionLbl = new JLabel(controller.getSelectedExhibition().getTitle(), JLabel.RIGHT);
        Font font = exhibitionLbl.getFont();
        exhibitionLbl.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
        txtFieldTitle = new JTextField(FIELD_WIDTH);
        txtFieldDescription = new JTextField(FIELD_WIDTH);
        txtFieldPlace = new JTextField(FIELD_WIDTH);

        // Set main panel
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);

        // Align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(selectedExhibitionLbl)
                        .addComponent(titleLbl)
                        .addComponent(descriptionLbl)
                        .addComponent(placeLbl)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(exhibitionLbl)
                        .addComponent(txtFieldTitle)
                        .addComponent(txtFieldDescription)
                        .addComponent(txtFieldPlace)
                )
        );

        // Align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectedExhibitionLbl)
                        .addComponent(exhibitionLbl))
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
        );

        return panel;
    }

    /**
     * Create scroll panel for resources list.
     *
     * @return scroll panel for resources list
     */
    public JPanel createResourcesPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel listPanel = new JPanel(new GridLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Select Resources:", TitledBorder.LEFT, TitledBorder.TOP));

        ModelListSelectable resourcesModel = new ModelListSelectable(controller.getDemonstration().getResourcesList());
        resourcesJList = new JList(resourcesModel);

        resourcesJList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                removeResourceBtn.setEnabled(!resourcesJList.isSelectionEmpty());
            }
        });

        JScrollPane scrollPane = new JScrollPane(resourcesJList);
        scrollPane.setBorder(PADDING_BORDER);
        listPanel.setMinimumSize(scrollPane.getMinimumSize());
        listPanel.add(scrollPane);

        panel.add(listPanel, BorderLayout.NORTH);
        panel.add(createAddAndRemoveButtons(), BorderLayout.CENTER);

        return panel;
    }

    /**
     * Create panel with add & remove resources buttons.
     *
     * @return panel with add & remove resources buttons
     */
    private JPanel createAddAndRemoveButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.add(createAddResourceButton());
        panel.add(createRemoveResourceButton());

        return panel;
    }

    /**
     * Create Add Resource Button.
     *
     * @return Add Resource Button
     */
    private JButton createAddResourceButton() {

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    List<Resource> resourcesList = controller.getResoucesList();

                    for (Resource resource : controller.getDemonstration().getResourcesList()) {
                        resourcesList.remove(resource);
                    }

                    if (resourcesList.isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    DialogSelectable dialogoNewResource = new DialogSelectable(CreateDemonstrationUI.this, resourcesList, "Select Resource from list:");
                    Resource selectedResource = (Resource) dialogoNewResource.getSelectedItem();
                    if (selectedResource == null) {
                        throw new NullPointerException();
                    }

                    if (controller.addResource(selectedResource)) {
                        updateResourcesList();
                        String successMessage = "Resource added successfully!";
                        String successTitle = "Resource added.";

                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NullPointerException ex) {

                } catch (IllegalArgumentException ex) {

                    String warningMessage = "There is no more resources to add";
                    String warningTitle = "No more resources in system";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);

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
    private JButton createRemoveResourceButton() {

        removeResourceBtn = new JButton("Remove");
        removeResourceBtn.setEnabled(false);
        removeResourceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int index = resourcesJList.getSelectedIndex();

                    if (controller.removeResource(index)) {
                        updateResourcesList();
                        String successMessage = "Resource removed successfully!";
                        String successTitle = "Resource removed.";

                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        throw new IllegalArgumentException();
                    }
                } catch (Exception ex) {

                    String warningMessage = "Something wen't wrong please try again.";
                    String warningTitle = "ERROR 404";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
                }

                removeResourceBtn.setEnabled(!resourcesJList.isSelectionEmpty());
            }
        });
        return removeResourceBtn;
    }

    /**
     * Create panel with resource list & confirmation buttons.
     *
     * @return panel with organizers list & confirmation buttons
     */
    private JPanel createResourcesListAndButtonsPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(PADDING_BORDER);

        panel.add(createResourcesPanel(), BorderLayout.NORTH);
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

                    String title = txtFieldTitle.getText();
                    String description = txtFieldDescription.getText();
                    Place place = new Place(description);
                    controller.setData(title, description, place);

                    boolean validAndNextStage = controller.validateAndChangeStateDemonstration();
                    if (!validAndNextStage) {
                        throw new IllegalArgumentException("Invalid Data, please verify");
                    }
                    String data = String.format("Title: %s%n"
                            + "Description: %s%n"
                            + "Place: %s%n",
                            controller.getDemonstration().getTitle(),
                            controller.getDemonstration().getDescription(),
                            controller.getDemonstration().getPlace().getLocation());

                    String question = String.format("%n%nPlease confirm the new demonstration?");

                    StringBuilder message = new StringBuilder();
                    message.append(String.format("New Demosntration%n%n"));
                    message.append(data);
                    message.append(String.format("%nResources:%n"));
                    for (Resource resource : controller.getDemonstration().getResourcesList()) {
                        message.append(String.format("%s%n", resource.getDesignation()));
                    }
                    message.append(question);

                    int confirma = JOptionPane.showConfirmDialog(rootPane, message);

                    if (confirma == JOptionPane.YES_OPTION && controller.registerDemonstration()) {
                        controller.setDemonstrationDefined();
                        
                        dispose();
                        new DashboardUI(CreateDemonstrationUI.this.exhibitionCenter, CreateDemonstrationUI.this.organizer);
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
                new DashboardUI(CreateDemonstrationUI.this.exhibitionCenter, CreateDemonstrationUI.this.organizer);
            }
        });
        return cancelBtn;
    }

    /**
     * Refresh the resources list.
     */
    private void updateResourcesList() {
        this.resourcesJList.setModel(new ModelListSelectable(controller.getDemonstration().getResourcesList()));
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

        Exhibition e1 = new Exhibition();
        e1.setState(new ExhibitionCreatedState(e1));
        e1.setOrganizersList(new OrganizersList(lo));

        Exhibition e2 = new Exhibition();
        e2.setState(new ExhibitionCreatedState(e2));
        e2.setOrganizersList(new OrganizersList(lo));

        List<Exhibition> le = new ArrayList<>();
        le.add(e1);
        le.add(e2);

        ec.setExhibitionsRegister(new ExhibitionsRegister(le));

        new CreateDemonstrationUI(ec, o);
    }
}
