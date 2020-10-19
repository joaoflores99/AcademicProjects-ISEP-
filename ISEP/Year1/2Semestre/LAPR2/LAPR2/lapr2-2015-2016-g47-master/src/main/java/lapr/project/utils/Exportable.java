package lapr.project.utils;

//import org.w3c.dom.Node;

import lapr.project.model.Selectable;

//import javax.xml.transform.TransformerException;
/**
 * Interface that allows object's content to be exported.
 *
 * @author Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
 *
 * Changed by:
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
//@FunctionalInterface
public interface Exportable extends Selectable {

    /**
     * Get some part of exportable information
     *
     * @return
     */
    String getData();

    /**
     * Exports with help of JAXB library the object to a XML file.
     *
     * @param path the path where the file will be saved
     */
    void jaxbObjectExportableToXML(String path);
    
}
