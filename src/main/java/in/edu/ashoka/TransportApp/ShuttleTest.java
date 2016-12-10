import org.junit.Test;
import static org.junit.Assert.*
import org.junit.framework.*;

public class ShuttleTest
{
	@Test
	public void testShuttles()
	{
		Shuttle testShuttle1  = new Shuttle("13-12-2016 09:00", "Campus", 12);
		Shuttle testShuttle3 = new Shuttle("12-12-2016 09:00", "Campus", 12);
		Shuttle testShuttle4 = new Shuttle("12-12-2016 09:00", "Campus", 12);
		Shuttle testShuttle5 = new Shuttle("12-12-2016 09:00", "Jahangirpuri", 12);
		Shuttle testShuttle6 = new Shuttle("12-12-2016 10:00", "Campus", 12);
		for(int i=1; i<=12; i++){
			assertTrue(testShuttle1.bookSeat("A")==1);
		}
		for(int i=13; i<=24; i++){
			assertTrue(testShuttle1.bookSeat("B")==0);
		}
		assertTrue(testShuttle1.bookSeat("C")==2);
		assertTrue(testShuttle1.checkForBooking("A")==1);
		assertTrue(testShuttle1.checkForBooking("B")==0);		
		assertTrue(testShuttle3.equals(testShuttle4)==True)
		assertTrue(testShuttle3.equals(testShuttle5)==False)
		assertTrue(testShuttle3.equals(testShuttle6)==False)
	}
}