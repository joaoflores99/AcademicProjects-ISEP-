/**
 * Package location for AssignStandsController concepts.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lapr.project.controller.GenerateEvaluationsStatisticsController;
import lapr.project.model.Actor;
import lapr.project.model.ApplicationAnalysis;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;
import lapr.project.utils.CSVParser;

/**
 * Represents a panel with the generate keywords rankings.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class GenerateEvaluationsStatisticsPanel extends JPanel {

    /**
     * The controller to generate statistics.
     */
    private final GenerateEvaluationsStatisticsController controller;

    /**
     * The exhibitions list.
     */
    private final List<Exhibition> exhibitionsList;

    /**
     * Exhibitions JComboBox.
     */
    private JComboBox exhibitionsJComboBox;

    /**
     * Acceptance rate JLabel.
     */
    private JLabel acceptanceRateJLabel;

    /**
     * List with the application analysis.
     */
    private List<ApplicationAnalysis> applicationAnalysisesList;

    /**
     * The acceptance rate.
     */
    private float acceptanceRate;

    /**
     * Table mode list.
     */
    private ModelTableApplicationsAnalysis modelTableApplicationsAnalysis;

    /**
     * Evaluations statistics table.
     */
    private JTable evaluationsStatisticsJTable;

    /**
     * Empty border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates and instance of generate evaluations statistics panel.
     *
     * @param exhibitionCenter the exhibition center
     * @param actor the actor logged in
     */
    public GenerateEvaluationsStatisticsPanel(ExhibitionCenter exhibitionCenter, Actor actor) {
        super();

        this.controller = new GenerateEvaluationsStatisticsController(exhibitionCenter, actor);
        this.exhibitionsList = this.controller.getExhibitionsList();

        if (this.exhibitionsList.size() > 0) {
            controller.selectExhibition(exhibitionsList.get(0));
            acceptanceRate = controller.getAcceptanceRate();
            applicationAnalysisesList = controller.getApplicationsAnalysis();
            createComponents();
        } else {
            createNoExhibitionsComponents();
        }

    }

    /**
     * Creates the components of the user interface.
     */
    private void createComponents() {
        setLayout(new BorderLayout(0, 10));

        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
    }

    /**
     * Creates the top panel.
     *
     * @return top panel.
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        acceptanceRateJLabel = new JLabel(String.format("Acceptance rate: %.2f", acceptanceRate), SwingConstants.CENTER);

        String[] exhibitionsChoices = new String[this.exhibitionsList.size()];
        for (int i = 0; i < exhibitionsChoices.length; i++) {
            exhibitionsChoices[i] = this.exhibitionsList.get(i).getDisplayInfo();
        }
        this.exhibitionsJComboBox = new JComboBox(exhibitionsChoices);
        this.exhibitionsJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedIndex = exhibitionsJComboBox.getSelectedIndex();
                controller.selectExhibition(exhibitionsList.get(selectedIndex));
                acceptanceRate = controller.getAcceptanceRate();
                acceptanceRateJLabel = new JLabel(String.format("Acceptance rate: %.2f", acceptanceRate), SwingConstants.CENTER);
                updateTable();

            }
        });

        topPanel.add(this.exhibitionsJComboBox);
        topPanel.add(this.acceptanceRateJLabel);
        topPanel.add(createExportButton());

        return topPanel;
    }

    /**
     * Panel with a export button.
     *
     * @return Panel with a export button
     */
    private JPanel createExportButton() {

        JPanel btnPanel = new JPanel(new BorderLayout());

        JButton exportBtn = new JButton("Export to CSV");
        exportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = exhibitionsJComboBox.getSelectedIndex();
                Exhibition selectedExhibition = exhibitionsList.get(selectedIndex);

                String filename = selectedExhibition.getTitle() + "_EvaluationsStatistics.csv";

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify where to save csv");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {

                    String acceptedPath = fileChooser.getSelectedFile().getAbsoluteFile().getPath() + File.separator + filename;
                    File csvFile = new File(acceptedPath);

                    if (csvFile.exists()) {
                        csvFile.delete();
                    }
                    CSVParser.writeToCSV(evaluationsStatisticsJTable, csvFile);
                    
                    String success = "File saved. "+csvFile.toString();
                    
                    JOptionPane.showMessageDialog(GenerateEvaluationsStatisticsPanel.this, success, "File Saved", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        btnPanel.setBorder(PADDING_BORDER);

        btnPanel.add(new JPanel(), BorderLayout.CENTER);
        btnPanel.add(exportBtn, BorderLayout.WEST);

        return btnPanel;
    }

    /**
     * Creates the tabel panel.
     *
     * @return table panel
     */
    private JScrollPane createTablePanel() {

        this.modelTableApplicationsAnalysis = new ModelTableApplicationsAnalysis(this.applicationAnalysisesList);
        this.evaluationsStatisticsJTable = new JTable(this.modelTableApplicationsAnalysis);

        JScrollPane scrollPane = new JScrollPane(this.evaluationsStatisticsJTable);
        scrollPane.setBorder(PADDING_BORDER);

        return scrollPane;
    }

    /**
     * Creates the no exhibitions components.
     */
    private void createNoExhibitionsComponents() {
        setLayout(new GridBagLayout());
        JPanel componentsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        componentsPanel.add(new JLabel("No decided exhibitions available.", SwingConstants.CENTER));

        add(componentsPanel, new GridBagConstraints());
    }

    private void updateTable() {
        this.applicationAnalysisesList = this.controller.getApplicationsAnalysis();
        this.modelTableApplicationsAnalysis = new ModelTableApplicationsAnalysis(this.applicationAnalysisesList);
        this.evaluationsStatisticsJTable = new JTable(this.modelTableApplicationsAnalysis);
    }

}
