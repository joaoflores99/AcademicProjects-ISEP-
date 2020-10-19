/**
 * Package location for UI concepts.
 */
package lapr.project.ui.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import lapr.project.ui.ExhibitionApplicationUI;

/**
 * GUI for a new product
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class NewProductDialog extends JDialog {

    /**
     * The parent frame.
     */
    private ExhibitionApplicationUI parentFrame;

    /**
     * The text field for the product designation.
     */
    private JTextField txtDesignation;

    /**
     * Contructs a new instance of this class receivem the parent frame as parameter.
     *
     * @param parentFrame the frame that calls this one
     */
    public NewProductDialog(ExhibitionApplicationUI parentFrame) {
        super(parentFrame, "New Product", true);
        this.parentFrame = parentFrame;

        createPanels();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    /**
     * Creates UI panels.
     */
    private void createPanels() {

        JPanel panelNorth = createNorthPanel();
        JPanel panelSouth = createSouthPanel();

        add(panelNorth, BorderLayout.NORTH);
        add(panelSouth, BorderLayout.CENTER);

    }

    /**
     * Returns the north panel.
     *
     * @return the north panel
     */
    private JPanel createNorthPanel() {

        JLabel lblDesignation = new JLabel("Designation:", JLabel.RIGHT);

        final int FIELD_WIDTH = 20;
        this.txtDesignation = new JTextField(FIELD_WIDTH);

        JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final int MARGIN_SUPERIOR = 10, MARGIN_INFERIOR = 0;
        final int MARGIN_RIGHT = 10, MARGIN_LEFT = 0;
        panelNorth.setBorder(new EmptyBorder(MARGIN_SUPERIOR, MARGIN_RIGHT,
                MARGIN_INFERIOR, MARGIN_LEFT));

        panelNorth.add(lblDesignation);
        panelNorth.add(this.txtDesignation);

        return panelNorth;
    }

    /**
     * Returns the south panel
     *
     * @return the south panel
     */
    private JPanel createSouthPanel() {

        JButton btnOK = createOKButton();
        getRootPane().setDefaultButton(btnOK);

        JButton btnCancel = createCancelButton();

        JPanel panelSouth = new JPanel();
        final int MARGIN_SUPERIOR = 0, MARGIN_INFERIOR = 10;
        final int MARGIN_RIGHT = 10, MARGIN_LEFT = 10;
        panelSouth.setBorder(new EmptyBorder(MARGIN_SUPERIOR, MARGIN_RIGHT,
                MARGIN_INFERIOR, MARGIN_LEFT));

        panelSouth.add(btnOK);
        panelSouth.add(btnCancel);

        return panelSouth;
    }

    /**
     * Returns the OK button.
     *
     * @return the OK button
     */
    private JButton createOKButton() {

        JButton btn = new JButton("OK");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String designation = txtDesignation.getText();
                    if (designation.length() < 1) {
                        throw new IllegalArgumentException("Invalid designation. Try again.");
                    }

                    boolean addedProduct = parentFrame.newProduct(designation);

                    if (!addedProduct) {
                        throw new IllegalArgumentException("The product already exists. Name a new one!");
                    } else {
                        JOptionPane.showMessageDialog(rootPane,
                                "Product added sucessfully!",
                                "Add product",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    dispose();

                } catch (IllegalArgumentException ex) {

                    JOptionPane.showMessageDialog(
                            parentFrame,
                            ex.getMessage(),
                            "Invalid designation",
                            JOptionPane.WARNING_MESSAGE);
                    txtDesignation.requestFocusInWindow();
                }
            }
        }
        );

        return btn;
    }

    /**
     * Returns the cancel button
     *
     * @return the cancel button
     */
    private JButton createCancelButton() {

        JButton btn = new JButton("Cancel");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        return btn;
    }
}
