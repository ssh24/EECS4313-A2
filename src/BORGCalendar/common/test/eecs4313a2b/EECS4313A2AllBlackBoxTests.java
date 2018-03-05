package eecs4313a2b;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import net.sf.borg.common.DateUtil;
import net.sf.borg.common.SocketClient;
import net.sf.borg.common.SocketHandler;
import net.sf.borg.common.SocketServer;

public class EECS4313A2AllBlackBoxTests implements SocketHandler {

	/**
	 * process a socket message
	 */
	@Override
	public synchronized String processMessage(String msg) {
		return msg;
	}

	@Test
	public void test_sendMsg_WeakNormal() {
		/** Method used: Boundary Value Testing **/
		String validHost = "localhost";

		int port_norm = 2929; // x_norm
		int port_min = 0; // x_min
		int port_min_plus = 1; // x_min+
		int port_max = 65535; // x_max
		int port_max_minus = 65534; // x_max-

		String response = "";
		// port_norm
		String msg = "Port 2929";
		SocketServer ss = new SocketServer(port_norm, this);
		try {
			response = SocketClient.sendMsg(validHost, port_norm, msg);
			assertEquals("Testing if a localhost on port_norm sends a message", response, msg);
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
			assertEquals("Testing if a localhost on port_min sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// port_min+
		msg = "Port 1";
		try {
			ss = new SocketServer(port_min_plus, this);
			response = SocketClient.sendMsg(validHost, port_min_plus, msg);
			assertEquals("Testing if a localhost on port port_min+ sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max
		msg = "Port 65535";
		try {
			ss = new SocketServer(port_max, this);
			response = SocketClient.sendMsg(validHost, port_max, msg);
			assertEquals("Testing if a localhost on port port_max sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max-
		msg = "Port 65534";
		try {
			ss = new SocketServer(port_max_minus, this);
			response = SocketClient.sendMsg(validHost, port_max_minus, msg);
			assertEquals("Testing if a localhost on port_max- sends a message", response, msg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_sendMsg_WeakRobust() {
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
			assertEquals("Testing if a localhost on port_norm sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* Unknown host exception extends IOException */
		try {
			response = SocketClient.sendMsg(invalidHost, port_norm, msg);
			assertEquals("Testing if an invalid host on port_norm sends a message", response, msg);
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
			assertEquals("Testing if a localhost on port_min sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// port_min+
		msg = "Port 1";
		try {
			ss = new SocketServer(port_min_plus, this);
			response = SocketClient.sendMsg(validHost, port_min_plus, msg);
			assertEquals("Testing if a localhost on port port_min+ sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max
		msg = "Port 65535";
		try {
			ss = new SocketServer(port_max, this);
			response = SocketClient.sendMsg(validHost, port_max, msg);
			assertEquals("Testing if a localhost on port port_max sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max-
		msg = "Port 65534";
		try {
			ss = new SocketServer(port_max_minus, this);
			response = SocketClient.sendMsg(validHost, port_max_minus, msg);
			assertEquals("Testing if a localhost on port_max- sends a message", response, msg);

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
			assertEquals("Testing if a localhost on port_min- sends a message", response, msg);
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
			assertEquals("Testing if a localhost on port_max+ sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException iae) {
			fail("Ports > 65535 should be handled by the method");
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
	
	@Test
	public void testMinuteString() {
	
	//NINE Weak Normal Equivalence classes
	//Class 1: Minute/60 = 1 and Minute%60 = 0  (valid input) -- Testing 1 hour [Range: [1]]
	assertEquals("1 Hour",DateUtil.minuteString(60));
	//Class 2: Minute/60 > 1 and Minute%60 = 0 (valid input) --Testing hours more than one [Range:[2, infinity] hours] 
	assertEquals("3 Hours",DateUtil.minuteString(180));
	//-------------------------------------------------
	//Class 3: Minute/60 = 1 and Minute%60 > 1 (valid input) --Testing 1 hour with some minutes [Range:[1] hour and (1,59] minutes]
	assertEquals("1 Hour 15 Minutes",DateUtil.minuteString(75));
	//Class 4: Minute/60 = 1 and Minute%60 = 1 (valid input) --Testing 1 hour with 1 minute [Range:[1] hour and [1] minute]
	assertEquals("1 Hour 1 Minute",DateUtil.minuteString(61));
	//Class 5: Minute/60 > 1 and Minute%60 > 1 (valid input) --Testing hours more than 1 hour with some minutes [Range:[2,infinity] hours and (1,59] minutes]
	assertEquals("2 Hours 25 Minutes",DateUtil.minuteString(145));
	//Class 6: Minute/60 > 1 and Minute%60 = 1 (valid input) --Testing hours more than 1 hour with some minutes [Range:[2,infinity] hours and [1] minute]
	assertEquals("2 Hours 1 Minute",DateUtil.minuteString(121));
	//----------------------------------------------------------
	//Class 7: Minute/60 = 0 and Minute%60 = 0 (valid input) --Testing 0 minutes [Range:[0] minute]
	assertEquals("0 Minutes",DateUtil.minuteString(0));
	//Class 8: Minute/60 = 0 and Minute%60 = 1 (valid input) --Testing 1 minute [Range:[1] minute]
	assertEquals("1 Minute",DateUtil.minuteString(1));
	//Class 9: Minute/60 = 0 and Minute%60 > 1 (valid input) -- Testing minutes that are less than 1 hour [Range: (1,59] minutes]
	assertEquals("50 Minutes",DateUtil.minuteString(50));
	}
}
