/**Package declaration. */
package in.edu.ashoka.TransportApp;

/** Necessary imports */
import java.util.Calendar;
import java.util.TimerTask;

/** Will delete the details of those shuttles whose departure times are past. 
 * 
 * @author Nishka
 */
class Sweeper extends TimerTask{
@Override
    public void run() {
    Calendar c=Calendar.getInstance();
    String formattedDate=c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE);
    System.out.println("Sweeper has been triggered at "+formattedDate);
    Bookings.accessData(formattedDate, "Campus", 2);
    Bookings.accessData(formattedDate, "Jahangirpuri", 2);
    }
}