import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
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
		return "";
	}

	@Test
	public void testIsAfter() {
		fail("Not yet implemented");
	}

	@Before
	public void setUp_sendMsg() {

	}

	@Test
	public void test_sendMsg() throws IOException {
		/** Method used: Boundary Value Testing **/
		String validHost = "localhost";
		String invalidHost = "asdfasdf";
		String msg = "Port 2929";

		int port_norm = 2929; // x_norm
		int port_min = 0; // x_min
		int port_min_plus = 1; // x_min+
		int port_max = 65535; // x_max
		int port_max_minus = 65534; // x_max-

		// robustness
		int port_min_minus = -1; // x_min-
		int port_max_plus = 65536; // x_max_+

		String response = "";
		// port_norm
		SocketServer ss = new SocketServer(port_norm, this);
		response = SocketClient.sendMsg(validHost, port_norm, msg);

		// port_min
		/*
		 * Throws connection problem. port 0 isn't available on my computer msg =
		 * "Port 0"; ss= new SocketServer(port_min, this); response
		 * =SocketClient.sendMsg(validHost, port_min, msg);
		 */
		// port_min+
		msg = "Port 1";
		ss = new SocketServer(port_min_plus, this);
		response = SocketClient.sendMsg(validHost, port_min_plus, msg);

		// port_max
		msg = "Port 65535";
		ss = new SocketServer(port_max, this);
		response = SocketClient.sendMsg(validHost, port_max, msg);

		// port_max-
		msg = "Port 65534";
		ss = new SocketServer(port_max_minus, this);
		response = SocketClient.sendMsg(validHost, port_max_minus, msg);

		// port_min-
		/*
		 * Illegal argument Exception msg = "Port -1"; ss = new
		 * SocketServer(port_min_minus, this); response =
		 * SocketClient.sendMsg(validHost, port_min_minus, msg);
		 */
		// port_max+
		/*
		 * Illegal argument Exception msg = "Port -1"; ss = new msg = "Port 65536"; ss =
		 * new SocketServer(port_max_plus, this); response =
		 * SocketClient.sendMsg(validHost, port_max_plus, msg);
		 */

	}

}
