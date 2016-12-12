/**Package declaration. */
package in.edu.ashoka.TransportApp;

/**Importing necessary packages and classes.
 */
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**Contains code for the core functionality of the project,i.e
 * maintaining bookings across all shuttles for 9 days and 
 * enabling changes when the appropriate methods are called.
 * 
 * @author Nishka
 */
public class Bookings {

    /**Contains key, value pairs of the departure timings from campus and the Shuttle-type objects */
    private static HashMap<String, Shuttle> fromCampus = new HashMap<String, Shuttle>();
    /**Contains key, value pairs of the departure timings from Delhi and the Shuttle-type objects */
    private static HashMap<String, Shuttle> fromStation = new HashMap<String, Shuttle>();
    /**Contains the departure timings from campus */
    private static ArrayList fcTimings= new ArrayList();
    /**Contains the departure timings from the station*/
    private static ArrayList fsTimings= new ArrayList();
    
    /**This method runs when the program begins. 
     * It creates the Shuttle objects for the present day and the next 8 days
     * 
     * @throws IOException in case of errors
     */
    static void initialise() throws IOException {
        Calendar cal=Calendar.getInstance();
        for(int i=0; i<=8; i++)
        {
            createNextDay(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
            cal.add(Calendar.DATE, 1);
        }
    }
    
    /**Takes a date as day, month, year.
     * Then calculates the day of week, finds the relevant file and calls createLists().
     * 
     * @param day the day of the month
     * @param month the number of the month
     * @param year the  year
     * @throws IOException 
     */
    static void createNextDay(int day, int month, int year) throws IOException
    {
        Calendar cal = Calendar.getInstance();
        String formattedDate = day + "-" + (month+1) + "-" + year;
        Calendar newCal=Calendar.getInstance();
        newCal.set(year, month, day);
        String whichFile = getFileName(newCal.get(Calendar.DAY_OF_WEEK));
        String wdaycj = "C:/ShuttleApp/campus_to_jahangirpuri_"+whichFile+".txt";
        String wdayjc = "C:/ShuttleApp/jahangirpuri_to_campus_"+whichFile+".txt";
        File cfile = new File(wdaycj);
        File jfile = new File(wdayjc);
        createLists(cfile, formattedDate, "Jahangirpuri", fromCampus, fcTimings);
        createLists(jfile, formattedDate, "Campus", fromStation, fsTimings);
        }
    
    /**Returns which category - weekday, weekend or Friday - is required.
     * 
     * @param day the number of the day of week
     * @return which category of the schedule should be read
     */
    private static String getFileName(int day) {
        if (day >= 2 && day <= 5) {
            return "weekday";
        } else if (day == 6) {
            return "friday";
        } else {
            return "weekend";
        }
    }
    
    /**Reads Shuttle timings from a file and adds the relevant Shuttle type object
     * to the respective hashmap.
     * 
     * @param address the address of the file with the timings
     * @param date the date for which the timings are to be read
     * @param goingTo the destination
     * @param hl the relevant Hashmap
     * @param a1 the relevant ArrayList
     * @throws IOException in case of errors
     */
    static void createLists(File address, String date, String goingTo, HashMap h1, ArrayList a1) throws IOException {

        System.out.println("Now creating for day "+date);
        FileReader timings = null;
        try {
            timings = new FileReader(address);//attempts to read the file
            int fc;
            String t = "";
            /**
             * Reads lines of timings from the file.
             */
            while ((fc = timings.read()) != -1) {
                if (fc == 10) {
                    t = t.replaceAll("\\s+", "");
                    t = date + " " + t;
                    //System.out.println("Working for "+t);
                    Shuttle nextOne = new Shuttle(t, goingTo, 12);
                    h1.put(t, nextOne);
                    a1.add(t);
                    t = "";
                } else {
                    t = t + (char) fc;
                }
            }
            if (t.length() > 0) {
                t=date+" "+t;
                Shuttle nextOne = new Shuttle(t, goingTo, 12);
                h1.put(t, nextOne);
                a1.add(t);
            }
        } finally {
            if (timings != null) {
                timings.close();
            }
        }
    }
        
    /**Books the seats of the intended shuttle by calling the relevant object from the right hashmap.
     * 
     * @param id the name of the user 
     * @param toBook the shuttle to book a seat on
     * @return 1 for successful booking; 0 for waitlisted; 2 for waitlist full; -1 for incorrect details or failure
     */
    private static int book(String id, Shuttle toBook) {
        try{
            return toBook.bookSeat(id);
        }
        catch(NullPointerException e)
        {
            return -1;
        }
    }
    
    /**Cancels the seats of the intended shuttle by calling the relevant Shuttle object from the right hashmap
     * 
     * @param id the name of the user
     * @param toCancel the shuttle to book a seat on
     * @return 1 if cancelled, -1 if incorrect details
     */
    private static int cancelBooking(String id, Shuttle toCancel) {
        try{
            toCancel.cancel(id);
            return 1;
        }
        catch(NullPointerException e)
        {
            return -1;
        }
    }
    
    /**Checks if user's name has been added to the list of bookings in a shuttle.
     * 
     * @param id the name of the user
     * @param toCheck the shuttle to be checked
     * @return True if booked, false otherwise
     */
    private static int checkIfBooked(String id, Shuttle toCheck) {
        try{
            return toCheck.checkForBooking(id);
        }
        catch(NullPointerException e){
            return -1;
        }
    }
    
    /**Returns the Shuttle object to be modified.
     * 
     * @param timing the timing
     * @param destination the destination
     * @return the Shuttle object in the hashmap with key identical to timing
     */
    public static Shuttle toBeChanged(String timing, String destination) {
        System.out.println("Now in toBeChanged");
        System.out.println(timing);
        System.out.println(destination);
        HashMap<String, Shuttle> toModify;
        if (destination.equals("Campus")) {
            toModify = fromStation;
        } else {
            toModify = fromCampus;
        }
        Shuttle toBookOrCancel = toModify.get(timing);
        return toBookOrCancel;
    }

    /**Enables safe modification of the hashmaps
     * 
     * @param currDate the date and time to be compared against
     * @param currMap the Hashmap to be modified
     * @param ch the user's choice (whether to get details for SMS or to delete old data)
     * @param managerData[] Will be used to pass data from method manager()
     * @return String[] the Array to be returned
     */
    static synchronized String[] accessData(String currDate, String currMap, int ch, String[] managerData)
    {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String[] rv=new String[2];
        rv[0]="";
        rv[1]="";
        switch(ch){
            case 1:
                return fiveMinutesBefore(currDate, currMap);
            case 2:
                if(currMap.equals("Campus")){
                    cleanUp(currDate, 0);
                }
                else{
                    cleanUp(currDate, 1);
                }
                return rv;
            case 3:
                rv[0]=Integer.toString(runEverything(managerData));
                rv[1]="";
                return rv;
            default:
                System.out.println("Error in method accessData");
                return rv;
        }
        }
    
    /**Takes a hashmap and removes all entries for shuttles which have departed.
     * 
     * @param pastDate the date which has passed
     * @param i 1 for cleaning HashMap fromCampus; 2 for cleaning HashMap fromStation
     */
    private static void cleanUp(String pastDate, int i)
    {
        HashMap<String, Shuttle> toModify;
        if(i==1)
        {
            toModify=fromCampus;
        }
        else
        {
            toModify=fromStation;
        }
        Iterator it=toModify.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Shuttle> entry=(Map.Entry<String, Shuttle>) it.next();
            String beingChecked=entry.getKey();
            if(isAfter(beingChecked, pastDate))
            {
                it.remove();
            }
        }
    }
    
