package backend.websocket_client;

import middleware.engine.GameModel;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Created by JiajunChen on 2016/12/16.
 */
public class SimpleClient extends WebSocketClient {

    String aLastMessage;
    private boolean isConnected;

    public SimpleClient(URI serverURI) {
        super(serverURI);
        aLastMessage = null;
        isConnected = false;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        isConnected = true;
    }

    @Override
    public void onMessage(String s) {
        GameModel.getInstance().receiveMessage(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        isConnected = false;
    }

    @Override
    public void onError(Exception e) {

    }

    public boolean isConnected(){
        return isConnected;
    }
}
