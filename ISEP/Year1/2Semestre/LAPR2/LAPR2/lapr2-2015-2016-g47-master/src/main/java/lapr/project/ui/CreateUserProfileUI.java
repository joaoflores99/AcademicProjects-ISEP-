/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.CreateUserProfileController;
import lapr.project.model.Actor;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.User;
import lapr.project.model.UsersRegister;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;

/**
 * Graphic user interface to create user profile.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CreateUserProfileUI extends JFrame {

    /**
     * The create user profile controller.
     */
    private final CreateUserProfileController controller;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The user.
     */
    private User user;

    /**
     * The users register.
     */
    private UsersRegister usersRegister;

    /**
     * The users list
     */
    private List<User> usersList;

    /**
     * Name textfield component.
     */
    private JTextField textFieldName;

    /**
     * Username textfield component.
     */
    private JTextField textFieldUsername;

    /**
     * Email textfield component.
     */
    private JTextField textFieldEmail;

    /**
     * Password passwordfield component.
     */
    private JPasswordField passwordFieldPassword;

    /**
     * ConfirmPassword passwordfield component.
     */
    private JPasswordField passwordFieldConfirmPassword;

    /**
     * UserCypher textfield component.
     */
    private JTextField textFieldUserCypher;

    /**
     * Users JList component.
     */
    private JList relatedUsersJList;

    /**
     * Remove a related user button.
     */
    private JButton removeRelatedUserButton;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Create user";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(800, 350);

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
     * A boolean to differ between changing a user and creating one.
     */
    boolean changeUser = false;
    /**
     * The actor user.
     */
    Actor actorUser;

    /**
     * Constructs of a Create User Profile UI Class.
     *
     * @param exhibitionCenter the exhibition center
     */
    public CreateUserProfileUI(ExhibitionCenter exhibitionCenter, Actor user) {

        super(WINDOW_TITLE);
        this.actorUser = user;

        this.exhibitionCenter = exhibitionCenter;
        this.controller = new CreateUserProfileController(exhibitionCenter);

        // Create a new user
        if (user == null) {
            this.controller.newUser();
        } else {
            this.user = user.getUser();
            this.controller.setUser(this.user);
            changeUser = true;
            setTitle("Change user data");
        }

        setLayout(new GridLayout(1, 2));
        createComponents();
        CustomMenuBar customMenuBar = new CustomMenuBar(exhibitionCenter, this);
        setJMenuBar(customMenuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                customMenuBar.exit();
            }
        });

        pack();
        setSize(WINDOW_DIMENSION);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Create the UI components.
     */
    private void createComponents() {

        add(createSetUserDataPanel());
        add(createUsersListAndButtonsPanel());
    }

    /**
     * Create Panel with fields to set the user's data.
     *
     * @return Panel with fields to set the exhibition's data
     */
    public JPanel createSetUserDataPanel() {

        JLabel nameLabel = new JLabel("Name:", JLabel.RIGHT);
        JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
        JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
        JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:", JLabel.RIGHT);
        JLabel userCypherLabel = new JLabel("User Cypher:", JLabel.RIGHT);

        textFieldName = new JTextField(FIELD_WIDTH);
        textFieldUsername = new JTextField(FIELD_WIDTH);
        textFieldEmail = new JTextField(FIELD_WIDTH);
        passwordFieldPassword = new JPasswordField(FIELD_WIDTH);
        passwordFieldConfirmPassword = new JPasswordField(FIELD_WIDTH);
        textFieldUserCypher = new JTextField(FIELD_WIDTH);
        if(changeUser){
            getUserInfo();
        }
        // Set the main panel
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);

        // Align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(usernameLabel)
                        .addComponent(emailLabel)
                        .addComponent(passwordLabel)
                        .addComponent(confirmPasswordLabel)
                        .addComponent(userCypherLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFieldName)
                        .addComponent(textFieldUsername)
                        .addComponent(textFieldEmail)
                        .addComponent(passwordFieldPassword)
                        .addComponent(passwordFieldConfirmPassword)
                        .addComponent(textFieldUserCypher)
                )
        );

        // Align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(textFieldName))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(usernameLabel)
                        .addComponent(textFieldUsername)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(textFieldEmail))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordFieldPassword)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmPasswordLabel)
                        .addComponent(passwordFieldConfirmPassword)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(userCypherLabel)
                        .addComponent(textFieldUserCypher)
                )
        );

        return panel;

    }

    /**
     * Gets the user info into the textboxes.
     */
    private void getUserInfo() {
        textFieldName.setText(user.getName());
        textFieldEmail.setText(user.getEmail());
        textFieldUsername.setText(user.getUsername());
        
    }

    /**
     * Create scroll panel for related users list.
     *
     * @return scroll panel for related users list
     */
    public JPanel createUserRelatedUsersPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel listPanel = new JPanel(new GridLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
                "Select Related Users:", TitledBorder.LEFT, TitledBorder.TOP));

        ModelListSelectable usersModel = new ModelListSelectable(controller.getUser().getRelatedUsers());
        relatedUsersJList = new JList(usersModel);

        relatedUsersJList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                removeRelatedUserButton.setEnabled(!relatedUsersJList.isSelectionEmpty());
            }
        });

        JScrollPane scrollPane = new JScrollPane(relatedUsersJList);
        scrollPane.setBorder(PADDING_BORDER);
        listPanel.setMaximumSize(scrollPane.getMinimumSize());
        listPanel.add(scrollPane);

        panel.add(listPanel, BorderLayout.NORTH);
        panel.add(createAddAndRemoveButtons(), BorderLayout.CENTER);

        return panel;

    }

    /**
     * Create panel with add and remove related users buttons.
     *
     * @return panel with add and remove related users buttons
     */
    private JPanel createAddAndRemoveButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.add(createAddRelatedUserButton());
        panel.add(createRemoveRelatedUserButton());

        return panel;
    }

    /**
     * Create add related user button.
     *
     * @return add related user button
     */
    private JButton createAddRelatedUserButton() {

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<User> usersList = controller.getUsersRegister().getUsersList();
                    List<User> relatedUsers = controller.getUser().getRelatedUsers();
                    for (User user : relatedUsers) {
                        usersList.remove(user);
                    }

                    if (usersList.isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    DialogSelectable dialogNewRelatedUser = new DialogSelectable(CreateUserProfileUI.this, usersList, "Select user from list:");
                    User selectedUser = (User) dialogNewRelatedUser.getSelectedItem();

                    if (selectedUser != null && !relatedUsers.contains(selectedUser)) {
                        relatedUsers.add(selectedUser);
                        controller.getUser().setRelatedUsers(relatedUsers);
                        updateRelatedUsersList();
                        String successMessage = "Related User added successfully.";
                        String successTitle = "Related user added.";

                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (NullPointerException ex) {

                } catch (IllegalArgumentException ex) {

                    String warningMessage = "There is no more users to add";
                    String warningTitle = "No more users available in the system";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);

                } catch (Exception ex) {

                    String warningMessage = "An error has occurred. Please try again.";
                    String warningTitle = "Error 404";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
                }

            }

        });
        return addButton;
    }

    /**
     * Create remove related user button.
     *
     * @return remove related user button
     */
    private JButton createRemoveRelatedUserButton() {

        removeRelatedUserButton = new JButton("Remove");
        removeRelatedUserButton.setEnabled(false);
        removeRelatedUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int index = relatedUsersJList.getSelectedIndex();
                    List<User> relatedUsers = controller.getUser().getRelatedUsers();
                    User toRemove = relatedUsers.get(index);

                    if (relatedUsers.remove(toRemove)) {
                        controller.getUser().setRelatedUsers(relatedUsers);
                        updateRelatedUsersList();
                        String successMessage = "Related User removed successfully.";
                        String successTitle = "Related user removed.";

                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (Exception ex) {

                    String warningMessage = "An error has occurred. Please try again.";
                    String warningTitle = "Error 404";

                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
                }

                removeRelatedUserButton.setEnabled(!relatedUsersJList.isSelectionEmpty());
            }
        });
        return removeRelatedUserButton;
    }

    /**
     * Create panel with user list and confirmation buttons.
     *
     * @return panel with users list and confirmation buttons
     */
    private JPanel createUsersListAndButtonsPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(PADDING_BORDER);

        panel.add(createUserRelatedUsersPanel(), BorderLayout.NORTH);
        panel.add(createConfirmButtons(), BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Create panel with confirm and cancel buttons.
     *
     * @return panel with confirm and cancel buttons
     */
    private JPanel createConfirmButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panel.add(createConfirmButton());
        panel.add(createCancelButton());

        return panel;
    }

    /**
     * Create confirm new user button.
     *
     * @return confirm new user button
     */
    private JButton createConfirmButton() {

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = textFieldName.getText();
                    String username = textFieldUsername.getText();
                    String email = textFieldEmail.getText();
                    String password = passwordFieldPassword.getText();
                    String passwordConfirm = passwordFieldConfirmPassword.getText();
                    String userCypher = textFieldUserCypher.getText();
                     Pattern pattern =Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@+])(?=\\S+$).{5,}$");
                     Matcher matcher = pattern.matcher(password);
                    if (!password.equals(passwordConfirm)) {
                        throw new IllegalArgumentException("Passwords do not match");
                    }
                    if(!matcher.find()){
                           throw new IllegalArgumentException("Passwords must have an upper case letter, a lower case letter, a number, a special character (@ or +) and at least 5 in length.");
                    }
                    if (controller.setUserData(name, username, email, password, userCypher)) {
                        if (controller.registerUser() && !changeUser) {

                            String successMessage = "User created with success";
                            String successTitle = "Success";

                            JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);

                            textFieldName.setText("");
                            textFieldUsername.setText("");
                            textFieldEmail.setText("");
                            passwordFieldPassword.setText("");
                            passwordFieldConfirmPassword.setText("");
                            textFieldUserCypher.setText("");

                            controller.newUser();
                            updateRelatedUsersList();
                            new LoginUI(exhibitionCenter);
                            dispose();

                        } else {
                            if (changeUser) {
                                String successMessage = "User information updated with sucess";
                                String successTitle = "Success";

                                JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);

                                textFieldName.setText("");
                                textFieldUsername.setText("");
                                textFieldEmail.setText("");
                                passwordFieldPassword.setText("");
                                passwordFieldConfirmPassword.setText("");
                                textFieldUserCypher.setText("");

                                updateRelatedUsersList();
                                new DashboardUI(exhibitionCenter, actorUser);
                                dispose();
                            } else {
                                throw new Exception("repeated user");
                            }
                        }
                    } else {
                        throw new Exception("Invalid user parameters");
                    }
                } catch (Exception ex) {
                    String errorMessage = ex.getMessage();
                    String errorTitle = "Error 404";

                    JOptionPane.showMessageDialog(rootPane, errorMessage, errorTitle, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        return confirmButton;

    }

    /**
     * Create confirm new user button.
     *
     * @return confirm new user button
     */
    private JButton createCancelButton() {

        JButton confirmButton = new JButton("Cancel");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                if (changeUser) {
                    new DashboardUI(exhibitionCenter, actorUser);
                } else {
                    new LoginUI(exhibitionCenter);
                }
            }
        });
        return confirmButton;

    }

    /**
     * Refresh the users list.
     */
    private void updateRelatedUsersList() {
        this.relatedUsersJList.setModel(new ModelListSelectable(controller.getUser().getRelatedUsers()));
    }

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        ExhibitionCenter ec = new ExhibitionCenter();
        List<User> lu = new ArrayList<>();
        lu.add(new User("Daniel", "daniell", "email@dd23", "password", new ArrayList<>(), "3f3r34"));
        lu.add(new User("Fabio", "fabioA", "email@dd24", "password", new ArrayList<>(), "43f3fe"));
        lu.add(new User("Andre", "andree", "email@dd25", "password", new ArrayList<>(), "fe3f3f"));
        lu.add(new User("Jonas", "pistolas", "email@dd26", "password", new ArrayList<>(), "f3ef4f"));
        ec.setUsersRegister(new UsersRegister(lu));
        new CreateUserProfileUI(ec, null);
    }

}
