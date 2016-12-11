/*
 * Package declaration
 */
package in.edu.ashoka.TransportApp;
import java.util.Calendar;
import java.util.TimerTask;

/**This will obtain the names 5 minutes before departure.
 *
 * @author Nishka
 */
public class CheckTimings extends TimerTask {
    @Override
    public void run(){
        Calendar now = Calendar.getInstance();
        String[] goingToCampus;
        String[] leavingCampus;
        String t1 = now.get(Calendar.DAY_OF_MONTH)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.YEAR)+" "+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE);
        goingToCampus = Bookings.accessData(t1, "Campus", 1);
        leavingCampus = Bookings.accessData(t1, "Jahangirpuri", 1);
    }
}
