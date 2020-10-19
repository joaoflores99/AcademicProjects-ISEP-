/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lapr.project.model.DemonstrationApplication;
import lapr.project.model.Evaluable;
import lapr.project.model.ExhibitionApplication;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Keyword;
import lapr.project.model.StaffMember;
import lapr.project.ui.EvaluateApplicationUI;
import lapr.project.utils.DefaultInstantiator;

/**
 * Dialog to see an application info.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DialogSeeApplication extends JDialog {

    /**
     * The evaluable.
     */
    private final Evaluable evaluable;

    /**
     * The window title.
     */
    private static final String WINDOW_TITLE = "Application";

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates a dialog to see an evaluable.
     *
     * @param evaluable evaluable to be shown
     * @param parentFrame parent's frame
     */
    public DialogSeeApplication(Evaluable evaluable, JFrame parentFrame) {
        super(parentFrame, WINDOW_TITLE, true);

        this.evaluable = evaluable;

        createComponents();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Creates the components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(createTitleAndKeywordsPanel(), BorderLayout.CENTER);
        componentsPanel.add(createInfoAndListsPanel(), BorderLayout.SOUTH);
        componentsPanel.setBorder(PADDING_BORDER);

        add(componentsPanel);
    }

    /**
     * Creates the title and keywords panel.
     *
     * @return title and keywords panel
     */
    private JPanel createTitleAndKeywordsPanel() {
        JPanel titlePanel = new JPanel(new BorderLayout(0, 10));

        titlePanel.add(new JLabel(this.evaluable.getTitle()), BorderLayout.NORTH);
        titlePanel.add(createKeywordsPanel(), BorderLayout.CENTER);

        return titlePanel;
    }

    /**
     * Creates the keywords panel.
     *
     * @return keywords panel
     */
    private JPanel createKeywordsPanel() {
        JPanel keywordsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        List<Keyword> keywordsList = evaluable.getKeywordsList();
        for (Keyword keyword : keywordsList) {
            keywordsPanel.add(new JLabel(keyword.getDescription()));
        }

        return keywordsPanel;
    }

    /**
     * Creates the indo and lists panel.
     *
     * @return info and lists panel
     */
    private JPanel createInfoAndListsPanel() {
        JPanel infoAndListsPanel = new JPanel(new BorderLayout(0, 10));

        infoAndListsPanel.add(createExhibitorPanel(), BorderLayout.NORTH);
        infoAndListsPanel.add(createAreaAndTablesPanel(), BorderLayout.CENTER);

        return infoAndListsPanel;
    }

    /**
     * Creates the exhibitor panel.
     *
     * @return exhibitor panel
     */
    private JPanel createExhibitorPanel() {
        JPanel exhibitorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        JLabel CompanyNameLabel = new JLabel("Name: " + this.evaluable.getExhibitor().getName());
        JLabel CompanyPhoneLabel = new JLabel("Phone Number: " + this.evaluable.getExhibitor().getMobileNumber());
        JLabel CompanyAddress = new JLabel("Address: " + this.evaluable.getExhibitor().getAddress());
        exhibitorPanel.add(CompanyNameLabel);
        exhibitorPanel.add(CompanyPhoneLabel);
        exhibitorPanel.add(CompanyAddress);

        exhibitorPanel.setBorder(BorderFactory.createTitledBorder("Exhibitor"));

        return exhibitorPanel;
    }

    /**
     * Creates the area and lists panel.
     *
     * @return area and lists panel
     */
    private JPanel createAreaAndTablesPanel() {
        JPanel areaAndListsPanel = new JPanel(new BorderLayout(0, 10));

        areaAndListsPanel.add(createAreaAndInvitationsPanel());
        areaAndListsPanel.add(createTablesAndButtonsPanel());

        return areaAndListsPanel;
    }

    /**
     * Creates the area and invitations panel.
     *
     * @return area and invitations panel
     */
    private JPanel createAreaAndInvitationsPanel() {
        JPanel areaAndInvitationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        areaAndInvitationsPanel.add(new JLabel(String.format("Pretended area: %.2f", this.evaluable.getArea())));
        areaAndInvitationsPanel.add(new JLabel(String.format("Invitations number: %d", this.evaluable.getNumberInvitations())));

        return areaAndInvitationsPanel;
    }

    /**
     * Creates the lists panel.
     *
     * @return the lists panel
     */
    private JPanel createTablesAndButtonsPanel() {
        JPanel tablesAndButtonsLabel = new JPanel(new BorderLayout(0, 10));

        tablesAndButtonsLabel.add(createTablesLabel(), BorderLayout.CENTER);
        tablesAndButtonsLabel.add(createBackButton(), BorderLayout.SOUTH);

        return tablesAndButtonsLabel;
    }

    /**
     * Creates the tables label.
     *
     * @return tables label
     */
    private JPanel createTablesLabel() {
        JPanel tablesPanel;

        if (this.evaluable instanceof ExhibitionApplication) {
            tablesPanel = new JPanel(new GridLayout(1, 2, 10, 0));
            tablesPanel.add(createProductsLabel());
            tablesPanel.add(createDemonstrationsLabel());
        } else {
            tablesPanel = new JPanel(new BorderLayout());
            tablesPanel.add(createProductsLabel());
        }

        return tablesPanel;
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
            }
        });

        return backButton;
    }

    /**
     * Creates the products label.
     *
     * @return products label
     */
    private JPanel createProductsLabel() {
        JPanel productsPanel = new JPanel(new BorderLayout(0, 5));

        JLabel titleLabel = new JLabel("Products:", SwingConstants.CENTER);
        JList productsJList = new JList();

        productsJList.setModel(new ModelListSelectable(this.evaluable.getProductsList()));
        JScrollPane productsJScroll = new JScrollPane(productsJList);

        productsPanel.add(titleLabel, BorderLayout.NORTH);
        productsPanel.add(productsJScroll, BorderLayout.CENTER);

        return productsPanel;
    }

    /**
     * Creates the demonstrations label.
     *
     * @return demonstrations label
     */
    private JPanel createDemonstrationsLabel() {
        JPanel demonstrationsPanel = new JPanel(new BorderLayout(0, 5));

        JLabel titleLabel = new JLabel("Demonstrations:", SwingConstants.CENTER);
        
        JList demonstrationsJList = new JList();
        demonstrationsJList.setModel(new ModelListSelectable(((ExhibitionApplication) this.evaluable).getDemonstrationsList()));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) demonstrationsJList.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        JScrollPane demonstrationsJScroll = new JScrollPane(demonstrationsJList);

        demonstrationsPanel.add(titleLabel, BorderLayout.NORTH);
        demonstrationsPanel.add(demonstrationsJScroll, BorderLayout.CENTER);

        return demonstrationsPanel;
    }

    /**
     * This method is for testing purposes, later will be removed.
     *
     * @param args arguments of the command line
     */
    public static void main(String[] args) {
        ExhibitionCenter exhibitionCenter = DefaultInstantiator.createExhibitionCenter();
        ExhibitionApplication exhibitionApplication = (ExhibitionApplication)exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getApplicationsList().getApplicationsList().get(0);
        DemonstrationApplication demonstrationApplication = (DemonstrationApplication)exhibitionCenter.getExhibitionsRegister()
                .getExhibitionsList().get(0).getDemonstrationsList().getDemonstrationsList()
                .get(0).getApplicationsList().getApplicationsList().get(0);
        new DialogSeeApplication(exhibitionApplication, new EvaluateApplicationUI(new ExhibitionCenter(), new StaffMember()));
        new DialogSeeApplication(demonstrationApplication, new EvaluateApplicationUI(new ExhibitionCenter(), new StaffMember()));
    }
}
