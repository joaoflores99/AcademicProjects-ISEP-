/**
 * Package location for UI components classes.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.AbstractListModel;
import lapr.project.model.Selectable;

/**
 * Model list for submittables.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class ModelListSelectable extends AbstractListModel {

    /**
     * The selectables list;
     */
    private final List<? extends Selectable> selectablesList;

    /**
     * Constructs a model list for selectables.
     *
     * @param selectablesList submittables list
     */
    public ModelListSelectable(List<? extends Selectable> selectablesList) {
        this.selectablesList = selectablesList;
    }

    @Override
    public int getSize() {
        return this.selectablesList.size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.selectablesList.get(i).getDisplayInfo();
    }
    
    public Object getObject(int i){
        return this.selectablesList.get(i);
    }
    
    public void clear(){
        this.selectablesList.clear();
    }
}
