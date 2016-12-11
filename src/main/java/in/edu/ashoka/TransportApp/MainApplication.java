/** Package declaration */
package in.edu.ashoka.TransportApp;

/**Necessary imports */
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/* This will constantly run and supervise all the other classes. 
 * 
 * @author Nishka */
 
public class MainApplication extends HttpServlet{

    /*
     * To execute the program
     */


    public void init() throws ServletException {
        System.out.println("MainApp is running");
        try {
            Bookings.initialise();
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkToSMS();
        refreshSchedule();
        deleteOld();
        checkToSMS();
        SMSPush smsPush = new SMSPush();
        try {
            smsPush.pushMessage("Test","+917898214528");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        DBScribe dbScribe = new DBScribe();
//        dbScribe.checkDB();
        dbScribe.addToConfirmedList("Yo");
    }
    
    /**
     * To execute the program
     * 
     * @param ch User's choice
     * @param name User's name
     * @param datetime Timing of departure of the shuttle
     * @param destination Destination of the 
     * @throws ParseException
     * @return Whether success or failure
     */
    public static int manager(int ch, String name, String datetime, String destination) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date currentDate = new Date();
        Date selectedDate = sdf.parse(datetime);
        if(selectedDate.after(currentDate))
        {
            Shuttle toModify = Bookings.toBeChanged(datetime, destination);
            switch(ch)
            {
                case 0:
                    return Bookings.book(name, toModify);
                case 1:
                    return Bookings.cancelBooking(name, toModify);
                case 2:
                    return Bookings.checkIfBooked(name, toModify);
                default:
                    return -1;
            }
        }
        return -1;
    }
    
    /**To run the program
     * 
     * @param args not used
     * @throws IOException 
     */
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
    }

    //MainApplication ends
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


