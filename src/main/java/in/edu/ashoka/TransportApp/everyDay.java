/** Package declaration */
package in.edu.ashoka.TransportApp;

/**
 * Necessary imports
 */
import java.util.Calendar;
import java.util.TimerTask;

/**Will enable bookings 8 days in advance, resetting every day.
 * 
 * @author Nishka
 */
class everyDay extends TimerTask {
@Override
  public void run() {
    Calendar cal=Calendar.getInstance();
    cal.add(Calendar.DATE, 8);
    try{
        Bookings.createNextDay(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
    }
    catch(Exception e){
        System.out.println("ERROR.");
    }
  }
}

