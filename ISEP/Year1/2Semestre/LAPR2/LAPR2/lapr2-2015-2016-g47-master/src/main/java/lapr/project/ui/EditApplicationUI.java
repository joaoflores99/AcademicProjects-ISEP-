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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lapr.project.controller.EditApplicationController;
import lapr.project.model.Demonstration;
import lapr.project.model.Editable;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitorResponsible;
import lapr.project.model.Keyword;
import lapr.project.model.Product;
import lapr.project.model.Submittable;
import lapr.project.model.application.ApplicationInSubmissionState;
import lapr.project.model.DemonstrationApplication;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.DialogSelectable;
import lapr.project.ui.components.ModelListSelectable;
import lapr.project.utils.DefaultInstantiator;

/**
 * Graphic user interface to edit applications.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class EditApplicationUI extends JFrame {

    /**
     * The edit application controller.
     */
    private final EditApplicationController controller;

    /**
     * The logged exhibitor responsible.
     */
    private final ExhibitorResponsible exhibitorResponsible;

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The submttables list.
     */
    private final List<Submittable> submittablesList;

    /**
     * The applicaiton to be edited.
     */
    private Editable editable;

    /**
     * The editable products list.
     */
    private List<Product> productsList;

    /**
     * The editable produtcts JList.
     */
    private JList<Product> productsJList;

    /**
     * The editable demonstrations list.
     */
    private List<Demonstration> demonstrationsList;

    /**
     * The editable demonstrations JList.
     */
    private JList<Demonstration> demonstrationsJList;

    /**
     * The list of avaliable demonstrations on the selected exhibition.
     */
    private List<Demonstration> avaliableDemonstrationsInExhibition;

    /**
     * The remove products button.
     */
    private JButton removeProductsButton;

    /**
     * The remove demonstrations button.
     */
    private JButton removeDemonstrationsButton;

    /**
     * The title JTextField.
     */
    private JTextField titleTextField;

    /**
     * The invitations number JTextField.
     */
    private JTextField invitationsNumberTextField;

    /**
     * The exhibitor area JTextField.
     */
    private JTextField exhibitorAreaTextField;

    /**
     * The keywords text area.
     */
    private JTextArea keywordsTextArea;

    /**
     * Title for the window.
     */
    private static final String WINDOW_TITLE = "Edit Application";

    /**
     * Window dimension.
     */
    private static final Dimension WINDOW_DIMEMNSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of edit application user interface.
     *
     * @param exhibitionCenter the exhibition center
     * @param exhibitorResponsible the exhibitor responsible logged in
     */
    public EditApplicationUI(ExhibitionCenter exhibitionCenter, ExhibitorResponsible exhibitorResponsible) {
        super(WINDOW_TITLE);

        this.exhibitionCenter = exhibitionCenter;
        this.exhibitorResponsible = exhibitorResponsible;
        this.controller = new EditApplicationController(this.exhibitionCenter, this.exhibitorResponsible);

        this.submittablesList = this.controller.getSubmittablesByExhibitorResponsible();

        final String chooseSubmittableText = "Which exhibition/demonstration you wish to edit the application?";
        DialogSelectable dialogSelectable = new DialogSelectable(this, this.submittablesList, chooseSubmittableText);
        Submittable selectedSubmittable = (Submittable) dialogSelectable.getSelectedItem();

        if (selectedSubmittable != null) {

            this.controller.setSubmittable(selectedSubmittable);
            this.editable = this.controller.cloneEditable();

            this.productsList = this.editable.getProductsList();
            if (this.editable instanceof ExhibitionApplication) {
                this.demonstrationsList = ((ExhibitionApplication) this.editable).getDemonstrationsList();
                this.avaliableDemonstrationsInExhibition = this.controller.getAvailableDemonstrationsInExhibition();
            }

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
            setSize(WINDOW_DIMEMNSION);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        else{
            dispose();
            new DashboardUI(exhibitionCenter, exhibitorResponsible);
        }
    }

    /**
     * Create the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        componentsPanel.add(createDataPanel());
        componentsPanel.add(createListsButtonsPanel());
        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the data panel.
     *
     * @return data panel
     */
    private JPanel createDataPanel() {
        JPanel dataPanel = new JPanel(new GridLayout(2, 1, 0, 10));

        dataPanel.add(createFieldsPanel());
        dataPanel.add(createKeywordsPanel());

        return dataPanel;
    }

    /**
     * Creates the fields panel.
     *
     * @return fields panel.
     */
    private JPanel createFieldsPanel() {
        JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        final int TEXT_COLS = 20;
        final int NUMBER_COLS = 10;

        JLabel titleLabel = new JLabel("Title:");
        this.titleTextField = new JTextField(this.editable.getTitle(), TEXT_COLS);
        titleLabel.setLabelFor(this.titleTextField);
        fieldsPanel.add(titleLabel);
        fieldsPanel.add(this.titleTextField);;

        JLabel invitationsNumberLabel = new JLabel("Number of Invitations:");
        this.invitationsNumberTextField = new JTextField(Integer.toString(this.editable.getNumberInvitations()), NUMBER_COLS);
        invitationsNumberLabel.setLabelFor(this.invitationsNumberTextField);
        fieldsPanel.add(invitationsNumberLabel);
        fieldsPanel.add(this.invitationsNumberTextField);

        if (this.editable instanceof ExhibitionApplication) {
            JLabel exhibitorAreaLabel = new JLabel("Exhibitor Area:");
            this.exhibitorAreaTextField = new JTextField(Float.toString(((ExhibitionApplication) this.editable).getExhibitorArea()), NUMBER_COLS);
            exhibitorAreaLabel.setLabelFor(exhibitorAreaTextField);
            fieldsPanel.add(exhibitorAreaLabel);
            fieldsPanel.add(exhibitorAreaTextField);
        }

        return fieldsPanel;
    }

    /**
     * Creates the keywords panel.
     *
     * @return keywords panel.
     */
    private JPanel createKeywordsPanel() {
        JPanel keywordsPanel = new JPanel(new BorderLayout(0, 10));

        String keywords = this.editable.getKeywordsCSV();

        JLabel keywordsLabel = new JLabel("Keywords (comma separated):", SwingConstants.CENTER);
        this.keywordsTextArea = new JTextArea(keywords, 3, 10);
        keywordsLabel.setLabelFor(this.keywordsTextArea);

        keywordsPanel.add(keywordsLabel, BorderLayout.NORTH);
        keywordsPanel.add(this.keywordsTextArea, BorderLayout.CENTER);

        return keywordsPanel;
    }

    /**
     * Creates the lists and buttons panel.
     *
     * @return lists and buttons panel
     */
    private JPanel createListsButtonsPanel() {
        JPanel listsButtonsPanel = new JPanel(new BorderLayout(0, 10));

        listsButtonsPanel.add(createListPanel(), BorderLayout.CENTER);
        listsButtonsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        return listsButtonsPanel;
    }

    /**
     * Creates the lists panel.
     *
     * @return lists panel
     */
    private JPanel createListPanel() {
        JPanel listsPanel;

        if (this.editable instanceof ExhibitionApplication) {
            listsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
            listsPanel.add(createProductsListSectionPanel());
            listsPanel.add(createDemonstrationsListSectionPanel());
        } else {
            listsPanel = new JPanel();
            listsPanel.add(createProductsListSectionPanel());
        }

        return listsPanel;
    }

    /**
     * Creates the products list section panel.
     *
     * @return products list section panel
     */
    private JPanel createProductsListSectionPanel() {
        JPanel productsListSectionPanel = new JPanel(new BorderLayout(10, 10));

        productsListSectionPanel.add(new JLabel("Products List:", SwingConstants.CENTER), BorderLayout.NORTH);
        productsListSectionPanel.add(createProductsListPanel(), BorderLayout.CENTER);
        productsListSectionPanel.add(createProductsButtonsPanel(), BorderLayout.SOUTH);

        Border border = new EtchedBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        productsListSectionPanel.setBorder(new CompoundBorder(border, margin));

        return productsListSectionPanel;
    }

    /**
     * Creates the products list panel.
     *
     * @return products list panel
     */
    private JPanel createProductsListPanel() {
        JPanel productsListPanel = new JPanel(new BorderLayout());

        this.productsJList = new JList();
        this.productsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.productsJList.setModel(new ModelListSelectable(this.productsList));

        this.productsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                EditApplicationUI.this.removeProductsButton.setEnabled(!EditApplicationUI.this.productsJList.isSelectionEmpty());
            }
        });

        JScrollPane productsJScrollPane = new JScrollPane(this.productsJList);
        productsListPanel.add(productsJScrollPane, BorderLayout.CENTER);

        return productsListPanel;
    }

    /**
     * Creates the producs buttons Panel.
     *
     * @return products buttons panel
     */
    private JPanel createProductsButtonsPanel() {
        JPanel productsButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        productsButtonsPanel.add(createProductAddButton());
        productsButtonsPanel.add(createProductRemoveButton());

        return productsButtonsPanel;
    }

    /**
     * Creates the add button for products.
     *
     * @return products add button
     */
    private JButton createProductAddButton() {
        JButton addProductsButton = new JButton("Add Product");

        addProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String productDesignation = JOptionPane.showInputDialog(EditApplicationUI.this,
                        "Insert the product designation:",
                        "Add Product",
                        JOptionPane.QUESTION_MESSAGE);

                if (productDesignation != null && productDesignation.length() > 1) {
                    Product product = new Product(productDesignation);
                    if (!EditApplicationUI.this.productsList.contains(product)) {
                        EditApplicationUI.this.productsList.add(product);
                        JOptionPane.showMessageDialog(EditApplicationUI.this,
                                "The product was successful insert!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        EditApplicationUI.this.updateProductsList();
                    } else {
                        JOptionPane.showMessageDialog(EditApplicationUI.this,
                                "The product already exists.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } else if (productDesignation != null) {
                    JOptionPane.showMessageDialog(EditApplicationUI.this,
                            "The product is invalid.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return addProductsButton;
    }

    /**
     * Creates the remove button for products.
     *
     * @return products remove button
     */
    private JButton createProductRemoveButton() {
        this.removeProductsButton = new JButton("Remove Product");
        this.removeProductsButton.setEnabled(false);

        this.removeProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Product product = EditApplicationUI.this.productsList
                        .get(EditApplicationUI.this.productsJList.getSelectedIndex());
                EditApplicationUI.this.productsList.remove(product);
                EditApplicationUI.this.updateProductsList();
                JOptionPane.showMessageDialog(EditApplicationUI.this,
                        "The product was successful removed!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });

        return this.removeProductsButton;
    }

    /**
     * Creates the demonstrations list section panel.
     *
     * @return demonstrations list section panel
     */
    private JPanel createDemonstrationsListSectionPanel() {
        JPanel demonsrationsListSectionPanel = new JPanel(new BorderLayout(0, 10));

        demonsrationsListSectionPanel.add(new JLabel("Demonstrations List:", SwingConstants.CENTER), BorderLayout.NORTH);
        demonsrationsListSectionPanel.add(createDemonstrationsListPanel(), BorderLayout.CENTER);
        demonsrationsListSectionPanel.add(createDemonstrationsButtonsPanel(), BorderLayout.SOUTH);

        Border border = new EtchedBorder();
        Border margin = new EmptyBorder(10, 10, 10, 10);
        demonsrationsListSectionPanel.setBorder(new CompoundBorder(border, margin));

        return demonsrationsListSectionPanel;
    }

    /**
     * Creates the demonstrations list panel.
     *
     * @return demonstrations list panel
     */
    private JPanel createDemonstrationsListPanel() {
        JPanel demonstrationsListPanel = new JPanel(new BorderLayout());

        this.demonstrationsJList = new JList();
        this.demonstrationsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.demonstrationsJList.setModel(new ModelListSelectable(this.demonstrationsList));

        this.demonstrationsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                EditApplicationUI.this.removeDemonstrationsButton.setEnabled(!EditApplicationUI.this.demonstrationsJList.isSelectionEmpty());
            }
        });

        JScrollPane demonstrationsJScrollPane = new JScrollPane(this.demonstrationsJList);
        demonstrationsListPanel.add(demonstrationsJScrollPane, BorderLayout.CENTER);

        return demonstrationsListPanel;
    }

    /**
     * Creates the demonstrations buttons panel.
     *
     * @return demonstrations buttons panel
     */
    private JPanel createDemonstrationsButtonsPanel() {
        JPanel demonstrationsButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        demonstrationsButtonsPanel.add(createDemonstrationAddButton());
        demonstrationsButtonsPanel.add(createDemonstrationRemoveButton());

        return demonstrationsButtonsPanel;
    }

    /**
     * Creates the add demonstration buton.
     *
     * @return add demonstration buton
     */
    private JButton createDemonstrationAddButton() {
        JButton addDemonstrationButton = new JButton("Add Demonstration");

        addDemonstrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Demonstration[] choices = new Demonstration[EditApplicationUI.this.avaliableDemonstrationsInExhibition.size()];
                boolean isChoicesEmpty = true;
                for (int i = 0; i < choices.length; i++) {
                    if (!EditApplicationUI.this.demonstrationsList
                            .contains(EditApplicationUI.this.avaliableDemonstrationsInExhibition.get(i))) {
                        choices[i] = EditApplicationUI.this.avaliableDemonstrationsInExhibition.get(i);
                        isChoicesEmpty = false;
                    }
                }
                if (isChoicesEmpty) {
                    JOptionPane.showMessageDialog(EditApplicationUI.this,
                            "There is no available demonstrations to add.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Demonstration demonstration = (Demonstration) JOptionPane.showInputDialog(
                            EditApplicationUI.this,
                            "Select an available demonstration to add:",
                            "Add Demonstration",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            choices,
                            choices[0]);

                    if (demonstration != null) {
                        EditApplicationUI.this.demonstrationsList.add(demonstration);
                        EditApplicationUI.this.updateDemonstrationsList();
                        JOptionPane.showMessageDialog(EditApplicationUI.this,
                                "The demonstration was successful added!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        return addDemonstrationButton;
    }

    /**
     * Creates the remove demonstration button.
     *
     * @return remove demonstration button
     */
    private JButton createDemonstrationRemoveButton() {
        this.removeDemonstrationsButton = new JButton("Remove Demonstration");
        this.removeDemonstrationsButton.setEnabled(false);

        this.removeDemonstrationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Demonstration demonstration = EditApplicationUI.this.demonstrationsList
                        .get(EditApplicationUI.this.demonstrationsJList.getSelectedIndex());
                EditApplicationUI.this.demonstrationsList.remove(demonstration);
                EditApplicationUI.this.updateDemonstrationsList();
                JOptionPane.showMessageDialog(EditApplicationUI.this,
                        "The demonstration was successful removed!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return this.removeDemonstrationsButton;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonsPanel.add(createSaveChangesButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the save changes button.
     *
     * @return save changes button
     */
    private JButton createSaveChangesButton() {
        JButton saveChangesButton = new JButton("Save Changes");

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    // Get the data
                    String title = EditApplicationUI.this.titleTextField.getText();
                    int invitationsNumber = Integer.parseInt(EditApplicationUI.this.invitationsNumberTextField.getText());
                    List<Keyword> keywords = Keyword.toKeywordsList(EditApplicationUI.this.keywordsTextArea.getText());

                    // Set the data
                    EditApplicationUI.this.editable.setTitle(title);
                    EditApplicationUI.this.editable.setNumberInvitations(invitationsNumber);
                    EditApplicationUI.this.editable.setKeywordsList(keywords);
                    EditApplicationUI.this.editable.setProductsList(EditApplicationUI.this.productsList);
                    if (EditApplicationUI.this.editable instanceof ExhibitionApplication) {
                        float exhibitorArea = Float.parseFloat(EditApplicationUI.this.exhibitorAreaTextField.getText());
                        ((ExhibitionApplication) EditApplicationUI.this.editable).setExhibitorArea(exhibitorArea);
                        ((ExhibitionApplication) EditApplicationUI.this.editable).setDemonstrationsList(EditApplicationUI.this.demonstrationsList);
                    }

                    // Save the editable
                    if (EditApplicationUI.this.controller.validate(EditApplicationUI.this.editable)) { //Rever validate da application
                        EditApplicationUI.this.controller.modifyEditable();

                        JOptionPane.showMessageDialog(EditApplicationUI.this,
                                String.format("The application was successfull edited!"),
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                        dispose();
                        new DashboardUI(exhibitionCenter, exhibitorResponsible);
                    } else {
                        JOptionPane.showMessageDialog(EditApplicationUI.this,
                                String.format("The values are invalid."),
                                "Invalid Data",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(EditApplicationUI.this,
                            String.format("The introduced values are invalid.%nPlease review your data."),
                            "Invalid Data",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return saveChangesButton;
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
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new DashboardUI(exhibitionCenter, exhibitorResponsible);
            }
        });

        return cancelButton;
    }

    /**
     * Refresh the products list.
     */
    private void updateProductsList() {
        this.productsJList.setModel(new ModelListSelectable(this.productsList));
    }

    /**
     * Refresh the demonstrations list.
     */
    private void updateDemonstrationsList() {
        this.demonstrationsJList.setModel(new ModelListSelectable(this.demonstrationsList));
    }

    /**
     * Starting method for testing purposes.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        ExhibitionCenter ec = DefaultInstantiator.createExhibitionCenter();
        ExhibitorResponsible er = ((ExhibitionApplication) ec.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0)).getExhibitor().getExhibitorResponsible();
        ((DemonstrationApplication) ec.getExhibitionsRegister().getExhibitionsList().get(0).getDemonstrationsList()
                .getDemonstrationsList().get(0).getApplicationsList().getApplicationsList().get(0)).getExhibitor().setExhibitorResponsible(er);
        ((DemonstrationApplication)ec.getExhibitionsRegister().getExhibitionsList().get(0).getDemonstrationsList().getDemonstrationsList().get(0).getApplicationsList().getApplicationsList().get(0))
                .setState(new ApplicationInSubmissionState(ec.getExhibitionsRegister().getExhibitionsList().get(0).getDemonstrationsList().getDemonstrationsList().get(0).getApplicationsList().getApplicationsList().get(0)));
        ((ExhibitionApplication) ec.getExhibitionsRegister().getExhibitionsList().get(0).getApplicationsList().getApplicationsList()
                .get(0)).setState(new ApplicationInSubmissionState(ec.getExhibitionsRegister().getExhibitionsList().get(0)
                .getApplicationsList().getApplicationsList().get(0)));
        new EditApplicationUI(ec, er);
    }

}
