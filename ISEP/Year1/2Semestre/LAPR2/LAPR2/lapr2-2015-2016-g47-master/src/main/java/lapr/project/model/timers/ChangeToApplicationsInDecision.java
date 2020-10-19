/**
 * Package location for the Exhibiton timertasks & related classes.
 */
package lapr.project.model.timers;

import java.io.Serializable;
import java.util.TimerTask;
import lapr.project.model.Submittable;

/**
 * Represents the timertask to change state to applications in decision.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Renato Oliveira 1140822
 * @author Ricardo Correia 1151231
 *
 * @param <T> generic type to receive a submittable
 */
public class ChangeToApplicationsInDecision<T extends Submittable> extends TimerTask implements Serializable {

    /**
     * Exhibiton wich preformes the task.
     */
    private final Submittable submittable;

    /**
     * Constructor of the timer task.
     *
     * @param submittable the submittable that will activate the task
     */
    public ChangeToApplicationsInDecision(T submittable) {

        this.submittable = (Submittable) submittable;
    }

    /**
     * Task that is preformed once the timer is triggered.
     */
    @Override
    public void run() {

        this.submittable.setApplicationsInDecision();

    }

}
