package server.server;

import server.client.ClientController;
import server.client.ClientGUI;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private FileStorage fileStorage = new FileStorage();
    private ServerGUI serverGUI;
    private List<ClientController> clientControllers = new ArrayList<>();;
    private boolean work;


    public boolean isWork() {
        return work;
    }

    public void setServerView(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }

    public String getHistory() {
        return fileStorage.read();
    }

    public boolean connectUser(ClientController clientController) {
        if (!work){
            return false;
        }
        clientControllers.add(clientController);
        return true;
    }

    public void disconnectUser(ClientController clientController){
        clientController.getClientView().disconnectedFromServer();
    }
    public void message(String text){
        if (!work){
            return;
        }
        appendLog(text);
        answerAll(text);
        fileStorage.save(text);
    }
    private void answerAll(String text){
        for (ClientController clientController : clientControllers) {
            clientController.answerFromServer(text);
        }
    }
    private void appendLog(String text){
        serverGUI.getLog().append(text + "\n");
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public List<ClientController> getClientControllers() {
        return clientControllers;
    }
}
