/**
 * Package location for the Exhibiton timertasks & related classes.
 */
package lapr.project.model.timers;

import java.io.Serializable;
import java.util.TimerTask;
import lapr.project.controller.DetectConflictsController;
import lapr.project.model.ExhibitionCenter;
import lapr.project.model.Submittable;

/**
 * Represents the timertask to start conflict detection.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 *
 * @param <T> generic type to receive a submittable
 */
public class DetectConflictsTask<T extends Submittable> extends TimerTask implements Serializable {

    /**
     * Exhibiton wich preformes the task.
     */
    private final Submittable submittable;

    /**
     * The exhibiton Center.
     */
    private final ExhibitionCenter exhibitionCenter;

    /**
     * Constructor of the timer task.
     *
     * @param submittable the submittable that will activate the task
     * @param exhibitionCenter the exhibition center
     */
    public DetectConflictsTask(T submittable, ExhibitionCenter exhibitionCenter) {

        this.submittable = (Submittable) submittable;
        this.exhibitionCenter = exhibitionCenter;
    }

    /**
     * Task that is preformed once the timer is triggered.
     */
    @Override
    public void run() {

        DetectConflictsController controller = new DetectConflictsController(exhibitionCenter, submittable);
    }

}
