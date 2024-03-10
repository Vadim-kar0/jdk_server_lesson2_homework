package server.server;

public interface ServerView {
    void showMessage(String text);
    void disconnect();
    void connect();
}
