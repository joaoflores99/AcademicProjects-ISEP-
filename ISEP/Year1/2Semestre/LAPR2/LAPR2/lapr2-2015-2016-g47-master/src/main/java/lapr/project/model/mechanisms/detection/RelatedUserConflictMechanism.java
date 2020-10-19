/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model.mechanisms.detection;

import java.io.Serializable;
import java.util.List;
import lapr.project.model.Application;
import lapr.project.model.ConflictDetectionMechanism;
import lapr.project.model.StaffMember;
import lapr.project.model.User;

/**
 * Represents a conflict detection mechanism: it verifies if a staff member is
 * related to any exhibitor responsible.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 */
public class RelatedUserConflictMechanism implements ConflictDetectionMechanism, Serializable {

    /**
     * Mechanism description
     */
    private static final String MECHANISM_DESCRIPTION = "Related User Conflict Mechanism";

    @Override
    public boolean detectConflict(StaffMember staffMember, Application application) {
        return isConflict(staffMember, application);
    }

    private boolean isConflict(StaffMember staffMember, Application application) {

        List<User> staffMemberRelatives = staffMember.getUser().getRelatedUsers();

        //TODO : Review after exhibitior responsible is a attribute of exhibitor.
//        User ExhibitorResponsible = application
        boolean isConflict = true;

        return isConflict;
    }

    @Override
    public String getDescription() {

        return MECHANISM_DESCRIPTION;
    }

    /**
     * Compares if this object is equal to otherObject.
     *
     * @param otherObject other object to compare with
     * @return true if it represents the same object, false otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        return true;
    }

}
