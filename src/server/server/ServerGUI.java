package server.server;

import server.client.ClientController;
import server.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//класс требуется разделить на GUI, controller и repository (смотри схему проекта)
public class ServerGUI extends JFrame implements ServerView{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    ServerController serverController;

    JButton btnStart, btnStop;
    JTextArea log;

    public JTextArea getLog() {
        return log;
    }

    public ServerGUI(){
        setting();
        createPanel();
        setVisible(true);
    }

    private void setting(){
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverController.isWork()){
                    showMessage("Сервер уже был запущен");
                } else {
                    serverController.setWork(true);
                    showMessage("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverController.isWork()){
                    showMessage("Сервер уже был остановлен");
                } else {
                    serverController.setWork(false);
                    disconnect();
                    showMessage("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    @Override
    public void showMessage(String text) {
        log.append(text + "\n");
    }

    @Override
    public void disconnect() {
        for (ClientController clientController : serverController.getClientControllers()) {
            clientController.disconnectedFromServer();
        }
    }

    @Override
    public void connect() {

    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }
}
