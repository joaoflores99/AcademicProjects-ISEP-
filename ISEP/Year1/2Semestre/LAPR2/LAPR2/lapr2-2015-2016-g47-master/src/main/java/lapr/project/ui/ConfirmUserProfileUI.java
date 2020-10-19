/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lapr.project.controller.ConfirmUserProfileController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManager;
import lapr.project.model.User;
import lapr.project.ui.components.CustomMenuBar;
//import lapr.project.ui.components.DialogSeeUserProfile;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to create user profile.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ConfirmUserProfileUI extends JFrame {

    /**
     * The model for users
     */
    private ModelListSelectable modelUser;
    /**
     * The confirm user profile controller.
     */
    private final ConfirmUserProfileController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibitions manager.
     */
    private final ExhibitionsManager exhibitionsManager;

    /**
     * The unconfirmed users list.
     */
    private List<User> unconfirmedUserProfilesList;

    /**
     * Unconfirmed users JList.
     */
    private JList unconfirmedUserProfilesJList;

    /**
     * See user profile button.
     */
    private JButton seeUserProfileButton;

    /**
     * Confirm user profile button.
     */
    private JButton confirmUserProfileButton;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Confirm User Profile";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of confirm user profile user interface.
     *
     * @param exhibitionCenter the exhibition center
     * @param exhibitionsManager the exhibitions manager
     */
    public ConfirmUserProfileUI(ExhibitionCenter exhibitionCenter, ExhibitionsManager exhibitionsManager) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsManager = exhibitionsManager;
        this.controller = new ConfirmUserProfileController(this.exhibitionCenter);

        this.unconfirmedUserProfilesList = controller.getUnconfirmedUserList();

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
     * Creates the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));
        componentsPanel.add(createTitleLabel(), BorderLayout.NORTH);
        componentsPanel.add(createUserPanel());
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
        return new JLabel("Select an unconfirmed user to be confirmed:", SwingConstants.CENTER);
    }

    private JPanel createUserPanel() {
        modelUser = new ModelListSelectable(unconfirmedUserProfilesList);
        unconfirmedUserProfilesJList = new JList(modelUser);
        unconfirmedUserProfilesJList.setPreferredSize(new Dimension(250, 350));
        unconfirmedUserProfilesJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (unconfirmedUserProfilesJList.getSelectedValue()!=null) {
                    confirmUserProfileButton.setEnabled(true);
                }
            }
        });
        JPanel panel = new JPanel();
        panel.add(unconfirmedUserProfilesJList);
        return panel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createConfirmUserProfileButton());
        buttonsPanel.add(createBackButton());

        return buttonsPanel;
    }

    /**
     * Creates the confirm user profile button.
     *
     * @return confirm user profile button
     */
    private JButton createConfirmUserProfileButton() {
        this.confirmUserProfileButton = new JButton("Confirm user profile");
        this.confirmUserProfileButton.setEnabled(false);

        this.confirmUserProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String[] options = {"Yes, confirm user profile", "No, discard user profile"};

                int userProfileDecision = JOptionPane.showConfirmDialog(ConfirmUserProfileUI.this,
                        "Do you with to confirm the current user profile?",
                        "Confirm user profile",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (userProfileDecision == 0) { //accepted user profile
                    ConfirmUserProfileUI.this.controller.confirmUser((User) modelUser.getObject(unconfirmedUserProfilesJList.getSelectedIndex()));
                    ConfirmUserProfileUI.this.updateUnconfirmedUserProfiles();
                    JOptionPane.showMessageDialog(rootPane, "User confirmed with sucess", "Sucess", JOptionPane.PLAIN_MESSAGE);
                } else if (userProfileDecision == 1) { //declined user profile
                    ConfirmUserProfileUI.this.updateUnconfirmedUserProfiles();
                }
            }
        });

        return this.confirmUserProfileButton;
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
                new DashboardUI(ConfirmUserProfileUI.this.exhibitionCenter, ConfirmUserProfileUI.this.exhibitionsManager);
            }
        });

        return backButton;
    }

    /**
     * Refresh the unconfirmed users list.
     */
    private void updateUnconfirmedUserProfiles() {

        this.unconfirmedUserProfilesList = controller.getUnconfirmedUserList();
        modelUser = new ModelListSelectable(unconfirmedUserProfilesList);
        this.unconfirmedUserProfilesJList.setModel(modelUser);
    }

    /**
     * main() for testing purposes.
     *
     * @param args
     */
    public static void main(String[] args) {
        ExhibitionCenter ex = DefaultInstantiator.createExhibitionCenter();
        ExhibitionsManager em = ex.getExhibitionsManagerRegister().getExhibitionsManagerList().get(0);
        ex.getUsersRegister().getUsersList().add(new User());
        new ConfirmUserProfileUI(ex, em);
    }

}
