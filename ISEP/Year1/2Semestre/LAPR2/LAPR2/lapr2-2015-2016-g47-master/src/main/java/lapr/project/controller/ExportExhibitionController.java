/**
 * Package location for Export Exhibition Controller concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.ExhibitionsRegister;
import lapr.project.model.Organizer;
import lapr.project.utils.Exportable;

/**
 * Represents an export exhibitions controller.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ExportExhibitionController {

    /**
     * The exhibition center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * The exhibition manager.
     */
    private final Organizer organizer;
    /**
     * The exhibitions Register.
     */
    private final ExhibitionsRegister exhibitionsRegister;

    /**
     * The selected exportable.
     */
    private Exportable selectedExportable;

    /**
     * Builds instance of this class receiving an organizer and exhibition
     * center as parameters.
     *
     * @param organizer the organizer
     * @param exhibitionCenter the exhibition center
     */
    public ExportExhibitionController(Organizer organizer, ExhibitionCenter exhibitionCenter) {
        this.organizer = organizer;
        this.exhibitionCenter = exhibitionCenter;
        this.exhibitionsRegister = this.exhibitionCenter.getExhibitionsRegister();
    }

    /**
     * Gets the list of exhibitions filtered by organizer.
     *
     * @param organizer the organizer to filter.
     * @return the list of exhibitions filtered by organizer.
     */
    public List<Exportable> getListExportablesByOrganizer(Organizer organizer) {
        return this.exhibitionsRegister.getExhibitionsListByOrganizer(organizer);
    }

    /**
     * Set the selectedExportable to the new exportable passed as parameter, and
     * shows its data.
     *
     * @param exportable the new exportable selected to to show it's data.
     */
    public void setExportable(Exportable exportable) {
        this.selectedExportable = exportable;
        this.selectedExportable.getData();
    }

    /**
     * Export the selected exportable to a XML file.
     *
     * @param path the path where the file will be saved
     */
    public void exportExportable(String path) {
        this.selectedExportable.jaxbObjectExportableToXML(path);
    }
}
