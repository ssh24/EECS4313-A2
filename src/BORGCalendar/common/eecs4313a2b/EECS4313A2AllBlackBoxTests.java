import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

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
	}

}
