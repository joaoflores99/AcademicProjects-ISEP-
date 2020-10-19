/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.GroupLayout;
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
import lapr.project.controller.DefineEffectiveDemosController;
import lapr.project.model.Demonstration;

/**
 * Represents a dialog to define a effective demonstration.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DialogEffectiveDemo extends JDialog {

    /**
     * The controller to define effective demonstrations.
     */
    private final DefineEffectiveDemosController controller;

    /**
     * The Demonstration list.
     */
    private final List<Demonstration> demonstrationsList;

    /**
     * Start Date Picker.
     */
    private JDateChooser startDatePicker;

    /**
     * End Date Picker.
     */
    private JDateChooser endDatePicker;

    /**
     * Placeholder date.
     */
    private static final Date PLACEHOLDER_DATE = new Date();

    /**
     * The Demonstrations JList.
     */
    private JList demonstationsJList;

    /**
     * The select button.
     */
    private JButton selectButton;

    /**
     * Text to be shown on the top of the list.
     */
    private final String chooseText;

    /**
     * Window title.
     */
    private static final String WINDOW_TITLE = "Choose from list";

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of choose effective demo dialog.
     *
     * @param parentFrame parent JFrame
     * @param controller Define effective demonstrations controller
     * @param demonstrationsList the list of demonstrations
     * @param chooseText text to be shown on the top of the list
     */
    public DialogEffectiveDemo(JFrame parentFrame, DefineEffectiveDemosController controller, List<Demonstration> demonstrationsList, String chooseText) {
        super(parentFrame, WINDOW_TITLE, true);

        this.demonstrationsList = demonstrationsList;
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
        componentsPanel.add(createListPanel(), BorderLayout.CENTER);
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
     * Creates the list panel
     *
     * @return list panel
     */
    private Component createListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());

        this.demonstationsJList = new JList();
        this.demonstationsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.demonstationsJList.setModel(new ModelListSelectable(this.demonstrationsList));

        this.demonstationsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                DialogEffectiveDemo.this.selectButton.setEnabled(!DialogEffectiveDemo.this.demonstationsJList.isSelectionEmpty());
            }
        });

        JScrollPane jScrollPane = new JScrollPane(this.demonstationsJList);
        listPanel.add(jScrollPane, BorderLayout.NORTH);

        JLabel startDateLbl = new JLabel("Start Date:", JLabel.RIGHT);
        JLabel endDateLbl = new JLabel("End Date:", JLabel.RIGHT);
        startDatePicker = new JDateChooser(PLACEHOLDER_DATE);
        endDatePicker = new JDateChooser(PLACEHOLDER_DATE);

        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        startDatePanel.add(startDatePicker);
        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        endDatePanel.add(endDatePicker);

        // Set main panel
        JPanel datePanel = new JPanel();
        GroupLayout layout = new GroupLayout(datePanel);
        datePanel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);

        // Align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(startDateLbl)
                        .addComponent(endDateLbl)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(startDatePanel)
                        .addComponent(endDatePanel)
                )
        );
        // Align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(startDateLbl)
                        .addComponent(startDatePanel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(endDateLbl)
                        .addComponent(endDatePanel)
                )
        );
        listPanel.add(datePanel, BorderLayout.CENTER);

        return listPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private Component createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createSelectButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the select button.
     *
     * @return select button
     */
    private JButton createSelectButton() {
        this.selectButton = new JButton("Select");
        this.selectButton.setEnabled(false);

        this.selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Demonstration selectedDemo = (Demonstration) DialogEffectiveDemo.this.demonstrationsList
                        .get(DialogEffectiveDemo.this.demonstationsJList.getSelectedIndex());

                controller.setEfectiveDemonstration(selectedDemo);
                try {

                    if (!controller.setDemonstrationDates(startDatePicker.getDate(), endDatePicker.getDate())) {
                        throw new IllegalArgumentException("Invalid Dates! Please select new dates.");
                    }
                    if (!controller.updateDemonstration()) {
                        throw new IllegalArgumentException("Something wen't wrong please try again");
                    }

                    String successMessage = "Effective Demonstration added successfully!";
                    String successTitle = "Effective Demonstration added.";
                    JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } catch (IllegalArgumentException ex) {
                    String warningTitle = "ERROR";
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), warningTitle, JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.rootPane.setDefaultButton(selectButton);

        return this.selectButton;
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
}
