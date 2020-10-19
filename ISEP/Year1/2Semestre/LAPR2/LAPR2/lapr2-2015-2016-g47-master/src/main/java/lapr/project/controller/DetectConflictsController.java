/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ApplicationsList;
import lapr.project.model.ConflictDetectionMechanism;
import lapr.project.model.ConflictType;
import lapr.project.model.ConflictTypesRegister;
import lapr.project.model.ConflictsList;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.StaffList;
import lapr.project.model.StaffMember;
import lapr.project.model.Submittable;

/**
 * Represents the controller to detect conflicts.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class DetectConflictsController {

    /**
     * Constructs a DetectConflictsController Class.
     *
     * @param exhibitionCenter Exhibition Center
     * @param submittable Submittable which the timer was triggered
     */
    public DetectConflictsController(ExhibitionCenter exhibitionCenter, Submittable submittable) {

        ConflictTypesRegister conflictTypesRegister = exhibitionCenter.getConflictTypesRegister();
        List<ConflictType> conflictTypesList = conflictTypesRegister.getConflictTypesList();

        ApplicationsList applicationsList = submittable.getApplicationsList();
        List<Application> applications = applicationsList.getApplicationsList();

        StaffList staffList = submittable.getStaffList();
        List<StaffMember> staff = staffList.getStaffList();

        ConflictsList conflictsList = submittable.getConflictsList();

        for (ConflictType conflictType : conflictTypesList) {

            ConflictDetectionMechanism mechanism = conflictType.getConflictDetectionMechanism();

            for (StaffMember staffMember : staff) {
                for (Application application : applications) {

                    // TODO : Create a conflict detection mechanism
                    boolean isConflict = mechanism.detectConflict(staffMember, application);

                    if (isConflict) {

                        boolean addedConflict = conflictsList.registerConflict(staffMember, application, conflictType);
                        if (addedConflict) {
                            // TODO: log that the conflict already exists
                        }

                    }
                }
            }
        }

        submittable.setDetectedConflicts();
    }

}
