package eecs4313a2w;

import static org.junit.Assert.*;
<<<<<<< HEAD

=======
<<<<<<< HEAD
import java.util.Date;
import net.sf.borg.common.*;
>>>>>>> 27b64f5... test to see if 88.5% coverage
import org.junit.Test;
import java.util.Date;

import net.sf.borg.common.*;
public class EECS4313A2AllWhiteBoxTests {
	
	@Test
	public void testIsAfter() {
		/** Method used: Decision Table Testing **/
		
		Date d1 = new Date(117, 11, 3);
		Date d2 = new Date(117, 11, 3);
		boolean result;
		
		// date d1 is equal to d2
		result = DateUtil.isAfter(d1, d2);
		assertFalse("Date d1 is equal to d2", result);
		
		// date d1 is before d2
		d1.setDate(2);
		result = DateUtil.isAfter(d1, d2);
		assertFalse("Date d1 is before d2", result);
		
		// date d1 is after d2
		d1.setDate(4);
		result = DateUtil.isAfter(d1, d2);
		assertTrue("Date d1 is after d2", result);
	}
<<<<<<< HEAD
	
	@Test
	public void testMinuteString() {
		/** Method used: Equivalence Class Testing **/
	
	//Hour
	//Class 1: Mins/60 = 1 and Mins%60 = 0 - Testing 1 hour [Range: [1]]
	assertEquals("1 Hour",DateUtil.minuteString(60));
	//Class 2: Mins/60 = 1 and Mins%60 = 1 -Testing 1 hour with 1 minute [Range:[1] hour and [1] minute]
	assertEquals("1 Hour 1 Minute",DateUtil.minuteString(61));
	//Class 3: Mins/60 = 1 and Mins%60 > 1 -Testing 1 hour with some minutes [Range:[1] hour and (1,59] minutes]
	assertEquals("1 Hour 15 Minutes",DateUtil.minuteString(75));
	//--------------------------------------------------------------------------------------------------------------
	//Hours
	//Class 4: Mins/60 > 1 and Mins%60 = 0 -Testing hours more than one [Range:(1, infinity) hours] 
	assertEquals("3 Hours",DateUtil.minuteString(180));
	//Class 5: Mins/60 > 1 and Mins%60 = 1 -Testing hours more than 1 hour with some minutes [Range:(1,infinity) hours and [1] minute]
	assertEquals("2 Hours 1 Minute",DateUtil.minuteString(121));
	//Class 6: Mins/60 > 1 and Mins%60 > 1 -Testing hours more than 1 hour with some minutes [Range:(1,infinity) hours and (1,59] minutes]
	assertEquals("2 Hours 25 Minutes",DateUtil.minuteString(145));
	//-----------------------------------------------------------------------------------------------------------------
	// Minutes
	//Class 7: Mins/60 = 0 and Mins%60 = 0 -Testing 0 minutes [Range:[0] minute]
	assertEquals("0 Minutes",DateUtil.minuteString(0));
	//Class 8: Mins/60 = 0 and Mins%60 = 1 -Testing 1 minute [Range:[1] minute]
	assertEquals("1 Minute",DateUtil.minuteString(1));
	//Class 9: Mins/60 = 0 and Mins%60 > 1 - Testing minutes that are less than 1 hour [Range: (1,59] minutes]
	assertEquals("50 Minutes",DateUtil.minuteString(50));
	// ----------------------------------------------------------------------------------------------------------------
	//based off the implmentation of negative inputs in the method [ Invalid inputs]
	//Class 10: Mins/60 < 0 and Mins%60 = 1 - Testing negative hours with negative minutes [Range:[(0,negative infinity)hour and [-1] minutes]
	assertEquals("1 Minute",DateUtil.minuteString(-61)); // (-61) mod 60 = 1 but the output is -1 
	//Class 11 : Mins/60 < 0 and Mins%60 > 1 - Teseing -1 minute [Range:[(0,negative infinity)hour and [-1] minute]
	assertEquals("10 Minutes",DateUtil.minuteString(-70)); // (-71) mod 60 = 10 but the output is -10	
	}

=======
=======
import java.io.IOException;
import org.junit.Test;
import net.sf.borg.common.*;
import java.util.Date;

import org.junit.Test;

public class EECS4313A2AllWhiteBoxTests implements SocketHandler {

    /**
     * process a socket message
     */
    @Override
    public synchronized String processMessage(String msg) {
        return msg;
    }

