package in.edu.ashoka.TransportApp;


/**
 * Created by Mayukh Nair on 11-Dec-16.
 */
class ShuttleTest extends GroovyTestCase {
    void testEquals() {
        Shuttle testShuttle3 = new Shuttle("12-12-2016 09:00", "Campus", 12);
        Shuttle testShuttle4 = new Shuttle("12-12-2016 09:00", "Campus", 12);
        Shuttle testShuttle5 = new Shuttle("12-12-2016 09:00", "Jahangirpuri", 12);
        Shuttle testShuttle6 = new Shuttle("12-12-2016 10:00", "Campus", 12);
        assertTrue(testShuttle3.equals(testShuttle4)==true);
        assertTrue(testShuttle3.equals(testShuttle5)==false);
        assertTrue(testShuttle3.equals(testShuttle6)==false);
    }

    void testBookSeat() {
        Shuttle testShuttle1  = new Shuttle("13-12-2016 09:00", "Campus", 12);
        for(int i=1; i<=12; i++){
            assertEquals(testShuttle1.bookSeat("A"), 1);
        }
        for(int i=1; i<=12; i++){
            assertEquals(testShuttle1.bookSeat("B"), 0);
        }
        assertTrue(testShuttle1.bookSeat("C")==2);
    }

    void testCheckForBooking() {
        Shuttle testShuttle2 = new Shuttle("12-12-2016 09:00", "Campus", 12);
        testShuttle2.bookSeat("A");
        assertTrue(testShuttle2.checkForBooking("A")==1);
        assertTrue(testShuttle2.checkForBooking("B")==0);
    }
}
