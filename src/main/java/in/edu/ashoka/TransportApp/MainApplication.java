/** Package declaration */
package in.edu.ashoka.TransportApp;

/**Necessary imports */
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.util.*;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/* This will constantly run and supervise all the other classes. 
 * 
 * @author Nishka */
 
public class MainApplication extends HttpServlet{

    /*
     * To execute the program.
     *
     * @param args not used
     */

    public void init() throws ServletException{
        System.out.println("MainApp is running");
        try {
            Bookings.initialise();
        } catch (IOException e) {
            e.printStackTrace();
        }
        refreshSchedule();
        deleteOld();
        checkToSMS();
        System.out.println(BookUser("Mayukh", "10-12-2016 09:00", "Campus"));
    }

    public static int BookUser(String name, String datetime, String destination){
        return Bookings.book(name,datetime,destination);
    }

    public static void main(String[] args) throws IOException {

//        SMSPush messageTool = new SMSPush();
//        try {
//            messageTool.pushMessage("Someday I'll find whoever thought of embedding Tomcat in Java apps and beat him up with a tire iron");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }


        /**Will create new Shuttle-type objects every day at midnight.
         *
         */


    }//MainApplication ends
    static void refreshSchedule() {
        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        /**Schedule to run every day */
        timer.schedule(
                new everyDay(),
                date.getTime(),
                1000 * 60 * 60 * 24
        );
    }

    /**
     * Will delete the details of past shuttles every hour.
     */
    static void deleteOld() {
        /**Schedule to run every hour*/
        Timer cleaner = new Timer();
        cleaner.schedule(new Sweeper(), 0, 1000 * 60 * 60);

    }

    /**
     * Runs every 5 minutes to check whether it's time to send a message to the guard.
     */
    static void checkToSMS() {
        Timer toSMS = new Timer();
        toSMS.schedule(new CheckTimings(), 0, 1000 * 60 * 5);
    }
}


