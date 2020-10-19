/**
 * Package location for UI concepts.
 */
package lapr.project.ui;

import javax.swing.JFrame;
import lapr.project.controller.UpdateUserProfileController;
import lapr.project.model.Actor;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.User;
import lapr.project.utils.DefaultInstantiator;

/**
 * Represents a update user profile.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class UpdateUserProfileUI extends JFrame {

    /**
     * The update user profile controller.
     */
    private UpdateUserProfileController userProfileController;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The user.
     */
    private User user;
    /**
     * The actor.
     */
    private Actor actor;

    /**
     * builds instance of this class
     *
     * @param exhibitionCenter
     * @param user
     */
    public UpdateUserProfileUI(ExhibitionCenter exhibitionCenter, Actor user) {
        this.actor=user;
        this.exhibitionCenter = exhibitionCenter;
        this.userProfileController = new UpdateUserProfileController(this.exhibitionCenter);
        this.user = actor.getUser();
        setTitle("Update user profile");

        new CreateUserProfileUI(this.exhibitionCenter, actor);

    }

    public static void main(String[] args) {
        ExhibitionCenter ex = DefaultInstantiator.createExhibitionCenter();
        User u = new User();
        //new UpdateUserProfileUI(ex, actor);
    }

    /**
     * Create the UI components.
     */
//    private void createComponents() {
//
//        add(createSetUserDataPanel());
//        add(createUsersListAndButtonsPanel());
//    }
    /**
     * Create Panel with fields to set the user's data.
     *
     * @return Panel with fields to set the exhibition's data
     */