    @Test
    public void test_sendMsg() {
        /** Method used: Boundary Value Testing **/
        String validHost = "localhost";

        int port_norm = 2929; // x_norm
        int port_min = 0; // x_min
        int port_min_plus = 1; // x_min+
        int port_max = 65535; // x_max
        int port_max_minus = 65534; // x_max-

        // robustness
        String invalidHost = "asdfasdf";
        int port_min_minus = -1; // x_min-
        int port_max_plus = 65536; // x_max_+

        String response = "";
        // port_norm
        String msg = "Port 2929";
        SocketServer ss = new SocketServer(port_norm, this);
        try {
            response = SocketClient.sendMsg(validHost, port_norm, msg);
            assertTrue("Testing if a localhost on port_norm sends a message", response.equals(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Unknown host exception extends IOException */
        try {
            response = SocketClient.sendMsg(invalidHost, port_norm, msg);
            assertTrue("Testing if an invalid host on port_norm sends a message", response.equals(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // port_min
        /*
         * Throws connection problem. port 0 isn't available on my computer Connect
         * Exception extends Socket Exception which extends IOException
         */
        msg = "Port 0";
        try {
            ss = new SocketServer(port_min, this);
            response = SocketClient.sendMsg(validHost, port_min, msg);
            assertTrue("Testing if a localhost on port_min sends a message", response.equals(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // port_min+
        msg = "Port 1";
        try {
            ss = new SocketServer(port_min_plus, this);
            response = SocketClient.sendMsg(validHost, port_min_plus, msg);
            assertTrue("Testing if a localhost on port port_min+ sends a message", response.equals(msg));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // port_max
        msg = "Port 65535";
        try {
            ss = new SocketServer(port_max, this);
            response = SocketClient.sendMsg(validHost, port_max, msg);
            assertTrue("Testing if a localhost on port port_max sends a message", response.equals(msg));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // port_max
        msg = " ";
        try {
            ss = new SocketServer(port_max, this);
            response = SocketClient.sendMsg(validHost, 17501, msg);
            assertTrue("Testing if a localhost on port port_max sends a message", response.equals(msg));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // port_max-
        msg = "Port 65534";
        try {
            ss = new SocketServer(port_max_minus, this);
            response = SocketClient.sendMsg(validHost, port_max_minus, msg);
            assertTrue("Testing if a localhost on port_max- sends a message", response.equals(msg));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // port_min-
        /*
         * Illegal argument Exception
         */

        msg = "Port -1";
        try {
            ss = new SocketServer(port_min_minus, this);
            response = SocketClient.sendMsg(validHost, port_min_minus, msg);
            assertTrue("Testing if a localhost on port_min- sends a message", response.equals(msg));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            fail("Ports < 0 should be handled by the method");
        }

        // port_max+
        /*
         * Illegal argument Exception
         */
        msg = "Port 65536";
        try {
            ss = new SocketServer(port_max_plus, this);
            response = SocketClient.sendMsg(validHost, port_max_plus, msg);
            assertTrue("Testing if a localhost on port_max+ sends a message", response.equals(msg));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            fail("Ports > 65535 should be handled by the method");
        }
        
        // null??
        msg = "Port 17500";
        try {
            ss = new SocketServer(port_max, this);
            response = SocketClient.sendMsg(validHost, 17500, msg);
            assertTrue("Testing if a localhost on port port_max sends a message", response.equals(msg));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // null??
        msg = null;
        try {
            ss = new SocketServer(2090, this);
            response = SocketClient.sendMsg(validHost, 17502, msg);
            assertTrue("Testing if a localhost on port port_max sends a message", response.equals(msg));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //hits line 47
    @Test(expected = Exception.class)
    public void exceptionTest() {
    String msg = null;
    SocketServer ss;
    String response;
        try {
            ss = new SocketServer(2920, null);
            response = SocketClient.sendMsg("localhost", 2920, msg);
            assertTrue("Testing if a localhost on port port_max sends a message", response.equals(msg));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testIsAfter() {
        /** Method used: Decision Table Testing **/
        
        Date d1 = new Date(117, 11, 3);
        Date d2 = new Date(117, 11, 3);
        boolean result;
        
        // date d1 is equal to d2
        result = DateUtil.isAfter(d1, d2);
        assertFalse("Date d1 is equal to d2", result);
        
        // date d1 is before d2
        d1.setDate(2);
        result = DateUtil.isAfter(d1, d2);
        assertFalse("Date d1 is before d2", result);
        
        // date d1 is after d2
        d1.setDate(4);
        result = DateUtil.isAfter(d1, d2);
        assertTrue("Date d1 is after d2", result);
    }

>>>>>>> 9877383... test to see if 88.5% coverage
>>>>>>> 27b64f5... test to see if 88.5% coverage
}
