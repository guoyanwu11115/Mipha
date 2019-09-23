package demo;

public class TestSocketServer {
    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.setPort(8088);
        socketServer.start();

    }
}
