/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import javafx.util.Pair;
import javax.swing.GroupLayout;
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
import lapr.project.controller.GenerateKeywordRankingsController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Keyword;
import lapr.project.model.Submittable;
import lapr.project.utils.CSVParser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 * Represents a panel with the generate keywords rankings.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class GenerateKeywordsRankingPanel extends JPanel {

    /**
     * The generate keyword rankings controller.
     */
    private final GenerateKeywordRankingsController controller;

    /**
     * The submittables list available to create a ranking.
     */
    private final List<Submittable> submittablesList;

    /**
     * Accepted Applications Keywords Ranking.
     */
    private JTable acceptedKeywordsTable;

    /**
     * Accepted Applications Keywords Chart.
     */
    private ChartPanel acceptedKeywordsChart;

    /**
     * Rejected Applications Keywords Ranking.
     */
    private JTable rejectedKeywordsTable;

    /**
     * Rejected Applications Keywords Chart.
     */
    private ChartPanel rejectedKeywordsChart;

    /**
     * ComboBox to select submittable.
     */
    private JComboBox submittableCombo;

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Constructor of a panel with the staff evaluations analysis.
     *
     * @param exhibitionCenter the exhibition center
     */
    public GenerateKeywordsRankingPanel(ExhibitionCenter exhibitionCenter) {
        super();

        controller = new GenerateKeywordRankingsController(exhibitionCenter);
        submittablesList = controller.getDecidedSubmittables();

        if (submittablesList.size() > 0) {
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

        Submittable submittable = submittablesList.get(submittableCombo.getSelectedIndex());
        controller.setApplicationsList(submittable);

        JPanel statisticsPanel = new JPanel(new GridLayout(2, 1));

        statisticsPanel.add(createRankingAccepted());
        statisticsPanel.add(createRankingRejected());

        add(statisticsPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the no exhibitions components.
     */
    private void createNoExhibitionsComponents() {
        setLayout(new GridBagLayout());
        JPanel componentsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        componentsPanel.add(new JLabel("No exhibitions available.", SwingConstants.CENTER));

        add(componentsPanel, new GridBagConstraints());
    }

    /**
     * Creates a panel with the submittables selection combo box.
     *
     * @return panel with the submittables selection combo box
     */
    private JPanel createTopPanel() {

        JLabel submittableLbl = new JLabel("Select a Submittable:", JLabel.LEADING);

        String[] comboValues = new String[submittablesList.size()];
        for (int i = 0; i < comboValues.length; i++) {
            comboValues[i] = submittablesList.get(i).getDisplayInfo();
        }

        submittableCombo = new JComboBox(comboValues);
        // Start with the first submittable from the list
        submittableCombo.setSelectedIndex(0);
        submittableCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                updateTables();
            }
        });

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        JPanel comboPanel = new JPanel();
        GroupLayout layout = new GroupLayout(comboPanel);
        comboPanel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        // Align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(submittableLbl)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(submittableCombo)
                )
        );
        // Align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(submittableLbl)
                        .addComponent(submittableCombo))
        );

        topPanel.add(comboPanel);
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

                int selectedIndex = submittableCombo.getSelectedIndex();
                Submittable selectedSubmittable = submittablesList.get(selectedIndex);

                String acceptedFilename = selectedSubmittable.getName() + "_AcceptedKeywordsRanking.csv";
                String rejectedFilename = selectedSubmittable.getName() + "_RejectKeywordsRanking.csv";

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify where to save csv");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {

                    String acceptedPath = fileChooser.getSelectedFile().getAbsoluteFile().getPath() + File.separator + acceptedFilename;
                    File acceptedCsvFile = new File(acceptedPath);
                    String rejectedPath = fileChooser.getSelectedFile().getAbsoluteFile().getPath() + File.separator + rejectedFilename;
                    File rejectedCsvFile = new File(rejectedPath);

                    if (acceptedCsvFile.exists()) {
                        acceptedCsvFile.delete();
                    } else if (rejectedCsvFile.exists()) {
                        rejectedCsvFile.delete();
                    }
                    CSVParser.writeToCSV(acceptedKeywordsTable, acceptedCsvFile);
                    CSVParser.writeToCSV(rejectedKeywordsTable, rejectedCsvFile);

                    String success = String.format("Files saved.%n" + acceptedCsvFile.toString() + "%n"
                            + rejectedCsvFile.toString());

                    JOptionPane.showMessageDialog(GenerateKeywordsRankingPanel.this, success, "File Saved", JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });

        btnPanel.setBorder(PADDING_BORDER);

        btnPanel.add(new JPanel(), BorderLayout.CENTER);
        btnPanel.add(exportBtn, BorderLayout.WEST);

        return btnPanel;
    }

    /**
     * Creates a panel with a chart & a table for a accepted apps ranking.
     *
     * @return panel with analytics table
     */
    private JPanel createRankingAccepted() {

        List<Pair<Keyword, Integer>> ranking = controller.calcuateAcceptedAppsRanking();

        // Create Table
        acceptedKeywordsTable = new JTable(new ModelTableKeywordRanking(ranking));
        JScrollPane scrollPane = new JScrollPane(acceptedKeywordsTable);
        scrollPane.setBorder(PADDING_BORDER);
        // Create Chart
        PieDataset dataSet = new DefaultPieDataset(createDataset(ranking));
        acceptedKeywordsChart = new ChartPanel(createPieChart(dataSet, Color.GREEN));
//        chart.setPreferredSize(); Set if necessary

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Accepted Applications Keywords Ranking", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(acceptedKeywordsChart, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.EAST);

        return panel;
    }

    /**
     * Creates a panel with a chart & a table for a rejected apps ranking.
     *
     * @return panel with analytics table
     */
    private JPanel createRankingRejected() {

        List<Pair<Keyword, Integer>> ranking = controller.calcuateDeclinedAppsRanking();

        // Create Table
        rejectedKeywordsTable = new JTable(new ModelTableKeywordRanking(ranking));
        JScrollPane scrollPane = new JScrollPane(rejectedKeywordsTable);
        scrollPane.setBorder(PADDING_BORDER);
        // Create Chart
        PieDataset dataSet = new DefaultPieDataset(createDataset(ranking));
        rejectedKeywordsChart = new ChartPanel(createPieChart(dataSet, Color.RED));
//        chart.setPreferredSize(); Set if necessary

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Declined Applications Keywords Ranking", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(rejectedKeywordsChart, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.EAST);

        return panel;
    }

    /**
     * Creates a chart dataset
     *
     * @param ranking the keyword ranking
     */
    private PieDataset createDataset(List<Pair<Keyword, Integer>> ranking) {

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Pair<Keyword, Integer> rank : ranking) {
            dataset.setValue(rank.getKey(), rank.getValue());
        }

        return dataset;

    }

    /**
     * Creates a pie chart.
     *
     * @param dataset the data to create the chart
     * @param backgroundColor backgroundColor
     * @return
     */
    private JFreeChart createPieChart(PieDataset dataset, Color backgroundColor) {

        JFreeChart pieChart = ChartFactory.createPieChart(null, // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        PieSectionLabelGenerator lblGenerator = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setLabelGenerator(lblGenerator);
        plot.setBackgroundPaint(backgroundColor);
        plot.setBackgroundAlpha(0.25f);

        return pieChart;
    }

    /**
     * Update the table & chart information.
     */
    public void updateTables() {

        Submittable submittable = submittablesList.get(submittableCombo.getSelectedIndex());
        controller.setApplicationsList(submittable);
        List<Pair<Keyword, Integer>> acceptedRanking = controller.calcuateAcceptedAppsRanking();
        List<Pair<Keyword, Integer>> rejectedRanking = controller.calcuateDeclinedAppsRanking();

        acceptedKeywordsTable.setModel(new ModelTableKeywordRanking(acceptedRanking));
        PieDataset dataSetAccepted = new DefaultPieDataset(createDataset(acceptedRanking));
        JFreeChart chartAccepted = createPieChart(dataSetAccepted, Color.GREEN);
        acceptedKeywordsChart.setChart(chartAccepted);
        acceptedKeywordsChart.repaint();

        rejectedKeywordsTable.setModel(new ModelTableKeywordRanking(rejectedRanking));
        PieDataset dataSetRejected = new DefaultPieDataset(createDataset(rejectedRanking));
        JFreeChart chartRejected = createPieChart(dataSetRejected, Color.RED);
        rejectedKeywordsChart.setChart(chartRejected);
        rejectedKeywordsChart.repaint();

        repaint();

    }
}
