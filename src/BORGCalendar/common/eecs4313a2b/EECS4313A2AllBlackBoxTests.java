import static org.junit.Assert.fail;

import org.junit.Test;

import net.sf.borg.common.SocketClient;

public class EECS4313A2AllBlackBoxTests {

	@Test
	public void testIsAfter() {
		fail("Not yet implemented");
	}

	@Test
	public void test_sendMsg() {
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

		// port_norm
		String response = SocketClient.sendMsg(invalidHost, port_norm, msg);
		response = SocketClient.sendMsg(validHost, port_norm, msg);

		// port_min
		msg = "Port 0";
		response = SocketClient.sendMsg(invalidHost, port_min, msg);
		response = SocketClient.sendMsg(validHost, port_min, msg);

		// port_min+
		msg = "Port 1";
		response = SocketClient.sendMsg(invalidHost, port_min_plus, msg);
		response = SocketClient.sendMsg(validHost, port_min_plus, msg);

		// port_max
		msg = "Port 65535";
		response = SocketClient.sendMsg(invalidHost, port_max, msg);
		response = SocketClient.sendMsg(validHost, port_max, msg);

		// port_max-
		msg = "Port 65534";
		response = SocketClient.sendMsg(invalidHost, port_max_minus, msg);
		response = SocketClient.sendMsg(validHost, port_max_minus, msg);

		// port_min-
		msg = "Port -1";
		response = SocketClient.sendMsg(invalidHost, port_min_minus, msg);
		response = SocketClient.sendMsg(validHost, port_min_minus, msg);

		// port_max+
		msg = "Port 65536";
		response = SocketClient.sendMsg(invalidHost, port_max_plus, msg);
		response = SocketClient.sendMsg(validHost, port_max_plus, msg);

	}

}
