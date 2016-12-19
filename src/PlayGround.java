import backend.websocket_client.SimpleClient;
import backend.websocket_client.TextClient;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayGround {
	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		TextClient client = new TextClient(new URI("ws://localhost:8886/websocket/"));

		client.connect();
	}
}
