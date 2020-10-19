package lapr.project.utils;

import lapr.project.model.Exhibition;
import lapr.project.model.ExhibitionCenter;

/**
 * All domains classes should include this interface.
 * Created by Nuno Bettencourt [NMB] on 29/05/16.
 */
@FunctionalInterface
public interface Importable<T extends Exhibition> {
	/**
	 * Imports the object content from an XML format.
	 *
	 * @return Structured String containing content.
	 */
	T importByFileName(String fileName,ExhibitionCenter exhibitionCenter);
}
