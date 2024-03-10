package server;

import server.client.ClientController;
import server.client.ClientGUI;
import server.server.ServerController;
import server.server.ServerGUI;

public class Main {
    public static void main(String[] args) {

        //создание объектов сервера и создание связи между ними
        ServerGUI serverGUI = new ServerGUI();
        ServerController serverController = new ServerController();
        serverController.setServerView(serverGUI);
        serverGUI.setServerController(serverController);

        //создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClient(clientController1);
        clientController1.setServer(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClient(clientController2);
        clientController2.setServer(serverController);
    }
}
