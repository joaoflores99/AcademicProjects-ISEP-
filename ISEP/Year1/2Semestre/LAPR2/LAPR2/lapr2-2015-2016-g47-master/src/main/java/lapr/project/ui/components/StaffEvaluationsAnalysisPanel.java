/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.table.TableCellRenderer;
import lapr.project.controller.StaffEvaluationsAnalysisController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.StaffMemberAnalytic.ConfidenceIntervals;
import lapr.project.utils.CSVParser;

/**
 * Represents a panel with the staff evaluations analysis.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class StaffEvaluationsAnalysisPanel extends JPanel {

    /**
     * The staff evaluations analysis controller.
     */
    private final StaffEvaluationsAnalysisController controller;

    /**
     * Output Table with staff evaluations analytics.
     */
    private JTable analyticsTable;

    /**
     * ComboBox with different significance levels.
     */
    private JComboBox signLvlcomboBox;

    /**
     * Label with selected z value.
     */
    private JLabel zValueLbl;

    /**
     * prefix to z value string
     */
    private static final String PREFIX_Z_Value = "> ";

    /**
     * Padding border.
     */
    final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Constructor of a panel with the staff evaluations analysis.
     *
     * @param exhibitionCenter the exhibition center
     */
    public StaffEvaluationsAnalysisPanel(ExhibitionCenter exhibitionCenter) {
        super();

        controller = new StaffEvaluationsAnalysisController(exhibitionCenter);

        if (exhibitionCenter.getRecord().getStaffList().size() > 0) {
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
        add(createTable(), BorderLayout.CENTER);
    }

    /**
     * Creates a panel with significance level selection combo box.
     *
     * @return panel with significance level selection combo box
     */
    private JPanel createTopPanel() {

        JLabel signLvlLbl = new JLabel("Significance Level:", JLabel.LEADING);
        JLabel criticalRegionLbl = new JLabel("Critical Region:", JLabel.LEADING);

        signLvlcomboBox = new JComboBox(ConfidenceIntervals.values());
        // Start with a default Significance Level of 95%
        signLvlcomboBox.setSelectedIndex(1);
        signLvlcomboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                updateTable();
            }
        });

        float selectedValue = ((ConfidenceIntervals) signLvlcomboBox.getSelectedItem()).zValue();
        zValueLbl = new JLabel(String.format("%s%.2f", PREFIX_Z_Value, selectedValue), JLabel.LEADING);

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        JPanel comboPanel = new JPanel();
        GroupLayout layout = new GroupLayout(comboPanel);
        comboPanel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        // Align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(signLvlLbl)
                        .addComponent(criticalRegionLbl)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(signLvlcomboBox)
                        .addComponent(zValueLbl)
                )
        );
        // Align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(signLvlLbl)
                        .addComponent(signLvlcomboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(criticalRegionLbl)
                        .addComponent(zValueLbl)
                )
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

                ConfidenceIntervals interval = (ConfidenceIntervals) signLvlcomboBox.getSelectedItem();
                
                String filename = "ExhibitionsCenter"+interval.getText()+"_StaffEvaluationsStatistics.csv";

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
                    CSVParser.writeToCSV(analyticsTable, csvFile);

                    String success = "File saved. " + csvFile.toString();

                    JOptionPane.showMessageDialog(StaffEvaluationsAnalysisPanel.this, success, "File Saved", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        btnPanel.setBorder(PADDING_BORDER);

        btnPanel.add(new JPanel(), BorderLayout.CENTER);
        btnPanel.add(exportBtn, BorderLayout.WEST);

        return btnPanel;
    }

    /**
     * Creates a panel with analytics table.
     *
     * @return panel with analytics table
     */
    private JScrollPane createTable() {

        analyticsTable = new JTable(new ModelTableStaffMemeberAnalytics(controller.getStaffAnalyticsList())) {

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

                int warningColumn = analyticsTable.getColumnCount() - 1;
                Component component = super.prepareRenderer(renderer, row, column);
                try {
                    String value = (String) getModel().getValueAt(row, warningColumn);
                    if (value.equals("Affirmative") && warningColumn == column) {
                        component.setForeground(Color.red);
//                        component.setBackground(Color.red);
                    } else {
                        component.setForeground(this.getForeground());
//                        component.setBackground(this.getBackground());
                    }

                } catch (Exception ex) {
                    // Do nothing
                }
                return component;
            }

        };
        updateTable();

        JScrollPane scrollPane = new JScrollPane(analyticsTable);
        scrollPane.setBorder(PADDING_BORDER);

        return scrollPane;
    }

    /**
     * Update the table information.
     */
    public void updateTable() {

        ConfidenceIntervals selectedConfInterval = (ConfidenceIntervals) signLvlcomboBox.getSelectedItem();
        float selectedValue = selectedConfInterval.zValue();
        zValueLbl.setText(String.format("%s%.2f", PREFIX_Z_Value, selectedValue));

        controller.updateWarnings(selectedConfInterval);
        analyticsTable.setModel(new ModelTableStaffMemeberAnalytics(controller.getStaffAnalyticsList()));
        analyticsTable.repaint();
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

}
