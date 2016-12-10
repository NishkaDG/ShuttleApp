import org.scalatest._

class ShuttleSpec extends FlatSpec {
	"A Shuttle" should "store bookings" in{
		val testShuttle1 = new Shuttle("13-12-2016 09:00", "Campus", 12)
		var rv = new Int
		for(var i <- 1 to 12){
			assert(testShuttle1.bookSeat("A")==1)
		}
		for(var i <- 13 to 24){
			assert(testShuttle1.bookSeat("B")==0)
		}
		assert(testShuttle1.bookSeat("C")==2)
		
	}
	it should "check booking of seats" in {
		val testShuttle2 = new Shuttle("14-12-2016 10:00", "Campus", 12)
		var rv = testShuttle2.bookSeat("A")
		assert(testShuttle2.checkForBooking("A")==1)
		assert(testShuttle2.checkForBooking("B")==0)
	}
	it should "be equal to another object with the same time and destination" in {
		val testShuttle3 = new Shuttle("12-12-2016 09:00", "Campus", 12)
		val testShuttle4 = new Shuttle("12-12-2016 09:00", "Campus", 12)
		val testShuttle5 = new Shuttle("12-12-2016 09:00", "Jahangirpuri", 12)
		val testShuttle6 = new Shuttle("12-12-2016 10:00", "Campus", 12)
		assert(testShuttle3.equals(testShuttle4)==True)
		assert(testShuttle3.equals(testShuttle5)==False)
		assert(testShuttle3.equals(testShuttle6)==False)
	}
}