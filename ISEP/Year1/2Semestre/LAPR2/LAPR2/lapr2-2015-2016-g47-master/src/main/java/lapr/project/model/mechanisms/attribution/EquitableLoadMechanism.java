/**
 * Package location for Model concepts.
 */
package lapr.project.model.mechanisms.attribution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.Assingnable;
import lapr.project.model.StaffAttribution;
import lapr.project.model.StaffAttributionMechanism;
import lapr.project.model.StaffAttributionsList;
import lapr.project.model.StaffMember;
import lapr.project.model.Submittable;

/**
 * Represents a mechanism of attribution: it assigns an application for each FAE
 * equitably .
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class EquitableLoadMechanism implements StaffAttributionMechanism, Serializable {

    public EquitableLoadMechanism() {

    }
    /**
     * Mechanism description
     */
    private static final String MECHANISM_DESCRIPTION = "Equitable Load Mechanism";

    /**
     * Creates a list of attributions
     *
     * @param submittable
     * @return attributionsList
     */
    @Override
    public List<StaffAttribution> toAssign(Submittable submittable) {

        StaffAttributionsList staffAttributionsList = submittable.getStaffAttributionsList();

        List<Application> applicationsList = new ArrayList<>();
        applicationsList = submittable.getApplicationsList().getApplicationsList();

        List<StaffMember> staffList = new ArrayList<>();
        staffList = submittable.getStaffList().getStaffList();

        List<StaffAttribution> attributionsList = new ArrayList<>();

        int countAssigneds = 0;
        for (Application application : applicationsList) {

            if (countAssigneds > staffList.size() - 1) {
                countAssigneds = 0;
            }
            StaffAttribution attribution = staffAttributionsList.newAttribution((Assingnable) application, staffList.get(countAssigneds));
            countAssigneds++;

            if (staffAttributionsList.validateAttribution(attribution)) {
                attributionsList.add(attribution);
            }
        }
        return attributionsList;
    }

    /**
     * Get the Mechanism's description
     *
     * @return the Mechanism's description
     */
    @Override
    public String getDisplayInfo() {
        return MECHANISM_DESCRIPTION;
    }

}
