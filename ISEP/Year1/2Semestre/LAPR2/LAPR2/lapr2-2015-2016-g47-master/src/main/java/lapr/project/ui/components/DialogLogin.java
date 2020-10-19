/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lapr.project.controller.LoginController;
import lapr.project.model.Actor;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Organizer;
import lapr.project.model.StaffMember;
import lapr.project.model.User;
import lapr.project.ui.DashboardUI;
import lapr.project.ui.LoginUI;
import lapr.project.utils.DefaultInstantiator;

/**
 * Represents a dialog to login as.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DialogLogin extends JDialog {

    /**
     * The parent JFrame.
     */
    private final JFrame parentFrame;

    /**
     * The user.
     */
    private final User user;

    /**
     * The ok button.
     */
    private JButton okButton;

    /**
     * The cancel button.
     */
    private JButton cancelButton;

    /**
     * Window title.
     */
    private static final String WINDOW_TITLE = "Login";

    /**
     * The exhibitionsCenter.
     */
    private final ExhibitionCenter exhibitionsCenter;

    /**
     * The login controller.
     */
    private final LoginController loginController;
    /**
     * This instance's Actor.
     */
    private Actor thisActor;

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The login combo box.
     */
    private JComboBox comboBoxLogin;

    /**
     * Creates an instance of DialogLogin
     *
     * @param parentFrame
     * @param user
     * @param exhibitionsCenter
     */
    public DialogLogin(JFrame parentFrame, User user, ExhibitionCenter exhibitionsCenter) {
        super(parentFrame, WINDOW_TITLE, true);
        this.parentFrame = parentFrame;
        this.user = user;
        this.exhibitionsCenter = exhibitionsCenter;
        this.loginController = new LoginController(this.exhibitionsCenter);
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
        componentsPanel.add(createComboPanel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the top label with the text to choose a type of user to login.
     *
     * @return string of JLabel
     */
    private JLabel createChooseTextLabel() {
        return new JLabel("Login as: ", SwingConstants.CENTER);
    }

    /**
     * Creates the panel with the ComboBox to choose a type of user to login.
     *
     * @return returns comboPanel
     */
    private JPanel createComboPanel() {
        JPanel comboPanel = new JPanel(new BorderLayout(0, 10));
        comboBoxLogin = createComboBox();
        comboPanel.add(comboBoxLogin);
        return comboPanel;
    }

    /**
     * Create the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        buttonsPanel.add(createOkButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the ok button.
     *
     * @return ok button
     */
    private JButton createOkButton() {
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    switch (comboBoxLogin.getSelectedItem().toString()) {

                        case "Staff Member":
                            new DashboardUI(exhibitionsCenter, new StaffMember(user));
                            break;
                        case "Organizer":
                            new DashboardUI(exhibitionsCenter, new Organizer(user));

                            break;
                        case "Exhibitor Responsible":
                            new DashboardUI(exhibitionsCenter, new ExhibitorResponsible(user));
                            break;
                        case "Exhibitions Manager":
                            new DashboardUI(exhibitionsCenter, new ExhibitionsManager(user));
                            break;

                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(rootPane,"You cant login if you dont have a role on the system.","Error!",JOptionPane.ERROR_MESSAGE);
                    new LoginUI(exhibitionsCenter);
                }
                parentFrame.dispose();
                dispose();
            }
        });

        return okButton;
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

    /**
     * Creates the combo box
     *
     * @return the combo box
     */
    private JComboBox createComboBox() {
        List<String> userPossibilities = new ArrayList();
        if (loginController.verifyUserByStaffMember(user)) {
            userPossibilities.add("Staff Member");
        }
        if (loginController.verifyUserByOrganizer(user)) {
            userPossibilities.add("Organizer");
        }
//        if (loginController.verifyUserByExhibitorResponsible(user)) {
            userPossibilities.add("Exhibitor Responsible");
//        }

        if (loginController.verifyUserByExhibitionsManager(user)) {
            userPossibilities.add("Exhibitions Manager");
        }
        JComboBox<String> usersPossibilitiesListJcomboBox = new JComboBox<>();
        for (String string : userPossibilities) {
            usersPossibilitiesListJcomboBox.addItem(string);
        }
        return usersPossibilitiesListJcomboBox;
    }

    //Main to test dialog
    public static void main(String[] args) {
        ExhibitionCenter ex = DefaultInstantiator.createExhibitionCenter();
        DialogLogin dialog = new DialogLogin(null, ex.getExhibitionsRegister().getExhibitionsList().get(0).getStaffList().getStaffList().get(0).getUser(), ex);
    }
}