    /**Checks if one date and time is after the other
     * 
     * @param date1 the first date
     * @param date2 the date to compare
     * @return whether date2 is before date1
     */
    private static boolean isAfter(String date1, String date2) 
    {
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            Date day1=sdf.parse(date1);
            Date day2=sdf.parse(date2);
            return day1.after(day2);
        }
        catch(ParseException p){
            return false;
        }
    }
    
    /**Returns an array containing the names of the successful bookings and the names of the waitlisted users.
     * Names are separated by commas.
     * 
     * @param timing the timing of the shuttle
     * @param destination the destination
     * @return Array containing the names
     */
    private static String[] forSMS(String timing, String destination)
    {
        String[] toSend = new String[2];
        Shuttle tbc = toBeChanged(timing, destination);
        try{
            toSend[0]=tbc.getBookings();
            toSend[1]=tbc.getWaitlist();
        }
        catch(NullPointerException e){
            toSend[0]="";
            toSend[1]="";
        }
        return toSend;
    }
    
    /**Enables booking, cancellation and confirmation.
     * 
     * @param details User's choice and details
     * @return 1 if successful; -1 if error; 0 if waitlisted (for booking attempt) and 2 for waitlist full
     */
    private static int runEverything(String[] details){
        try{
            int ch=Integer.parseInt(details[0]);
            String name=details[1];
            String datetime=details[2];
            String destination=details[3];
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Calendar cd=Calendar.getInstance();
            Date currentDate = sdf.parse(cd.get(Calendar.DAY_OF_MONTH)+"-"+cd.get(Calendar.MONTH)+"-"+cd.get(Calendar.YEAR)+" "+cd.get(Calendar.HOUR)+":"+cd.get(Calendar.MINUTE));
            Date selectedDate = sdf.parse(datetime);
            if(selectedDate.after(currentDate))
            {
                Shuttle toModify = Bookings.toBeChanged(datetime, destination);
                //System.out.println(toModify);
                switch(ch)
                {
                    case 0:
                        int r=Bookings.book(name, toModify);
                        System.out.println(r);
                        return r;
                    case 1:
                        return Bookings.cancelBooking(name, toModify);
                    case 2:
                        return Bookings.checkIfBooked(name, toModify);
                    default:
                        return -1;
                }
            }
            else{
            System.out.println("Oops! Main Application has failed; Check the Timing!");
            return -1;
            }
            }
        catch(ParseException p){
            System.out.println("runEverything is unable to parse dates.");
            return -1;
        }
        catch(ArrayIndexOutOfBoundsException a){
            System.out.println("Too few fields passed to runEverything. Check input.");
            return -1;
        }
    }
    
    /**Returns the confirmed bookings and the waitlist for any shuttle leaving 
     * for destination currMap five minutes from currDate
     * 
     * @param currDate Current date and time
     * @param currMap Destination
     * @return 
     */
    private static String[] fiveMinutesBefore(String currDate, String currMap){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String[] msgs=new String[2];
        msgs[0]="";
        msgs[1]="";
        try{
            Date d1 = format.parse(currDate);
            ArrayList timingsList;
            if(currMap.equals("Campus")){
            timingsList=fsTimings;
            }
            else{
                timingsList=fcTimings;
                }
            for(int j=0; j<timingsList.size(); j++)
            {
                Date d2=format.parse((timingsList.get(j)).toString());
                long diff=(d2.getTime()-d1.getTime())/(60*1000)%60;
                if(diff==5)
                {
                    msgs=forSMS((timingsList.get(j)).toString(), currMap);
                }
            }
            return msgs;
        }
        catch(ParseException p){
            return msgs;
        }
    }
}
