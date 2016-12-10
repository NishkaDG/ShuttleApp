package in.edu.ashoka.TransportApp;

import java.util.*;

/**For maintaining Shuttle type objects. 
 * Each object contains a list of successful bookings, 
 * a list of waitlisted users, 
 * and the departure time of the shuttle.
 * 
 * @author Nishka
 */
public class Shuttle
{
    
    /** Stores the timing of departure of this shuttle */
    private String timing;
    /**Stores the destination of the shuttle */
    private String destination;
    /** Stores the names of users who have successfully booked seats */
    private String[] names;
    /** Stores the names of users on the waitlist */
    private String[] waitlist;
    /** Stores the number of seats yet to be booked */
    private int empty;
    /** Stores the number of places in the waitlist */
    private int numwaitlisted;
    /** Stores the number of seats in the shuttle */
    private int numSeats;
            
    /**Initialises a Shuttle-type object with empty lists.
     * 
     * @param t date and time of departure of the Shuttle
     * @param goingTo destination
     * @param ns the number of seats in the shuttle
     */
    Shuttle(String t, String goingTo, int ns)
    {
        numSeats=ns;
        empty=12;
        waitlist=initArray(new String[numSeats]);
        numwaitlisted=0;
        names=initArray(new String[numSeats]);
        timing=t;
        destination=goingTo;
    }
    
    /**Two Shuttle-type objects are equal if they depart the same place at the same time.
     * 
     * @param sh2 the shuttle ot be compared
     * @return true or false
     */
    public boolean equals(Shuttle sh2)
    {
        return((this.getTiming()).equals(sh2.getTiming()) && (this.getDestination()).equals(sh2.getDestination()));
    }
    
    /**Returns the hashcode of the object.
     * 
     * @return String hashcode
     */
    public String hashcode()
    {
        return this.getTiming()+this.getDestination();
    }
    
    /**Returns the time of departure
     * 
     * @return timing
     */
    public String getTiming()
    {
        return this.timing;
    }
    
    /**Returns the destination
     * 
     * @return destination
     */
    public String getDestination()
    {
        return this.destination; 
    }
    
    /**Returns how many seats are yet to be booked in this shuttle.
     * 
     * @return 
     */
    int howmanyempty()
    {
        return this.empty;
    }
    
    /**Returns the names of confirmed users in String format for easy reference.
     * 
     * @return the String of names of users who have booked
     */
    String getBookings()
    {
        String succBooked="";
        for(int i=0; i<names.length; i++)
        {
            if(!names[i].isEmpty())
            {
                succBooked=succBooked+names[i]+",";
            }
        }
        return succBooked;
    }
    
    /**Returns the names of the people on the waitlist.
     * 
     * @return the entries in the array witlist[] in String form
     */
    String getWaitlist()
    {
        String w="";
        for(int i=0; i<waitlist.length; i++)
        {
            if(!waitlist[i].isEmpty())
            {
                w=w+waitlist[i]+",";
            }
        }
        return w;
    }
    
    /**Creates an empty String array 
     * 
     * @param a the array to be initalised
     * @return 
     */
    String[] initArray(String[] a)
    {
        String[] b=new String[a.length];
        for(int j=0; j<a.length; j++)
        {
            b[j]="";
        }
        return b;
    }
    
    /**Takes an array and removes the empty entries. 
     * Useful for consolidating all the bookings after cancellations or other changes.
     * 
     * @param arr the array to be modified
     */
    void restructure(String[] arr)
    {
        String[] c=initArray(new String[12]);
        int ctr=0;
        int e=0;
        for(int i=0; i<arr.length; i++)
        {
            if(!arr[i].isEmpty())
            {
                c[ctr]=arr[i];
                ctr++;
            }
            else
            {
                e++;
            }
        }
        arr=c;
        this.empty=e;
    }
    
    /**Books a seat, i.e adds user name to names[] or waitlist[]
     * 
     * @param id name of the user
     * @return 1 if s was added to names[]; 0 if added to waitlist[]; 2 if both were full
     */
    int bookSeat(String id)
    {
        if(this.empty>0)
        {
<<<<<<< HEAD
            this.names[this.numSeats-this.empty]=id;
=======
            this.names[12-this.empty]=id;
>>>>>>> 907949bf5bec8893bd2e5230d202b34c193a973b
            this.empty--;
            return 1;
        }
        else
        {
            try
            {
                this.waitlist[numwaitlisted+1]=id;
                return 0;
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                return 2;
            }
        }
    }
    
    /**Cancels a booking.
     * 
     * @param id the name of the user
     */
    void cancel(String id)
    {
        String[] b=this.names;
        String[] w=this.waitlist;
        int e=this.empty;
        for(int i=0; i<12-e; i++)
        {
            if(b[i].equals(id))
            {
                b[i]="";
                if(w.length>0)
                {
                    String jb=w[0];
                    int r=this.bookSeat(w[0]);
                    w[0]="";
                    this.restructure(w);
                    this.numwaitlisted=this.numwaitlisted-1;
                }
                this.empty=this.empty+1;
                this.restructure(b);
            }
        }
    }
    
    
    /**Returns whether or not a user has a confirmed seat in this shuttle.
     * 
     * @param id the user's name
     * @return whether or not a seat has been booked for the user
     */
    int checkForBooking(String id)
    {
        for(int i=0; i<this.names.length; i++)
        {
            if(this.names[i].equals(id))
            {
                return 1;
            }
        }
        return 0;
    }
}