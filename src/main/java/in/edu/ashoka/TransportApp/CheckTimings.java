/*
 * Package declaration
 */
package in.edu.ashoka.TransportApp;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.TimerTask;
import java.text.SimpleDateFormat;

/**This will obtain the names 5 minutes before departure.
 *
 * @author Nishka
 */
public class CheckTimings extends TimerTask {
    
    /**To run the thread. 
     * This function will obtain the lists from method accessData in class Bookings
     * and send the SMS
     * 
     */
    @Override
    public void run(){
        SMSPush smsPush = new SMSPush();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar now = Calendar.getInstance();
        String[] goingToCampus;
        String[] leavingCampus;
        String t1 = now.get(Calendar.DAY_OF_MONTH)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.YEAR)+" "+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE);
        goingToCampus = Bookings.accessData(t1, "Campus", 1, new String[2]);
        leavingCampus = Bookings.accessData(t1, "Jahangirpuri", 1, new String[2]);
        try {
            if(leavingCampus[0].length()>0){
                smsPush.pushMessage(leavingCampus[0], "+917898214528");
            }
            System.out.println("Confirmed list for Jahangirpuri dispatched");
            if(leavingCampus[1].length()>0){
                smsPush.pushMessage(leavingCampus[1], "+917898214528");
            }
            System.out.println("Waitlist for Jahangirpuri dispatched");
            if(goingToCampus[0].length()>0){
                smsPush.pushMessage(goingToCampus[0], "+917898214528");
            }
            System.out.println("Confirmed list for Campus dispatched");
            if(goingToCampus[1].length()>0){
            smsPush.pushMessage(goingToCampus[1], "+917898214528");
            }
            System.out.println("Waitlist for Campus dispatched");
        }
        catch (URISyntaxException e){
            e.printStackTrace();
        }
    }
}
