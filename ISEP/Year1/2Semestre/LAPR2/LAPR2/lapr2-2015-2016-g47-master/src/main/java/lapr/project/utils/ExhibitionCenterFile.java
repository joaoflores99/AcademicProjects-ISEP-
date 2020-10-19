/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import lapr.project.model.Demonstration;
import lapr.project.model.DemonstrationsList;
import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;

/**
 * Read and write the exhibition center data from a binary file.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExhibitionCenterFile {

    /**
     * Name of the file.
     */
    public static final String NAME = "ExhibitionCenter.bin";

    /**
     * Read a binary file with the exhibition center and returns the exhibition
     * center.
     *
     * @param fileName name of the binary file
     * @return exhibition center
     */
    public static ExhibitionCenter read(String fileName) {
        ExhibitionCenter exhibitionCenter;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(fileName));
            try {
                exhibitionCenter = (ExhibitionCenter) in.readObject();
                renewTimers(exhibitionCenter);
            } finally {
                in.close();
            }
            return exhibitionCenter;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    /**
     * Saves the exhibition center data in a binary file.
     *
     * @param fileName name of the file to be saved
     * @param exhibitionCenter exhibition center
     * @return true if it is successful saved, false otherwise
     */
    public static boolean save(String fileName, ExhibitionCenter exhibitionCenter) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            try {
                out.writeObject(exhibitionCenter);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Re-create timers from dates.
     *
     * @param exhibitionCenter the exhibition center.
     */
    private static void renewTimers(ExhibitionCenter exhibitionCenter) {

        List<Exhibition> exhibitionsList = exhibitionCenter.getExhibitionsRegister().getExhibitionsList();

        for (Exhibition exhibition : exhibitionsList) {

            exhibition.setTimer(new Timer());
            exhibition.createTimers(exhibitionCenter);

            if (exhibition.isApplicationsDecided()) {

                DemonstrationsList demonstrationsList = exhibition.getDemonstrationsList();
                for (Demonstration demonstration : demonstrationsList.getDecidedDemonstrations()) {
                    Date[] dates = {demonstrationsList.getSubStartDate(),
                        demonstrationsList.getSubEndDate(),
                        demonstrationsList.getConflictLimitDate(),
                        demonstrationsList.getEvaluationLimitDate()};

                    demonstration.setTimer(new Timer());
                    demonstration.createTimers(exhibitionCenter, dates);
                }
            }
        }
    }
}
