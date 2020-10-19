/*
 * Fornece classes para componentes do UI do centro de exposições.
 */
package lapr.project.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import lapr.project.model.ExhibitionCenter;
import lapr.project.ui.LoginUI;
import lapr.project.utils.ExhibitionCenterFile;

/**
 * A cutom menu bar with log out and exit options.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class CustomMenuBar extends JMenuBar {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The parent JFrame.
     */
    private final JFrame parentFrame;

    /**
     * Valor de opções.
     */
    private static final String OPTIONS = "Options";

    /**
     * Valor de terminar sessão.
     */
    private static final String LOG_OUT = "Log out";

    /**
     * Valor de sair.
     */
    private static final String EXIT = "Exit";

    /**
     * Creates a custom JMenuBar.
     *
     * @param exhibitionCenter the exhibition center
     * @param parentFrame the parent frame
     */
    public CustomMenuBar(ExhibitionCenter exhibitionCenter, JFrame parentFrame) {
        this.exhibitionCenter = exhibitionCenter;
        this.parentFrame = parentFrame;

        JMenu optionsMenu = new JMenu(OPTIONS);
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        JMenuItem logOutMenuItem = new JMenuItem(LOG_OUT, 'L');
        logOutMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        logOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CustomMenuBar.this.logOut();
            }
        });

        JMenuItem exitMenuItem = new JMenuItem(EXIT, 'E');
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CustomMenuBar.this.exit();
            }
        });

        optionsMenu.add(logOutMenuItem);
        optionsMenu.add(exitMenuItem);

        add(optionsMenu);
    }

    /**
     * Log out to login window.
     */
    private void logOut() {
        this.parentFrame.dispose();
        new LoginUI(this.exhibitionCenter);
    }

    /**
     * Exit from application.
     */
    public void exit() {
        boolean isSaved = ExhibitionCenterFile.save(ExhibitionCenterFile.NAME, this.exhibitionCenter);

        if (isSaved) {
            System.exit(0);
        } else {
            System.exit(1);
        }
    }
}
