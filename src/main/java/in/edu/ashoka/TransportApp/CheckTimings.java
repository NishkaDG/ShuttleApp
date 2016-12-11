/*
 * Package declaration
 */
package in.edu.ashoka.TransportApp;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**This will obtain the names 5 minutes before departure.
 *
 * @author Nishka
 */
public class CheckTimings extends TimerTask {
    @Override
    public void run(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar now = Calendar.getInstance();
        String[] goingToCampus;
        String[] leavingCampus;
        String t1 = now.get(Calendar.DAY_OF_MONTH)+"-"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.YEAR)+" "+now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE);
        try{
            Date d1 = format.parse(t1);
            HashMap fc=Bookings.fromCampus;
            HashMap fs=Bookings.fromStation;
            synchronized (fc){
                Iterator it=fc.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, Shuttle> entry=(Map.Entry<String, Shuttle>) it.next();
                    Date d2=format.parse(entry.getKey());
                    long diff=(d2.getTime()-d1.getTime())/(60*1000)%60;
                    if(diff==5)
                    {
                        leavingCampus=Bookings.forSMS(entry.getKey(), "Jahangirpuri");
                    }
                }
            }
            synchronized (fs){
                Iterator it=fs.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, Shuttle> entry=(Map.Entry<String, Shuttle>) it.next();
                    Date d2=format.parse(entry.getKey());
                    long diff=(d2.getTime()-d1.getTime())/(60*1000)%60;
                    if(diff==5)
                    {
                        goingToCampus=Bookings.forSMS(entry.getKey(), "Campus");
                    }
                }
            }
        }
        catch(Exception e){
            leavingCampus=new String[2];
            goingToCampus=new String[2];
            e.printStackTrace();
        }
    }
}