//    public JPanel createSetUserDataPanel() {
//
//        JLabel nameLabel = new JLabel("Name:", JLabel.RIGHT);
//        JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
//        JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
//        JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
//        JLabel confirmPasswordLabel = new JLabel("Confirm Password:", JLabel.RIGHT);
//        JLabel userCypherLabel = new JLabel("User Cypher:", JLabel.RIGHT);
//
//        textFieldName = new JTextField(FIELD_WIDTH);
//        textFieldUsername = new JTextField(FIELD_WIDTH);
//        textFieldEmail = new JTextField(FIELD_WIDTH);
//        passwordFieldPassword = new JPasswordField(FIELD_WIDTH);
//        passwordFieldConfirmPassword = new JPasswordField(FIELD_WIDTH);
//        textFieldUserCypher = new JTextField(FIELD_WIDTH);
//
//        // Set the main panel
//        JPanel panel = new JPanel();
//        GroupLayout layout = new GroupLayout(panel);
//        panel.setLayout(layout);
//        layout.setAutoCreateContainerGaps(true);
//
//        // Align horizontally
//        layout.setHorizontalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addComponent(nameLabel)
//                        .addComponent(usernameLabel)
//                        .addComponent(emailLabel)
//                        .addComponent(passwordLabel)
//                        .addComponent(confirmPasswordLabel)
//                        .addComponent(userCypherLabel)
//                )
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addComponent(textFieldName)
//                        .addComponent(textFieldUsername)
//                        .addComponent(textFieldEmail)
//                        .addComponent(passwordFieldPassword)
//                        .addComponent(passwordFieldConfirmPassword)
//                        .addComponent(textFieldUserCypher)
//                )
//        );
//
//        // Align vertically
//        layout.setVerticalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(nameLabel)
//                        .addComponent(textFieldName))
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(usernameLabel)
//                        .addComponent(textFieldUsername)
//                )
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(emailLabel)
//                        .addComponent(textFieldEmail))
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(passwordLabel)
//                        .addComponent(passwordFieldPassword)
//                )
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(confirmPasswordLabel)
//                        .addComponent(passwordFieldConfirmPassword)
//                )
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(userCypherLabel)
//                        .addComponent(textFieldUserCypher)
//                )
//        );
//
//        return panel;
//
//    }
//
//    /**
//     * Create scroll panel for related users list.
//     *
//     * @return scroll panel for related users list
//     */
//    public JPanel createUserRelatedUsersPanel() {
//
//        JPanel panel = new JPanel(new BorderLayout());
//
//        JPanel listPanel = new JPanel(new GridLayout());
//        listPanel.setBorder(BorderFactory.createTitledBorder(PADDING_BORDER,
//                "Select Related Users:", TitledBorder.LEFT, TitledBorder.TOP));
//
//        ModelListSelectable usersModel = new ModelListSelectable(this.userProfileController.getUser(this.user.getName()).getRelatedUsers());
//        relatedUsersJList = new JList(usersModel);
//
//        relatedUsersJList.addListSelectionListener(new ListSelectionListener() {
//
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                removeRelatedUserButton.setEnabled(!relatedUsersJList.isSelectionEmpty());
//            }
//        });
//
//        JScrollPane scrollPane = new JScrollPane(relatedUsersJList);
//        scrollPane.setBorder(PADDING_BORDER);
//        listPanel.setMaximumSize(scrollPane.getMinimumSize());
//        listPanel.add(scrollPane);
//
//        panel.add(listPanel, BorderLayout.NORTH);
//        panel.add(createAddAndRemoveButtons(), BorderLayout.CENTER);
//
//        return panel;
//
//    }
//
//    /**
//     * Create panel with add and remove related users buttons.
//     *
//     * @return panel with add and remove related users buttons
//     */
//    private JPanel createAddAndRemoveButtons() {
//        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
//        panel.add(createAddRelatedUserButton());
//        panel.add(createRemoveRelatedUserButton());
//
//        return panel;
//    }
//
//    /**
//     * Create add related user button.
//     *
//     * @return add related user button
//     */
//    private JButton createAddRelatedUserButton() {
//
//        JButton addButton = new JButton("Add");
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    List<User> usersList = userProfileController.getUsersRegister().getUsersList();
//                    List<User> relatedUsers = controller.getUser().getRelatedUsers();
//                    for (User user : relatedUsers) {
//                        usersList.remove(user);
//                    }
//
//                    if (usersList.isEmpty()) {
//                        throw new IllegalArgumentException();
//                    }
//
//                    DialogSelectable dialogNewRelatedUser = new DialogSelectable(CreateUserProfileUI.this, usersList, "Select user from list:");
//                    User selectedUser = (User) dialogNewRelatedUser.getSelectedItem();
//
//                    if (selectedUser != null && !relatedUsers.contains(selectedUser)) {
//                        relatedUsers.add(selectedUser);
//                        controller.getUser().setRelatedUsers(relatedUsers);
//                        updateRelatedUsersList();
//                        String successMessage = "Related User added successfully.";
//                        String successTitle = "Related user added.";
//
//                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
//                    }
//
//                } catch (NullPointerException ex) {
//
//                } catch (IllegalArgumentException ex) {
//
//                    String warningMessage = "There is no more users to add";
//                    String warningTitle = "No more users available in the system";
//
//                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
//
//                } catch (Exception ex) {
//
//                    String warningMessage = "An error has occurred. Please try again.";
//                    String warningTitle = "Error 404";
//
//                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
//                }
//
//            }
//
//        });
//        return addButton;
//    }
//
//    /**
//     * Create remove related user button.
//     *
//     * @return remove related user button
//     */
//    private JButton createRemoveRelatedUserButton() {
//
//        removeRelatedUserButton = new JButton("Remove");
//        removeRelatedUserButton.setEnabled(false);
//        removeRelatedUserButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                try {
//
//                    int index = relatedUsersJList.getSelectedIndex();
//                    List<User> relatedUsers = controller.getUser().getRelatedUsers();
//                    User toRemove = relatedUsers.get(index);
//
//                    if (relatedUsers.remove(toRemove)) {
//                        controller.getUser().setRelatedUsers(relatedUsers);
//                        updateRelatedUsersList();
//                        String successMessage = "Related User removed successfully.";
//                        String successTitle = "Related user removed.";
//
//                        JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        throw new IllegalArgumentException();
//                    }
//                } catch (Exception ex) {
//
//                    String warningMessage = "An error has occurred. Please try again.";
//                    String warningTitle = "Error 404";
//
//                    JOptionPane.showMessageDialog(rootPane, warningMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
//                }
//
//                removeRelatedUserButton.setEnabled(!relatedUsersJList.isSelectionEmpty());
//            }
//        });
//        return removeRelatedUserButton;
//    }
//
//    /**
//     * Create panel with user list and confirmation buttons.
//     *
//     * @return panel with users list and confirmation buttons
//     */
//    private JPanel createUsersListAndButtonsPanel() {
//
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(PADDING_BORDER);
//
//        panel.add(createUserRelatedUsersPanel(), BorderLayout.NORTH);
//        panel.add(createConfirmButtons(), BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    /**
//     * Create panel with confirm and cancel buttons.
//     *
//     * @return panel with confirm and cancel buttons
//     */
//    private JPanel createConfirmButtons() {
//        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//        panel.add(createConfirmButton());
//        panel.add(createCancelButton());
//
//        return panel;
//    }
//
//    /**
//     * Create confirm new user button.
//     *
//     * @return confirm new user button
//     */
//    private JButton createConfirmButton() {
//
//        JButton confirmButton = new JButton("Confirm");
//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String name = textFieldName.getText();
//                    String username = textFieldUsername.getText();
//                    String email = textFieldEmail.getText();
//                    String password = passwordFieldPassword.getText();
//                    String passwordConfirm = passwordFieldConfirmPassword.getText();
//                    String userCypher = textFieldUserCypher.getText();
//
//                    if (!password.equals(passwordConfirm)) {
//                        throw new IllegalArgumentException("Passwords do not match");
//                    }
//
//                    if (controller.setUserData(name, username, email, password, userCypher)) {
//                        if (controller.registerUser()) {
//                            String successMessage = "User created with success";
//                            String successTitle = "Success";
//
//                            JOptionPane.showMessageDialog(rootPane, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
//
//                            textFieldName.setText("");
//                            textFieldUsername.setText("");
//                            textFieldEmail.setText("");
//                            passwordFieldPassword.setText("");
//                            passwordFieldConfirmPassword.setText("");
//                            textFieldUserCypher.setText("");
//
//                            controller.newUser();
//                            updateRelatedUsersList();
//
//                        } else {
//                            throw new Exception("repeated user");
//                        }
//                    } else {
//                        throw new Exception("Invalid user parameters");
//                    }
//                } catch (Exception ex) {
//                    String errorMessage = ex.getMessage();
//                    String errorTitle = "Error 404";
//
//                    JOptionPane.showMessageDialog(rootPane, errorMessage, errorTitle, JOptionPane.INFORMATION_MESSAGE);
//                }
//            }
//        });
//        return confirmButton;
//
//    }
//
//    /**
//     * Create confirm new user button.
//     *
//     * @return confirm new user button
//     */
//    private JButton createCancelButton() {
//
//        JButton confirmButton = new JButton("Cancel");
//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });
//        return confirmButton;
//
//    }
//
//    /**
//     * Refresh the users list.
//     */
//    private void updateRelatedUsersList() {
//        this.relatedUsersJList.setModel(new ModelListSelectable(controller.getUser().getRelatedUsers()));
//    }
}
