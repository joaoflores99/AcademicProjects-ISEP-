/**
 * Package location for UI concepts.
 */
package lapr.project.ui;

import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsManagerRegister;
import lapr.project.utils.ExhibitionCenterFile;

/**
 * Represents a applications list
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
class Main {

    /**
     * Private constructor to hide implicit public one.
     */
    private Main(ExhibitionCenter exhibitionCenter) {
        ExhibitionsManagerRegister exhibitionManagerRegister = exhibitionCenter.getExhibitionsManagerRegister();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       ExhibitionCenter exhibitionCenter = ExhibitionCenterFile.read(ExhibitionCenterFile.NAME);
  
        if (exhibitionCenter == null) {
            exhibitionCenter = new ExhibitionCenter();
        }

        ExhibitionsManagerRegister exhibitionsManagerRegister = exhibitionCenter.getExhibitionsManagerRegister();
        if (exhibitionsManagerRegister.hasAnyExhibitionManager()) {
            new LoginUI(exhibitionCenter);
        } else {
            new CreateUserProfileUI(exhibitionCenter, null);

        }
    }
}
